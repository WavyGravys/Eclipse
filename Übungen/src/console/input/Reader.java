package console.input;

import java.util.Scanner;

import console.output.ProgramMessages;

public class Reader {
    private static final Scanner scan = new Scanner(System.in);

    public static String readLine() {
        if (!scan.hasNextLine()) {
            System.out.println("\nInput gestoppt.");
            ProgramMessages.closeProgram();
        }
        return scan.nextLine();
    }

    public static boolean hasAvailableInput() {
        try {
            return System.in.available() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static void clearScannerCache() {
        while (hasAvailableInput()) {
            try {
				System.in.read();
			} catch (Exception e) {}
        }
	}
}