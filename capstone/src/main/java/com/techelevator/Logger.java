package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;


public class Logger {
    static final File LOG_FILE = new File("log.txt");
    static final String TIME_STAMP = String.valueOf(LocalDateTime.now());

    public static void logMoneyIn(String moneyIn, BigDecimal newBalance) {
        try (PrintWriter logOutput = new PrintWriter(new FileOutputStream((LOG_FILE), true))) {
            BigDecimal printMoneyIn = new BigDecimal(moneyIn);
//            logOutput.printf(TIME_STAMP + " FEED MONEY: " + "$" + moneyIn + " $" + newBalance);
            logOutput.printf(TIME_STAMP + " FEED MONEY: $%.2s $%.2f", printMoneyIn, newBalance + "\n");
        } catch (FileNotFoundException fnf) {
            System.out.println("The file was not found");
        }
    }

    public void logVendItem(String itemVended, String slotVended, BigDecimal costVended, BigDecimal balanceVended) {
        try (PrintWriter logOutput = new PrintWriter(new FileOutputStream((LOG_FILE), true))) {
            logOutput.println(TIME_STAMP + itemVended + " " + slotVended + " " + costVended  + " " + balanceVended);
        } catch (FileNotFoundException fnf) {
            System.out.println("The file was not found");
        }
    }

    public static void logGiveChange() {
        try (PrintWriter logOutput = new PrintWriter(new FileOutputStream((LOG_FILE), true))) {
            logOutput.println(TIME_STAMP + " GIVE CHANGE: " + "balance before" + " balance after");
        } catch (FileNotFoundException fnf) {
            System.out.println("The file was not found");
        }

    }

}