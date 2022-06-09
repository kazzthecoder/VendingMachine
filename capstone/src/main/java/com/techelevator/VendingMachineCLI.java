package com.techelevator;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
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
        StartUp start = new StartUp();
        start.inventorySetUp();
        MoneyHandler mrMoney = new MoneyHandler();

        System.out.println("\nWelcome to the vending machine!\n");  // aka greeting
        while (mainMenu) {

            Scanner userInput = new Scanner(System.in);
            System.out.println("***MAIN MENU***\n");
            System.out.println("Enter a number: \n(1) Display Vending Machine Items \n(2) Purchase \n(3) Exit");
            String mainMenuChoice = userInput.nextLine();
            if (mainMenuChoice.equals("1")) {
                start.displayInventory();
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
                System.out.println("Select purchase **items will be displayed with their prices and codes");
                productCode = userInput.nextLine();
                for (Map.Entry<String, ItemForSale> entry : start.itemChoices.entrySet()) {
                    if(entry.getKey().equals(productCode)) {
                        ItemForSale item = entry.getValue();
                        mrMoney.chargeMoney(item);
                    }
                }
                start.itemChoices.containsKey(productCode);
                System.out.println("*call dispense here, which will also print item name, cost, and balance, along with sound of item");

                // ^^^ this transaction should all take place in ShowRunner and/or MoneyHandler probably MoneyHandler
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
