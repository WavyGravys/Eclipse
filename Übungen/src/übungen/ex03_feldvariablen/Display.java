package übungen.ex03_feldvariablen;

import console.menu.Menu;
import console.output.ProgramMessages;
import util.ArrayUtils;

public class Display {

	public static void display(int[] numbers) {
		DisplayConfig config = new DisplayConfig(numbers);

		if (numbers.length == 0) {
			return;
		}

		printTopLoadingBar(config);
		printOpeningBracket(config);
		printNumberGrid(numbers, config);
		printClosingBracket(numbers, config);
		printBottomLoadingBar(numbers, config);
	}

	

	private static void printTopLoadingBar(DisplayConfig config) {
		Menu.loadingBar(config.loadingBarDotAmount, 400, false);
		System.out.println();
	}

	private static void printNumberGrid(int[] numbers, DisplayConfig config) {
		for (int index = 0; index < numbers.length - 1; index++) {
			int columnIndex = index % config.numColumns;

			printLineBreak(index, columnIndex, config);

			printNumberWithSpacing(numbers[index], columnIndex, config);
		}

		printLastNumber(numbers, config);
	}

	private static void printLineBreak(int index, int columnIndex, DisplayConfig config) {
		if (columnIndex == 0 && index != 0) {
			System.out.println();
			ProgramMessages.printSpaces(config.fullOffset);
		}
	}

	private static void printOpeningBracket(DisplayConfig config) {
		ProgramMessages.printSpaces(config.OUTSIDE_BRACKET_OFFSET);
		System.out.print("[");

		if (config.IN_LINE_BRACKETS) {
			ProgramMessages.printSpaces((int) config.INSIDE_BRACKET_OFFSET);
		} else {
			System.out.println();
			ProgramMessages.printSpaces(config.fullOffset);
		}
	}

	private static void printNumberWithSpacing(int number, int columnIndex, DisplayConfig config) {
		System.out.print(number);
		System.out.print(config.DELIMETER);
		if (columnIndex == config.numColumns - 1) {
			return;
		}

		int spacesNeeded = getSpacing(number, columnIndex, config);
		ProgramMessages.printSpaces(spacesNeeded);
	}

	private static int getSpacing(int number, int columnIndex, DisplayConfig config) {
		int numDigits = Integer.toString(number).length();
		if (config.IS_DYNAMIC_SPACING) {
			return config.columnWidths[columnIndex] - numDigits;
		}
		return config.spacing - numDigits;
	}

	private static void printLastNumber(int[] numbers, DisplayConfig config) {
		if (numbers.length % config.numColumns == 1) {
			System.out.println();
			ProgramMessages.printSpaces(config.fullOffset);
		}

		System.out.print(numbers[numbers.length - 1]);

	}

	private static void printClosingBracket(int[] numbers, DisplayConfig config) {
		if (config.IN_LINE_BRACKETS) {
			int spacesToEnd = calculateSpacesToEnd(numbers, config);
			ProgramMessages.printSpaces(spacesToEnd);
			System.out.print("]");
		} else {
			System.out.println();
			ProgramMessages.printSpaces(config.OUTSIDE_BRACKET_OFFSET);
			System.out.print("]");
		}
	}

	private static int calculateSpacesToEnd(int[] numbers, DisplayConfig config) {
		int lastIndex = numbers.length - 1;
		int columnsBeforeLast = (lastIndex % config.numColumns);
		int lastNumberLength = Integer.toString(numbers[lastIndex]).length();

		int spacingWidth = calculateSpacingWidth(columnsBeforeLast, config);
		int delimiterWidth = config.DELIMETER.length() * columnsBeforeLast;
		int usedWidth = config.fullOffset + spacingWidth + delimiterWidth + lastNumberLength;

		return config.loadingBarDotAmount - usedWidth + 1 - config.OUTSIDE_BRACKET_OFFSET;
	}

	private static int calculateSpacingWidth(int columnCount, DisplayConfig config) {
		if (config.IS_DYNAMIC_SPACING && columnCount > 1) {
			return (int) ArrayUtils.sumInRange(config.columnWidths, 0, columnCount - 1);
		}
		return config.spacing * columnCount;
	}

	private static void printBottomLoadingBar(int[] numbers, DisplayConfig config) {
		int loadingBartime = numbers.length * 400;
		loadingBartime = Math.clamp(loadingBartime, 400, 3000);

		System.out.print("\n");
		Menu.loadingBar(config.loadingBarDotAmount, loadingBartime, true);
		System.out.print("\n\n");
	}

	public static void string(String result) {
		// TODO: add clamping/scientific notation for large numbers
		// maybe directly in ArrayUtils
		System.out.println(result);
		Menu.loadingBar(result.length() - 2, 2000, false);
		System.out.print("\n\n");
	}
}
