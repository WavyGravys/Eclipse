package helfer;

import _main.Hauptprogramm;

public class Generell {
	
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
	
	
	public static void programmBeenden() {
		// speichern
		Hauptprogramm.speicher.ueberschreiben();
		
		// Bestätigung drucken
		System.out.println("\u2503 Programm wurde geschlossen.");
		
		// Programm schließen
		System.exit(0);
	}
}
