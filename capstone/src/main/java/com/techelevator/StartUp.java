package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class StartUp{
    Map<String, ItemForSale> itemChoices = new HashMap<>();

    public static void main(String[] args) {

        File file = new File("C:\\Users\\Student\\workspace\\capstone-1-team-02\\capstone\\vendingmachine.csv");
        Map<String, ItemForSale> itemChoices = new HashMap<>();

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
                    itemChoices.put(productName, item);
                }
                if (type.equals("Candy")) {
                    Candy item = new Candy(slotLocation, productName, price, type);
                    itemChoices.put(productName, item);
                }
                if (type.equals("Drink")) {
                    Drink item = new Drink(slotLocation, productName, price, type);
                    itemChoices.put(productName, item);
                }
                if (type.equals("Gum")) {
                    Gum item = new Gum(slotLocation, productName, price, type);
                    itemChoices.put(productName,item);
                }
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("file not available");
        }
        for ( Map.Entry<String, ItemForSale> entry : itemChoices.entrySet()) {
            String key = entry.getKey();
            ItemForSale item = entry.getValue();
             String slotLocation2 = item.getSlotLocation();
            System.out.println(key + " " + slotLocation2);

        }

    }
}