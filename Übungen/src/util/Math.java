package util;


public class Math {
	
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
}
