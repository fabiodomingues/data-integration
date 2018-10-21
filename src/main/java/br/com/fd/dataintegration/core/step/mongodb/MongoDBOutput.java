package br.com.fd.dataintegration.core.step.mongodb;

import br.com.fd.dataintegration.core.step.Step;
import br.com.fd.dataintegration.core.step.json.input.JsonInput;

import java.util.List;

public class MongoDBOutput extends Step {

	public static final String TYPE = "MongoDBOutput";

	private String host;
	private Integer port;
	private String username;
	private String password;
	private String database;
	private String collection;
	private String inputVariable;
	private List<MongoDBFields> fields;

	public MongoDBOutput() {
		super(MongoDBOutput.TYPE);
	}

	public String getHost() {
		return host;
	}

	public Integer getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getDatabase() {
		return database;
	}

	public String getCollection() {
		return collection;
	}

	public String getInputVariable() {
		return inputVariable;
	}

	public List<MongoDBFields> getFields() {
		return fields;
	}
}