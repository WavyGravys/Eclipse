package main;


import java.awt.*;
import javax.swing.*;

import math.Graph;
import util.ArrayUtils;

//import java.util.Scanner;


public class Testing {
	
	public static void main(String[] args) {
		int[] arr = new int[] {3, 2, 9, 8, 1, 0, 3, 5, 0};
		arr = ArrayUtils.quicksortInt(arr, true);
		for (int num : arr) {
			System.out.print(num + ", ");
		}
	}
}


