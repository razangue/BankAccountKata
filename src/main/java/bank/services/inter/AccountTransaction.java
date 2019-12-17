package bank.services.inter;

import bank.entities.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountTransaction {
    void makeDeposit(Account account, BigDecimal amount);

    void makeWithdrawal(Account account, BigDecimal amount);

    List<String> getAccountHistory(Account account);
}
