package console.box;

import console.box.Box.Type;


public class StepInterfaces {
	
	public interface TypeStep {
		ContentStep lineType(Type type);
	}
	
	public interface ContentStep {
		OutputStep newSection(String[] section, Boolean isCentered);
		OutputStep newSections(String[][] sections, boolean[] isCentered);
		boolean printEmpty(int width, int height);
	}
	
	public interface OutputStep {
		OutputStep newSection(String[] section, Boolean isCentered);
		OutputStep newSections(String[][] sections, boolean[] isCentered);
		boolean print();
		boolean printExt();
	}
}
