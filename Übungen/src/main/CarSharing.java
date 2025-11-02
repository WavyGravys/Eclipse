package main;


public class CarSharing {
		
	// settings TODO: settings file (JSON?)
	static final int[] VEHICLE_CLASSES = {1,2};
	static final Integer[] KM_THRESHOLDS = {100,250,500,1000,Integer.MAX_VALUE};
	//COSTS = [VehicleClassOne[CostAtCertainDistances],VehicleClassTwo[CostAtCertainDistances]]
	static final Double[][] COSTS = {{0.33,0.31,0.29,0.27,0.25}, {0.38,0.36,0.34,0.32,0.30}};
	static final Double[] HOUR_COSTS = {3.00,4.00};
	
	private static void explainProgram() {
		System.out.print("\n========== Wilkommen ==========\n");
		System.out.print("In folgendem Programm geben Sie Ihre Car Sharing daten ein, \n");
		System.out.print("womit Ihre Kosten für den Nutzen des Autos errechnet werden. \n");
		System.out.print("viel Spaß! \n");
	}
	
	private static int getInput(String preInputMessage) {
		return util.Input.getIntInRange(preInputMessage,null,null,null,(double)Integer.MAX_VALUE,null,null);
	}
	
	private static int[] getData() {
		int vehicleClassIndex = util.Input.getMatchingInt("Fahrzeugklasse: ",null,null,VEHICLE_CLASSES) - 1;
		int drivenKm = getInput("Gefahrene Kilometer: ");
		int hoursUsed = getInput("Stunden genutzt: ");
		
		return new int[] {vehicleClassIndex,drivenKm,hoursUsed};
	}
	
	private static double calculateCost(int[] data) {
		double cost = 0;
		int vehicleClassIndex = data[0];
		int drivenKm = data[1];
		int hoursUsed = data[2];
		
		for (int index = 0; index < KM_THRESHOLDS.length; index++) {
			if (drivenKm <= KM_THRESHOLDS[index]) {
				cost = COSTS[vehicleClassIndex][index] * drivenKm;
				break;
			}
		}
		cost += HOUR_COSTS[vehicleClassIndex] * (double)hoursUsed;
		return cost;
	}
	
	public static void start() {
		int[] data;
		double cost;
		
		explainProgram();
		
		while (true) {	
			data = getData();
			cost = calculateCost(data);
			System.out.printf("Rechnungsbetrag: %.2f€ \n", cost);
			if (util.General.basicMenu()) {
				return;
			}
		}
	}
}
