package com.techelevator;

import java.math.BigDecimal;

public class ItemForSale {
    private String slotLocation;
    private String productName;
    private BigDecimal price;
    private String type;
    private int inventory;


    ItemForSale(String slotLocation, String productName, BigDecimal price, String type) {
        this.slotLocation = slotLocation;
        this.productName = productName;
        this.price = price;
        this.type = type;
        this.inventory = 5;
    }

    public String getSlotLocation() {
        return slotLocation;
    }
    public String getProductName() {
        return productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }
    public int getInventory() {
        return inventory;
    }

    public void setInventory(int newInventory) {
        this.inventory = newInventory;
    }
}
