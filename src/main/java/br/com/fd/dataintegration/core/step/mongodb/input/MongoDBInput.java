package br.com.fd.dataintegration.core.step.mongodb.input;

import br.com.fd.dataintegration.core.step.Step;
import br.com.fd.dataintegration.core.step.mongodb.MongoDBBase;
import br.com.fd.dataintegration.core.step.mongodb.output.MongoDBOutputField;

import java.util.List;

public class MongoDBInput extends MongoDBBase {

	public static final String TYPE = "MongoDBInput";

	private String outputVariable;
	private String query;
	private List<MongoDBInputField> fields;

	public MongoDBInput() {
		super(MongoDBInput.TYPE);
	}

	public String getOutputVariable() {
		return outputVariable;
	}

	public String getQuery() {
		return query;
	}

	public List<MongoDBInputField> getFields() {
		return fields;
	}
}