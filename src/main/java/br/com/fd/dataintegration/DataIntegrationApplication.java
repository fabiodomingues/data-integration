package br.com.fd.dataintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

@SpringBootApplication
public class DataIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataIntegrationApplication.class, args);
	}

}