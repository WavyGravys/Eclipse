package _main;

public class Mietvertrag {
	
	// Initialisierung
	public String wohnung;
	private String mieter;
	private String vermieter;
	private int miete;				// €/Monat
	private int vertragslaufzeit; 	// Monate
	private float wohnfläche; 		// m^2
	private int zimmerAnzahl;
	private int kostenStrom;		// €
	private int kostenHeizung;		// €
	private int kostenWasser;		// €
	private int kostenInternet;		// ? €
	
	Object[] infoListe = new Object[] {
			wohnung,
			mieter,
			vermieter,
			miete,
			vertragslaufzeit,
			wohnfläche,
			zimmerAnzahl,
			kostenStrom,
			kostenHeizung,
			kostenWasser,
			kostenInternet
	};
	
	// Konstruktor
	Mietvertrag(String wohnung, String mieter, String vermieter, 
				int miete, int vertragslaufzeit, float wohnfläche,
				int zimmerAnzahl, int kostenStrom, int kostenHeizung, 
				int kostenWasser, int kostenInternet) {
		this.wohnung = wohnung;
		this.mieter = mieter;
		this.vermieter = vermieter;
		this.miete = miete;
		this.vertragslaufzeit = vertragslaufzeit;
		this.wohnfläche = wohnfläche;
		this.zimmerAnzahl = zimmerAnzahl;
		this.kostenStrom = kostenStrom;
		this.kostenHeizung = kostenHeizung;
		this.kostenWasser = kostenWasser;
		this.kostenInternet = kostenInternet;
	}
	
	
	/* - Getter - */
	
	// ...
	
	
	// Druckt alle Informationen in die Console.
	public void printInfo() {
		Object[] infoStringListe = new Object[] {
				"Der Mieter heißt "+mieter+".",
				"Der Vermieter heißt "+vermieter+".",
				"Die Miete ist "+miete+" €/Monat.",
				"Die Vertragslaufzeit ist "+vertragslaufzeit+" Monate.",
				"Die Wohnfläche ist "+wohnfläche+" m^2.",
				"Die ZimmerAnzahl ist "+zimmerAnzahl+".",
				"Die Stromkosten sind "+kostenStrom+" €.",
				"Die Heizkosten sind "+kostenHeizung+" €.",
				"Die Wasserkosten sind "+kostenWasser+" €.",
				"Die Internetkosten sind "+kostenInternet+" €."
		};
		
		Box.basic(wohnung, infoStringListe, false);
	}
	
	
}
