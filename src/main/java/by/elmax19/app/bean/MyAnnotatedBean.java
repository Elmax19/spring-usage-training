package by.elmax19.app.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class MyAnnotatedBean {
    public MyAnnotatedBean() {
        System.out.println("-Annotated bean-: Constructor invoked.");
    }

    public void init() {
        System.out.println("-Annotated bean-: Initialization.");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("-Annotated bean-: post Construction.");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("-Annotated bean-: preparing for Destruction.");
    }

    public void destroy() {
        System.out.println("-Annotated bean-: Destruction.");
    }
}
