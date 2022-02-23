package by.elmax19.app;

import by.elmax19.app.bean.OnPropertyInterface;
import by.elmax19.app.bean.impl.property.OnPropertySecondImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {"selected.first = false"})
public class SecondConditionalOnPropertyTest {
    @Autowired
    private OnPropertyInterface bean;

    @Test
    @DisplayName("Only Second bean has been injected")
    void checkSecondBeanCreation() {
        assertEquals(bean.getClass(), OnPropertySecondImpl.class);
    }
}
