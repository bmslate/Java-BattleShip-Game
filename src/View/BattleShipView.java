/**
 * CET - CS Academic Level 4
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * Student Name: Huijun Bu Kexin Huang
 * Student Number: 0411121881   041096457   
 * Section #: 301  
 * Course: CST8221 - Java application
 * Assignment: 3.2
 * @author Huijun Bu. Kexin Huang
 *
 */
package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Model.ServerManager;

/**
 * BattleShipView class represents the GUI for the Battleship game. It allows users to 
 * choose between client and server mode, configure game settings, and start the game.
 */
public class BattleShipView extends JFrame {
    private static final long serialVersionUID = 1L;

    // Resource bundle for internationalization
    private ResourceBundle messages;
    private Locale locale;

    // GUI components
    JButton chooseServerOrClient = new JButton();
    JButton startGame = new JButton();
    JButton layout = new JButton();
    JButton setting = new JButton();
    JButton ProductionTeam = new JButton();
    JButton exit = new JButton();

    JLabel logo = new JLabel();
    JLabel onlineText = new JLabel();
    
    private JTextField clientNameField;
    private JTextField serverNameField;
    private JTextField clientAddressField;
    private JComboBox<String> serverPortComboBox;
    private JComboBox<String> clientPortComboBox;
    private JLabel statusLabel = new JLabel();
    
    // Client properties
    public String clientSelectedPort;
    public String clientAddress;
    public String clientName;
    
    // Server properties
    public String serverSelectedPort;
    public String serverAddress;
    public String serverName;
    
    /**
     * Constructor for the BattleShipView class. Initializes the main menu UI with options 
     * to start the game, configure settings, and choose server or client mode.
     */
    public BattleShipView() {
        // Default language is English
        this.locale = new Locale("en", "US");
        loadResourceBundle();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(980, 800);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        ImageIcon menuFrameIcon = new ImageIcon("battleApplogo.png");
        this.setIconImage(menuFrameIcon.getImage());

        JPanel logoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        logo.setFont(new Font("Arial", Font.ITALIC, 80));
        onlineText.setFont(new Font("Arial", Font.ITALIC, 30));
        onlineText.setForeground(Color.ORANGE);

        // Adding logo label
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridheight = 2;
        gc.insets = new Insets(50, -400, 0, 0);
        logoPanel.add(logo, gc);

        // Adding online text label
        gc.gridx = 2;
        gc.gridy = 0;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.SOUTH;
        gc.insets = new Insets(0, 0, 0, 20);
        logoPanel.add(onlineText, gc);

        // Adding buttons
        chooseServerOrClient.setPreferredSize(new Dimension(200, 50));
        startGame.setPreferredSize(new Dimension(200, 50));
        layout.setPreferredSize(new Dimension(200, 50));
        setting.setPreferredSize(new Dimension(200, 50));
        ProductionTeam.setPreferredSize(new Dimension(200, 50));
        exit.setPreferredSize(new Dimension(200, 50));
        
        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(20, -550, 20, 0);
        logoPanel.add(startGame, gc);

        gc.gridx = 0;
        gc.gridy = 4;
        gc.insets = new Insets(0, -550, 20, 0);
        logoPanel.add(layout, gc);

        gc.gridx = 0;
        gc.gridy = 6;
        gc.insets = new Insets(0, -550, 20, 0);
        logoPanel.add(setting, gc);

        gc.gridx = 0;
        gc.gridy = 8;
        gc.insets = new Insets(0, -550, 20, 0);
        logoPanel.add(ProductionTeam, gc);
        
        gc.gridx = 0;
        gc.gridy = 10;
        gc.insets = new Insets(20, -550, 20, 0);
        logoPanel.add(chooseServerOrClient, gc);

        gc.gridx = 0;
        gc.gridy = 12;
        gc.insets = new Insets(0, -550, 20, 0);
        logoPanel.add(exit, gc);
        
        // Setting up the image
        ImageIcon pic = new ImageIcon("MenuPicture1.png");
        JLabel menuPic = new JLabel();
        menuPic.setIcon(pic);
        gc.gridx = 4;
        gc.gridy = 3;
        gc.gridheight = 10;
        gc.gridwidth = 10;
        gc.insets = new Insets(0, -300, 0, -400);
        logoPanel.add(menuPic, gc);

        // Adding the logoPanel to the top of the frame
        this.add(logoPanel, BorderLayout.NORTH);

        // Initialize UI text
        updateUIText();
    }
    
