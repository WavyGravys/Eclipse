package _main;

import helfer.Box;

public class Mietvertrag {
	
	// Initialisierung
	public String wohnung;
	private String mieter;
	private String vermieter;
	private int miete;				// €/Monat
	private int vertragslaufzeit; 	// Monate
	private float wohnflaeche; 		// m^2
	private int zimmerAnzahl;
	private int kostenStrom;		// €
	private int kostenHeizung;		// €
	private int kostenWasser;		// €
	private int kostenInternet;		// €
	
	// Konstruktor
	public Mietvertrag(String wohnung, String mieter, String vermieter, 
				int miete, int vertragslaufzeit, float wohnflaeche,
				int zimmerAnzahl, int kostenStrom, int kostenHeizung, 
				int kostenWasser, int kostenInternet) {
		this.wohnung = wohnung;
		this.mieter = mieter;
		this.vermieter = vermieter;
		this.miete = miete;
		this.vertragslaufzeit = vertragslaufzeit;
		this.wohnflaeche = wohnflaeche;
		this.zimmerAnzahl = zimmerAnzahl;
		this.kostenStrom = kostenStrom;
		this.kostenHeizung = kostenHeizung;
		this.kostenWasser = kostenWasser;
		this.kostenInternet = kostenInternet;
	}
	
	
	// Getter (Kapselung)
	
	public String getWohnung() {
	    return wohnung;
	}

	public String getMieter() {
	    return mieter;
	}

	public String getVermieter() {
	    return vermieter;
	}

	public int getMiete() {
	    return miete;
	}

	public int getVertragslaufzeit() {
	    return vertragslaufzeit;
	}

	public float getWohnflaeche() {
	    return wohnflaeche;
	}

	public int getZimmerAnzahl() {
	    return zimmerAnzahl;
	}

	public int getKostenStrom() {
	    return kostenStrom;
	}

	public int getKostenHeizung() {
	    return kostenHeizung;
	}

	public int getKostenWasser() {
	    return kostenWasser;
	}

	public int getKostenInternet() {
	    return kostenInternet;
	}
	
	public int getGesamtkosten() {
		return kostenStrom + kostenHeizung + kostenWasser + kostenInternet;
	}
	
	// Druckt alle Informationen in die Console.
	public void printInfo() {
		String[] infoStringListe = new String[] {
				"Der Mieter heißt "+mieter+".",
				"Der Vermieter heißt "+vermieter+".",
				"Die Miete ist "+miete+" €/Monat.",
				"Die Vertragslaufzeit ist "+vertragslaufzeit+" Monate.",
				"Die Wohnfläche ist "+wohnflaeche+" m^2.",
				"Die ZimmerAnzahl ist "+zimmerAnzahl+".",
				"Die Stromkosten sind "+kostenStrom+" €.",
				"Die Heizkosten sind "+kostenHeizung+" €.",
				"Die Wasserkosten sind "+kostenWasser+" €.",
				"Die Internetkosten sind "+kostenInternet+" €."
		};
		
		Box.basic(wohnung, infoStringListe, false);
	}
	
	public String[] getDaten() {
		return new String[] {wohnung, mieter, vermieter, 
				Integer.toString(miete), Integer.toString(vertragslaufzeit), 
				Float.toString(wohnflaeche), Integer.toString(zimmerAnzahl), 
				Integer.toString(kostenStrom), Integer.toString(kostenHeizung), 
				Integer.toString(kostenWasser), Integer.toString(kostenInternet)};
	}
}
