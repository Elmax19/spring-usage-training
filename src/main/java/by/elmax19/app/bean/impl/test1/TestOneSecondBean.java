package by.elmax19.app.bean.impl.test1;

import by.elmax19.app.bean.FirstTestBeanInterface;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class TestOneSecondBean implements FirstTestBeanInterface, InitializingBean, DisposableBean {
    public TestOneSecondBean() {
        System.out.println("-TestOne-SecondBean-: Constructor invoked");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("-TestOne-SecondBean-: Initialization.");
    }

    @Override
    public void destroy() {
        System.out.println("-TestOne-SecondBean-: Destruction.");
    }
}