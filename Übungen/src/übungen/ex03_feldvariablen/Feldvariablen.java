package übungen.ex03_feldvariablen;

import _main.Übung;
import console.input.Input;
import console.menu.Menu;
import console.output.ProgramMessages;
import util.ArrayUtils;
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
		
		ProgramMessages.explainProgram(explainStrings);
		
		int[] numbers;
		
		if (!saver.saveFileEmpty()) {
			numbers = ArrayUtils.toIntArray(saver.readSaveFile());
			
			numbers = menu.mainMenu(numbers);
			if (menu.menuState == MenuLogic.MenuState.EXIT) {
				save(saver, numbers);
				return;
			}
		} else {
			numbers = new int[0];
			if (menu.firstMenu() == 0) {
				save(saver, numbers);
				return;
			}
			
		}
		
		while (true) {

			if (menu.menuState == MenuLogic.MenuState.ADD) {
				numbers = ArrayUtils.appendArray(numbers, getNumbers());
			} else {
				if (menu.firstMenu() == 0) {
					save(saver, numbers);
					return;
				}
				numbers = getNumbers();
			}

			if (numbers.length == 0 || numbers == null) {
				System.out.print("[ ¯\\_(ツ)_/¯ ]\n\n");

				if (Menu.shouldExit()) {
					save(saver, numbers);
					return;
				}
				continue;
			}
			
			Display.display(numbers);

			numbers = menu.mainMenu(numbers);
			if (menu.menuState == MenuLogic.MenuState.EXIT) {
				save(saver, numbers);
				return;
			}
		}
	}

	private static int[] getNumbers() {
		return (int[]) Input.builder().
				typeInteger().
				stopOnEmptyLine().
				numberValidation().
				getMult();
	}
	
	private static void save(Saver saver, int[] numbers) {
		saver.writeSaveFile(ArrayUtils.toStringArray(numbers));
	}
}
