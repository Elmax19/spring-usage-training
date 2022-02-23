package by.elmax19.app;

import by.elmax19.app.bean.OnPropertyInterface;
import by.elmax19.app.bean.impl.property.OnPropertyFirstImpl;
import by.elmax19.app.bean.impl.property.OnPropertySecondImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@TestPropertySource(properties = {"property.condition.selected.class = first"})
public class FirstConditionalOnPropertyTest {
    @Autowired
    private OnPropertyInterface bean;
    @Autowired
    private ApplicationContext context;

    @Test
    @DisplayName("Only First bean has been injected")
    void checkFirstBeanCreation() {
        assertEquals(bean.getClass(), OnPropertyFirstImpl.class);
        if (context.containsBean("onPropertySecondImpl")) {
            assertNotEquals(context.getBean("onPropertySecondImpl").getClass(), OnPropertySecondImpl.class);
        }
    }
}
