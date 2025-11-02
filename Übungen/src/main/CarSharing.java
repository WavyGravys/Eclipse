package main;


public class CarSharing {
		
	// settings TODO: settings file (JSON?)
	public static final Integer[] VEHICLE_CLASSES = {1,2};
	public static final Integer[] KM_THRESHOLDS = {100,250,500,1000,Integer.MAX_VALUE};
	//COSTS = [VehicleClassOne[CostAtCertainDistances],VehicleClassTwo[CostAtCertainDistances]]
	public static final Double[][] COSTS = {{0.33,0.31,0.29,0.27,0.25}, {0.38,0.36,0.34,0.32,0.30}};
	public static final Double[] HOUR_COSTS = {3.00,4.00};
	
	
	private static void explainProgram() {
		System.out.print("\n========== Wilkommen ==========\n");
		System.out.print("In folgendem Programm geben Sie Ihre Car Sharing daten ein, \n");
		System.out.print("womit Ihre Kosten für den Nutzen des Autos errechnet werden. \n");
		System.out.print("viel Spaß! \n");
	}
	
	private static int[] getData() {
		int vehicleClassIndex = util.Input.getMatchingNumber(
				util.Numbers.INT, "Fahrzeugklasse: ", 
				util.Input.DEFAULT_ERROR, true, VEHICLE_CLASSES);
		vehicleClassIndex--;
		int drivenKm = util.Input.getNumber(util.Numbers.INT, "Gefahrene Kilometer: ");
		int hoursUsed = util.Input.getNumber(util.Numbers.INT, "Stunden genutzt: ");
		
		return new int[] {vehicleClassIndex,drivenKm,hoursUsed};
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
	
	public static void start() {
		explainProgram();
		
		while (true) {	
			int[] data = getData();
			double cost = calculateCost(data);
			System.out.printf("Rechnungsbetrag: %.2f€ \n", cost);
			if (util.Menu.shouldExit()) {
				return;
			}
		}
	}
}
