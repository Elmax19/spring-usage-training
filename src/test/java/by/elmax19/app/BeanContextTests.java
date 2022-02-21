package by.elmax19.app;

import by.elmax19.app.been.AnotherClassPathBean;
import by.elmax19.app.been.Bean;
import by.elmax19.app.been.ClassPathBean;
import by.elmax19.app.been.FileSystemBean;
import by.elmax19.app.been.FreeBean;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BeanContextTests {
    private static final BeanClassAndContextScenario firstClassPathScenario = new BeanClassAndContextScenario();
    private static final BeanClassAndContextScenario secondClassPathScenario = new BeanClassAndContextScenario();
    private static final BeanClassAndContextScenario fileSystemScenario = new BeanClassAndContextScenario();
    private static final BeanClassAndContextScenario applicationScenario = new BeanClassAndContextScenario();
    private static class BeanClassAndContextScenario {
        private String contextClassName;
        private Bean bean;

        public String getContextClassName() {
            return contextClassName;
        }

        public Bean getBean() {
            return bean;
        }

        public void setApplicationContextAndBean(String contextClassName, Bean bean) {
            this.contextClassName = contextClassName;
            this.bean = bean;
        }
    }

    @Qualifier("classPathContext")
    @Autowired
    private ClassPathXmlApplicationContext tmpClassPathContext;
    @Autowired
    private FileSystemXmlApplicationContext tmpFileSystemContext;
    @Autowired
    private AnotherClassPathBean anotherClassPathBean;
    @Autowired
    private FreeBean freeBean;

    @PostConstruct
    public void init() {
        firstClassPathScenario.setApplicationContextAndBean(
                "ClassPathXmlApplicationContext",
                tmpClassPathContext.getBean(ClassPathBean.class));
        secondClassPathScenario.setApplicationContextAndBean(
                "AnnotationConfigApplicationContext",
                anotherClassPathBean);
        fileSystemScenario.setApplicationContextAndBean(
                "FileSystemXmlApplicationContext",
                tmpFileSystemContext.getBean(FileSystemBean.class));
        applicationScenario.setApplicationContextAndBean(
                "AnnotationConfigApplicationContext",
                freeBean);
    }

    @ParameterizedTest
    @MethodSource("beanScenarioProvider")
    void checkBeanContext(BeanClassAndContextScenario scenario) {
        checkContext(scenario.getContextClassName(), scenario.getBean());
    }

    private static Stream<Arguments> beanScenarioProvider() {
        return Stream.of(
                Arguments.of(firstClassPathScenario),
                Arguments.of(secondClassPathScenario),
                Arguments.of(fileSystemScenario),
                Arguments.of(applicationScenario));
    }

    private void checkContext(String contextClassName, Bean bean) {
        assertEquals(contextClassName, bean.getContext().getClass().getSimpleName());
    }
}
