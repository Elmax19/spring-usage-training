package by.elmax19.commonsstarter.bean.impl;

public class HelloWorldBean {
    private final String name;

    public HelloWorldBean(String name) {
        this.name = name;
    }

    public String getMessage() {
        return "Hello, " + name + '!';
    }
}
