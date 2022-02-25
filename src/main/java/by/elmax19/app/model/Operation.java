package by.elmax19.app.model;

import by.elmax19.app.exception.NoSuchOperationException;

import java.math.BigDecimal;

public enum Operation {
    DEBIT {
        public BigDecimal getNewBalance(BigDecimal balance, BigDecimal amount) {
            return balance.subtract(amount);
        }
    }, CREDIT {
        public BigDecimal getNewBalance(BigDecimal balance, BigDecimal amount) {
            return balance.add(amount);
        }
    };

    public abstract BigDecimal getNewBalance(BigDecimal balance, BigDecimal amount);

    static Operation of(String name) throws NoSuchOperationException {
        for (Operation operation : values()) {
            if (operation.name().equalsIgnoreCase(name)) {
                return operation;
            }
        }
        throw new NoSuchOperationException("No such operation with name: " + name);
    }
}
