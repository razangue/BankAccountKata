package bank.services.impl;

import bank.entities.Account;
import bank.entities.Operation;
import bank.enumerations.OperationType;
import bank.services.inter.AccountTransaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class AccountTransactionService implements AccountTransaction {
    private AccountTransactionValidatorService accountTransactionValidatorService;

    public AccountTransactionService(AccountTransactionValidatorService accountTransactionValidatorService) {
        this.accountTransactionValidatorService = accountTransactionValidatorService;
    }

    public void makeDeposit(Account account, BigDecimal amount) {
        if (this.accountTransactionValidatorService.isValidDeposit(account, amount)) {
            BigDecimal currentBalance = account.getBalance().add(amount);
            makeOperation(account, OperationType.DEPOSIT, amount, currentBalance);
        }
    }

    public void makeWithdrawal(Account account, BigDecimal amount) {
        if (this.accountTransactionValidatorService.isValidWithdraw(account, amount)) {
            BigDecimal currentBalance = account.getBalance().subtract(amount);
            makeOperation(account, OperationType.WITHDRAWAL, amount, currentBalance);
        }
    }

    public List<String> getAccountHistory(Account account) {
        List<String> accountHistory = new ArrayList<>();
        if (account != null) {
            Consumer<Operation> operationConsumer = operation -> accountHistory.add(createHistoryLine(operation));
            account.getOperations().forEach(operation -> operationConsumer.accept(operation));
        }
        return accountHistory;
    }

    private void makeOperation(Account account, OperationType operationType, BigDecimal amount, BigDecimal currentBalance) {
        // create operation
        Operation depositOperation = new Operation(operationType, amount, currentBalance, LocalDateTime.now());
        // update balance
        account.setBalance(currentBalance);
        // operations account
        account.getOperations().add(depositOperation);
    }

    public String createHistoryLine(Operation operation) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy hh:mm:ss");
        StringBuilder builder = new StringBuilder();
        builder.append(operation.getOperationType());
        builder.append(" Operation on ");
        builder.append(operation.getDate().format(formatter));
        builder.append(" of Amount: ");
        builder.append(String.valueOf(operation.getAmount()));
        builder.append(",  Balance : ");
        builder.append(String.valueOf(operation.getCurrentBalance()));
        return builder.toString();
    }
}
