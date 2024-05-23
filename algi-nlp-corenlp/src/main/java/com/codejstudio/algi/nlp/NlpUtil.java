package com.codejstudio.algi.nlp;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.common.PropertiesConstant;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CharacterUtil;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.LocaleConstant;
import com.codejstudio.lim.common.util.LogUtil;
import com.codejstudio.lim.common.util.PropertiesLoader;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.pipeline.StanfordCoreNLPClient;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public final class NlpUtil {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(NlpUtil.class);

	private static final String ZH_PROPERTIES = "StanfordCoreNLP-chinese";
	private static final String EN_PROPERTIES = "StanfordCoreNLP";

	private static final String PIPELINE_TYPE_LOCAL = "Local";
	private static final String PIPELINE_TYPE_CLIENT = "Client";

	private static final String DEFAULT_PIPELINE_TYPE = PIPELINE_TYPE_CLIENT;

	private static final String PROPERTY_KEY_HOST = "nlp.host";
	private static final String PROPERTY_KEY_PORT = "nlp.port";
	private static final String PROPERTY_KEY_THREADS = "nlp.threads";
	private static final String PROPERTY_KEY_LANGUAGE = "nlp.language";


	/* variables */

	private static Annotation annotationDocument;


	/* variables: arrays, collections, maps, groups */

	private static Map<String, String> localeMap = null;
	private static Map<String, AnnotationPipeline> pipelineMap = null;


	/* initializers */

	static {
		try {
			localeMap = CollectionUtil.generateNewMap();
			localeMap.put(LocaleConstant.LOCALE_ZH, ZH_PROPERTIES);
			localeMap.put(LocaleConstant.LOCALE_US, EN_PROPERTIES);

			pipelineMap = CollectionUtil.generateNewMap();
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	/* static methods */

	private static AnnotationPipeline initAndGetPipeline(final boolean forceFlag, final String language) {
		AnnotationPipeline pipeline = null;
		try {
			if (forceFlag || (pipeline = pipelineMap.get(language)) == null) {
				if (DEFAULT_PIPELINE_TYPE.equals(PIPELINE_TYPE_LOCAL)) {
					pipelineMap.put(language, (pipeline = getLocalPipeline(language)));
				} else if (DEFAULT_PIPELINE_TYPE.equals(PIPELINE_TYPE_CLIENT)) {
					pipelineMap.put(language, (pipeline = getClientPipeline(language)));
				}
			}
		} catch (LIMException e) {
			log.error(e.toString(), e);
		}
		return pipeline;
	}

	private static AnnotationPipeline getLocalPipeline(final String language) throws LIMException {
		return new StanfordCoreNLP(getProperties(language));
	}

	private static AnnotationPipeline getClientPipeline(String language) throws LIMException {
		String host;
		int port;
		int threads;

		host = PropertiesLoader.getProperty(
				PropertiesConstant.PROPERTIES_FILENAME_NLP_CONFIG, language + "." + PROPERTY_KEY_HOST);
		String p = PropertiesLoader.getProperty(
				PropertiesConstant.PROPERTIES_FILENAME_NLP_CONFIG, language + "." + PROPERTY_KEY_PORT);
		String t = PropertiesLoader.getProperty(
				PropertiesConstant.PROPERTIES_FILENAME_NLP_CONFIG, language + "." + PROPERTY_KEY_THREADS);
		log.debug(LogUtil.wrap(language + " properties(" 
				+ language + "." + PROPERTY_KEY_HOST + ": " + host + ", "
				+ language + "." + PROPERTY_KEY_PORT + ": " + p + ", "
				+ language + "." + PROPERTY_KEY_THREADS + ": " + t + ")"));

		if (!PropertiesLoader.isValid(host) || !PropertiesLoader.isValid(p) || !PropertiesLoader.isValid(t)) {
			language = PropertiesLoader.getProperty(
					PropertiesConstant.PROPERTIES_FILENAME_NLP_CONFIG, PROPERTY_KEY_LANGUAGE);
			host = PropertiesLoader.getProperty(
					PropertiesConstant.PROPERTIES_FILENAME_NLP_CONFIG, PROPERTY_KEY_HOST);
			p = PropertiesLoader.getProperty(
					PropertiesConstant.PROPERTIES_FILENAME_NLP_CONFIG, PROPERTY_KEY_PORT);
			t = PropertiesLoader.getProperty(
					PropertiesConstant.PROPERTIES_FILENAME_NLP_CONFIG, PROPERTY_KEY_THREADS);
			log.debug(LogUtil.wrap("default properties(" + PROPERTY_KEY_LANGUAGE + ": " + language + ", " 
					+ PROPERTY_KEY_HOST + ": " + host + ", " + PROPERTY_KEY_PORT + ": " + p + ", " 
					+ PROPERTY_KEY_THREADS + ": " + t + ")"));
		}

		if (!PropertiesLoader.isValid(host) || !PropertiesLoader.isValid(p) || !PropertiesLoader.isValid(t)) {
			throw LIMException.getLIMException("invalid default properties for nlp", 
					new IllegalArgumentException());
		}

		port = Integer.parseInt(p);
		threads = Integer.parseInt(t);

		return new StanfordCoreNLPClient(getProperties(language), host, port, threads);
	}

	private static Properties getProperties(final String language) throws LIMException {
		String propertiesFilename = localeMap.get(language);
		Properties props = (Properties) PropertiesLoader.getProperties(propertiesFilename).clone();
		log.debug(LogUtil.wrap("CoreNlpUtil.getProperties() props(" + props.size() + "): " + props));
		return props;
	}


	public static CoreDocument getDocument(final String inputText, final String language) {
		return new CoreDocument(getAnnotation(inputText, language));
	}

	public static Annotation getAnnotation(final String inputText, final String language) {
		if (!localeMap.keySet().contains(language)) {
			return getAnnotation(inputText);
		}
		annotationDocument = new Annotation(inputText);
		AnnotationPipeline pipeline = initAndGetPipeline(false, language);
		if (pipeline != null) {
			pipeline.annotate(annotationDocument);
		}
		return annotationDocument;
	}

	public static Annotation getAnnotation(final String inputText) {
		String language = CharacterUtil.isChinese(inputText) 
				? LocaleConstant.LOCALE_ZH : LocaleConstant.LOCALE_US;
		log.debug(LogUtil.wrap("The language of inputText is : " + language));

		return getAnnotation(inputText, language);
	}



	public static List<String> getSegmentation(final String inputText, final String language) 
			throws LIMException {
		List<CoreSentence> sentenceList = getDocument(inputText, language).sentences();
		if (CollectionUtil.checkNullOrEmpty(sentenceList)) {
			return null;
		}

		List<String> segmentList = CollectionUtil.generateNewList();
		for (CoreSentence sentence : sentenceList) {
			for (CoreLabel token : sentence.tokens()) {
				segmentList.add(token.word());
			}
		}
		return segmentList;
	}

}
