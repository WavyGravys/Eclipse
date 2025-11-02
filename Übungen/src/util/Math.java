package util;


public class Math {
	
	public static boolean isInRange(
			Number value, Number minimum, Number maximum, 
			boolean minBoundType, boolean maxBoundType) {
		double val = value.doubleValue();
		double min = value.doubleValue();
		double max = value.doubleValue();
		
		boolean aboveMin = minBoundType ? val >= min : val > min;
		boolean belowMax = maxBoundType ? val <= max : val < max;;
		
		return aboveMin && belowMax;
	}
}
