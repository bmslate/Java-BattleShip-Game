/**
 * CET - CS Academic Level 4
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * Student Name: Huijun Bu Kexin Huang
 * Student Number: 0411121881   041096457   
 * Section #: 301  
 * Course: CST8221 - Java application
 * Assignment: 3.2
 * @author Huijun Bu, Kexin Huang
 *
 */

package Model;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The Computer class is responsible for creating and managing the computer's 
 * game board in the Battleship game. It generates a grid of buttons representing
 * the computer's map.
 */
public class Computer {
	
    // Array of buttons representing the computer's game map
	private JButton[] mapButton;
    // JPanel representing the computer's game map grid
	private JPanel computerMap;

    /**
     * Constructor for the Computer class.
     * Initializes the computer's game map with a 10x10 grid of buttons.
     */
	public Computer () {
        // Initialize the array to hold 100 buttons
		mapButton = new JButton[100];
        // Create the map panel with a GridLayout of 10x10
		computerMap = new JPanel(new GridLayout(10, 10));
        // Set the preferred size of the map panel
		computerMap.setPreferredSize(new Dimension(200, 180));
        // Populate the map panel with buttons
	    for(int i = 0; i < mapButton.length; i++) {
	    	mapButton[i] = new JButton("co" + i);
	    	// Add each button to the map panel
	    	computerMap.add(mapButton[i]);
	    }
	}
	
    /**
     * Returns the JPanel representing the computer's game map.
     * 
     * @return JPanel representing the computer's map
     */
	public JPanel getComputerMap() {
		return computerMap;
	}
	
    /**
     * Returns the array of buttons representing the computer's game map.
     * 
     * @return Array of JButton representing the computer's map
     */
	public JButton[] getMapButton() {
		return mapButton;
	}
}
