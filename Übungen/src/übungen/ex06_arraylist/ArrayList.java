package übungen.ex06_arraylist;

import _main.Übung;
import console.output.ProgramMessages;

public class ArrayList implements Übung {

	private final String[] explainStrings = new String[] { 
			"Mit folgendem Programm können Sie Autos in einer Liste Organisieren" };

	
	@Override
	public void start() {
		ProgramMessages.printWelcomeMessageBoxed(explainStrings);
		
	}

}
