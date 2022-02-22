package by.elmax19.app;

import by.elmax19.app.bean.impl.case4.MyBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FourthTest {
    @Qualifier("beanName")
    @Autowired
    private MyBean myBean;
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void checkBeanByClassName(){
        assertDoesNotThrow(() -> applicationContext.getBean(myBean.getClass()));
        assertFalse(applicationContext.containsBean("myBean"));
    }
}
