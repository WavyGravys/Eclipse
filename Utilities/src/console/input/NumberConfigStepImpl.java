package console.input;

import console.input.InputSteps.NumberConfigStep;
import console.input.InputSteps.OutputStep;
import console.input.InputSteps.RangeConfigStep;
import util.Function;

public class NumberConfigStepImpl<T> implements NumberConfigStep<T>{
	
	private final InputConfig config;
	
	public NumberConfigStepImpl(InputConfig config) {
        this.config = config;
    }
	
	@Override public NumberConfigStep<T> prompt(String p) {config.prompt = p; return this;}
	@Override public NumberConfigStep<T> error(String e) {config.error = e; return this;}
	@Override public NumberConfigStep<T> stopOnEmptyLine() {
		config.exitConditions = Validator.getDefaultExitConditions(config.exitConditions);
		return this;
	}
	@Override public NumberConfigStep<T> shouldFormat(boolean f) {config.shouldFormat = f; return this;}
	@Override public NumberConfigStep<T> delimiter(String d) {config.delimiter = d; return this;}
	@Override public NumberConfigStep<T> exitConditions(String... x) {config.exitConditions = x; return this;}
	
	@Override
	public OutputStep<T> validateFunc(Function<String, Boolean> func) {
		config.validateFunc = func;
		return new OutputStepImpl<T>(config);
	}

	@Override
	public OutputStep<T> numberValidation() {
		config.validateFunc = Validator.number(config.type);
		return new OutputStepImpl<T>(config);
	}
	
	@Override
	public RangeConfigStep<T> rangeValidation(Number min, Number max) {
		config.min = min;
		config.max = max;
		return new RangeConfigStepImpl<T>(config);
	}

	@Override
	public OutputStep<T> matchingValidation(Number... match) {
		config.validateFunc = Validator.matching(config.type, match);
		config.toMatch = match;
		return new OutputStepImpl<T>(config);
	}
}
