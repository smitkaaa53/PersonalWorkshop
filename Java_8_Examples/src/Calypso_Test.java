import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Calypso_Test {

	public static void main(String[] args) {
		double _notional = 50000000;
		BigDecimal strike = new BigDecimal(350);
		MathContext mc = new MathContext(20, RoundingMode.HALF_UP);
		double quantity = new BigDecimal(_notional).divide(strike, mc).doubleValue();
		//142857.142857
		System.out.println(quantity);
		
		
	}

}
