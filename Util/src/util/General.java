package util;


public class General {
	
	/**
	 * prints menu and then returns entered choice as an in, once one is input, that matches one of the acceptedMenuOptions
	 * @param menuString the string that is printed to show the menu
	 * @param preInputMessage printed before each input attempt
	 * @param retryMessage printed after each failed input attempt
	 * @param shouldFormat if the inpout should be formatted before parse
	 * @param acceptedMenuOptions array of ints, of which one needs to math the input <p>
	 * @Defaults
	 * <b>menuString</b> <code>"\n========= Menü =========\n 1 - Erneute Eingabe \n 0 - Programm Schließen \n"<code> <br>
	 * <b>preInputMessage</b> <code>"Eingabe: "<code> <br>
	 * <b>retryMessage</b> <code>"ERROR: %s is not a valid input. Please try again \n"<code> <br>
	 * <b>shouldFormat</b> <code>true</code> <br>
	 * <b>acceptedMenuOptions</b> <code>new int[] {0,1}</code>
	 */
	public static int menu(String menuString, String preInputMessage, String retryMessage, Boolean shouldFormat, int[] acceptedMenuOptions) {
		menuString = menuString == null ? "\n========= Menü =========\n 1 - Erneute Eingabe \n 0 - Programm Schließen \n" : menuString;
		shouldFormat = shouldFormat == null ? true : shouldFormat;
		acceptedMenuOptions = acceptedMenuOptions == null ? new int[] {0,1} : acceptedMenuOptions;
		
		System.out.print(menuString);
		return Input.getMatchingInt(preInputMessage, retryMessage, shouldFormat, acceptedMenuOptions);
	}
}
