package console.input;

import console.input.StepInterfaces.TypeStep;

public class Input {

	public static void clearScannerCache() {
		while (Reader.hasAvailableInput()) {
			try {
				System.in.read();
			} catch (Exception e) {}
		}
	}

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
				System.out.println("Skipped!");
			}

			clearScannerCache();
			return true;
		}
		return false;
	}

	public static TypeStep builder() {
		return new TypeStepImpl();
	}

	public static String getString(String prompt) {
		return builder()
				.typeString()
				.prompt(prompt)
				.stringValidation()
				.get();
	}

	public static int getInt(String prompt) {
		return builder()
				.typeInteger()
				.prompt(prompt)
				.numberValidation()
				.get();
	}
	
	public static double getDouble(String prompt) {
		return builder()
				.typeDouble()
				.prompt(prompt)
				.numberValidation()
				.get();
	}

	public static int getIntInRange(String prompt, double min, double max) {
		return builder()
				.typeInteger()
				.prompt(prompt)
				.rangeValidation(min, max)
				.inclusivity(true, true)
				.get();
	}

	public static int getMatchingInt(String prompt, Number[] match) {
		return builder()
				.typeInteger()
				.prompt(prompt)
				.matchingValidation(match)
				.get();
	}
}
