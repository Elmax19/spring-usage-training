package by.elmax19.app;

import by.elmax19.app.bean.MyAnnotatedBean;
import by.elmax19.app.bean.MyBean;
import by.elmax19.app.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringBootApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MyBean myBean = (MyBean) context.getBean("myBean");
        MyAnnotatedBean myAnnotatedBean = AppConfig.myAnnotatedBean();
        System.out.println("----------PROCESSING--------------");
        myBean.process();
        myAnnotatedBean.action();
        System.out.println("----------DESTRUCTION-------------");
        context.registerShutdownHook();
    }
}
