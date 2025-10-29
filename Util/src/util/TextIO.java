package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class TextIO {
	
	public static void writeMatrix {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt"));
			writer.write("hello");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void write {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt"));
			writer.write("hello");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
