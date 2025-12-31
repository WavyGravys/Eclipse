package console.menu;

public class Config {
	String[] header = {"- Menü -"};
	String[] menuStrings = new String[] { "1 - Erneute Eingabe", "0 - ZURÜCK" };
	String prompt = " Auswahl: ";
	String error = "ERROR: \"%s\" ist keine valide Auswahl\n";
	Integer[] options = { 0, 1 };
	boolean shouldFormat = false;
}

// {[section]:true, ...}
