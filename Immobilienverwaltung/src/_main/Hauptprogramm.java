package _main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import input.Input;
import menu.Menu;
import speicher.SpeicherManager;

public class Hauptprogramm {
	// ab 10 wohnungen fuzzy search insead of full menu?
	
	
	// Menüs
	private static String[] voreinstellungMenue = new String[] {
		"1 - ",
		"",
		""
	};
	private static String[] startMenue = new String[] {
			"1 - Neuer Mietvertag",
			"2 - Wohnungen auflisten",
			"0 - ENDE"
	};
	private static String[] hauptMenue = new String[] {
			"1 - Neuer Mietvertag",
			"2 - Aktuelle Vertragsliste",
			"3 - Statistik (gesamt)",
			"4 - Statistik (je Wohnung)",
			"5 - Neu beginnen",
			"0 - ENDE"
	};
	private static String[] wohnungsMenue = new String[] {
			"1 - Erdgeschoss links",
			"2 - Erdgeschoss mitte",
			"3 - Erdgescchoss rechts",
			"4 - Obergeschoss links",
			"5 - Obergeschoss mitte",
			"6 - Obergeschoss rechts",
			"7 - Dachgeschoss links",
			"8 - Dachgeschoss mitte",
			"9 - Dachgeschoss rechts",
			"0 - Abbrechen"
	};
	
	// 
	private static ArrayList<Mietvertrag> vertraege = new ArrayList<Mietvertrag>();
	private static int vertragsmaenge = 0;
	
	// generelle info
	private static String vermieter;
	private static int wohnungsmenge;
	private static float standardmieteProQdrM = 15.38f;
	
	public static void main(String[] args) {
		// speichermanager inizialisieren
		SpeicherManager speicher = new SpeicherManager("immobilienverwaltung");
		
		vertragsmaenge = vertraege.size();
		
		
		// Voreinstellungen bekommen falls der speicher leer ist
		if (vermieter.isBlank()) {
			// voreinstellungen
			speicher.voreinstellungenÜberschreiben(voreinstellung());
		}
		
		// Hauptschleife
		while (true) {
			// Hauptmenü auswahl bekommen
			byte auswahl = Menu.getAuswahl("Menü", hauptMenue);
			
			// Auswahl auswerten
			switch (auswahl) {
			case 1 -> neuerVertrag();
			case 2 -> vertragslisteDrucken();
			case 3 -> gesamtstatistikDrucken();
			case 4 -> teilstatistikDrucken();
			case 5 -> vertraege = new ArrayList<Mietvertrag>();
			case 0 -> programmBeenden();
			}
			
			// Änderungen speichern
			speicherVertraege.überschreiben(vertraege);
		}
	}
	
	
	private static void voreinstellung() {
		vermieter = Input.getString("Name des Vermieters: ");
		wohnungsmenge = Input.getInt("Anzahl an Wohnungen: ");
		System.out.println("Drücken Sie Enter um zu überspringen.");
		standardmieteProQdrM = Input.getInt("Standardmiete pro m² (€): ");
		
		
		byte auswahl = Menu.getAuswahl("Menü", startMenue);
	}
	
	
	private static void neuerVertrag() {
		// Informationen bekommen
		String wohnung = wohnungsMenue[Menu.getAuswahl("Wohnungsmenü", hauptMenue) - 1];
		String mieter = Input.getString("Name des/der Mieters/in: ");
		//String vermieter = Input.getString("Ihr Name: ");
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
	
	private static void teilstatistikDrucken() {
		// Vertragsmenü erstellen
		ArrayList<String> vertragsMenü = new ArrayList<String>();
		for (int i = 0; i < vertragsmaenge; i++) {
			verträgeDictionary.get(wohnungsMenue[i]);
			vertragsMenü.add(wohnungsMenue[i]);
		}
		String[] x = vertragsMenü.toArray(new String[vertragsMenü.size()]);
		getVertragsMenue();
		
		// Vertragsauswahl bekommen
		byte vertragsauswahl = Menu.getAuswahl("Verträge", vertragsMenü);
		
		// drucken der wohnungsspezifischer Statistik
		wohnungsstatistikDrucken(vertragsliste[auswahl - 1]);
	}
	
	private static void wohnungsstatistikDrucken(Mietvertrag wohnung) {
		
	}
	
	
	private static void programmBeenden() {
		Speicherer.überschreiben(verträgeDictionary);
	}
}
