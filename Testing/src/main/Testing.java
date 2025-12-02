package main;


import java.awt.*;
import javax.swing.*;

import console.output.ProgramMessages;
import math.Graph;
import util.ArrayUtils;
import file.Saver;

//import java.util.Scanner;


public class Testing {
	
	public static void main(String[] args) {
		Saver saver = new Saver("test");
		String[] data = new String[] {"hi", "hello"};
		
		saver.writeSaveFile(data);
		
		System.out.println(saver.readSaveFile()[0]);
	}
}


