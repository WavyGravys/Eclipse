package util;


public class Math {
	
	public static boolean isInRange(
			Number value, Number minimum, Number maximum, 
			boolean minInclusive, boolean maxInclusive) {
		double val = value.doubleValue();
		double min = minimum.doubleValue();
		double max = maximum.doubleValue();
		
		boolean aboveMin = minInclusive ? val >= min : val > min;
		boolean belowMax = maxInclusive ? val <= max : val < max;;
		
		return aboveMin && belowMax;
	}
}
