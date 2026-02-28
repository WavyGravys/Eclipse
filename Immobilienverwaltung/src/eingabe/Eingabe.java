package eingabe;

import helfer.Funktion;
import helfer.Generell;

public class Eingabe<T> {
	
	// einstellungen
	private static String fehler = "\u2503 ERROR: \"%s\" ist keine valide Eingabe.\n";
	private static boolean sollFormatieren = false;
	
	
	// benutzerfreundliche explizite Methoden
	public String getString(String aufforderung, boolean skipbar) {
		String rückgabe = get(aufforderung, Validierer.valString(), true, skipbar, false);
		if (rückgabe == null) { return null; }
		return rückgabe;
	}
	
	public Integer getInt(String aufforderung, boolean skipbar) {
		String rückgabe = get(aufforderung, Validierer.valInt(), false, skipbar, false);
		if (rückgabe == null) { return null; }
		return Integer.parseInt(rückgabe);
	}
	
	public Float getFloat(String aufforderung, boolean skipbar) {
		String rückgabe = get(aufforderung, Validierer.valFloat(), false, skipbar, false);
		if (rückgabe == null) { return null; }
		return Float.parseFloat(rückgabe);
	}
	
	public Integer getInRange(String aufforderung, int min, int max, boolean skipbar, boolean isMenue) {
		String rückgabe = get(aufforderung, Validierer.valWertebereich(min, max), false, skipbar, isMenue);
		if (rückgabe == null) { return null; }
		return Integer.parseInt(rückgabe);
	}
	
	// overload
	public Integer getInRange(String aufforderung, int min, int max, boolean skipbar) {
		String rückgabe = get(aufforderung, Validierer.valWertebereich(min, max), false, skipbar, false);
		if (rückgabe == null) { return null; }
		return Integer.parseInt(rückgabe);
	}
	
	
	// Haupt-Inputfunktion
	private String get(String aufforderung, Funktion<String, Boolean> validierungsFunk, 
			boolean isString, boolean skipbar, boolean isMenue) {

		while (true) {
			// Aufforderung drucken
			System.out.print(aufforderung);
			
			// Eingabe einlesen
			String eingabe = Leser.zeileEinlesen();
			
			// 
			if (eingabe == "") {
				if (skipbar) {
					if (!isMenue) {
						System.out.println("\u2503 Standardwert / geschlussfolgerter Wert wurde hergenommen.");
					}
					return null;
				} else {
					System.out.println("\u2503 Dieses Feld ist nicht überspringbar.");
					continue;
				}
			}
			
			// schauen ob User aus der eingabe exiten will
			//if (Validierer.ausstiegErwollt(eingabe)) { return null; }
			
			// Eingabe formatieren falls nötig und gewollt
			String formatierteEingabe = sollFormatieren && !isString ? eingabe.replaceAll("[^0-9, xX]", "") : eingabe;
			
			// Eingabe validieren und wiedergeben oder einen Fehler drucken und neu anfangen
			if (Validierer.isValidInput(formatierteEingabe, validierungsFunk)) {
				return formatierteEingabe;
			} else {
				String clamped = Generell.clampString(eingabe, 30);
				System.out.printf(fehler, clamped);
			}
		}
	}
	
}
