package by.elmax19.app.bean.impl.expression;

import by.elmax19.app.bean.DateInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@ConditionalOnMissingBean(value = InstantDateImpl.class)
public class NotInstantDateImpl implements DateInterface<LocalDate> {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Value("${expression.condition.date}")
    private LocalDate date;

    @Override
    public LocalDate getDate() {
        return date;
    }
}
