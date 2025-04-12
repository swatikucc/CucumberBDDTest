package com.automation.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = {CucumberSpringConfig.class})
public class CucumberSpringConfig {
}