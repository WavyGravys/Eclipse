package console.input;

import java.util.ArrayList;

import util.Types;

public class Processor {
	
	public static ArrayList<Object> processInputs(
			 String[] parts, ArrayList<Object> inputs, Types type) {
    	
		Object[] toAdd = Parser.parse(parts, type);
		
		for (Object elem : toAdd) {
    		inputs.add(elem);
    	}
    	
	    return inputs;
	}
}
