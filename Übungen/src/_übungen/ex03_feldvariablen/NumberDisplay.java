package _übungen.ex03_feldvariablen;

import console.menu.Menu;
import console.output.ProgramMessages;

public class NumberDisplay {
	private static int NUM_COLUMNS = 5;
	private static int INSIDE_BRACKET_OFFSET = 4;
	private static int OUTSIDE_BRACKET_OFFSET = 1;
	private static String DELIMETER = "  ";
	private static boolean IS_DYNAMIC_SPACING = true;
	private static boolean IN_LINE_BRACKETS = true;

	public static void display(int[] numbers) {
	    if (numbers.length == 0) { return; }
	    else if (numbers.length < NUM_COLUMNS) { 
			NUM_COLUMNS = numbers.length; 
		}
	    
	    DisplayConfig config = new DisplayConfig(numbers);
	    
	    printTopLoadingBar(config);
	    printOpeningBracket(config);
	    printNumberGrid(numbers, config);
	    printClosingBracket(numbers, config);
	    printBottomLoadingBar(config);
	}
	
	private static class DisplayConfig {
	    final int[] columnWidths;
	    final int spacing;
	    final int loadingBarDotAmount;
	    final byte fullOffset;
	    
	    DisplayConfig(int[] numbers) {
	    	this.columnWidths = calculateColumnWidths(numbers);
		    this.spacing = calculateSpacing(numbers);
		    this.fullOffset = (byte) (INSIDE_BRACKET_OFFSET + OUTSIDE_BRACKET_OFFSET + 1);
		    this.loadingBarDotAmount = calculateLoadingBarDotAmount(columnWidths, spacing, fullOffset);
	    }
	    
	    private static int[] calculateColumnWidths(int[] numbers) {
			int[] columnWidths = new int[NUM_COLUMNS];

			for (int column = 0; column < NUM_COLUMNS; column++) {
				columnWidths[column] = 0;
			}

			for (int index = 0; index < numbers.length; index++) {
				int collumn = index % NUM_COLUMNS;
				int numDigits = String.valueOf(numbers[index]).length();

				if (columnWidths[collumn] < numDigits) {
					columnWidths[collumn] = numDigits;
				}
			}
			
			return columnWidths;
		}
	    
	    private static int calculateSpacing(int[] numbers) {
			int spacing = 0;

			for (int index = 0; index < numbers.length; index++) {
				int numDigits = String.valueOf(numbers[index]).length();

				if (spacing < numDigits) {
					spacing = numDigits;
				}
			}
			
			return spacing;
		}
	    
	    private static int calculateLoadingBarDotAmount(int[] columnWidths, int spacing, byte fullOffset) {
			int allDelimiter = DELIMETER.length() * NUM_COLUMNS;
			int allSpacing =  IS_DYNAMIC_SPACING ? (int) util.ArrayUtils.sumInt(columnWidths) : spacing * NUM_COLUMNS;
			int offsetWidth = INSIDE_BRACKET_OFFSET * 2 + OUTSIDE_BRACKET_OFFSET * 2;
			
			return  allDelimiter + allSpacing  - DELIMETER.length() + offsetWidth;
		}
	}
	
	private static void printTopLoadingBar(DisplayConfig config) {
		System.out.println();
		Menu.loadingBar(config.loadingBarDotAmount, 600, false);
		System.out.println();
	}

	private static void printNumberGrid(int[] numbers, DisplayConfig config) {
		for (int index = 0; index < numbers.length - 1; index++) {
			int columnIndex = index % NUM_COLUMNS;
			
			printLineBreak(index, columnIndex, config);
			
			printNumberWithSpacing(numbers[index], columnIndex, config);
		}
		
		printLastNumber(numbers, config);
	}
	
	private static void printLineBreak(int index, int columnIndex, DisplayConfig config) {
		if (columnIndex == 0 && index != 0) {
			System.out.println();
			ProgramMessages.spaces(config.fullOffset);
		}
	}
	
	private static void printOpeningBracket(DisplayConfig config) {
		ProgramMessages.spaces(OUTSIDE_BRACKET_OFFSET);
		System.out.print("[");
		
		if (IN_LINE_BRACKETS) {
			ProgramMessages.spaces((int) INSIDE_BRACKET_OFFSET);
		} else {
			System.out.println();
			ProgramMessages.spaces(config.fullOffset);
		}
	}
	
	private static void printNumberWithSpacing(int number, int columnIndex, DisplayConfig config) {
		System.out.print(number);
		System.out.print(DELIMETER);
		if (columnIndex == NUM_COLUMNS - 1) { return; }
		
		int spacesNeeded = getSpacing(number, columnIndex, config);
		ProgramMessages.spaces(spacesNeeded);
	}

	private static int getSpacing(int number, int columnIndex, DisplayConfig config) {
		int numDigits = Integer.toString(number).length();
		if (IS_DYNAMIC_SPACING) {
			return config.columnWidths[columnIndex] - numDigits;
		}
		return config.spacing - numDigits;
	}
	
	private static void printLastNumber(int[] numbers, DisplayConfig config) {
		if (numbers.length % NUM_COLUMNS == 1) {
			System.out.println();
			ProgramMessages.spaces(config.fullOffset);
		}
		
		System.out.print(numbers[numbers.length - 1]);
		
	}
	
	private static void printClosingBracket(int[] numbers, DisplayConfig config) {
		if (IN_LINE_BRACKETS) {
			int spacesToEnd = calculateSpacesToEnd(numbers, config);
			ProgramMessages.spaces(spacesToEnd);
			System.out.print("]");
		} else {
			System.out.println();
			ProgramMessages.spaces(OUTSIDE_BRACKET_OFFSET);
			System.out.print("]");
		}
	}

	private static int calculateSpacesToEnd(int[] numbers, DisplayConfig config) {
		int lastIndex = numbers.length - 1;
	    int columnsBeforeLast = (lastIndex % NUM_COLUMNS);
	    int lastNumberLength = Integer.toString(numbers[lastIndex]).length();
	    
	    int spacingWidth = calculateSpacingWidth(columnsBeforeLast, config);
	    int delimiterWidth = DELIMETER.length() * columnsBeforeLast;
	    int usedWidth = config.fullOffset + spacingWidth + delimiterWidth + lastNumberLength;
	    
	    return config.loadingBarDotAmount - usedWidth + 1 - OUTSIDE_BRACKET_OFFSET;
	}
	
	private static int calculateSpacingWidth(int columnCount, DisplayConfig config) {
	    if (IS_DYNAMIC_SPACING && columnCount > 1) {
	        return (int) util.ArrayUtils.sumIntInRange(config.columnWidths, 0, columnCount - 1);
	    }
	    return config.spacing * columnCount;
	}
	
	private static void printBottomLoadingBar(DisplayConfig config) {
		System.out.println();
		Menu.loadingBar(config.loadingBarDotAmount, 3000, true);
		System.out.print("\n\n");
	}

}
