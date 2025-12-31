package console.menu;

import console.box.Box;
import console.box.Box.Type;
import console.output.ConsoleAnimations;

public class Display {

	public static void menu(Config config) {
		Box.builder()
				.lineType(Type.THICK_LINE)
				.newSection(config.header, true)
				.newSection(config.menuStrings, false)
				.printExt();
	}

	public static void loadingBar(int dotAmount, int loadTime, boolean shouldPrintSkipping) {
		int delay = (int) (loadTime / (double) dotAmount);
		String loadingBar = "[" + "═".repeat(dotAmount) + "]";
		ConsoleAnimations.type(loadingBar, (int) delay, 0, false);
	}
}
