package by.elmax19.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

@SpringBootTest
class SpringBootAppTests {
    private static class BeanScenario {
        private ApplicationContext applicationContext;
        private final String beanName;

        public BeanScenario(String beanName) {
            this.beanName = beanName;
        }

        public ApplicationContext getApplicationContext() {
            return applicationContext;
        }

        public void setApplicationContext(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        public String getBeanName() {
            return beanName;
        }
    }

    private static final BeanScenario classPathScenario = new BeanScenario("classPathBean");
    private static final BeanScenario fileSystemScenario = new BeanScenario("fileSystemBean");
    private static final BeanScenario applicationScenario = new BeanScenario("freeBean");

    @Autowired
    private ClassPathXmlApplicationContext tmpClassPathContext;
    @Autowired
    private FileSystemXmlApplicationContext tmpFileSystemContext;
    @Autowired
    private ApplicationContext tmpApplicationContext;

    @PostConstruct
    public void init() {
        classPathScenario.setApplicationContext(tmpClassPathContext);
        fileSystemScenario.setApplicationContext(tmpFileSystemContext);
        applicationScenario.setApplicationContext(tmpApplicationContext);
    }

    @Test
    void contextLoads() {
        System.out.println("Context loading test");
    }

    @ParameterizedTest
    @MethodSource("beanContextAndNameProvider")
    void checkBeanContext(BeanScenario scenario) {
        Assertions.assertTrue(scenario.getApplicationContext().containsBean(scenario.getBeanName()));
    }

    private static Stream<Arguments> beanContextAndNameProvider() {
        return Stream.of(
                Arguments.of(classPathScenario),
                Arguments.of(fileSystemScenario),
                Arguments.of(applicationScenario));
    }
}
