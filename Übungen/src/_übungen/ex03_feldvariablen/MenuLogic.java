package _übungen.ex03_feldvariablen;

import console.menu.Menu;
import math.MathUtils;
import util.ArrayUtils;

public class MenuLogic {
	private static final String[] menu = new String[] {
			"[═══════] Menü [═══════]", 
			" 1 - Zahlen hinzufügen",
			" 2 - Zahlen anzeigen",
			" 3 - Zahlen sortieren (ansteigend)",
			" 4 - Zahlen sortieren (absteigend)",
			" 5 - Mathe Operationen", 
			" 6 - Erneute Eingabe",
			" 0 - ZURÜCK"};
	private static final String[] mathMenu = new String[] {
			"[═════] Arraymenü [═════]", 
			" 1 - Summe", 
			" 2 - Produkt", 
			" 3 - Minimum", 
			" 4 - Maximum", 
			" 5 - Durschnitt",
			" 0 - ZURÜCK"
	};
	public static enum MenuState {ADD, CONTINUE, EXIT};
	public MenuState menuState;
	
	
	public int[] mainMenu(int[] numbers) {
		int choice = getChoice(menu, 7);
		return switch (choice) {
		case 0 -> {
			menuState = MenuState.EXIT;
			yield numbers;
		}
		case 1 -> {
			menuState = MenuState.ADD;
			yield numbers;
		}
		case 2 -> {
			NumberDisplay.display(numbers);
			yield mainMenu(numbers);
		}
		case 3, 4 -> {
			numbers = ArrayUtils.sortInt(numbers, choice == 3);
			NumberDisplay.display(numbers);
			yield mainMenu(numbers);
		}
		case 5 -> {
			yield mathMenu(numbers);
		}
		case 6 -> {
			menuState = MenuState.CONTINUE;
			yield numbers;
		}
		default -> {
			throw new IllegalArgumentException("Unexpected value: " + choice);
		}
		};
	}
	
	private int[] mathMenu(int[] numbers) {
		int choice = getChoice(mathMenu, 6);
		if (choice == 0) {
			return mainMenu(numbers);
		}
		
		String result = calculateChoice(choice, numbers);
		displayResult(result);
		
		return mathMenu(numbers);
	}
	
	
	private static int getChoice(String[] menuStrings, int options) {
		return Menu.basic(menuStrings, ArrayUtils.integerRange(options));
	}
	
	private static String calculateChoice(int choice, int[] numbers) {
		String base = "Ihrer eingegebenen Zahlen ist ["; 
		String result = switch (choice) {
		case 1 -> ("Die Summe" + base + ArrayUtils.sumInt(numbers));
		case 2 -> ("Das Produkt" + base + ArrayUtils.prodInt(numbers));
		case 3 -> ("Das Minimum" + base + ArrayUtils.minInt(numbers));
		case 4 -> ("Das Maximum" + base + ArrayUtils.maxInt(numbers));
		case 5 -> ("Der Durschschnitt" + base + (MathUtils.roundTo(ArrayUtils.avgInt(numbers), 2)));
		default -> throw new IllegalArgumentException("Unexpected value: " + choice);
		};
		
		return result + "]";
	}
	
	private static void displayResult(String result) {
		// TODO: add clamping/scientific notation for large numbers.
		System.out.println(result);
		Menu.loadingBar(result.length() - 3, 2000, false);
		System.out.print("\n\n");
	}
}
