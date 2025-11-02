package util;

import java.util.Scanner;


public class Input {
	
	public static InputBuilder builder() {
		return new InputBuilder();
	}
	
	public static class InputBuilder {
		private String prompt = "Eingabe: ";
		private String error = 
				"ERROR: \"%s\" ist keine valide Eingabe. Versuchen Sie es bitte erneut. \n";
		private boolean shouldFormat = false;
		private boolean minInclusive = true;
		private boolean maxInclusive = true;
		private double min = 0;
		private double max = Integer.MAX_VALUE;
		
		public InpuBuilder prompt(String prompt) {
			this.prompt = prompt;
			return this;
		}
		
	}
	
	public static final String DEFAULT_PROMPT = "Eingabe: ";
	public static final String DEFAULT_ERROR = 
			"ERROR: \"%s\" ist keine valide Eingabe. Versuchen Sie es bitte erneut. \n";
	public static final boolean DEFAULT_SHOULD_FORMAT = false;
	public static final boolean DEFAULT_MIN_INCLUSIVE = true;
	public static final boolean DEFAULT_MAX_INCLUSIVE = true;
	public static final double DEFAULT_MIN = 0;
	public static final double DEFAULT_MAX = Integer.MAX_VALUE;
	
	private static Scanner scan = new Scanner(System.in); // static used for readability and ease of use
	
	
    /**
     * Returns a validated string from user input.
     * Repeatedly prompts until the input passes validation.
     * @param prompt message displayed before each input attempt (default: "Eingabe: ")
     * @param error message for invalid input, use %s for the input value
     * @param checkInputFunc validation predicate that returns true if input is valid
     * @return the first valid input string
     */
	public static String getString(String prompt, String error, 
			util.Function<String, Boolean> validateInput) {
		
		while (true) {
    		System.out.print(prompt);
    		
    		if (!scan.hasNextLine()) { // XXX: handle CTRL+Z 
    			System.out.println(" ");
    		}
    		
    		String input = scan.nextLine();
    		
    		if (isValidInput(input, validateInput)) {
    			return input;
    		}
    		
    		System.out.printf(error, input);
		}
	}
	
	private static boolean isValidInput(String input, 
			util.Function<String, Boolean> validateInput) {
		if (input == null || input.isEmpty()) {
			return false;
		}
		
		try {
			return validateInput.apply(input);
		} catch (Exception e) {
			return false;
		}
	}
	
	// overload: only validation function
	public static String getString(util.Function<String, Boolean> 
								   validateInput) {
		return getString(DEFAULT_PROMPT, DEFAULT_ERROR, validateInput);
	}
	
	
    /**
     * Returns an entered number once a valid non-empty input is provided.
     * @param prompt
     * @param error
     * @return the validated non-empty String
     */
	public static String getNonEmptyString(String prompt, String error) {
		util.Function<String, Boolean> validateInput = input -> {
			return !input.equals("");
		};
		
		return getString(prompt, error, validateInput);
	}

	// overload: default
	public static String getNonEmptyString() {
	    return getNonEmptyString(DEFAULT_PROMPT, DEFAULT_ERROR);
	}
	
	
    /**
     * Returns an entered number once valid input within the given range is provided.
     * @param type the type of number to parse (Integer, Double, etc.)
     * @param prompt message before each input attempt
     * @param error message after invalid input
     * @param shouldFormat whether to strip non-numeric characters before parsing
     * @param min minimum value (default: 0)
     * @param max maximum value (default: Integer.MAX_VALUE)
     * @param minInclusive whether min is inclusive (default: true)
     * @param maxInclusive whether max is inclusive (default: true)
     * @return the validated number
     */
	public static <T extends Number> T getNumberInRange(
			util.Numbers type, String prompt, String error, 
			Boolean shouldFormat, Double min, Double max, 
			Boolean minInclusive, Boolean maxInclusive) {
		
		util.Function<String, Boolean> validateInput = input -> {
			input = shouldFormat ? input.replaceAll("[^0-9.-]", "") : input;
			Number number = util.Numbers.parseAs(input, type);
			return util.Math.isInRange(number, min, max, minInclusive, maxInclusive);
		};
		
		String input = getString(prompt, error, validateInput);
		return util.Numbers.parseAs(input, type);
	}
	
