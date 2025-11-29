package übungen.ex04_bordcomputer;

import _main.Übung;
import console.input.Input;
import console.menu.Menu;
import console.output.ProgramMessages;
import util.ArrayUtils;

public class Bordcomputer implements Übung {
	
	private final String[] explainStrings = new String[] { 
			"In folgendem Programm kontrollieren sie einen PKW.",
			"Sie starten mit einem vollen Tank." };
	
	private final String[] menu = new String[] {
			"[═══════] Menü [═══════]",
			" 1 – Fahren",
			" 2 – Tanken",
			" 3 – Daten anzeigen",
			" 0 - Zurück" };
	
	private static void displayData(double[] data) {
		final String[] displayStrings = new String[] {
				"Der Kilometerstand liegt bei %.0f Km.",
				"Der Tankinhalt liegt bei %.2f L.",
				"Der Tankvolumen ist %.2f L.",
				"Der Durchschnittsverbrauch liegt bei %.2f L/100Km." };
		
		int i = 0;
		for (double elem : data) {
			System.out.printf(displayStrings[i++], elem);
			System.out.println();
		}
	}
	
	public void start() {
		PKW herbie = new PKW();
		
		ProgramMessages.explainProgram(explainStrings);
		
		while(true) {
			int choice = Menu.basic(menu, ArrayUtils.integerRange(4));
			switch (choice) {
			case 0:
				return;
			case 1:
				int kmToDrive = Input.getInt("zu fahrende Kilometer: ");
				int kmDriven = herbie.drive(kmToDrive);
				if (kmDriven < kmToDrive) {
					System.out.println("Der treibstoff war nicht für die volle Fahrt genügend.");
				}
				System.out.printf("Sie sind %d Km gefahren! \n", kmDriven);
				break;
			case 2:
				double refuelAmount = Input.getDouble("zu tankende Menge in Litern: ");
				double fuelLevel = herbie.refuel(refuelAmount);
				System.out.printf("Sie haben %.2f Liter getankt! und liegen nun bei %.2f L.\n", refuelAmount, fuelLevel);
				break;
			case 3:
				displayData(herbie.getData());
				break;
			}
			
			System.out.println();
		}
	}
}
