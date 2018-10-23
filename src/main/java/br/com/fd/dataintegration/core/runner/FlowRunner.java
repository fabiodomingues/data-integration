package br.com.fd.dataintegration.core.runner;

import br.com.fd.dataintegration.core.flow.Flow;
import br.com.fd.dataintegration.core.step.join.JoinTable;
import br.com.fd.dataintegration.core.step.join.JoinTableRunner;
import br.com.fd.dataintegration.core.step.json.input.JsonInput;
import br.com.fd.dataintegration.core.step.json.input.JsonInputRunner;
import br.com.fd.dataintegration.core.step.json.output.JsonOutput;
import br.com.fd.dataintegration.core.step.mongodb.input.MongoDBInput;
import br.com.fd.dataintegration.core.step.mongodb.input.MongoDBInputRunner;
import br.com.fd.dataintegration.core.step.mongodb.output.MongoDBOutput;
import br.com.fd.dataintegration.core.step.mongodb.output.MongoDBOutputRunner;
import br.com.fd.dataintegration.core.step.rest.RestClient;
import br.com.fd.dataintegration.core.step.Step;
import br.com.fd.dataintegration.core.step.json.output.JsonOutputRunner;
import br.com.fd.dataintegration.core.step.rest.RestClientRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FlowRunner {

	private static final Logger logger = LoggerFactory.getLogger(FlowRunner.class);

	public void run(Flow flow) {
		FlowContext context = new FlowContext();

		for (Step step : flow.getSteps()) {
			long startTimestamp = System.currentTimeMillis();

			Runner runner = getRunnerFor(step);
			runner.run(step, context);

			long endTimestamp = System.currentTimeMillis();
			logAnalysisDuration(endTimestamp - startTimestamp);
		}
	}

	public Runner getRunnerFor(Step step) {
		switch (step.getType()) {
			case JsonInput.TYPE: return new JsonInputRunner();
			case JsonOutput.TYPE: return new JsonOutputRunner();
			case RestClient.TYPE: return new RestClientRunner();
			case MongoDBInput.TYPE: return new MongoDBInputRunner();
			case MongoDBOutput.TYPE: return new MongoDBOutputRunner();
			case JoinTable.TYPE: return new JoinTableRunner();
		}

		throw new UnknownStepType();
	}

	private static void logAnalysisDuration(long duration) {
		logger.info(String.format("This analysis took %,d miliseconds", duration));
	}

}