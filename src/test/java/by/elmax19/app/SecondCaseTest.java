package by.elmax19.app;

import by.elmax19.app.bean.SecondInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SecondCaseTest {
    @Qualifier("firstBean")
    @Autowired
    private SecondInterface secondInterfaceFirstBean;
    @Qualifier("secondBean")
    @Autowired
    private SecondInterface secondInterfaceSecondBean;

    @Test
    @DisplayName("First bean has SecondInterfaceFirstImpl type")
    void checkFirstBeanByClassName() {
        assertEquals(secondInterfaceFirstBean.getClass().getSimpleName(), "SecondInterfaceFirstImpl");
    }

    @Test
    @DisplayName("Second bean has SecondInterfaceSecondImpl type")
    void checkSecondBeanByClassName() {
        assertEquals(secondInterfaceSecondBean.getClass().getSimpleName(), "SecondInterfaceSecondImpl");
    }
}
