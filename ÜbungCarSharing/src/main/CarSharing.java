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
	
	static final int maxIndex = kmNumbers.length - 1;
	
	
	public static void main(String[] args) throws InterruptedException {
		System.out.print("========== Wilkommen ==========");
		while (true) {	
			System.out.println();
			int vehicleClass = util.Input.getMatchingInt("Fahrzeugklasse: ",null,null,vehicleClasses);
			int drivenKm = (int)Math.ceil(util.Input.getDoubleInRange("Gefahrene Kilometer: ",null,null,null,(double)Integer.MAX_VALUE,null,null));
			int hoursUsed = (int)Math.ceil(util.Input.getDoubleInRange("Stunden genutzt: ",null,null,null,(double)Integer.MAX_VALUE,null,null));
			int vehicleClassIndex = vehicleClass-1; 
			
			// find the correct cost and calculate the distance cost
			double cost = 0;
			
			boolean stop = false;
			for (int index=0; index<maxIndex-1 && !stop; index++) {
				if (drivenKm < kmNumbers[index]+1) {
					cost = costs[vehicleClassIndex][index] * drivenKm;
					stop = true;
				}
			}
			// if the for loop didn't get broken then use the last cost to calculate end cost
			if (!stop) 
				cost = costs[vehicleClassIndex][maxIndex] * drivenKm;
			
			cost += hourCosts[vehicleClassIndex] * (double)hoursUsed;
			
			System.out.printf("Rechnungsbetrag: %.2f€ \n", cost);
			
			int selectionInput = util.General.menu(null,null,null,null,null,null,null);
			if (selectionInput == 0)
				return;
		}
	}
}
