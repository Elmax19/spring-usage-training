package by.elmax19.app.bean.impl;

import by.elmax19.app.bean.FirstTestBeanInterface;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class TestOneFirstBean implements FirstTestBeanInterface, InitializingBean, DisposableBean {
    public TestOneFirstBean() {
        System.out.println("-TestOneFirstBean-: Constructor invoked");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("-TestOneFirstBean-: Initialization.");
    }

    @Override
    public void destroy() {
        System.out.println("-TestOneFirstBean-: Destruction.");
    }
}
