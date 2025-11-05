package main;

import java.util.Scanner;


public class Feldvariablen implements Übung {

	static Scanner scan = new Scanner(System.in);

	// settings TODO: settings file (JSON?)
	private static byte NUM_COLUMNS = 5;
	private static byte X_OFFSET = 4;
	private static byte xOffset = X_OFFSET; // not a setting
	private static String DELIMETER = ", ";
	private static boolean IS_DYNAMIC_SPACING = false;
	private static boolean IN_LINE_BRACKETS = false;
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
	
	private int[] columnWidths;
	private int spacing;
	int loadingBarDotAmount;
	
	public void start() {
		util.General.explainProgram(explainStrings);
		
		while (true) {
			int[] numbers =  getNumbers();
			
			if (numbers.length == 0) {
				System.out.print("[ ¯\\_(ツ)_/¯ ]\n\n");
				
				if (util.Menu.shouldExit()) {
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
		
		// TODO: erfolgreich als [1, 2, 3 ...] gepars
		
		int[] numArray = (int[]) util.Input.builder()
				.numType(util.Numbers.INT)
				.getNumbers();
				
		return numArray;
	}
	
	private void displayNumbers(int[] numbers) {
		if (numbers.length == 0)
			return;
		
		updateColumnWidths(numbers);
		updateSpacing(numbers);
		updateLoadingBarDotAmount();
		
		printTopLoadingBar();
		printOpeningBracket();
		printNumberGrid(numbers);
		printClosingBracket(numbers);
		printBottomLoadingBar();
	}
	
	private void updateColumnWidths(int[] numbers) {
		columnWidths = new int[NUM_COLUMNS];

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
	}

	private void updateSpacing(int[] numbers) {
		spacing = 0;

		for (int index = 0; index < numbers.length; index++) {
			int numDigits = String.valueOf(numbers[index]).length();

			if (spacing < numDigits) {
				spacing = numDigits;
			}
		}
	}

	private void updateLoadingBarDotAmount() {
		int delimiterLength = DELIMETER.length();
		int columnSpacing = (int) (IS_DYNAMIC_SPACING ? util.Array.sumInt(columnWidths) : spacing);
		int contentWidth = (delimiterLength + columnSpacing) * NUM_COLUMNS;
		loadingBarDotAmount =  (int) (xOffset + (X_OFFSET - delimiterLength) + 2 + contentWidth);
	}

	private void printTopLoadingBar() {
		System.out.println();
		util.Menu.loadingBar(loadingBarDotAmount, 40, false);
	}

	private void printOpeningBracket() {
		if (!IN_LINE_BRACKETS) {
			System.out.print("\n [");
			xOffset += 2;
		}
	}

	private void printNumberGrid(int[] numbers) {
		for (int index = 0; index < numbers.length - 1; index++) {
			int columnIndex = index % NUM_COLUMNS;
			
			if (columnIndex == 0) {
				System.out.println();
				printRowIndentation(index);
			}
			
			printNumberWithSpacing(numbers[index], columnWidths, columnIndex, spacing);
		}
		
		printLastNumber(numbers);
	}
	
	private void printRowIndentation(int index) {
		if (IN_LINE_BRACKETS && index == 0) {
			System.out.print(" [");
			util.Print.spaces(X_OFFSET);
			xOffset += 2;
		} else {
			util.Print.spaces(xOffset);
		}
	}
	
	private void printNumberWithSpacing(int number, int[] columnWidths, int columnIndex, int spacing) {
		System.out.print(number);
		if (columnIndex == NUM_COLUMNS - 1) { return; }
		
		System.out.print(DELIMETER);
		int spacesNeeded = calculateSpacing(number, columnWidths, columnIndex, spacing);
		util.Print.spaces(spacesNeeded);
	}

	private int calculateSpacing(int number, int[] columnWidths, int columnIndex, int spacing) {
		int numDigits = Integer.toString(number).length();
		if (IS_DYNAMIC_SPACING) {
			return columnWidths[columnIndex] - numDigits;
		}
		return spacing - numDigits;
	}
	
	private void printLastNumber(int[] numbers) {
		if (numbers.length % NUM_COLUMNS == 1) {
			System.out.println();
			util.Print.spaces(xOffset);
		}
		
		System.out.print(numbers[numbers.length - 1]);
		
	}
	
	private void printClosingBracket(int[] numbers) {
		if (IN_LINE_BRACKETS) {
			int spacesToEnd = calculateSpacesToEnd(numbers, numbers.length - 1, loadingBarDotAmount);
			util.Print.spaces(spacesToEnd);
			System.out.print("]");
		} else {
			System.out.print("\n ]");
		}
	}

	private int calculateSpacesToEnd(int[] numbers, int lastIndex, int loadingBarDotAmount) { // FIXME
		int lastNumberLength = Integer.toString(numbers[lastIndex]).length();
		int columnSpacing =  (IS_DYNAMIC_SPACING ? (int) util.Array.sumInt(columnWidths) : spacing);
		int columnsBeforeLast = (numbers.length % NUM_COLUMNS) - 1;
		int usedSpace = (xOffset + DELIMETER.length() + lastNumberLength
				+ (DELIMETER.length() + columnSpacing) * columnsBeforeLast);
		return loadingBarDotAmount - usedSpace + xOffset - X_OFFSET;
	}

	private void printBottomLoadingBar() {
		System.out.println();
		util.Menu.loadingBar(loadingBarDotAmount, 160, true);
		System.out.print("\n\n");
	}

	private boolean menu(int[] numbers) {
		String menuString = "\n========= Menü =========\n 1 - Erneute Eingabe \n "
				+ "2 - Mathe Operationen \n 0 - ZURÜCK \n";
		int menuChoice = util.Menu.basic(menuString, util.Array.integerRange(3));

		switch (menuChoice) {
		case 0:
			return false;

		case 1:
			return true;

		case 2:
			return mathMenu(numbers);

		default:
			System.out.println("ERROR: util.General.menu() " + "returned byte out of range of acceptedMenuOptions");
			return false;
		}
	}

	private boolean mathMenu(int[] numbers) {
		String mathMenuString = "\n====== Array Menü ======\n 1 - Summe \n 2 - Produkt \n"
				+ " 3 - Minimum \n 4 - Maximum \n 0 - ZURÜCK \n";
		String mathOutputString = "\n%s Ihrer eingegebenen Zahlen ist %d \n";

		int mathMenuChoice = util.Menu.instantBasic(mathMenuString, util.Array.integerRange(5));
		
		switch (mathMenuChoice) {
		case 0:
			return menu(numbers);

		case 1:
			System.out.printf(mathOutputString, "Die Summe", util.Array.sumInt(numbers));
			break;

		case 2:
			System.out.printf(mathOutputString, "Das Produkt", util.Array.prodInt(numbers));
			break;

		case 3:
			System.out.printf(mathOutputString, "Das Minimum", util.Array.minInt(numbers));
			break;

		case 4:
			System.out.printf(mathOutputString, "Das Maximum", util.Array.maxInt(numbers));
			break;
		}
		return mathMenu(numbers);
	}
}
