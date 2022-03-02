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
        return attributeNames != null && !attributeNames.isEmpty() && areAllClassesExist(attributeNames, classLoader);
    }

    private List<String> getAttributeValue(MultiValueMap<String, Object> map, String attributeName) {
        List attributes = map.get(attributeName);
        return attributes == null ? null : List.of((String[]) attributes.get(0));
    }

    private boolean areAllClassesExist(List<String> classesNames, ClassLoader classLoader) {
        return classesNames.stream().allMatch(name -> isClassPresent(classLoader, name));
    }

    private boolean isClassPresent(ClassLoader classLoader, String className) {
        return ClassUtils.isPresent(className, classLoader);
    }
}
