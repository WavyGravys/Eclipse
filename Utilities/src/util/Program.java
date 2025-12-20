package util;

import console.output.ProgramMessages;

public class Program {
	
	public static void close() {
		ProgramMessages.closeProgram();
		System.exit(0);
	}
}
