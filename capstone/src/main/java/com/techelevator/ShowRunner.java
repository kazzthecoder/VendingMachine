package com.techelevator;

import java.math.BigDecimal;
import java.math.BigInteger;
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
        this.mainMenuGreeting = "\n /$$    /$$                           /$$                   /$$      /$$             /$$     /$$                  /$$$$$$   /$$$$$$   /$$$$$$ \n| $$   | $$                          | $$                  | $$$    /$$$            | $$    |__/                 /$$__  $$ /$$$_  $$ /$$$_  $$\n| $$   | $$  /$$$$$$  /$$$$$$$   /$$$$$$$  /$$$$$$         | $$$$  /$$$$  /$$$$$$  /$$$$$$   /$$  /$$$$$$$      | $$  \\ $$| $$$$\\ $$| $$$$\\ $$\n|  $$ / $$/ /$$__  $$| $$__  $$ /$$__  $$ /$$__  $$ /$$$$$$| $$ $$/$$ $$ |____  $$|_  $$_/  | $$ /$$_____/      |  $$$$$$/| $$ $$ $$| $$ $$ $$\n \\  $$ $$/ | $$$$$$$$| $$  \\ $$| $$  | $$| $$  \\ $$|______/| $$  $$$| $$  /$$$$$$$  | $$    | $$| $$             >$$__  $$| $$\\ $$$$| $$\\ $$$$\n  \\  $$$/  | $$_____/| $$  | $$| $$  | $$| $$  | $$        | $$\\  $ | $$ /$$__  $$  | $$ /$$| $$| $$            | $$  \\ $$| $$ \\ $$$| $$ \\ $$$\n   \\  $/   |  $$$$$$$| $$  | $$|  $$$$$$$|  $$$$$$/        | $$ \\/  | $$|  $$$$$$$  |  $$$$/| $$|  $$$$$$$      |  $$$$$$/|  $$$$$$/|  $$$$$$/\n    \\_/     \\_______/|__/  |__/ \\_______/ \\______/         |__/     |__/ \\_______/   \\___/  |__/ \\_______/       \\______/  \\______/  \\______/ \n";
        this.mainMenuScript = "\n\n***MAIN MENU***\n\n Please select a number: \n\n(1) Display Vending Machine Items \n(2) Purchase \n(3) Exit\n\n";
        this.purchaseMenuScript = "\n\n***PURCHASE MENU***\n\nPlease select a number: \n\n(1) Feed Money \n(2) Purchase Item \n(3) Finish Transaction\n\n";
        this.moneyFeedPrompt = "\nPlease enter number of dollars you would like to feed:\n\n";
        this.optionUnavailable = "\n1Invalid input! Please enter a valid number!\n";
        this.mainMenuOn = true;
        this.purchaseMenuOn = true;
        this.inventory = new Inventory();
        this.mrMoney = new MoneyHandler();
        this.mrLogger = new Logger();
        this.coins = new Coins();
    }


    public void openMainMenu() {
        System.out.println(mainMenuGreeting);
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
                mainMenuOn = true;
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
                BigDecimal dollarCheck = new BigDecimal(dollarsFedIn);

                if (dollarCheck.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("Silly you, can't add negative money.  Try again.");
                } else if (dollarCheck.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) != 0) {
                    System.out.println("You can only add whole dollars.  Please try again.");
                } else {
                    mrMoney.addMoney(dollarsFedIn);
                    mrLogger.logMoneyIn(dollarsFedIn, mrMoney.getBalance());
                }

            } else if (productMenuChoice.equals("2")) {
                System.out.println("\nYour balance is: $" + mrMoney.getBalance() + "\n");
                inventory.displayAllInventoryData();
                System.out.println("\nPlease Enter a Slot: \n");
                String productCode = userInput.nextLine().toUpperCase();
           //     mrMoney.chargeMoney(productCode, inventory);
                try {
                    inventory.subtractInventory(productCode);
                    mrMoney.chargeMoney(productCode, inventory);
                    mrMoney.printReceipt(inventory.getItemChoices().get(productCode));
                    BigDecimal logPrice = inventory.getItemChoices().get(productCode).getPrice();
                    String logName = inventory.getItemChoices().get(productCode).getProductName();
                    mrLogger.logVendItem(logName, productCode, logPrice, mrMoney.getBalance());
                } catch (NullPointerException npe) {
                    System.out.println("Not a valid code.");
                }
            } else if (productMenuChoice.equals("3")) {
                BigDecimal finalBalance = coins.makeChange(mrMoney.getBalance());
                mrLogger.logGiveChange(mrMoney.getBalance(),finalBalance);
                mrMoney.setBalance(BigDecimal.ZERO);
                purchaseMenuOn = false;
                mainMenuOn = true;
                openMainMenu();


            } else {
                System.out.println(optionUnavailable);
            }
        }
    }


}

