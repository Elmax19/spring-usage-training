package by.elmax19.app.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

import java.util.List;
import java.util.Map;

public class CustomOnClassCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        var map = metadata.getAnnotationAttributes(CustomConditionalOnClass.class.getName());
        ClassLoader classLoader = context.getClassLoader();
        List<String> attributeNames = getNameAttributeValue(map);
        return attributeNames != null && !attributeNames.isEmpty() && areAllClassesExist(attributeNames, classLoader);
    }

    private List<String> getNameAttributeValue(Map<String, Object> map) {
        String[] attributes = (String[]) map.get("name");
        return attributes == null ? null : List.of(attributes);
    }

    private boolean areAllClassesExist(List<String> classesNames, ClassLoader classLoader) {
        return classesNames.stream().allMatch(name -> isClassPresent(classLoader, name));
    }

    private boolean isClassPresent(ClassLoader classLoader, String className) {
        return ClassUtils.isPresent(className, classLoader);
    }
}
