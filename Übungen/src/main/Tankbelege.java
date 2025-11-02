package main;


public class Tankbelege {
	
    private static int getInput(String prompt, double min, boolean minInclusive) {
    	return util.Input.getNumberInRange(util.Numbers.INT,prompt,min,Integer.MAX_VALUE,minInclusive,false);
    }
    
	private static void explainProgram() {
		System.out.print("\n========== Wilkommen ==========\n");
		System.out.print("In folgendem Programm geben Sie Ihre Tankbelege ein, \n");
		System.out.print("mit welchen anschließnd Ihr Durschnittsverbrauch errechnet wird. \n");
		System.out.print("viel Spaß! \n\n");
	}
	
    public static void start() {
    	int startingMileage;
		int lastMileage;
    	int totalDistanceDriven = 0;
    	int totalRefuelAmount = 0;
    	
    	explainProgram();
    	startingMileage = getInput("Aktueller Kilometerstand: ", 0d, true);
    	lastMileage = startingMileage;
    	
    	System.out.print("\n - Erster Tankbeleg - \n");
    	while (true) {
    		int newRefuelAmount = getInput("getankte Menge in Litern: ", 0d, false);
    		int newMileage = getInput("Aktueller Kilometerstand: ", (double)lastMileage, false);
    		float distanceDiv100;
    		float literPer100Km;
    		
    		totalRefuelAmount += newRefuelAmount;
			totalDistanceDriven += newMileage - lastMileage;
			lastMileage = newMileage;
			distanceDiv100 = totalDistanceDriven/100 == 0 ? 0.01f : totalDistanceDriven/100;
			literPer100Km = totalRefuelAmount / distanceDiv100;
			
			System.out.printf("Ihr durchschnittlicher verbrauch ist %.2f L/100Km \n", literPer100Km);
			
			if (util.Menu.shouldExit()) {
				return;
			}
			System.out.print("\n - Neuer Tankbeleg - \n");
    	}
	}
}