package by.elmax19.app.bean.impl.property;

import by.elmax19.app.bean.OnPropertyInterface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "property.condition", name = "selected.class", havingValue = "second")
public class OnPropertySecondImpl implements OnPropertyInterface {
}
