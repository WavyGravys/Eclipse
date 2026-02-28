package eingabe;

import java.util.Scanner;

import helfer.Drucken;

public class Leser {
	private static final Scanner scan = new Scanner(System.in);

	public static String zeileEinlesen() {
		if (!scan.hasNextLine()) {
			System.out.println("\nInputstream wurde geschlossen.");
			Drucken.programmSchließen();
		}
		return scan.nextLine();
	}
}