package main;

import console.box.Box;
import console.box.Box.Type;


public class Testing {
	
	private static final String[] sectionOne = new String[] {
			"In folgendem Programm geben Sie eine Zahlenfolge ein,",
			"welche dann schön dargestellt wird,", "und womit Sie rechenoperationen durchführen können.",
			"Die zahlenreihe kann mit folgenden Zeichen/Zeichenreihen getrennt werden:", 
	};
	private static final String[] sectionTwo = new String[] {
			"' '", 
			"','",
			" ', '"
	};
	private static final String[] sectionThree = new String[] {
			"oder einfach mit Zeilenumbruchen (Enter).", 
			"Wenn sie alle Ihrer Nummern eingegeben haben,",
			"entern Sie eine leere Eingabe", "oder entern Sie eine lehre Eingabe.", 
	};
	
	
	private static final String[] heading = new String[] {
			"- Menü -" 
	};
	private static final String[] menuOptions = new String[] {
			"1 - Zahlen hinzufügen",
			"2 - Zahlen anzeigen",
			"3 - Zahlen sortieren (ansteigend)",
			"4 - Zahlen sortieren (absteigend)",
			"5 - Mathe Operationen", 
			"6 - Zahlen löschen", 
			"0 - ZURÜCK"
	};
	
	
	public static void main(String[] args) {
		testing1();
		System.out.print("\n\n");
		
		testing2();
		System.out.print(" Eingabe: \n\n");
	}
	
	private static boolean testing1() {
		return Box.builder()
				.lineType(Type.THICK_LINE)
				.newSections(new String[][] {sectionOne, sectionTwo, sectionThree} , new boolean[] {false, true, false})
				.print();
	}
	
	private static boolean testing2() {
		return Box.builder()
				.lineType(Type.THICK_LINE)
				.newSection(heading, true)
				.newSection(menuOptions, false)
				.printExt();
	}
}


