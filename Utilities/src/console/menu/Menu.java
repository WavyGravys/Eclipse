package console.menu;

import console.input.Input;

public class Menu {
	
    public static MenuBuilder builder() {
        return new MenuBuilder();
    }
    
    public static boolean shouldExit() {
        int choice = builder().show();
        return choice == 0;
    }
    
    public static int basic(String[] menu, Integer[] options) {
        return builder()
                .menu(menu)
                .options(options)
                .show();
    }
    
    public static void loadingBar(int dotAmount, int loadTime, boolean shouldPrintSkipping) {
        MenuDisplay.loadingBar(dotAmount, loadTime, shouldPrintSkipping);
        Input.clearScannerCache();
    }
}
