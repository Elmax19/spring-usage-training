package by.elmax19.app.config;

import by.elmax19.app.bean.MyAnnotatedBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.PostConstruct;

@Configuration
public class AppConfig {
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public MyAnnotatedBean myAnnotatedBean() {
        return new MyAnnotatedBean();
    }

    @PostConstruct
    public void postConstruct(){
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.registerShutdownHook();
    }
}
