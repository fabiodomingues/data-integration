package br.com.fd.dataintegration.core.step;

import java.util.HashMap;
import java.util.Map;

public class RestClient extends Step {

	public static final String TYPE = "RestClient";

	private String url;
	private HttpMethod httpMethod;
	private ApplicationType applicationType;
	private Map<String, String> headers;
	private Map<String, String> queryVariables;
	private Map<String, String> pathVariables;
	private String bodyVariable;
	private String responseVariable;

	public RestClient() {
		super(RestClient.TYPE);

		headers = new HashMap<>();
		queryVariables = new HashMap<>();
		pathVariables = new HashMap<>();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	public ApplicationType getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(ApplicationType applicationType) {
		this.applicationType = applicationType;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void addHeader(Map.Entry<String, String> header) {
		this.headers.put(header.getKey(), header.getValue());
	}

	public Map<String, String> getQueryVariables() {
		return queryVariables;
	}

	public void addQueryVariables(Map.Entry<String, String> queryVariable) {
		this.queryVariables.put(queryVariable.getKey(), queryVariable.getValue());
	}

	public Map<String, String> getPathVariables() {
		return pathVariables;
	}

	public void addPathVariable(Map.Entry<String, String> pathVariable) {
		this.pathVariables.put(pathVariable.getKey(), pathVariable.getValue());
	}

	public String getBodyVariable() {
		return bodyVariable;
	}

	public String getResponseVariable() {
		return responseVariable;
	}
}