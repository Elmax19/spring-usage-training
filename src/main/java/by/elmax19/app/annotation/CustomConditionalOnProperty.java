package by.elmax19.app.annotation;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Conditional(CustomOnPropertyCondition.class)
public @interface CustomConditionalOnProperty {
    String name() default "";
    String value() default "";
}
