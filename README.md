# Custom Starter

The core **_Spring Boot_** developers provide _**starters**_ for most of the popular open source projects, but we are
not limited to these.

We can also write our own custom starters. If we have an internal library for use within our organization, it would be a
good practice to also write a starter for it if it's going to be used in Spring Boot context.

These starters enable developers to avoid lengthy configuration and quickly jumpstart their development. However, with a
lot of things happening in the background, it sometimes becomes difficult to understand how an annotation or just
including a dependency in the pom.xml enables so many features.

When Spring Boot starts up, it looks for a file named spring.factories in the classpath. This file is located in the
META-INF directory.

The majority of the metadata file is generated automatically at compile time by processing all items annotated with
_**ConfigurationProperties**_.

The processor picks up both classes and methods that are annotated with _**ConfigurationProperties**_.

If the class is also annotated with _**ConstructorBinding**_, a single constructor is expected and one property is
created per constructor parameter. Otherwise, properties are discovered through the presence of standard getters and
setters with special handling for collection and map types.

## Useful links:

- https://www.baeldung.com/spring-boot-custom-starter
- https://github.com/proob/gradle-multi-module-project
- https://docs.spring.io/spring-boot/docs/2.6.4/reference/html/configuration-metadata.html#appendix.configuration-metadata.annotation-processor