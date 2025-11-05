package util;


public class Menu {

	public static MenuBuilder builder() {
        return new MenuBuilder();
    }
    
    public static class MenuBuilder {
        private Numbers type = Numbers.INT;
        private String menuString = "\n========= Menü =========\n 1 - Erneute Eingabe \n 0 - ZURÜCK \n";
        private String prompt = "Auswahl: ";
        private String error = Input.InputBuilder.error;
        private Integer[] options = {0, 1};
        private boolean shouldFormat = false;
        private boolean displayLoadingBar = true;
        private int loadingBarDotAmount = 22;
        private int loadingBarSpeed = 10;
        
        public MenuBuilder type(Numbers type) {
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
        
        public MenuBuilder options(Integer[] options) {
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
                Menu.loadingBar(loadingBarDotAmount, loadingBarSpeed, false);
            }
            
            System.out.print(menuString);
            return Input.builder()
            		.numType(type)
            		.prompt(prompt)
            		.error(error)
            		.numbersToMatch(options)
            		.shouldFormat(shouldFormat)
            		.getNumber();
            
        }
    }
    
    
    public static void loadingBar(int dotAmount, double delay, boolean shouldPrintSkipping) {
    	delay = delay * 25d / (double) dotAmount;
    	
    	System.out.print("[");
    	
		for (byte i = 0; i < dotAmount; i++) {
			General.sleep((int) delay);
			
			if (Input.isSkipping(shouldPrintSkipping)) {
                return;
        	}
			
			System.out.print(".");
        	
			if (Input.isSkipping(shouldPrintSkipping)) {
                return;
        	}
		}
		
		System.out.print("]");
		
		General.clearScannerCache();
		return;
	}
    
    public static boolean shouldExit() {
    	int choice = (int) builder().show();
    	return choice == 0;
    }
    
    public static <T extends Number> T basic(String menuString, Integer[] options) {
    	return builder()
    			.menuString(menuString)
    			.options(options)
    			.loadingBar(22, 15)
    			.show();
    }
    
    public static <T extends Number> T instantBasic(String menuString, Integer[] options) {
    	return builder()
    			.menuString(menuString)
    			.options(options)
    			.loadingBar(22, 0)
    			.show();
    }
}
