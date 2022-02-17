package by.elmax19.app.bean;

import javax.annotation.PreDestroy;

public class MyAnnotatedBean {
    public void init() {
        System.out.println("Annotated bean Initialization.");
    }

    public void action() {
        System.out.println("Some Actions with Annotated bean.");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("Annotated bean preparing for Destruction.");
    }

    public void destroy() {
        System.out.println("Annotated bean Destruction.");
    }
}
