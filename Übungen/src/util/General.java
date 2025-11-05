package util;

public class General {
	
	public static void sleep(int ms) {
		if (ms == 0) { return; }
		
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.err.println("Loading bar interrupted");
		}
		
		return;
	}
	
	public static void explainProgram(String[] explainLines) {
		// TODO: Drücken Sie Enter, um zu skippen.
		
		if (!Print.typeLines(explainLines, 15, 50, 200))
			return;
		General.sleep(100);
		if (!Print.type("3 2 1 - ", 150, 150))
			return;
		if (!Print.type("viel Spaß!", 80, 80))
			return;
		System.out.print("\n\n");
	}
	
	public static void closeProgram() {
		System.out.print("Programm wird geschlossen");
		Print.type(" . . .", 150, 150);
		System.exit(0);
	}
	
	public static void clearScannerCache() {
	    try {
	        while (System.in.available() > 0) {
	            System.in.read();
	        }
	    } catch (Exception e) {}
	}
	
	public static String clampString(String input, int maxLength) {
    	if (input.length() > maxLength) {
    		input = input.substring(0, maxLength - 4);
    		char lastChar = input.charAt(maxLength - 5);
    		
    		if (lastChar == ',') {
    			input = input  + " ";
    		}
    		
    		input = input + "...]";
    	}
    	
    	return input;
	}
}
