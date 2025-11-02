package util;


public class Array {
	
	public static void validate(Number[] array) {
	    if (array == null || array.length == 0) {
	    	throw new IllegalArgumentException("Array cannot be null or empty");
	    }
	    
		for (int index = 0; index < array.length; index++) {
			if (array[index] == null) {
				throw new IllegalArgumentException(
						"Array contains null value at index " + index);
			}
		}
	}
	
	public static double toDoubleAndValidate(Number number, int index) {
		double doubleValue = number.doubleValue();
		
		if (Double.isNaN(doubleValue)) {
            throw new ArithmeticException("Array contains NaN at index " + index);
        }
		
        if (Double.isInfinite(doubleValue)) {
            throw new ArithmeticException("Overflow detected at index " + index + 
                ": value exceeds double range");
        }
        
        return doubleValue;
	}
	
	/**
	 * prints the array
	 * @param array input array <p>
	 * @Defaults
	 * <b>array</b> <code>N/A<code> 
	 */
	public static void print(Number[] array) {
		System.out.print("[");
		for (int index = 0; index < array.length - 1; index++) {
			System.out.print(array[index]);
			System.out.print(", ");
		}
		System.out.print(array[array.length - 1] + "]");
	}
	
	/**
	 * returns the array as a String
	 * @param array input array <p>
	 * @Defaults
	 * <b>array</b> <code>N/A<code> 
	 */
	public static String toString(Number[] array) {
		String returnString = "[";
		
		for (int index = 0; index < array.length - 1; index++) {
			returnString = String.join(returnString, array[index].toString(), ", ");
		}
		returnString = String.join(returnString, array[array.length - 1].toString() + "]");
		
		return returnString;
	}
	
	/**
	 * returns sum of array
	 * @param array input array <p>
	 * @Defaults
	 * <b>array</b> <code>N/A<code> 
	 */
	public static double sum(Number[] array) {
		double prod = 1;
		double numAtIndex;
		
		for (int index = 0; index < array.length; index++) {
			numAtIndex = array[index].doubleValue();
			
			prod += numAtIndex;
		}
		return prod;
	}
	
	/**
	 * returns product of array
	 * @param array input array <p>
	 * @Defaults
	 * <b>array</b> <code>N/A<code> 
	 */
	public static double prod(Number[] array) {
		double prod = 1;
		double numAtIndex;
		
		for (int index = 0; index < array.length; index++) {
			numAtIndex = array[index].doubleValue();
			
			prod *= numAtIndex;
		}
		return prod;
	}
	
	/**
	 * returns smallest number of array
	 * @param array input array <p>
	 * @Defaults
	 * <b>array</b> <code>N/A<code> 
	 */
	public static double min(Number[] array) {
		double smallestNumber = Double.POSITIVE_INFINITY;
		double numAtIndex;
		
		for (int index = 0; index < array.length; index++) {
			numAtIndex = array[index].doubleValue();
			
			if (numAtIndex < smallestNumber) {
				smallestNumber = numAtIndex;
			}
		}
		return smallestNumber;
	}
	
	/**
	 * returns highest number of array
	 * @param array input array <p>
	 * @Defaults
	 * <b>array</b> <code>N/A<code> 
	 */
	public static double max(Number[] array) {
		validate(array);
		
		double biggestNumber = Double.NEGATIVE_INFINITY;
		
		for (int index = 0; index < array.length; index++) {
			double numAtIndex = toDoubleAndValidate(array[index], index);
			
			if (numAtIndex > biggestNumber) {
				biggestNumber = numAtIndex;
			}
		}
		return biggestNumber;
	}
}
