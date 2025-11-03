package util;


public class Menu {

	public static MenuBuilder builder() {
        return new MenuBuilder();
    }
    
    public static class MenuBuilder {
        private util.Numbers type = util.Numbers.BYTE;
        private String menuString = "\n========= Menü =========\n 1 - Erneute Eingabe \n 0 - Programm Schließen \n";
        private String prompt = "Auswahl: ";
        private String error = "ERROR: \"%s\" ist keine valide Eingabe. Versuchen Sie es bitte erneut. \n";
        private Byte[] options = {0, 1};
        private boolean shouldFormat = false;
        private boolean displayLoadingBar = true;
        private int loadingBarDotAmount = 22;
        private int loadingBarSpeed = 1;
        
        public MenuBuilder type(util.Numbers type) {
        	this.type = type;
        	return this;
        }
        
        public MenuBuilder menuString(String menuString) {
            this.menuString = menuString;
            return this;
        }
        
        public MenuBuilder prompt(String prompt) {
        	this.prompt = prompt;
        	return this;
        }
        
        public MenuBuilder options(Byte[] options) {
            this.options = options;
            return this;
        }
        
        public MenuBuilder noLoadingBar() {
            this.displayLoadingBar = false;
            return this;
        }
        
        public MenuBuilder loadingBar(int dots, int speed) {
            this.displayLoadingBar = true;
            this.loadingBarDotAmount = dots;
            this.loadingBarSpeed = speed;
            return this;
        }
        
        public <T extends Number> T show() {
            if (displayLoadingBar) {
                Menu.loadingBar(loadingBarDotAmount, loadingBarSpeed);
            }
            
            System.out.print(menuString);
            return util.Input.builder()
            		.prompt(prompt)
            		.error(error)
            		.numbersToMatch(options)
            		.shouldFormat(shouldFormat)
            		.getNumber(type);
        }
    }
    
    /**
     * Displays an animated loading bar.
     * @param dotAmount number of dots to display
     * @param speed animation speed (0 = instant, 1 = normal)
     */
    public static void loadingBar(int dotAmount, int speed) {
    	int timeToSleep;
		if (speed == 0) {
			timeToSleep = 0;
		} else {
			timeToSleep = 17 / speed;
		}
    	
    	System.out.print("\n[");
		for (byte i = 0; i < dotAmount; i++) {
			try {
				Thread.sleep(timeToSleep);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.err.println("Loading bar interrupted");
				break;
			}
			System.out.print(".");
		}
		System.out.print("]");
	}
	
    /**
     * Displays default loading bar with standard settings.
     */
    public static void loadingBar() {
        loadingBar(22, 1);
    }
    
    /**
     * Displays a simple menu asking if user wants to continue or exit.
     * @return true if user chooses to exit (option 0), false to retry (option 1)
     */
    public static boolean shouldExit() {
    	byte choice = builder().show();
    	return choice == 0;
    }
    
    public static <T extends Number> T basic(String menuString, Byte[] options) {
    	return builder()
    			.menuString(menuString)
    			.options(options)
    			.show();
    }
    
    public static <T extends Number> T instantBasic(String menuString, Byte[] options) {
    	return builder()
    			.menuString(menuString)
    			.options(options)
    			.loadingBar(22, 0)
    			.show();
    }
}
