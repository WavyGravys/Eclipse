package util;


public class Array {
	
	public static void printInt(int[] array) {
		System.out.print("[");
		for (int index=0; index<array.length-1; index++) {
			System.out.print(array[index]);
			System.out.print(", ");
		}
		System.out.print(array[array.length-1]+"]");
	}
	
	
	/**
	 * returns sum of array
	 * @param array input array <p>
	 * @Defaults
	 * <b>array</b> <code>N/A<code> 
	 */
	public static int sum(int[] array) {
		int sum = 0;
		for (int index=0; index<array.length; index++) {
			sum += array[index];
		}
		return sum;
	}
	
	
	/**
	 * returns product of array
	 * @param array input array <p>
	 * @Defaults
	 * <b>array</b> <code>N/A<code> 
	 */
	public static int prod(int[] array) {
		int prod = 1;
		for (int index=0; index<array.length; index++) {
			prod *= array[index];
		}
		return prod;
	}
	
	
	/**
	 * returns smallest int of array
	 * @param array input array <p>
	 * @Defaults
	 * <b>array</b> <code>N/A<code> 
	 */
	public static int min(int[] array) {
		int smallestNumber = Integer.MAX_VALUE;
		for (int index=0; index<array.length; index++) {
			if (array[index] < smallestNumber)
				smallestNumber = array[index];
		}
		return smallestNumber;
	}
	
	
	/**
	 * returns highest int of array
	 * @param array input array <p>
	 * @Defaults
	 * <b>array</b> <code>N/A<code> 
	 */
	public static int max(int[] array) {
		int biggestNumber = 0;
		for (int index=0; index<array.length; index++) {
			if (array[index] > biggestNumber)
				biggestNumber = array[index];
		}
		return biggestNumber;
	}
}
