package by.elmax19.app.annotation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomOnClassConditionTest {
    private final CustomOnClassCondition customOnBeanCondition = new CustomOnClassCondition();
    private final ClassLoader classLoader = this.getClass().getClassLoader();
    @Mock
    private AnnotatedTypeMetadata metadata;
    @Mock
    private ConditionContext context;

    @BeforeEach
    void getBeanFactory() {
        when(context.getClassLoader()).thenReturn(classLoader);
    }

    @Test
    @DisplayName("matches() should return false when map is empty")
    void matchesReturnsFalseWhenEmptyMap() {
        when(metadata.getAnnotationAttributes(CustomConditionalOnClass.class.getName()))
                .thenReturn(Map.of());
        assertFalse(customOnBeanCondition.matches(context, metadata));
    }

    @Test
    @DisplayName("matches() should return false when map has no classes names")
    void matchesReturnsFalseWhenMapHasNoNames() {
        when(metadata.getAnnotationAttributes(CustomConditionalOnClass.class.getName()))
                .thenReturn(Map.of("name", new String[]{}));
        assertFalse(customOnBeanCondition.matches(context, metadata));
    }

    @Test
    @DisplayName("matches() should return false when at least bean by name is absent")
    void matchesReturnsFalseWhenNoClassByName() {
        when(metadata.getAnnotationAttributes(CustomConditionalOnClass.class.getName()))
                .thenReturn(Map.of("name", new String[]{
                        "by.elmax19.app.bean.impl.property.custom.FirstCustomOnPropertyImpl",
                        "by.elmax19.app.bean.impl.property.custom.SomeAbsentClass"}));
        assertFalse(customOnBeanCondition.matches(context, metadata));
    }

    @Test
    @DisplayName("matches() should return true")
    void matchesReturnsTrue() {
        when(metadata.getAnnotationAttributes(CustomConditionalOnClass.class.getName()))
                .thenReturn(Map.of("name", new String[]{
                        "by.elmax19.app.bean.impl.property.custom.FirstCustomOnPropertyImpl",
                        "by.elmax19.app.bean.impl.property.custom.SecondCustomOnPropertyImpl"}));
        assertTrue(customOnBeanCondition.matches(context, metadata));
    }
}