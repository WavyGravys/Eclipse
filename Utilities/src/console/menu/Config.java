package console.menu;

public class Config {
	String[] menuStrings = new String[] { "[═══════] Menü [═══════]", " 1 - Erneute Eingabe", " 0 - ZURÜCK" };
	String prompt = "Auswahl: ";
	String error = "ERROR: \"%s\" ist keine valide Auswahl\n";
	Integer[] options = { 0, 1 };
	boolean shouldFormat = false;
	int typeDelay = 15;
	int lineDelay = 0;
}
