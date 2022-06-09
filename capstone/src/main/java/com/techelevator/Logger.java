package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;


public class Logger {
    static final File LOG_FILE = new File("log.txt");
    static final String TIME_STAMP = String.valueOf(LocalDateTime.now());

    public static void logMoneyIn() {
        try (PrintWriter logOutput = new PrintWriter(new FileOutputStream((LOG_FILE), true))) {
            logOutput.println(TIME_STAMP + " FEED MONEY: " + "balance before" + " balance after");
        } catch (FileNotFoundException fnf) {
            System.out.println("The file was not found");
        }
    }

    public void logVendItem() {
        try (PrintWriter logOutput = new PrintWriter(new FileOutputStream((LOG_FILE), true))) {
            logOutput.println(TIME_STAMP + "item vended" + "item slot" + "balance before" + " balance after");
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