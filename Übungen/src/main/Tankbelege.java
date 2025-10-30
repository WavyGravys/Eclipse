package main;


public class Tankbelege {
	
    private static int getInput(String preInputMessage, Double min, Boolean minInclusive) {
    	return util.Input.getIntInRange(preInputMessage,null,null,min,null,minInclusive,false);
    }
    
    
	private static void explainProgram() {
		System.out.print("\n========== Wilkommen ==========\n");
		System.out.print("In folgendem Programm geben Sie Ihre Tankbelege ein, \n");
		System.out.print("mit welchen anschließnd Ihr Durschnittsverbrauch errechnet wird. \n");
		System.out.print("viel Spaß! \n\n");
	}
    
    
    public static void start() {
    	explainProgram();
    	// initialize
		int startingMileage = getInput("Aktueller Kilometerstand: ", 0d, true);
		int lastMileage = startingMileage;
    	int totalDistanceDriven = 0;
    	int totalRefuelAmount = 0;
    	// start main loop
    	System.out.print("\n - Erster Tankbeleg - \n");
    	while (true) {
    		// get data
    		int newRefuelAmount = getInput("getankte Menge in Litern: ", 0d, false);
    		int newMileage = getInput("Aktueller Kilometerstand: ", (double)lastMileage, false);
			// calculate average
    		totalRefuelAmount += newRefuelAmount;
			totalDistanceDriven += newMileage - lastMileage;
			lastMileage = newMileage;
			float distanceDiv100 = totalDistanceDriven / 100;
			float literPer100Km = totalRefuelAmount / ((distanceDiv100 == 0) ? 0.01f : distanceDiv100);
			// print average
			System.out.printf("Ihr durchschnittlicher verbrauch ist %.2f L/100Km \n", literPer100Km);
			// menu
			if (util.General.basicMenu())
				return;
			System.out.print("\n - Neuer Tankbeleg - \n");
    	}
	}
}