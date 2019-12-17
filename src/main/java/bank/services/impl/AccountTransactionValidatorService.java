package bank.services.impl;

import bank.entities.Account;
import bank.services.inter.AccountTransactionValidator;

import java.math.BigDecimal;

public class AccountTransactionValidatorService implements AccountTransactionValidator {
    public boolean isValidAmount(BigDecimal amount) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) >= 0;
    }

    public boolean isValidDeposit(Account account, BigDecimal amount) {
        return account != null && account.getBalance() != null && isValidAmount(amount);
    }

    public boolean isValidWithdraw(Account account, BigDecimal amount) {
        return isValidDeposit(account, amount) && account.getBalance().compareTo(amount) >= 0;
    }
}
