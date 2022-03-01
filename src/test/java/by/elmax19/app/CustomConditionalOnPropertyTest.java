package by.elmax19.app;

import by.elmax19.app.bean.CustomOnPropertyInterface;
import by.elmax19.app.bean.impl.property.custom.FirstCustomOnPropertyImpl;
import by.elmax19.app.bean.impl.property.custom.SecondCustomOnPropertyImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource(properties = {"property.condition.selected.class = first"})
public class CustomConditionalOnPropertyTest {
    @Autowired
    private CustomOnPropertyInterface bean;
    @Autowired
    private ApplicationContext context;

    @Test
    @DisplayName("First bean has been injected")
    void checkFirstBeanCreation() {
        assertEquals(bean.getClass(), FirstCustomOnPropertyImpl.class);
    }

    @Test
    @DisplayName("Second bean has not been injected")
    void checkSecondBeanAbsence() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean(SecondCustomOnPropertyImpl.class));
    }
}