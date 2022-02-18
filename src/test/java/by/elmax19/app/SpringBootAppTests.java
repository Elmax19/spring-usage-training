package by.elmax19.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.util.Assert;

@SpringBootTest
class SpringBootAppTests {
    @Autowired
    private ClassPathXmlApplicationContext classPathContext;
    @Autowired
    private FileSystemXmlApplicationContext fileSystemContext;
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        System.out.println("Context loading test");
    }

    @Test
    void classPathBeanTest() {
        Assert.isTrue(classPathContext.containsBean("classPathBean"), "ClassPathXmlApplicationContext should contains classPathBean");
        System.out.println("ClassPathXmlApplicationContext contains classPathBean");
    }

    @Test
    void fileSystemBeanTest() {
        Assert.isTrue(fileSystemContext.containsBean("fileSystemBean"), "FileSystemXmlApplicationContext should contains fileSystemBean");
        System.out.println("FileSystemXmlApplicationContext contains fileSystemBean");
    }

    @Test
    void freeBeanTest() {
        Assert.isTrue(applicationContext.containsBean("freeBean"), "ApplicationContext should contains fileSystemBean");
        System.out.println("ApplicationContext contains freeBean");
    }
}
