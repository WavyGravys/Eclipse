package _übungen.ex03_feldvariablen;

import _main.Übung;
import console.input.Input;
import console.menu.Menu;
import console.output.ProgramMessages;


public class Feldvariablen implements Übung {
	
	public final String[] explainStrings = new String[] {
			"In folgendem Programm geben Sie eine Zahlenfolge ein,", 
			"welche dann schön dargestellt wird,",
			"und womit Sie rechenoperationen durchführen können.",
			"Die zahlenreihe kann mit folgenden Zeichen/Zeichenreihen getrennt werden:", 
			"    ' '", 
			"    ','",
			"    ', '", 
			"oder einfach mit Zeilenumbruchen (Enter).",
			"Wenn sie alle Ihrer Nummern eingegeben haben,",
			"entern Sie eine leere Eingabe", 
			"oder entern Sie eine lehre Eingabe", 
			};
	
	public void start() {
		ProgramMessages.explainProgram(explainStrings);
		
		while (true) {
			int[] numbers =  getNumbers();
			
			if (numbers.length == 0 || numbers == null) {
				System.out.print("[ ¯\\_(ツ)_/¯ ]\n\n");
				
				if (Menu.shouldExit()) {
					return;
				}
				continue;
			}
			
			NumberDisplay.display(numbers);

			if (!MenuLogic.menu(numbers)) {
				return;
			}
		}
	}
	
	private static int[] getNumbers() {
		return (int[]) Input.builder()
				.typeInteger()
				.numberValidation()
				.getMult();
	}
}
