package com.techelevator;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
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
    MoneyHandler moneyHandler;
    Logger logger;
    Coins coins;
    Scanner scanner = new Scanner(System.in);


    ShowRunner() {
        this.mainMenuGreeting = "\n /$$    /$$                           /$$                   /$$      /$$             /$$     /$$                  /$$$$$$   /$$$$$$   /$$$$$$ \n| $$   | $$                          | $$                  | $$$    /$$$            | $$    |__/                 /$$__  $$ /$$$_  $$ /$$$_  $$\n| $$   | $$  /$$$$$$  /$$$$$$$   /$$$$$$$  /$$$$$$         | $$$$  /$$$$  /$$$$$$  /$$$$$$   /$$  /$$$$$$$      | $$  \\ $$| $$$$\\ $$| $$$$\\ $$\n|  $$ / $$/ /$$__  $$| $$__  $$ /$$__  $$ /$$__  $$ /$$$$$$| $$ $$/$$ $$ |____  $$|_  $$_/  | $$ /$$_____/      |  $$$$$$/| $$ $$ $$| $$ $$ $$\n \\  $$ $$/ | $$$$$$$$| $$  \\ $$| $$  | $$| $$  \\ $$|______/| $$  $$$| $$  /$$$$$$$  | $$    | $$| $$             >$$__  $$| $$\\ $$$$| $$\\ $$$$\n  \\  $$$/  | $$_____/| $$  | $$| $$  | $$| $$  | $$        | $$\\  $ | $$ /$$__  $$  | $$ /$$| $$| $$            | $$  \\ $$| $$ \\ $$$| $$ \\ $$$\n   \\  $/   |  $$$$$$$| $$  | $$|  $$$$$$$|  $$$$$$/        | $$ \\/  | $$|  $$$$$$$  |  $$$$/| $$|  $$$$$$$      |  $$$$$$/|  $$$$$$/|  $$$$$$/\n    \\_/     \\_______/|__/  |__/ \\_______/ \\______/         |__/     |__/ \\_______/   \\___/  |__/ \\_______/       \\______/  \\______/  \\______/ \n";
        this.mainMenuScript = "\n\n***MAIN MENU***\n\n Please select a number: \n\n(1) Display Vending Machine Items \n(2) Purchase \n(3) Exit\n\n";
        this.purchaseMenuScript = "\n\n***PURCHASE MENU***\n\nPlease select a number: \n\n(1) Feed Money \n(2) Purchase Item \n(3) Finish Transaction\n\n";
        this.moneyFeedPrompt = "\nPlease enter number of dollars you would like to feed:\n\n";
        this.optionUnavailable = "\nInvalid input! Please enter a valid number!\n";
        this.mainMenuOn = false;
        this.purchaseMenuOn = true;
        this.inventory = new Inventory();
        this.moneyHandler = new MoneyHandler();
        this.logger = new Logger();
        this.coins = new Coins();

    }


    public void openMainMenu() {
        ShowRunner showRunner = new ShowRunner();
        System.out.println(mainMenuGreeting);
        System.out.println(mainMenuScript);
        mainMenuOn = true;

    }


   public  HashMap<String, Integer> mainMenuChoice(String userInput) {
        while (mainMenuOn) {
            String mainMenuChoice = scanner.nextLine();
            if (mainMenuChoice.equals("1")) {
                return inventory.displayNameAndInventory();
            } else if (mainMenuChoice.equals("2")) {
                mainMenuOn = false;
            } else if (mainMenuChoice.equals("3")) {
                System.out.println("\nGoodbye! Please come again!");
                mainMenuOn = true;
                purchaseMenuOn = false;
                break;

            } else {
                System.out.println(optionUnavailable);
            }
        }
        return inventory.displayNameAndInventory();
    }

    public void openPurchaseMenu() {
        while (purchaseMenuOn) {
            Scanner userInput = new Scanner(System.in);
            System.out.println(purchaseMenuScript);
            String productMenuChoice = userInput.nextLine();
            if (productMenuChoice.equals("1")) {
                System.out.println(moneyFeedPrompt);
                try {String dollarsFedIn = userInput.nextLine();
                BigDecimal dollarCheck = new BigDecimal(dollarsFedIn);

                if (dollarCheck.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("\nSilly you, can't add negative money.  Try again.");
                } else if (dollarCheck.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) != 0) {
                    System.out.println("\nYou can only add whole dollars.  Please try again.");
                } else {
                    moneyHandler.addMoney(dollarsFedIn);
                    logger.logMoneyIn(dollarsFedIn, moneyHandler.getBalance());
                }} catch(NumberFormatException nfe) {
                    System.out.println("\n Inout Not Valid not try again.");
                }

            } else if (productMenuChoice.equals("2")) {
                System.out.printf("\nYour balance is: $%.2f\n\n", moneyHandler.getBalance());
                inventory.displayAllInventoryData();
                System.out.println("\nPlease Enter a Slot: \n");
                String productCode = userInput.nextLine().toUpperCase();
                //     mrMoney.chargeMoney(productCode, inventory);
                try {
                    inventory.subtractInventory(productCode);
                    moneyHandler.chargeMoney(productCode, inventory);
                    moneyHandler.printReceipt(inventory.getItemChoices().get(productCode));
                    BigDecimal logPrice = inventory.getItemChoices().get(productCode).getPrice();
                    String logName = inventory.getItemChoices().get(productCode).getProductName();
                    logger.logVendItem(logName, productCode, logPrice, moneyHandler.getBalance());
                } catch (NullPointerException npe) {
                    System.out.println("Not a valid code.");
                }
            } else if (productMenuChoice.equals("3")) {
                BigDecimal finalBalance = coins.makeChange(moneyHandler.getBalance());
                logger.logGiveChange(moneyHandler.getBalance(), finalBalance);
                moneyHandler.setBalance(BigDecimal.ZERO);
                purchaseMenuOn = false;
                mainMenuOn = true;
                openMainMenu();


            } else {
                System.out.println(optionUnavailable);
            }
        }
    }


}

