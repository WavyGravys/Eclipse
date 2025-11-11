package console.input;

import console.input.StepInterfaces.NumberConfigStep;
import console.input.StepInterfaces.StringConfigStep;
import console.input.StepInterfaces.TypeStep;
import util.Types;

public class TypeStepImpl implements TypeStep {
	
	@Override
    public StringConfigStep typeString() {
        Config config = new Config();
        config.type = Types.STRING;
        return new StringConfigStepImpl(config);
    }

	@Override
	public NumberConfigStep<Byte> typeByte() {
		Config config = new Config();
        config.type = Types.BYTE;
		return new NumberConfigStepImpl<Byte>(config);
	}

	@Override
	public NumberConfigStep<Short> typeShort() {
		Config config = new Config();
        config.type = Types.SHORT;
		return new NumberConfigStepImpl<Short>(config);
	}

	@Override
	public NumberConfigStep<Integer> typeInteger() {
		Config config = new Config();
        config.type = Types.INT;
		return new NumberConfigStepImpl<Integer>(config);
	}

	@Override
	public NumberConfigStep<Long> typeLong() {
		Config config = new Config();
        config.type = Types.LONG;
		return new NumberConfigStepImpl<Long>(config);
	}

	@Override
	public NumberConfigStep<Float> typeFloat() {
		Config config = new Config();
        config.type = Types.FLOAT;
		return new NumberConfigStepImpl<Float>(config);
	}

	@Override
	public NumberConfigStep<Double> typeDouble() {
		Config config = new Config();
        config.type = Types.DOUBLE;
		return new NumberConfigStepImpl<Double>(config);
	}
}
