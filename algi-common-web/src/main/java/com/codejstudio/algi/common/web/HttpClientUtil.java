package com.codejstudio.algi.common.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;

import com.codejstudio.algi.common.exception.ALGIException;
import com.codejstudio.lim.common.util.CloseUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public final class HttpClientUtil {

	/* static methods */

	public static final String sendPost(final String requestUrl, final JSONObject json) throws ALGIException {
		BufferedReader br = null;
		HttpPost postRequest = new HttpPost(requestUrl);
		try {
			postRequest.setHeader(HTTP.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
			postRequest.setEntity(new StringEntity(json.toJSONString(), Charsets.UTF_8));

			HttpClient client = HttpClients.createDefault();
			HttpResponse response = client.execute(postRequest);
			br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuilder sb = new StringBuilder();
			final String ls = System.getProperty("line.separator");
			for (String str; (str = br.readLine()) != null;) {
				sb.append(str + ls);
			}
			return sb.toString();
		} catch (Exception e) {
			throw ALGIException.getALGIException(e);
		} finally {
			postRequest.releaseConnection();
			CloseUtil.quietlyClose(br);
		}
	}

}
