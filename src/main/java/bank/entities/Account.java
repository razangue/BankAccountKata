package bank.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String ownerAccountName;
    private long accountNumber;
    private BigDecimal balance;
    private List<Operation> operations;

    public Account(String ownerAccountName, long accountNumber) {
        this.ownerAccountName = ownerAccountName;
        this.accountNumber = accountNumber;
        this.balance = new BigDecimal(0);
        this.operations = new ArrayList<>();
    }


    public String getOwnerAccountName() {
        return ownerAccountName;
    }

    public void setOwnerAccountName(String ownerAccountName) {
        this.ownerAccountName = ownerAccountName;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
}
