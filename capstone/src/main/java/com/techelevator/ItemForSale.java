package com.techelevator;

import java.math.BigDecimal;

public class ItemForSale {
    private String slotLocation;
    private String productName;
    private BigDecimal price;
    private String type;
    private int inventory;
    private String sound;


    ItemForSale(String slotLocation, String productName, BigDecimal price, String type, String sound) {
        this.slotLocation = slotLocation;
        this.productName = productName;
        this.price = price;
        this.type = type;
        this.inventory = 5;
        this.sound  = sound;

    } // this is for test purposes only
    public ItemForSale () {

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

    public String getSound() {return sound;}

    public void setInventory(int newInventory) {
        this.inventory = newInventory;
    }
}
