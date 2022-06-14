package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.rmi.MarshalledObject;
import java.util.Map;
import java.util.Scanner;

public class ShowRunnerTests {

    private MoneyHandler moneyHandler;
    ShowRunner showRunner;
    Inventory inventory;
    ItemForSale item;
    private File file;


    @Before
    public void Setup() {
        MoneyHandler moneyHandler = new MoneyHandler();
        ShowRunner showRunner = new ShowRunner();
        this.file = new File("C:\\Users\\Student\\workspace\\capstone-1-team-02\\capstone\\vendingmachine.csv");
        ItemForSale item;
    }

    @Test
    public void when_main_menu_is_open__and_user_selects_1_A_LIST_of_products_IS_displayed_with_inventory() {
        showRunner.openMainMenu();
        Map<String, Integer> actual = showRunner.mainMenuChoice("1");
        Map<String, Integer> expected = inventory.displayNameAndInventory();
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void adding_negative_money_should_not_change_balance() {
        showRunner.openPurchaseMenu();
        showRunner.moneyHandler.setBalance(BigDecimal.TEN);
        moneyHandler.addMoney("-3");

        Assert.assertEquals(BigDecimal.TEN, showRunner.moneyHandler.getBalance());
    }

    @Test
    public void when_exiting_purchase_menu_inventory_remains_same() {
        showRunner.openMainMenu();
        String ProductCode = "A1";
        showRunner.mainMenuChoice(ProductCode);

        showRunner.openPurchaseMenu();
        showRunner.inventory.subtractInventory(ProductCode);
        showRunner.inventory.subtractInventory(ProductCode);
        showRunner.inventory.subtractInventory(ProductCode);
        showRunner.purchaseMenuOn =true;

        int expected = 3;
        int actual= inventory.getItemChoices().get("A1").getInventory();

        Assert.assertEquals(expected,actual);


    }


}
