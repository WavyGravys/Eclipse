package util;


public class Array {
	
	public static void validateIntArray(int[] array) {
		if (array.length == 0) {
			throw new IllegalArgumentException("array cannot be empty");
		}
	}
	
	public static void print(int[] array) throws IllegalArgumentException {
		if (array.length == 0) {return;}
		
		System.out.print("[");
		
		for (int index = 0; index < array.length - 1; index++) {
			System.out.print(array[index]);
			System.out.print(", ");
		}
		
		System.out.print(array[array.length - 1] + "]");
	}
	
	public static String intToString(int[] array) throws IllegalArgumentException  {
		if (array.length == 0) {return ""; }
		
		String returnString = "[";
		
		for (int index = 0; index < array.length - 1; index++) {
			returnString = returnString + array[index] + ", ";
		}
		returnString = returnString + array[array.length - 1] + "]";
		
		return returnString;
	}
	
	public static String numberToString(Number[] array) throws IllegalArgumentException  {
		if (array.length == 0) {return ""; }
		
		String returnString = "[";
		
		for (int index = 0; index < array.length - 1; index++) {
			returnString = returnString + array[index] + ", ";
		}
		returnString = returnString + array[array.length - 1] + "]";
		
		return returnString;
	}
	
	public static long sumInt(int[] array) throws IllegalArgumentException  {
		validateIntArray(array);
		
		long sum = 0;
	    
		for (int number : array) {
	        sum += number;
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
		
		for (int index = 0; index < array.length; index++) {
			int numAtIndex = array[index];
			
			if (numAtIndex > biggestNumber) {
				biggestNumber = numAtIndex;
			}
		}
		
		return biggestNumber;
	}
	
	public static int[] intRange(int n) {
		int[] array = new int[n];
		
		for (int index = 0; index < n; index++) {
			array[index] = index;
		}
		
		return array;
	}
	
	public static Integer[] integerRange(int n) {
		Integer[] array = new Integer[n];
		
		for (Integer index = 0; index < n; index++) {
			array[index] = index;
		}
		
		return array;
	}
}
