package com.yang.controller;

import org.springframework.boot.actuate.endpoint.HealthEndpoint;
import org.springframework.boot.actuate.endpoint.mvc.EndpointHandlerMapping;
import org.springframework.boot.actuate.endpoint.mvc.HealthMvcEndpoint;
import org.springframework.boot.actuate.endpoint.mvc.MvcEndpoint;

import java.util.Collection;

//@Configuration
//@Import({ EndpointAutoConfiguration.class,
//		HealthIndicatorAutoConfiguration.class})
public class MyAppSpringConfig {

//	@Bean
	public EndpointHandlerMapping endpointHandlerMapping(
			Collection<? extends MvcEndpoint> endpoints) {
		return new EndpointHandlerMapping(endpoints);
	}

//	@Bean
	public HealthMvcEndpoint healthMvcEndpoint(HealthEndpoint delegate) {
		return new HealthMvcEndpoint(delegate, false);
	}

}