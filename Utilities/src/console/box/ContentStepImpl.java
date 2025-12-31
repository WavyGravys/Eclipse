package console.box;

import java.util.ArrayList;

import console.box.Box.Type;
import console.box.StepInterfaces.ContentStep;
import console.box.StepInterfaces.OutputStep;

public class ContentStepImpl implements ContentStep{
	
	private ArrayList<String[]> sections;
	private ArrayList<Boolean> centered;
	private Type type;
	
	ContentStepImpl(ArrayList<String[]> sections, ArrayList<Boolean> centered, Type type) { // [sections][lines]
		this.sections = sections;
		this.centered = centered;
		this.type = type;
	}
	
	@Override
	public OutputStep newSection(String[] section, Boolean isCentered) {
		sections.add(section);
		centered.add(isCentered);
		return new OutputStepImpl(sections, centered, type);
	}

	@Override
	public OutputStep newSections(String[][] sections, boolean[] centered) {
		int i = 0;
		for (String[] section : sections) {
			this.sections.add(section);
			this.centered.add(centered[i++]);
		}
		return new OutputStepImpl(this.sections, this.centered, type);
	}

	@Override
	public boolean printEmpty(int width, int height) {
		// TODO
		return false;
	}
}
