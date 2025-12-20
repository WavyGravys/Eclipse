package main;

import console.output.Box;


public class Testing {
	
	private static final String[] content = new String[] {
			"Hello World!",
			"Hows it goin'? ,_,"};
	
	public static void main(String[] args) {
		Box.draw(content, Box.Type.DOUBLE_LINE);
	}
}