    // overload: type and range (inclusive)
    public static <T extends Number> T getNumberInRange(
    		util.Numbers type, double min, double max) {
       
    	return getNumberInRange(type, DEFAULT_PROMPT, DEFAULT_ERROR, 
            DEFAULT_SHOULD_FORMAT, min, max, 
            DEFAULT_MIN_INCLUSIVE, DEFAULT_MAX_INCLUSIVE);
    }
    
    // overload: with inclusivity flags
    public static <T extends Number> T getNumberInRange(
    		util.Numbers type, double min, double max, 
    		boolean minInclusive, boolean maxInclusive) {
        
    	return getNumberInRange(type, DEFAULT_PROMPT, DEFAULT_ERROR, 
            DEFAULT_SHOULD_FORMAT, min, max, minInclusive, maxInclusive);
    }
	
    // overload: with prompt
    public static <T extends Number> T getNumberInRange(
    		util.Numbers type, String prompt, double min, double max, 
    		boolean minInclusive, boolean maxInclusive) {
        
    	return getNumberInRange(type, prompt, DEFAULT_ERROR, 
            DEFAULT_SHOULD_FORMAT, min, max, minInclusive, maxInclusive);
    }
    
	
	/**
	 * Returns an entered number once valid input 
	 * matching one of numbersToMatch is provided.
	 * @param prompt printed before each input attempt
	 * @param error printed after each failed input attempt
	 * @param shouldFormat if the inpout should be formatted before parse
	 * @param numbersToMatch array of ints, of which one needs to math the input
	 * @returns the validated number
	 */
	public static <T extends Number> T getMatchingNumber(
			util.Numbers type, String prompt, String error, 
			Boolean shouldFormat, Number[] numbersToMatch) {
		
		util.Function<String, Boolean> validateInput = input -> {
			input = shouldFormat ? input.replaceAll("[^0-9-]", "") : input;
			Number inputNumber = util.Numbers.parse(input);
			
			for (int index = 0; index < numbersToMatch.length; index++) {
				if (inputNumber == numbersToMatch[index]) {
					return true;
				}
			}
			
			return false;
		};
		
		String input = getString(prompt, error, validateInput);
		return util.Numbers.parseAs(input, type);
	}
	
	// overload: only numbersToMatch
	public static <T extends Number> T getMatchingNumber(util.Numbers type, Number[] numbersToMatch) {
		return getMatchingNumber(type, DEFAULT_PROMPT, DEFAULT_ERROR, 
							  DEFAULT_SHOULD_FORMAT, numbersToMatch);
	}
	
	// overload: numbersToMatch and shouldFormat
	public static <T extends Number> T getMatchingNumber(util.Numbers type, Number[] numbersToMatch, boolean shouldFormat) {
		return getMatchingNumber(type, DEFAULT_PROMPT, DEFAULT_ERROR, 
				shouldFormat, numbersToMatch);
	}
	
	
	/**
	 * Returns an entered Number once valid input 
	 * of util.Numbers type is provided.
	 * @param prompt
	 * @param error
	 * @param shouldFormat
	 * @return returns valid number of given type 
	 */
	public static <T extends Number> T getNumber(util.Numbers type, String prompt, String error, Boolean shouldFormat) {
		
		util.Function<String, Boolean> validateInput = input -> {
				input = shouldFormat ? input.replaceAll("[^0-9-]", "") : input;
				Byte.parseByte(input);
				return true;
		};
		
		String input = getString(prompt, error, validateInput);
		return util.Numbers.parseAs(input, type);
	}
	
	// overload: only type
	public static <T extends Number> T getNumber(util.Numbers type) {
		return getNumber(type, DEFAULT_PROMPT, DEFAULT_ERROR, DEFAULT_SHOULD_FORMAT);
	}
	
	// overload: type and prompt
	public static <T extends Number> T getNumber(util.Numbers type, String prompt) {
		return getNumber(type, prompt, DEFAULT_ERROR, DEFAULT_SHOULD_FORMAT);
	}
	
	// overload: type and shouldFormat
	public static <T extends Number> T getNumber(util.Numbers type, boolean shouldFormat) {
		return getNumber(type, DEFAULT_PROMPT, DEFAULT_ERROR, shouldFormat);
	}
}
