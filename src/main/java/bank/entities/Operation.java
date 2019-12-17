package bank.entities;

import bank.enumerations.OperationType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Operation {
    private OperationType operationType;
    private BigDecimal amount;
    private BigDecimal currentBalance;
    private LocalDateTime date;

    public Operation(OperationType operationType, BigDecimal amount, BigDecimal currentBalance, LocalDateTime date) {
        this.operationType = operationType;
        this.amount = amount;
        this.currentBalance = currentBalance;
        this.date = date;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
