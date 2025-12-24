package console.box;

import console.box.OutputStepImpl.Type;
import console.box.StepInterfaces.ContentStep;
import console.box.StepInterfaces.OutputStep;

public class ContentStepImpl implements ContentStep{
	
	@SuppressWarnings("unused")
	private String[][] content;
	private Type type;
	
	ContentStepImpl(String[][] content, Type type) { // [sections][lines]
		this.content = content;
		this.type = type;
	}
	
	@Override
	public OutputStep newSections(String[][] content, boolean[][] centered) {
		return new OutputStepImpl(content, centered, type);
	}
	
	public OutputStep emptyBox(int width, int height) {
		
		// TODO
		
		return new OutputStepImpl(new String[0][0], new boolean[0][0], type);
	}
}
