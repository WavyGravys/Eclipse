package main;

import console.box.Box;


public class Testing {
	
	private static final String[][] content = new String[][] {
			{"- Menü -"},
			{"1 - Zahlen hinzufügen",
			"2 - Zahlen anzeigen",
			"3 - Zahlen sortieren (ansteigend)",
			"4 - Zahlen sortieren (absteigend)",
			"5 - Mathe Operationen", 
			"6 - Zahlen löschen", 
			"0 - ZURÜCK" }};
	private static final boolean[][] centered = {{true},
			{false,false,false,false,false,false,false}};
	
	public static void main(String[] args) {
		Box.content(content, centered);
		System.out.print(" Eingabe: ");
	}
}


