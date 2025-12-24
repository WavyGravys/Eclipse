package console.box;

import console.box.OutputStepImpl.Type;

public class StepInterfaces {
	
	public interface TypeStep {
		ContentStep lineType(Type type);
	}
	
	public interface ContentStep {
		OutputStep newSections(String[][] content, boolean[][] centered);
		OutputStep emptyBox(int width, int height);
	}
	
	public interface OutputStep {
		boolean show();
		boolean showExt();
	}
}
