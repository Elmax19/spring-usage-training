package by.elmax19.app.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.MultiValueMap;

import java.util.List;

public class CustomOnClassCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        MultiValueMap<String, Object> map = metadata.getAllAnnotationAttributes(CustomConditionalOnClass.class.getName());
        ClassLoader classLoader = context.getClassLoader();
        List<String> attributeNames = getAttributeValue(map, "name");
        List<Class<?>> attributeValue = getAttributeValue(map, "value");
        if (attributeNames.isEmpty() && attributeValue.isEmpty()) {
            return false;
        }
        return (attributeNames.isEmpty() || matchClassNames(attributeNames, classLoader))
                && (attributeValue.isEmpty() || matchClassValues(attributeValue, classLoader));
    }

    private <T> List<T> getAttributeValue(MultiValueMap<String, Object> map, String attributeName) {
        Object attributes = map.get(attributeName).get(0);
        return List.of((T[]) attributes);
    }

    private boolean matchClassNames(List<String> names, ClassLoader classLoader) {
        return names.stream().allMatch(name -> checkClassExists(classLoader, name));
    }

    private boolean matchClassValues(List<Class<?>> values, ClassLoader classLoader) {
        return values.stream().allMatch(value -> checkClassExists(classLoader, value.getName()));
    }

    private boolean checkClassExists(ClassLoader classLoader, String className) {
        return ClassUtils.isPresent(className, classLoader);
    }
}
