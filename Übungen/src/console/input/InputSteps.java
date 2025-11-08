package console.input;

import util.Function;

public class InputSteps {
	
    public interface TypeStep {
    	StringConfigStep typeString();
    	NumberConfigStep<Byte> typeByte();
    	NumberConfigStep<Short> typeShort();
    	NumberConfigStep<Integer> typeInteger();
    	NumberConfigStep<Long> typeLong();
    	NumberConfigStep<Float> typeFloat();
    	NumberConfigStep<Double> typeDouble();
    }
    
    interface BaseConfig<T> {
    	T prompt(String prompt);
    	T error(String error);
    	T stopOnEmptyLine(boolean stop); // only has an impact on getMult
    	T shouldFormat(boolean format);
        T delimiter(String delimiter);
        T exitConditions(String... exits);
    }
    
    public interface StringConfigStep extends BaseConfig<StringConfigStep>{
    	OutputStep<String> validateFunc(Function<String, Boolean> func);
    	OutputStep<String> stringValidation();
    	OutputStep<String> matchingValidation(String... match);
    }
    
    public interface NumberConfigStep<T> extends BaseConfig<NumberConfigStep<T>>{
    	OutputStep<T> validateFunc(Function<String, Boolean> func);
    	OutputStep<T> numberValidation();
    	RangeConfigStep<T> rangeValidation(Number min, Number max);
        OutputStep<T> matchingValidation(Number... match);
    }
    
    public interface RangeConfigStep<T> {
    	OutputStep<T> inclusivity(boolean minInc, boolean maxInc);
    }
    
    public interface OutputStep<T> {
    	T get();
    	Object getMult();
    }
}
