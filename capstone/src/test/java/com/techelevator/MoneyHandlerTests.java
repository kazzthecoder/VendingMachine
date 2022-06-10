package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MoneyHandlerTests {

    @Test
    public void balance_should_reflect_added_money() {
        MoneyHandler myMoney = new MoneyHandler();

        myMoney.addMoney("10");

        Assert.assertEquals(BigDecimal.TEN, myMoney.getBalance());
    }

    @Test
    public void charge_test_should_result_six_ninety_five() {
        MoneyHandler myMoney = new MoneyHandler();
        Inventory inv = new Inventory();
        myMoney.setBalance(BigDecimal.TEN);
        myMoney.chargeMoney("A1", inv);
        Assert.assertEquals(new BigDecimal("6.95"), myMoney.getBalance());
    }

    @Test
    public void should_not_charge_if_out_of_stock() {
        MoneyHandler myMoney = new MoneyHandler();
        Inventory inv = new Inventory();
        inv.getItemChoices().get("A1").setInventory(0);
        myMoney.setBalance(BigDecimal.TEN);
        myMoney.chargeMoney("A1", inv);
        Assert.assertEquals(BigDecimal.TEN, myMoney.getBalance());
    }




}
