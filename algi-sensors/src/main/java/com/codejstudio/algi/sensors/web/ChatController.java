package com.codejstudio.algi.sensors.web;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codejstudio.algi.common.IALGIRunnable;
import com.codejstudio.algi.common.web.JSONConstant;
import com.codejstudio.algi.common.web.MediaTypeWrapper;
import com.codejstudio.algi.common.web.SseEmitterUtil;
import com.codejstudio.algi.common.web.SseEmitterWrapper;
import com.codejstudio.algi.input.DefaultWebInput;
import com.codejstudio.algi.sensors.InputLauncher;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CloseUtil;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.LocaleConstant;
import com.codejstudio.lim.common.util.LogUtil;
import com.codejstudio.lim.common.util.StringUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
@Controller
@RequestMapping(value = ChatController.CHAT_CONTROLLER_REST_VALUE)
public class ChatController {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(ChatController.class);

	private static final String CHAT_CONTROLLER_REST_VALUE = "/chat";
	private static final String WEB_SENSOR_OUTPUT_CALLBACK_REST_VALUE = "/webSensorOutput";

	private static final Locale DEFAULT_LOCALE = Locale.PRC;

	private static final String BUNDLE_BASE_NAME = "properties.chat";
	private static final String BUNDLE_KEY_CHAT_GREETING = "chat.greeting";
	private static final String BUNDLE_KEY_CHAT_EMPTY_REPLY = "chat.empty_reply";


	/* variables */

	@Value("${sse.timeout}")
	private Long sseTimeout;

	@Value("${sse.reconnectTime}")
	private long sseReconnectTime;

	@Value("${sensors.serverPath}")
	private String sensorsServerPath;

	@Value("${inputs.web}")
	private String inputsWeb;


	/* variables: arrays, collections, maps, groups */

	private static final Map<String, SseEmitterWrapper> EMITTER_MAP;

	private static final Map<String, Locale> LOCALE_MAP;


	/* initializers */


