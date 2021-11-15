# spring-core-devops
This repository contains code related to my Spring Core DevOps course.

You can learn more about the course [here](http://courses.springframework.guru/courses/spring-core-dev-ops) on my site.

### Upgrade to Java 11 & Spring Boot 2.5.6

I was getting this error after changing the Java version from 1.8 to 11 when running `mvn compile`.

**Error 1** Changing to Java 11
```text
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.582 s
[INFO] Finished at: 2021-11-13T19:11:08-06:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:compile (default-compile) on project spring-core-devops: Fatal error compiling: invalid target release: 11 -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoExecutionException
```

It took awhile to remember that my default Java version 1.8. I used SDKMAN to change the version to Amazon Corretto 11.0.9
by running `sdk use java 11.0.9-amzn`. Why that JDK? It's the flavor of the month, that's why.

**Error 2** Changing to Spring Boot 2.5.6

`mvn compile` fails with error messages about

```text
package org.hibernate.validator.constraints does not exist
package javax.validation.constraints does not exist
package org.springframework.web.servlet does not exist
package org.springframework.web.servlet.config.annotation does not exist
package org.springframework.web.servlet.i18n does not exist
package javax.validation does not exist
 /udemy/john_thompson/devops/spring-core-devops/src/main/java/guru/springframework/config/SpringMvcConfiguration.java:[numbers] cannot find symbol
  symbol: class WebMvcConfigurerAdapter
 /udemy/john_thompson/devops/spring-core-devops/src/main/java/guru/springframework/config/SpringMvcConfiguration.java:[numbers] cannot find symbol
  symbol:   class LocaleResolver
  location: class guru.springframework.config.SpringMvcConfiguration
 /udemy/john_thompson/devops/spring-core-devops/src/main/java/guru/springframework/config/SpringMvcConfiguration.java:[numbers] cannot find symbol
  symbol:   class LocaleChangeInterceptor
  location: class guru.springframework.config.SpringMvcConfiguration
 /udemy/john_thompson/devops/spring-core-devops/src/main/java/guru/springframework/config/SpringMvcConfiguration.java:[numbers] cannot find symbol
  symbol:   class SessionLocaleResolver
  location: class guru.springframework.config.SpringMvcConfiguration
 /udemy/john_thompson/devops/spring-core-devops/src/main/java/guru/springframework/config/SpringMvcConfiguration.java:[numbers] cannot find symbol
  symbol:   class InterceptorRegistry
  location: class guru.springframework.config.SpringMvcConfiguration
 /udemy/john_thompson/devops/spring-core-devops/src/main/java/guru/springframework/comands/LoginCommand.java:[numbers] cannot find symbol
  symbol:   class NotEmpty
  location: class guru.springframework.comands.LoginCommand
 /udemy/john_thompson/devops/spring-core-devops/src/main/java/guru/springframework/comands/CheckoutCommand.java:[numbers] cannot find symbol
  symbol:   class Size
  location: class guru.springframework.comands.CheckoutCommand
 /udemy/john_thompson/devops/spring-core-devops/src/main/java/guru/springframework/controllers/CheckoutController.java:[numbers] cannot find symbol
  symbol:   class Valid
  location: class guru.springframework.controllers.CheckoutController
 /udemy/john_thompson/devops/spring-core-devops/src/main/java/guru/springframework/config/SpringMvcConfiguration.java:[numbers] method does not override or implement a method from a supertype
 /udemy/john_thompson/devops/spring-core-devops/src/main/java/guru/springframework/services/ProductServiceImpl.java:[numbers] cannot find symbol
  symbol:   method findOne(java.lang.Integer)
  location: variable productRepository of type guru.springframework.repositories.ProductRepository
 /udemy/john_thompson/devops/spring-core-devops/src/main/java/guru/springframework/config/SpringSecConfig.java:[numbers] cannot access javax.servlet.Filter
   class file for javax.servlet.Filter not found
```

`mvn test-compile` fails with error messages about

```text
/udemy/john_thompson/devops/spring-core-devops/src/test/java/guru/springframework/test/external/props/PropertySourceTest.java:[3,24] package org.junit does not exist
/udemy/john_thompson/devops/spring-core-devops/src/test/java/guru/springframework/test/external/props/PropertySourceTest.java:[3,1] static import only from classes and interfaces
/udemy/john_thompson/devops/spring-core-devops/src/test/java/guru/springframework/test/external/props/PropertySourceTest.java:[6,24] package org.junit.runner does not exist
/udemy/john_thompson/devops/spring-core-devops/src/test/java/guru/springframework/test/external/props/PropertySourceTest.java:[19,2] cannot find symbol
  symbol: class RunWith
/udemy/john_thompson/devops/spring-core-devops/src/test/java/guru/springframework/SpringCoreDevOpsApplicationTests.java:[5,37] package org.springframework.boot.test does not exist
/udemy/john_thompson/devops/spring-core-devops/src/test/java/guru/springframework/SpringCoreDevOpsApplicationTests.java:[10,2] cannot find symbol
  symbol: class SpringApplicationConfiguration
/udemy/john_thompson/devops/spring-core-devops/src/test/java/guru/springframework/test/external/props/PropertySourceTest.java:[26,6] cannot find symbol
  symbol:   class Test
  location: class guru.springframework.test.external.props.PropertySourceTest
/udemy/john_thompson/devops/spring-core-devops/src/test/java/guru/springframework/test/external/props/PropertySourceTest.java:[28,9] cannot find symbol
  symbol:   method assertEquals(java.lang.String,java.lang.String)
```


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