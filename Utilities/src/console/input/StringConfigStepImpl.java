package console.input;

import console.input.StepInterfaces.OutputStep;
import console.input.StepInterfaces.StringConfigStep;
import util.Function;

public class StringConfigStepImpl implements StringConfigStep {

	private final Config config;

	public StringConfigStepImpl(Config config) {
		this.config = config;
	}

	@Override
	public StringConfigStep prompt(String p) {
		config.prompt = p;
		return this;
	}

	@Override
	public StringConfigStep error(String e) {
		config.error = e;
		return this;
	}

	@Override
	public StringConfigStep stopOnEmptyLine() {
		config.addExitConditions(new String[] { "" });
		return this;
	}

	@Override
	public StringConfigStep shouldFormat(boolean f) {
		config.shouldFormat = f;
		return this;
	}

	@Override
	public StringConfigStep delimiter(String d) {
		config.delimiter = d;
		return this;
	}

	@Override
	public StringConfigStep exitConditions(String... x) {
		config.addExitConditions(x);
		return this;
	}

	@Override
	public OutputStep<String> validateFunc(Function<String, Boolean> func) {
		config.validateFunc = func;
		return new OutputStepImpl<String>(config);
	}

	@Override
	public OutputStep<String> stringValidation() {
		config.validateFunc = Validator.string();
		return new OutputStepImpl<String>(config);
	}

	@Override
	public OutputStep<String> matchingValidation(String... match) {
		config.validateFunc = Validator.matching(config.type, match);
		return new OutputStepImpl<String>(config);
	}

}