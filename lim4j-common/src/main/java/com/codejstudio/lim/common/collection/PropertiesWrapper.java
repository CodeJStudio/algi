package com.codejstudio.lim.common.collection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * <code>PropertiesWrapper</code> is rewritten for "<code>java.util.Properties</code>" 
 * to extend Hashtable&lt;String, String&gt;, instead of Hashtable&lt;Object,Object&gt;.
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class PropertiesWrapper extends Hashtable<String, String> {

	/* constants */

	private static final long serialVersionUID = 2081406128281026566L;


	/* variables */

	protected transient Properties properties;


	/* constructors */

	public PropertiesWrapper() {
		this(new Properties());
	}

	public PropertiesWrapper(Properties properties) {
		this.properties = properties;
	}


	/* rewritten methods */

	public String getProperty(final String key) {
		return this.properties.getProperty(key);
	}

	public String getProperty(final String key, final String defaultValue) {
		return this.properties.getProperty(key, defaultValue);
	}

	public synchronized Object setProperty(final String key, final String value) {
		return this.properties.setProperty(key, value);
	}

	public Enumeration<?> propertyNames() {
		return this.properties.propertyNames();
	}

	public Set<String> stringPropertyNames() {
		return this.properties.stringPropertyNames();
	}

	public void list(final PrintStream out) {
		this.properties.list(out);
	}

	public void list(final PrintWriter out) {
		this.properties.list(out);
	}

	public synchronized void load(final InputStream inStream) throws IOException {
		this.properties.load(inStream);
	}

	public synchronized void load(final Reader reader) throws IOException {
		this.properties.load(reader);
	}

	public void store(final OutputStream out, final String comments) throws IOException {
		this.properties.store(out, comments);
	}

	public void store(final Writer writer, final String comments) throws IOException {
		this.properties.store(writer, comments);
	}

	public synchronized void loadFromXML(final InputStream in) 
			throws IOException, InvalidPropertiesFormatException {
		this.properties.loadFromXML(in);
	}

	public void storeToXML(final OutputStream os, final String comment) throws IOException {
		this.properties.storeToXML(os, comment);
	}

	public void storeToXML(final OutputStream os, final String comment, final String encoding) 
			throws IOException {
		this.properties.storeToXML(os, comment, encoding);
	}


	/* overridden methods */

	@Override
	public synchronized void clear() {
		this.properties.clear();
	}

	@Override
	public synchronized Object clone() {
		return this.properties.clone();
	}

	@Override
	@SuppressWarnings("unchecked")
	public synchronized String compute(final String key, 
			final BiFunction<? super String, ? super String, ? extends String> remappingFunction) {
		return (String) this.properties.compute(key, 
				(BiFunction<? super Object, ? super Object, ? extends Object>) remappingFunction);
	}

	@Override
	@SuppressWarnings("unchecked")
	public synchronized String computeIfAbsent(final String key, 
			final Function<? super String, ? extends String> mappingFunction) {
		return (String) this.properties.computeIfAbsent(key, 
				(Function<? super Object, ? extends Object>) mappingFunction);
	}

	@Override
	@SuppressWarnings("unchecked")
	public synchronized String computeIfPresent(final String key, 
			final BiFunction<? super String, ? super String, ? extends String> remappingFunction) {
		return (String) this.properties.computeIfPresent(key, 
				(BiFunction<? super Object, ? super Object, ? extends Object>) remappingFunction);
	}

	@Override
	public synchronized boolean contains(final Object value) {
		return this.properties.contains(value);
	}

	@Override
	public synchronized boolean containsKey(final Object key) {
		return this.properties.containsKey(key);
	}

	@Override
	public boolean containsValue(final Object value) {
		return this.properties.containsValue(value);
	}

	@Override
	@SuppressWarnings("unchecked")
	public synchronized Enumeration<String> elements() {
		return (Enumeration<String>)(Object) this.properties.elements();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Map.Entry<String, String>> entrySet() {
		return (Set<java.util.Map.Entry<String, String>>)(Object) this.properties.entrySet();
	}

	@Override
	public synchronized boolean equals(final Object object) {
		return this.properties.equals(object);
	}

	@Override
	@SuppressWarnings("unchecked")
	public synchronized void forEach(final BiConsumer<? super String, ? super String> action) {
		this.properties.forEach((BiConsumer<? super Object, ? super Object>) action);
	}

	@Override
	public synchronized String get(final Object key) {
		return (String) this.properties.get(key);
	}

	@Override
	public synchronized String getOrDefault(final Object key, final String defaultValue) {
		return (String) this.properties.getOrDefault(key, defaultValue);
	}

	@Override
	public synchronized int hashCode() {
		return this.properties.hashCode();
	}

	@Override
	public synchronized boolean isEmpty() {
		return this.properties.isEmpty();
	}

	@Override
	@SuppressWarnings("unchecked")
	public synchronized Enumeration<String> keys() {
		return (Enumeration<String>)(Object) this.properties.keys();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<String> keySet() {
		return (Set<String>)(Object) this.properties.keySet();
	}

	@Override
	@SuppressWarnings("unchecked")
	public synchronized String merge(final String key, final String value, 
			final BiFunction<? super String, ? super String, ? extends String> remappingFunction) {
		return (String) this.properties.merge(key, value, 
				(BiFunction<? super Object, ? super Object, ? extends Object>) remappingFunction);
	}

	@Override
	public synchronized String put(final String key, final String value) {
		return (String) this.properties.put(key, value);
	}

	@Override
	public synchronized void putAll(final Map<? extends String, ? extends String> map) {
		this.properties.putAll(map);
	}

	@Override
	public synchronized String putIfAbsent(final String key, final String value) {
		return (String) this.properties.putIfAbsent(key, value);
	}

	@Override
	protected void rehash() {
		throw new UnsupportedOperationException();
	}

	@Override
	public synchronized String remove(final Object key) {
		return (String) this.properties.remove(key);
	}

	@Override
	public synchronized boolean remove(final Object key, final Object value) {
		return this.properties.remove(key, value);
	}

	@Override
	public synchronized String replace(final String key, final String value) {
		return (String) this.properties.replace(key, value);
	}

	@Override
	public synchronized boolean replace(final String key, final String oldValue, final String newValue) {
		return this.properties.replace(key, oldValue, newValue);
	}

	@Override
	@SuppressWarnings("unchecked")
	public synchronized void replaceAll(
			final BiFunction<? super String, ? super String, ? extends String> function) {
		this.properties.replaceAll((BiFunction<? super Object, ? super Object, ? extends Object>) function);
	}

	@Override
	public synchronized int size() {
		return this.properties.size();
	}

	@Override
	public synchronized String toString() {
		return this.properties.toString();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<String> values() {
		return (Collection<String>)(Object) this.properties.values();
	}

}
