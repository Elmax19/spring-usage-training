package by.elmax19.app.annotation;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;

public class CustomOnBeanCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        MultiValueMap<String, Object> map = metadata.getAllAnnotationAttributes(CustomConditionalOnBean.class.getName());
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        List<String> attributeNames = getAttributeValue(map, "name");
        List<String> attributeTypes = getAttributeValue(map, "type");
        List<Class<?>> attributeValue = getAttributeValue(map, "value");
        if (attributeNames.isEmpty() && attributeTypes.isEmpty() && attributeValue.isEmpty()) {
            return false;
        }
        return (attributeNames.isEmpty() || matchBeanNames(attributeNames, beanFactory))
                && (attributeTypes.isEmpty() || matchBeanTypes(attributeTypes, beanFactory))
                && (attributeValue.isEmpty() || matchBeanValues(attributeValue, beanFactory));
    }

    private <T> List<T> getAttributeValue(MultiValueMap<String, Object> map, String attributeName) {
        Object attributes = map.get(attributeName).get(0);
        return List.of((T[]) attributes);
    }

    private boolean matchBeanNames(List<String> names, ConfigurableListableBeanFactory beanFactory) {
        return names.stream().allMatch(beanFactory::containsBean);
    }

    private boolean matchBeanTypes(List<String> types, ConfigurableListableBeanFactory beanFactory) {
        return types.stream().allMatch(type -> {
            try {
                return checkAnyFromBeansExists(beanFactory, Class.forName(type));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    private boolean matchBeanValues(List<Class<?>> values, ConfigurableListableBeanFactory beanFactory) {
        return values.stream().allMatch(value -> checkAnyFromBeansExists(beanFactory, value));
    }

    private boolean checkAnyFromBeansExists(ConfigurableListableBeanFactory beanFactory, Class<?> beanClass) {
        String[] beanNames = beanFactory.getBeanNamesForType(beanClass);
        return Arrays.stream(beanNames).anyMatch(beanFactory::containsBean);
    }
}
