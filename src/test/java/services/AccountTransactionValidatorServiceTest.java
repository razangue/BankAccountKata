package services;

import bank.entities.Account;
import bank.services.impl.AccountTransactionValidatorService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class AccountTransactionValidatorServiceTest {
    private AccountTransactionValidatorService accountTransactionValidatorService;
    private Account account;

    @Before
    public void init() {
        this.account = new Account("razangue", 5111988);
        if (this.accountTransactionValidatorService == null) {
            this.accountTransactionValidatorService = new AccountTransactionValidatorService();
        }
    }

    @Test
    public void isValidAmountOKTest() {
        Assert.assertTrue(this.accountTransactionValidatorService.isValidAmount(new BigDecimal(100)));
    }

    @Test
    public void isValidAmountKOTest() {
        Assert.assertFalse(this.accountTransactionValidatorService.isValidAmount(new BigDecimal(-100)));
    }

    @Test
    public void isValidDepositOKTest() {
        Assert.assertTrue(this.accountTransactionValidatorService.isValidDeposit(this.account, new BigDecimal(200)));
    }

    @Test
    public void isValidDepositKOAmountTest() {
        Assert.assertFalse(this.accountTransactionValidatorService.isValidDeposit(this.account, new BigDecimal(-200)));
    }

    @Test
    public void isValidDepositKONullAccountTest() {
        Assert.assertFalse(this.accountTransactionValidatorService.isValidDeposit(null, new BigDecimal(200)));
    }

    @Test
    public void isValidDepositKONullBalanceAccountTest() {
        this.account.setBalance(null);
        Assert.assertFalse(this.accountTransactionValidatorService.isValidDeposit(this.account, new BigDecimal(200)));
    }

    @Test
    public void isValidDepositKOAmountAndAccountTest() {
        Assert.assertFalse(this.accountTransactionValidatorService.isValidDeposit(null, new BigDecimal(-200)));
    }

    @Test
    public void isValidWithdrawOKTest() {
        account.setBalance(new BigDecimal(1000));
        Assert.assertTrue(this.accountTransactionValidatorService.isValidWithdraw(account, new BigDecimal(100)));
    }

    @Test
    public void isValidWithdrawKOTest() {
        account.setBalance(new BigDecimal(1000));
        Assert.assertFalse(this.accountTransactionValidatorService.isValidWithdraw(account, new BigDecimal(1500)));
    }
}
