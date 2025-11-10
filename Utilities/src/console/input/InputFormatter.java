package console.input;

import util.Types;

public class InputFormatter {
	public static String formatInput(String input, InputConfig config) {
		if (!config.shouldFormat || config.type == Types.STRING) {
			return input; 
		}
		
		StringBuilder sbRegex = new StringBuilder("[^0-9.-");
		sbRegex.append(config.delimiter);
		sbRegex.append(config.exitConditions.toString());
		sbRegex.append("]");
		
		return input.replaceAll(sbRegex.toString(), "");
	}
}
