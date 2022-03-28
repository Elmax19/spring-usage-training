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

# Useful links:

- https://tanzu.vmware.com/content/springone-platform-2017/test-driven-development-with-spring-boot-sannidhi-jalukar-madhura-bhave
- https://reflectoring.io/spring-boot-web-controller-test
- https://www.arhohuttunen.com/spring-boot-webmvctest