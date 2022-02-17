package by.elmax19.app.been;

import org.springframework.stereotype.Component;

@Component
public class FreeBean {
    public FreeBean() {
        System.out.println("FreeBean: Constructor invoked");
    }
}
