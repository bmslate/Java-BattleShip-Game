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
package View;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * The Strategy class provides a user interface for configuring the layout and positioning of battleships in the game.
 * It follows a singleton pattern to ensure only one instance is created.
 * The interface allows players to save, reset, and select pre-defined strategies.
 * The UI is fully localizable with different languages.
 */
public class Strategy {

    public JFrame strategyFrame; // Main frame for the strategy interface
    private JButton[] mapButton; // Array of buttons representing the grid map
    private JButton save; // Button for saving the strategy
    private JButton back; // Button for going back to the previous screen
    private JButton reset; // Button for resetting the current strategy
    private JButton s1, s2, s3, s4, s5; // Buttons for selecting predefined strategies

    private JPanel map; // Panel representing the grid map
    private JLabel battleShip2, battleShip3, battleShip4, battleShip5; // Labels for the different battleships

    private ResourceBundle messages; // Resource bundle for localized text
    private Locale locale; // Current locale
    private static Strategy instance; // Singleton instance of the Strategy class

    /**
     * Private constructor for Strategy class, initializing components with the specified locale.
     * 
     * @param locale The locale for localization.
     */
    private Strategy(Locale locale) {
        this.locale = locale;
        loadResourceBundle();
        initializeComponents();
    }

    /**
     * Singleton pattern implementation. Returns the single instance of Strategy.
     * 
     * @param locale The locale for localization.
     * @return The single instance of Strategy.
     */
    public static synchronized Strategy getInstance(Locale locale) {
        if (instance == null) {
            instance = new Strategy(locale);
        }
        return instance;
    }

    /**
     * Loads the resource bundle based on the current locale.
     */
    private void loadResourceBundle() {
        this.messages = ResourceBundle.getBundle("MessagesBundle", this.locale);
    }

    /**
     * Updates the text of UI components based on the current locale.
     */
    private void updateUIText() {
        if (strategyFrame != null) {
            strategyFrame.setTitle(messages.getString("Strategy.title"));
        }
        if (save != null) {
            save.setText(messages.getString("Strategy.save"));
        }
        if (back != null) {
            back.setText(messages.getString("Strategy.back"));
        }
        if (reset != null) {
            reset.setText(messages.getString("Strategy.reset"));
        }
        if (s1 != null) {
            s1.setText(messages.getString("Strategy.s1"));
        }
        if (s2 != null) {
            s2.setText(messages.getString("Strategy.s2"));
        }
        if (s3 != null) {
            s3.setText(messages.getString("Strategy.s3"));
        }
        if (s4 != null) {
            s4.setText(messages.getString("Strategy.s4"));
        }
        if (s5 != null) {
            s5.setText(messages.getString("Strategy.s5"));
        }
    }

    /**
     * Initializes UI components for the strategy configuration.
     */
    private void initializeComponents() {
        mapButton = new JButton[100];
        map = new JPanel(new GridLayout(10, 10));
        map.setPreferredSize(new Dimension(200, 180));
        for (int i = 0; i < mapButton.length; i++) {
            mapButton[i] = new JButton("i" + i);
            map.add(mapButton[i]);
        }

        save = new JButton(messages.getString("Strategy.save"));
        back = new JButton(messages.getString("Strategy.back"));
        reset = new JButton(messages.getString("Strategy.reset"));
        s1 = new JButton(messages.getString("Strategy.s1"));
        s2 = new JButton(messages.getString("Strategy.s2"));
        s3 = new JButton(messages.getString("Strategy.s3"));
        s4 = new JButton(messages.getString("Strategy.s4"));
        s5 = new JButton(messages.getString("Strategy.s5"));
    }

