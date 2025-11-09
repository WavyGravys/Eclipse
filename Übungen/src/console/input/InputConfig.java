package console.input;

import util.Function;
import util.Types;

public class InputConfig {
	Types type;
	public String prompt = "Eingabe: ";
    public String error = "ERROR: \"%s\" ist keine valide Eingabe\n";
    boolean shouldFormat = false;
    String delimiter = ", ";
    String[] exitConditions = new String[] {""};
    Function<String, Boolean> validateFunc = null;
    Number min = 0;
    Number max = Integer.MAX_VALUE;
    boolean minInclusive = true;
    boolean maxInclusive = true;
    Object[] toMatch = null;
}