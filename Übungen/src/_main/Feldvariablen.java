package _main;

import _übungen.Übung;
import console.input.Input;
import console.menu.Menu;
import console.output.ProgramMessages;


public class Feldvariablen implements Übung {
	
	private static int NUM_COLUMNS = 5;
	private static int INSIDE_BRACKET_OFFSET = 4;
	private static int OUTSIDE_BRACKET_OFFSET = 1;
	private static String DELIMETER = "  ";
	private static boolean IS_DYNAMIC_SPACING = true;
	private static boolean IN_LINE_BRACKETS = true;
	private static final String[] explainStrings = new String[] {
			"In folgendem Programm geben Sie eine Zahlenfolge ein,", 
			"welche dann schön dargestellt wird,",
			"und womit Sie rechenoperationen durchführen können.",
			"Die zahlenreihe kann mit folgenden Zeichen/Zeichenreihen getrennt werden:", 
			"    ' '", 
			"    ','",
			"    ', '", 
			"oder einfach mit Zeilenumbruchen (Enter).",
			"Wenn sie alle Numern eingegeben haben, entern Sie ein 'X'", 
			"oder entern Sie eine lehre Eingabe", 
			};
	
	public void start() {
		ProgramMessages.explainProgram(explainStrings);
		
		while (true) {
			int[] numbers =  getNumbers();
			
			if (numbers.length == 0 || numbers == null) {
				System.out.print("[ ¯\\_(ツ)_/¯ ]\n\n");
				
				if (Menu.shouldExit()) {
					return;
				}
				continue;
			}
			
			if (numbers.length < NUM_COLUMNS) { 
				NUM_COLUMNS = (byte) numbers.length; 
			}
			
			displayNumbers(numbers);

			if (!menu(numbers)) {
				return;
			}
		}
	}
	
	private static int[] getNumbers() {
		return (int[]) Input.builder()
				.typeInteger()
				.numberValidation()
				.getMult();
	}
	
	private void displayNumbers(int[] numbers) {
	    if (numbers.length == 0) return;
	    
	    DisplayConfig config = calculateDisplayConfig(numbers);
	    
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
	    
	    DisplayConfig(int[] columnWidths, int spacing, int loadingBarDotAmount, byte fullOffset) {
	        this.columnWidths = columnWidths;
	        this.spacing = spacing;
	        this.loadingBarDotAmount = loadingBarDotAmount;
	        this.fullOffset = fullOffset;
	    }
	}
	
	private DisplayConfig calculateDisplayConfig(int[] numbers) {
	    int[] columnWidths = calculateColumnWidths(numbers);
	    int spacing = calculateSpacing(numbers);
	    byte fullOffset = (byte) (INSIDE_BRACKET_OFFSET + OUTSIDE_BRACKET_OFFSET + 1);
	    int loadingBarDotAmount = calculateLoadingBarDotAmount(columnWidths, spacing, fullOffset);
	    
	    return new DisplayConfig(columnWidths, spacing, loadingBarDotAmount, fullOffset);
	}
	
