package console.input;

import java.util.ArrayList;

import console.input.StepInterfaces.OutputStep;
import util.ArrayUtils;
import util.StringUtils;

public class OutputStepImpl<T> implements OutputStep<T>{
	
	private final Config config;
	
	public OutputStepImpl(Config config) {
        this.config = config;
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public T get() {
		
		while (true) {
			System.out.print(config.prompt);
			
			String input = Reader.readLine();
    		
			if (shouldExit(input, config.exitConditions)) {
	        	return null;
	        }
			
    		String formattedInput = Processor.formatInput(input, config);
    		
    		if (Validator.isValidInput(formattedInput, config.validateFunc)) {
				return (T) config.type.parse(formattedInput);
    		}
    		Print.error(input, config.error);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object getMult() {
		ArrayList<Object> inputs = new ArrayList<>();
	    
	    while (true) {
	    	System.out.print(config.prompt);
	    	
	        String input = Reader.readLine();
	        
	        if (shouldExit(input, config.exitConditions)) {
	        	return ArrayUtils.toPrimitive(config.type, inputs.toArray((T[]) config.type.newArray(0)));
	        }

	        String formattedInput = Processor.formatInput(input, config);
	        
	        String[] parts = Processor.getCleanParts(formattedInput);

	        if (!Validator.isAllValid(parts, config.validateFunc)) {
	        	Print.error(input, config.error);
	        	continue;
	        }
	        
	        Print.parseConfirmation(parts);
	        
	        inputs = Processor.processInputs(parts, inputs, config.type);
	        
	    }
	}
    
    public static boolean shouldExit(String input, String[] exitConditions) {
	    if (exitConditions == null) { return false; }
    	for (String condition : exitConditions) {
	        if (input.equalsIgnoreCase(condition)) {
	            return true;
	        }
	    }
	    return false;
	}
    
    private class Print {
    	public static void parseConfirmation(String[] parsed) {
        	String parsedNumbers = ArrayUtils.combineStrings(parsed);
        	parsedNumbers = StringUtils.clampString(parsedNumbers, 30);
        	
        	System.out.print("Eingabe erfolgreich als ");
        	System.out.print(parsedNumbers);
        	System.out.print(" geparst.\n");
    	}
    	
    	public static void error(String input, String error) {
            String clamped = StringUtils.clampString(input, 30);
            System.out.printf(error, clamped);
        }
    }
}