package util;


public class Print {
	
	public static void printLinesWithDelay(String[] input, int lineDelay) {
		for (String string : input) {
			System.out.println(string);
			General.sleep(lineDelay);
		}
		
		return;
	}
		
	public static boolean typeLines(String[] input, int typeDelay, int wordDelay, int lineDelay) {
		for (String string : input) {
			boolean finishedTyping = typeln(string, typeDelay, wordDelay, lineDelay);
			
			if (!finishedTyping) {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean typeln(String string, int typeDelay, 
								 int wordDelay, int lineDelay) {
		boolean finishedTyping = type(string, typeDelay, wordDelay);
		
		if (!finishedTyping) {
			return false;
		}
		
		General.sleep(lineDelay);
		System.out.println();
		
		return true;
	}
	
	public static boolean type(String string, int typeDelay, int wordDelay) {
	    String[] words = string.split(" ");

        for (String word : words) {
            char[] chars = word.toCharArray();

            for (char ch : chars) {
            	if (Input.isSkipping(true)) {
                    return false;
            	}
            	
            	System.out.print(ch);
                
                if (Input.isSkipping(true)) {
                    return false;
            	}
                
                General.sleep(typeDelay);
            }

            General.sleep(wordDelay);
            System.out.print(" ");
        }
        
        if (Input.isSkipping(true)) {
            return false;
    	}
        
        General.clearScannerCache();
        return true;
	    
	    
	}
	
	public static void spaces(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(" ");
		}
	}
}
