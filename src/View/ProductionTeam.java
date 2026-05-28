/**
 * CET - CS Academic Level 4
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * Student Name: Huijun Bu, Kexin Huang
 * Student Number: 0411121881, 041096457
 * Section #: 301
 * Course: CST8221 - Java application
 * Assignment: 3.2
 * @author Huijun Bu, Kexin Huang
 *
 */
package View;

import javax.swing.*;
import java.awt.*;

/**
 * The ProductionTeam class represents a simple GUI window that displays the production team information.
 * It includes a logo image and a text area listing the team members.
 */
public class ProductionTeam {

    /**
     * Constructor for the ProductionTeam class.
     * Initializes the GUI components and displays the production team information in a new window.
     */
    public ProductionTeam() {
        // Create the main frame for the window
        JFrame frame = new JFrame("Production Team");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(980, 800);
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setLayout(new BorderLayout());

        // Create a content panel to hold the components
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the panel

        // Add an image to the panel
        ImageIcon teamImage = new ImageIcon("battleApplogo.png"); // Replace with actual image path
        JLabel imageLabel = new JLabel(teamImage);
        imageLabel.setHorizontalAlignment(JLabel.CENTER); // Center the image in the panel
        contentPanel.add(imageLabel, BorderLayout.CENTER);

        // Add text information about the production team
        JTextArea textArea = new JTextArea();
        textArea.setText("Production Team:\n\n1. Huijun Bu\n2. Kexin Huang\n\nThanks for playing our game!");
        textArea.setEditable(false); // Make the text area non-editable
        textArea.setFont(new Font("Arial", Font.PLAIN, 16)); // Set the font for the text
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the text
        contentPanel.add(textArea, BorderLayout.SOUTH); // Place the text area at the bottom of the panel

        // Add the content panel to the main frame
        frame.add(contentPanel);
        frame.setVisible(true); // Display the frame
    }
}

// The main method is commented out, but it can be used to test the ProductionTeam class independently.
// public static void main(String[] args) {
//    new ProductionTeam();
// }
