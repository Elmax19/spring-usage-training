package by.elmax19.app.bean.impl.test3;

import by.elmax19.app.bean.ThirdTestBeanInterface;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class TestThreeFirstBean implements ThirdTestBeanInterface, InitializingBean, DisposableBean {
    public TestThreeFirstBean() {
        System.out.println("-TestThree-FirstBean-: Constructor invoked");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("-TestThree-FirstBean-: Initialization.");
    }

    @Override
    public void destroy() {
        System.out.println("-TestThree-FirstBean-: Destruction.");
    }
}