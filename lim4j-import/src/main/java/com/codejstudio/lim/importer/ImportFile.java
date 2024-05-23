package com.codejstudio.lim.importer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.JAXBContextProperties;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.PropertiesLoader;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
@XmlRootElement
public class ImportFile {

	/* constants */

	private static final String PROPERTIES_FILENAME_JAXB = "jaxb-import";


	/* variables */

	private static JAXBContext context;


	/* variables: arrays, collections, maps, groups */

	@XmlElement(name = "records", required = true)
	protected List<Record> recordList;


	/* constructors */

	/**
	 * for JAXB auto unmarshalling usage
	 */
	public ImportFile() {}


	/* initializers */

	private void initRecordList() throws LIMException {
		if (this.recordList == null) {
			this.recordList = CollectionUtil.generateNewList();
		}
	}


	/* destroyers */

	private void destroyRecordList() {
		if (this.recordList != null && this.recordList.size() == 0) {
			this.recordList = null;
		}
	}


	/* getters & setters */

	private static JAXBContext getContext() throws LIMException, JAXBException {
		if (context == null) {
			System.setProperty("javax.xml.bind.context.factory", 
					"org.eclipse.persistence.jaxb.JAXBContextFactory");
			context = org.eclipse.persistence.jaxb.JAXBContext.newInstance(ImportFile.class);
		}
		return context;
	}


	/* class methods */

	public boolean addRecord(final Record... records) throws LIMException {
		return addRecord((records == null) ? null : Arrays.asList(records));
	}

