package com.in28minutes.microservices.currencyexchangeservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
public class CurrencyExchangeWebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure (HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated().and().httpBasic();
    }
}
