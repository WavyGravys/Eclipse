package main;


public class CarSharing {
		
	// settings TODO: settings file (JSON?)
	static final int[] vehicleClasses = {1,2};
	static final Integer[] kmNumbers = {100,250,500,1000,Integer.MAX_VALUE};
	//costs = [VehicleClassOne[CostAtCertainDistances],VehicleClassTwo[CostAtCertainDistances]]
	static final Double[][] costs = {{0.33,0.31,0.29,0.27,0.25},{0.38,0.36,0.34,0.32,0.30}};
	static final Double[] hourCosts = {3.00,4.00};
	
	
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
		int vehicleClassIndex = util.Input.getMatchingInt("Fahrzeugklasse: ",null,null,vehicleClasses) - 1;
		int drivenKm = getInput("Gefahrene Kilometer: ");
		int hoursUsed = getInput("Stunden genutzt: ");
		
		return new int[] {vehicleClassIndex,drivenKm,hoursUsed};
	}
	
	
	private static double calculateCost(int[] data) {
		double cost = 0;
		
		int vehicleClassIndex = data[0];
		int drivenKm = data[1];
		int hoursUsed = data[2];
		
		for (int index=0; index<kmNumbers.length; index++) {
			if (drivenKm <= kmNumbers[index]) {
				cost = costs[vehicleClassIndex][index] * drivenKm;
				break;
			}
		}
		cost += hourCosts[vehicleClassIndex] * (double)hoursUsed;
		
		return cost;
	}
	
	
	public static void start() {
		explainProgram();
		while (true) {	
			int[] data = getData();
			double cost = calculateCost(data);
			System.out.printf("Rechnungsbetrag: %.2f€ \n", cost);
			if (util.General.basicMenu()) 
				return;
		}
	}
}
