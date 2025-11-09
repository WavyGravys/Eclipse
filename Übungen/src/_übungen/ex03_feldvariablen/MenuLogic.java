package _übungen.ex03_feldvariablen;

import console.menu.Menu;
import util.ArrayUtils;

public class MenuLogic {
	private static final String[] menu = new String[] {
			"[═══════] Menü [═══════]", "1 - Erneute Eingabe",
			"2 - Mathe Operationen", "0 - ZURÜCK"};
	private static final String[] mathMenu = new String[] {
			"[═════] Arraymenü [═════]", 
			" 1 - Summe", " 2 - Produkt", 
			" 3 - Minimum", " 4 - Maximum", 
			" 0 - ZURÜCK"
	};

	public static boolean menu(int[] numbers) {
		int menuChoice = getChoice(menu, 3);
		switch (menuChoice) {
		case 0: return false;
		case 1: return true;
		case 2: return mathMenu(numbers);
		default: throw new IllegalArgumentException(menuChoice + "out of bounds: ");
		}
	}
	
	private static boolean mathMenu(int[] numbers) {
		int choice = getChoice(mathMenu, 5);
		if (choice == 0) { return menu(numbers); }
		
		String result = calculateChoice(choice, numbers);
		displayResult(result);
		
		return mathMenu(numbers);
	}
	
	
	private static int getChoice(String[] menuStrings, int options) {
		return Menu.basic(menuStrings, ArrayUtils.integerRange(options));
	}
	
	private static String calculateChoice(int choice, int[] numbers) {
		// TODO: add clamping/scientific notation for large numbers.
		String base = "Ihrer eingegebenen Zahlen ist "; 
		String result = switch (choice) {
		case 1 -> ("Die Summe" + base + ArrayUtils.sumInt(numbers));
		case 2 -> ("Das Produkt" + base + ArrayUtils.prodInt(numbers));
		case 3 -> ("Das Minimum" + base + ArrayUtils.minInt(numbers));
		case 4 -> ("Das Maximum" + base + ArrayUtils.maxInt(numbers));
		default -> throw new IllegalArgumentException("Unexpected value: " + choice);
		};
		
		return result + ".";
	}
	
	private static void displayResult(String result) {
		System.out.println(result);
		Menu.loadingBar(result.length() - 3, 1200, false);
		System.out.print("\n\n");
	}
}
