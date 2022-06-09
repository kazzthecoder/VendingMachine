package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class StartUp {
    File file;
    Map<String, ItemForSale> itemChoices;
    ;

    StartUp() {
        this.file = new File("C:\\Users\\Student\\workspace\\capstone-1-team-02\\capstone\\vendingmachine.csv");
        this.itemChoices = new LinkedHashMap<>();
    }

    public Map<String, ItemForSale> getItemChoices() {
        return itemChoices;
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
    public void displayInventory() {
        System.out.println("Slot:     Product:      Price:     Inventory");
        for (Map.Entry<String, ItemForSale> entry : itemChoices.entrySet()) {
            ItemForSale item = entry.getValue();
            String itemSlot = item.getSlotLocation();
            String itemName = item.getProductName();
            BigDecimal itemPrice = item.getPrice();
            int itemInventory = item.getInventory();
            System.out.println(itemSlot + "\t" + itemName + "\t\t\t" + itemPrice + "\t\t\t" + itemInventory);
        }
    }
}
















