package by.elmax19.app.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

@Configuration
public class ApplicationContextProvider {
    @Bean
    public ClassPathXmlApplicationContext classPathContext() {
        return new ClassPathXmlApplicationContext("classPathContext.xml");
    }

    @Bean
    public FileSystemXmlApplicationContext fileSystemContext() {
        return new FileSystemXmlApplicationContext("classpath:fileSystemContext.xml");
    }
}
