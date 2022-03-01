package by.elmax19.app.bean.impl.property;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(type = "by.elmax19.app.bean.impl.property.OnPropertyFirstImpl")
public class OnBeanBean {
}
