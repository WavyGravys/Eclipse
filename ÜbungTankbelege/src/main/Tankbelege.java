package main;


public class Tankbelege {
	
    private static float getInput(String preInputMessage, Double min, Boolean minInclusive) {
    	float inputMileage = (float)util.Input.getFloatInRange(preInputMessage,null,null,min,null,minInclusive,false);
    	float roundedMileage = util.Math.roundFloatUp(inputMileage, 2);
    	if (inputMileage != roundedMileage) {
    		System.out.printf("Kilometerstand wurde von %.4f auf %.2f gerundet.\n", inputMileage, roundedMileage);
    		return roundedMileage;
    	} else
			return inputMileage;
    }
    
    
    public static void main(String[] args) {
    	System.out.print("========== Wilkommen ==========\n");
		
    	float totalDistanceDriven = 0;
		float totalRefuelAmount = 0;
		
		float startingMileage = getInput("Aktueller Kilometerstand: ", 0d, true);
		float lastMileage = startingMileage;
    	
    	System.out.print("\n - Erster Tankbeleg - \n");
    	while (true) {
    		float newRefuelAmount = getInput("getankte Menge in Litern: ", 0d, false);
    		float newMileage = getInput("Aktueller Kilometerstand: ", (double)lastMileage, false);
			totalRefuelAmount += newRefuelAmount;
			totalDistanceDriven += newMileage - lastMileage;
			lastMileage = newMileage;
			
			float literPer100Km = totalRefuelAmount / (totalDistanceDriven / 100);
			System.out.printf("Ihr durchschnittlicher verbrauch ist %.2f L/100Km \n", literPer100Km);
			
			byte selectionInput = util.General.menu(null,null,null,null,null,null,null);
			if (selectionInput == 0)
				return;
			
			System.out.print("\n - Neuer Tankbeleg - \n");
    	}
	}
}