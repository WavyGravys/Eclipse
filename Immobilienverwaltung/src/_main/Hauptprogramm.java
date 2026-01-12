package _main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hauptprogramm {
	
	// voreinstellungen mit menge von wohnungen und namen dieser wohnungen
	// ab 10 wohnungen fuzzy search insead of full menu?
	
	
	// Menüs
	private static String[] startMenü = new String[] {
			"1 - Neuer Mietvertag",
			"0 - ENDE"
	};
	private static String[] hauptMenü = new String[] {
			"1 - Neuer Mietvertag",
			"2 - Aktuelle Vertragsliste",
			"3 - Statistik (gesamt)",
			"4 - Statistik (je Wohnung)",
			"5 - Neu beginnen",
			"0 - ENDE"
	};
	private static String[] wohnungsMenü = new String[] {
			"1 - Erdgeschoss links",
			"2 - Erdgeschoss mitte",
			"3 - Erdgescchoss rechts",
			"4 - Obergeschoss links",
			"5 - Obergeschoss mitte",
			"6 - Obergeschoss rechts",
			"7 - Dachgeschoss links",
			"8 - Dachgeschoss mitte",
			"9 - Dachgeschoss rechts",
	};
	
	// 
	private static Map<String, Mietvertrag> verträgeDictionary = new HashMap<>();
	private static byte vertragsmänge = 0;
	
	
	public static void main(String[] args) {
		verträgeDictionary = Speicherer.auslesen();
		
		// Simpleres Menü, falls keine verträge gespeichert waren
		if (vertragsmänge == 0) {
			byte auswahl = Menü.simpel("Menü", startMenü);
		}
		
		// Hauptschleife
		while (true) {
			// Hauptmenü auswahl bekommen
			byte auswahl = Menü.simpel("Menü", hauptMenü);
			
			// Auswahl auswerten
			switch (auswahl) {
			case 1:
				neuerVertrag();
				break;
			case 2:
				vertragslisteDrucken();
				break;
			case 3:
				gesamtstatistikDrucken();
				break;
			case 4:
				// Vertragsmenü erstellen
				ArrayList<String> vertragsMenü = new ArrayList<String>();
				for (int i = 0; i < vertragsmänge; i++) {
					verträgeDictionary.get(wohnungsMenü[i]);
					vertragsMenü.add(wohnungsMenü[i]);
				}
				String[] x = vertragsMenü.toArray(new String[vertragsMenü.size()]);
				
				// Vertragsauswahl bekommen
				byte vertragsauswahl = Menü.simpel("Verträge", vertragsMenü);
				
				// drucken der wohnungsspezifischer Statistik
				wohnungsstatistikDrucken(vertragsliste[auswahl - 1]);
				break;
			case 5:
				verträgeDictionary = new HashMap<>();
				vertragsmänge = 0;
				break;
			case 0:
				Speicherer.überschreiben(verträgeDictionary);
				return;
			}
			
			// Änderungen speichern
			Speicherer.überschreiben(vertragsliste);
		}
	}
	
	
	private static void neuerVertrag() {
		// Informationen bekommen
		String wohnung = wohnungsMenü[Menü.simpel("Wohnungsmenü", hauptMenü) - 1];
		String mieter = Input.getString("Name des/der Mieters/in: ");
		String vermieter = Input.getString("Ihr Name: ");
		int miete = Input.getInt("Miete (€/Monat): ");
		int vertragslaufzeit = Input.getInt("Vertragslaufzeit (Monate): ");
		float wohnfläche = Input.getFloat("Wohnfläche (m^2): ");
		int zimmerAnzahl = Input.getInt("Zimmerantahl: ");
		int kostenStrom = Input.getInt("Stromkosten (€): ");
		int KostenHeizung = Input.getInt("Heizungskosten (€): ");
		int kostenWasser = Input.getInt("Wasserkosten (€): ");
		int kostenInternet = Input.getInt("Internetkosten (€): ");
		
		// Mietvertrag bekommen
		Mietvertrag vertrag = new Mietvertrag(wohnung, mieter, vermieter, 
				miete, vertragslaufzeit, wohnfläche, zimmerAnzahl,
				kostenStrom, KostenHeizung, kostenWasser, kostenInternet);
		verträgeDictionary.put(wohnung, vertrag);
	}
	
	
	private static void vertragslisteDrucken() {
		
	}
	
	
	private static void gesamtstatistikDrucken() {
		
	}
	
	
	private static void wohnungsstatistikDrucken(Mietvertrag wohnung) {
		
	}

}
