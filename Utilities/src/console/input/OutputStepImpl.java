package console.input;

import java.util.ArrayList;

import console.input.InputSteps.OutputStep;
import util.ArrayUtils;
import util.StringUtils;

public class OutputStepImpl<T> implements OutputStep<T>{
	
	private final InputConfig config;
	
	public OutputStepImpl(InputConfig config) {
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
			
    		String formattedInput = InputFormatter.formatInput(input, config);
    		
    		if (Validator.isValidInput(formattedInput, config.validateFunc)) {
				return (T) config.type.parse(formattedInput);
    		}
    		printError(input, config.error);
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

	        String formattedInput = InputFormatter.formatInput(input, config);
	        
	        String[] parts = Parser.getCleanParts(formattedInput);

	        if (!Validator.isAllValid(parts, config.validateFunc)) {
	        	printError(input, config.error);
	        	continue;
	        }
	        
	        Parser.printParseConfirmation(parts);
	        
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
    
    private static void printError(String input, String error) {
        String clamped = StringUtils.clampString(input, 30);
        System.out.printf(error, clamped);
    }
}
