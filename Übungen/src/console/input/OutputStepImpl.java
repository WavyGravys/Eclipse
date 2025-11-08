package console.input;

import java.util.ArrayList;

import console.input.InputSteps.OutputStep;
import console.output.ProgramMessages;
import util.ArrayUtils;

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
    		
    		String formattedInput = InputFormatter.formatInput(input, config);
    		
    		if (Validator.isValidInput(formattedInput, config.validateFunc)) {
				return (T) config.type.parse(formattedInput);
    		}
    		ProgramMessages.printError(input, config.error);
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
	        
			if (!Validator.isValidInput(formattedInput, config.validateFunc)) {
				ProgramMessages.printError(input, config.error);
				continue;
			}
			
	        
	        inputs = Processor.processInput(formattedInput, inputs, config.type);
	    }
	}
    
    private boolean shouldStop(String input) {
        return config.stopOnEmptyLine && input.isEmpty();
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
}
