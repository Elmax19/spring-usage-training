package by.elmax19.app;

import by.elmax19.app.bean.FirstInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FirstCaseTest {
    @Autowired
    private FirstInterface firstInterfaceFirstImpl;
    @Autowired
    private FirstInterface firstInterfaceSecondImpl;

    @Test
    @DisplayName("First bean has FirstInterfaceFirstImpl type")
    void checkFirstBeanByClassName() {
        assertEquals(firstInterfaceFirstImpl.getClass().getSimpleName(), "FirstInterfaceFirstImpl");
    }

    @Test
    @DisplayName("Second bean has FirstInterfaceSecondImpl type")
    void checkSecondBeanByClassName() {
        assertEquals(firstInterfaceSecondImpl.getClass().getSimpleName(), "FirstInterfaceSecondImpl");
    }
}
