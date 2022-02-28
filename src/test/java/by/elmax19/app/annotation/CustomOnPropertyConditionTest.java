package by.elmax19.app.annotation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomOnPropertyConditionTest {
    private static CustomOnPropertyCondition customOnPropertyCondition;
    @Mock
    private static AnnotatedTypeMetadata metadata;
    private final MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
    @Mock
    private ConditionContext context;
    @Mock
    private Environment resolver;

    @BeforeAll
    static void init() {
        MockitoAnnotations.openMocks(CustomOnPropertyConditionTest.class);
        customOnPropertyCondition = new CustomOnPropertyCondition();
    }

    @Test
    @DisplayName("matches() should return false when attributeName == null")
    void matchesFirstFalseTest() {
        when(metadata.getAllAnnotationAttributes(any())).thenReturn(map);
        assertFalse(customOnPropertyCondition.matches(context, metadata));
        verify(context, times(0)).getEnvironment();
    }

    @Test
    @DisplayName("matches() should return false when attributeValue == null")
    void matchesSecondFalseTest() {
        map.put("name", Collections.singletonList("property.condition.selected.class"));
        when(metadata.getAllAnnotationAttributes(any())).thenReturn(map);
        assertFalse(customOnPropertyCondition.matches(context, metadata));
        verify(context, times(0)).getEnvironment();
    }

    @Test
    @DisplayName("matches() should return false when attributeValue is different from property")
    void matchesThirdFalseTest() {
        map.put("name", Collections.singletonList("property.condition.selected.class"));
        map.put("value", Collections.singletonList("first"));
        when(metadata.getAllAnnotationAttributes(any())).thenReturn(map);
        when(context.getEnvironment()).thenReturn(resolver);
        when(resolver.getProperty(any())).thenReturn("second");
        assertFalse(customOnPropertyCondition.matches(context, metadata));
        verify(context, times(1)).getEnvironment();
    }

    @Test
    @DisplayName("matches() should return true")
    void matchesTrueTest() {
        map.put("name", Collections.singletonList("property.condition.selected.class"));
        map.put("value", Collections.singletonList("first"));
        when(metadata.getAllAnnotationAttributes(any())).thenReturn(map);
        when(context.getEnvironment()).thenReturn(resolver);
        when(resolver.getProperty(any())).thenReturn("first");
        assertTrue(customOnPropertyCondition.matches(context, metadata));
    }
}