	private int[] calculateColumnWidths(int[] numbers) {
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

	private int calculateSpacing(int[] numbers) {
		int spacing = 0;

		for (int index = 0; index < numbers.length; index++) {
			int numDigits = String.valueOf(numbers[index]).length();

			if (spacing < numDigits) {
				spacing = numDigits;
			}
		}
		
		return spacing;
	}

	private int calculateLoadingBarDotAmount(int[] columnWidths, int spacing, byte fullOffset) {
		int allDelimiter = DELIMETER.length() * NUM_COLUMNS;
		int allSpacing =  IS_DYNAMIC_SPACING ? (int) util.ArrayUtils.sumInt(columnWidths) : spacing * NUM_COLUMNS;
		int offsetWidth = INSIDE_BRACKET_OFFSET * 2 + OUTSIDE_BRACKET_OFFSET * 2;
		
		return  allDelimiter + allSpacing  - DELIMETER.length() + offsetWidth;
	}

	private void printTopLoadingBar(DisplayConfig config) {
		System.out.println();
		console.menu.Menu.loadingBar(config.loadingBarDotAmount, 40, false);
		System.out.println();
	}

	private void printNumberGrid(int[] numbers, DisplayConfig config) {
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
	
	private void printNumberWithSpacing(int number, int columnIndex, DisplayConfig config) {
		System.out.print(number);
		System.out.print(DELIMETER);
		if (columnIndex == NUM_COLUMNS - 1) { return; }
		
		int spacesNeeded = getSpacing(number, columnIndex, config);
		ProgramMessages.spaces(spacesNeeded);
	}

	private int getSpacing(int number, int columnIndex, DisplayConfig config) {
		int numDigits = Integer.toString(number).length();
		if (IS_DYNAMIC_SPACING) {
			return config.columnWidths[columnIndex] - numDigits;
		}
		return config.spacing - numDigits;
	}
	
	private void printLastNumber(int[] numbers, DisplayConfig config) {
		if (numbers.length % NUM_COLUMNS == 1) {
			System.out.println();
			ProgramMessages.spaces(config.fullOffset);
		}
		
		System.out.print(numbers[numbers.length - 1]);
		
	}
	
	private void printClosingBracket(int[] numbers, DisplayConfig config) {
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

	private int calculateSpacesToEnd(int[] numbers, DisplayConfig config) {
		int lastIndex = numbers.length - 1;
	    int columnsBeforeLast = (lastIndex % NUM_COLUMNS);
	    int lastNumberLength = Integer.toString(numbers[lastIndex]).length();
	    
	    int spacingWidth = calculateSpacingWidth(columnsBeforeLast, config);
	    int delimiterWidth = DELIMETER.length() * columnsBeforeLast;
	    int usedWidth = config.fullOffset + spacingWidth + delimiterWidth + lastNumberLength;
	    
	    return config.loadingBarDotAmount - usedWidth + 1 - OUTSIDE_BRACKET_OFFSET;
	}
	
	private int calculateSpacingWidth(int columnCount, DisplayConfig config) {
	    if (IS_DYNAMIC_SPACING && columnCount > 1) {
	        return (int) util.ArrayUtils.sumIntInRange(config.columnWidths, 0, columnCount - 1);
	    }
	    return config.spacing * columnCount;
	}
	
	private void printBottomLoadingBar(DisplayConfig config) {
		System.out.println();
		Menu.loadingBar(config.loadingBarDotAmount, 160, true);
		System.out.print("\n\n");
	}

	private boolean menu(int[] numbers) {
		String[] menu = new String[] {
				"========= Menü =========", "1 - Erneute Eingabe",
				"2 - Mathe Operationen", "0 - ZURÜCK"};
		int menuChoice = Menu.basic(menu, util.ArrayUtils.integerRange(3));

		switch (menuChoice) {
		case 0:
			return false;

		case 1:
			return true;

		case 2:
			return mathMenu(numbers);
		};
		
		return false;
	}

	private boolean mathMenu(int[] numbers) {
		String[] menu = new String[] {"====== Array Menü ======", "1 - Summe", "2 - Produkt", " 3 - Minimum", "4 - Maximum", "0 - ZURÜCK"};
		String mathOutputString = "\n%s Ihrer eingegebenen Zahlen ist %d \n";

		int mathMenuChoice = Menu.basic(menu, util.ArrayUtils.integerRange(5));
		
		switch (mathMenuChoice) {
		case 0:
			return menu(numbers);     

		case 1:
			System.out.printf(mathOutputString, "Die Summe", util.ArrayUtils.sumInt(numbers));
			break;

		case 2:
			System.out.printf(mathOutputString, "Das Produkt", util.ArrayUtils.prodInt(numbers));
			break;

		case 3:
			System.out.printf(mathOutputString, "Das Minimum", util.ArrayUtils.minInt(numbers));
			break;

		case 4:
			System.out.printf(mathOutputString, "Das Maximum", util.ArrayUtils.maxInt(numbers));
			break;
		};
		
		return mathMenu(numbers);
	}
}
