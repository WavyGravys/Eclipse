package console.box;

import console.box.OutputStepImpl.Type;
import console.box.StepInterfaces.TypeStep;

public class Box {
	
	public static TypeStep builder() {
		return new TypeStepImpl();
	}
	
	public static boolean content(String[][] content, boolean[][] centered) {
		return Box.builder()
				.lineType(Type.SINGLE_LINE_ROUND)
				.newSections(content, centered)
				.showExt();
	}
}
