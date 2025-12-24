package console.box;

import console.box.StepInterfaces.OutputStep;
import console.output.ProgramMessages;

public class OutputStepImpl implements OutputStep{
	
	private String[][] content;
	private boolean[][] centered;
	private Type type;
	
	OutputStepImpl(String[][] content, boolean[][] centered, Type type) { // [sections][lines]
		this.content = content;
		this.centered = centered;
		this.type = type;
	}
	
	
	public enum Type {
		SINGLE_LINE_ROUND,
		SINGLE_LINE_SQUARE,
		DOUBLE_LINE,
		THICK_LINE
	}
	
	private static final char[][] corners = new char[][] {
		{'\u256D', '\u256E', '\u2570', '\u256F'},
		{'\u250C', '\u2510', '\u2514', '\u2518'},
		{'\u2554', '\u2557', '\u255A', '\u255D'},
		{'\u250F', '\u2513', '\u2517', '\u251B'}};
	private static final char[][] splits = new char[][] {
		{'\u251C', '\u2524'},
		{'\u251C', '\u2524'},
		{'\u255F', '\u2562'},
		{'\u2520', '\u2528'}};
	private static final char[] verticalLines = new char[] 
		{'\u2502', '\u2502', '\u2551', '\u2503'};
	private static final char[] horizontalLines = new char[] 
		{'\u2500', '\u2500', '\u2550', '\u2501'};
	private static final char[] splitsExt = new char[]
		{'\u251C','\u251C','\u2560','\u2523'};
	
	
	@Override
	public boolean show() {
		int width = calcWidth(this.content);
		
		printFirstLine(width, type);
		
		for (int i = 0; i < content.length; i++) {
			String[] section = content[i];
			printSection(width, section.length + 2, section, centered[i], type);
			
			if (i != content.length - 1) {
				printSplit(width);
			}
		}
		
		printLastLine(width, type);
		
		return true;
	}
	
	
	private static int calcWidth(String[][] content) {
		int width = 0;
		for (String[] section : content) {
			for (String line : section) {
				int lineLength = line.length();
				if (width < lineLength) {
					width = lineLength;
				}
			}
		}
		return width;
	}
	
	private static void printFirstLine(int width, Type type) {
		printCorner(type, (byte) 0);
		printHorizontalLine(width, type);
		printCorner(type, (byte) 1);
		System.out.println();
	}
	
	private static void printLastLine(int width, Type type) {
		printCorner(type, (byte) 2);
		printHorizontalLine(width, type);
		printCorner(type, (byte) 3);
		System.out.println();
	}
	
	private void printSection(int width, int height, String[] section, boolean[] centered, Type type) {
		if (section.length == 0 || section == null) {
			for (int y = 0; y < height - 2; y++) {
				printEmptyContentLine(width);
			}
		} else {
			for (int y = 0; y < height - 2; y++) {
				printContentLine(width, section[y], centered[y]);
			}
		}
	}
	
	private void printEmptyContentLine(int width) {
		printVertical(type);
		ProgramMessages.printSpaces(width + 1);
		printVertical(type);
		System.out.println();
	}
	
	private static void printVertical(Type boxType, boolean isSplit, byte location) {
		if (isSplit) {
			System.out.print(splits[boxType.ordinal()][location]);
		} else {
			System.out.print(verticalLines[boxType.ordinal()]);
		}
	}
	
	private static void printCorner(Type boxType, byte cornerLocation) {
		System.out.print(corners[boxType.ordinal()][cornerLocation]);
	}
	
	private static void printHorizontalLine(int width, Type boxType) {
		for (int x = 1; x < width + 2 + 2 - 1; x++) {
			printHorizontal(boxType);
		}
	}
	
	
	
	private void printContentLine(int width, String line, boolean centered) {
		int spacesBefore = 1;
		int spacesAfter = width - line.length() + 1;
		if (centered) {
			int spaces = spacesBefore + spacesAfter;
			spacesBefore = spaces / 2;
			spacesAfter = spaces / 2;
			if (spaces % 2 != 0) spacesAfter++;
		}
		printVertical(type);
		ProgramMessages.printSpaces(spacesBefore);
		System.out.print(line);
		ProgramMessages.printSpaces(spacesAfter);
		printVertical(type);
		System.out.println();
	}
	
	private void printSplit(int width) {
		printVertical(type, true, (byte) 0);
		printHorizontalLine(width, Type.SINGLE_LINE_SQUARE);
		printVertical(type, true, (byte) 1);
		System.out.println();
	}
	
	private static void printVertical(Type boxType) {
		printVertical(boxType, false, (byte) 0);
	}
	
	private static void printHorizontal(Type boxType) {
		System.out.print(horizontalLines[boxType.ordinal()]);
	}


	@Override
	public boolean showExt() {
		int width = calcWidth(this.content);
		
		printFirstLine(width, type);
		
		for (int i = 0; i < content.length; i++) {
			String[] section = content[i];
			printSection(width, section.length + 2, section, centered[i], type);
			
			if (i != content.length - 1) {
				printSplit(width);
			}
		}
		
		System.out.print(splitsExt[type.ordinal()]);
		printHorizontalLine(width, type);
		printCorner(type, (byte) 3);
		System.out.println();
		printVertical(type);
		
		return true;
	}
	
	
}
