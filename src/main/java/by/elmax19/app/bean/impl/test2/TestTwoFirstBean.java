package by.elmax19.app.bean.impl.test2;

import by.elmax19.app.bean.SecondTestBeanInterface;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component("firstBean")
public class TestTwoFirstBean implements SecondTestBeanInterface, InitializingBean, DisposableBean {
    public TestTwoFirstBean() {
        System.out.println("-TestTwo-FirstBean-: Constructor invoked");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("-TestTwo-FirstBean-: Initialization.");
    }

    @Override
    public void destroy() {
        System.out.println("-TestTwo-FirstBean-: Destruction.");
    }
}