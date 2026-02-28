package helfer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import _main.Hauptprogramm;
import _main.Mietvertrag;

public class SpeicherManager {
	private static String dateipfad = "speicher\\immobilienverwaltung.txt";
	private File file;
	private FileReader reader;
	private FileWriter writer;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	
	//private static 
	
	// Voreinstellungen
	private String vermieter = "";
	private int wohnungsmaenge = 9;
	private float standardmieteProQdrM = 15.38f;
	
	// Verträge
	private ArrayList<Mietvertrag> vertraege = new ArrayList<Mietvertrag>();
	private int vertragsmaenge = 0;
	
	
	// Getter und Setter (Kapselung)
	
	public ArrayList<Mietvertrag> getVertraege() {
	    return vertraege;
	}

	public void setVertraege(ArrayList<Mietvertrag> vertraege) {
	    this.vertraege = vertraege;
	    vertragsmaengeAktualisieren();
	}

	public String getVermieter() {
	    return vermieter;
	}

	public void setVermieter(String vermieter) {
	    this.vermieter = vermieter;
	}

	public int getWohnungsmaenge() {
	    return wohnungsmaenge;
	}

	public boolean setWohnungsmaenge(Integer wohnungsmaenge) {
	    if (wohnungsmaenge == null) {
	    	this.wohnungsmaenge = 9;
	    	System.out.println("\u2503 Ihre Wohnungsmänge wurde auf die standardmenge von 9 gestellt.");
	    	System.out.println("\u2503 Wollten sie die Standard .");
	    }
		
		this.wohnungsmaenge = wohnungsmaenge == null ? 9 : wohnungsmaenge;
		
		return true;
	}
	
	public int getVertragsmaenge() {
	    return vertragsmaenge;
	}

	public float getStandardmieteProQdrM() {
	    return standardmieteProQdrM;
	}

	public void setStandardmieteProQdrM(Float standardmieteProQdrM) {
	    this.standardmieteProQdrM = standardmieteProQdrM == null ? 15.38f : standardmieteProQdrM;
	}
	
	// Konstruktor
	public SpeicherManager() {
		try {
			// versucht es einen "speicher" ordner zu erstellen
			new File("speicher").mkdir();
			
			// versucht es die speicher datei zu erstellen
			file = new File(dateipfad);
			boolean speicherdateiKreiert = file.createNewFile();
			
			// falls keine neue Datei erstellt wurde daten auslesen
			if (!speicherdateiKreiert) {
				auslesen();
			}
		
		} catch (IOException e) {
			// crash
			e.printStackTrace();
		}
	}
	
	
	private void vertragsmaengeAktualisieren() {
		vertragsmaenge = vertraege.size();
	}
	
	
	public void vertragHinzufügen(Mietvertrag vertrag) {
		if (vertragsmaenge == wohnungsmaenge) {
			byte auswahl = Menue.getAuswahl(dateipfad, null);
			/*
			switch (auswahl) {
			case 1 -> 
			}
			
			vertragWechseln();
			*/
		} else {
			vertraege.add(vertrag);
			vertragsmaenge ++;
			// vertragSpeichern(vertrag);
			ueberschreiben();
		}
	}
	
	
	public void vertragLöschen(byte auswahl) {
		if (auswahl == 0) { return; }
		vertraege.remove(auswahl - 1);
		vertragsmaenge --;
		ueberschreiben();
	}
	
	
	private void vertragSpeichern(Mietvertrag vertrag) {
		
	}
	
	
	private void auslesen() {
		try {
	        // Daten aus der Datei auslesen
			String[] daten = datenLesen();
	        
			// schauen ob etwas gespeichert war
			if (daten.length == 0) { return; }
			
			// Voreinstellungen einsortieren
			daten[0] = daten[0] == null ? "" : daten[0];
	    	vermieter = daten[0];
	    	wohnungsmaenge = Byte.parseByte(daten[1]);
	    	standardmieteProQdrM = Float.parseFloat(daten[2]);
	        
	    	// Voreinstellungen aus den Daten löschen
	    	String[] vertragsdaten = new String[daten.length - 3];
	    	System.arraycopy(daten, 3, vertragsdaten, 0, daten.length - 3);
	    	
	    	
	    	
	    	// Mietverträge einsortieren
	    	vertragsmaenge = 0;
	    	int i = 0;
	    	// vertragsdaten x gennant um die mietvertrags initialisierung überschaulicher zu machen
	        String[] x = new String[11];
	    	for (String zeile : vertragsdaten) {
	    		x[i] = zeile;
	    		i ++;
	    		if (i == 11) {
	    			i = 0;
	    			
	    			// vertrag einsortieren
	    			vertraege.add(new Mietvertrag(
	    					x[0], x[1], x[2], Integer.parseInt(x[3]), Integer.parseInt(x[4]),
							Float.parseFloat(x[5]), Integer.parseInt(x[6]), Integer.parseInt(x[7]),
							Integer.parseInt(x[8]), Integer.parseInt(x[9]), Integer.parseInt(x[10])));
	    			vertragsmaenge ++;
	    			x = new String[11];
	    		}
	        }
	    }  catch (IOException e) {
	    	// crash
	    	e.printStackTrace();
	    }
	}
	
	
	public void ueberschreiben() {
		try {
			// Daten Array initialisieren
			String[] daten = new String[3 + 11 * vertragsmaenge];
			
			// Daten sammeln
			daten[0] = vermieter;
			daten[1] = Integer.toString(wohnungsmaenge);
			daten[2] = Float.toString(standardmieteProQdrM);
			
			int zähler = 3;
			for (int i = 0; i < vertragsmaenge; i++) {
				String[] vertragDaten = vertraege.get(i).getDaten();
				for (String wert : vertragDaten) {
					daten[zähler] = wert;
					zähler ++;
				}
			}
			
			// Daten überschreiben
			datenÜberschreiben(daten);
	    
		} catch (Exception e) {
	    	// crash
			e.printStackTrace();	
	    }
	}
	
	
	private String[] datenLesen() throws IOException {
		int lineCount = (int) Files.lines(Paths.get(dateipfad)).count();
		String[] data = new String[lineCount]; 
		
		readerOeffnen();
		
		int i = 0;
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			data[i++] = line;
		}
		
		readerSchliessen();
		
		return data;
	}
	
	
	private void datenÜberschreiben(String[] daten) throws IOException{
		if (daten == null || daten.length == 0) { return; }
		
		writerOeffnen();
		
		for (int i = 0; i < daten.length - 1; i++) {
        	bufferedWriter.write(daten[i]);
        	bufferedWriter.newLine();
        }
        bufferedWriter.write(daten[daten.length - 1]);
        
        writerSchliessen();
	}
	
	
	private void readerOeffnen() throws IOException {
		reader = new FileReader(dateipfad);
		bufferedReader = new BufferedReader(reader);
	}
	
	
	private void readerSchliessen() throws IOException {
		bufferedReader.close();
        reader.close();
	}
	
	
	private void writerOeffnen() throws IOException {
		writer = new FileWriter(dateipfad, false);
		bufferedWriter = new BufferedWriter(writer);
	}
	
	
	private void writerSchliessen() throws IOException {
		bufferedWriter.close();
		writer.close();
	}
	
	public void loeschen() {
		try {
			writerOeffnen();
			writerSchliessen();
			
			vermieter = "";
			wohnungsmaenge = 9;
			standardmieteProQdrM = 15.38f;
			vertraege = new ArrayList<Mietvertrag>();
			vertragsmaenge = 0;
			
		} catch (IOException e) {
			// crash
			e.printStackTrace();
		}
	}
}