    /**
     * Creates and displays the strategy configuration frame.
     */
    public void createStrategyFrame() {
        strategyFrame = new JFrame(messages.getString("Strategy.title"));
        strategyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        strategyFrame.setSize(980, 800);
        strategyFrame.setLocationRelativeTo(null);
        strategyFrame.setLayout(new BorderLayout());

        ImageIcon strategyFrameIcon = new ImageIcon("battleApplogo.png");
        strategyFrame.setIconImage(strategyFrameIcon.getImage());

        // Setting up the left panel with battleship images
        JPanel left = new JPanel(new GridBagLayout());
        left.setPreferredSize(new Dimension(250, 150));
        GridBagConstraints gc = new GridBagConstraints();

        ImageIcon battleShip2Icon = new ImageIcon("battleShip2Icon.png");
        ImageIcon battleShip4Icon = new ImageIcon("battleShip2.png");
        ImageIcon battleShip5Icon = new ImageIcon("battleShip5.png");
        battleShip2 = new JLabel("2UN");

        battleShip2.setPreferredSize(new Dimension(50, 80));
        battleShip2.setIcon(battleShip2Icon);

        battleShip3 = new JLabel("3UN");
        battleShip3.setIcon(battleShip2Icon);
        battleShip3.setPreferredSize(new Dimension(50, 100));

        battleShip4 = new JLabel("4UN");
        battleShip4.setIcon(battleShip4Icon);
        battleShip4.setPreferredSize(new Dimension(50, 140));

        battleShip5 = new JLabel("5UN");
        battleShip5.setIcon(battleShip5Icon);
        battleShip5.setPreferredSize(new Dimension(50, 200));

        gc.gridx = 0;
        gc.gridy = 0;
        gc.weighty = 0.5;
        gc.weightx = 1;
        gc.insets = new Insets(-100, 10, 10, 10);
        left.add(battleShip2, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.weighty = 0.5;
        gc.insets = new Insets(0, 0, 0, 0);
        left.add(battleShip3, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.weighty = 1;
        gc.insets = new Insets(-100, 20, 0, 20);
        left.add(battleShip4, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.weighty = 1;
        gc.weightx = 2;
        gc.insets = new Insets(-100, 0, 0, 0);
        left.add(battleShip5, gc);

        // Set up the bottom panel with action buttons
        JPanel bottom = new JPanel(new BorderLayout());

        // Bottom left panel
        JPanel bottomLeft = new JPanel(new GridLayout(5, 0));
        bottomLeft.setPreferredSize(new Dimension(200, 200));
        bottomLeft.setBorder(new EmptyBorder(20, 40, 20, 20));
        JLabel empty1 = new JLabel();
        JLabel empty2 = new JLabel();
        empty1.setPreferredSize(new Dimension(63, 26));
        bottomLeft.add(save);
        bottomLeft.add(empty1);
        bottomLeft.add(back);
        bottomLeft.add(empty2);
        bottomLeft.add(reset);

        // Bottom center panel
        JPanel bottomCenterPanel = new JPanel(new GridLayout(3, 7));
        JLabel[] occupy = new JLabel[7];
        for (int i = 0; i < occupy.length; i++) {
            occupy[i] = new JLabel();
            occupy[i].setPreferredSize(new Dimension(63, 26));
        }

        bottomCenterPanel.add(occupy[0]);
        bottomCenterPanel.add(s1);
        bottomCenterPanel.add(occupy[1]);
        bottomCenterPanel.add(s2);
        bottomCenterPanel.add(occupy[2]);
        bottomCenterPanel.add(s3);
        bottomCenterPanel.add(occupy[3]);
        bottomCenterPanel.add(s4);
        bottomCenterPanel.add(occupy[4]);
        bottomCenterPanel.add(s5);
        bottomCenterPanel.add(occupy[5]);

        bottom.add(bottomCenterPanel, BorderLayout.CENTER);
        bottom.add(bottomLeft, BorderLayout.WEST);

        // Adding all components to the main frame
        strategyFrame.add(new JLabel(), BorderLayout.NORTH);
        strategyFrame.add(left, BorderLayout.WEST);
        strategyFrame.add(map, BorderLayout.CENTER);
        strategyFrame.add(bottom, BorderLayout.SOUTH);
        strategyFrame.setVisible(true);

        // Update UI text based on current locale
        updateUIText();
    }

    /**
     * Displays the strategy frame if it's not already visible.
     */
    public void showStrategyFrame() {
        if (strategyFrame == null) {
            createStrategyFrame();
        }
        strategyFrame.setVisible(true);
    }

    /**
     * Closes the strategy frame.
     */
    public void closeFrame() {
        if (strategyFrame != null) {
            strategyFrame.dispose();
        }
    }

    /**
     * Adds an ActionListener to the save button.
     * 
     * @param saveButtonListener The ActionListener to add.
     */
    public void saveButtonListener(ActionListener saveButtonListener) {
        save.addActionListener(saveButtonListener);
    }

    public JButton getSaveButton() {
        return save;
    }

    /**
     * Adds an ActionListener to the reset button.
     * 
     * @param resetButtonListener The ActionListener to add.
     */
    public void resetButtonListener(ActionListener resetButtonListener) {
        reset.addActionListener(resetButtonListener);
    }

    public JButton getResetButton() {
        return reset;
    }

    /**
     * Adds an ActionListener to the back button.
     * 
     * @param backButtonListener The ActionListener to add.
     */
    public void backButtonListener(ActionListener backButtonListener) {
        back.addActionListener(backButtonListener);
    }

    public void ship2LabelListener(MouseListener ship2LabelListener) {
        battleShip2.addMouseListener(ship2LabelListener);
    }

    public void ship3LabelListener(MouseListener ship3LabelListener) {
        battleShip3.addMouseListener(ship3LabelListener);
    }

    public void ship4LabelListener(MouseListener ship4LabelListener) {
        battleShip4.addMouseListener(ship4LabelListener);
    }

    public void ship5LabelListener(MouseListener ship5LabelListener) {
        battleShip5.addMouseListener(ship5LabelListener);
    }

    public JButton getBackButton() {
        return back;
    }

    public void s1ButtonListener(ActionListener s1ButtonListener) {
        s1.addActionListener(s1ButtonListener);
    }

    public JButton getS1Button() {
        return s1;
    }

    public void s2ButtonListener(ActionListener s2ButtonListener) {
        s2.addActionListener(s2ButtonListener);
    }

    public JButton getS2Button() {
        return s2;
    }

    public void s3ButtonListener(ActionListener s3ButtonListener) {
        s3.addActionListener(s3ButtonListener);
    }

    public JButton getS3Button() {
        return s3;
    }

    public void s4ButtonListener(ActionListener s4ButtonListener) {
        s4.addActionListener(s4ButtonListener);
    }

    public JButton getS4Button() {
        return s4;
    }

    public void s5ButtonListener(ActionListener s5ButtonListener) {
        s5.addActionListener(s5ButtonListener);
    }

    public JButton getS5Button() {
        return s5;
    }

    public JButton[] getMapButton() {
        return mapButton;
    }

    /**
     * Returns the JPanel representing the map grid.
     * 
     * @return The JPanel representing the map grid.
     */
    public JPanel getMap() {
        return map;
    }
    
    public void setMap(JPanel map) {
        this.map = map;
    }

    public void setMapButton(JButton[] mapButton) {
        this.mapButton = mapButton;
    }

    public void battleShip2Listener(MouseListener battleShip2Listener) {
        battleShip2.addMouseListener(battleShip2Listener);
    }

    public void battleShip3Listener(MouseListener battleShip2Listener) {
        battleShip3.addMouseListener(battleShip2Listener);
    }

    public void battleShip4Listener(MouseListener battleShip2Listener) {
        battleShip4.addMouseListener(battleShip2Listener);
    }

    public void battleShip5Listener(MouseListener battleShip2Listener) {
        battleShip5.addMouseListener(battleShip2Listener);
    }

    /**
     * Updates the locale and UI text based on the new locale.
     * 
     * @param locale The new locale.
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
        loadResourceBundle();
        updateUIText();
    }
}
