package by.elmax19.app.config;

import by.elmax19.app.bean.MyAnnotatedBean;
import by.elmax19.app.bean.MyFourthBean;
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

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public MyFourthBean myFourthBean() {
        return new MyFourthBean();
    }

    @PostConstruct
    public void postConstruct() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.registerShutdownHook();
    }
}
