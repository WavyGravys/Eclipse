package util;

import java.util.Scanner;
import java.util.ArrayList;


public class Input {
	
	private static final Scanner scan = new Scanner(System.in);
	
	
	public static boolean checkForAndClearInput() {
        try {
        	if (System.in.available() > 0) {
        		System.in.read();
        		return true;
        	}
        	return false;
        } catch (Exception e) {
	        return true;
        }
	}
	
	public static boolean isSkipping(boolean shouldPrint) {
		if (Input.checkForAndClearInput()) {
            if (shouldPrint) {
            	System.out.print("Skipped!");
            }
            
            General.clearScannerCache();
            return true;
    	}
		return false;
	}
	
	
	public static InputBuilder builder() {
		return new InputBuilder();
	}
	
	public static class InputBuilder {
		/* -- general variables -- */
		private Numbers numType = Numbers.INT;
		private String prompt = "Eingabe: ";
		public static String error = "ERROR: %s ist keine valide Eingabe. "
								   + "Versuchen Sie es bitte erneut. \n";
		private boolean shouldFormat = false;
		private Function <String, Boolean> validateFunc = null;
		/* -- validate in range variables -- */
		private Number min = 0;
		private Number max = Integer.MAX_VALUE;
		private boolean minInclusive = true;
		private boolean maxInclusive = true;
		/* -- validate matching variables -- */
		private Number[] numbersToMatch = new Number[] {0, 1};
		/* -- getNumbers variables -- */
		private String exitInstruction = "\nDrücken Sie ohne Eingabe Enter, "
									   + "um Ihre Auswahl zu bestätigen"; 
		private String[] exitConditions = new String[] {""}; // "" means entering an empty line aka pressing enter
		
	    /* -- variable setters -- */
		public InputBuilder numType(Numbers numType) {
			this.numType = numType;
			this.validateFunc = validateInRange;
			return this;
		}
		
		public InputBuilder prompt(String prompt) {
			this.prompt = prompt;
			return this;
		}
		
		public InputBuilder error(String error) {
			InputBuilder.error = error;
			return this;
		}
		
		public InputBuilder shouldFormat(boolean shouldFormat) {
			this.shouldFormat = shouldFormat;
			return this;
		}
		
		public InputBuilder validateFunc(
				Function <String, Boolean> validateFunc) {
			this.validateFunc = validateFunc;
			return this;
		}
		
