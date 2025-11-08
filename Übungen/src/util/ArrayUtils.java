package util;

import java.util.Arrays;

public class ArrayUtils {
	
	public static void validateIntArray(int[] array) {
		if (array.length == 0) {
			throw new IllegalArgumentException("array cannot be empty");
		}
	}
	
	public static String intToString(int[] array) throws IllegalArgumentException  {
		if (array.length == 0) {return ""; }
		
		String returnString = "[";
		
		for (int i = 0; i < array.length - 1; i++) {
			returnString = returnString + array[i] + ", ";
		}
		returnString = returnString + array[array.length - 1] + "]";
		
		return returnString;
	}
	
	public static String numberToString(Number[] array) throws IllegalArgumentException  {
		if (array.length == 0) {return ""; }
		
		String returnString = "[";
		
		for (int i = 0; i < array.length - 1; i++) {
			returnString = returnString + array[i] + ", ";
		}
		returnString = returnString + array[array.length - 1] + "]";
		
		return returnString;
	}
	
	public static String toString(Object[] array) throws IllegalArgumentException  {
		if (array.length == 0) {return ""; }
		
		StringBuilder sb = new StringBuilder("[");
		
		for (int i = 0; i < array.length - 1; i++) {
			sb.append(array[i]);
			sb.append(", "); 
		}
		sb.append(array[array.length - 1]);
		sb.append("]");
		
		return sb.toString();
	}
	
	public static long sumInt(int[] array) throws IllegalArgumentException  {
		validateIntArray(array);
		
		long sum = 0;
	    
		for (int number : array) {
	        sum += number;
	    }
		
	    return sum;
	}
	
	public static long sumIntInRange(int[] array, int startIndex, int lastIndex) throws IllegalArgumentException  {
		validateIntArray(array);
		
		long sum = 0;
	    
		for (int i = startIndex; i <= lastIndex; i++) {
	        sum += array[i];
	    }
	    return sum;
	}
	
	public static long prodInt(int[] array) throws IllegalArgumentException  {
		validateIntArray(array);
		
		long prod = 1;
		
	    for (int number : array) {
	    	prod *= number;
	    }
	    
	    return prod;
	}
	
	public static int minInt(int[] array) throws IllegalArgumentException  {
		validateIntArray(array);
		
		int smallestNumber = Integer.MAX_VALUE;
		
		for (int number : array) {
			if (number < smallestNumber) {
				smallestNumber = number;
			}
		}
		
		return smallestNumber;
	}
	
	public static int maxInt(int[] array) throws IllegalArgumentException {
		validateIntArray(array);
		
		int biggestNumber = Integer.MIN_VALUE;
		
		for (int i = 0; i < array.length; i++) {
			int numAtIndex = array[i];
			
			if (numAtIndex > biggestNumber) {
				biggestNumber = numAtIndex;
			}
		}
		
		return biggestNumber;
	}
	
	public static int[] intRange(int n) {
		int[] array = new int[n];
		
		for (int i = 0; i < n; i++) {
			array[i] = i;
		}
		
		return array;
	}
	
	public static Integer[] integerRange(int n) {
		Integer[] array = new Integer[n];
		
		for (Integer i = 0; i < n; i++) {
			array[i] = i;
		}
		
		return array;
	}
	
	public static Object toPrimitive(Types type, Object[] boxed) {
		return switch(type) {
		case INT -> Arrays.stream((Integer[]) boxed).mapToInt(Integer::intValue).toArray();
		case LONG -> Arrays.stream((Long[])boxed).mapToLong(Long::longValue).toArray();
		case DOUBLE -> Arrays.stream((Double[]) boxed).mapToDouble(Double::doubleValue).toArray();
		default -> throw new IllegalArgumentException("Unexpected value: " + type);
		
		};
	}
}
