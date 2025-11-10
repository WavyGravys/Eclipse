package _main;

import console.menu.Menu;
import console.output.ProgramMessages;
import util.ArrayUtils;
import util.TimeUtils;

public class Exercises {
	
	private static final Exercise[] OPTIONS = {
		    new Tankbelege(),
		    new CarSharing(),
		    new Feldvariablen(),
		    
		    /* Add new Exercise here. 
		     * It's Class must implent Exercise.
		     */
		};
	
	
	public static void main(String[] args) {
		String[] menu = createMenuString();
		Integer[] menuOptions = ArrayUtils.integerRange(OPTIONS.length + 1);
		
		TimeUtils.sleep(200); // to reduce the lag on startup
		
		while (true) {
			int menuChoice = Menu.basic(menu, menuOptions);
			
			if (menuChoice == 0) {
				ProgramMessages.closeProgram();
			}
			
			OPTIONS[menuChoice - 1].start();
		}
	}
	
	private static String[] createMenuString() {
		String[] menu = new String[OPTIONS.length + 2];
		
		menu[0] = "[═════] Übungsmenü [═════]";
		
		for (int i = 0; i < OPTIONS.length; i++) {
			String className = OPTIONS[i].getClass().getSimpleName();
			menu[i + 1] = String.format(" %d - %s", i + 1, className);
		}
		
		menu[OPTIONS.length + 1] = " 0 - Exit Program";
		
		return menu;
	}
}
