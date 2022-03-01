package by.elmax19.app.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

public class CustomOnPropertyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        MultiValueMap<String, Object> map = metadata.getAllAnnotationAttributes(CustomConditionalOnProperty.class.getName());
        String attributeName = getAttributeValue(map, "name");
        String attributeValue = getAttributeValue(map, "value");
        if (attributeName == null || attributeValue == null) {
            return false;
        }
        PropertyResolver resolver = context.getEnvironment();
        String envVar = resolver.getProperty(attributeName);
        return attributeValue.equals(envVar);
    }


    private String getAttributeValue(MultiValueMap<String, Object> map, String attributeName) {
        var value = map.get(attributeName);
        return value == null ? null : String.valueOf(value.get(0));
    }
}
