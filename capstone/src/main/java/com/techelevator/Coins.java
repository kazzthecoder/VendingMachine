package com.techelevator;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;

public class Coins {
    private static final BigDecimal NICKEL = new BigDecimal(".05");
    private static final BigDecimal DIME = new BigDecimal(".10");
    private static final BigDecimal QUARTER = new BigDecimal(".25");


    Coins() {
    }

    public BigDecimal makeChange(BigDecimal balance) {
        BigDecimal[] currencies = new BigDecimal[]{QUARTER, DIME, NICKEL};
        String[] nameOfChange = new String[]{"Quarters", "Dimes", "Nickels"};

        Map<String, BigDecimal> coinCount = new LinkedHashMap<>();
        BigDecimal amountLeft = balance;
        for (int i = 0; i < currencies.length; i++) {
            while (amountLeft.compareTo(currencies[i]) >= 0) {
                BigDecimal counter = amountLeft.divide(currencies[i], 0, RoundingMode.DOWN);
                coinCount.put(nameOfChange[i], counter);
                amountLeft = amountLeft.subtract(currencies[i].multiply(counter));
            }

        }
        System.out.printf("Please Take Your Change: %.2f\n", balance);
        BigDecimal finalBalance = new BigDecimal("0.0");
        for (Map.Entry<String, BigDecimal> entry : coinCount.entrySet()) {
            String key = entry.getKey();
            BigDecimal value = entry.getValue();
            BigDecimal changeTracker;
            switch (key) {
                case "Quarters":
                    changeTracker = QUARTER.multiply(value);
                    finalBalance = finalBalance.add(changeTracker);
                case "Dimes":
                    changeTracker = DIME.multiply(value);
                    finalBalance = finalBalance.add(changeTracker);
                case "Nickels":
                    changeTracker = NICKEL.multiply(value);
                    finalBalance = finalBalance.add(changeTracker);
            }
            System.out.println(key + " : " + value);
            return finalBalance = balance.subtract(finalBalance);
        }
        System.out.println("Thank You, Come Again!\n");
        return finalBalance;
    }



}

