package by.elmax19.app;

import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("ClassPathXmlApplicationContext contains classPathBean")
    void classPathBeanTest() {
        Assert.isTrue(classPathContext.containsBean("classPathBean"), "ClassPathXmlApplicationContext doesn't contain classPathBean");
    }

    @Test
    @DisplayName("FileSystemXmlApplicationContext contains fileSystemBean")
    void fileSystemBeanTest() {
        Assert.isTrue(fileSystemContext.containsBean("fileSystemBean"), "FileSystemXmlApplicationContext doesn't contain fileSystemBean");
        System.out.println("");
    }

    @Test
    @DisplayName("ApplicationContext contains freeBean")
    void freeBeanTest() {
        Assert.isTrue(applicationContext.containsBean("freeBean"), "ApplicationContext doesn't contain fileSystemBean");
    }
}
