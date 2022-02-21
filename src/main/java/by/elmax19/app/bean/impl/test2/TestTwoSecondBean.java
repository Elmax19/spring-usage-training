package by.elmax19.app.bean.impl.test2;

import by.elmax19.app.bean.SecondTestBeanInterface;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component("secondBean")
public class TestTwoSecondBean implements SecondTestBeanInterface, InitializingBean, DisposableBean {
    public TestTwoSecondBean() {
        System.out.println("-TestTwo-SecondBean-: Constructor invoked");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("-TestTwo-SecondBean-: Initialization.");
    }

    @Override
    public void destroy() {
        System.out.println("-TestTwo-SecondBean-: Destruction.");
    }
}