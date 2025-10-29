package main;

import java.util.Scanner;


public class OverUnderFlowCheck {
	
	static final Scanner scan = new Scanner(System.in);
	
	private static enum MainTypes {
		BYTE,
		INT,
		LONG,
		FLOAT,
		DOUBLE
	}
	
	
	/**
	 * @param input must be one of the valid types as String
	 * @param typeToCheck type for which to validate over/under-flow
	 * @return true if the number would over/under-flow
	 */
	public static boolean isOverOrUnderFlow(String input, MainTypes typeToCheck) {
		//boolean isOverflow;
		//boolean isUnderflow;
		int inputLength = input.length();
		byte[] numberArray = new byte[inputLength];
		
		switch (typeToCheck) {
		case MainTypes.BYTE:
		case MainTypes.INT:
		case MainTypes.LONG:
			// turn string into array of bytes
			for (int index=0; index<inputLength; index++)
				numberArray[index] = Byte.parseByte(Character.toString(input.charAt(index)));
		default:
			break;
		}
		
		switch (typeToCheck) {
		case MainTypes.BYTE:
			break;
		case MainTypes.INT:
			if (inputLength > 10)
				return true;
			
			byte[] maxIntArray = new byte[] {2,1,4,7,4,8,3,6,4,7};
			byte[] minIntArray = new byte[] {2,1,4,7,4,8,3,6,4,8};
			boolean equalUntilNow = true;
			for (int index=0; index<input.length(); index++) {
				if (numberArray[index] > maxIntArray[index])
					return true;
				else if (numberArray[index] == maxIntArray[index])
					equalUntilNow = false;
				else // if (numberArray[index] < maxIntArray[index])
					if (!equalUntilNow)
						break;
					else
						return true;
						
			}
		case MainTypes.LONG:
			break;
		case MainTypes.FLOAT:
			return Float.isInfinite(Float.parseFloat(input));
		case MainTypes.DOUBLE:
			return Double.isInfinite(Double.parseDouble(input));
		}
		
		return false;
	}
	
	
	public static void main(String[] args) { // testing
		//final String errorMessage = "ERROR: %s is not a valid input. Please try again. \n";
		String input = scan.nextLine();
		input = util.Input.format(true, input, "[^0-9.-]");
		System.out.printf("%b - overflow? - ", isOverOrUnderFlow(input, MainTypes.INT));

	}
}
