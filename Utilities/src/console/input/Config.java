package console.input;

import util.ArrayUtils;
import util.Function;
import util.Types;

public class Config {
	Types type;
	public String prompt = "Eingabe: ";
    public String error = "ERROR: \"%s\" ist keine valide Eingabe\n";
    boolean shouldFormat = false;
    String delimiter = ", ";
    String[] exitConditions = new String[] {};
    Function<String, Boolean> validateFunc = null;
    Number min = 0;
    Number max = Integer.MAX_VALUE;
    boolean minInclusive = true;
    boolean maxInclusive = true;
    Object[] toMatch = null;
    
    public void addExitConditions(String[] conditionsToAdd) {
    	if (exitConditions == null || exitConditions.length == 0) {
			exitConditions = conditionsToAdd;
		} else if (!ArrayUtils.hasAny(exitConditions, conditionsToAdd) ) {
			exitConditions = ArrayUtils.appendStringArray(exitConditions, conditionsToAdd);
		}
	}
}