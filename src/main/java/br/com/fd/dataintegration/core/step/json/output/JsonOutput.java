package br.com.fd.dataintegration.core.step.json.output;

import br.com.fd.dataintegration.core.step.Step;

import java.util.List;

public class JsonOutput extends Step {

	public static final String TYPE = "JsonOutput";

	private String inputVariable;
	private String outputVariable;
	private boolean array;
	private String arrayElementName;

	private List<JsonOutputField> fields;

	public JsonOutput() {
		super(JsonOutput.TYPE);
	}

	public String getInputVariable() {
		return inputVariable;
	}

	public String getOutputVariable() {
		return outputVariable;
	}

	public boolean isArray() {
		return array;
	}

	public String getArrayElementName() {
		return arrayElementName;
	}

	public List<JsonOutputField> getFields() {
		return fields;
	}
}
