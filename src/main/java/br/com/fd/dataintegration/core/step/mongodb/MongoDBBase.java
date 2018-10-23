package br.com.fd.dataintegration.core.step.mongodb;

import br.com.fd.dataintegration.core.step.Step;

public class MongoDBBase extends Step {

	private String host;
	private Integer port;
	private String username;
	private String password;
	private String database;
	private String collection;

	public MongoDBBase(String type) {
		super(type);
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
}
