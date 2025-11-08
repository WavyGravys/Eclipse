package console.input;

import java.util.ArrayList;

import util.Types;

public class Processor {
	
	public static ArrayList<Object> processInput(String input, ArrayList<Object> inputs, Types type) {
    	Object[] toAdd = Parser.parse(input, type);
    	
    	for (Object elem : toAdd) {
    		inputs.add(elem);
    	}
    	
	    return inputs;
	}
}
