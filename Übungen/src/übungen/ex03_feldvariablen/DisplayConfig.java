package übungen.ex03_feldvariablen;

import util.ArrayUtils;

public class DisplayConfig {
	final int INSIDE_BRACKET_OFFSET = 4;
	final int OUTSIDE_BRACKET_OFFSET = 1;
	final String DELIMETER = "  ";
	final boolean IS_DYNAMIC_SPACING = true;
	final boolean IN_LINE_BRACKETS = true;

	int numColumns = 5;
	int[] columnWidths;
	int spacing;
	int loadingBarDotAmount;
	byte fullOffset;

	DisplayConfig(int[] numbers) {
		this.numColumns = updateNumColumns(numbers);
		this.columnWidths = calculateColumnWidths(numbers, this.numColumns);
		this.spacing = calculateSpacing(numbers);
		this.loadingBarDotAmount = calculateLoadingBarDotAmount(this.numColumns, this.columnWidths, this.spacing);
		this.fullOffset = calculateFulOffset();
	}

	public int updateNumColumns(int[] numbers) {
		if (numbers.length < this.numColumns) {
			return numbers.length;
		}
		return this.numColumns;
	}

	public int[] calculateColumnWidths(int[] numbers, int numColumns) {
		int[] columnWidths = new int[numColumns];

		for (int column = 0; column < numColumns; column++) {
			columnWidths[column] = 0;
		}

		for (int index = 0; index < numbers.length; index++) {
			int collumn = index % numColumns;
			int numDigits = String.valueOf(numbers[index]).length();

			if (columnWidths[collumn] < numDigits) {
				columnWidths[collumn] = numDigits;
			}
		}

		return columnWidths;
	}

	public int calculateSpacing(int[] numbers) {
		int spacing = 0;

		for (int index = 0; index < numbers.length; index++) {
			int numDigits = String.valueOf(numbers[index]).length();

			if (spacing < numDigits) {
				spacing = numDigits;
			}
		}

		return spacing;
	}

	public byte calculateFulOffset() {
		return (byte) (INSIDE_BRACKET_OFFSET + OUTSIDE_BRACKET_OFFSET + 1);
	}

	public int calculateLoadingBarDotAmount(int numColumns, int[] columnWidths, int spacing) {
		int allDelimiter = DELIMETER.length() * numColumns;
		int allSpacing = !IS_DYNAMIC_SPACING ? spacing * numColumns : (int) ArrayUtils.sum(columnWidths);
		int offsetWidth = INSIDE_BRACKET_OFFSET * 2 + OUTSIDE_BRACKET_OFFSET * 2;

		return allDelimiter + allSpacing - DELIMETER.length() + offsetWidth;
	}
}