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
	static final String errorMessage = "ERROR: \"%s\" ist keine valide Eingabe. Versuchen Sie es erneut \n";
	
	
	private static ArrayList<Integer> getNumbers() {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		System.out.println("Geben Sie Ihre Nummern ein!");
		System.out.println("Entweder mit Lehrzeichen oder mit Zeilenumbruch (Enter) getrennt"); //todo: spaces
		
		while (true) {
			System.out.print("Eingabe: ");
			String input = scan.nextLine();
			if(input.equals("")) 
				return numbers;
			try {
				numbers.add(Integer.parseInt(input));
			} catch(Exception e) {
				System.out.printf(errorMessage, input);
				continue;
			}
		}
	}
	
	
	private static int[] getIndividualColumnWidths(ArrayList<Integer> numbers, int numberAmount) {
		int columnWidths[] = new int[numColumns];
		for (int column=0; column<numColumns; column++) {
			columnWidths[column] = 0;
		}
		for (int index=0; index<numberAmount; index++) {
			int collumn = index%numColumns;
			int numDigits = String.valueOf(numbers.get(index)).length();
			if (columnWidths[collumn] < numDigits) {
				columnWidths[collumn] = numDigits;
			}
		}
		return columnWidths;
	}
	
	
	private static int getSpacing(ArrayList<Integer> numbers, int numberAmount) {
		int highestNumDigits = 0;
		for (int index=0; index<numberAmount; index++) {
			int numDigits = String.valueOf(numbers.get(index)).length();
			if (highestNumDigits < numDigits) {
				highestNumDigits = numDigits;
			}
		}
		return highestNumDigits;
	}
	
	
	private static void displayNumbers(ArrayList<Integer> numbers) {
		int numberAmount = numbers.size();
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
			
			System.out.print(numbers.get(index));
			System.out.print(delimeter);
			
			// spacing
			int numDigits = String.valueOf(numbers.get(index)).length();
			int neededSpaces;
			if (isDynamicSpacing)
				neededSpaces = columnWidths[index%numColumns]-numDigits;
			else
				neededSpaces = spacing-numDigits;
			for (int m=0; m<(neededSpaces); m++) {
				System.out.print(" ");
			}
		}
		
		System.out.printf("%d",numbers.get(numberAmount-1)); 
		System.out.print("\n]\n");
	}
	
	
	public static void main(String[] args) {
		while (true) {
			ArrayList<Integer> numbers = getNumbers();
			
			displayNumbers(numbers);
			
			if (util.General.menu(null, null, null, null, null, null, null) == 0)
				return;
		}
	}
}
