package by.elmax19.commonsstarter.bean.impl;

import org.springframework.beans.factory.annotation.Value;

public class PropertyBean {
    @Value("${database.workspace-prefix}")
    private String prefix;

    public String getMessage() {
        return "Hello, " + prefix + '!';
    }
}
