package übungen.ex05_bankkonten;

import _main.Übung;
import console.input.Input;
import console.menu.Menu;
import console.output.ProgramMessages;
import util.ArrayUtils;

public class Bankkonten implements Übung {
	
	private final String[] explainStrings = new String[] { 
			"Mit folgendem Programm können Sie Ihre banking Konten nutzen." };
	
	private final String[] menu = new String[] {
			" 1 - Girokonto",
			" 2 - Sparkonto",
			" 3 - Geldmarktkonto",
			" 0 - Zurück" };
	private final String[] accountMenu = new String[] {
			" 1 - Einzahlen",
			" 2 - Abheben",
			" 3 - Kontostand anzeigen",
			" 0 - Zurück" };
	
	public void start() {
		ProgramMessages.printWelcomeMessageBoxed(explainStrings);
		
		Konto[] accounts = new Konto[] {new Girokonto(), new Sparkonto(), new Geldmarktkonto()};
		Konto currentAccount;
		
		while (true) {
			byte choice = (byte) Menu.basic(menu, ArrayUtils.integerRange(menu.length));
			if (choice == 0) return;
			currentAccount = accounts[choice - 1];
			
			while (true) {
				double balance = currentAccount.getBalance();
				byte choiceAccount = (byte) Menu.basic(accountMenu, ArrayUtils.integerRange(4));
				if (choiceAccount == 0) break;
				switch (choiceAccount) {
				case 1:
					if (currentAccount.deposit(getInput("Einzuzahlende Menge: "))) {
						System.out.println("Ihr neuer Kontostand ist: " + balance);
					} else {
						System.out.println("Ihre Einzahlung ist Fehlgeschlagen");
					}
					break;
				case 2:
					if (currentAccount.withdraw(getInput("Auszuzahlende Menge: "))) {
						System.out.println("Ihr neuer Kontostand ist: " + balance);
					} else {
						System.out.println("Ihre Abhebung ist Fehlgeschlagen");
					}
					break;
				case 3:
					System.out.println("Ihr aktueller Kontostand ist: " + balance);
					break;
				}
			}
		}
	}
	
	
	private static Integer getInput(String prompt) {
		return Input.builder()
				.typeInteger()
				.prompt(prompt)
				.exitConditions("x")
				.rangeValidation(0, Integer.MAX_VALUE)
				.inclusivity(true, true)
				.get();
	}

}
