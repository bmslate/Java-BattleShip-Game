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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Model.BattleShipModel;

import javax.swing.Timer;

/**
 * The GameInterFace class represents the main game interface for the BattleShip game.
 * It includes elements such as the game board, player avatars, timers, chat functionality, 
 * and buttons for swapping turns, restarting the game, and sending messages.
 */
public class GameInterFace extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    // Game settings
    private int totalHealth = 100; // Total health for battleships
    private int time = 20; // Initial time for the countdown timer
    private int countDown; // Countdown timer variable
    private Timer timer; // Timer for managing turn durations
    private BattleShipModel theModel; // Reference to the game model

    boolean gameTurnSwitch = false; // Indicates if the turn should be switched

    // UI Components
    JButton swapButton = new JButton(); // Button to swap the game board
    JButton restartButton; // Button to restart the game
    JButton send; // Button to send chat messages

    private JLabel player1Avatar; // Label for player 1's avatar
    private JLabel player2Avatar; // Label for player 2's avatar
    private JLabel[] playerBattleShip = new JLabel[4]; // Labels for player 1's battleships
    private JLabel[] player2BattleShip = new JLabel[4]; // Labels for player 2's battleships

    public JPanel gameCenterPanel; // Panel for the main game board

    JProgressBar timeProgressBar; // Progress bar for the countdown timer
    JProgressBar[] damageStatus = new JProgressBar[4]; // Progress bars for player 1's battleship health
    JProgressBar[] damageStatus2 = new JProgressBar[4]; // Progress bars for player 2's battleship health

    private ResourceBundle messages; // Resource bundle for internationalization
    private Locale locale; // Locale for language settings

    private JTextArea chatArea; // Text area for displaying chat messages
    private JTextField chatInput; // Text field for inputting chat messages

    private boolean isTimeUp = false; // Indicates if the time is up

    /**
     * Constructor to initialize the game interface.
     * 
     * @param locale The locale for internationalization.
     * @param mapPanel The panel containing the game map.
     * @param theModel The BattleShipModel instance representing the game state.
     */
    public GameInterFace(Locale locale, JPanel mapPanel, BattleShipModel theModel) {
        this.locale = locale;
        this.theModel = theModel;
        loadResourceBundle();
        initializeComponents(mapPanel, theModel);
        updateUIText();
        startTimer();
        sendButtonListener(new sendButtonListener());
        chatInput.addActionListener(new sendButtonListener());
    }

    /**
     * Gets the current state of gameTurnSwitch.
     * 
     * @return The current state of gameTurnSwitch.
     */
    public boolean getGameTurnSwitch() { return gameTurnSwitch; }

    /**
     * Sets the state of gameTurnSwitch.
     * 
     * @param gameTurnSwitch The new state of gameTurnSwitch.
     */
    public void setGameTurnSwitch(boolean gameTurnSwitch) { this.gameTurnSwitch = gameTurnSwitch; }

    /**
     * Gets the total health value.
     * 
     * @return The total health value.
     */
    public int getTotalHealth() {
        return totalHealth;
    }

    /**
     * Sets the time value for the countdown timer.
     * 
     * @param time The new time value for the countdown timer.
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Loads the resource bundle for internationalization.
     */
    private void loadResourceBundle() {
        this.messages = ResourceBundle.getBundle("MessagesBundle", this.locale);
    }

    /**
     * Closes the current game interface window.
     */
    public void closeFrame() {
        if (this != null) {
            this.dispose();
        }
    }

    /**
     * Updates the chat area with a new message.
     * 
     * @param chatMessage The message to be added to the chat area.
     */
    public void updateChatMessage(String chatMessage) {
        chatArea.append(chatMessage + "\n");
        // Set the cursor to the end of chat area
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    /**
     * Updates the text of UI components to match the selected language.
     */
    private void updateUIText() {
        setTitle(messages.getString("Game.title"));
        swapButton.setText(messages.getString("Game.swap"));
        restartButton.setText(messages.getString("Game.Restart"));
        send.setText(messages.getString("Game.send"));
        player1Avatar.setText(messages.getString("Game.player1"));
        player2Avatar.setText(messages.getString("Game.player2"));
    }

    /**
     * Initializes and sets up the components of the game interface.
     * 
     * @param mapPanel The panel containing the game map.
     * @param theModel The BattleShipModel instance representing the game state.
     */
    private void initializeComponents(JPanel mapPanel, BattleShipModel theModel) {
        setTitle(messages.getString("Game.title"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                theModel.initiateNewComputerMap();
                theModel.setGameInterFaceClosed(true);
            }
        });
        setSize(980, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        ImageIcon battleShipIcon = new ImageIcon("battleApplogo.png");
        setIconImage(battleShipIcon.getImage());
        getContentPane().setBackground(Color.white);

        ImageIcon player1Image = new ImageIcon("player1.png");
        player1Avatar = new JLabel();
        player1Avatar.setIcon(player1Image);
        player1Avatar.setText(messages.getString("Game.player1"));
        player1Avatar.setFont(new Font("Arial", Font.PLAIN, 15));
        player1Avatar.setHorizontalTextPosition(JLabel.CENTER);
        player1Avatar.setVerticalTextPosition(JLabel.BOTTOM);
        player1Avatar.setBorder(new EmptyBorder(0, 20, 20, 20));
        player1Avatar.setPreferredSize(new Dimension(200, 100));

        player2Avatar = new JLabel();
        player2Avatar.setIcon(player1Image);
        player2Avatar.setText(messages.getString("Game.player2"));
        player2Avatar.setFont(new Font("Arial", Font.PLAIN, 15));
        player2Avatar.setHorizontalTextPosition(JLabel.CENTER);
        player2Avatar.setVerticalTextPosition(JLabel.BOTTOM);
        player2Avatar.setBorder(new EmptyBorder(0, 50, 20, 20));
        player2Avatar.setPreferredSize(new Dimension(200, 100));

        JLabel gameName = new JLabel();
        gameName.setText("Battle SHIP");
        gameName.setFont(new Font("Arial", Font.ITALIC, 25));
        gameName.setHorizontalAlignment(JLabel.CENTER);
        gameName.setVerticalAlignment(JLabel.TOP);
        gameName.setHorizontalTextPosition(JLabel.CENTER);
        gameName.setVerticalTextPosition(JLabel.CENTER);
        gameName.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.RED));

        timeProgressBar = new JProgressBar(0, time);
        timeProgressBar.setValue(time);
        timeProgressBar.setStringPainted(true);
        timeProgressBar.setPreferredSize(new Dimension(200, 10));

        JLabel[] emptyLabel = new JLabel[3];
        for (int i = 0; i < 3; i++) {
            emptyLabel[i] = new JLabel();
        }

        JPanel centerPanel = new JPanel(new GridLayout(5, 0));
        centerPanel.add(gameName);
        centerPanel.add(emptyLabel[0]);
        centerPanel.add(timeProgressBar, BorderLayout.CENTER);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(player1Avatar, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(player2Avatar, BorderLayout.CENTER);

        JPanel gameNorthPanel = new JPanel(new BorderLayout());
        gameNorthPanel.add(leftPanel, BorderLayout.WEST);
        gameNorthPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        gameNorthPanel.add(rightPanel, BorderLayout.EAST);
        gameNorthPanel.add(centerPanel, BorderLayout.CENTER);
        gameNorthPanel.setPreferredSize(new Dimension(200, 180));

        JPanel Player1BattleShipInfo = new JPanel(new GridLayout(22, 0));
        Player1BattleShipInfo.setPreferredSize(new Dimension(200, 100));
        for (int i = 0; i < damageStatus.length; i++) {
            playerBattleShip[i] = new JLabel(messages.getString("Game.battleShip" + (i + 1)));
            playerBattleShip[i].setHorizontalAlignment(JLabel.CENTER);
            Player1BattleShipInfo.add(playerBattleShip[i]);
            damageStatus[i] = new JProgressBar(0, 100);
            damageStatus[i].setValue(totalHealth);
            damageStatus[i].setStringPainted(true);
            damageStatus[i].setBorder(new EmptyBorder(0, 10, 10, 10));
            Player1BattleShipInfo.add(damageStatus[i]);
        }

        JPanel Player2BattleShipInfo = new JPanel(new GridLayout(22, 0));
        Player2BattleShipInfo.setPreferredSize(new Dimension(200, 100));
        for (int i = 0; i < damageStatus2.length; i++) {
            player2BattleShip[i] = new JLabel(messages.getString("Game.battleShip" + (i + 1)));
            player2BattleShip[i].setHorizontalAlignment(JLabel.CENTER);
            Player2BattleShipInfo.add(player2BattleShip[i]);
            damageStatus2[i] = new JProgressBar(0, 100);
            damageStatus2[i].setValue(totalHealth);
            damageStatus2[i].setStringPainted(true);
            damageStatus2[i].setBorder(new EmptyBorder(0, 10, 10, 10));
            Player2BattleShipInfo.add(damageStatus2[i]);
        }

        gameCenterPanel = new JPanel(new BorderLayout());
        gameCenterPanel.add(mapPanel, BorderLayout.CENTER);

        JPanel gameSouthPanel = new JPanel(new BorderLayout());
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatInput = new JTextField();

        JLabel j1 = new JLabel("");
        JLabel j2 = new JLabel("");
        JLabel j3 = new JLabel("");
        JLabel j4 = new JLabel("");
        JLabel j5 = new JLabel("");
        JLabel j6 = new JLabel("");
        JLabel j7 = new JLabel("");
        JLabel j8 = new JLabel("");
        JLabel j9 = new JLabel("");
        JLabel j10 = new JLabel("");
        JLabel j11 = new JLabel("");
        JLabel j12 = new JLabel("");

        JPanel gameSouthCenterChatPanel = new JPanel(new BorderLayout());
        gameSouthCenterChatPanel.add(chatScrollPane, BorderLayout.CENTER);
        gameSouthCenterChatPanel.add(chatInput, BorderLayout.SOUTH);

        swapButton = new JButton(messages.getString("Game.swap"));
        restartButton = new JButton(messages.getString("Game.Restart"));
        send = new JButton(messages.getString("Game.send"));
        JPanel gameSettingButtonPanel = new JPanel(new GridLayout(5, 0));
        gameSettingButtonPanel.add(j1);
        gameSettingButtonPanel.add(swapButton);
        gameSettingButtonPanel.add(j2);
        gameSettingButtonPanel.add(j3);
        gameSettingButtonPanel.add(j4);
        gameSettingButtonPanel.add(restartButton);
        gameSettingButtonPanel.add(j5);
        gameSettingButtonPanel.add(j6);
        gameSettingButtonPanel.add(send);

        JPanel buttonsPanel = new JPanel(new GridLayout(5, 0));
        JButton one = new JButton(messages.getString("Game.one"));
        JButton two = new JButton(messages.getString("Game.two"));
        JButton three = new JButton(messages.getString("Game.three"));
        buttonsPanel.add(one);
        buttonsPanel.add(j7);
        buttonsPanel.add(j8);
        buttonsPanel.add(j9);
        buttonsPanel.add(two);
        buttonsPanel.add(j10);
        buttonsPanel.add(j11);
        buttonsPanel.add(j12);
        buttonsPanel.add(three);

        gameSouthPanel.add(gameSouthCenterChatPanel, BorderLayout.CENTER);
        gameSouthPanel.add(buttonsPanel, BorderLayout.WEST);
        gameSouthPanel.add(gameSettingButtonPanel, BorderLayout.EAST);

        add(Player1BattleShipInfo, BorderLayout.WEST);
        add(Player2BattleShipInfo, BorderLayout.EAST);
        add(gameCenterPanel, BorderLayout.CENTER);
        add(gameSouthPanel, BorderLayout.SOUTH);
        add(gameNorthPanel, BorderLayout.NORTH);

        theModel.setDamageStatus(damageStatus);
        theModel.setComputerDamageStatus(damageStatus2);
        theModel.setCenterPanel(gameCenterPanel);
        setVisible(true);
        
    }
    
    public boolean serverTurnFromTimer = true;
    
    /**
     * Starts the countdown timer and handles turn switching logic.
     */
    private void startTimer() {
        countDown = time;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (theModel.isGameInterFaceClosed()) {  // Check if the game interface is closed
                    timer.stop();
                    return;
                }
                if (countDown > 0) {
                    if (theModel.isServerTurn() == false && theModel.getServerManager() != null) {
                        timer.stop();
                        startTimer();
                        if (theModel.getPlayerWin() || theModel.getComputerWin() || theModel.isGameInterFaceClosed()||theModel.getComputerWin()||theModel.getPlayerWin()) {
                            timer.stop();
                        }
                    } else if (theModel.isServerTurn() == true && theModel.getClientSocket() != null) {
                        System.out.println("CLIENT is serverTurn : " + theModel.isServerTurn());
                        System.out.println("The client is : " + theModel.getButtonNumber());
                        timer.stop();
                        startTimer();
                        if (theModel.getPlayerWin() || theModel.getComputerWin() || theModel.isGameInterFaceClosed()||theModel.getComputerWin()||theModel.getPlayerWin()) {
                            timer.stop();
                        }
                    } else {
                        countDown--;

                        timeProgressBar.setValue(countDown);
                        timeProgressBar.setString("Time remaining: " + countDown + "s");
                    }
                } else {
                    if (theModel.getServerManager() != null) {
                        serverTurnFromTimer = false;
                        theModel.setServerTurn(serverTurnFromTimer);
                        System.out.println("Timer:  " + theModel.isServerTurn());
                        String response = "Timer" + "," + theModel.isServerTurn(); // Send timer update to client
                        System.out.println(response);
                        theModel.sendMessageToClient(response);
                        showNonBlockingMessage("Your Turn Ended", "Time is up. Switching turn to your enemy.");
                        countDown = time;
                        timer.stop();
                        startTimer();
                    } else if (theModel.getClientSocket() != null) {
                        serverTurnFromTimer = true;
                        theModel.setServerTurn(serverTurnFromTimer);
                        System.out.println("Timer:  " + theModel.isServerTurn());
                        String response = "Timer" + "," + theModel.isServerTurn(); // Send timer update to server
                        System.out.println(response);
                        theModel.sendMessageToServer(response);
                        showNonBlockingMessage("Your Turn Ended", "Time is up. Switching turn to Your enemy.");
                        countDown = time;
                        timer.stop();
                        startTimer();
                    }
                }
            }
        });
        
        if (theModel.clientStartTimer == true) {
            timer.setInitialDelay(1000);
            timer.start();
        } else if (theModel.serverStartTimer == true) {
            timer.start();
        }
    }

    /**
     * Displays a non-blocking message dialog.
     * 
     * @param title The title of the dialog.
     * @param message The message to be displayed.
     */
    private void showNonBlockingMessage(String title, String message) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = optionPane.createDialog(this, title);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        });
    }
    

    /**
     * Checks if the time is up.
     * 
     * @return True if the time is up, false otherwise.
     */
    public boolean isTimeUp() {
        return isTimeUp;
    }

    /**
     * Adds an ActionListener to the swapButton.
     * 
     * @param swapButtonListener The ActionListener to be added.
     */
    public void swapButtonListener(ActionListener swapButtonListener) {
        swapButton.addActionListener(swapButtonListener);
    }

    /**
     * Adds an ActionListener to the restartButton.
     * 
     * @param restartButtonListener The ActionListener to be added.
     */
    public void restartButtonListener(ActionListener restartButtonListener) {
        restartButton.addActionListener(restartButtonListener);
    }

    /**
     * Adds an ActionListener to the send button.
     * 
     * @param sendButtonListener The ActionListener to be added.
     */
    void sendButtonListener(ActionListener sendButtonListener) {
        send.addActionListener(sendButtonListener);
    }
    
    /**
     * Inner class to handle the send button's actions.
     */
    class sendButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String message = chatInput.getText();
            if (!message.isEmpty()) {
                String escapeMessage = message.replace(",", "#@!123"); // Handle comma in message.
                if (theModel.getServerManager() != null) {
                    theModel.sendMessageToClient("C," + "Server: " + escapeMessage);
                    chatInput.setText("");
                } else if (theModel.getClientSocket() != null) {
                    theModel.sendMessageToServer("C," + "Client: " + escapeMessage);
                    chatInput.setText("");
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle action events (currently not used)
    }
}
