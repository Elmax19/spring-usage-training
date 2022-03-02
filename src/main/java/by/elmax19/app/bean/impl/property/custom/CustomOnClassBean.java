package by.elmax19.app.bean.impl.property.custom;

import by.elmax19.app.annotation.CustomConditionalOnClass;
import by.elmax19.app.bean.impl.property.OnPropertyFirstImpl;
import org.springframework.stereotype.Component;

@Component
@CustomConditionalOnClass(value = {OnPropertyFirstImpl.class})
public class CustomOnClassBean {
}
