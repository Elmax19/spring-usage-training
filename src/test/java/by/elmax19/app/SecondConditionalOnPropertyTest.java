package by.elmax19.app;

import by.elmax19.app.bean.OnPropertyInterface;
import by.elmax19.app.bean.impl.property.OnPropertyFirstImpl;
import by.elmax19.app.bean.impl.property.OnPropertySecondImpl;
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
@TestPropertySource(properties = {"property.condition.selected.class = second"})
public class SecondConditionalOnPropertyTest {
    @Autowired
    private OnPropertyInterface bean;
    @Autowired
    private ApplicationContext context;

    @Test
    @DisplayName("Second bean has been injected")
    void checkSecondBeanCreation() {
        assertEquals(bean.getClass(), OnPropertySecondImpl.class);
    }

    @Test
    @DisplayName("First bean has not been injected")
    void checkFirstBeanAbsence() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean(OnPropertyFirstImpl.class));
    }
}
