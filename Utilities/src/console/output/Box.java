package console.output;

public class Box {
	
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
			{'\u250F', '\u2513', '\u2517', '\u251B'} };
	private static final char[][] splits = new char[][] {
			{'\u251C', '\u2524'},
			{'\u251C', '\u2524'},
			{'\u255F', '\u2562'},
			{'\u2520', '\u2562'} };
	private static final char[] verticalLines = new char[] 
			{'\u2502', '\u2502', '\u2551', '\u2503'};
	private static final char[] horizontalLines = new char[] 
			{'\u2500', '\u2500', '\u2550', '\u2501'};
	
	public static void draw(int width, int height, String[] content, String header, Type type) {
		//assert content.length == width;
		
		// first line
		printCorner(type, (byte) 0);
		printHorizontalLine(width, type);
		printCorner(type, (byte) 1);
		System.out.println();
		// header
		printHeader(header, type);
		// main body
		for (int y = 1; y < height - 1; y++) {
			printVertical(type);
			
			if (content == null) {
				ProgramMessages.printSpaces(width + 1);
			} else {
				System.out.print(" ");
				int contentIndex = y - 1;
				System.out.print(content[contentIndex]);
				ProgramMessages.printSpaces(width - content[contentIndex].length() + 1);
			}
			printVertical(type);
			System.out.println();
		}
		// last line
		printCorner(type, (byte) 2);
		printHorizontalLine(width, type);
		printCorner(type, (byte) 3);
		System.out.println();
	}
	
	public static void draw(int width, int height, String[] content) {
		draw(width, height, content, Type.THICK_LINE);
	}
	
	public static void draw(int width, int height, Type type) {
		draw(width, height, null, type);
	}
	
	public static void draw(int width, int height) {
		draw(width, height, null, Type.THICK_LINE);
	}
	
	public static void draw(String[] content, Type type) {
		int[] dimensions = calcDimensions(content);
		draw(dimensions[0], dimensions[1] , content, type);
	}
	
	public static void draw(String[] content) {
		draw(content, Type.THICK_LINE);
	}
	
	
	private static int[] calcDimensions(String[] content) {
		int[] dimensions = new int[] {0, content.length + 2};
		
		for (String elem : content) {
			int elemLength = elem.length();
			if (elemLength > dimensions[0]) {
				dimensions[0] = elemLength;
			}
		}
		
		return dimensions;
	}
	
	private static void printCorner(Type boxType, byte cornerLocation) {
		System.out.print(corners[boxType.ordinal()][cornerLocation]);
	}
	
	private static void printHeader(String header, Type type) {
		
	}
	
	private static void printHorizontalLine(int width, Type boxType) {
		for (int x = 1; x < width + 2 + 2 - 1; x++) {
			printHorizontal(boxType);
		}
	}
	
	private static void printVertical(Type boxType) {
		System.out.print(verticalLines[boxType.ordinal()]);
	}
	
	private static void printHorizontal(Type boxType) {
		System.out.print(horizontalLines[boxType.ordinal()]);
	}
}
