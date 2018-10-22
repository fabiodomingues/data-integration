package br.com.fd.dataintegration.core.step;

import br.com.fd.dataintegration.core.step.join.JoinTable;
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
		@Type(value = JsonInput.class, name = JsonInput.TYPE),
		@Type(value = JsonOutput.class, name = JsonOutput.TYPE),
		@Type(value = RestClient.class, name = RestClient.TYPE),
		@Type(value = MongoDBOutput.class, name = MongoDBOutput.TYPE),
		@Type(value = JoinTable.class, name = JoinTable.TYPE)
})
public abstract class Step {

	private String type;
	private String name;

	public Step(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

}