	public boolean addRecord(final Collection<? extends Record> recordCollection) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(recordCollection)) {
			return false;
		}

		try {
			initRecordList();
			return this.recordList.addAll(recordCollection);
		} finally {
			destroyRecordList();
		}
	}


	/* JAXB methods, for marshalling & unmarshalling processes */

	public void marshalToJson(final OutputStream os) throws LIMException {
		try {
			Marshaller msl = getContext().createMarshaller();

			String mediaType = PropertiesLoader.getProperty(PROPERTIES_FILENAME_JAXB, 
					JAXBContextProperties.MEDIA_TYPE);
			if (mediaType != null) {
				msl.setProperty(JAXBContextProperties.MEDIA_TYPE, mediaType);
			}

			String jsonIncludeRoot = PropertiesLoader.getProperty(PROPERTIES_FILENAME_JAXB, 
					JAXBContextProperties.JSON_INCLUDE_ROOT);
			if (jsonIncludeRoot != null) {
				msl.setProperty(JAXBContextProperties.JSON_INCLUDE_ROOT, Boolean.valueOf(jsonIncludeRoot));
			}

			String jaxbEncoding = PropertiesLoader.getProperty(PROPERTIES_FILENAME_JAXB, 
					Marshaller.JAXB_ENCODING);
			if (jaxbEncoding != null) {
				msl.setProperty(Marshaller.JAXB_ENCODING, jaxbEncoding);
			}

			String jaxbFormattedOutput = PropertiesLoader.getProperty(PROPERTIES_FILENAME_JAXB, 
					Marshaller.JAXB_FORMATTED_OUTPUT);
			if (jaxbFormattedOutput != null) {
				msl.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.valueOf(jaxbFormattedOutput));
			}

			String jaxbSchemaLocation = PropertiesLoader.getProperty(PROPERTIES_FILENAME_JAXB, 
					Marshaller.JAXB_SCHEMA_LOCATION);
			if (jaxbSchemaLocation != null) {
				msl.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, jaxbSchemaLocation);
			}

			String jaxbNoNamespaceSchemaLocation = PropertiesLoader.getProperty(PROPERTIES_FILENAME_JAXB, 
					Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION);
			if (jaxbNoNamespaceSchemaLocation != null) {
				msl.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, jaxbNoNamespaceSchemaLocation);
			}

			String jaxbFragment = PropertiesLoader.getProperty(PROPERTIES_FILENAME_JAXB, 
					Marshaller.JAXB_FRAGMENT);
			if (jaxbFragment != null) {
				msl.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.valueOf(jaxbFragment));
			}

			msl.marshal(this, os);
		} catch (JAXBException e) {
			throw LIMException.getLIMException(e);
		}
	}


	public static ImportFile unmarshalFromJson(final File file) throws LIMException {
		try {
			return unmarshalFromJson(new BufferedInputStream(new FileInputStream(file)));
		} catch (FileNotFoundException e) {
			throw LIMException.getLIMException(e);
		}
	}

	public static ImportFile unmarshalFromJson(final InputStream is) throws LIMException {
		return unmarshalFromJson(new StreamSource(is));
	}

	public static ImportFile unmarshalFromJson(final Reader reader) throws LIMException {
		return unmarshalFromJson(new StreamSource(reader));
	}

	public static ImportFile unmarshalFromJson(final URL url) throws LIMException {
		return unmarshalFromJson(new StreamSource(url.toExternalForm()));
	}

	public static ImportFile unmarshalFromJson(final StreamSource source) throws LIMException {
		try {
			Unmarshaller umsl = getContext().createUnmarshaller();

			String mediaType = PropertiesLoader.getProperty(PROPERTIES_FILENAME_JAXB, 
					JAXBContextProperties.MEDIA_TYPE);
			if (mediaType != null) {
				umsl.setProperty(JAXBContextProperties.MEDIA_TYPE, mediaType);
			}

			String jsonIncludeRoot = PropertiesLoader.getProperty(PROPERTIES_FILENAME_JAXB, 
					JAXBContextProperties.JSON_INCLUDE_ROOT);
			if (jsonIncludeRoot != null) {
				umsl.setProperty(JAXBContextProperties.JSON_INCLUDE_ROOT, Boolean.valueOf(jsonIncludeRoot));
			}

			return umsl.unmarshal(source, ImportFile.class).getValue();
		} catch (JAXBException e) {
			throw LIMException.getLIMException(e);
		}
	}



	/* inner classes */

	@XmlRootElement
	public static class Record {

		/* constants */

		public static final String ATTRIBUTE_NAME_RECORD_ID = "rid";
		public static final String ATTRIBUTE_NAME_ACTION_TYPE = "at";
		public static final String ATTRIBUTE_NAME_ELEMENT_ALIAS = "eas";
		public static final String ATTRIBUTE_NAME_ELEMENT_TYPE = "et";
		public static final String ATTRIBUTE_NAME_ACTION_SOURCE = "as";
		public static final String ATTRIBUTE_NAME_ACTION_NAME = "an";
		public static final String ATTRIBUTE_NAME_ACTION_PARAMETERS = "ap";

		public static final String ACTION_TYPE_NEW_GENERATION = "new_generation";
		public static final String ACTION_TYPE_NG = "ng";
		public static final String ACTION_TYPE_STATIC_CONSTANT_GETTING = "static_constant_getting";
		public static final String ACTION_TYPE_SCG = "scg";
		public static final String ACTION_TYPE_ELEMENT_OPERATION = "element_operation";
		public static final String ACTION_TYPE_EO = "eo";
		public static final String ACTION_TYPE_ELEMENT_OPERATION_GENERATION = "element_operation_generation";
		public static final String ACTION_TYPE_EOG = "eog";
		public static final String ACTION_TYPE_STATIC_OPERATION = "static_operation";
		public static final String ACTION_TYPE_SO = "so";
		public static final String ACTION_TYPE_STATIC_OPERATION_GENERATION = "static_operation_generation";
		public static final String ACTION_TYPE_SOG = "sog";

		static final char SYMBOL_ESCAPE_CHARACTER = '\\';
		static final char QUOTATIVE_SYMBOL_LEFT = '[';
		static final char QUOTATIVE_SYMBOL_RIGHT = ']';


		/* variables */

		@XmlAttribute(name = ATTRIBUTE_NAME_RECORD_ID, required = true)
		protected long recordId;

		@XmlAttribute(name = ATTRIBUTE_NAME_ACTION_TYPE, required = true)
		protected String actionType;

		@XmlAttribute(name = ATTRIBUTE_NAME_ELEMENT_ALIAS)
		protected String elementAlias;

		@XmlAttribute(name = ATTRIBUTE_NAME_ELEMENT_TYPE)
		protected String elementType;

		@XmlAttribute(name = ATTRIBUTE_NAME_ACTION_SOURCE)
		protected String actionSource;

		@XmlAttribute(name = ATTRIBUTE_NAME_ACTION_NAME)
		protected String actionName;


		/* variables: arrays, collections, maps, groups */

		@XmlElement(name = ATTRIBUTE_NAME_ACTION_PARAMETERS)
		protected List<String> actionParameterList;


		/* constructors */

		/**
		 * for JAXB auto unmarshalling usage
		 */
		public Record() {}

		public Record(long recordId, String actionType, String elementAlias, String elementType, 
				String actionSource, String actionName, String... actionParameters) throws LIMException {
			this.recordId = recordId;
			this.actionType = actionType;
			this.elementAlias = elementAlias;
			this.elementType = elementType;
			this.actionSource = actionSource;
			this.actionName = actionName;
			this.actionParameterList = CollectionUtil.convertToListOfSuperClass(actionParameters);
		}

	}

}
