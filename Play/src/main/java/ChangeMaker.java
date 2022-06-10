import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChangeMaker {

    private final BigDecimal NICKEL = new BigDecimal(".05");
    private final BigDecimal DIME = new BigDecimal(".10");
    private final BigDecimal QUARTER = new BigDecimal(".25");

        public void makeChange1(BigDecimal balance) {
        BigDecimal[] currencies = new BigDecimal[]{ QUARTER, DIME, NICKEL};
        String[] nameOfChange = new String[]{"Quarters", "Dimes", "Nickels"};
        Map<String, Integer> coinCount = new LinkedHashMap<>();
        BigDecimal amountLeft = balance;
        for (int i = 0; i < currencies.length; i++) {
            while (amountLeft.compareTo(currencies[i]) >= 0) {
                BigDecimal counter = amountLeft.divide(currencies[i], 0, RoundingMode.DOWN);
                int count = counter.intValue();
                coinCount.put(nameOfChange[i], count);
                amountLeft = amountLeft.subtract(currencies[i].multiply(counter));
            }
        } for (Map.Entry<String, Integer> entry : coinCount.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            System.out.println(key + " " + value);
        }
    }
//}
//
//
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
        }
        System.out.println("Thank You, Come Again!\n");
        return finalBalance;

    }
}
