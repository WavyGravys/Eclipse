package main;


public class Tankbelege {
	
    private static float getInput(String preInputMessage, Double min, Boolean minInclusive) {
    	float inputMileage = (float)util.Input.getDoubleInRange(preInputMessage,null,null,min,null,minInclusive,null);
    	float roundedMileage = (float)((int)(Math.ceil(inputMileage*100))/100); //rounds up to the second decimal place
    	if (inputMileage != roundedMileage)
			System.out.printf("Kilometerstand wurde von %f auf %.2f gerundet.\n", inputMileage, roundedMileage);
		return (float)roundedMileage;
    }
    
    
    public static void main(String[] args) {
    	System.out.print("========== Wilkommen ==========\n");
		
		double totalDistanceDriven = 0;
		double totalRefuelAmount = 0;
		
		float startingMileage = getInput("Aktueller Kilometerstand: ", 0d, true);
		float lastMileage = startingMileage;
    	
    	System.out.printf("\n - Erster Tankbeleg - \n");
    	while (true) {
    		double newRefuelAmount = getInput("getankte Menge in Litern: ", 0d, false);
    		float newMileage = getInput("Aktueller Kilometerstand: ", (double)lastMileage, false);
			totalRefuelAmount += newRefuelAmount;
			totalDistanceDriven = startingMileage - newMileage;
			lastMileage = newMileage;
			
			double literPer100Km = totalRefuelAmount / (totalDistanceDriven / 100);
			System.out.printf("Ihr durchschnittlicher verbrauch ist %.2f L/100Km %n", literPer100Km);
			
			int selectionInput = util.General.menu(null,null,null,null,null,null,null);
			if (selectionInput == 0)
				return;
			
			System.out.printf("\n - Neuer Tankbeleg - \n");
    	}
	}
}