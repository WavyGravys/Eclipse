package console.output;

public class Text {
	public static enum Language {ENGLISH, DEUTSCH};
	
	public static String welcome;
	public static String skip;

	public Text(Language language) {
		switch (language) {
		case DEUTSCH:
			welcome = "[══════] Wilkommen [══════]";
			skip = "(drücken Sie Enter um zu Skippen)";
			
			
			
		case ENGLISH:
			
		}
	}
	
}
