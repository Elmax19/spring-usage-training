package by.elmax19.app;

import by.elmax19.app.bean.MyBean;
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
        myBean.process();
        context.registerShutdownHook();
    }
}
