package com.techelevator;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Map;

public class MoneyHandler {
    BigDecimal balance = BigDecimal.ZERO;
    String receipt;
    public MoneyHandler() {

    }


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

    public void chargeMoney(String productCode, Inventory inventory) {
        for (Map.Entry<String, ItemForSale> entry : inventory.getItemChoices().entrySet()) {
            if (entry.getKey().equals(productCode)) {
                ItemForSale item = entry.getValue();
                BigDecimal itemPrice = item.getPrice();

                if ((balance.compareTo(itemPrice) < 0)) {
                    System.out.println("INSUFFICIENT FUNDS!! Please select another option...");
                }
                else if ((item.getInventory() < 1)){
                    System.out.println("SOLD OUT! Please select another item.");
                    break;
                }
                balance = balance.subtract(itemPrice);
            }
        }
    }

    public void printReceipt (ItemForSale item) {
//        if(item.getInventory() == 0) {
//            receipt = "SOLD OUT!! Please select another option...";
//        }
//        else {
            receipt = (item.getProductName() + "  Price:$" + item.getPrice() + "  Current Balance:  $" + getBalance() + " " + item.getSound());
//        }

        System.out.println(receipt);
    }




}





