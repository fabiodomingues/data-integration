package br.com.fd.dataintegration.core.flow;

import br.com.fd.dataintegration.core.step.Step;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Flow {

	@Id
	private String id;
	private String description;
	private List<Step> steps;

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void addStep(Step step) {
		steps.add(step);
	}

}