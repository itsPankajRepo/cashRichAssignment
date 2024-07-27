package com.assignment.cashRich;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
@EnableJpaRepositories
public class CashRichApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashRichApplication.class, args);
	}


	@Bean(name = "restTemplate")
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = null;

		var requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(420000);
		restTemplate = new RestTemplate(requestFactory);
		restTemplate.setErrorHandler(new com.assignment.cashRich.advice.RestTemplateResponseErrorHandler());

		return restTemplate;

	}

}
