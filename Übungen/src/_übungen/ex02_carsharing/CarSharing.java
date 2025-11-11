package _übungen.ex02_carsharing;

import _main.Übung;
import console.input.Input;
import console.menu.Menu;
import console.output.ProgramMessages;
import math.MathUtils;

public class CarSharing implements Übung {
	
	private static final Integer[] VEHICLE_CLASSES = {1,2};
	private static final Integer[] KM_THRESHOLDS = {100,250,500,1000,Integer.MAX_VALUE};
	//COSTS = [VehicleClassOne[CostAtCertainDistances],VehicleClassTwo[CostAtCertainDistances]]
	private static final Double[][] COSTS = {{0.33,0.31,0.29,0.27,0.25}, {0.38,0.36,0.34,0.32,0.30}};
	private static final Double[] HOUR_COSTS = {3.00,4.00};
	public final String[] explainLines = new String[] {
			"In folgendem Programm geben Sie Ihre Car Sharing daten ein,",
			"womit Ihre Kosten für den Nutzen des Autos errechnet werden.",
			"Geben Sie während der Eingabe ein 'X' ein um das Programm zu schließen."
	};
	
	
	public void start() {
		ProgramMessages.explainProgram(explainLines);
		
		while (true) {	
			int[] data = getData();
			
			System.out.printf("\nIhr Rechnungsbetrag ist [%f€] \n\n", MathUtils.roundTo(calculateCost(data), 2));
			
			if (Menu.shouldExit()) {
				return;
			}
		}
	}
	
	private static int[] getData() {
		int vehicleClassIndex = Input.getMatchingInt("Fahrzeugsklasee: ", VEHICLE_CLASSES);
		vehicleClassIndex--;
		int drivenKm = getInput("Gefahrene Kilometer: ");
		int hoursUsed = getInput("Stunden genutzt: ");
		
		return new int[] {vehicleClassIndex,drivenKm,hoursUsed};
	}
	
	private static Integer getInput(String prompt) {
        return Input.builder()
        		.typeInteger()
        		.prompt(prompt)
        		.exitConditions("x")
        		.numberValidation()
        		.get();
    }
	
	private static double calculateCost(int[] data) {
		double cost = 0;
		int vehicleClassIndex = data[0];
		double hoursUsed = (double) data[2];
		
		for (int index = 0; index < KM_THRESHOLDS.length; index++) {
			int drivenKm = data[1];
			
			if (drivenKm <= KM_THRESHOLDS[index]) {
				cost = COSTS[vehicleClassIndex][index] * drivenKm;
				break;
			}
		}
		
		cost += HOUR_COSTS[vehicleClassIndex] * hoursUsed;
		
		return cost;
	}
}
