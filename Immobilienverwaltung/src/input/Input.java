package input;

import input.StepInterfaces.TypeStep;

public class Input<T> {
	
	public static TypeStep builder() {
		return new TypeStepImpl();
	}
	
	
	private class Config {
		Types type;
		String prompt;
		String exitConditions;
		String error;
		Function<T,Boolean> validateFunc;
		
	}
	
	
	private T get(Config config) {
		
		
		
		
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
	
	
	public static int getInt() {
		
		
		
	}
	
	public static float getFloat() {
		
	}
	
	public static String getString() {
		
	}
}
