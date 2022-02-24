package by.elmax19.app.bean.impl.resource;

import by.elmax19.app.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@ConditionalOnResource(resources = {"transaction.json"})
public class ResourceBean {
    private final Transaction transaction;

    public ResourceBean() throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/transaction.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        transaction = objectMapper.readValue(jsonData, Transaction.class);
    }

    public BigDecimal getNewBalance() {
        return transaction.getNewBalance();
    }
}
