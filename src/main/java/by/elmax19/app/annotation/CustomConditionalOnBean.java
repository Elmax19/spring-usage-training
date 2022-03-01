package by.elmax19.app.annotation;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Conditional(CustomOnBeanCondition.class)
public @interface CustomConditionalOnBean {
    String[] name() default {};
    String[] type() default {};
    Class<?>[] value() default {};
}
