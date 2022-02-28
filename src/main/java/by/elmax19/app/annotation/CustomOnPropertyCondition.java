package by.elmax19.app.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomOnPropertyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        MultiValueMap<String, Object> map = metadata.getAllAnnotationAttributes(CustomConditionalOnProperty.class.getName());
        assert map != null;
        Property property = new Property(String.valueOf(map.get("name").get(0)), String.valueOf(map.get("value").get(0)));
        PropertyResolver resolver = context.getEnvironment();
        if ((map.containsKey("name"))) {
            return Objects.requireNonNull(resolver.getProperty(property.getName())).equalsIgnoreCase(property.getValue());
        }
        return false;
    }

    private class Property {
        private String name = "";
        private String value = "";

        public Property(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }
}
