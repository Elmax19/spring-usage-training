package by.elmax19.app.bean.impl.expression;

import by.elmax19.app.bean.DateInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@ConditionalOnExpression("#{T(java.util.regex.Pattern).matches(\"\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d*?Z\",\"${expression.condition.date}\")}")
public class InstantDateImpl implements DateInterface<Instant> {
    @Value("${expression.condition.date}")
    private Instant date;

    @Override
    public Instant getDate() {
        return date;
    }
}

