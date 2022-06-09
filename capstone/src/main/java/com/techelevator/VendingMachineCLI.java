package com.techelevator;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {
    // any instance variables will go here
    boolean mainMenu = true;
    boolean purchaseMenu = true;
    String dollarsFedIn;
    String productCode;


    public VendingMachineCLI() {

    }

    public static void main(String[] args) {
        VendingMachineCLI cli = new VendingMachineCLI();
        cli.run();
    }

    // program code should be written in public void run block
    public void run() {
        Inventory inventory = new Inventory();
        inventory.inventorySetUp();
        MoneyHandler mrMoney = new MoneyHandler();

        System.out.println("\nWelcome to the vending machine!\n");  // aka greeting
        while (mainMenu) {

            Scanner userInput = new Scanner(System.in);
            System.out.println("***MAIN MENU***\n");
            System.out.println("Enter a number: \n(1) Display Vending Machine Items \n(2) Purchase \n(3) Exit");
            String mainMenuChoice = userInput.nextLine();
            if (mainMenuChoice.equals("1")) {
                inventory.displayNameAndInventory();
            } else if (mainMenuChoice.equals("2")) {
                mainMenu = false;
            } else {
                System.out.println("Goodbye! Please come again!");
                mainMenu = false;
                purchaseMenu = false;
                break;
            }
        }
        while (purchaseMenu) {
            System.out.println("\n***PURCHASE MENU***");
            Scanner userInput = new Scanner(System.in);
            System.out.println("Enter a number: \n(1) Feed Money \n(2) Purchase Item \n(3) Finish Transaction");
            String productMenuChoice = userInput.nextLine();
            if (productMenuChoice.equals("1")) {
                System.out.println("Please enter number of dollars you would like to feed:");
                dollarsFedIn = userInput.nextLine();
                mrMoney.addMoney(dollarsFedIn);


            } else if (productMenuChoice.equals("2")) {
                System.out.println("Your balance is: " + mrMoney.getBalance());
                inventory.displayAllInventoryData();
                System.out.println("Please Select Corresponding Code: \n");
                productCode = userInput.nextLine();
                inventory.subtractInventory(productCode);
                mrMoney.vendAndChargeMoney(productCode);
                mrMoney.printReceipt(inventory.itemChoices.get(productCode));
            } else {
            //charge money
            //make change

            //return change
            System.out.println("Your change is ...\n");
            System.out.println("Thank you, come again!");
            purchaseMenu = false;
        }


    }

}

}
