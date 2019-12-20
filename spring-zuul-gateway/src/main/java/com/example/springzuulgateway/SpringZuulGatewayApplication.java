package com.example.springzuulgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import com.example.springbootzuulgatway.filters.ErrorFilter;
import com.example.springbootzuulgatway.filters.PostFilter;
import com.example.springbootzuulgatway.filters.PreFilter;
import com.example.springbootzuulgatway.filters.RouteFilter;

@SpringBootApplication
@EnableZuulProxy
public class SpringZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringZuulGatewayApplication.class, args);
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}


}
