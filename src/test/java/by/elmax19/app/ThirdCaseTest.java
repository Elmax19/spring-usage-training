package by.elmax19.app;

import by.elmax19.app.bean.ThirdInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ThirdCaseTest {
    @Autowired
    private ThirdInterface bean;

    @Test
    void checkBeanByClassName() {
        assertEquals(bean.getClass().getSimpleName(), "ThirdInterfaceSecondImpl");
    }
}
