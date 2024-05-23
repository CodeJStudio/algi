package com.codejstudio.algi.common.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;

/**
 * @Description Wrapped Class from {@code SseEmitter}.
 * (org.springframework.web.servlet.mvc.method.annotation)
 * 
 * @see     
 * @since   algi_v1.0.0
 */
public class SseEmitterWrapper extends ResponseBodyEmitterWrapper implements Serializable {

	/* constants */

	private static final long serialVersionUID = -505820123551837591L;


	/* variables */

	private HttpServletRequest request;
	private HttpServletResponse response;


	/* constructors */

	public SseEmitterWrapper(Long timeout, HttpServletRequest request, HttpServletResponse response) {
		super(timeout);
		setRequest(request);
		setResponse(response);
	}


	/* getters & setters */

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}


	/* rewritten methods */

	@Override
	protected void extendResponse(final ServerHttpResponse outputMessage) {
		super.extendResponse(outputMessage);

		HttpHeaders headers = outputMessage.getHeaders();
		if (headers.getContentType() == null) {
			headers.setContentType(MediaTypeWrapper.TEXT_EVENTSTREAM_UTF8);
		}
	}

	@Override
	public void send(final Object object, final MediaType mediaType) throws IOException {
		if (object != null) {
			send(event().data(object, mediaType));
		}
	}

	public void send(final SseEventBuilder builder) throws IOException {
		Set<DataWithMediaType> dataToSend = builder.build();
		synchronized (this) {
			for (DataWithMediaType entry : dataToSend) {
				super.send(entry.getData(), entry.getMediaType());
			}
		}
	}

	public static SseEventBuilder event() {
		return new SseEventBuilderImpl();
	}



	/* inner interface */

	public interface SseEventBuilder {

		/* interface methods */

		SseEventBuilder comment(String comment);
		SseEventBuilder name(String eventName);
		SseEventBuilder id(String id);
		SseEventBuilder reconnectTime(long reconnectTimeMillis);
		SseEventBuilder data(Object object);
		SseEventBuilder data(Object object, MediaType mediaType);
		Set<DataWithMediaType> build();
	}



	/* inner classes */

	private static class SseEventBuilderImpl implements SseEventBuilder {

		/* variables */

		private StringBuilder sb;


		/* variables: arrays, collections, maps, groups */

		private final Set<DataWithMediaType> dataToSend = new LinkedHashSet<DataWithMediaType>(4);


		/* overridden methods */

		@Override
		public SseEventBuilder comment(final String comment) {
			append(":").append((comment != null) ? comment : "").append("\n");
			return this;
		}

		@Override
		public SseEventBuilder name(final String name) {
			append("event:").append((name != null) ? name : "").append("\n");
			return this;
		}

		@Override
		public SseEventBuilder id(final String id) {
			append("id:").append((id != null) ? id : "").append("\n");
			return this;
		}

		@Override
		public SseEventBuilder reconnectTime(final long reconnectTimeMillis) {
			append("retry:").append(String.valueOf(reconnectTimeMillis)).append("\n");
			return this;
		}

		@Override
		public SseEventBuilder data(final Object object) {
			return data(object, null);
		}

		@Override
		public SseEventBuilder data(final Object object, final MediaType mediaType) {
			append("data:");
			saveAppendedText();
			this.dataToSend.add(new DataWithMediaType(object, mediaType));
			append("\n");
			return this;
		}

		@Override
		public Set<DataWithMediaType> build() {
			if (!StringUtils.hasLength(this.sb) && this.dataToSend.isEmpty()) {
				return Collections.<DataWithMediaType>emptySet();
			}
			append("\n");
			saveAppendedText();
			return this.dataToSend;
		}

		SseEventBuilderImpl append(final String text) {
			if (this.sb == null) {
				this.sb = new StringBuilder();
			}
			this.sb.append(text);
			return this;
		}

		private void saveAppendedText() {
			if (this.sb != null) {
				this.dataToSend.add(
						new DataWithMediaType(this.sb.toString(), MediaTypeWrapper.TEXT_PLAIN_UTF8));
				this.sb = null;
			}
		}
	}

}
