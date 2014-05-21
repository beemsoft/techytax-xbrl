package org.techytax.dao;

import java.util.HashMap;
import java.util.Map;

public class QueryParameter {
	
	private Map<String, Object> parameters = null;
	
	public QueryParameter(String name, Object value) {
		this.parameters = new HashMap<>();
		this.parameters.put(name, value);
	}

	public static QueryParameter with(String name, Object value) {
		return new QueryParameter(name, value);
	}
	
	public QueryParameter and(String name, Object value) {
		this.parameters.put(name, value);
		return this;
	}
	
	public Map<String, Object> parameters() {
		return this.parameters;
	}
}
