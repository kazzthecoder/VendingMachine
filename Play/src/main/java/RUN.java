import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;

public class RUN {
    public static void main(String[] args) {
        ChangeMaker change = new ChangeMaker();
        BigDecimal bal = new BigDecimal("69.69");
        change.makeChange(bal);
    }
}