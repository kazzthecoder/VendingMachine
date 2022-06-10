package com.techelevator;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class
VendingMachineCLI {



    public static void main(String[] args) {
        VendingMachineCLI cli = new VendingMachineCLI();
        cli.run();
    }

    // program code should be written in public void run block
    public void run() {
        ShowRunner showRunner = new ShowRunner();
        showRunner.openMainMenu();
        showRunner.openPurchaseMenu();
        }
    }

