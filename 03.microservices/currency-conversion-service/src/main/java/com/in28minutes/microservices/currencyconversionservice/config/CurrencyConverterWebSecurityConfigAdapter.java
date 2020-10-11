package com.in28minutes.microservices.currencyconversionservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class CurrencyConverterWebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure (HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated().and().httpBasic();
    }
}
