package by.elmax19.app.been;

import org.springframework.context.ApplicationContext;

public class BeanScenario {
    private ApplicationContext applicationContext;
    private final String beanName;

    public BeanScenario(String beanName) {
        this.beanName = beanName;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public String getBeanName() {
        return beanName;
    }
}
