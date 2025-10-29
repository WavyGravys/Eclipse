package main;

import java.util.Scanner;


/*  int km = 10;
	String str = "km<5";
	if (str) 
		System.out.print(km);
	
	use lambdas!
*/

public class CarSharing {
	
	static final Scanner scan = new Scanner(System.in);
	
	static final int[] vehicleClasses = {1,2};
	static final Integer[] kmNumbers = {100,250,500,1000,Integer.MAX_VALUE};
	//costs = [VehicleClassOne[CostAtCertainDistances],VehicleClassTwo[CostAtCertainDistances]]
	static final Double[][] costs = {{0.33,0.31,0.29,0.27,0.25},{0.38,0.36,0.34,0.32,0.30}};
	static final Double[] hourCosts = {3.00,4.00};
	
	
	private static int getInput(String preInputMessage) {
		return util.Input.getIntInRange(preInputMessage,null,null,null,(double)Integer.MAX_VALUE,null,null);
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		System.out.print("========== Wilkommen ==========");
		while (true) {	
			System.out.println();
			int vehicleClassIndex = util.Input.getMatchingInt("Fahrzeugklasse: ",null,null,vehicleClasses) - 1;
			int drivenKm = getInput("Gefahrene Kilometer: ");
			System.out.println(drivenKm);
			int hoursUsed = getInput("Stunden genutzt: ");
			
			double cost = 0;
			for (int index=0; index<kmNumbers.length; index++) {
				if (drivenKm <= kmNumbers[index]) {
					cost = costs[vehicleClassIndex][index] * drivenKm;
					break;
				}
			}
			cost += hourCosts[vehicleClassIndex] * (double)hoursUsed;
			
			System.out.printf("Rechnungsbetrag: %.2f€ \n", cost);
			
			if (util.General.menu(null,null,null,null,null,null,null) == 0)
				return;
		}
	}
}
