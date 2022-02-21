package by.elmax19.app.been;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public abstract class Bean implements CustomApplicationContextAwareBean {
    private ApplicationContext context;

    @Override
    public ApplicationContext getContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
