package com.codejstudio.algi.common.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.async.AsyncWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptor;
import org.springframework.web.context.request.async.WebAsyncManager;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.codejstudio.algi.common.web.ResponseBodyEmitterWrapper.Handler;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public final class SseEmitterUtil {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(SseEmitterUtil.class);


	/* static methods */

	public static final void initialize(final SseEmitterWrapper emitter) throws Exception {
		doDispatch(emitter);
	}

	/**
	 * Wrapped Method from {@code DispatcherServlet.doDispatch()}.
	 * (org.springframework.web.servlet)
	 */
	private static void doDispatch(final SseEmitterWrapper emitter) throws Exception {
		/*
		 * (HandlerAdapter) AbstractHandlerMethodAdapter.handle() -> 
		 * 		(org.springframework.web.servlet.mvc.method)
		 * RequestMappingHandlerAdapter.handleInternal() -> 
		 * 		(org.springframework.web.servlet.mvc.method.annotation)
		 * RequestMappingHandlerAdapter.invokeHandlerMethod();
		 * 		(org.springframework.web.servlet.mvc.method.annotation)
		 */
		invokeHandlerMethod(emitter);
	}

	/**
	 * Wrapped Method from {@code RequestMappingHandlerAdapter.invokeHandlerMethod()}.
	 * (org.springframework.web.servlet.mvc.method.annotation)
	 */
	private static void invokeHandlerMethod(final SseEmitterWrapper emitter) 
			throws Exception {
		HttpServletRequest request = emitter.getRequest();
		HttpServletResponse response = emitter.getResponse();

		ServletWebRequest webRequest = new ServletWebRequest(request, response);
		ModelAndViewContainer mavContainer = null;
		try {
			mavContainer = new ModelAndViewContainer();
			mavContainer.addAllAttributes(RequestContextUtils.getInputFlashMap(request));
			mavContainer.setIgnoreDefaultModelOnRedirect(false);

			AsyncWebRequest asyncWebRequest = WebAsyncUtils.createAsyncWebRequest(request, response);
			asyncWebRequest.setTimeout(emitter.getTimeout());

			WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);
			asyncManager.setTaskExecutor(new SimpleAsyncTaskExecutor("MvcAsync"));
			asyncManager.setAsyncWebRequest(asyncWebRequest);
			asyncManager.registerCallableInterceptors(new CallableProcessingInterceptor[0]);
			asyncManager.registerDeferredResultInterceptors(new DeferredResultProcessingInterceptor[0]);

			if (asyncManager.hasConcurrentResult()) {
				Object result = asyncManager.getConcurrentResult();
				mavContainer = (ModelAndViewContainer) asyncManager.getConcurrentResultContext()[0];
				asyncManager.clearConcurrentResult();
				if (log.isDebugEnabled()) {
					log.debug("Found concurrent result value [" + result + "]");
				}
			}
		}
		finally {
			/*
			 * ServletInvocableHandlerMethod.invokeAndHandle() -> 
			 * 		(org.springframework.web.servlet.mvc.method.annotation)
			 * HandlerMethodReturnValueHandlerComposite.handleReturnValue() -> 
			 * 		(org.springframework.web.method.support)
			 * (HandlerMethodReturnValueHandler) ResponseBodyEmitterReturnValueHandler.handleReturnValue();
			 * 		(org.springframework.web.servlet.mvc.method.annotation)
			 */
			handleReturnValue(emitter, webRequest, mavContainer);

			webRequest.requestCompleted();
		}
	}

	/**
	 * Wrapped Method from {@code ResponseBodyEmitterReturnValueHandler.handleReturnValue()}.
	 * (org.springframework.web.servlet.mvc.method.annotation)
	 */
	private static void handleReturnValue(final SseEmitterWrapper emitter, 
			final ServletWebRequest webRequest, final ModelAndViewContainer mavContainer) throws Exception {
		ServerHttpResponse outputMessage = null;
		DeferredResult<?> deferredResult = null;
		try {
			outputMessage = new ServletServerHttpResponse(emitter.getResponse());

			emitter.extendResponse(outputMessage);

			// Commit the response and wrap to ignore further header changes
			outputMessage.getBody();
			outputMessage.flush();
			outputMessage = new StreamingServletServerHttpResponseWrapper(outputMessage);

			deferredResult = new DeferredResult<Object>(emitter.getTimeout());
			WebAsyncUtils.getAsyncManager(webRequest)
					.startDeferredResultProcessing(deferredResult, mavContainer);
		}
		finally {
			HttpMessageConvertingHandlerWrapper handler 
					= new HttpMessageConvertingHandlerWrapper(outputMessage, deferredResult);
			emitter.initialize(handler);
		}
	}



	/* inner classes */

	/**
	 * Wrapped Class from {@code ResponseBodyEmitterReturnValueHandler$HttpMessageConvertingHandler}.
	 * (org.springframework.web.servlet.mvc.method.annotation)
	 */
	private static class HttpMessageConvertingHandlerWrapper implements Handler {

		/* variables */

		private final ServerHttpResponse outputMessage;
		private final DeferredResult<?> deferredResult;
		HttpMessageConverter<?> messageConverter;


		/* constructors */

		public HttpMessageConvertingHandlerWrapper(ServerHttpResponse outputMessage, 
				DeferredResult<?> deferredResult) {
			this.outputMessage = outputMessage;
			this.deferredResult = deferredResult;
			ApplicationContext context = ApplicationContextUtil.getApplicationContext();
			this.messageConverter = (context == null) 
					? null : (HttpMessageConverter<?>) context.getBean("stringHttpMessageConverter");
		}


		/* overridden methods */

		@Override
		public void send(final Object data, final MediaType mediaType) throws IOException {
			sendInternal(data, mediaType);
		}

		@SuppressWarnings("unchecked")
		private <T> void sendInternal(final T data, final MediaType mediaType) throws IOException {
			if (messageConverter.canWrite(data.getClass(), mediaType)) {
				((HttpMessageConverter<T>) messageConverter).write(data, mediaType, this.outputMessage);
				this.outputMessage.flush();
				if (log.isDebugEnabled()) {
					log.debug("Written [" + data + "] using [" + messageConverter + "]");
				}
				return;
			}
			throw new IllegalArgumentException("No suitable converter for " + data.getClass());
		}


		@Override
		public void complete() {
			this.deferredResult.setResult(null);
		}

		@Override
		public void completeWithError(final Throwable failure) {
			this.deferredResult.setErrorResult(failure);
		}

		@Override
		public void onTimeout(final Runnable callback) {
			this.deferredResult.onTimeout(callback);
		}

		@Override
		public void onCompletion(final Runnable callback) {
			this.deferredResult.onCompletion(callback);
		}

	}



	/* inner classes */

	/**
	 * Wrapped Class from {@code ResponseBodyEmitterReturnValueHandler$StreamingServletServerHttpResponse}.
	 * (org.springframework.web.servlet.mvc.method.annotation)
	 */
	private static class StreamingServletServerHttpResponseWrapper implements ServerHttpResponse {


		/* variables */

		private final ServerHttpResponse delegate;
		private final HttpHeaders mutableHeaders = new HttpHeaders();


		/* constructors */

		public StreamingServletServerHttpResponseWrapper(ServerHttpResponse delegate) {
			this.delegate = delegate;
			this.mutableHeaders.putAll(delegate.getHeaders());
		}


		/* overridden methods */

		@Override
		public void setStatusCode(final HttpStatus status) {
			this.delegate.setStatusCode(status);
		}

		@Override
		public HttpHeaders getHeaders() {
			return this.mutableHeaders;
		}

		@Override
		public OutputStream getBody() throws IOException {
			return this.delegate.getBody();
		}

		@Override
		public void flush() throws IOException {
			this.delegate.flush();
		}

		@Override
		public void close() {
			this.delegate.close();
		}
	}

}
