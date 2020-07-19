package com.socgen.employeeportal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.socgen.employeeportal.repository.database")
public class DatabaseConfiguration {
}
