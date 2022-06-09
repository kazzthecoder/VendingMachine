package com.techelevator;

import java.math.BigDecimal;

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
            return receipt;
        }
        else if (item.getType().equals("Candy")) {
            receipt = (item.getProductName() +" "+ item.getPrice() +" "+ getBalance() +" "+ "Munch Munch, Yum!");
            return receipt;
        }
        else if (item.getType().equals("Drink")) {
            receipt = (item.getProductName() +" "+ item.getPrice() +" "+ getBalance() +" "+ "Glug Glug, Yum!");
            return receipt;
        }
        else if (item.getType().equals("Gum")) {
            receipt = (item.getProductName() +" "+ item.getPrice() +" "+ getBalance() +" "+ "Chew Chew, Yum!");
            return receipt;
        } return null;
    }


}
