package by.elmax19.app.been;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public interface CustomApplicationContextAwareBean extends ApplicationContextAware {
    ApplicationContext getContext();
}
