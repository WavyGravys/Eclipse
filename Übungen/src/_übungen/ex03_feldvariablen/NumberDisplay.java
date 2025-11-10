package _übungen.ex03_feldvariablen;

import console.menu.Menu;
import console.output.ProgramMessages;
import util.ArrayUtils;

public class NumberDisplay {

	public static void display(int[] numbers) {
		DisplayConfig config = new DisplayConfig(numbers);

		if (numbers.length == 0) { return; }
	    else 
	    
	    printTopLoadingBar(config);
	    printOpeningBracket(config);
	    printNumberGrid(numbers, config);
	    printClosingBracket(numbers, config);
	    printBottomLoadingBar(numbers, config);
	}
	
	private static class DisplayConfig {
		private static int INSIDE_BRACKET_OFFSET = 4;
		private static int OUTSIDE_BRACKET_OFFSET = 1;
		private static String DELIMETER = "  ";
		private static boolean IS_DYNAMIC_SPACING = true;
		private static boolean IN_LINE_BRACKETS = true;
		
		private int numColumns = 5;
		final int[] columnWidths;
	    final int spacing;
	    final int loadingBarDotAmount;
	    final byte fullOffset;
	    
	    DisplayConfig(int[] numbers) {
	    	this.numColumns = updateNumColumns(numbers);
	    	this.columnWidths = calculateColumnWidths(numbers, this.numColumns);
		    this.spacing = calculateSpacing(numbers);
		    this.loadingBarDotAmount = calculateLoadingBarDotAmount(this.numColumns, this.columnWidths, this.spacing);
		    this.fullOffset = calculateFulOffset();
	    }
	    
	    private int updateNumColumns(int[] numbers) {
	    	if (numbers.length < this.numColumns) { 
	    		return numbers.length; 
			}
	    	return this.numColumns;
	    }
	    
	    private static int[] calculateColumnWidths(int[] numbers, int numColumns) {
			int[] columnWidths = new int[numColumns];

			for (int column = 0; column < numColumns; column++) {
				columnWidths[column] = 0;
			}

			for (int index = 0; index < numbers.length; index++) {
				int collumn = index % numColumns;
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
	    
	    private static  byte calculateFulOffset() {
	    	return (byte) (INSIDE_BRACKET_OFFSET + OUTSIDE_BRACKET_OFFSET + 1);
	    }
	    
	    private static int calculateLoadingBarDotAmount(int numColumns, int[] columnWidths, int spacing) {
			int allDelimiter = DELIMETER.length() * numColumns;
			int allSpacing =  !IS_DYNAMIC_SPACING ? spacing * numColumns : (int) ArrayUtils.sumInt(columnWidths);
			int offsetWidth = INSIDE_BRACKET_OFFSET * 2 + OUTSIDE_BRACKET_OFFSET * 2;
			
			return  allDelimiter + allSpacing  - DELIMETER.length() + offsetWidth;
		}
	}
	
	private static void printTopLoadingBar(DisplayConfig config) {
		System.out.println();
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
			ProgramMessages.spaces(config.fullOffset);
		}
	}
	
	private static void printOpeningBracket(DisplayConfig config) {
		ProgramMessages.spaces(DisplayConfig.OUTSIDE_BRACKET_OFFSET);
		System.out.print("[");
		
		if (DisplayConfig.IN_LINE_BRACKETS) {
			ProgramMessages.spaces((int) DisplayConfig.INSIDE_BRACKET_OFFSET);
		} else {
			System.out.println();
			ProgramMessages.spaces(config.fullOffset);
		}
	}
	
	private static void printNumberWithSpacing(int number, int columnIndex, DisplayConfig config) {
		System.out.print(number);
		System.out.print(DisplayConfig.DELIMETER);
		if (columnIndex == config.numColumns - 1) { return; }
		
		int spacesNeeded = getSpacing(number, columnIndex, config);
		ProgramMessages.spaces(spacesNeeded);
	}

	private static int getSpacing(int number, int columnIndex, DisplayConfig config) {
		int numDigits = Integer.toString(number).length();
		if (DisplayConfig.IS_DYNAMIC_SPACING) {
			return config.columnWidths[columnIndex] - numDigits;
		}
		return config.spacing - numDigits;
	}
	
	private static void printLastNumber(int[] numbers, DisplayConfig config) {
		if (numbers.length % config.numColumns == 1) {
			System.out.println();
			ProgramMessages.spaces(config.fullOffset);
		}
		
		System.out.print(numbers[numbers.length - 1]);
		
	}
	
	private static void printClosingBracket(int[] numbers, DisplayConfig config) {
		if (DisplayConfig.IN_LINE_BRACKETS) {
			int spacesToEnd = calculateSpacesToEnd(numbers, config);
			ProgramMessages.spaces(spacesToEnd);
			System.out.print("]");
		} else {
			System.out.println();
			ProgramMessages.spaces(DisplayConfig.OUTSIDE_BRACKET_OFFSET);
			System.out.print("]");
		}
	}

	private static int calculateSpacesToEnd(int[] numbers, DisplayConfig config) {
		int lastIndex = numbers.length - 1;
	    int columnsBeforeLast = (lastIndex % config.numColumns);
	    int lastNumberLength = Integer.toString(numbers[lastIndex]).length();
	    
	    int spacingWidth = calculateSpacingWidth(columnsBeforeLast, config);
	    int delimiterWidth = DisplayConfig.DELIMETER.length() * columnsBeforeLast;
	    int usedWidth = config.fullOffset + spacingWidth + delimiterWidth + lastNumberLength;
	    
	    return config.loadingBarDotAmount - usedWidth + 1 - DisplayConfig.OUTSIDE_BRACKET_OFFSET;
	}
	
	private static int calculateSpacingWidth(int columnCount, DisplayConfig config) {
	    if (DisplayConfig.IS_DYNAMIC_SPACING && columnCount > 1) {
	        return (int) ArrayUtils.sumIntInRange(config.columnWidths, 0, columnCount - 1);
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
}
