package by.elmax19.app;

import by.elmax19.app.bean.SecondTestBeanInterface;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SecondCaseTest {
    private static final Scenario firstScenario = new Scenario();
    private static final Scenario secondScenario = new Scenario();

    private static class Scenario {
        SecondTestBeanInterface bean;
        String beanClassName;

        public SecondTestBeanInterface getBean() {
            return bean;
        }

        public String getBeanClassName() {
            return beanClassName;
        }

        public void setBeanAndBeanClassName(SecondTestBeanInterface bean, String beanClassName) {
            this.bean = bean;
            this.beanClassName = beanClassName;
        }
    }

    @Qualifier("firstBean")
    @Autowired
    private SecondTestBeanInterface testTwoFirstBean;
    @Qualifier("secondBean")
    @Autowired
    private SecondTestBeanInterface testTwoSecondBean;

    @PostConstruct
    private void init() {
        firstScenario.setBeanAndBeanClassName(testTwoFirstBean, "TestTwoFirstBean");
        secondScenario.setBeanAndBeanClassName(testTwoSecondBean, "TestTwoSecondBean");
    }

    @ParameterizedTest
    @MethodSource("beanScenarioProvider")
    void checkBeanByClassName(Scenario scenario) {
        assertEquals(scenario.getBean().getClass().getSimpleName(), scenario.getBeanClassName());
    }

    private static Stream<Arguments> beanScenarioProvider() {
        return Stream.of(
                Arguments.of(firstScenario),
                Arguments.of(secondScenario));
    }
}
