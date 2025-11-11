package console.menu;

public class Builder {

	private final Config config = new Config();

	public Builder menu(String[] menuStrings) {
		config.menuStrings = menuStrings;
		return this;
	}

	public Builder prompt(String prompt) {
		config.prompt = prompt;
		return this;
	}

	public Builder error(String error) {
		config.error = error;
		return this;
	}

	public Builder options(Integer[] options) {
		config.options = options;
		return this;
	}

	public Builder shouldFormat(boolean shouldFormat) {
		config.shouldFormat = shouldFormat;
		return this;
	}

	public Builder typeDelay(int typeDelay) {
		config.typeDelay = typeDelay;
		return this;
	}

	public Builder lineDelay(int lineDelay) {
		config.lineDelay = lineDelay;
		return this;
	}

	public int show() {
		Display.displayMenu(config);
		int choice = Menu.getSelection(config);
		System.out.println();
		return choice;
	}
}
