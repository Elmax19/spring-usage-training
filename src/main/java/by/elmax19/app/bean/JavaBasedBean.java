package by.elmax19.app.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class JavaBasedBean implements InitializingBean, DisposableBean {
    public JavaBasedBean() {
        System.out.println("-JavaBasedBean-: Constructor invoked.");
    }

    public void initMethod() {
        System.out.println("-JavaBasedBean-: Configuration Initialization method.");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("-JavaBasedBean-: Implementation Initialization method.");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("-JavaBasedBean-: Post Construction.");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("-JavaBasedBean-: Preparing for Destruction.");
    }

    public void destroyMethod() {
        System.out.println("-JavaBasedBean-: Configuration Destruction method.");
    }

    @Override
    public void destroy() {
        System.out.println("-JavaBasedBean-: Implementation Destruction method.");
    }
}
