package com.techelevator;

import java.math.BigDecimal;
import java.util.Map;

public class MoneyHandler {
BigDecimal balance = BigDecimal.ZERO;
String receipt;



    public BigDecimal getBalance() {
        return balance;
    }

    //methods
    public void addMoney(String dollars) {
        BigDecimal stringBalance = new BigDecimal(dollars);
        balance = balance.add(stringBalance);

    }
    public void chargeMoney(ItemForSale item) {
        BigDecimal price = item.getPrice();
        balance = balance.subtract(price);
    }
    public String printReceipt(ItemForSale item) {
        if (item.getType().equals("Chip")) {
            receipt = (item.getProductName() +" "+ item.getPrice() +" "+ getBalance() +" "+ "Crunch Crunch, Yum!");
            System.out.println(receipt);
        } else if (item.getType().equals("Candy")) {
            receipt = (item.getProductName() +" "+ item.getPrice() +" "+ getBalance() +" "+ "Munch Munch, Yum!");
            System.out.println(receipt);
        }
        else if (item.getType().equals("Drink")) {
            receipt = (item.getProductName() +" "+ item.getPrice() +" "+ getBalance() +" "+ "Glug Glug, Yum!");
            System.out.println(receipt);
        }
        else if (item.getType().equals("Gum")) {
            receipt = (item.getProductName() +" "+ item.getPrice() +" "+ getBalance() +" "+ "Chew Chew, Yum!");
            System.out.println(receipt);
        } return null;
    }

    public void vendItem(String productCode) {
        Inventory startUp = new Inventory();
        for (Map.Entry<String, ItemForSale> entry : startUp.itemChoices.entrySet()) {
            if(entry.getKey().equals(productCode)) {
                ItemForSale item = entry.getValue();
                chargeMoney(item);
            }
        }


        }
    }
