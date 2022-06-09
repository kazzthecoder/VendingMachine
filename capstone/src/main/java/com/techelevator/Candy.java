package com.techelevator;

import java.math.BigDecimal;

public class Candy extends ItemForSale {
    Candy(String slotLocation, String productName, BigDecimal price, String type) {
        super(slotLocation, productName, price, type);
    }
}
