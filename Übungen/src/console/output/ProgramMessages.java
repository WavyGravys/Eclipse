package console.output;

import util.ArrayUtils;
import util.StringUtils;
import util.TimeUtils;

public class ProgramMessages {
	
	public static void spaces(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(" ");
		}
	}
	
	public static void explainProgram(String[] explainLines) {
		// if (printFunc) { return; } is the exit check for Skipped
		
		if (!ConsoleAnimations.typeLines(explainLines, 15, 50, 200, true)) { return; }
		
		TimeUtils.sleep(100);
		
		if (!ConsoleAnimations.type("3 2 1 - ", 150, 150, true)) { return; }
		if (!ConsoleAnimations.type("viel Spaß!", 80, 80, true)) { return; }
			
		System.out.print("\n\n");
	}
	
	public static void closeProgram() {
		System.out.print("Programm wird geschlossen");
		ConsoleAnimations.type(" . . .", 150, 150, true);
		System.exit(0);
	}
	
	public static void printParseConfirmation(Object[] parsed) {
    	String parsedNumbers = ArrayUtils.toString(parsed);
    	parsedNumbers = StringUtils.clampString(parsedNumbers, 30);
    	
    	System.out.print("Eingabe erfolgreich als ");
    	System.out.print(parsedNumbers);
    	System.out.print(" geparst.\n");
	}
	
	public static void printError(String input, String error) {
        String clamped = StringUtils.clampString(input, 30);
        System.out.printf(error, clamped);
    }
}
