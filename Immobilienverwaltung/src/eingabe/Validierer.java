package eingabe;

import helfer.Funktion;

public class Validierer {
	
	// private static String Ausstiegsbedingung = "X";
	
	
	public static boolean isValidInput(String eingabe, Funktion<String, Boolean> valierungsFunk) {
		return valierungsFunk.apply(eingabe);
	}
	
	/*
	public static boolean ausstiegErwollt(String eingabe) {
		if (Ausstiegsbedingung.isBlank()) { return false; }
		if (eingabe.equalsIgnoreCase(Ausstiegsbedingung)) { return true; }
		return false;
	}
	*/
	
	public static Funktion<String, Boolean> valString() {
		// String als String validieren indem die Länge überprüft wird
		return eingabe -> {
			if (eingabe.length() > 100) { return false; }
			return true; 
			};
	}

	public static Funktion<String, Boolean> valInt() {
		// int validieren indem versucht wird den String als int zu parsen
		return eingabe -> {
			try {
				Integer.parseInt(eingabe);
				return true;
			} catch (Exception e) {
				return false;
			}
		};
	}
	
	public static Funktion<String, Boolean> valFloat() {
		// float validieren indem versucht wird den String als float zu parsen
		return eingabe -> {
			try {
				float nummer = Float.parseFloat(eingabe);
				if (nummer < 0 || nummer > 999999) { return false; }
				return true;
			} catch (Exception e) {
				return false;
			}
		};
	}

	public static Funktion<String, Boolean> valWertebereich(int min, int max) {
		return eingabe -> {
			// int validieren und parsen
			int nummer;
			try {
				nummer = Integer.parseInt(eingabe);
			} catch (Exception e) {
				return false;
			}
			
			// validieren ob der int in dem gegebenen Wertebereich ist
			return nummer >= min && nummer <= max;
		};
	}
}