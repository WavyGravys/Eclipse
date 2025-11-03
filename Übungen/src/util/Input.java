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
		private Number min = 0;
		private Number max = Integer.MAX_VALUE;
		private boolean minInclusive = true;
		private boolean maxInclusive = true;
		private Number[] numbersToMatch = new Number[] {0, 1};
		private util.Function <String, Boolean> validateFunc = null;
		
		public InputBuilder validateFunc(
				util.Function <String, Boolean> validateFunc) {
			this.validateFunc = validateFunc;
			return this;
		}
		
		public InputBuilder prompt(String prompt) {
			this.prompt = prompt;
			return this;
		}
		
		public InputBuilder error(String error) {
			this.error = error;
			return this;
		}
		
		public InputBuilder shouldFormat(boolean shouldFormat) {
			this.shouldFormat = shouldFormat;
			return this;
		}
		
		public InputBuilder	range(Number min, Number max) {
			this.min = min;
			this.max = max;
			this.validateFunc = validateInRange;
			return this;
		}
		
		public InputBuilder inclusivity(boolean minInclusive, boolean maxInclusive) {
			this.minInclusive = minInclusive;
			this.maxInclusive = maxInclusive;
			this.validateFunc = validateInRange;
			return this;
		}
		
		public InputBuilder numbersToMatch(Number[] numbersToMatch) {
			this.numbersToMatch = numbersToMatch;
			this.validateFunc = validateMatching;
			return this;
		}
		
		
		private static class Result {
		    private String input;
		    
		    public static Result ofInput(String value) {
		        Result r = new Result();
		        r.input = value;
		        return r;
		    }
		    
		    public String asString() { return input; }
		    public <T extends Number> T asNumber(util.Numbers type) { 
		    	return util.Numbers.parseAs(input, type); 
	    	}
		}
		
		private Result getInput() {
			Scanner scan = new Scanner(System.in);
			while (true) {
	    		System.out.print(prompt);
	    		
	    		while (!scan.hasNext()) {}
	    		
	    		/*
	    		if (!scan.hasNextLine()) { // XXX: handle CTRL+Z 
	      			System.out.println(" ");
	    			scan.close();
	    			scan = new Scanner(System.in); 
	    		}
	    		*/
	    		
	    		String input = scan.nextLine();
	    		String formattedInput = shouldFormat ? input.replaceAll("[^0-9.-]", "") : input;
	    		
	    		if (isValidInput(formattedInput, validateFunc)) {
	    			scan.close();
    				return Result.ofInput(formattedInput);
	    			
	    		}
	    		System.out.printf(error, formattedInput);
			}
		}
		
		public String getString() {
			return this.getInput().asString();
		}
		
		public <T extends Number> T getNumber(util.Numbers type) {
			if (this.validateFunc == null) {
				this.validateFunc = validateNumber;
			}
			if (type == null) {
				type = util.Numbers.INT;
			}
			
			return this.getInput().asNumber(type);
		}
		
		private static  boolean isValidInput(String input, 
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
		
		
		public InputBuilder withRangeValidation() {
		    return this.validateFunc(validateInRange);
		}
		
		public util.Function<String, Boolean> validateInRange = input -> {
			Number number = util.Numbers.parse(input);
			return util.Math.isInRange(number, min, max, minInclusive, maxInclusive);
		};
		
		
		public InputBuilder withNumberValidation() {
		    return this.validateFunc(validateNumber);
		}
		
		public util.Function<String, Boolean> validateNumber = input -> {
			util.Numbers.parse(input); // check if parsable
			return true;
		};
		
		
		public InputBuilder withMatchingValidation() {
		    return this.validateFunc(validateMatching);
		}
		
		public util.Function<String, Boolean> validateMatching = input -> {
			Number inputNumber = util.Numbers.parse(input);
			
			for (Number numberToMatch : numbersToMatch) {
				if (inputNumber.getClass() == numberToMatch.getClass()) {
					if (inputNumber.equals(numberToMatch)) {
						return true;
					}
				} else if (inputNumber.doubleValue() == numberToMatch.doubleValue()) {
					return true;
				}
			}
			
			return false;
		};
	}
	
	// Convenience methods:
	
	public static String getString(String prompt) {
		return builder()
				.prompt(prompt)
				.getString();
	}
	
	public static <T extends Number> T getNumber(util.Numbers type, String prompt) {
		return builder()
				.prompt(prompt)
				.getNumber(type);
	}
	
	public static <T extends Number> T getNumberInRange(
			util.Numbers type, String prompt,
			boolean shouldFormat, double min, double max, 
			boolean minInclusive, boolean maxInclusive) {
		
	    return builder()
	    		.prompt(prompt)
	    		.shouldFormat(shouldFormat)
	    		.range(min, max)
	    		.inclusivity(minInclusive, maxInclusive)
	    		.getNumber(type);
	}
	
	public static <T extends Number> T getNumberInRange(
			util.Numbers type, String prompt, double min, double max) {
		
	    return builder()
	    		.prompt(prompt)
	    		.range(min, max)
	    		.getNumber(type);
	}
	
	public static <T extends Number> T getMatchingNumber(
			util.Numbers type, String prompt, Number[] numbersToMatch) {
		
		return builder()
				.prompt(prompt)
				.numbersToMatch(numbersToMatch)
				.getNumber(type);
	}
}
