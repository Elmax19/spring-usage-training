# Conditions of Bean creating

It is often useful to conditionally enable or disable a complete _**Configuration**_ class or even individual _**Bean**_
methods, based on some arbitrary system state. The _**Conditional**_ annotation indicates specific
_**org.springframework.context.annotation.Condition**_ implementations that should be consulted before a _**Bean**_ is
registered.

Typically, when developing Spring-based applications, we may need to create some beans conditionally based on the
presence and the value of a configuration property. Fortunately, achieving that isn't as hard as it might look upon
first glance. The Spring framework provides the _**ConditionalOnProperty**_ annotation precisely for this purpose.

_**ConditionalOnResource**_ is annotation using that we can create a bean if specified resource found otherwise it will
not create a bean. We can specify resources location of classpath as well as system locations like:  
_**ConditionalOnResource(resources = "file:\\C:\\data\\example.json")**_.

The _**ConditionalOnExpression**_ annotation lets configuration be included based on the result of a SpEL (Spring
Expression Language) expression. For this example the _**Module**_ class is only loaded if a particular SpEL is enabled.
This way, you might create similar modules that are only loaded if their respective SpEL has been found or enabled.

SpEL or Spring Expression Language which can be used to query property value from properties file using _**$**_, or
manipulate Java object and its attributes at runtime using _**#**_. Both modifiers _**$**_ and _**#**_ can be used in
spring XML configuration file directly, or can be used in Java source code with _**Value**_ annotation.

Spring _**ConditionalOnClass**_ and _**ConditionalOnMissingClass**_ annotations let _**Configuration**_ classes be
included based on the presence or absence of specific classes. So _**ConditionalOnClass**_ loads a bean only if a
certain class is on the classpath and _**ConditionalOnMissingClass**_ loads a bean only if a certain class is not on the
classpath.

The _**ConditionalOnMissingBean**_ annotation is a spring conditional annotation for registering beans only when they
are not already in the application context. We use _**ConditionalOnMissingBean**_ if we want to include a bean only if a
specified bean is not present. For ex. To understand more consider this scenario as well.

## Useful links:

- https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-java-conditional
- https://www.baeldung.com/spring-conditional-annotations
- https://roytuts.com/spring-conditionalonexpression-example
- https://javadeveloperzone.com/spring/spring-conditionalonresource-example
- https://roytuts.com/spring-conditionalonmissingbean-example
