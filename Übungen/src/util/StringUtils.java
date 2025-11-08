package util;

public class StringUtils {
	public static String clampString(String input, int maxLength) {
		if (input.length() > maxLength) {
    		input = input.substring(0, maxLength - 4);
    		char lastChar = input.charAt(maxLength - 5);
    		
    		if (lastChar == ',') {
    			input = input  + " ";
    		}
    		
    		input = input + "...";
    	}
    	
    	return input;
	}
}
