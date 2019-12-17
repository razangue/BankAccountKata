package entities;

import bank.entities.Account;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class AccountTest {

    @Test
    public void createAccountTest() {
        Account account = new Account("razangue", 5111988);
        Assert.assertEquals("razangue", account.getOwnerAccountName());
        Assert.assertEquals(5111988, account.getAccountNumber());
        Assert.assertEquals(new BigDecimal(0), account.getBalance());
        Assert.assertNotNull(account.getOperations());
        Assert.assertTrue(account.getOperations().isEmpty());
    }
}
