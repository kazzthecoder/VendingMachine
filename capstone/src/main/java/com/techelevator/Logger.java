package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Logger {
    static final File LOG_FILE = new File("log.txt");
    static LocalDateTime myDate = LocalDateTime.now();
    static DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss a");
    static final String TIME_STAMP = myDate.format(myFormatDate);

    public void logMoneyIn(String moneyIn, BigDecimal newBalance) {
        try (PrintWriter logOutput = new PrintWriter(new FileOutputStream((LOG_FILE), true))) {
            BigDecimal printMoneyIn = new BigDecimal(moneyIn);
            logOutput.printf(TIME_STAMP + " FEED MONEY: $%.2f $%.2f\n", printMoneyIn, newBalance);
        } catch (FileNotFoundException fnf) {
            System.out.println("The file was not found");
        }
    }

    public void logVendItem(String itemVended, String slotVended, BigDecimal costVended, BigDecimal balanceVended) {
        try (PrintWriter logOutput = new PrintWriter(new FileOutputStream((LOG_FILE), true))) {
            logOutput.printf(TIME_STAMP + " %s %s %.2f %.2f\n", itemVended, slotVended, costVended, balanceVended);
        } catch (FileNotFoundException fnf) {
            System.out.println("The file was not found");
        }
    }

    public void logGiveChange(BigDecimal balanceBefore, BigDecimal balanceAfter) {
        try (PrintWriter logOutput = new PrintWriter(new FileOutputStream((LOG_FILE), true))) {
            logOutput.printf(TIME_STAMP + " GIVE CHANGE: %.2f %.2f\n",balanceBefore, balanceAfter);
        } catch (FileNotFoundException fnf) {
            System.out.println("The file was not found");
        }

    }

}