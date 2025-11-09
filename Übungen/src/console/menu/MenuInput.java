package console.menu;

import console.input.Input;

public class MenuInput {
	
	/**
	 *  returns 0 if user pressed enter without an input
	 */
    public static int getSelection(MenuConfig config) {
        Integer choice = Input.builder()
                .typeInteger()
                .prompt(config.prompt)
                .error(config.error)
                .shouldFormat(config.shouldFormat)
                .stopOnEmptyLine()
                .matchingValidation(config.options)
                .get();
        
        if (choice == null) {
        	return (int) 0;
        }
        return (int) choice;
    }
}