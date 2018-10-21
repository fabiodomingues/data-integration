package br.com.fd.dataintegration.core.runner;

import java.util.HashMap;
import java.util.Map;

public class FlowContext {

	private Map<String, Object> variables;

	public FlowContext() {
		this.variables = new HashMap<>();
	}

	public Object get(String variable) {
		return variables.get(variable);
	}

	public void put(String variable, Object value) {
		variables.put(variable, value);
	}

}