package _main;

import _übungen.Übung;
import console.input.Input;
import console.output.ProgramMessages;

public class Tankbelege implements Übung {
	
	public static final String[] explainStrings = new String[] {
			"In folgendem Programm geben Sie Ihre Tankbelege ein,",
			"mit welchen anschließend Ihr Durschnittsverbrauch errechnet wird.",
			};
	private static int totalRefuelAmount = 0;
	private static int totalDistanceDriven = 0;
	private static int lastMileage;
	
	
	public void start() {
		ProgramMessages.explainProgram(explainStrings);
		
		lastMileage = getInput("Aktueller Kilometerstand: ", 0d, true);

		
		System.out.print("\n - Erster Tankbeleg - \n");
		
		while (true) {
			int refuelAmount = getInput("Getankte Menge in Litern: ", 0d, false);
			int currentMileage = getInput("Aktueller Kilometerstand: ", (double) 
										  lastMileage, false);
			
			updateVars(refuelAmount, currentMileage);
			
			getAndPrintAvgConsumption();
			
			if (console.menu.Menu.shouldExit()) {
				return;
			}
			
			System.out.print("\n - Neuer Tankbeleg - \n");
		}
	}
	
    private static int getInput(String prompt, double min, boolean minInclusive) {
    	
    	return Input.builder()
    			.typeInteger()
    			.prompt(prompt)
    			.rangeValidation(min, Integer.MAX_VALUE)
    			.inclusivity(minInclusive, false)
    			.get();
    }
    
    private static void updateVars(int refuelAmount, int currentMileage) {
		totalRefuelAmount += refuelAmount;
		totalDistanceDriven += currentMileage - lastMileage;
		lastMileage = currentMileage;
    }
    
    private static void getAndPrintAvgConsumption() {
        float distanceIn100Km = totalDistanceDriven / 100f;
        if (distanceIn100Km == 0) {
        	distanceIn100Km = 0.01f;
        }
        
        System.out.println(totalRefuelAmount / distanceIn100Km);
    }
}