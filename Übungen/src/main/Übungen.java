package main;

public class Übungen {
	
	public static void main(String[] args) {
		String menuString = "\n===== Übungs Menü =====\n 1 - Tankbelege \n 2 - CarSharing \n 3 - Feldvariablen \n 0 - ENDE \n";
		while (true) {
			byte menuChoice = util.General.instantCustomChoicesMenu(menuString, new int[] {0,1,2,3});
			switch (menuChoice) {
			case 1:
				Tankbelege.start();
				break;
			case 2:
				CarSharing.start();
				break;
			case 3:
				Feldvariablen.start();
				break;
			case 0:
				System.out.println("Programm wurde geschlossen.");
				return;
			}
		}
	}
}
