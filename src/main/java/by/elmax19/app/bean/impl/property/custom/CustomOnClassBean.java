package by.elmax19.app.bean.impl.property.custom;

import by.elmax19.app.annotation.CustomConditionalOnClass;
import org.springframework.stereotype.Component;

@Component
@CustomConditionalOnClass(name = {"com.fasterxml.jackson.databind.ObjectMapper"})
public class CustomOnClassBean {
}
