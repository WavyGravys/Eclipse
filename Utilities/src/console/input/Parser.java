package console.input;

import util.ArrayUtils;
import util.StringUtils;
import util.Types;

public class Parser {
	
	public static Object[] parse(String[] parts, Types type) {
		
		Object[] parsed = new Object[parts.length];
        
        for (int i = 0; i < parts.length; i++) {
            parsed[i] = type.parse(parts[i]);
        }
        
        return parsed;
    }
	
	public static String[] getCleanParts(String input) {
        String[] parts = input.trim().split("[,\\s]+");
        
        if (parts[0].isEmpty() && parts.length > 0) {
            String[] newParts = new String[parts.length - 1];
            System.arraycopy(parts, 1, newParts, 0, parts.length - 1);
            
            return newParts;
        }
        return parts;
    }
	
	public static void printParseConfirmation(String[] parsed) {
    	String parsedNumbers = ArrayUtils.toString(parsed);
    	parsedNumbers = StringUtils.clampString(parsedNumbers, 30);
    	
    	System.out.print("Eingabe erfolgreich als ");
    	System.out.print(parsedNumbers);
    	System.out.print(" geparst.\n");
	}
}
