package console.menu;

import console.input.Input;

public class MenuInput {
    
    public static int getSelection(MenuConfig config) {
        return Input.builder()
                .typeInteger()
                .prompt(config.prompt)
                .error(config.error)
                .shouldFormat(config.shouldFormat)
                .matchingValidation(config.options)
                .get();
    }
}