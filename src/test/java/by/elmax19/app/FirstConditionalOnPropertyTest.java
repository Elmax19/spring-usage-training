package by.elmax19.app;

import by.elmax19.app.bean.OnPropertyInterface;
import by.elmax19.app.bean.impl.property.custom.CustomOnClassBean;
import by.elmax19.app.bean.impl.property.OnPropertyFirstImpl;
import by.elmax19.app.bean.impl.property.OnPropertySecondImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource(properties = {"property.condition.selected.class = first"})
public class FirstConditionalOnPropertyTest {
    @Autowired
    private OnPropertyInterface bean;
    @Autowired
    private ApplicationContext context;

    @Test
    @DisplayName("First bean has been injected")
    void checkFirstBeanCreation() {
        assertEquals(bean.getClass(), OnPropertyFirstImpl.class);
    }

    @Test
    @DisplayName("Second bean has not been injected")
    void checkSecondBeanAbsence() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean(OnPropertySecondImpl.class));
    }

    @Test
    @DisplayName("Custom onClass bean has been injected")
    void checkOnBeanCreation() {
        assertDoesNotThrow(() -> context.getBean(CustomOnClassBean.class));
    }
}
