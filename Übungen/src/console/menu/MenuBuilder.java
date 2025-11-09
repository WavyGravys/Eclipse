package console.menu;

public class MenuBuilder {
	
	private final MenuConfig config = new MenuConfig();
	
    public MenuBuilder menu(String[] menuStrings) {
    	config.menuStrings = menuStrings;
        return this;
    }
    
    public MenuBuilder prompt(String prompt) {
    	config.prompt = prompt;
    	return this;
    }
    
    public MenuBuilder error(String error) {
        config.error = error;
        return this;
    }
    
    public MenuBuilder options(Integer[] options) {
    	config.options = options;
        return this;
    }
    
    public MenuBuilder shouldFormat(boolean shouldFormat) {
    	config.shouldFormat = shouldFormat;
    	return this;
    }
    
    public MenuBuilder typeDelay(int typeDelay) {
    	config.typeDelay = typeDelay;
    	return this;
    }
    
    public MenuBuilder lineDelay(int lineDelay) {
    	config.lineDelay = lineDelay;
    	return this;
    }
    
    public int show() {
    	MenuDisplay.displayMenu(config);
    	int choice = MenuInput.getSelection(config);
    	System.out.println();
    	return choice;
    }
}
