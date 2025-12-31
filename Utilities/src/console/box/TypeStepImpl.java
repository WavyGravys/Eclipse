package console.box;

import java.util.ArrayList;

import console.box.Box.Type;
import console.box.StepInterfaces.ContentStep;
import console.box.StepInterfaces.TypeStep;

public class TypeStepImpl implements TypeStep{

	@Override
	public ContentStep lineType(Type type) {
		return new ContentStepImpl(new ArrayList<String[]>(), new ArrayList<Boolean>(), type);
	}
}
