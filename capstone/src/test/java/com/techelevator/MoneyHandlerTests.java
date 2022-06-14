package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MoneyHandlerTests {

    MoneyHandler moneyHandler;
    ShowRunner showRunner;
    Inventory inventory;
    ItemForSale item;

    @Before
    public void Setup() {
        moneyHandler = new MoneyHandler();
        showRunner = new ShowRunner();
        inventory = new Inventory();
    }


    @Test
    public void when_given_number_balance_should_reflect_added_money() {

        moneyHandler.addMoney("10");

        Assert.assertEquals(BigDecimal.TEN, moneyHandler.getBalance());
    }

    @Test
    public void When_User_tries_to_feed_Negative_money_should_get_errorPrompt() {
        BigDecimal expected = BigDecimal.ZERO;
        BigDecimal actual = showRunner.moneyHandler.addMoney("-1");
        Assert.assertEquals( expected, actual );
    }

    @Test
    public void charge_test_should_subtract_from_currentBalance_if_item_is_in_stock() {
       moneyHandler.setBalance(BigDecimal.TEN);
        moneyHandler.chargeMoney("A1", inventory);
        Assert.assertEquals(new BigDecimal("6.95"), moneyHandler.getBalance());
    }

    @Test
    public void should_not_charge_if_out_of_stock() {
        inventory.getItemChoices().get("A1").setInventory(0);
        moneyHandler.setBalance(BigDecimal.TEN);
        moneyHandler.chargeMoney("A1", inventory);
        Assert.assertEquals(BigDecimal.TEN, moneyHandler.getBalance());
    }


}
