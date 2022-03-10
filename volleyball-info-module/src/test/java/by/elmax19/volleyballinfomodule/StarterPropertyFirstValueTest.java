package by.elmax19.volleyballinfomodule;

import by.elmax19.commonsstarter.bean.impl.HelloWorldBean;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {"hello.world.display-name=TestAccount"})
public class StarterPropertyFirstValueTest {
    @Autowired
    private HelloWorldBean bean;

    @Test
    @DisplayName("Bean message should be 'Hello, TestAccount!'")
    void checkPropertyBeanMessage() {
        assertEquals("Hello, TestAccount!", bean.getMessage());
    }
}
