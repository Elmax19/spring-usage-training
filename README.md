# Bean Resolving

Because autowiring by type may lead to multiple candidates, it is often necessary to have more control over the
selection process. One way to accomplish this is with Spring’s _**Primary**_ annotation. _**Primary**_ indicates that a
particular bean should be given preference when multiple beans are candidates to be autowired to a single-valued
dependency. If exactly one primary bean exists among the candidates, it becomes the autowired value.

_**Primary**_ is an effective way to use autowiring by type with several instances when one primary candidate can be
determined. When you need more control over the selection process, you can use Spring’s _**Qualifier**_ annotation. You can
associate qualifier values with specific arguments, narrowing the set of type matches so that a specific bean is chosen
for each argument.

You can also specify the _**Qualifier**_ annotation on individual constructor arguments or method parameters

## Useful links:

- https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-definition