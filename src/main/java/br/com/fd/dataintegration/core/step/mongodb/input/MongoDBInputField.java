package br.com.fd.dataintegration.core.step.mongodb.input;

import br.com.fd.dataintegration.core.DataType;

public class MongoDBInputField {

	private String name;
	private String path;
	private DataType type;

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
