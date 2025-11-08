package console.menu;

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

    public static void loadingBar(int dotAmount, double delay, boolean shouldPrintSkipping) {
        MenuDisplay.loadingBar(dotAmount, delay, shouldPrintSkipping);
    }
}
