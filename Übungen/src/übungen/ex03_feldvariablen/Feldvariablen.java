package übungen.ex03_feldvariablen;

import _main.Übung;
import console.input.Input;
import console.output.ProgramMessages;
import util.ArrayUtils;
import util.Program;
import file.Saver;

public class Feldvariablen implements Übung {

	private final String[] explainStrings = new String[] { 
			"In folgendem Programm geben Sie eine Zahlenfolge ein,",
			"welche dann schön dargestellt wird,", "und womit Sie rechenoperationen durchführen können.",
			"Die zahlenreihe kann mit folgenden Zeichen/Zeichenreihen getrennt werden:", 
			"    ' '", 
			"    ','",
			"    ', '", 
			"oder einfach mit Zeilenumbruchen (Enter).", 
			"Wenn sie alle Ihrer Nummern eingegeben haben,",
			"entern Sie eine leere Eingabe", "oder entern Sie eine lehre Eingabe", };
	
	public void start() {
		MenuLogic menu = new MenuLogic();
		Saver saver = new Saver("feldvariablen");
		
		if (saver.isSaveFileEmpty()) {
			ProgramMessages.explainProgram(explainStrings);
		}
		
		// get save and set menuState
		int[] numbers;
		if (saver.isSaveFileEmpty()) {
			numbers = new int[0];
			menu.menuState = MenuLogic.MenuState.FIRST;
		} else {
			numbers = ArrayUtils.toIntArray(saver.readSaveFile());
			menu.menuState = MenuLogic.MenuState.ADD;
		}
		
		while (true) {
			// menus
			if (menu.menuState == MenuLogic.MenuState.ADD) {
				numbers = menu.mainMenu(numbers);
				if (menu.menuState == MenuLogic.MenuState.EXIT) {
					save(saver, numbers);
					return;
				}
			} else if (menu.menuState == MenuLogic.MenuState.FIRST) { // if menu state is FIRST
				if (menu.firstMenu() == 0) {
					return;
				}
			} else {
				System.out.println("ERROR: feldvariablen; MenuLogic; menuState - wurde falsch gesetzt");
				Program.close();
			}
			
			// when we get here, we assume, that we want to get number input
			if (menu.menuState == MenuLogic.MenuState.ADD) {
				numbers = ArrayUtils.appendArray(numbers, getNumbers());
			} else {
				numbers = getNumbers();
			}
			
			// check if numbers are present to display
			if (numbers.length == 0 || numbers == null) {
				System.out.print("[ ¯\\_(ツ)_/¯ ]\n\n");
			} else {
				save(saver, numbers);
				Display.display(numbers);
			}
		}
	}

	private static int[] getNumbers() {
		return (int[]) Input.builder()
				.typeInteger()
				.stopOnEmptyLine()
				.exitConditions("X")
				.numberValidation()
				.getMult();
	}
	
	private static void save(Saver saver, int[] numbers) {
		saver.writeSaveFile(ArrayUtils.toStringArray(numbers));
	}
}
