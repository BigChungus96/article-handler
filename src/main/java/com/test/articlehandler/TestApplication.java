package com.test.articlehandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


@SpringBootApplication
public class TestApplication  {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }



}
