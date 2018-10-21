package br.com.fd.dataintegration.core.step;

import br.com.fd.dataintegration.core.step.json.input.JsonInput;
import br.com.fd.dataintegration.core.step.json.output.JsonOutput;
import br.com.fd.dataintegration.core.step.mongodb.MongoDBOutput;
import br.com.fd.dataintegration.core.step.rest.RestClient;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.EXISTING_PROPERTY,
		property = "type")
@JsonSubTypes({
		@Type(value = JsonInput.class, name = "JsonInput"),
		@Type(value = JsonOutput.class, name = "JsonOutput"),
		@Type(value = RestClient.class, name = "RestClient"),
		@Type(value = MongoDBOutput.class, name = "MongoDBOutput")
})
public abstract class Step {

	private String type;

	public Step(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}