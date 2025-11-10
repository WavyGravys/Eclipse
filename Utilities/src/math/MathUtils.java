package math;

import java.math.BigDecimal;

public class MathUtils {
	
	public static <N extends Number> boolean isInRange(
			N value, N minimum, N maximum, 
			boolean minInclusive, boolean maxInclusive) {
		double val = value.doubleValue();
		double min = minimum.doubleValue();
		double max = maximum.doubleValue();
		
		boolean aboveMin = minInclusive ? val >= min : val > min;
		boolean belowMax = maxInclusive ? val <= max : val < max;;
		
		return aboveMin && belowMax;
	}
	
	public static double roundTo(double value, int decimal) {
		BigDecimal valueX100 = new BigDecimal(value * 100);
		return (double) (valueX100.intValue()) / 100;
		
		
	}
	
	// built into java.lang.Math
	/* 
	public static int clamp(int value, int min, int max) {
		if (value < min) {
			return min;
		} else if (value > max) {
			return max;
		}
		return value;
	}
	*/
}
