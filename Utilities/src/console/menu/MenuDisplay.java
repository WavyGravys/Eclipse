package console.menu;

import console.output.ConsoleAnimations;

public class MenuDisplay {
	
	public static void displayMenu(MenuConfig config) {
        ConsoleAnimations.typeln(config.menuStrings[0], config.typeDelay, 0, config.lineDelay, false);
        ConsoleAnimations.linesWithDelay(config.menuStrings, config.lineDelay, 1);
    }
	
	public static void loadingBar(int dotAmount, int loadTime, boolean shouldPrintSkipping) {
        int delay = (int) (loadTime / (double) dotAmount);
        String loadingBar = "[" + "═".repeat(dotAmount) + "]";
        ConsoleAnimations.type(loadingBar, (int) delay, 0, false);
    }
}
