package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ShowRunnerTests {

    @Test
    public void adding_negative_money_should_not_change_balance() {
        MoneyHandler mrMoney = new MoneyHandler();
        ShowRunner mrShow = new ShowRunner();
        mrShow.mrMoney.setBalance(BigDecimal.TEN);
        mrMoney.addMoney("-3");

        Assert.assertEquals(BigDecimal.TEN, mrShow.mrMoney.getBalance());
    }
}
