package by.elmax19.volleyballinfomodule;

import by.elmax19.commonsstarter.bean.impl.FirstInterfaceImpl;
import by.elmax19.commonsstarter.bean.impl.SecondInterfaceImpl;
import by.elmax19.volleyballinfomodule.bean.impl.VolleyballInfoBean;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ContextBeanImplClassTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @DisplayName("FirstInterface has bean created in volleyball-info-module")
    void checkFirstInterfaceBeanIsFromThisModule() {
        assertDoesNotThrow(() -> applicationContext.getBean(VolleyballInfoBean.class));
        assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(FirstInterfaceImpl.class));
    }

    @Test
    @DisplayName("SecondInterface has bean created in commons-starter")
    void checkSecondInterfaceBeanIsNotFromThisModule() {
        assertDoesNotThrow(() -> applicationContext.getBean(SecondInterfaceImpl.class));
    }
}
