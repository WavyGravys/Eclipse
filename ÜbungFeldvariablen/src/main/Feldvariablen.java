package main;

import java.util.Scanner;
import java.util.ArrayList;


public class Feldvariablen {
	
	static final Scanner scan = new Scanner(System.in);
	
	// settings
	static final byte numColumns = 10;
	static final byte xOffset = 4;
	static final byte delimeterSpaces = 1;
	static final boolean isDynamicSpacing = false;
	static final String errorMessage = "ERROR: \"%s\" ist keine valide Eingabe. Versuchen Sie es erneut \n";
	
	
	private static ArrayList<Integer> getNumbers() {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		while (true) {
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
		for (int index=0; index<numberAmount+1; index++) {
			int numDigits = String.valueOf(numbers.get(index)).length();
			if (highestNumDigits < numDigits) {
				highestNumDigits = numDigits;
			}
		}
		return highestNumDigits;
	}
	
	
	private static void displayNumbers(ArrayList<Integer> numbers, int numberAmount) {
		System.out.print("\n[\n");
		
		int[] columnWidths = null;
		
		if (isDynamicSpacing) {
			columnWidths = getIndividualColumnWidths(numbers, numberAmount);
			
		} else {
			int spacing = getSpacing(numbers, numberAmount);
		}
		
		for(int index=0; index<numberAmount; index++) {
			if(index%numColumns==0) {
				if (index!=0) {
					System.out.print("\n");
				}
				for (int i=0; i<xOffset; i++) {
					System.out.print(" ");
				}
			}
			
			if(index==numberAmount-1) {
				System.out.printf("%d",numbers.get(index)); 
			} else {
				System.out.printf("%d",numbers.get(index));
				int numDigits = String.valueOf(numbers.get(index)).length();
				int columnWidth = columnWidths[index%numColumns];
				for (int m=0; m<(columnWidth-numDigits); m++) {
					System.out.print(" ");
				}
				System.out.print(",");
				for (int m=0; m<delimeterSpaces; m++) {
					System.out.print(" ");
				}	
			}
		}
	}
	
	
	private static Integer menu(int[] acceptedMenuOptions) {
		int acceptedMenuOptionsAmount = acceptedMenuOptions.length;
		
		System.out.print("\n\n - Menü - \n");
		System.out.print("Erneute Eingabe - 1 \n");
		System.out.print("Programm Schließen - 0 \n");
		
		while (true) {
			String input = scan.nextLine();
			try {
				int choice = Integer.parseInt(input);
				
				boolean isCorrect = false;
				for (int index=0; index<acceptedMenuOptionsAmount; index++) {
					if (choice == acceptedMenuOptions[index]) {
						isCorrect = true;
						break;
					}
				}
				if (isCorrect)
					return choice;
			
			} catch (Exception e) {
				System.out.printf(errorMessage, input);
			}
			
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		while (true) {
			ArrayList<Integer> numbers = getNumbers();
			int numberAmount = numbers.size();
			
			System.out.print("Geben Sie Ihre Nummern ein!");
			System.out.print("Entweder mit Lehrzeichen oder mit Zeilenumbruch (Enter) getrennt"); //todo: spaces
			
			displayNumbers(numbers, numberAmount);
			
			scan.nextLine();
			
			int[] acceptedMenuOptions = {0,1};
			int menuOutput = menu(acceptedMenuOptions);
			switch (menuOutput) {
			case 0:
				System.out.print("Programm wurde geschlossen!");
				return;
			case 1:
				break;
			}
		}
	}
	
	
	
	public static void mai(String[] args) throws InterruptedException {
		
		while (true) {
			ArrayList<Integer> numbers = new ArrayList<Integer>();
			boolean cont = true;
			int i = 0;
			while (cont==true) {
				try {
					System.out.printf("%d. Zahl: ", i+1);
					String input = scan.nextLine();
					
					if(input.equals("")) {
						throw new Exception("enter_pressed");
					} else {
						numbers.add(Integer.parseInt(input));
					}
				} catch(Exception e) {
					System.out.print("\n[\n");
					
					// initialize columnWidths with zeroes
					int columnWidths[] = new int[numColumns];
					for (int n=0; n<numColumns; n++) {
						columnWidths[n] = 0;
					}
					
					for (int n=0; n<i; n++) {
						int collumn = n%numColumns;
						int numDigits = String.valueOf(numbers.get(n)).length();
						if (columnWidths[collumn] < numDigits) {
							columnWidths[collumn] = numDigits;
						}
					}
					
					for(int n=0; n<i; n++) {
						if(n%numColumns==0) {
							if (n!=0) {
								System.out.print("\n");
							}
							for (int m=0; m<xOffset; m++) {
								System.out.print(" ");
							}
						}
						if(n==i-1) {
							System.out.printf("%d",numbers.get(n)); 
						} else {
							System.out.printf("%d",numbers.get(n));
							int numDigits = String.valueOf(numbers.get(n)).length();
							int columnWidth = columnWidths[n%numColumns];
							for (int m=0; m<(columnWidth-numDigits); m++) {
								System.out.print(" ");
							}
							System.out.print(",");
							for (int m=0; m<delimeterSpaces; m++) {
								System.out.print(" ");
							}	
						}
					}
					System.out.print("\n]");
					cont = false;
				}
				i++;			
			}
			
			/* -- repeat code -- */
			scan.nextLine();
			System.out.print("\n\n - Menü - \n");
			System.out.print("Erneute Eingabe - 1 \n");
			System.out.print("Programm Schließen - 0 \n");
			
			while (true) {
				String input = scan.nextLine();
				try {
					int choice = Integer.parseInt(input);
					if (choice == 0) {
						System.out.print("Geschlossen!");
						scan.close();
						return;
					} else if (choice == 1) {
						break;
					}
				} catch (Exception e) {}
				
//				String errorMessage = "ERROR: \"%s\" ist keine valide Eingabe. Versuchen Sie es erneut \n";
				System.out.printf(errorMessage, input);
			}
			/* -- repeat code -- */
		}
	}
}
