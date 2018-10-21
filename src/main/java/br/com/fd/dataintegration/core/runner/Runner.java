package br.com.fd.dataintegration.core.runner;

public interface Runner<T> {
	void run(T t, FlowContext context);
}