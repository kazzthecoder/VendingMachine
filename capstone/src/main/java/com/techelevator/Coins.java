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

    public BigDecimal getNICKEL() {
        return NICKEL;
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
        for (Map.Entry<String, BigDecimal> entry : coinCount.entrySet()) {
            String key = entry.getKey();
            BigDecimal value = entry.getValue();
            System.out.println(key + " : " + value);
        }
        System.out.println("Thank You, Come Again!\n");
        return amountLeft;
    }
}
