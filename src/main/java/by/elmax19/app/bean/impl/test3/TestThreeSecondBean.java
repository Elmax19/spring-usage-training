package by.elmax19.app.bean.impl.test3;

import by.elmax19.app.bean.ThirdTestBeanInterface;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class TestThreeSecondBean implements ThirdTestBeanInterface, InitializingBean, DisposableBean {
    public TestThreeSecondBean() {
        System.out.println("-TestThree-SecondBean-: Constructor invoked");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("-TestThree-SecondBean-: Initialization.");
    }

    @Override
    public void destroy() {
        System.out.println("-TestThree-SecondBean-: Destruction.");
    }
}
