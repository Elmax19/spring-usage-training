package by.elmax19.app.bean.impl;

import by.elmax19.app.bean.OnPropertyInterface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component("propertyBean")
@ConditionalOnProperty(name = "selected.first", havingValue = "false")
public class OnPropertySecondImpl implements OnPropertyInterface {
}
