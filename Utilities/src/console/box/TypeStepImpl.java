package console.box;

import console.box.OutputStepImpl.Type;
import console.box.StepInterfaces.ContentStep;
import console.box.StepInterfaces.TypeStep;

public class TypeStepImpl implements TypeStep{

	@Override
	public ContentStep lineType(Type type) {
		return new ContentStepImpl(new String[][] {}, type);
	}
}
