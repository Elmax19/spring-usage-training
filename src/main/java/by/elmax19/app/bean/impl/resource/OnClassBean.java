package by.elmax19.app.bean.impl.resource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnClass(name = "com.fasterxml.jackson.databind.ObjectMapper")
public class OnClassBean {
    public OnClassBean() {
        System.out.println("Bean with ConditionalOnClass annotation has bean created");
    }
}
