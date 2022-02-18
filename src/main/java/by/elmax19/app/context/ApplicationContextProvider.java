package by.elmax19.app.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.annotation.PostConstruct;

@Configuration
public class ApplicationContextProvider {
    private final ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classPathContext.xml");
    private final FileSystemXmlApplicationContext fileSystemContext = new FileSystemXmlApplicationContext("classpath:fileSystemContext.xml");

    @Bean
    public ClassPathXmlApplicationContext classPathContext() {
        return classPathXmlApplicationContext;
    }

    @Bean
    public FileSystemXmlApplicationContext fileSystemContext() {
        return fileSystemContext;
    }

    @PostConstruct
    private void postConstruct() {
        classPathXmlApplicationContext.registerShutdownHook();
        fileSystemContext.registerShutdownHook();
    }
}
