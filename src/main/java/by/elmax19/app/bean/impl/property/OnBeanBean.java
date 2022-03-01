package by.elmax19.app.bean.impl.property;

import by.elmax19.app.annotation.CustomConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
@CustomConditionalOnBean(value = OnPropertyFirstImpl.class)
public class OnBeanBean {
}
