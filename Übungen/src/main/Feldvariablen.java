package main;

import java.util.Scanner;
import java.util.ArrayList;


public class Feldvariablen {
	
	static final Scanner scan = new Scanner(System.in);
	
	// settings TODO: settings file (JSON?)
	static final byte numColumns = 5;
	static final byte xOffset = 4;
	static final String delimeter = ", ";
	static final boolean isDynamicSpacing = false;
	static final String errorMessage = "ERROR: \"%s\" ist keine valide Eingabe. Versuchen Sie es bitte erneut. \n";
	
	
	private static void explainProgram() {
		System.out.print("\n========== Wilkommen ==========\n");
		System.out.print("In folgendem Programm geben Sie eine Zahlenfolge ein, \n");
		System.out.print("welche dann schön dargestellt wird, \n");
		System.out.print("und womit Sie rechenoperationen durchführen können. \n");
		System.out.print("Die zahlenreihe kann mit folgenden Zeichen/Zeichenreihen getrennt werden: \n");
		System.out.print("' '\n','\n', '\noder einfach mit Zeilenumbruchen (Enter) \n\n");
		System.out.print("Wenn sie alle Numern eingegeben haben, geben Sie ein 'X' ein \n");
		System.out.print("oder entern Sie eine lehre Eingabe \n");
		System.out.print("viel Spaß! \n");
	}
	
	
	private static int[] getNumbers() {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		String input;
		System.out.println("\nGeben Sie Ihre Nummern ein");
		while (true) {
    		System.out.print("Eingabe: ");
    		if (!scan.hasNextLine()) // to handle the user pressing "ctrl+z", while scan is waiting for input. (TODO: doesnt work at the start)
    			System.out.println(" ");
    		input = scan.nextLine();
			
			if ((input.equals("") || input.equals("X") || input.equals("x"))) {
				if (numbers.isEmpty()) {
					System.out.printf(errorMessage, input);
					continue;
				}
				int[] numbersArray = convertArrayListToArray(numbers);
				return numbersArray;
			}
			
			try {
				int[] numbersToAdd = parseInput(input);
				
				for (int index=0; index<numbersToAdd.length; index++)
					numbers.add(numbersToAdd[index]);
			}
			catch(Exception e) {
				System.out.printf(errorMessage, input);
				continue;
			}
		}
	}
	
	
	private static int[] parseInput(String input) throws Exception {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		int numberStartIndex = 0;
		char currentChar;
		enum LastChar {NUMBER, COMMA, SPACE}
		LastChar lastChar = LastChar.NUMBER;
		
		input = input.replaceAll("[^0-9, ]", "");
		char[] inputCharArray = input.toCharArray();
		if (input.equals(""))
			throw new Exception("empty input after formatting");
		
		for (int index=0; index<inputCharArray.length; index++) {
			currentChar = inputCharArray[index];
			switch (currentChar) {
			case ',':
				if (lastChar == LastChar.NUMBER)
					numbers.add(Integer.parseInt(input.substring(numberStartIndex,index)));
				lastChar = LastChar.COMMA;
				break;
			case ' ':
				if (lastChar == LastChar.NUMBER)
					numbers.add(Integer.parseInt(input.substring(numberStartIndex,index)));
				lastChar = LastChar.SPACE;
				break;
			default: // if charAtIndex is a number
				if (lastChar == LastChar.COMMA || lastChar == LastChar.SPACE)
					numberStartIndex = index;
				if (index == inputCharArray.length-1)
					numbers.add(Integer.parseInt(input.substring(numberStartIndex,index+1)));
				lastChar = LastChar.NUMBER;
				break;
			}
		}
		
		int [] returnArray = convertArrayListToArray(numbers);
		System.out.print("Eingabe erfolgreich als ");
		util.Array.printInt(returnArray);
		System.out.println(" geparst.");
		return returnArray;
	}
	
	
	private static int[] convertArrayListToArray(ArrayList<Integer> numbers) {
		int numbersAmount = numbers.size();
		int[] numbersArray = new int[numbersAmount];
		
		for (int index=0; index<numbersAmount; index++) {
			numbersArray[index] = numbers.get(index);
		}
		
		return numbersArray;
	}
	
	
	private static int[] getIndividualColumnWidths(int[] numbers, int numberAmount) {
		int columnWidths[] = new int[numColumns];
		for (int column=0; column<numColumns; column++) {
			columnWidths[column] = 0;
		}
		for (int index=0; index<numberAmount; index++) {
			int collumn = index%numColumns;
			int numDigits = String.valueOf(numbers[index]).length();
			if (columnWidths[collumn] < numDigits) {
				columnWidths[collumn] = numDigits;
			}
		}
		return columnWidths;
	}
	
	
	private static int getSpacing(int[] numbers, int numberAmount) {
		int highestNumDigits = 0;
		for (int index=0; index<numberAmount; index++) {
			int numDigits = String.valueOf(numbers[index]).length();
			if (highestNumDigits < numDigits) {
				highestNumDigits = numDigits;
			}
		}
		return highestNumDigits;
	}
	
	
	private static void displayNumbers(int[] numbers) {
		int numberAmount = numbers.length;
		int[] columnWidths;
		int spacing;
		
		if (isDynamicSpacing)
			columnWidths = getIndividualColumnWidths(numbers, numberAmount);
		else 
			spacing = getSpacing(numbers, numberAmount);
		
		System.out.print("\n[");
		
		for(int index=0; index<numberAmount-1; index++) {
			// indentation and linebreaks
			if(index%numColumns==0) {
				System.out.print("\n");
				for (int i=0; i<xOffset; i++) {
					System.out.print(" ");
				}
			}
			
			System.out.print(numbers[index]);
			System.out.print(delimeter);
			
			// spacing
			int numDigits = String.valueOf(numbers[index]).length();
			int neededSpaces;
			if (isDynamicSpacing)
				neededSpaces = columnWidths[index%numColumns]-numDigits;
			else
				neededSpaces = spacing-numDigits;
			for (int m=0; m<(neededSpaces); m++) {
				System.out.print(" ");
			}
		}
		
		System.out.printf("%d",numbers[numberAmount-1]); 
		System.out.print("\n]\n");
	}
	
	
	private static boolean menu(int[] numbers) {
		String menuString = "\n========= Menü =========\n 1 - Erneute Eingabe \n 2 - Mathe Operationen \n 0 - ENDE \n";
		byte menuChoice = util.General.customChoicesMenu(menuString, new int[] {0,1,2});
			
		switch (menuChoice) {
		case 0:
			return false;
		case 1:
			return true;
		case 2:
			return mathMenu(numbers);
		default: // this should never gets executed but calms the IDE down
			System.out.println("ERROR: util.General.menu() returned byte out of range of acceptedMenuOptions");
			return false;
		}
	}
	
	
	private static boolean mathMenu(int[] numbers) {
		
		String mathMenuString = "\n====== Array Menü ======\n 1 - Summe \n 2 - Produkt \n 3 - Minimum \n 4 - Maximum \n 0 - ZURÜCK \n";
		byte mathMenuChoice = util.General.instantCustomChoicesMenu(mathMenuString, new int[] {0,1,2,3,4});
		
		switch (mathMenuChoice) {
		case 0:
			return menu(numbers);
		case 1:
			System.out.printf("\nDie Summe Ihrer eingegebenen Zahlen ist %d \n", util.Array.sum(numbers));
			break;
		case 2:
			System.out.printf("\nDas Produkt Ihrer eingegebenen Zahlen ist %d \n", util.Array.prod(numbers));
			break;
		case 3:
			System.out.printf("\nDas Minimum Ihrer eingegebenen Zahlen ist %d \n", util.Array.min(numbers));
			break;
		case 4:
			System.out.printf("\nDas Maximum Ihrer eingegebenen Zahlen ist %d \n", util.Array.max(numbers));
			break;
		}
		
		return mathMenu(numbers);
	}
	
	
	public static void start() {
		explainProgram();
		while (true) {
			int[] numbers = getNumbers();
			displayNumbers(numbers);
			if (!menu(numbers)) {
				return;
			}
		}
	}
}
