package main;


public class Übungen {
	
	public static void main(String[] args) {
		String menuString = "\n===== Übungs Menü =====\n 1 - Tankbelege \n"
						  + " 2 - CarSharing \n 3 - Feldvariablen \n 0 - ENDE \n";
		byte menuChoice;
		
		while (true) {
			menuChoice = util.Menu.builder()
					.menuString(menuString)
					.options(new Byte[] {0, 1, 2, 3})
					.loadingBar(22, 0)
					.show();
			
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
				
			default:
				break;
			}
		}
	}
}
