package by.elmax19.app.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class MyFourthBean implements InitializingBean, DisposableBean {
    public MyFourthBean() {
        System.out.println("-Fourth bean-: Constructor invoked.");
    }

    public void initMethod() {
        System.out.println("-Fourth bean-: Configuration Initialization method.");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("-Fourth bean-: Implementation Initialization method.");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("-Fourth bean-: post Construction.");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("-Fourth bean-: preparing for Destruction.");
    }

    public void destroyMethod() {
        System.out.println("-Fourth bean-: Configuration Destruction method.");
    }

    @Override
    public void destroy() {
        System.out.println("-Fourth bean-: Implementation Destruction method.");
    }
}
