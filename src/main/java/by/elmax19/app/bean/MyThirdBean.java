package by.elmax19.app.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class MyThirdBean implements InitializingBean, DisposableBean {
    public MyThirdBean() {
        System.out.println("-Third bean-: Constructor invoked.");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("-Third bean-: Initialization.");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("-Third bean-: post Construction.");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("-Third bean-: preparing for Destruction.");
    }

    @Override
    public void destroy() {
        System.out.println("-Third bean-: Destruction.");
    }
}
