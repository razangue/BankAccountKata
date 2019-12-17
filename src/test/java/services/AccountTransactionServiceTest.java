package services;

import bank.entities.Account;
import bank.entities.Operation;
import bank.enumerations.OperationType;
import bank.services.impl.AccountTransactionService;
import bank.services.impl.AccountTransactionValidatorService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class AccountTransactionServiceTest {
    private boolean isInit = false;
    private Account account;
    private AccountTransactionValidatorService accountTransactionValidatorService;
    private AccountTransactionService accountTransactionService;


    @Before
    public void init() {
        this.account = new Account("razangue", 5111988);
        if (!isInit) {
            this.accountTransactionValidatorService = new AccountTransactionValidatorService();
            this.accountTransactionService = new AccountTransactionService(this.accountTransactionValidatorService);
            isInit = true;
        }
    }

    @Test
    public void makeDepositOKTest() {
        long accountNumber = this.account.getAccountNumber();
        String ownerAccountName = this.account.getOwnerAccountName();
        this.account.setBalance(new BigDecimal(100));
        this.accountTransactionService.makeDeposit(this.account, new BigDecimal(200));

        Assert.assertEquals(accountNumber, this.account.getAccountNumber());
        Assert.assertEquals(ownerAccountName, this.account.getOwnerAccountName());
        Assert.assertEquals(new BigDecimal(300), this.account.getBalance());
        Assert.assertEquals(1, this.account.getOperations().size());

        Operation operation = this.account.getOperations().get(0);
        Assert.assertEquals(OperationType.DEPOSIT, operation.getOperationType());
        Assert.assertEquals(new BigDecimal(200), operation.getAmount());
        Assert.assertEquals(new BigDecimal(300), operation.getCurrentBalance());
        Assert.assertNotNull(operation.getDate());
    }

    @Test
    public void makeDepositKOAmountTest() {
        long accountNumber = this.account.getAccountNumber();
        String ownerAccountName = this.account.getOwnerAccountName();
        this.account.setBalance(new BigDecimal(100));
        this.accountTransactionService.makeDeposit(this.account, new BigDecimal(-200));

        Assert.assertEquals(accountNumber, this.account.getAccountNumber());
        Assert.assertEquals(ownerAccountName, this.account.getOwnerAccountName());
        Assert.assertEquals(new BigDecimal(100), this.account.getBalance());
        Assert.assertEquals(0, this.account.getOperations().size());
    }

    @Test
    public void makeWithdrawalOKTest() {
        long accountNumber = this.account.getAccountNumber();
        String ownerAccountName = this.account.getOwnerAccountName();
        this.account.setBalance(new BigDecimal(300));
        this.accountTransactionService.makeWithdrawal(this.account, new BigDecimal(200));

        Assert.assertEquals(accountNumber, this.account.getAccountNumber());
        Assert.assertEquals(ownerAccountName, this.account.getOwnerAccountName());
        Assert.assertEquals(new BigDecimal(100), this.account.getBalance());
        Assert.assertEquals(1, this.account.getOperations().size());

        Operation operation = this.account.getOperations().get(0);
        Assert.assertEquals(OperationType.WITHDRAWAL, operation.getOperationType());
        Assert.assertEquals(new BigDecimal(200), operation.getAmount());
        Assert.assertEquals(new BigDecimal(100), operation.getCurrentBalance());
        Assert.assertNotNull(operation.getDate());
    }

    @Test
    public void makeWithdrawalKOAmountTest() {
        long accountNumber = this.account.getAccountNumber();
        String ownerAccountName = this.account.getOwnerAccountName();
        this.account.setBalance(new BigDecimal(300));
        this.accountTransactionService.makeWithdrawal(this.account, new BigDecimal(-100));

        Assert.assertEquals(accountNumber, this.account.getAccountNumber());
        Assert.assertEquals(ownerAccountName, this.account.getOwnerAccountName());
        Assert.assertEquals(new BigDecimal(300), this.account.getBalance());
        Assert.assertEquals(0, this.account.getOperations().size());
    }

    @Test
    public void makeWithdrawalKOBalanceTest() {
        long accountNumber = this.account.getAccountNumber();
        String ownerAccountName = this.account.getOwnerAccountName();
        this.account.setBalance(new BigDecimal(300));
        this.accountTransactionService.makeWithdrawal(this.account, new BigDecimal(800));

        Assert.assertEquals(accountNumber, this.account.getAccountNumber());
        Assert.assertEquals(ownerAccountName, this.account.getOwnerAccountName());
        Assert.assertEquals(new BigDecimal(300), this.account.getBalance());
        Assert.assertEquals(0, this.account.getOperations().size());
    }

    @Test
    public void getAccountHistoryTest() {
        this.accountTransactionService.makeDeposit(this.account, new BigDecimal(200));
        this.accountTransactionService.makeDeposit(this.account, new BigDecimal(500));
        this.accountTransactionService.makeWithdrawal(this.account, new BigDecimal(100));
        this.accountTransactionService.makeDeposit(this.account, new BigDecimal(300));
        this.accountTransactionService.makeWithdrawal(this.account, new BigDecimal(400));

        List<String> accountHistory = this.accountTransactionService.getAccountHistory(this.account);
        Assert.assertEquals(5, accountHistory.size());
        Assert.assertTrue(accountHistory.get(0).contains(OperationType.DEPOSIT.toString()));
        Assert.assertTrue(accountHistory.get(0).contains("200"));
        Assert.assertTrue(accountHistory.get(4).contains(OperationType.WITHDRAWAL.toString()));
        Assert.assertTrue(accountHistory.get(4).contains("400"));
    }

}
