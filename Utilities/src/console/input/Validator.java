package console.input;

import util.Types;
import util.Function;

public class Validator {

	public static boolean isAllValid(String[] inputs, Function<String, Boolean> validateFunc) {
		for (String part : inputs) {
			if (!isValidInput(part, validateFunc)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isValidInput(String input, Function<String, Boolean> validateFunc) {
		return validateFunc.apply(input);
	}

	public static Function<String, Boolean> string() {
		return s -> { return true; };
	}

	public static Function<String, Boolean> number(Types numType) {
		return input -> {
			try {
				numType.parse(input);
				return true;
			} catch (Exception e) {
				return false;
			}
		};
	}

	public static Function<String, Boolean> inRange(Types numType, Number min, Number max, boolean minInclusive,
			boolean maxInclusive) {
		return input -> {
			Number number;
			try {
				number = numType.parse(input);
			} catch (Exception e) {
				return false;
			}
			return math.MathUtils.isInRange(number, min, max, minInclusive, maxInclusive);
		};
	}

	public static Function<String, Boolean> matching(Types type, Object[] toMatch) {
		return input -> {
			if (type == Types.STRING) {
				for (int i = 0; i < toMatch.length; i++) {
					String match = toMatch[i].toString();
					if (input.equals(match)) {
						return true;
					}
				}
				return false;
			}
			Number number;
			try {
				number = type.parse(input);
			} catch (Exception e) {
				return false;
			}

			for (int i = 0; i < toMatch.length; i++) {
				Number match = (Number) toMatch[i];
				if (number.equals(match)) {
					return true;
				}
			}
			return false;
		};
	}
}