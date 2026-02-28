package helfer;

public class Box {
	
	private static final char[] ecken = new char[] {'\u250F', '\u2513', '\u2517', '\u251B'};
	private static final char[] abspaltungen = new char[] {'\u2520', '\u2528'};
	private static final char vertikaleLinie = '\u2503';
	private static final char horizontaleLinie = '\u2501';
	private static final char horizontaleAbschnittlinie = '\u2500';
	private static final char abspaltungErw = '\u2523';
	
	
	public static void trennerDrucken() {
		System.out.print(abspaltungErw);
		for (int i = 0; i < 29; i++) {
			System.out.print(horizontaleLinie);
		}
		System.out.println("\u252B");
	}
	
	// Haupt-Ausgabefunktion
	public static void drucken(String[][] abschnitte, boolean[] zentriert, 
							   boolean vorErweitert, boolean nachErweitert) {
		// Breite bekommen
		int breite = breiteBerechnen(abschnitte);
		
		// Inhalt drucken
		anfangOderEndZeileDrucken(breite, vorErweitert, true);
		abschnitteDrucken(abschnitte, zentriert, breite);
		anfangOderEndZeileDrucken(breite, nachErweitert, false);
	}
	
	
	// overload falls man keine erweiterungen will
	public static void drucken(String[][] abschnitte, boolean[] zentriert) {
		drucken(abschnitte, zentriert, false, false);
	}
	
	
	// vorkonfigurierte basis Methoden
	public static void basic(String ueberschrift, String[] abschnitt, boolean zentriert, boolean isErstesMenue) {
		drucken(new String[][] {{ueberschrift}, abschnitt}, new boolean[] {true, zentriert}, !isErstesMenue, true);
	}
	
	
	// overload falls man den abschnitt nicht zentrieren möchte
	public static void basic(String ueberschrift, String[] abschnitt, boolean zentriert) {
		drucken(new String[][] {{ueberschrift}, abschnitt}, new boolean[] {true, false});
	}
	
	
	// private funktionen
	
	private static void abschnitteDrucken(String[][] abschnitte, boolean[] zentriert, int breite) {
		int anzahlAbschnitte = abschnitte.length;
		for (int i = 0; i < anzahlAbschnitte; i++) {
			String[] abschnitt = abschnitte[i];
			
			abschnittDrucken(breite, abschnitt.length + 2, abschnitt, zentriert[i]);
			
			if (i != anzahlAbschnitte - 1) {
				abspaltungDrucken(breite);
			}
		}
	}
	
	
	private static int breiteBerechnen(String[][] abschnitte) {
		int breite = 0;
		for (String[] abschnitt : abschnitte) {
			for (String zeile : abschnitt) {
				int zeilenlaenge = zeile.length();
				if (breite < zeilenlaenge) {
					breite = zeilenlaenge;
				}
			}
		}
		return breite;
	}
	
	
	private static void anfangOderEndZeileDrucken(int breite, boolean erweitert,  boolean isErsteZeile) {
		int ecke = isErsteZeile ? 0 : 2;
		if (erweitert) {
			System.out.print(abspaltungErw);
		} else {
			System.out.print(ecken[ecke]);
		}
		
		horizontaleLinieDrucken(breite);
		System.out.println(ecken[ecke + 1]);
	}
	
	
	private static void abschnittDrucken(int breite, int hoehe, String[] abschnitt, boolean isZentriert) {
		if (abschnitt.length == 0 || abschnitt == null) {
			for (int y = 0; y < hoehe - 2; y++) {
				lehreInhaltszeileDrucken(breite);
			}
		} else {
			for (int y = 0; y < hoehe - 2; y++) {
				inhaltszeileDrucken(breite, abschnitt[y], isZentriert);
			}
		}
	}
	
	
	private static void lehreInhaltszeileDrucken(int breite) {
		System.out.print(vertikaleLinie);
		Drucken.lehrzeichen(breite + 1);
		System.out.println(vertikaleLinie);
	}
	
	
	private static void horizontaleLinieDrucken(int breite) {
		for (int x = 1; x < breite + 2 + 2 - 1; x++) {
			System.out.print(horizontaleLinie);
		}
	}
	
	
	private static void horizontaleAbschnittlinieDrucken(int breite) {
		for (int x = 1; x < breite + 2 + 2 - 1; x++) {
			System.out.print(horizontaleAbschnittlinie);
		}
	}
	
	
	private static void inhaltszeileDrucken(int breite, String inhaltszeile, boolean zentriert) {
		int lehrzeichenVor = 1;
		int lehrzeichenNach = breite - inhaltszeile.length() + 1;
		if (zentriert) {
			int lehrzeichen = lehrzeichenVor + lehrzeichenNach;
			lehrzeichenVor = lehrzeichen / 2;
			lehrzeichenNach = lehrzeichen / 2;
			if (lehrzeichen % 2 != 0) lehrzeichenNach++;
		}
		System.out.print(vertikaleLinie);
		Drucken.lehrzeichen(lehrzeichenVor);
		System.out.print(inhaltszeile);
		Drucken.lehrzeichen(lehrzeichenNach);
		System.out.println(vertikaleLinie);
	}
	
	
	private static void abspaltungDrucken(int breite) {
		System.out.print(abspaltungen[0]);
		horizontaleAbschnittlinieDrucken(breite);
		System.out.println(abspaltungen[1]);
	}
}
