package by.elmax19.app.config;

import by.elmax19.app.bean.JavaBasedBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.PostConstruct;

@Configuration
public class AppConfig {
    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public JavaBasedBean javaBasedBean() {
        return new JavaBasedBean();
    }

    @PostConstruct
    public void postConstruct() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.registerShutdownHook();
    }
}
