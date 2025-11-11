package console.input;

import console.input.StepInterfaces.NumberConfigStep;
import console.input.StepInterfaces.OutputStep;
import console.input.StepInterfaces.RangeConfigStep;
import util.Function;

public class NumberConfigStepImpl<T> implements NumberConfigStep<T> {

	private final Config config;

	public NumberConfigStepImpl(Config config) {
		this.config = config;
	}

	@Override
	public NumberConfigStep<T> prompt(String p) {
		config.prompt = p;
		return this;
	}

	@Override
	public NumberConfigStep<T> error(String e) {
		config.error = e;
		return this;
	}

	@Override
	public NumberConfigStep<T> stopOnEmptyLine() {
		config.addExitConditions(new String[] { "" });
		return this;
	}

	@Override
	public NumberConfigStep<T> shouldFormat(boolean f) {
		config.shouldFormat = f;
		return this;
	}

	@Override
	public NumberConfigStep<T> delimiter(String d) {
		config.delimiter = d;
		return this;
	}

	@Override
	public NumberConfigStep<T> exitConditions(String... x) {
		config.addExitConditions(x);
		return this;
	}

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

class RangeConfigStepImpl<T> implements RangeConfigStep<T> {

	private final Config config;

	public RangeConfigStepImpl(Config config) {
		this.config = config;
	}

	@Override
	public OutputStep<T> inclusivity(boolean minInc, boolean maxInc) {
		config.minInclusive = minInc;
		config.maxInclusive = maxInc;
		config.validateFunc = Validator.inRange(config.type, config.min, config.max, config.minInclusive,
				config.maxInclusive);
		return new OutputStepImpl<T>(config);
	}
}
