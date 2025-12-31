package console.menu;

public class Builder {
	
	private final Config config = new Config();
	
	public Builder header(String header) {
		config.header = new String[] {header};
		return this;
	}
	
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

	public int get() {
		Display.menu(config);
		int choice = Menu.getSelection(config);
		System.out.println();
		return choice;
	}
}
