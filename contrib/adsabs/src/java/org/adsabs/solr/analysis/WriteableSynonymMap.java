package org.adsabs.solr.analysis;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WriteableSynonymMap {
	
    public static final Logger log = LoggerFactory.getLogger(WriteableSynonymMap.class);
	
    private HashMap<String, List<String>> map;
	private int numUpdates = 0;
	private String outFile = null;
	
	public WriteableSynonymMap(String outFile) {
		this.map = new HashMap<String, List<String>>();
		this.outFile = outFile;
	}
	
	public List<String> put(String k, List<String> v) {
		log.trace("setting " + k + " to " + v);
		numUpdates++;
		return this.map.put(k, v);
	}
	
	public List<String> get(String k) {
		return this.map.get(k);
	}
	
	public List<String> get(Pattern p) {
		for (String k : this.map.keySet()) {
			Matcher m = p.matcher(k);
			if (m.matches()) {
				return this.map.get(k);
			}
		}
		return null;
	}
	
	public boolean isDirty() {
		if (numUpdates > 0) 
			return true;
		return false;
	}
	
	public void persist() throws IOException {
		Writer writer = getWriter();
		if (writer == null) {
			log.error("Cannot write synonyms, writer object is null.");
			return;
		}
		
		writeSynonyms(map, writer);
		numUpdates = 0;
		writer.close();
	}
	
	public Writer getWriter() {
		if (outFile == null)
			return null;
		
		log.info("Creating new Writer for " + outFile);
		Writer w;
		
		Charset UTF_8 = Charset.forName("UTF-8");
		try {
			w = new OutputStreamWriter(new FileOutputStream(this.outFile, true), UTF_8);
			//w = new BufferedWriter(w);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} 
		
		return w;
	}
	
	
	public void writeSynonyms(Map<String, List<String>> map, Writer writer) {
		StringBuffer out = new StringBuffer();
		int max = 1000;
		int i = 0;
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			for (String s : entry.getValue()) { // escape the entry
				out.append(s.replace(",", "\\,").replace(" ", "\\ "));
				out.append(",");
			}
			out.append(entry.getKey().replace(",", "\\,").replace(" ", "\\ "));
			out.append("\n");
			i++;
			if (i > max) {
				i = 0;
				write(writer, out.toString());
				out = new StringBuffer();
			}
		}
		write(writer, out.toString());
	}
	
	private void write(Writer writer, String out) {
		try {
			synchronized(writer) {
				writer.write(out);
				writer.flush();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void finalize() {
		if (isDirty())
			try {
				this.persist();
			} catch (IOException e) {
				log.error(e.getLocalizedMessage());
			}
		
	}
}