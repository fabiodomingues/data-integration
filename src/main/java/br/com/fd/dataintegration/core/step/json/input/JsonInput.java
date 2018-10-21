package br.com.fd.dataintegration.core.step.json.input;

import br.com.fd.dataintegration.core.step.Step;

import java.util.List;

public class JsonInput extends Step {

	public static final String TYPE = "JsonInput";

	private String inputVariable;
	private String outputVariable;

	private List<JsonInputField> fields;

	public JsonInput() {
		super(JsonInput.TYPE);
	}

	public String getInputVariable() {
		return inputVariable;
	}

	public String getOutputVariable() {
		return outputVariable;
	}

	public List<JsonInputField> getFields() {
		return fields;
	}

	public void addField(JsonInputField field) {
		fields.add(field);
	}
}
