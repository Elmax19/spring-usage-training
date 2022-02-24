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

## Useful links:

- https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-java-conditional
- https://www.baeldung.com/spring-conditional-annotations
- https://javadeveloperzone.com/spring/spring-conditionalonresource-example