package console.output;

import console.box.Box;
import util.TimeUtils;

public class ProgramMessages {
	
	public static void printSpaces(int n) {
		System.out.print(" ".repeat(n));
	}
	
	public static boolean printWelcomeMessage() {
		if (!ConsoleAnimations.type("[══════] Wilkommen [══════]", 17, 50, false)) { return false;}
		System.out.println(" (drücken Sie Enter um zu Skippen)");
		TimeUtils.sleep(100);
		return true;
	}
	
	public static void printWelcomeMessageBoxed(String[] Strings) {
		Box.builder()
				.lineType(Box.Type.THICK_LINE)
				.newSection(Strings, true)
				.printWait(2000);
	}
	
	public static void explainProgram(String[] explainLines) {
		// if (printFunc) { return; } is the exit check for Skipped
		
		if (!printWelcomeMessage()) {
			System.out.println();
			return;
		}
		
		if (!ConsoleAnimations.typeLines(explainLines, 15, 50, 200, true)) {
			System.out.println();
			return;
		}
		
		if (!ConsoleAnimations.type("3 2 1 - ", 125, 125, true)) {
			System.out.println(); 
			return; 
		}
		if (!ConsoleAnimations.type("viel Spaß!", 25, 50, true)) {
			System.out.println();
			return;
		}
		
		System.out.print("\n\n");
	}
	
	public static void closeProgram() {
		System.out.print("Programm wird geschlossen");
		ConsoleAnimations.type(" . . .", 150, 150, true);
	}
}
