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
			" 1 - Fahren",
			" 2 - Tanken",
			" 3 - Daten anzeigen",
			" 4 - Neues Auto",
			" 0 - Zurück" };
	
	public void start() {
		PKW auto = new PKW();
		
		ProgramMessages.printWelcomeMessageBoxed(explainStrings);
		
		while(true) {
			byte choice = (byte) Menu.basic(menu, ArrayUtils.integerRange(5));
			switch (choice) {
			case 0:
				return;
			case 1:
				int kmToDrive = Input.getInt("zu fahrende Kilometer: ");
				int kmDriven = auto.drive(kmToDrive);
				if (kmDriven < kmToDrive) {
					System.out.println("Der treibstoff war nicht genügend für die volle Fahrt.");
				}
				System.out.printf("Sie sind %d Km gefahren! \n", kmDriven);
				break;
			case 2:
				double refuelAmount = Input.getDouble("zu tankende Menge in Litern: ");
				double fuelLevel = auto.refuel(refuelAmount);
				System.out.printf("Sie haben %.2f Liter getankt! und liegen nun bei %.2f L.\n", refuelAmount, fuelLevel);
				break;
			case 3:
				displayData(auto.getData());
				break;
			case 4:
				auto = new PKW();
			}
			
			System.out.println();
		}
	}
	
	private static void displayData(double[] data) {
		final String[] displayStrings = new String[] {
				"Der Kilometerstand liegt bei %.0f Km.",
				"Der Tankinhalt liegt bei %.2f L.",
				"Der Tankvolumen ist %.2f L.",
				"Der Durchschnittsverbrauch liegt bei %.2f L/100Km.", 
				"Die Reichweite liegt bei %.2f Km" };
		
		int maxLineLength = 0;
		int i = 0;
		for (double elem : data) {
			String line = displayStrings[i++].formatted(elem);
			int lineLength = line.length();
			maxLineLength = lineLength > maxLineLength ? lineLength : maxLineLength;
			System.out.println(line);
		}
		
		Menu.loadingBar(maxLineLength - 2, 3000, false);
		System.out.println();
	}
}
