package br.com.fd.dataintegration.core.runner;

import br.com.fd.dataintegration.core.flow.Flow;
import br.com.fd.dataintegration.core.step.JsonInput;
import br.com.fd.dataintegration.core.step.JsonOutput;
import br.com.fd.dataintegration.core.step.RestClient;
import br.com.fd.dataintegration.core.step.Step;
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
		}

		throw new UnknownStepType();
	}

	private static void logAnalysisDuration(long duration) {
		logger.info(String.format("This analysis took %,d miliseconds", duration));
	}

}