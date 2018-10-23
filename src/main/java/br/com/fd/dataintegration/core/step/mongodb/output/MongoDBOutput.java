package br.com.fd.dataintegration.core.step.mongodb.output;

import br.com.fd.dataintegration.core.step.mongodb.MongoDBBase;

import java.util.List;

public class MongoDBOutput extends MongoDBBase {

	public static final String TYPE = "MongoDBOutput";

	private String inputVariable;
	private List<MongoDBOutputField> fields;

	public MongoDBOutput() {
		super(MongoDBOutput.TYPE);
	}

	public String getInputVariable() {
		return inputVariable;
	}

	public List<MongoDBOutputField> getFields() {
		return fields;
	}
}