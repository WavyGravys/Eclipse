package console.input;

import console.input.InputSteps.OutputStep;
import console.input.InputSteps.RangeConfigStep;

public class RangeConfigStepImpl<T> implements RangeConfigStep<T> {
	
	private final InputConfig config;
	
	public RangeConfigStepImpl(InputConfig config) {
        this.config = config;
    }
	
	@Override
	public OutputStep<T> inclusivity(boolean minInc, boolean maxInc) {
		config.minInclusive = minInc;
		config.maxInclusive = maxInc;
		config.validateFunc = Validator.inRange(config.type, config.min, config.max, 
												config.minInclusive, config.maxInclusive);
		return new OutputStepImpl<T>(config);
	}

}
