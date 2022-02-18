package by.elmax19.app.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

@Configuration
public class AppConfig {
    @Bean
    public ClassPathXmlApplicationContext classPathContext() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classPathContext.xml");
        classPathXmlApplicationContext.registerShutdownHook();
        return classPathXmlApplicationContext;
    }

    @Bean
    public FileSystemXmlApplicationContext fileSystemContext() {
        FileSystemXmlApplicationContext fileSystemContext = new FileSystemXmlApplicationContext("classpath:fileSystemContext.xml");
        fileSystemContext.registerShutdownHook();
        return fileSystemContext;
    }
}