	static {
		try {
			EMITTER_MAP = CollectionUtil.generateNewConcurrentMap();

			LOCALE_MAP = CollectionUtil.generateNewMap();
			LOCALE_MAP.put(LocaleConstant.LOCALE_ZH, Locale.PRC);
			LOCALE_MAP.put(LocaleConstant.LOCALE_US, Locale.US);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	/* class methods */

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public JSONObject greeting(final String lang) {
		log.debug(LogUtil.wrap("greeting(String lang), lang: " + lang));

		Locale locale = LOCALE_MAP.get(lang);
		ResourceBundle rb = ResourceBundle.getBundle(BUNDLE_BASE_NAME, 
				((locale != null) ? locale : DEFAULT_LOCALE));
		String greeting = rb.getString(BUNDLE_KEY_CHAT_GREETING);
		log.debug(LogUtil.wrap("greeting: " + greeting));

		JSONObject json = new JSONObject();
		json.put(JSONConstant.JSON_TEXT, greeting); 
		return json;
	}


	@RequestMapping(value = "/subscribe", method = RequestMethod.GET, 
			produces = MediaTypeWrapper.TEXT_EVENTSTREAM_UTF8_VALUE)
	@ResponseBody
	public void subscribe(final String id, final HttpServletRequest request, 
			final HttpServletResponse response) throws Exception {
		log.debug(LogUtil.wrap("subscribe(String id, HttpServletRequest request, " 
				+ "HttpServletResponse response), id=" + id));

		SseEmitterWrapper emitter = new SseEmitterWrapper(sseTimeout, request, response);
		EMITTER_MAP.put(id, emitter);
		emitter.onTimeout(() -> EMITTER_MAP.remove(id));
		emitter.onCompletion(() -> log.debug(LogUtil.wrap("onCompletion(()")));
		emitter.send(SseEmitterWrapper.event().reconnectTime(sseReconnectTime));
		SseEmitterUtil.initialize(emitter);
	}

	@RequestMapping(value = "/closeSse", method = RequestMethod.GET)
	@ResponseBody
	public void closeSse(final String id) throws IOException {
		new Thread() {
			@Override
			public void run() {
				log.debug(LogUtil.wrap("closeSse(String id), id=" + id));

				try {
					SseEmitterWrapper emitter = EMITTER_MAP.get(id);
					if (emitter != null) {
						emitter.send(SseEmitterWrapper.event().name("closeSse").id(id));
						emitter.complete();
						EMITTER_MAP.remove(id);
					}
				} catch (IllegalStateException e) {
					log.debug(LogUtil.wrap(e.toString() + ", " + e.getMessage()));
				} catch (Exception e) {
					log.error(e.toString(), e);
				}
			}
		}.start();
	}


	@RequestMapping(value = "/chat", method = RequestMethod.POST)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public void chat(@RequestBody final JSONObject requestJSON, final HttpServletRequest request) {
		log.debug(LogUtil.wrap("chat(@RequestBody JSONObject requestJSON, HttpServletRequest request)"));

		JSONObject json = new JSONObject();
		json.put(JSONConstant.JSON_ID, requestJSON.get(JSONConstant.JSON_ID));
		json.put(JSONConstant.JSON_TEXT, requestJSON.get(JSONConstant.JSON_TEXT));
		json.put(JSONConstant.JSON_LANGUAGE, requestJSON.get(JSONConstant.JSON_LANGUAGE));
		json.put(JSONConstant.JSON_CALLBACK, sensorsServerPath + request.getContextPath() 
				+ CHAT_CONTROLLER_REST_VALUE + WEB_SENSOR_OUTPUT_CALLBACK_REST_VALUE);
		requestWebSensorInput(json);
	}

	private void requestWebSensorInput(final JSONObject json) {
		new Thread() {
			@Override
			public void run() {
				log.debug(LogUtil.wrap("requestWebSensorInput(JSONObject json);"));

				ObjectOutputStream oos = null;
				try {
					Map<String, IALGIRunnable> iarm = InputLauncher.getInputARMap();
					IALGIRunnable iar = iarm.get(inputsWeb);
					if (iar instanceof DefaultWebInput) {
						DefaultWebInput dwi = (DefaultWebInput) iar;
						oos = dwi.getObjectOutputStream();
						oos.writeObject(json);
						oos.flush();
					}
				} catch (IOException e) {
					log.error(e.toString(), e);
				} finally {
					CloseUtil.quietlyClose(oos);
				}
			}
		}.start();
	}

	@RequestMapping(value = WEB_SENSOR_OUTPUT_CALLBACK_REST_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public void webSensorOutputCallback(@RequestBody final JSONObject requestJSON) {
		new Thread() {
			@Override
			public void run() {
				log.debug(LogUtil.wrap("webSensorOutputCallback(@RequestBody JSONObject requestJSON);"));

				try {
					String reply = (String) requestJSON.get(JSONConstant.JSON_REPLY);
					String id = (String) requestJSON.get(JSONConstant.JSON_ID);
					SseEmitterWrapper emitter = EMITTER_MAP.get(id);
					if (!StringUtil.isEmpty(reply)) {
						emitter.send(reply);
					} else {
						final String lang = (String) requestJSON.get(JSONConstant.JSON_LANGUAGE);
						Locale locale = LOCALE_MAP.get(lang);
						ResourceBundle rb = ResourceBundle.getBundle(BUNDLE_BASE_NAME, 
								((locale != null) ? locale : DEFAULT_LOCALE));
						String emptyReply = rb.getString(BUNDLE_KEY_CHAT_EMPTY_REPLY);
						emitter.send(emptyReply);
					}
				} catch (IllegalStateException e) {
					log.debug(LogUtil.wrap(e.toString() + ", " + e.getMessage()));
				} catch (Exception e) {
					log.error(e.toString(), e);
				}
			}
		}.start();
	}

}
