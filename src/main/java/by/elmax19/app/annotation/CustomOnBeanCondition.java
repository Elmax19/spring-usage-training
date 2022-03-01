package by.elmax19.app.annotation;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

import java.util.List;

public class CustomOnBeanCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        MultiValueMap<String, Object> map = metadata.getAllAnnotationAttributes(CustomConditionalOnBean.class.getName());
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        List<String> attributeNames;
        List<String> attributeTypes;
        List<Class<?>> attributeValue;
        attributeNames = getAttributeValue(map, "name");
        attributeTypes = getAttributeValue(map, "type");
        attributeValue = getAttributeValue(map, "value");
        if (attributeNames.isEmpty() && attributeTypes.isEmpty() && attributeValue.isEmpty()) {
            return false;
        }
        return (attributeNames.isEmpty() || matchAbsenceBeanNames(attributeNames, beanFactory)) &&
                (attributeTypes.isEmpty() || matchAbsenceBeanTypes(attributeTypes, beanFactory)) &&
                (attributeValue.isEmpty() || matchAbsenceBeanValues(attributeValue, beanFactory));
    }

    private boolean matchAbsenceBeanNames(List<String> names, ConfigurableListableBeanFactory beanFactory) {
        for (String name : names) {
            if (!beanFactory.containsBean(name)) {
                return false;
            }
        }
        return true;
    }

    private boolean matchAbsenceBeanTypes(List<String> types, ConfigurableListableBeanFactory beanFactory) {
        try {
            for (String type : types) {
                if (checkNoneFromBeansExists(beanFactory, Class.forName(type))) {
                    return true;
                }
            }
        } catch (ClassNotFoundException e) {
            return true;
        }
        return false;
    }

    private boolean matchAbsenceBeanValues(List<Class<?>> values, ConfigurableListableBeanFactory beanFactory) {
        for (Class<?> value : values) {
            if (checkNoneFromBeansExists(beanFactory, value)) {
                return false;
            }
        }
        return true;
    }

    private <T> List<T> getAttributeValue(MultiValueMap<String, Object> map, String attributeName) {
        Object attributes = map.get(attributeName).get(0);
        return List.of((T[]) attributes);
    }

    private boolean checkNoneFromBeansExists(ConfigurableListableBeanFactory beanFactory, Class<?> beanClass) {
        String[] beanNames = beanFactory.getBeanNamesForType(beanClass);
        for (String name : beanNames) {
            if (beanFactory.containsBean(name)) {
                return false;
            }
        }
        return true;
    }
}
