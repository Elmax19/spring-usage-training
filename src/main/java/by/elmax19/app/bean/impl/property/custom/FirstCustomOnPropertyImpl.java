package by.elmax19.app.bean.impl.property.custom;

import by.elmax19.app.annotation.CustomConditionalOnProperty;
import by.elmax19.app.bean.CustomOnPropertyInterface;
import org.springframework.stereotype.Component;

@Component
@CustomConditionalOnProperty(name = "property.condition.selected.class", value = "first")
public class FirstCustomOnPropertyImpl implements CustomOnPropertyInterface {
}
