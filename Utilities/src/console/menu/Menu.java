package console.menu;

import console.input.Input;

public class Menu {

	public static Builder builder() {
		return new Builder();
	}

	public static boolean shouldExit() {
		int choice = builder().show();
		return choice == 0;
	}

	public static int basic(String[] menu, Integer[] options) {
		return builder().
				menu(menu).
				options(options).
				show();
	}

	public static void loadingBar(int dotAmount, int loadTime, boolean shouldPrintSkipping) {
		Display.loadingBar(dotAmount, loadTime, shouldPrintSkipping);
		Input.clearScannerCache();
	}

	/**
	 * returns 0 if user pressed enter without an input
	 */
	public static int getSelection(Config config) {
		Integer choice = Input.builder()
				.typeInteger()
				.prompt(config.prompt)
				.error(config.error)
				.shouldFormat(config.shouldFormat)
				.stopOnEmptyLine()
				.matchingValidation(config.options)
				.get();

		if (choice == null) {
			return (int) 0;
		}
		return (int) choice;
	}
}
