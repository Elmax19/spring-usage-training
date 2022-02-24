package by.elmax19.app.model;

import java.math.BigDecimal;

public class Transaction {
    private BigDecimal balance;
    private Operation operation;
    private BigDecimal amount;

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setOperation(String operation) {
        this.operation = Operation.of(operation);
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getNewBalance() {
        return operation.getNewBalance(balance, amount);
    }
}
