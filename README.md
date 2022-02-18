# Context containing Bean

The _**ApplicationContext**_ is the central interface within a Spring application for providing configuration information to the application. The ApplicationContext interface provides the _**getBean()**_ method to retrieve bean from the spring container.

Several implementations of the ApplicationContext interface are supplied with Spring. In stand-alone applications, it is
common to create an instance of _**ClassPathXmlApplicationContext**_ or _**FileSystemXmlApplicationContext**_.

Against a _**ClassPathXmlApplicationContext**_, that code returns a _**ClassPathResource**_. If the same method were run
against a _**FileSystemXmlApplicationContext**_ instance, it would return a _**FileSystemResource**_. As a result, you
can load resources in a fashion appropriate to the particular application context.

## Useful links:

- https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#resources-resourceloader
- https://www.javaguides.net/2019/10/how-to-get-application-context-in-spring-boot.html