package _übungen.ex01_tankbelege;

import _main.Übung;
import console.input.Input;
import console.menu.Menu;
import console.output.ProgramMessages;

public class Tankbelege implements Übung { // TODO: gleitender Mittelwert
	private final FuelConsumptionTracker tracker = new FuelConsumptionTracker();
	
	public final String[] explainStrings = new String[] {
			"In folgendem Programm geben Sie Ihre Tankbelege ein,",
			"mit welchen anschließend Ihr Durschnittsverbrauch errechnet wird.",
			"Geben Sie während der Eingabe ein 'X' ein um das Programm zu schließen."
	};
	
	public void start() {
		ProgramMessages.explainProgram(explainStrings);
     
		Integer initialMileage = getInput("Aktueller Kilometerstand: ", 0d, true);
		if (initialMileage == null) {return;}
		tracker.setInitialMileage(initialMileage);
		
		System.out.print("\n - Erster Tankbeleg - \n");
		while (true) {
			Integer refuelAmount = getInput("Getankte Menge in Litern: ", 0d, false);
			if (refuelAmount == null) {return;}
			Integer currentMileage = getInput("Aktueller Kilometerstand: ", 
					(double) tracker.getLastMileage(), false);
			if (currentMileage == null) {return;}

        tracker.recordRefueling(refuelAmount, currentMileage);
        
        printAverageConsumption();
        
        if (Menu.shouldExit()) { return; }
         
        System.out.print("\n - Neuer Tankbeleg - \n");
		}
	}
	
	private static Integer getInput(String prompt, double min, boolean minInclusive) {
		return Input.builder()
				.typeInteger()
				.prompt(prompt)
				.exitConditions("X")
				.rangeValidation(min, Integer.MAX_VALUE)
				.inclusivity(minInclusive, false)
				.get();
	}
	
	private void printAverageConsumption() {
		StringBuilder sb = new StringBuilder("\nIhr Treibstoffverbrauch ist: ");
        sb.append(tracker.getAverageConsumption());
        sb.append(" L/100Km.\n");
        String message = sb.toString();
        System.out.print(message);
        Menu.loadingBar(message.length(), 1000, false);
        System.out.println();
	}
	
	
	private static class FuelConsumptionTracker {
	    private int totalRefuelAmount = 0;
	    private int totalDistanceDriven = 0;
	    private int lastMileage;
	    
	    public void recordRefueling(int refuelAmount, int currentMileage) {
	        totalRefuelAmount += refuelAmount;
	        totalDistanceDriven += currentMileage - lastMileage;
	        lastMileage = currentMileage;
	    }
	    
	    public int getLastMileage() {
	    	return this.lastMileage;
	    }
	    
	    public void setInitialMileage(int mileage) {
	        this.lastMileage = mileage;
	    }
	    
	    public float getAverageConsumption() {
	        if (totalDistanceDriven == 0) return 0;
	        return totalRefuelAmount / (totalDistanceDriven / 100f);
	    }
	}
}