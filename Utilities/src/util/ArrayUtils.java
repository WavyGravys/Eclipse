package util;

public class ArrayUtils {

	public static int[] appendArray(int[] arr1, int[] arr2) {
		int[] result = new int[arr1.length + arr2.length];
		System.arraycopy(arr1, 0, result, 0, arr1.length);
		System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
		return result;
	}

	public static String[] appendArray(String[] arr1, String[] arr2) {
		String[] result = new String[arr1.length + arr2.length];
		System.arraycopy(arr1, 0, result, 0, arr1.length);
		System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
		return result;
	}

	public static String toStringClamped(Object[] array, int length) throws IllegalArgumentException {
		StringBuilder sb = new StringBuilder("[");
		int lastIndex = array.length - 1;
		
		for (int i = 0; i < lastIndex; i++) {
			sb.append(array[i]);
			sb.append(", ");
		}
		sb.append(array[lastIndex]);
		sb.append("]");

		String string = StringUtils.clampString(sb.toString(), length);
		return string;
	}

	public static <T> boolean hasAny(T[] array, T[] toCheck) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(toCheck[i])) {
				return true;
			}
		}
		return false;
	}

	public static int[] bubblesort(int[] array, boolean ascending) {

		Function<Integer, Boolean> isInOrder = index -> {
			if (ascending) {
				return array[index] > array[index + 1];
			} else {
				return array[index] < array[index + 1];
			}
		};

		int lastIndex = array.length - 1;

		while (true) {
			int numSorted = 0;
			for (int i = 0; i < lastIndex; i++) {
				if (isInOrder.apply(i)) {
					int tmp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = tmp;
					numSorted = 0;
				} else {
					numSorted++;
				}
			}

			if (numSorted == lastIndex)
				break;
		}

		return array;
	}

	public static int[] quicksort(int[] array, boolean ascending) {
		if (ascending) {
			return quicksortAsc(array);
		} else {
			return quicksortDesc(array);
		}
	}

	public static int[] quicksortAsc(int[] array) {
		int pivot = array[array.length / 2];
		if (array.length < 2) {
			return array;
		}

		int i = 0;
		int j = array.length - 1;

		while (i <= j) {
			while (array[i] < pivot)
				i++;
			while (array[j] > pivot)
				j--;

			if (i <= j) {
				int tmp = array[i];
				array[i] = array[j];
				array[j] = tmp;
				i++;
				j--;
			} else {
				break;
			}
		}

		if (i < 2 || i > array.length - 2) {
			return array;
		}

		int[] leftPart = new int[i];
		System.arraycopy(array, 0, leftPart, 0, i);
		int[] rightPart = new int[array.length - i];
		System.arraycopy(array, i, rightPart, 0, array.length - i);

		leftPart = quicksortAsc(leftPart);
		rightPart = quicksortAsc(rightPart);

		return ArrayUtils.appendArray(leftPart, rightPart);
	}

	public static int[] quicksortDesc(int[] array) {
		if (array.length < 2) {
			return array;
		}

		int pivot = array[array.length / 2];
		int i = 0;
		int j = array.length - 1;

		while (i <= j) {
			while (array[i] > pivot)
				i++;
			while (array[j] < pivot)
				j--;

			if (i <= j) {
				int tmp = array[i];
				array[i] = array[j];
				array[j] = tmp;
				i++;
				j--;
			} else {
				break;
			}
		}

		if (i == 0 || i == array.length) {
			return array;
		}

		int[] leftPart = new int[i];
		System.arraycopy(array, 0, leftPart, 0, i);
		int[] rightPart = new int[array.length - i];
		System.arraycopy(array, i, rightPart, 0, array.length - i);

		leftPart = quicksortDesc(leftPart);
		rightPart = quicksortDesc(rightPart);

		return ArrayUtils.appendArray(leftPart, rightPart);
	}

	public static String toString(int[] array) throws IllegalArgumentException {
		if (array.length == 0) {
			return "";
		}

		StringBuilder sb = new StringBuilder("[");

		for (int i = 0; i < array.length - 1; i++) {
			sb.append(array[i]);
			sb.append(", ");
		}
		sb.append(array[array.length - 1]);
		sb.append("]");

		return sb.toString();
	}
	
	public static String[] toStringArray(int[] array) {
		String[] strings = new String[array.length];
		
		for (int i = 0; i < array.length; i++) {
			strings[i] = Integer.toString(array[i]);
		}
		
		return strings;
	}
	
	public static int[] toIntArray(String[] array) {
		int[] ints = new int[array.length];
		
		for (int i = 0; i < array.length; i++) {
			ints[i] = Integer.parseInt(array[i]);
		}
		
		return ints;
	}

	public static void validateIntArray(int[] array) {
		if (array.length == 0) {
			throw new IllegalArgumentException("array cannot be empty");
		}
	}

	public static void validateDoubleArray(double[] array) {
		if (array.length == 0) {
			throw new IllegalArgumentException("array cannot be empty");
		}
	}

	public static long sum(int[] array) throws IllegalArgumentException {
		validateIntArray(array);

		long sum = 0;

		for (int number : array) {
			sum += number;
		}

		return sum;
	}

	public static double sum(double[] array) throws IllegalArgumentException {
		validateDoubleArray(array);

		double sum = 0;

		for (double number : array) {
			sum += number;
		}

		return sum;
	}

	public static long sumInRange(int[] array, int startIndex, int lastIndex) throws IllegalArgumentException {
		validateIntArray(array);

		long sum = 0;

		for (int i = startIndex; i <= lastIndex; i++) {
			sum += array[i];
		}

		return sum;
	}

	public static long prod(int[] array) throws IllegalArgumentException {
		validateIntArray(array);

		long prod = 1;

		for (int number : array) {
			prod *= number;
		}

		return prod;
	}

	public static int min(int[] array) throws IllegalArgumentException {
		validateIntArray(array);

		int min = array[0];

		for (int i = 1; i < array.length; i++) {
			int numAtIndex = array[i];
			
			if (numAtIndex < min) {
				min = numAtIndex;
			}
		}

		return min;
	}

	public static int max(int[] array) throws IllegalArgumentException {
		validateIntArray(array);

		int max = array[0];

		for (int i = 1; i < array.length; i++) {
			int numAtIndex = array[i];

			if (numAtIndex > max) {
				max = numAtIndex;
			}
		}

		return max;
	}

	public static double avg(int[] array) throws IllegalArgumentException {
		validateIntArray(array);

		long sum = sum(array);
		return (double) sum / (double) array.length;
	}

	public static double avg(double[] array) {
		validateDoubleArray(array);

		double sum = sum(array);
		return sum / (double) array.length;
	}

	public static int[] intRange(int n) {
		int[] array = new int[n];

		for (int i = 0; i < n; i++) {
			array[i] = i;
		}

		return array;
	}

	public static Integer[] integerRange(int n) {
		Integer[] array = new Integer[n];

		for (Integer i = 0; i < n; i++) {
			array[i] = i;
		}

		return array;
	}

	public static Object toPrimitive(Types type, Object[] boxed) {
		int len = boxed.length;
		return switch (type) {
		case STRING -> boxed;
		case BYTE -> {
			byte[] arr = new byte[len];
			for (int i = 0; i < len; i++)
				arr[i] = (Byte) boxed[i];
			yield arr;
		}
		case SHORT -> {
			short[] arr = new short[len];
			for (int i = 0; i < len; i++)
				arr[i] = (Short) boxed[i];
			yield arr;
		}
		case INT -> {
			int[] arr = new int[len];
			for (int i = 0; i < len; i++)
				arr[i] = (Integer) boxed[i];
			yield arr;
		}
		case LONG -> {
			long[] arr = new long[len];
			for (int i = 0; i < len; i++)
				arr[i] = (Long) boxed[i];
			yield arr;
		}
		case FLOAT -> {
			float[] arr = new float[len];
			for (int i = 0; i < len; i++)
				arr[i] = (Float) boxed[i];
			yield arr;
		}
		case DOUBLE -> {
			double[] arr = new double[len];
			for (int i = 0; i < len; i++)
				arr[i] = (Double) boxed[i];
			yield arr;
		}
		default -> throw new IllegalArgumentException("Unexpected value: " + type);
		};
	}
}
