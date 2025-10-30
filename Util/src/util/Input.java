package util;

import java.util.Scanner;


public class Input {
	
	private static Scanner scan = new Scanner(System.in); // static used for readability and ease of use
	
	
	/**
	 * returns entered string once one is input, that is valid according to the function tryInputFunc.
	 * @param preInputMessage printed before each input attempt
	 * @param retryMessage printed after each failed input attempt
	 * @param checkInputFunc custom (boolean) function which decides if the input attempt is successful
	 * @param extraParameters (Object) array with extra parameters for tryInputFunc <p>
	 * @Defaults
	 * <b>preInputMessage</b> <code>"Eingabe: "<code> <br>
	 * <b>retryMessage</b> <code>"ERROR: \"%s\" ist keine valide Eingabe. Versuchen Sie es bitte erneut. \n"<code> <br>
	 * <b>checkInputFunc</b> N/A <br>
	 * <b>extraParameters</b> N/A
	 */
	public static String getString(String preInputMessage, String retryMessage, Function checkInputFunc) {
		preInputMessage = preInputMessage == null ? "Eingabe: " : preInputMessage;
		retryMessage = retryMessage == null ? "ERROR: \"%s\" ist keine valide Eingabe. Versuchen Sie es bitte erneut. \n" : retryMessage;
		
		String input;
    	while (true) {
    		System.out.print(preInputMessage);
    		if (!scan.hasNextLine()) // to handle the user pressing "ctrl+z", while scan is waiting for input. (doesnt work at the start)
    			System.out.println(" ");
    		input = scan.nextLine();
    		// if the checkInputFunc throws any exception or returns false we try again
    		try {
				boolean isCorrect = checkInputFunc.function(input);
				
				if (isCorrect) 
					return input;
				System.out.printf(retryMessage, input);
				
    		} catch (Exception e) {
				System.out.printf(retryMessage, input);
			}
		}
	}
	
	
	/**
	 * returns entered string once one is input, that is non-empty.
	 * @param preInputMessage printed before each input attempt
	 * @param retryMessage printed after each failed input attempt <p>
	 * @Defaults
	 * <b>preInputMessage</b> <code>"Eingabe: "</code> <br>
	 * <b>retryMessage</b> <code>"ERROR: %s is not a valid input. Please try again \n"</code>
	 */
	public static String getNonEmptyString(String preInputMessage, String retryMessage) {
		Function checkInput = new Function() {
			public boolean function(String input) {
				return input.equals("");
			}
		};
		
		return getString(preInputMessage, retryMessage, checkInput);
	}
	
	
	/**
	 * returns string formatted via the formatString if shouldFormat is true.
	 * @param shouldFormat if the inpout should be formatted 
	 * @param input string to be formatted 
	 * @param formatString string to be formatted with <p>
	 * @Defaults
	 * <b>shouldFormat</b> <code>false</code> <br>
	 * <b>inputString</b> N/A <br>
	 * <b>formatString</b> <code>"[^0-9-]"</code>
	 */
	public static String format(Boolean shouldFormat, String input, String formatString) {
		shouldFormat = shouldFormat == null ? false : shouldFormat;
		formatString = formatString == null ? "[^0-9-]" : formatString;
		
		if (shouldFormat)
			return input.replaceAll(formatString, "");
		return input;
	}
	
	
	/**
	 * returns entered int, once one is input, that is within the given range.
	 * @param preInputMessage printed before each input attempt
	 * @param retryMessage printed after each failed input attempt
	 * @param shouldFormat if the inpout should be formatted before parse
	 * @param min the minimum
	 * @param max the maximum
	 * @param minInclusive if min should be allowed as within range
	 * @param maxInclusive if max should be allowed as within range <p>
	 * @Defaults
	 * <b>preInputMessage</b> <code>"Eingabe: "<code> <br>
	 * <b>retryMessage</b> <code>"ERROR: %s is not a valid input. Please try again \n"<code> <br>
	 * <b>shouldFormat</b> <code>true</code> <br>
	 * <b>min</b> <code>0</code> <br>
	 * <b>max</b> <code>Integer.MAX_VALUE</code> <br>
	 * <b>minInclusive</b> <code>true</code> <br>
	 * <b>maxInclusive</b> <code>true</code>
	 */
	public static int getIntInRange(String preInputMessage, String retryMessage, Boolean shouldFormat,
			Double min, Double max, Boolean minInclusive, Boolean maxInclusive) {
		
		final boolean _minInclusive = minInclusive == null ? true : minInclusive;
		final boolean _maxInclusive = maxInclusive == null ? true : maxInclusive;
		final double _min = min == null ? 0 : min;
		final double _max = max == null ? Integer.MAX_VALUE : max;
		
		Function checkInput = new Function() {
			public boolean function(String input) {
				input = format(shouldFormat, input, null);
				int inputInt = Integer.parseInt(input);
				
				boolean aboveMin;
				boolean belowMax;
				if (_minInclusive && inputInt >= _min)
					aboveMin = true;
				else if (!_minInclusive && inputInt > _min)
					aboveMin = true;
				else
					aboveMin = false;
				if (_maxInclusive && inputInt <= _max) 
					belowMax = true;
				else if (!_maxInclusive && inputInt < _max)
					belowMax = true;
				else
					belowMax = false;
				return aboveMin && belowMax;
			}
		};
		
		String input = getString(preInputMessage, retryMessage, checkInput);
		return Integer.parseInt(input);
	}
	
	
	/**
	 * returns entered float, once one is input, that is within the given range.
	 * @param preInputMessage printed before each input attempt
	 * @param retryMessage printed after each failed input attempt
	 * @param shouldFormat if the inpout should be formatted before parse
	 * @param min the minimum
	 * @param max the maximum
	 * @param minInclusive if min should be allowed as within range
	 * @param maxInclusive if max should be allowed as within range <p>
	 * @Defaults
	 * <b>preInputMessage</b> <code>"Eingabe: "<code> <br>
	 * <b>retryMessage</b> <code>"ERROR: %s is not a valid input. Please try again \n"<code> <br>
	 * <b>shouldFormat</b> <code>true</code> <br>
	 * <b>min</b> <code>0</code> <br>
	 * <b>max</b> <code>1000000d</code> <br>
	 * <b>minInclusive</b> <code>true</code> <br>
	 * <b>maxInclusive</b> <code>true</code>
	 */
	public static float getFloatInRange(String preInputMessage, String retryMessage, Boolean shouldFormat,
			Double min, Double max, Boolean minInclusive, Boolean maxInclusive) {
		
		final boolean _minInclusive = minInclusive == null ? true : minInclusive;
		final boolean _maxInclusive = maxInclusive == null ? true : maxInclusive;
		final double _min = min == null ? 0 : min;
		final double _max = max == null ? 1000000d : max;
		
		Function checkInput = new Function() {
			public boolean function(String input) {
				input = format(shouldFormat, input, "[^0-9.-]");
				float inputFloat = Float.parseFloat(input);
				if (Double.isInfinite(inputFloat)) // over/under-flow check
					return false;
				
				boolean aboveMin;
				boolean belowMax;
				if (_minInclusive && inputFloat >= _min)
					aboveMin = true;
				else if (!_minInclusive && inputFloat > _min)
					aboveMin = true;
				else
					aboveMin = false;
				if (_maxInclusive && inputFloat <= _max) 
					belowMax = true;
				else if (!_maxInclusive && inputFloat < _max)
					belowMax = true;
				else
					belowMax = false;
				return aboveMin && belowMax;
			}
		};
		
		String input = getString(preInputMessage, retryMessage, checkInput);
		return Float.parseFloat(input);
	}
	
	
	/**
	 * returns entered double, once one is input, that is within the given range.
	 * @param preInputMessage printed before each input attempt
	 * @param retryMessage printed after each failed input attempt
	 * @param shouldFormat if the inpout should be formatted before parse
	 * @param min the minimum
	 * @param max the maximum
	 * @param minInclusive if min should be allowed as within range
	 * @param maxInclusive if max should be allowed as within range <p>
	 * @Defaults
	 * <b>preInputMessage</b> <code>"Eingabe: "<code> <br>
	 * <b>retryMessage</b> <code>"ERROR: %s is not a valid input. Please try again \n"<code> <br>
	 * <b>shouldFormat</b> <code>true</code> <br>
	 * <b>min</b> <code>0</code> <br>
	 * <b>max</b> <code>Double.MAX_VALUE</code> <br>
	 * <b>minInclusive</b> <code>true</code> <br>
	 * <b>maxInclusive</b> <code>true</code>
	 */
	public static double getDoubleInRange(String preInputMessage, String retryMessage, Boolean shouldFormat,
			Double min, Double max, Boolean minInclusive, Boolean maxInclusive) {
		
		final boolean _minInclusive = minInclusive == null ? true : minInclusive;
		final boolean _maxInclusive = maxInclusive == null ? true : maxInclusive;
		final double _min = min == null ? 0 : min;
		final double _max = max == null ? Double.MAX_VALUE : max;
		
		Function checkInput = new Function() {
			public boolean function(String input) {
				input = format(shouldFormat, input, "[^0-9.-]");
				double inputDouble = Double.parseDouble(input);
				if (Double.isInfinite(inputDouble)) // over/under-flow check
					return false;
				
				boolean aboveMin;
				boolean belowMax;
				if (_minInclusive && inputDouble >= _min)
					aboveMin = true;
				else if (!_minInclusive && inputDouble > _min)
					aboveMin = true;
				else
					aboveMin = false;
				if (_maxInclusive && inputDouble <= _max) 
					belowMax = true;
				else if (!_maxInclusive && inputDouble < _max)
					belowMax = true;
				else
					belowMax = false;
				return aboveMin && belowMax;
			}
		};
		
		String input = getString(preInputMessage, retryMessage, checkInput);
		return Double.parseDouble(input);
	}
	
	
	/**
	 * returns entered int, once one is input, that matches at least in in intsToMatch. 
	 * @param preInputMessage printed before each input attempt
	 * @param retryMessage printed after each failed input attempt
	 * @param shouldFormat if the inpout should be formatted before parse
	 * @param intsToMatch array of ints, of which one needs to math the input <p>
	 * @Defaults
	 * <b>preInputMessage</b> <code>"Eingabe: "<code> <br>
	 * <b>retryMessage</b> <code>"ERROR: %s is not a valid input. Please try again \n"<code> <br>
	 * <b>shouldFormat</b> <code>true</code> <br>
	 * <b>intsToMatch</b> N/A
	 */
	public static int getMatchingInt(String preInputMessage, String retryMessage, Boolean shouldFormat, int[] intsToMatch) {
		Function checkInput = new Function() {
			public boolean function(String input) {
				input = format(shouldFormat, input, null);
				int inputInt = Integer.parseInt(input);
				for (int index=0; index<intsToMatch.length; index++)
					if (inputInt == intsToMatch[index])
						return true;
				return false;
			}
		};
		
		String input = getString(preInputMessage, retryMessage, checkInput);
		return Integer.parseInt(input);
	}
	
	
	/**
	 * returns entered byte, once one is input, that is valid. 
	 * @param preInputMessage printed before each input attempt
	 * @param retryMessage printed after each failed input attempt
	 * @param shouldFormat if the inpout should be formatted before parse <p>
	 * @Defaults
	 * <b>preInputMessage</b> <code>"Eingabe: "<code> <br>
	 * <b>retryMessage</b> <code>"ERROR: %s is not a valid input. Please try again \n"<code> <br>
	 * <b>shouldFormat</b> <code>true</code>
	 */
	public static byte getByte(String preInputMessage, String retryMessage, Boolean shouldFormat) {
		Function checkInput = new Function() {
			public boolean function(String input) {
				input = format(shouldFormat, input, null);
				Byte.parseByte(input);
				return true;
			}
		};
		
		String input = getString(preInputMessage, retryMessage, checkInput);
		return Byte.parseByte(input);
	}
	
	
	/**
	 * returns entered int, once one is input, that is valid. 
	 * @param preInputMessage printed before each input attempt
	 * @param retryMessage printed after each failed input attempt
	 * @param shouldFormat if the inpout should be formatted before parse <p>
	 * @Defaults
	 * <b>preInputMessage</b> <code>"Eingabe: "<code> <br>
	 * <b>retryMessage</b> <code>"ERROR: %s is not a valid input. Please try again \n"<code> <br>
	 * <b>shouldFormat</b> <code>true</code>
	 */
	public static int getInt(String preInputMessage, String retryMessage, Boolean shouldFormat) {
		Function checkInput = new Function() {
			public boolean function(String input) {
				input = format(shouldFormat, input, null);
				Integer.parseInt(input);
				return true;
			}
		};
		
		String input = getString(preInputMessage, retryMessage, checkInput);
		return Integer.parseInt(input);
	}
	
	
	/**
	 * returns entered long, once one is input, that is valid. 
	 * @param preInputMessage printed before each input attempt
	 * @param retryMessage printed after each failed input attempt
	 * @param shouldFormat if the inpout should be formatted before parse <p>
	 * @Defaults
	 * <b>preInputMessage</b> <code>"Eingabe: "<code> <br>
	 * <b>retryMessage</b> <code>"ERROR: %s is not a valid input. Please try again \n"<code> <br>
	 * <b>shouldFormat</b> <code>true</code>
	 */
	public static long getLong(String preInputMessage, String retryMessage, Boolean shouldFormat) {
		Function checkInput = new Function() {
			public boolean function(String input) {
				input = format(shouldFormat, input, null);
				Long.parseLong(input);
				return true;
			}
		};
		
		String input = getString(preInputMessage, retryMessage, checkInput);
		return Long.parseLong(input);
	}
	
	
	/**
	 * returns entered float, once one is input, that is valid. 
	 * @param preInputMessage printed before each input attempt
	 * @param retryMessage printed after each failed input attempt
	 * @param shouldFormat if the inpout should be formatted before parse <p>
	 * @Defaults
	 * <b>preInputMessage</b> <code>"Eingabe: "<code> <br>
	 * <b>retryMessage</b> <code>"ERROR: %s is not a valid input. Please try again \n"<code> <br>
	 * <b>shouldFormat</b> <code>true</code>
	 */
	public static float getFloat(String preInputMessage, String retryMessage, Boolean shouldFormat) {
		Function checkInput = new Function() {
			public boolean function(String input) {
				input = format(shouldFormat, input, "[^0-9.-]");
				float inputFloat = Float.parseFloat(input);
				if (Double.isInfinite(inputFloat)) // over/under-flow check
					return false;
				return true;
			}
		};
		
		String input = getString(preInputMessage, retryMessage, checkInput);
		return Float.parseFloat(input);
	}
	
	
	/**
	 * returns entered double, once one is input, that is valid. 
	 * @param preInputMessage printed before each input attempt
	 * @param retryMessage printed after each failed input attempt
	 * @param shouldFormat if the inpout should be formatted before parse <p>
	 * @Defaults
	 * <b>preInputMessage</b> <code>"Eingabe: "<code> <br>
	 * <b>retryMessage</b> <code>"ERROR: %s is not a valid input. Please try again \n"<code> <br>
	 * <b>shouldFormat</b> <code>true</code>
	 */
	public static double getDouble(String preInputMessage, String retryMessage, Boolean shouldFormat) {
		Function checkInput = new Function() {
			public boolean function(String input) {
				input = format(shouldFormat, input, "[^0-9.-]");
				double inputDouble = Double.parseDouble(input);
				if (Double.isInfinite(inputDouble)) // over/under-flow check
					return false;
				return true;
			}
		};
		
		String input = getString(preInputMessage, retryMessage, checkInput);
		return Double.parseDouble(input);
	}
}