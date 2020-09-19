package com.in28minutes.microservices.currencyconversionservice.config;

import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;


@Configuration
public class CurrencyConversionWebSecurityConfig  {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(@Value("${currency-exchange-username}") String username ,
                                                                   @Value("${currency-exchange-password}") String password) {

        return new BasicAuthRequestInterceptor(username, password);
    }


}
