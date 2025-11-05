package main;

//import java.util.Scanner;


public class Testing {
	
	public static void main(String[] args) {
		String input = " 3 23 32 2 32 23 32 322 33 232 32  2323 32  3232 32  2323 32 23 2 323 23 ";
		String[] startingParts = input.split("[,\\s]+");
    	String[] parts;
    	if (startingParts[0].length() == 0) {
    		parts = new String[startingParts.length - 1];
    		System.arraycopy(startingParts, 1, parts, 0, startingParts.length - 1);
		} else {
			parts = startingParts;
		}
    	
    	for (String s : parts) {
    		System.out.println(s);
    	}
    	
    	System.out.println(parts.length);
	}
	
}

