package by.elmax19.app;

import by.elmax19.app.bean.impl.resource.ResourceBean;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ResourceBeanTest {
    @Autowired
    private ResourceBean bean;

    @Test
    @DisplayName("getBalance() method should return correct answer")
    void checkResourceBeanWorking() {
        assertEquals(bean.getNewBalance(), 65);
    }
}
