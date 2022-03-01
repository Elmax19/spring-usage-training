package by.elmax19.app.annotation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomOnPropertyConditionTest {
    @Mock
    private static AnnotatedTypeMetadata metadata;
    private final CustomOnPropertyCondition customOnPropertyCondition = new CustomOnPropertyCondition();
    @Mock
    private ConditionContext context;
    @Mock
    private Environment resolver;

    @Test
    @DisplayName("matches() should return false when attributeName == null")
    void matchesReturnsFalseWhenNullName() {
        when(metadata.getAllAnnotationAttributes(CustomConditionalOnProperty.class.getName()))
                .thenReturn(new LinkedMultiValueMap<>());
        assertFalse(customOnPropertyCondition.matches(context, metadata));
        verifyNoInteractions(context);
    }

    @Test
    @DisplayName("matches() should return false when attributeValue == null")
    void matchesReturnsFalseWhenNullValue() {
        when(metadata.getAllAnnotationAttributes((CustomConditionalOnProperty.class.getName())))
                .thenReturn(new LinkedMultiValueMap<>(
                        Map.of("name", List.of("property.condition.selected.class"))));
        assertFalse(customOnPropertyCondition.matches(context, metadata));
        verifyNoInteractions(context);
    }

    @Test
    @DisplayName("matches() should return false when attributeValue is different from property")
    void matchesReturnsFalseWhenAnotherValue() {
        when(metadata.getAllAnnotationAttributes((CustomConditionalOnProperty.class.getName())))
                .thenReturn(new LinkedMultiValueMap<>(
                        Map.of("name", List.of("property.condition.selected.class"),
                                "value", List.of("first"))));
        when(context.getEnvironment()).thenReturn(resolver);
        when(resolver.getProperty(any())).thenReturn("second");
        assertFalse(customOnPropertyCondition.matches(context, metadata));
        verify(context).getEnvironment();
    }

    @Test
    @DisplayName("matches() should return true")
    void matchesReturnsTrue() {
        when(metadata.getAllAnnotationAttributes((CustomConditionalOnProperty.class.getName())))
                .thenReturn(new LinkedMultiValueMap<>(
                        Map.of("name", List.of("property.condition.selected.class"),
                                "value", List.of("first"))));
        when(context.getEnvironment()).thenReturn(resolver);
        when(resolver.getProperty(any())).thenReturn("first");
        assertTrue(customOnPropertyCondition.matches(context, metadata));
    }
}