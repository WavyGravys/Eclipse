package _main;

import eingabe.Eingabe;
import helfer.Box;
import helfer.Generell;
import helfer.Menue;
import helfer.SpeicherManager;

public class Hauptprogramm<T> {
	
	// Menüs
	private static String[] voreinstellungMenue = new String[] {
			"1 - Voreinstellungen",
			"0 - ENDE"
	};
	private static String[] startMenue = new String[] {
			"1 - Neuer Mietvertag",
			"2 - Voreinstellungen ändern",
			"3 - Neu Beginnen",
			"0 - ENDE"
	};
	private static String[] hauptMenue = new String[] {
			"1 - Neuer Mietvertag",
			"2 - Mietvertrag löschen",
			"3 - Vertrag Ändern",
			"4 - Aktuelle Vertragsliste",
			"5 - Statistik (gesamt)",
			"6 - Statistik (je Wohnung)",
			"7 - Voreinstellungen ändern",
			"8 - Neu beginnen",
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
	
	// SpeicherManager initialisieren
	public static SpeicherManager speicher = new SpeicherManager();
	// Input initialisieren
	@SuppressWarnings("rawtypes")
	private static Eingabe eingabe = new Eingabe();
	
	
	public static void main(String[] args) {
		boolean neuBeginn = false;
		while (true) {
			if (!neuBeginn) {
				erklärungDrucken();
			}
			
			// Voreinstellungen
			while (speicher.getVermieter().isBlank()) {
				// Auswahl bekommen
				byte auswahl = Menue.getAuswahl("Menü", voreinstellungMenue, !neuBeginn);
				neuBeginn = false;
				
				// Auswahl auswerten
				switch (auswahl) {
				case 1 -> voreinstellung();
				case 0 -> Generell.programmBeenden();
				}
			}
			
			// erster Vertrag
			while (speicher.getVertragsmaenge() == 0) {
				Box.trennerDrucken();
				
				System.out.println("\u2503 Kein Vertrag vorhanden.");
				
				// Auswahl bekommen
				byte auswahl = Menue.getAuswahl("Menü", startMenue);
				
				// Auswahl auswerten
				switch (auswahl) {
				case 1 -> neuerVertrag();
				case 2 -> voreinstellung();
				case 3 -> {
					speicher.loeschen();
					neuBeginn = true;
					break;
					}
				case 0 -> Generell.programmBeenden();
				}
				
				// programm "neustarten" falls "Neu Beginnen" ausgewählt wurde
				if (neuBeginn) { break; }
			}
			
			// Hauptschleife
			while (speicher.getVertragsmaenge() > 0) {
				// Auswahl bekommen
				byte auswahl = Menue.getAuswahl("Menü", hauptMenue);
				
				// Auswahl auswerten
				switch (auswahl) {
				case 1 -> neuerVertrag();
				case 2 -> speicher.vertragLöschen(Menue.getAuswahl("Wohnungsmenü", wohnungsMenue));
				case 3 -> vertragAendern();
				case 4 -> vertragslisteDrucken();
				case 5 -> gesamtstatistikDrucken();
				case 6 -> teilstatistikenDrucken();
				case 7 -> voreinstellung();
				case 8 -> {
					speicher.loeschen();
					break;
					}
				case 0 -> Generell.programmBeenden();
				}
			}
			
			// neubeginn, damit die Programmerklärung bei verlassen der Hauptschleife 
			// nicht nochmal gedruckt wird.
			neuBeginn = true;
		}
		
	}
	
	
	private static void erklärungDrucken() {
		System.out.print(
			  "In diesem Programm können Sie alle Ihrer Mietvertäge eintragen.\n"
			+ "Alle Ihrer Einnahmen und Ausgaben einsehen.\n"
			+ "...\n");
		// TODO
	}
	

	private static void voreinstellung() {
		// voreinstellungen bekommen und speichern
		speicher.setVermieter(eingabe.getString("\u2503 Name des Vermieters: ", false));
		/*
		Integer wohnungsmaenge = eingabe.getInRange("\u2503 Anzahl an Wohnungen: ", 1, 127, true, true);
		speicher.setWohnungsmaenge(wohnungsmaenge);
		if (wohnungsmaenge < speicher.getVertragsmaenge()) {
			speicher.setWohnungsmaenge(speicher.getVertragsmaenge());
			System.out.println("\u2503 Ihre angegebene Wohnungsanzahl "
					+ "ist unter der Anzhal der schon vorhandenen Verträge,\n"
					+ "also wurde sie auf diese menge ("+speicher.getVertragsmaenge()+") gesetzt.");
		}
		*/
		speicher.setStandardmieteProQdrM(eingabe.getFloat("\u2503 Standardmiete pro m² (€): ", true));		
	}
	
	
	private static void neuerVertrag() {
		// Informationen bekommen
		Byte auswahl = Menue.getAuswahl("Wohnungsmenü", wohnungsMenue);
		if (auswahl == 0) {
			// Bestätigung drucken
			System.out.println("\u2503 Vertrag wurde verworfen.");
			return;
		}
		String wohnung = wohnungsMenue[auswahl - 1];
		String mieter = eingabe.getString("\u2503 Name des/der Mieters/Mieterin: ", false);
		String tempVermieter = eingabe.getString("\u2503 Vermieter/in: ", true);
		String vermieter = tempVermieter == null ? speicher.getVermieter() : tempVermieter;
		float wohnflaeche = eingabe.getFloat("\u2503 Wohnfläche (m^2): ", false);
		int zimmerAnzahl = eingabe.getInRange("\u2503 Zimmeranzahl: ", 0, 127, false);
		Integer tempMiete = eingabe.getInRange("\u2503 Miete (€/Monat): ", 0, 999999, true);
		int miete = tempMiete == null ? (int) (speicher.getStandardmieteProQdrM() * wohnflaeche) : tempMiete;
		int vertragslaufzeit = eingabe.getInRange("\u2503 Vertragslaufzeit (Monate): ", 1, 999, false);
		int kostenStrom = eingabe.getInRange("\u2503 Stromkosten (€/Monat): ", 0, 999999, false);
		int KostenHeizung = eingabe.getInRange("\u2503 Heizungskosten (€/Monat): ", 0, 999999, false);
		int kostenWasser = eingabe.getInRange("\u2503 Wasserkosten (€/Monat): ", 0, 999999, false);
		int kostenInternet = eingabe.getInRange("\u2503 Internetkosten (€/Monat): ", 0, 999999, false);
		
		// Mietvertrag initialisieren
		Mietvertrag vertrag = new Mietvertrag(
				wohnung, mieter, vermieter, miete, vertragslaufzeit, 
				wohnflaeche, zimmerAnzahl,kostenStrom, KostenHeizung, 
				kostenWasser, kostenInternet);
		
		// Mietvertrag hinzufügen
		speicher.vertragHinzufügen(vertrag);
		
		// Bestätigung drucken
		System.out.println("\u2503 Vertrag wurde erfolgreich hinzugefügt!");
	}
	
	
	private static void vertragAendern() {
		
	}
	
	
	private static void vertragslisteDrucken() {
		// vertragsliste erstellen
		String[] vertragsliste = new String[speicher.getVertragsmaenge()];
		for (int i = 0; i < speicher.getVertragsmaenge(); i++) {
			vertragsliste[i] = speicher.getVertraege().get(i).wohnung;
		}
		
		// abteile variable für die box darstellung initialisieren
		String[][] abteile = new String[][] {new String[] {"Vertragsliste"}, vertragsliste};
		
		// vertragsliste drucken
		Box.drucken(abteile, new boolean[] {true, false}, true, true);
	}
	
	
	private static void gesamtstatistikDrucken() {
		// Gesamtstatistik bekommen
		int gesamtwohnflaeche = 0;
		int gesamtzimmerAnzahl = 0;
		int gesamteinkommen = 0;
		int gesamtausgaben = 0;
		int gesamtvertragslaufzeit = 0;
		int gesamtkostenStrom = 0;
		int gesamtkostenHeizung = 0;
		int gesamtkostenWasser = 0;
		int gesamtkostenInternet = 0;

		for (Mietvertrag vertrag : speicher.getVertraege()) {
			gesamtwohnflaeche += vertrag.getWohnflaeche();
			gesamtzimmerAnzahl += vertrag.getZimmerAnzahl();
			gesamteinkommen += vertrag.getMiete();
			gesamtausgaben += vertrag.getGesamtkosten();
			gesamtvertragslaufzeit += vertrag.getVertragslaufzeit();
			gesamtkostenStrom += vertrag.getKostenStrom();
			gesamtkostenHeizung += vertrag.getKostenHeizung();
			gesamtkostenWasser += vertrag.getKostenWasser();
			gesamtkostenInternet += vertrag.getKostenInternet();
		}
		
		int gesamtgewinn = gesamteinkommen - gesamtausgaben;
		float avgVertragslaufzeit = gesamtvertragslaufzeit / speicher.getVertragsmaenge();
		
		// Statistik Strings aufbauen
		String[][] gesamtStatistik = new String[4][];
		gesamtStatistik[0] = new String[] {"Gesamtstatistik"};
		gesamtStatistik[1] = new String[] {
				"Gesamte Wohnfläche: "+gesamtwohnflaeche+" m²",
				"Gesamte Zimmeranzahl: "+gesamtzimmerAnzahl,
				"Gesamtes Einkommen: "+gesamteinkommen+" €/Monat",
				"Gesamte Ausgaben: "+gesamtausgaben+" €/Monat",
				"Gesamter Gewinn: "+gesamtgewinn+" €/Monat",
				"Gesamte Vertragslaufzeit: "+gesamtvertragslaufzeit+"Monate",
				"Durchschnittliche Vertragslaufzeit: "+avgVertragslaufzeit+" Monate"};
		gesamtStatistik[2] = new String[] {"Teilkosten (Prozente ungenau)"};
		gesamtStatistik[3] = new String[] {
				"Strom: "+gesamtkostenStrom+" €/Monat ("+100/(gesamtausgaben/gesamtkostenStrom)+"%)",
				"Heizung: "+gesamtkostenHeizung+" €/Monat ("+100/(gesamtausgaben/gesamtkostenHeizung)+"%)",
				"Wasser: "+gesamtkostenWasser+" €/Monat ("+100/(gesamtausgaben/gesamtkostenWasser)+"%)",
				"Internet: "+gesamtkostenInternet+" €/Monat ("+100/(gesamtausgaben/gesamtkostenInternet)+"%)"};
		
		// Gesamtstatistik drucken
		Box.drucken(gesamtStatistik, new boolean[] {true, false, true, false}, true, true);
	}
	
	
	private static void teilstatistikenDrucken() {
		// Teilstatistiken bekommen
		String[][] teilStatistik = new String[speicher.getVertragsmaenge() + 1][];
		boolean[] zentriert = new boolean[speicher.getVertragsmaenge() + 1]; 
		teilStatistik[0] = new String[] {"Teilstatistiken (Prozente ungenau)"};
		zentriert[0] = true;
		
		int i = 1;
		for (Mietvertrag vertrag : speicher.getVertraege()) {
			teilStatistik[i] = new String[] {
					"Vermieter: "+vertrag.getVermieter(),
					"Mieter: "+vertrag.getMieter(),
					"Wohnfläche: "+vertrag.getWohnflaeche()+" m²",
					"Zimmeranzahl: "+vertrag.getZimmerAnzahl(),
					"Miete: "+vertrag.getMiete()+" €/Monat",
					"Vertragslaufzeit: "+vertrag.getVertragslaufzeit()+" Monate",
					"Gesamtkosten: "+vertrag.getGesamtkosten()+" €/Monat",
					"Stromkosten: "+vertrag.getKostenStrom()+" €/Monat ("+100/(vertrag.getGesamtkosten()/vertrag.getKostenStrom())+"%)",
					"Heizungkosten: "+vertrag.getKostenHeizung()+" €/Monat ("+100/(vertrag.getGesamtkosten()/vertrag.getKostenHeizung())+"%)",
					"Wasserkosten: "+vertrag.getKostenWasser()+" €/Monat ("+100/(vertrag.getGesamtkosten()/vertrag.getKostenWasser())+"%)",
					"Internetkosten: "+vertrag.getKostenInternet()+" €/Monat ("+100/(vertrag.getGesamtkosten()/vertrag.getKostenInternet())+"%)"};
			
			zentriert[i] = false;
			i ++;
		}
		
		
		// Teilstatistiken drucken
		Box.drucken(teilStatistik, zentriert, true, true);
		
		
		
		/*
		// Vertragsmenü erstellen
		ArrayList<String> vertragsMenü = new ArrayList<String>();
		for (int i = 0; i < speicher.vertragsmaenge; i++) {
			verträgeDictionary.get(wohnungsMenue[i]);
			vertragsMenü.add(wohnungsMenue[i]);
		}
		String[] x = vertragsMenü.toArray(new String[vertragsMenü.size()]);
		getVertragsMenue();
		
		// Vertragsauswahl bekommen
		byte vertragsauswahl = Menue.getAuswahl("Verträge", vertragsMenü);
		
		// drucken der wohnungsspezifischer Statistik
		wohnungsstatistikDrucken(vertragsliste[auswahl - 1]);
		*/
	}
}
