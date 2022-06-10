import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChangeMaker {

    private final BigDecimal NICKEL = new BigDecimal(".05");
    private final BigDecimal DIME = new BigDecimal(".10");
    private final BigDecimal QUARTER = new BigDecimal(".25");

    public void makeChange(BigDecimal balance) {
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
}


//    makeChange(amountLeft);
//        for (Map.Entry<String, Integer> entry : coinCount.entrySet()) {
//        String key = entry.getKey();
//        int value = entry.getValue();
//        System.out.println(key + " " + value);