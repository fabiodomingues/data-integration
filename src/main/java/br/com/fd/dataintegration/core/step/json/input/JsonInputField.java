package br.com.fd.dataintegration.core.step.json.input;

import br.com.fd.dataintegration.core.DataType;

public class JsonInputField {

	private String name;
	private String path;
	private DataType type;

	public JsonInputField() { }

	public JsonInputField(String name, String path, DataType type) {
		this.name = name;
		this.path = path;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public DataType getType() {
		return type;
	}

}