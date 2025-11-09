package util;

public class ArrayUtils {
	
	public static <T> T[] append(T[] arr, T val) {
		arr[arr.length - 1] = val;
		return arr;
	}
	
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
	    int len = boxed.length;
	    return switch(type) {
	        case STRING -> boxed;
	        case BYTE -> {
	            byte[] arr = new byte[len];
	            for (int i = 0; i < len; i++) arr[i] = (Byte) boxed[i];
	            yield arr;
	        }
	        case SHORT -> {
	            short[] arr = new short[len];
	            for (int i = 0; i < len; i++) arr[i] = (Short) boxed[i];
	            yield arr;
	        }
	        case INT -> {
	            int[] arr = new int[len];
	            for (int i = 0; i < len; i++) arr[i] = (Integer) boxed[i];
	            yield arr;
	        }
	        case LONG -> {
	            long[] arr = new long[len];
	            for (int i = 0; i < len; i++) arr[i] = (Long) boxed[i];
	            yield arr;
	        }
	        case FLOAT -> {
	            float[] arr = new float[len];
	            for (int i = 0; i < len; i++) arr[i] = (Float) boxed[i];
	            yield arr;
	        }
	        case DOUBLE -> {
	            double[] arr = new double[len];
	            for (int i = 0; i < len; i++) arr[i] = (Double) boxed[i];
	            yield arr;
	        }
	        default -> throw new IllegalArgumentException("Unexpected value: " + type);
	    };
	}
}
