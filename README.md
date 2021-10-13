# spring-core-devops
This repository contains code related to my Spring Core DevOps course.

You can learn more about the course [here](http://courses.springframework.guru/courses/spring-core-dev-ops) on my site.

## Section 1. Introduction
`guru.springframework.bootstrap.DevOpsBootstrap` initializes the in memory database.
An embedded Active MQ container is fired up for the JMS messages.

## Section 2. Externalizing Properties

### Using Property Source

Use `@PropertySource` to read a properties file from the classpath during configuration.

Configuring a `org.springframework.context.support.PropertySourcesPlaceholderConfigurer`. This has been around since
Spring 3.1. It parses the Spring EL expressions in the `@Value` annotations to get you the value for the property.
(Spring Boot probably does this for you.)
