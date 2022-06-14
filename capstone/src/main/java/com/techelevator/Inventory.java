package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
    private File file = new File("C:\\Users\\Student\\workspace\\capstone-1-team-02\\capstone\\vendingmachine.csv");
    private Map<String, ItemForSale> itemChoices;
    ;

     Inventory(File file) {
         file = new File("C:\\Users\\Student\\workspace\\capstone-1-team-02\\capstone\\vendingmachine.csv");
        this.itemChoices = new LinkedHashMap<>();
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

    public Inventory() {

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

    public void displayAllInventoryData() {
        System.out.printf(" %-7s %-20s %-10s %-12s\n", "Slot:", "Product:","Price:","Inventory:");
        System.out.println(" _____________________________________________________");
        for (Map.Entry<String, ItemForSale> entry : itemChoices.entrySet()) {
            ItemForSale item = entry.getValue();
            String itemSlot = item.getSlotLocation();
            String itemName = item.getProductName();
            BigDecimal itemPrice = item.getPrice();
            int itemInventory = item.getInventory();
            System.out.printf("| %-7s %-20s %-10s %-12s|\n", itemSlot, itemName, "$" + itemPrice.toString(), String.valueOf(itemInventory));
        }
        System.out.println(" _____________________________________________________");
    }

    public HashMap<String,Integer> displayNameAndInventory() {
        System.out.println("\nProduct:        Inventory:");
        Map<String, Integer> nameInventoryMap = new LinkedHashMap<>();
        for (Map.Entry<String, ItemForSale> entry : itemChoices.entrySet()) {
            ItemForSale item = entry.getValue();
            String itemName = item.getProductName();
            int itemInventory = item.getInventory();
            System.out.printf("%18s  %3s\n", itemName, String.valueOf(itemInventory));
            nameInventoryMap.put(itemName,item.getInventory());

        }
        return (HashMap<String, Integer>) nameInventoryMap;
    }

    public void subtractInventory(String productCode) {
        ItemForSale item = itemChoices.get(productCode);
        int currentInventory = item.getInventory();
        if (currentInventory > 0) {
            item.setInventory(currentInventory - 1);
        }
        }
    }


















