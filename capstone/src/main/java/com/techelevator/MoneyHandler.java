package com.techelevator;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Map;

public class MoneyHandler {
    BigDecimal balance = BigDecimal.ZERO;
    String receipt;


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    //methods
    public void addMoney(String dollars) {
        BigDecimal stringBalance = new BigDecimal(dollars);

        balance = balance.add(stringBalance);

    }

    public void vendAndChargeMoney(String productCode) {
        Inventory inventory = new Inventory();
        inventory.inventorySetUp();
        for (Map.Entry<String, ItemForSale> entry : inventory.getItemChoices().entrySet()) {
            if (entry.getKey().equals(productCode)) {
                ItemForSale item = entry.getValue();
                BigDecimal itemPrice = item.getPrice();
                if(balance.compareTo(itemPrice) >= 0) {
                    balance = balance.subtract(itemPrice);
                } else {
                    System.out.println("INSUFFICIENT FUNDS!! Please select another option...");
                }
            }
        }
    }

    public void printReceipt(@NotNull ItemForSale item) {
        if(item.getInventory() == 0) {
            receipt = "SOLD OUT!! Please select another option...";
        }
        else if (item.getType().equals("Chip")) {
            receipt = (item.getProductName() + "  Price:$" + item.getPrice() + "  Current Balance:$" + getBalance() + "  Crunch Crunch, Yum!");
        } else if (item.getType().equals("Candy")) {
            receipt = (item.getProductName() + "  Price:$" + item.getPrice() + "  Current Balance:$" + getBalance() + "  Munch Munch, Yum!");
        } else if (item.getType().equals("Drink")) {
            receipt = (item.getProductName() + "  Price:$" + item.getPrice() + "  Current Balance:$" + getBalance() + "  Glug Glug, Yum!");
        } else if (item.getType().equals("Gum")) {
            receipt = (item.getProductName() + "  Price:$" + item.getPrice() + "  Current Balance:$" + getBalance() +  "  Chew Chew, Yum!");
        }
        System.out.println(receipt);
    }




}





