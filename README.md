# Bean using in Spring (working with Life Cycle)

The _**org.springframework.context.ApplicationContext**_ interface represents the Spring IoC container and is
responsible for instantiating, configuring, and assembling the beans.

XML-based configuration metadata configures these beans as _**\<bean/>**_ elements inside a top-level _**\<beans/>**_
element. Java configuration typically uses _**@Bean**_-annotated methods within a _**@Configuration**_ class.

The _**ApplicationContext**_ is the interface for an advanced factory capable of maintaining a registry of different
beans and their dependencies. By using the method _**T getBean(String name, Class<T> requiredType)**_, you can retrieve
instances of your beans.

In XML-based configuration metadata, you use the _**id**_ attribute, the _**name**_ attribute, or both to specify the
bean identifiers. The _**id**_ attribute lets you specify exactly one id. If you use XML-based configuration metadata,
you specify the type (or class) of object that is to be instantiated in the _**class**_ attribute of the _**<bean/>**_
element. This _**class**_ attribute is usually mandatory.

Bean life cycle is managed by the spring container. If we want to execute some code on the bean instantiation and just
after closing the spring container, then we can write that code inside the custom _**init()**_ method and the
_**destroy()**_ method.

To define setup and teardown for a bean, we simply declare the <bean> with _**init-method**_ and/or _**destroy-method**_
parameters. The _**init-method**_ attribute specifies a method that is to be called on the bean immediately upon
instantiation. Similarly, _**destroy-method**_ specifies a method that is called just before a bean is removed from the
container.

In XML, this behavior is controlled by the _**lazy-init**_ attribute on the _**<bean/>**_ element

Creating Spring beans generally used with _**@Configuration**_. We use _**@Bean**_ at method level.

## Useful links:

- https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-introduction
- https://www.geeksforgeeks.org/bean-life-cycle-in-java-spring
- https://www.tutorialspoint.com/spring/spring_bean_life_cycle.htm
- https://javatechonline.com/spring-boot-bean-annotations-with-examples
- https://docs.spring.io/spring-javaconfig/docs/1.0.0.M4/reference/html/ch02s02.html