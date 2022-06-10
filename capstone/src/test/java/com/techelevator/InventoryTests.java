package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class InventoryTests {

    @Test
    public void constructor_sets_inventory_to_5_on_startup() {
        ItemForSale item = new ItemForSale("A2", "Test", BigDecimal.ONE, "type", "sound");

        int result = item.getInventory();

        Assert.assertEquals(5, result);

    }

    @Test
    public void when_given_product_Code_expect_to_subtract_inventory() {
        Inventory inventory = new Inventory();
        String productCode = "A1";
        inventory.subtractInventory(productCode);
        int expected = 4;
        int actual = inventory.getItemChoices().get(productCode).getInventory();
        Assert.assertEquals(expected,actual);
    }
}
