package console.input;

import java.util.ArrayList;
import util.Types;

public class Processor {

	public static ArrayList<Object> processInputs(String[] parts, ArrayList<Object> inputs, Types type) {

		Object[] toAdd = parseMultiple(parts, type);

		for (Object elem : toAdd) {
			inputs.add(elem);
		}

		return inputs;
	}

	private static Object[] parseMultiple(String[] parts, Types type) {

		Object[] parsed = new Object[parts.length];

		for (int i = 0; i < parts.length; i++) {
			parsed[i] = type.parse(parts[i]);
		}

		return parsed;
	}

	public static String[] getCleanParts(String input) {
		String[] parts = input.trim().split("[,\\s]+");

		if (parts[0].isEmpty() && parts.length > 0) {
			String[] newParts = new String[parts.length - 1];
			System.arraycopy(parts, 1, newParts, 0, parts.length - 1);

			return newParts;
		}
		return parts;
	}

	public static String formatInput(String input, Config config) {
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