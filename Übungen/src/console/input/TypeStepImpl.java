package console.input;

import console.input.InputSteps.NumberConfigStep;
import console.input.InputSteps.StringConfigStep;
import console.input.InputSteps.TypeStep;
import util.Types;

public class TypeStepImpl implements TypeStep {
	
	@Override
    public StringConfigStep typeString() {
        InputConfig config = new InputConfig();
        config.type = Types.STRING;
        return new StringConfigStepImpl(config);
    }

	@Override
	public NumberConfigStep<Byte> typeByte() {
		InputConfig config = new InputConfig();
        config.type = Types.BYTE;
		return new NumberConfigStepImpl<Byte>(config);
	}

	@Override
	public NumberConfigStep<Short> typeShort() {
		InputConfig config = new InputConfig();
        config.type = Types.SHORT;
		return new NumberConfigStepImpl<Short>(config);
	}

	@Override
	public NumberConfigStep<Integer> typeInteger() {
		InputConfig config = new InputConfig();
        config.type = Types.INT;
		return new NumberConfigStepImpl<Integer>(config);
	}

	@Override
	public NumberConfigStep<Long> typeLong() {
		InputConfig config = new InputConfig();
        config.type = Types.LONG;
		return new NumberConfigStepImpl<Long>(config);
	}

	@Override
	public NumberConfigStep<Float> typeFloat() {
		InputConfig config = new InputConfig();
        config.type = Types.FLOAT;
		return new NumberConfigStepImpl<Float>(config);
	}

	@Override
	public NumberConfigStep<Double> typeDouble() {
		InputConfig config = new InputConfig();
        config.type = Types.DOUBLE;
		return new NumberConfigStepImpl<Double>(config);
	}
}
