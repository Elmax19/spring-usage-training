package by.elmax19.app.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomOnPropertyCondition extends SpringBootCondition {
    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        List<ConditionMessage> noMatch = new ArrayList<>();
        List<ConditionMessage> match = new ArrayList<>();
        MultiValueMap<String, Object> map = metadata.getAllAnnotationAttributes(CustomConditionalOnProperty.class.getName());
        assert map != null;
        Property property = new Property(String.valueOf(map.get("name").get(0)), String.valueOf(map.get("value").get(0)));
        PropertyResolver resolver = context.getEnvironment();
        if ((map.containsKey("name"))) {
            if (!Objects.requireNonNull(resolver.getProperty(property.getName())).equalsIgnoreCase(property.getValue())) {
                noMatch.add(ConditionMessage.forCondition(CustomConditionalOnProperty.class, property)
                        .found("different value in property", "different value in properties")
                        .items(ConditionMessage.Style.QUOTE, property.name));
            } else {
                match.add(ConditionMessage.forCondition(CustomConditionalOnProperty.class, property).because("matched"));
            }
        } else {
            noMatch.add(ConditionMessage.forCondition(CustomConditionalOnProperty.class, property)
                    .didNotFind("property", "properties").items(ConditionMessage.Style.QUOTE, property.name));
        }
        if (!noMatch.isEmpty()) {
            return ConditionOutcome.noMatch(ConditionMessage.of(noMatch));
        }
        return ConditionOutcome.match(ConditionMessage.of(match));
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

        @Override
        public String toString() {
            return "Property{" +
                    "name='" + name + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}
