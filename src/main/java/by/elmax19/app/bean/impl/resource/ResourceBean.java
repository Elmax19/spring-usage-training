package by.elmax19.app.bean.impl.resource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;

@Component
@ConditionalOnResource(resources = {"balance.json"})
public class ResourceBean {
    private final Long balance;
    private final String operation;
    private final Long amount;

    public ResourceBean() throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader("src/main/resources/balance.json"));
        JSONObject jo = (JSONObject) obj;
        balance = (Long) jo.get("balance");
        operation = (String) jo.get("operation");
        amount = (Long) jo.get("amount");
    }

    public Long getNewBalance() {
        return "debit".equals(operation) ? balance - amount : balance + amount;
    }
}
