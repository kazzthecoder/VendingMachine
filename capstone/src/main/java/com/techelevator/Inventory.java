package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
    File file;
    Map<String, ItemForSale> itemChoices;
    ;

    Inventory() {
        this.file = new File("C:\\Users\\Student\\workspace\\capstone-1-team-02\\capstone\\vendingmachine.csv");
        this.itemChoices = new LinkedHashMap<>();
    }

    public void inventorySetUp() {
        try (Scanner dataInput = new Scanner(file)) {
            while (dataInput.hasNextLine()) {
                String itemLine = dataInput.nextLine();
                String[] itemArray = itemLine.split("\\|");
                String slotLocation = itemArray[0];
                String productName = itemArray[1];
                BigDecimal price = new BigDecimal(itemArray[2]);
                String type = itemArray[3];
                if (type.equals("Chip")) {
                    Chip item = new Chip(slotLocation, productName, price, type);
                    itemChoices.put(slotLocation, item);
                }
                if (type.equals("Candy")) {
                    Candy item = new Candy(slotLocation, productName, price, type);
                    itemChoices.put(slotLocation, item);
                }
                if (type.equals("Drink")) {
                    Drink item = new Drink(slotLocation, productName, price, type);
                    itemChoices.put(slotLocation, item);
                }
                if (type.equals("Gum")) {
                    Gum item = new Gum(slotLocation, productName, price, type);
                    itemChoices.put(slotLocation, item);
                }
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("file not available");
        }
    }
    public void displayAllInventoryData() {
        System.out.println("Slot:           Product:    Price:   Inventory:");
        for (Map.Entry<String, ItemForSale> entry : itemChoices.entrySet()) {
            ItemForSale item = entry.getValue();
            String itemSlot = item.getSlotLocation();
            String itemName = item.getProductName();
            BigDecimal itemPrice = item.getPrice();
            int itemInventory = item.getInventory();
            System.out.printf("%3s %20s %8s %6s\n", itemSlot, itemName, itemPrice.toString(), String.valueOf(itemInventory));
        }
    }

    public void displayNameAndInventory() {
        System.out.println("       Product:   Inventory:");
        for (Map.Entry<String, ItemForSale> entry : itemChoices.entrySet()) {
            ItemForSale item = entry.getValue();
            String itemName = item.getProductName();
            int itemInventory = item.getInventory();
            System.out.printf("%18s  %3s\n", itemName, String.valueOf(itemInventory));
        }
    }

    public void subtractInventory(String productCode) {
        for (Map.Entry<String, ItemForSale> entry : itemChoices.entrySet()) {
            String key = entry.getKey();
            ItemForSale item = entry.getValue();
            if (key.equals(productCode)) {
                item.setInventory(item.getInventory() - 1);
            }
        }
    }
}















