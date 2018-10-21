package br.com.fd.dataintegration.core.step.rest;

import br.com.fd.dataintegration.core.runner.FlowContext;
import br.com.fd.dataintegration.core.runner.Runner;
import br.com.fd.dataintegration.core.step.rest.ApplicationType;
import br.com.fd.dataintegration.core.step.rest.RestClient;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

import java.util.List;

import static com.mashape.unirest.http.Unirest.*;

public class RestClientRunner implements Runner<RestClient> {

	@Override
	public void run(RestClient restClient, FlowContext context) {
		System.out.println("RestClientRunner.run(...)");

		switch (restClient.getHttpMethod()) {
			case GET:
				doGet(restClient, context);
				break;
			case POST:
				doPost(restClient, context);
				break;
			case PUT:
				doPut(restClient, context);
				break;
			case DELETE:
				doDelete(restClient, context);
				break;
		}
	}

	private void doGet(RestClient restClient, FlowContext context) {
		try {
			GetRequest request = get(restClient.getUrl());

			includeHeaders(restClient, request);
			includePathVariables(restClient, request);
			includeQueryVariables(restClient, request);

			HttpResponse<String> response = request.asString();

			if (restClient.getResponseVariable() != null && !restClient.getResponseVariable().isEmpty()) {
				context.put(restClient.getResponseVariable(), response.getBody());
			}
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}

	private void doPost(RestClient restClient, FlowContext context) {
		if (context.get(restClient.getBodyVariable()) instanceof List) {
			for (String body : (List<String>) context.get(restClient.getBodyVariable())) {
				doPost(restClient, context, body);
			}
		} else {
			doPost(restClient, context, (String) context.get(restClient.getBodyVariable()));
		}
	}

	private void doPost(RestClient restClient, FlowContext context, String body) {
		try {

			HttpRequestWithBody request = post(restClient.getUrl());

			includeHeaders(restClient, request);
			includePathVariables(restClient, request);
			includeQueryVariables(restClient, request);

			request.body(body);

			HttpResponse<String> response = request.asString();

			if (restClient.getResponseVariable() != null && !restClient.getResponseVariable().isEmpty()) {
				context.put(restClient.getResponseVariable(), response.getBody());
			}
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}

	private void doPut(RestClient restClient, FlowContext context) {
	}

	private void doDelete(RestClient restClient, FlowContext context) {
	}

	private void includeHeaders(RestClient restClient, HttpRequest request) {
		if (restClient.getHeaders() != null && restClient.getHeaders().size() > 0) {
			request.headers(restClient.getHeaders());
		}

		if (ApplicationType.JSON.equals(restClient.getApplicationType())) {
			request.header("accept", "application/json");
			request.header("Content-Type", "application/json");
		}
	}

	private void includePathVariables(RestClient restClient, HttpRequest request) {
		if (restClient.getPathVariables() != null && restClient.getPathVariables().size() > 0) {
			for (String key : restClient.getPathVariables().keySet()) {
				request.routeParam(key, restClient.getPathVariables().get(key));
			}
		}
	}

	private void includeQueryVariables(RestClient restClient, HttpRequest request) {
		if (restClient.getQueryVariables() != null && restClient.getQueryVariables().size() > 0) {
			for (String key : restClient.getQueryVariables().keySet()) {
				request.queryString(key, restClient.getQueryVariables().get(key));
			}
		}
	}

}