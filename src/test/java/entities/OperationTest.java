package entities;

import bank.entities.Operation;
import bank.enumerations.OperationType;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OperationTest {
    @Test
    public void createOperationTest() {
        LocalDateTime nowLocalDateTime = LocalDateTime.now();
        BigDecimal amount = new BigDecimal(100);
        BigDecimal currentBalance = new BigDecimal(300);
        Operation operation = new Operation(OperationType.DEPOSIT, amount, currentBalance, nowLocalDateTime);
        Assert.assertEquals(OperationType.DEPOSIT, operation.getOperationType());
        Assert.assertEquals(amount, operation.getAmount());
        Assert.assertEquals(currentBalance, operation.getCurrentBalance());
        Assert.assertEquals(nowLocalDateTime, operation.getDate());
    }
}
