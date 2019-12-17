package bank.services.inter;

import bank.entities.Account;

import java.math.BigDecimal;

public interface AccountTransactionValidator {
    boolean isValidAmount(BigDecimal amount);

    boolean isValidDeposit(Account account, BigDecimal amount);

    boolean isValidWithdraw(Account account, BigDecimal amount);
}
