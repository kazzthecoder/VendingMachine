package com.techelevator;

import java.math.BigDecimal;
import java.util.Scanner;

public class ShowRunner {
    String mainMenuGreeting;
    String mainMenuScript;
    String purchaseMenuScript;
    String optionUnavailable;
    String moneyFeedPrompt;
    Boolean mainMenuOn;
    Boolean purchaseMenuOn;
    Inventory inventory;
    MoneyHandler mrMoney;
    Logger mrLogger;
    Coins coins;


    ShowRunner() {
        this.mainMenuGreeting = "\nWelcome to the vending machine!\n";
        this.mainMenuScript = "***MAIN MENU***\n Please select a number: \n(1) Display Vending Machine Items \n(2) Purchase \n(3) Exit";
        this.purchaseMenuScript = "\n***PURCHASE MENU***\n Please select a number: \n(1) Feed Money \n(2) Purchase Item \n(3) Finish Transaction\n";
        this.moneyFeedPrompt = "Please enter number of dollars you would like to feed:";
        this.optionUnavailable = "Selected option not available! please Enter a valid number! !";
        this.mainMenuOn = true;
        this.purchaseMenuOn = true;
        this.inventory = new Inventory();
        this.mrMoney = new MoneyHandler();
        this.mrLogger = new Logger();
        this.coins = new Coins();
    }


    public void openMainMenu() {
        while (mainMenuOn) {
            Scanner userInput = new Scanner(System.in);
            System.out.println(mainMenuScript);
            String mainMenuChoice = userInput.nextLine();
            if (mainMenuChoice.equals("1")) {
                inventory.displayNameAndInventory();
            } else if (mainMenuChoice.equals("2")) {
                mainMenuOn = false;
            } else if(mainMenuChoice.equals("3")) {
                System.out.println("Goodbye! Please come again!");
                mainMenuOn = false;
                purchaseMenuOn = false;
                break;
            } else {
                System.out.println(optionUnavailable);
            }
        }
    }

    public void openPurchaseMenu() {
        while (purchaseMenuOn) {
            Scanner userInput = new Scanner(System.in);
            System.out.println(purchaseMenuScript);
            String productMenuChoice = userInput.nextLine();
            if (productMenuChoice.equals("1")) {
                System.out.println(moneyFeedPrompt);
                String dollarsFedIn = userInput.nextLine();
                mrMoney.addMoney(dollarsFedIn);
                mrLogger.logMoneyIn(dollarsFedIn, mrMoney.getBalance());

            } else if (productMenuChoice.equals("2")) {
                System.out.println("Your balance is: $" + mrMoney.getBalance());
                inventory.displayAllInventoryData();
                System.out.println("Please Select Corresponding Code: \n");
                String productCode = userInput.nextLine().toUpperCase();
                mrMoney.vendAndChargeMoney(productCode);
                inventory.subtractInventory(productCode);
                mrMoney.printReceipt(inventory.getItemChoices().get(productCode));
                BigDecimal logPrice = inventory.getItemChoices().get(productCode).getPrice();
                String logName = inventory.getItemChoices().get(productCode).getProductName();
                mrLogger.logVendItem(logName, productCode, logPrice, mrMoney.getBalance());

            } else if (productMenuChoice.equals("3")) {
                BigDecimal finalBalance = coins.makeChange(mrMoney.getBalance());
                mrMoney.setBalance(BigDecimal.ZERO);
                mrLogger.logGiveChange(mrMoney.getBalance(),finalBalance);
                purchaseMenuOn = false;
                mainMenuOn = true;
                openMainMenu();


            } else {
                System.out.println(optionUnavailable);
            }
        }
    }


}

