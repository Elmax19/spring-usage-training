package by.elmax19.app.bean.impl.test4;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component("myBean")
public class TestBean implements InitializingBean, DisposableBean {
    public TestBean() {
        System.out.println("-TestBean-: Constructor invoked");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("-TestBean-: Initialization.");
    }

    @Override
    public void destroy() {
        System.out.println("-TestBean-: Destruction.");
    }
}