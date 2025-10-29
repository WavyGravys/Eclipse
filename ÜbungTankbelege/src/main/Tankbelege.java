package main;


public class Tankbelege {
	
    private static int getInput(String preInputMessage, Double min, Boolean minInclusive) {
    	return util.Input.getIntInRange(preInputMessage,null,null,min,null,minInclusive,false);
    }
    
    
    public static void main(String[] args) {
    	System.out.print("========== Wilkommen ==========\n");
		int startingMileage = getInput("Aktueller Kilometerstand: ", 0d, true);
		int lastMileage = startingMileage;
    	
    	int totalDistanceDriven = 0;
    	int totalRefuelAmount = 0;
    	System.out.print("\n - Erster Tankbeleg - \n");
    	while (true) {
    		int newRefuelAmount = getInput("getankte Menge in Litern: ", 0d, false);
    		int newMileage = getInput("Aktueller Kilometerstand: ", (double)lastMileage, false);
			totalRefuelAmount += newRefuelAmount;
			totalDistanceDriven += newMileage - lastMileage;
			lastMileage = newMileage;
			
			float literPer100Km = totalRefuelAmount / (totalDistanceDriven / 100);
			System.out.printf("Ihr durchschnittlicher verbrauch ist %.2f L/100Km \n", literPer100Km);
			
			if (util.General.menu(null,null,null,null,null,null,null) == 0)
				return;
			
			System.out.print("\n - Neuer Tankbeleg - \n");
    	}
	}
}