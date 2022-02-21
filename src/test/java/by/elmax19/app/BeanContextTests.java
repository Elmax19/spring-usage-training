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
    private static class BeanClassAndContextScenario {
        private ApplicationContext applicationContext;
        private Bean bean;

        public ApplicationContext getApplicationContext() {
            return applicationContext;
        }

        public Bean getBean() {
            return bean;
        }

        public void setApplicationContextAndBean(ApplicationContext applicationContext, Bean bean) {
            this.applicationContext = applicationContext;
            this.bean = bean;
        }
    }

    private static final BeanClassAndContextScenario firstClassPathScenario = new BeanClassAndContextScenario();
    private static final BeanClassAndContextScenario secondClassPathScenario = new BeanClassAndContextScenario();
    private static final BeanClassAndContextScenario fileSystemScenario = new BeanClassAndContextScenario();
    private static final BeanClassAndContextScenario applicationScenario = new BeanClassAndContextScenario();

    @Qualifier("classPathContext")
    @Autowired
    private ClassPathXmlApplicationContext tmpClassPathContext;
    @Autowired
    private FileSystemXmlApplicationContext tmpFileSystemContext;
    @Autowired
    private ApplicationContext tmpApplicationContext;

    @PostConstruct
    public void init() {
        firstClassPathScenario.setApplicationContextAndBean(
                tmpClassPathContext,
                tmpClassPathContext.getBean(ClassPathBean.class));
        secondClassPathScenario.setApplicationContextAndBean(
                tmpApplicationContext,
                tmpApplicationContext.getBean(AnotherClassPathBean.class));
        fileSystemScenario.setApplicationContextAndBean(
                tmpFileSystemContext,
                tmpFileSystemContext.getBean(FileSystemBean.class));
        applicationScenario.setApplicationContextAndBean(
                tmpApplicationContext,
                tmpApplicationContext.getBean(FreeBean.class));
    }

    @ParameterizedTest
    @MethodSource("beanScenarioProvider")
    void checkBeanContext(BeanClassAndContextScenario scenario) {
        checkBeanContainedByContext(scenario.getApplicationContext(), scenario.getBean());
    }

    private static Stream<Arguments> beanScenarioProvider() {
        return Stream.of(
                Arguments.of(firstClassPathScenario),
                Arguments.of(secondClassPathScenario),
                Arguments.of(fileSystemScenario),
                Arguments.of(applicationScenario));
    }

    private void checkBeanContainedByContext(ApplicationContext context, Bean bean) {
        assertEquals(context, bean.getContext());
    }
}
