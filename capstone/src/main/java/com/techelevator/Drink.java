package com.techelevator;

import java.math.BigDecimal;

public class Drink extends ItemForSale {
    Drink(String slotLocation, String productName, BigDecimal price, String type) {
        super(slotLocation, productName, price, type);
    }
}