		public InputBuilder range(Number min, Number max) {
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
		
		public InputBuilder exitConditions(String... exitConditions) {
			this.exitConditions = exitConditions;
			return this;
		}
		
	    public InputBuilder exitInstruction(String instruction) {
	        this.exitInstruction = instruction;
	        return this;
	    }
	    
	    
	    /**
	     * class to handle both Strings and Numbers 
	     * as the result of getting Input
	     */
		private static class Result {
		    private String input;
		    
		    public static Result ofInput(String value) {
		        Result r = new Result();
		        r.input = value;
		        return r;
		    }
		    
		    public String asString() { return input; }
		    public <T extends Number> T asNumber(Numbers numType) { 
		    	return Numbers.parseAs(input, numType); 
	    	}
		}
		
		/* -- input setters -- */
	    /**
	     * Gets an Input from the user, that is validated.
	     * returns 0 if exit condition is met
	     * @implNote needs to be cast to numType[]
	     * @return array of all entered numbers as an Object
	     */
		private Result getInput() {
			
			while (true) {
	    		System.out.print(prompt);
	    		
	            if (!scan.hasNextLine()) {
	                System.out.print("\nInput wurde durch CTR+Z gestoppt. \n");
	                General.closeProgram();
	            }
	            
	    		String input = scan.nextLine();
	    		
	    		if (shouldExit(input)) {
	    			return Result.ofInput("0");
	    		}
	    		
	    		String formattedInput = shouldFormat ? input.replaceAll("[^0-9.-]", "") : input;
	    		
	    		if (isValidInput(formattedInput, validateFunc)) {
    				return Result.ofInput(formattedInput);
	    		}
	    		
	    		printError(input);
			}
		}
		
		private void printError(String input) {
			input = util.General.clampString(input, 30);
			
			System.out.printf("[" + error, input);
		}
		
		public String getString() {
			return this.getInput().asString();
		}
		
		public <T extends Number> T getNumber() {
			if (this.validateFunc == null) {
				this.validateFunc = validateNumber;
			}
			if (numType == null) {
				numType = Numbers.INT;
			}
			
			return this.getInput().asNumber(numType);
		}
		
	    /**
	     * Gets numbers from user input, that are validated,
	     * until they enter any of the exit condition Strings.
	     * User can input multiple numbers per line (space or comma separated).
	     * 
	     * @param numType of array
	     * @implNote needs to be cast to numType[]
	     * @return array of all entered numbers as an Object
	     */
		public Object getNumbers() {
		    ArrayList<Number> numbers = new ArrayList<>();
		    
		    printExitInstructions();
		    
		    while (true) {
		    	System.out.print(prompt);
		    	
		    	handleCtrlZ();
		    	
		        String input = scan.nextLine();
			    
		        if (shouldExit(input)) {
		            return arrayListToArrayObject(numbers);
		        }
		        
		        numbers = processNumberInput(input, numbers);
		    }
		}

		private void printExitInstructions() {
		    if (exitInstruction != null && !exitInstruction.isBlank()) {
		        Print.typeln(exitInstruction, 5, 15, 0);
		    }
		}
		
		private void handleCtrlZ() {
			if (!scan.hasNextLine()) {
                System.out.print("\nInput wurde durch CTR+Z gestoppt. \n");
                General.closeProgram();
            }
		}

		private boolean shouldExit(String input) {
		    for (String condition : exitConditions) {
		        if (input.equalsIgnoreCase(condition)) {
		            return true;
		        }
		    }
		    return false;
		}
		
		
		//TODO: implement arraylist myself then make this function in that class
		public Object arrayListToArrayObject(ArrayList<Number> list) {
			if (list == null) {
	    	    throw new IllegalArgumentException("list cannot be null");
	    	} else if (numType == null) {
	    	    throw new IllegalArgumentException("numType cannot be null");
	    	}
			
			Object array = numType.getPrimitiveArrayObject(list.size());
		    
		    for (int i = 0; i < list.size(); i++) {
		        Number value = list.get(i);
		        
		        switch (numType) {
		            case BYTE -> 	((byte[]) array)[i] =	value.byteValue();
		            case SHORT -> 	((short[]) array)[i] =	value.shortValue();
		            case INT -> 	((int[]) array)[i] =	value.intValue();
		            case LONG -> 	((long[]) array)[i] =	value.longValue();
		            case FLOAT -> 	((float[]) array)[i] =	value.floatValue();
		            case DOUBLE -> 	((double[]) array)[i] = value.doubleValue();
		            
		            default -> throw new IllegalArgumentException("Unsupported PrimNums numType: " + numType);
		        }
		    }

		    return array;
		}
		
		private ArrayList<Number> processNumberInput(String input, ArrayList<Number> numbers) {
		    try {
		    	Number[] numbersToAdd = parseNumbers(input);
		        
		        if (allNumbersValid(numbersToAdd)) {
		        	for (Number num : numbersToAdd) {
				    	numbers.add(num);
		        	}
		        	
		        	printParseConfirmation(numbersToAdd);
		        	
		        } else {
		        	printError(input);
		        }
		        
		    } catch (Exception e) {
		    	printError(input);
		    }
		    
		    return numbers;
		}
		
		/**
		 * Parse comma or space-separated numbers
		 * 
		 * @param input cannot be null
		 * @param numType cannot be null
		 * @return the seperated numbers in an array
		 */
	    private Number[] parseNumbers(String input) {
	    	String[] startingParts = input.split("[,\\s]+");
	    	String[] parts;
	    	
	    	if (startingParts[0].length() == 0) {
	    		parts = new String[startingParts.length - 1];
	    		System.arraycopy(startingParts, 1, parts, 0, startingParts.length - 1);
			} else {
				parts = startingParts;
			}
				
	        Number[] parsedArray = new Number[parts.length];
	        
	        for (int i = 0; i < parts.length; i++) {
	        	parsedArray[i] = Numbers.parseAs(parts[i], numType);
	        }
	        
	        return parsedArray;
	    }

		private boolean allNumbersValid(Number[] numbersToAdd) {
		    for (Number num : numbersToAdd) {
		        if (!isValidInput(num.toString(), validateFunc)) {
		            return false;
		        }
		    }
		    
		    return true;
		}
		
		private static void printParseConfirmation(Number[] numbersToAdd) {
        	String parsedNumbers = util.Array.numberToString(numbersToAdd);
        	
        	parsedNumbers = util.General.clampString(parsedNumbers, 30);
        	
        	System.out.print("Eingabe erfolgreich als ");
        	System.out.print(parsedNumbers);
        	System.out.print(" geparst. \n");
		}
		
		
		/* -- validation via lambdas -- */
		
		private static boolean isValidInput(String input, 
				Function<String, Boolean> validateInput) {
			if (input == null || input.isBlank()) {
				return false;
			}
			
			try {
				return validateInput.apply(input);
			} catch (Exception e) {
				return false;
			}
		}
		
		public InputBuilder numberValidation() {
			return this.validateFunc(validateNumber);
		}
		
		public Function<String, Boolean> validateNumber = input -> {
			Numbers.parseAs(input, numType);
			return true;
		};

		
		public InputBuilder rangeValidation() {
		    return this.validateFunc(validateInRange);
		}
		
		public Function<String, Boolean> validateInRange = input -> {
			Number number = Numbers.parseAs(input, numType);
			return util.Math.isInRange(number, min, max, minInclusive, maxInclusive);
		};
		
		
		public InputBuilder matchingValidation() {
		    return this.validateFunc(validateMatching);
		}
		
		public Function<String, Boolean> validateMatching = input -> {
			Number inputNumber = Numbers.parseAs(input, numType);
			
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
	
	/* -- convenience methods -- */
	
	public static String getString(String prompt) {
		return builder()
				.prompt(prompt)
				.getString();
	}
	
	public static <T extends Number> T getNumber(Numbers numType, String prompt) {
		return builder()
				.numType(numType)
				.prompt(prompt)
				.getNumber();
	}
	
	public static <T extends Number> T getNumberInRange(
			Numbers numType, String prompt, double min, double max) {
		
	    return builder()
	    		.numType(numType)
	    		.prompt(prompt)
	    		.range(min, max)
	    		.getNumber();
	}
	
	public static <T extends Number> T getMatchingNumber(
			Numbers numType, String prompt, Number[] numbersToMatch) {
		
		return builder()
				.numType(numType)
				.prompt(prompt)
				.numbersToMatch(numbersToMatch)
				.getNumber();
	}
}
