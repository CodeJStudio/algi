package com.codejstudio.algi.common.web;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpResponse;

/**
 * @Description Wrapped Class from {@code ResponseBodyEmitter}.
 * (org.springframework.web.servlet.mvc.method.annotation)
 * 
 * @see     
 * @since   algi_v1.0.0
 */
public class ResponseBodyEmitterWrapper {

	/* variables */

	private final Long timeout;

	private Handler handler;

	private boolean complete;

	private Throwable failure;

	private final DefaultCallback timeoutCallback = new DefaultCallback();
	private final DefaultCallback completionCallback = new DefaultCallback();


	/* variables: arrays, collections, maps, groups */

	private final Set<DataWithMediaType> earlySendAttempts = new LinkedHashSet<DataWithMediaType>(8);


	/* constructors */

	public ResponseBodyEmitterWrapper() {
		this.timeout = null;
	}

	public ResponseBodyEmitterWrapper(Long timeout) {
		this.timeout = timeout;
	}


	/* initializers */

	synchronized void initialize(final Handler handler) throws IOException {
		this.handler = handler;

		for (DataWithMediaType sendAttempt : this.earlySendAttempts) {
			sendInternal(sendAttempt.getData(), sendAttempt.getMediaType());
		}
		this.earlySendAttempts.clear();

		if (this.complete) {
			if (this.failure != null) {
				this.handler.completeWithError(this.failure);
			} else {
				this.handler.complete();
			}
		} else {
			this.handler.onTimeout(this.timeoutCallback);
			this.handler.onCompletion(this.completionCallback);
		}
	}


	/* getters & setters */

	public Long getTimeout() {
		return this.timeout;
	}


	/* class methods */

	protected void extendResponse(ServerHttpResponse outputMessage) {}

	public void send(Object object) throws IOException {
		send(object, null);
	}

	public synchronized void send(final Object object, final MediaType mediaType) throws IOException {
		sendInternal(object, mediaType);
	}

	private void sendInternal(final Object object, final MediaType mediaType) throws IOException {
		if (object != null) {
			if (this.handler != null) {
				try {
					this.handler.send(object, mediaType);
				} catch (IOException e) {
					completeWithError(e);
					throw e;
				} catch (Throwable t) {
					completeWithError(t);
					throw new IllegalStateException("Failed to send " + object, t);
				}
			} else {
				this.earlySendAttempts.add(new DataWithMediaType(object, mediaType));
			}
		}
	}


	public synchronized void complete() {
		this.complete = true;
		if (this.handler != null) {
			this.handler.complete();
		}
	}

	public synchronized void completeWithError(final Throwable failure) {
		this.complete = true;
		this.failure = failure;
		if (this.handler != null) {
			this.handler.completeWithError(failure);
		}
	}

	public synchronized void onTimeout(final Runnable callback) {
		this.timeoutCallback.setDelegate(callback);
	}

	public synchronized void onCompletion(final Runnable callback) {
		this.completionCallback.setDelegate(callback);
	}



	/* inner interface */

	interface Handler {

		/* interface methods */

		void send(Object data, MediaType mediaType) throws IOException;
		void complete();
		void completeWithError(Throwable failure);
		void onTimeout(Runnable callback);
		void onCompletion(Runnable callback);
	}



	/* inner classes */

	public static class DataWithMediaType {

		/* variables */

		private final Object data;
		private final MediaType mediaType;


		/* constructors */

		public DataWithMediaType(Object data, MediaType mediaType) {
			this.data = data;
			this.mediaType = mediaType;
		}


		/* getters & setters */

		public Object getData() {
			return this.data;
		}

		public MediaType getMediaType() {
			return this.mediaType;
		}
	}



	/* inner classes */

	private class DefaultCallback implements Runnable {

		/* variables */

		private Runnable delegate;


		/* getters & setters */

		public void setDelegate(Runnable delegate) {
			this.delegate = delegate;
		}


		/* overridden methods */

		@Override
		public void run() {
			ResponseBodyEmitterWrapper.this.complete = true;
			if (this.delegate != null) {
				this.delegate.run();
			}
		}
	}

}
