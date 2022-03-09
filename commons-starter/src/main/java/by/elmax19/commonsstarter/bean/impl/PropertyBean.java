package by.elmax19.commonsstarter.bean.impl;

public class PropertyBean {
    private final String prefix;

    public PropertyBean(String prefix) {
        this.prefix = prefix;
    }

    public String getMessage() {
        return "Hello, " + prefix + '!';
    }
}
