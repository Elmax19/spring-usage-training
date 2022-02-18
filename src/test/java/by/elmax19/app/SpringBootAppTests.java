package by.elmax19.app;

import by.elmax19.app.context.ApplicationContextProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class SpringBootAppTests {
    @Autowired
    ApplicationContextProvider appContext;

    @Test
    void contextLoads() {
        System.out.println("Context loading test");
    }

    @Test
    void classPathBeanTest() {
        Assert.isTrue(appContext.classPathContext().containsBean("classPathBean"), "ClassPathXmlApplicationContext should contains classPathBean");
        System.out.println("ClassPathXmlApplicationContext contains classPathBean");
    }

    @Test
    void fileSystemBeanTest() {
        Assert.isTrue(appContext.fileSystemContext().containsBean("fileSystemBean"), "FileSystemXmlApplicationContext should contains fileSystemBean");
        System.out.println("FileSystemXmlApplicationContext contains fileSystemBean");
    }

    @Test
    void freeBeanTest() {
        Assert.isTrue(appContext.applicationContext().containsBean("freeBean"), "ApplicationContext should contains fileSystemBean");
        System.out.println("ApplicationContext contains freeBean");
    }
}
