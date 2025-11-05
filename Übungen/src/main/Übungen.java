package main;


public class Übungen {
	
	private static final Übung[] OPTIONS = {
		    new Tankbelege(),
		    new CarSharing(),
		    new Feldvariablen(),
		    
		    /* Add new Übungen here. 
		     * It's Class must implent Übung and
		     * it's main function has to be
		     * public static void start()
		     */
		};
	
	
	public static void main(String[] args) {
		String menuString = createMenuString();
		Integer[] menuOptions = util.Array.integerRange(OPTIONS.length + 1);
		
		util.General.sleep(200); // to reduce the lag on startup
		
		while (true) {
			int menuChoice = util.Menu.basic(menuString, menuOptions);
			
			if (menuChoice == 0) {util.General.closeProgram();}
				
			System.out.print("\n========== Wilkommen ========== (drücken Sie Enter um zu Skippen)\n");
			OPTIONS[menuChoice - 1].start();
			
			System.out.println();
		}
	}
	
	private static String createMenuString() {
		String menuString = "\n====== Übungsmenü ======\n";
		
		for (int index = 0; index < OPTIONS.length; index++) {
			String className = OPTIONS[index].getClass().getSimpleName();
			String option = " " + (index + 1) + " - " + className + "\n";
			menuString = menuString + option;
		}
		
		return menuString.concat(" 0 - Programm Schließen \n");
	}
}
