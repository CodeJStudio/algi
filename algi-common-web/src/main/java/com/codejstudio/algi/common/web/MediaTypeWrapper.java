package com.codejstudio.algi.common.web;

import java.nio.charset.Charset;

import org.springframework.http.MediaType;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 */
public class MediaTypeWrapper extends MediaType {

	/* constants */

	private static final long serialVersionUID = -2990605947075613954L;

	private static final String PARAMETER_CHARSET_UTF8 = ";charset=UTF-8";

	public static final String TEXT_EVENTSTREAM_UTF8_VALUE = TEXT_EVENT_STREAM_VALUE + PARAMETER_CHARSET_UTF8;
	public static final String TEXT_PLAIN_UTF8_VALUE = TEXT_PLAIN_VALUE + PARAMETER_CHARSET_UTF8;

	public static final MediaType TEXT_PLAIN_UTF8 = valueOf(TEXT_PLAIN_UTF8_VALUE);
	public static final MediaType TEXT_EVENTSTREAM_UTF8 = valueOf(TEXT_EVENTSTREAM_UTF8_VALUE);


	/* constructors */

	public MediaTypeWrapper(String type, String subtype, Charset charset) {
		super(type, subtype, charset);
	}

}
