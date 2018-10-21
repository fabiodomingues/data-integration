package br.com.fd.dataintegration.core.project;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Project {

	@Id
	private String id;

	public String getId() {
		return id;
	}
}