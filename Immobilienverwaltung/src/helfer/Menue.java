package helfer;

import eingabe.Eingabe;

public class Menue {
	
	@SuppressWarnings("rawtypes")
	private static Eingabe input = new Eingabe();
	
	
	public static byte getAuswahl(String ueberschrift, String[] menue, boolean isErstesMenue) {
		// Menü drucken
		Box.basic(ueberschrift, menue, false, isErstesMenue);
		
		// Auswahl bekommen und zurückgeben
		Integer auswahl = input.getInRange("\u2503 Auswahl: ", 0, menue.length - 1, true, true);
		if (auswahl == null ) {
			return 0;
		} else {
			return auswahl.byteValue();
		}
	}
	
	
	// overload
	public static byte getAuswahl(String ueberschrift, String[] menue) {
		return getAuswahl(ueberschrift, menue, false);
	}
}
