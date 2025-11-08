package console.output;

import console.input.Input;
import console.input.Reader;
import util.TimeUtils;

public class ConsoleAnimations {
	
	public static boolean linesWithDelay(String[] input, int lineDelay, int startingIndex) {
		for (int i = startingIndex; i < input.length; i++) {
			System.out.println(input[i]);
			
			if (Input.isSkipping(false)) { return false; }
			
			TimeUtils.sleep(lineDelay);
			
			if (Input.isSkipping(false)) { return false; }
		}
		
		return true;
	}
		
	public static boolean typeLines(String[] input, int typeDelay, 
			int wordDelay, int lineDelay, boolean printSkip) {
		
		for (String string : input) {
			if (!typeln(string, typeDelay, wordDelay, lineDelay, printSkip)) {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean typeln(String string, int typeDelay, 
			int wordDelay, int lineDelay, boolean printSkip) {
		
		if (!type(string, typeDelay, wordDelay, printSkip)) { return false; }
		
		TimeUtils.sleep(lineDelay);
		System.out.println();
		
		return true;
	}
	
	public static boolean type(String string, int typeDelay, int wordDelay, boolean printSkip) {
	    String[] words = string.split(" ");

        for (String word : words) {
            for (char ch : word.toCharArray()) {
            	if (Input.isSkipping(printSkip)) {
                    return false;
            	}
            	
            	System.out.print(ch);
                
                if (Input.isSkipping(printSkip)) {
                	return false;
            	}
                
                TimeUtils.sleep(typeDelay);
            }

            TimeUtils.sleep(wordDelay);
            System.out.print(" ");
        }
        
        if (Input.isSkipping(printSkip)) {
            return false;
    	}
        
        Reader.clearScannerCache();
        return true;
	}
}
