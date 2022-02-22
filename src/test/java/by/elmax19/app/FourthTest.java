package by.elmax19.app;

import by.elmax19.app.bean.impl.test4.TestBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FourthTest {
    @Qualifier("myBean")
    @Autowired
    private TestBean testBean;
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void checkBeanByClassName(){
        assertTrue(applicationContext.containsBean("myBean"));
        assertFalse(applicationContext.containsBean("testBean"));
    }
}