    /**
     * Prompts the user to choose between hosting a server or connecting as a client.
     */
    public void chooseServerOrClient() {
        String[] options = {"Host as Server", "Connect as Client"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose to host as a server or connect as a client:",
                "Server or Client",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == 0) {
            showServerDialog();
        } else if (choice == 1) {
            showClientDialog();
        }
    }

    /**
     * Displays a dialog to set up server details such as server name and port.
     */
    private void showServerDialog() {
        JTextField serverNameField = new JTextField(20);
        String[] ports = {"1234", "2345", "3456", "4567"};
        JComboBox<String> serverPortComboBox = new JComboBox<>(ports);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(serverNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Port:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(serverPortComboBox, gbc);

        int result = JOptionPane.showConfirmDialog(null, panel, "Host Server", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            serverName = serverNameField.getText();
            serverSelectedPort = (String) serverPortComboBox.getSelectedItem();
            statusLabel.setText("Status: Server is listening on port " + serverSelectedPort);
            // Add logic to start the server here
        }
    }

    /**
     * Displays a dialog to set up client details such as client name, address, and port.
     */
    private void showClientDialog() {
        JTextField clientNameField = new JTextField(20);
        JTextField clientAddressField = new JTextField(20);
        String[] ports = {"1234", "2345", "3456", "4567"};
        JComboBox<String> clientPortComboBox = new JComboBox<>(ports);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(clientNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Address:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(clientAddressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Port:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(clientPortComboBox, gbc);

        int result = JOptionPane.showConfirmDialog(null, panel, "Connect as Client", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            clientName = clientNameField.getText();
            clientAddress = clientAddressField.getText();
            clientSelectedPort = (String) clientPortComboBox.getSelectedItem();
            statusLabel.setText("Status: Connecting to server at " + clientAddress + " on port " + clientSelectedPort);
            // Add logic to connect to the server here
        }
    }

    /**
     * Inner class that handles connection button actions, connecting the client to the server.
     */
    private class ConnectButtonListener implements ActionListener {
        private JFrame frame;

        public ConnectButtonListener(JFrame frame) {
            this.frame = frame;
        }
    	
    	@Override
        public void actionPerformed(ActionEvent e) {
        	clientName = clientNameField.getText();
            clientAddress = clientAddressField.getText();
            clientSelectedPort = (String) clientPortComboBox.getSelectedItem();
	        if(serverName != null) {
	            if(clientName == null || clientSelectedPort == null) {
	            	clientName = serverName;
	            	clientSelectedPort = serverSelectedPort;
	            	statusLabel.setText("Status: Connecting to server at " + clientAddress + " on port " + clientSelectedPort);
	            } else if(!clientSelectedPort.equals(serverSelectedPort)) {
	            	statusLabel.setText("Status: wrong port number");
	            	clientSelectedPort = null;
	            	clientName = null;
	            	clientAddress = null;
	            	frame.dispose();
	            	showClientDialog();
	            } else {
	            	 statusLabel.setText("Status: Connecting to server at " + clientAddress + " on port " + clientSelectedPort);
	            }
	        } else {
	        	statusLabel.setText("Server is not initiated");
	        }
        }
    }

    /**
     * Displays a dialog with a countdown timer while the server is waiting for a client to connect.
     * @param serverManager The server manager handling server connections.
     */
    public void serverIsWaiting(ServerManager serverManager) {
        final int[] countdown = {30}; // 30-second countdown
        JDialog dialog = new JDialog(this, "Server Status", true);
        dialog.setLayout(new BorderLayout());
        JLabel label = new JLabel("Waiting for client connection... (" + countdown[0] + " seconds remaining)");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        dialog.add(label, BorderLayout.CENTER);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdown[0]--;
                label.setText("Waiting for client connection... (" + countdown[0] + " seconds remaining)");

                if (countdown[0] <= 0) {
                    ((Timer) e.getSource()).stop();
                    dialog.dispose();
                    handleTimeout();
                }
            }
        });

        // Start the countdown and display the dialog
        timer.start();

        // Listen for client connections in a background thread
        new Thread(() -> {
            try {
                while (countdown[0] > 0 && !serverManager.getRemotePlayerIsSet()) {
                    Thread.sleep(1000);
                }
                if (serverManager.getRemotePlayerIsSet()) {
                    timer.stop();
                    dialog.dispose();
                    // Automatically start the game if a client connects
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();

        dialog.setVisible(true);
    }
    
    /**
     * Handles timeout events when the server is waiting for a client to connect.
     */
    private void handleTimeout() {
        System.out.println("Timeout reached. Handling timeout...");
        // Implement timeout handling logic here
    }
    
    /**
     * Displays a message indicating that the game is starting in Player vs Computer mode.
     */
    public void computerPlayerSet() {
        JOptionPane.showMessageDialog(
            null, 
            "Networking not selected. Start PVC mode...", 
            "MODE", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    /**
     * Displays a message indicating that the client connection failed and the game is starting in Player vs Computer mode.
     */
    public void clientNotConnected() {
        JOptionPane.showMessageDialog(
            null, 
            "Client not connected. Start PVC mode...", 
            "MODE", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Loads the resource bundle for internationalization based on the current locale.
     */
    private void loadResourceBundle() {
        this.messages = ResourceBundle.getBundle("MessagesBundle", this.locale);
    }

    /**
     * Sets the locale for the UI and updates the text to the selected language.
     * @param locale The new locale to set.
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
        loadResourceBundle();
        updateUIText();
    }

    /**
     * Updates the UI text components based on the current resource bundle.
     */
    private void updateUIText() {
        if (logo != null) {
            logo.setText(messages.getString("BattleShipView.title"));
        }
        if (onlineText != null) {
            onlineText.setText(messages.getString("BattleShipView.online"));
        }
        if (startGame != null) {
            startGame.setText(messages.getString("BattleShipView.startgame"));
        }
        if (chooseServerOrClient != null) {
        	chooseServerOrClient.setText(messages.getString("BattleShipView.chooseServerOrClient"));
        }
        if (layout != null) {
            layout.setText(messages.getString("BattleShipView.layout"));
        }
        if (setting != null) {
            setting.setText(messages.getString("BattleShipView.settings"));
        }
        if (ProductionTeam != null) {
            ProductionTeam.setText(messages.getString("BattleShipView.productionteam"));
        }
        if (exit != null) {
            exit.setText(messages.getString("BattleShipView.exit"));
        }
    }

    // Event listener methods for various buttons
    public void presetLayoutListener(ActionListener presetLayoutListener) {
        layout.addActionListener(presetLayoutListener);
    }
    
    public void chooseServerOrClientButtonListener(ActionListener chooseServerOrClientButtonListener) {
    	chooseServerOrClient.addActionListener(chooseServerOrClientButtonListener);
    }

    public void startButtonListener(ActionListener startButtonListener) {
        startGame.addActionListener(startButtonListener);
    }

    public void productionTeamListener(ActionListener productionTeamListener) {
        ProductionTeam.addActionListener(productionTeamListener);
    }

    public void settingListener(ActionListener settingListener) {
        setting.addActionListener(settingListener);
    }

    public void exitButtonListener(ActionListener exitButtonListener) {
        exit.addActionListener(exitButtonListener);
    }
}
