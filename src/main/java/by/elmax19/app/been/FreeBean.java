package by.elmax19.app.been;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class FreeBean implements InitializingBean, DisposableBean {
    public FreeBean() {
        System.out.println("-FreeBean-: Constructor invoked");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("-FreeBean-: Initialization.");
    }

    @Override
    public void destroy() {
        System.out.println("-FreeBean-: Destruction.");
    }
}
