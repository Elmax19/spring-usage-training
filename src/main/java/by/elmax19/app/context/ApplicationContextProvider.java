package by.elmax19.app.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    private ClassPathXmlApplicationContext classPathContext;
    private FileSystemXmlApplicationContext fileSystemContext;
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        classPathContext = new ClassPathXmlApplicationContext("classPathContext.xml");
        fileSystemContext = new FileSystemXmlApplicationContext("classpath:fileSystemContext.xml");
    }

    public ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }

    public ClassPathXmlApplicationContext getClassPathContext() {
        return classPathContext;
    }

    public FileSystemXmlApplicationContext getFileSystemContext() {
        return fileSystemContext;
    }
}
