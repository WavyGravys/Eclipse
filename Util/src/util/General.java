package util;


public class General {
	
	/**
	 * prints menu and then returns entered choice as an in, once one is input, that matches one of the acceptedMenuOptions
	 * @param menuString the string that is printed to show the menu
	 * @param preInputMessage printed before each input attempt
	 * @param retryMessage printed after each failed input attempt
	 * @param shouldFormat if the inpout should be formatted before parse
	 * @param acceptedMenuOptions array of ints, of which one needs to math the input
	 * @param loadingBarDotAmount how many dots should be printed as the loading bar
	 * @param loadingBarSpeed how fast the loading bar is printed <p>
	 * @Defaults
	 * <b>menuString</b> <code>"\n========= Menü =========\n 1 - Erneute Eingabe \n 0 - Programm Schließen \n"<code> <br>
	 * <b>preInputMessage</b> <code>"Eingabe: "<code> <br>
	 * <b>retryMessage</b> <code>"ERROR: %s is not a valid input. Please try again \n"<code> <br>
	 * <b>shouldFormat</b> <code>true</code> <br>
	 * <b>acceptedMenuOptions</b> <code>new int[] {0,1}</code>
	 * <b>loadingBarDotAmount</b> <code>22<code> <br>
	 * <b>loadingBarSpeed</b> <code>1<code>
	 */
	public static int menu(String menuString, String preInputMessage, String retryMessage, 
			Boolean shouldFormat, int[] acceptedMenuOptions, Integer loadingBarDotAmount, Integer loadingBarSpeed) {
		
		menuString = menuString == null ? "\n========= Menü =========\n 1 - Erneute Eingabe \n 0 - Programm Schließen \n" : menuString;
		shouldFormat = shouldFormat == null ? true : shouldFormat;
		acceptedMenuOptions = acceptedMenuOptions == null ? new int[] {0,1} : acceptedMenuOptions;
		
		loadingBar(loadingBarDotAmount, loadingBarSpeed);
		System.out.print(menuString);
		return Input.getMatchingInt(preInputMessage, retryMessage, shouldFormat, acceptedMenuOptions);
	}
	
	
	/**
	 * prints a small loading bar.
	 * @param dotAmount how many dots should be printed as the loading bar
	 * @param speed how fast the loading bar is printed <p>
	 * @Defaults
	 * <b>dotAmount</b> <code>22<code> <br>
	 * <b>speed</b> <code>1<code>
	 */
	public static void loadingBar(Integer dotAmount, Integer speed) {
		dotAmount = dotAmount == null ? 22 : dotAmount;
		int timeToSleep = speed == null ? 17 : 17/speed;
		
		System.out.print("\n[");
		for (byte i=0;i<dotAmount;i++) {
			try { Thread.sleep(timeToSleep);
			} catch (Exception e) {}
			System.out.print(".");
		}
		System.out.print("]");
	}
}
