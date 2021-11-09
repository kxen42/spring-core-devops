# spring-core-devops
This repository contains code related to my Spring Core DevOps course.

You can learn more about the course [here](http://courses.springframework.guru/courses/spring-core-dev-ops) on my site.

## Section 1. Introduction
`guru.springframework.bootstrap.DevOpsBootstrap` initializes the in memory database.
An embedded Active MQ container is fired up for the JMS messages.

## Section 2. Externalizing Properties

### Ch 10. Using @PropertySource

Use `@PropertySource` to read a properties file from the classpath during configuration.

Configuring a `org.springframework.context.support.PropertySourcesPlaceholderConfigurer`. This has been around since
Spring 3.1. It parses the Spring EL expressions in the `@Value` annotations to get you the value for the property.
(Spring Boot probably does this for you.)

### Ch 11. Using Environment Variables

Uses `@Autowired Environment env;` pull in the .properties files without the placeholder configurer.
Properties are fetched with `env.getProperty("property-name")` for optional properties.
Required property with simple type conversion from String `env.getRequiredProperty("property-name", Integer.class)`.

Properties can be set from environment variables; can set in IntelliJ Run Configuration.

### Ch 12. Multiple Property Files

I've run into this at work where there are property files for each environment, and some separated by resource.

Simple list still works
```text
@Configuration
@PropertySource({"classpath:testing.properties", "classpath:encrypted-testing.properties"})
```

Spring 4 added `@PropertySources`
```text
@Configuration
@PropertySources({
    @PropertySource("classpath:testing.properties"),
    @PropertySource("classpath:encrypted-testing.properties")
})
```