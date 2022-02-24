package by.elmax19.app;

import by.elmax19.app.bean.impl.expression.InstantDateImpl;
import by.elmax19.app.bean.impl.expression.LocalDateImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@TestPropertySource(properties = {"expression.condition.date=2022-02-23T11:34:26.485415600Z"})
public class InstantDateBeanTest {
    @Autowired
    private ApplicationContext context;

    @Test
    @DisplayName("InstantDate bean has been created")
    void checkInstantDateBeanCreation() {
        assertDoesNotThrow(() -> context.getBean(InstantDateImpl.class).getDate());
    }

    @Test
    @DisplayName("LocalDate bean has not been injected")
    void checkLocalDateBeanAbsence() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean(LocalDateImpl.class));
    }
}
