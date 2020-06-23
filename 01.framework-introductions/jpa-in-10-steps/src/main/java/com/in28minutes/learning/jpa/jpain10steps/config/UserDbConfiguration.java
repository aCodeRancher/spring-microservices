package com.in28minutes.learning.jpa.jpain10steps.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = {"com.in28minutes.learning.jpa.jpain10steps.db.users.dao"},
        entityManagerFactoryRef="userEntityManagerFactory")
public class UserDbConfiguration {
    @Autowired
    private Environment env;

    @Bean

    public LocalContainerEntityManagerFactoryBean userEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setJtaDataSource(userDataSource());
        em.setPackagesToScan("com.in28minutes.learning.jpa.jpain10steps.db.users.ds");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.ddl.auto", env.getProperty("hibernate.ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public DataSource userDataSource (){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("app.datasource.adb.driverClassName"));
        dataSource.setUrl(env.getProperty("app.datasource.adb.url"));
        dataSource.setUsername(env.getProperty("app.datasource.adb.username"));
        dataSource.setPassword(env.getProperty("app.datasource.adb.password"));
        return dataSource;
    }
}
