package main;


import java.awt.*;
import javax.swing.*;

//import java.util.Scanner;


public class Testing {
	
	private int count = 0;
    
    public static void main(String[] args) {
        new Testing().createGUI();
    }
    
    public void createGUI() {
        // Create window
        JFrame frame = new JFrame("Counter App");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        // Create label to show count
        JLabel label = new JLabel("Count: 0", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        
        // Create button
        JButton button = new JButton("Click Me!");
        button.addActionListener(_ -> {
            count++;
            label.setText("Count: " + count);
        });
        
        // Add components
        frame.add(label, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);
        
        // Show window
        frame.setVisible(true);
    }
}


