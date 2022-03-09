package by.elmax19.commonsstarter.bean.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "database", name = "workspace.prefix")
public class PropertyBean {
    @Value("${database.workspace.prefix}")
    private String prefix;

    public String getMessage() {
        return "Hello, " + prefix + '!';
    }
}
