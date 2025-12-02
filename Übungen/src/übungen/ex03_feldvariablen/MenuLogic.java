package übungen.ex03_feldvariablen;

import console.menu.Menu;
import math.MathUtils;
import util.ArrayUtils;

public class MenuLogic {
	private static final String[] firstMenu = new String[] {
			"[═══════] Menü [═══════]",
			" 1 - Zahlen eingeben",
			" 0 - ZURÜCK" };
	private static final String[] menu = new String[] { 
			"[═══════] Menü [═══════]", 
			" 1 - Zahlen hinzufügen",
			" 2 - Zahlen anzeigen",
			" 3 - Zahlen sortieren (ansteigend)", 
			" 4 - Zahlen sortieren (absteigend)",
			" 5 - Mathe Operationen", 
			" 6 - Zahlen löschen", 
			" 0 - ZURÜCK" };
	private static final String[] mathMenu = new String[] { 
			"[═════] Arraymenü [═════]", 
			" 1 - Summe", 
			" 2 - Produkt",
			" 3 - Minimum", 
			" 4 - Maximum", 
			" 5 - Durschnitt", 
			" 0 - ZURÜCK" };

	public static enum MenuState {
		ADD, CONTINUE, EXIT
	};

	public MenuState menuState = MenuState.EXIT;
	// starts at EXIT to avoid NullException crash if used incorrectly
	
	public int firstMenu() {
		int choice = Menu.basic(firstMenu, ArrayUtils.integerRange(2));
		if (choice == 1) {
			menuState = MenuState.ADD;
		}
		return choice;
	}
	
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
			Display.display(numbers);
			yield mainMenu(numbers);
		}
		case 3, 4 -> {
			numbers = ArrayUtils.quicksort(numbers, choice == 3);
			Display.display(numbers);
			yield mainMenu(numbers);
		}
		case 5 -> {
			yield mathMenu(numbers);
		}
		case 6 -> {
			menuState = MenuState.CONTINUE;
			Display.string("Zahlen wurden gelöscht.");
			yield new int[0];
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
		Display.string(result);

		return mathMenu(numbers);
	}

	private static int getChoice(String[] menuStrings, int options) {
		return Menu.basic(menuStrings, ArrayUtils.integerRange(options));
	}

	private static String calculateChoice(int choice, int[] numbers) {
		String base = "Ihrer eingegebenen Zahlen ist [";
		String result = switch (choice) {
		case 1 -> ("Die Summe" + base + ArrayUtils.sum(numbers));
		case 2 -> ("Das Produkt" + base + ArrayUtils.prod(numbers));
		case 3 -> ("Das Minimum" + base + ArrayUtils.min(numbers));
		case 4 -> ("Das Maximum" + base + ArrayUtils.max(numbers));
		case 5 -> ("Der Durschschnitt" + base + (MathUtils.roundTo(ArrayUtils.avg(numbers), 2)));
		default -> throw new IllegalArgumentException("Unexpected value: " + choice);
		};

		return result + "]";
	}
}
