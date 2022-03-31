## Players API

Starter of Spring web uses Spring MVC, REST and Tomcat as a default embedded server. The single spring-boot-starter-web
dependency transitively pulls in all dependencies related to web development. It also reduces the build dependency
count.

To test our Spring MVC controllers, we can use the **_WebMvcTest_** annotation. The annotation scans only beans for
**_Controller_**, **_ControllerAdvice_**, and a few others related to the web layer.

Spring Boot also autoconfigures a **_MockMvc_** bean for us so that we can autowire that. Using **_MockMvc_** fakes HTTP
requests for us, making it possible to run the controller tests without starting an entier HTTP server.

Slicing or program slicing is a technique used in software testing which takes a slice or a group of program statements
in the program for testing particular test conditions or cases that may affect a value at a particular point of
interest.

JSR-303 bean validation is an specification whose objective is to standardize the validation of Java beans through
annotations. The objective of the JSR-303 standard is to use annotations directly in a Java bean class.

JSR 303 specification allows the validation rules to be specified directly into the fields inside any Java class which
they are intended to validate, instead of creating validation rules in separate classes.

# Useful links:

- https://tanzu.vmware.com/content/springone-platform-2017/test-driven-development-with-spring-boot-sannidhi-jalukar-madhura-bhave
- https://reflectoring.io/spring-boot-web-controller-test
- https://www.arhohuttunen.com/spring-boot-webmvctest
- https://www.baeldung.com/javax-validation
- 