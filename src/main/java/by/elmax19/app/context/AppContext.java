package by.elmax19.app.context;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class AppContext {
    private final ClassPathXmlApplicationContext classPathContext = new ClassPathXmlApplicationContext("classPathContext.xml");
    private final FileSystemXmlApplicationContext fileSystemContext = new FileSystemXmlApplicationContext("classpath:fileSystemContext.xml");

    public ClassPathXmlApplicationContext getClassPathContext() {
        return classPathContext;
    }

    public FileSystemXmlApplicationContext getFileSystemContext() {
        return fileSystemContext;
    }
}
