package _main;

import _übungen.Übung;
import console.menu.Menu;
import console.output.ProgramMessages;
import util.TimeUtils;

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
		String[] menu = createMenuString();
		Integer[] menuOptions = util.ArrayUtils.integerRange(OPTIONS.length + 1);
		
		TimeUtils.sleep(200); // to reduce the lag on startup
		
		while (true) {
			int menuChoice = Menu.basic(menu, menuOptions);
			
			if (menuChoice == 0) {ProgramMessages.closeProgram();}
				
			System.out.print("\n========== Wilkommen ========== (drücken Sie Enter um zu Skippen)\n");
			OPTIONS[menuChoice - 1].start();
			
			System.out.println();
		}
	}
	
	private static String[] createMenuString() {
		String[] menu = new String[OPTIONS.length + 2];
		
		menu[0] = "[═════] Übungsmenü [═════]";
		
		for (int i = 0; i < OPTIONS.length; i++) {
			String className = OPTIONS[i].getClass().getSimpleName();
			menu[i + 1] = String.format(" %d - %s", i + 1, className);
		}
		
		menu[OPTIONS.length + 1] = " 0 - Programm Schließen";
		
		return menu;
	}
}
