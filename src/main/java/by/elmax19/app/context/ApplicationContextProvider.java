package by.elmax19.app.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public ApplicationContext applicationContext() {
        return this.applicationContext;
    }

    @Bean
    public ClassPathXmlApplicationContext classPathContext() {
        return new ClassPathXmlApplicationContext("classPathContext.xml");
    }

    @Bean
    public FileSystemXmlApplicationContext fileSystemContext() {
        return new FileSystemXmlApplicationContext("classpath:fileSystemContext.xml");
    }
}
