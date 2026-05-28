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
package Controller;

import javax.swing.*;

import Main.MVCBattleShip;
import Model.BattleShipModel;
import Model.ClientManager;
import Model.Computer;
import Model.ServerManager;
import View.BattleShipView;
import View.GameInterFace;
import View.ProductionTeam;
import View.Strategy;
import View.SettingUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Locale;

/**
 * The BattleShipController class acts as the controller in the MVC architecture for the Battleship game.
 * It handles user interactions, updates the model and view accordingly, and manages game states and settings.
 */
public class BattleShipController {
    private BattleShipView theView;
    private BattleShipModel theModel;
    public static GameInterFace game; // Declare game as a static variable
    private Computer computerPlayer;
    private Locale currentLocale;
    private Strategy layout;
    
    private ServerManager serverManager;
    private ClientManager clientManager;

    private boolean swapped = true;
    private boolean userLayoutClicked = false;
    private boolean s1IsClicked = false;
    private boolean s2IsClicked = false;
    private boolean s3IsClicked = false;
    private boolean s4IsClicked = false;
    private boolean s5IsClicked = false;
    private boolean newWorkingButtonIsClicked = false;
    private boolean pvcMode = false;

    /**
     * Getter for the Player vs Computer (PVC) mode.
     * @return true if PVC mode is enabled, false otherwise.
     */
    public boolean isPvcMode() { return pvcMode; }

    /**
     * Setter for the Player vs Computer (PVC) mode.
     * @param pvcMode boolean value to set the PVC mode.
     */
    public void setPvcMode(boolean pvcMode) { this.pvcMode = pvcMode; }

    /**
     * Constructor for the BattleShipController class.
     * @param theView the view associated with this controller.
     * @param theModel the model associated with this controller.
     * @param currentLocale the locale to be used for internationalization.
     * @param layout the layout strategy for the game.
     */
    public BattleShipController(BattleShipView theView, BattleShipModel theModel, Locale currentLocale, Strategy layout) {
        this.theView = theView;
        this.theModel = theModel;
        this.currentLocale = currentLocale;
        this.layout = layout;

        // Setup listeners for view components.
        this.theView.exitButtonListener(e -> System.exit(0)); // Close the application
    }

    /**
     * Starts the controller by setting the view visible and initializing the listeners.
     */
    public void start() {
        theView.setVisible(true);
        theView.presetLayoutListener(new presetLayoutListener());
        theView.startButtonListener(new startButtonActionListener());
        theView.chooseServerOrClientButtonListener(new chooseServerOrClientActionListener());
        theView.productionTeamListener(new ProductionTeamListener());
        theView.settingListener(new settingListener());
    }

    /**
     * Updates the game interface language based on the specified locale.
     * @param locale the locale to set the game interface language.
     */
    public static void updateGameInterfaceLanguage(Locale locale) {
        if (game != null) {
            game.setLocale(locale);
        }
    }

    /**
     * Updates the current locale and reflects this change across the view and layout.
     * @param locale the new locale to be used.
     */
    public void updateLocale(Locale locale) {
        this.currentLocale = locale;
        theView.setLocale(locale);
        layout.setLocale(locale);
        if (game != null) {
            game.setLocale(locale);
        }
    }

    /**
     * ActionListener for preset layout button.
     * This listener handles the interactions when a preset layout is selected by the user.
     */
    class presetLayoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Preset Layout button clicked");

            layout.showStrategyFrame(); 
            layout.s1ButtonListener(new s1ButtonActionListener());
            layout.s2ButtonListener(new s2ButtonActionListener());
            layout.s3ButtonListener(new s3ButtonActionListener());
            layout.s4ButtonListener(new s4ButtonActionListener());
            layout.s5ButtonListener(new s5ButtonActionListener());
            layout.saveButtonListener(new saveButtonActionListener());
            layout.backButtonListener(new backButtonActionListener());
            layout.resetButtonListener(new resetButtonActionListener());
            layout.ship2LabelListener(new ship2LabelListenerMouseListener());
            layout.ship3LabelListener(new ship3LabelListenerMouseListener());
            layout.ship4LabelListener(new ship4LabelListenerMouseListener());
            layout.ship5LabelListener(new ship5LabelListenerMouseListener());
        }
    }

    /**
     * ActionListener for the production team button.
     * This listener opens the production team information when triggered.
     */
    class ProductionTeamListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new ProductionTeam();
        }
    }

    /**
     * ActionListener for the settings button.
     * This listener opens the settings UI when triggered.
     */
    class settingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SettingUI setting = new SettingUI();
            setting.show();
        }
    }

    /**
     * MouseListener for the ship 2 label.
     * This listener handles the actions when the user clicks on the ship 2 label in the layout.
     */
    class ship2LabelListenerMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            theModel.shipSizeHorizontalBoundaries();
            userLayoutClicked = true;
            if (userLayoutClicked) {
                System.out.println("User Layout label clicked");
                theModel.showShipSettingsDialog();
                theModel.setUserLayoutSet(userLayoutClicked);
                theModel.userLayoutPreset(layout.getMapButton(), theModel.getButtonNumber(), theModel.getConfirmedOrientation(), 2);

                layout.setMapButton(theModel.getRealGameMapButton());
                s1IsClicked = false;
                s2IsClicked = false;
                s3IsClicked = false;
                s4IsClicked = false;
                s5IsClicked = false;

                theModel.setPresetS1IsSet(s1IsClicked);
                theModel.setPresetS2IsSet(s2IsClicked);
                theModel.setPresetS3IsSet(s3IsClicked);
                theModel.setPresetS4IsSet(s4IsClicked);
                theModel.setPresetS5IsSet(s5IsClicked);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }

    /**
     * MouseListener for the ship 3 label.
     * This listener handles the actions when the user clicks on the ship 3 label in the layout.
     */
    class ship3LabelListenerMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            userLayoutClicked = true;
            if (userLayoutClicked) {
                System.out.println("User Layout label clicked");
                theModel.showShipSettingsDialog();
                theModel.setUserLayoutSet(userLayoutClicked);
                theModel.userLayoutPreset(layout.getMapButton(), theModel.getButtonNumber(), theModel.getConfirmedOrientation(), 3);

                layout.setMapButton(theModel.getRealGameMapButton());
                s1IsClicked = false;
                s2IsClicked = false;
                s3IsClicked = false;
                s4IsClicked = false;
                s5IsClicked = false;

                theModel.setPresetS1IsSet(s1IsClicked);
                theModel.setPresetS2IsSet(s2IsClicked);
                theModel.setPresetS3IsSet(s3IsClicked);
                theModel.setPresetS4IsSet(s4IsClicked);
                theModel.setPresetS5IsSet(s5IsClicked);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }

    /**
     * MouseListener for the ship 4 label.
     * This listener handles the actions when the user clicks on the ship 4 label in the layout.
     */
    class ship4LabelListenerMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            userLayoutClicked = true;
            if (userLayoutClicked) {
                System.out.println("User Layout label clicked");
                theModel.showShipSettingsDialog();
                theModel.setUserLayoutSet(userLayoutClicked);
                theModel.userLayoutPreset(layout.getMapButton(), theModel.getButtonNumber(), theModel.getConfirmedOrientation(), 4);

                layout.setMapButton(theModel.getRealGameMapButton());
                s1IsClicked = false;
                s2IsClicked = false;
                s3IsClicked = false;
                s4IsClicked = false;
                s5IsClicked = false;

                theModel.setPresetS1IsSet(s1IsClicked);
                theModel.setPresetS2IsSet(s2IsClicked);
                theModel.setPresetS3IsSet(s3IsClicked);
                theModel.setPresetS4IsSet(s4IsClicked);
                theModel.setPresetS5IsSet(s5IsClicked);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }

    /**
     * MouseListener for the ship 5 label.
     * This listener handles the actions when the user clicks on the ship 5 label in the layout.
     */
    class ship5LabelListenerMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            userLayoutClicked = true;
            if (userLayoutClicked) {
                System.out.println("User Layout label clicked");
                theModel.showShipSettingsDialog();
                theModel.setUserLayoutSet(userLayoutClicked);
                theModel.userLayoutPreset(layout.getMapButton(), theModel.getButtonNumber(), theModel.getConfirmedOrientation(), 5);
                layout.setMapButton(theModel.getRealGameMapButton());

                s1IsClicked = false;
                s2IsClicked = false;
                s3IsClicked = false;
                s4IsClicked = false;
                s5IsClicked = false;

                theModel.setPresetS1IsSet(s1IsClicked);
                theModel.setPresetS2IsSet(s2IsClicked);
                theModel.setPresetS3IsSet(s3IsClicked);
                theModel.setPresetS4IsSet(s4IsClicked);
                theModel.setPresetS5IsSet(s5IsClicked);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }

    /**
     * ActionListener for strategy 1 button.
     * This listener handles the interactions when strategy 1 is selected by the user.
     */
    class s1ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            s1IsClicked = true;
            if (s1IsClicked) {
                System.out.println("Strategy 1 Layout button clicked");
                theModel.setPresetS1IsSet(s1IsClicked);
                theModel.presetS1(layout.getMapButton());
                layout.setMapButton(theModel.getRealGameMapButton());
                s2IsClicked = false;
                s3IsClicked = false;
                s4IsClicked = false;
                s5IsClicked = false;
                userLayoutClicked = false;

                theModel.setPresetS2IsSet(s2IsClicked);
                theModel.setPresetS3IsSet(s3IsClicked);
                theModel.setPresetS4IsSet(s4IsClicked);
                theModel.setPresetS5IsSet(s5IsClicked);
                theModel.setUserLayoutSet(userLayoutClicked);
            }
        }
    }

    /**
     * ActionListener for strategy 2 button.
     * This listener handles the interactions when strategy 2 is selected by the user.
     */
    class s2ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            s2IsClicked = true;
            if (s2IsClicked) {
                System.out.println("Strategy 2 Layout button clicked");
                theModel.setPresetS2IsSet(s2IsClicked);
                theModel.presetS2(layout.getMapButton());
                layout.setMapButton(theModel.getRealGameMapButton());
                s1IsClicked = false;
                s3IsClicked = false;
                s4IsClicked = false;
                s5IsClicked = false;
                userLayoutClicked = false;
                theModel.setPresetS1IsSet(s1IsClicked);
                theModel.setPresetS3IsSet(s3IsClicked);
                theModel.setPresetS4IsSet(s4IsClicked);
                theModel.setPresetS5IsSet(s5IsClicked);
                theModel.setUserLayoutSet(userLayoutClicked);
            }
        }
    }

    /**
     * ActionListener for strategy 3 button.
     * This listener handles the interactions when strategy 3 is selected by the user.
     */
    class s3ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            s3IsClicked = true;
            if (s3IsClicked) {
                System.out.println("Strategy 3 Layout button clicked");
                theModel.setPresetS3IsSet(s3IsClicked);
                theModel.presetS3(layout.getMapButton());
                layout.setMapButton(theModel.getRealGameMapButton());
                s1IsClicked = false;
                s2IsClicked = false;
                s4IsClicked = false;
                s5IsClicked = false;
                userLayoutClicked = false;
                theModel.setPresetS1IsSet(s1IsClicked);
                theModel.setPresetS2IsSet(s2IsClicked);
                theModel.setPresetS4IsSet(s4IsClicked);
                theModel.setPresetS5IsSet(s5IsClicked);
                theModel.setUserLayoutSet(userLayoutClicked);
            }
        }
    }

    /**
     * ActionListener for strategy 4 button.
     * This listener handles the interactions when strategy 4 is selected by the user.
     */
    class s4ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            s4IsClicked = true;
            if (s4IsClicked) {
                System.out.println("Strategy 4 Layout button clicked");
                theModel.setPresetS4IsSet(s4IsClicked);
                theModel.presetS4(layout.getMapButton());
                layout.setMapButton(theModel.getRealGameMapButton());
                s1IsClicked = false;
                s2IsClicked = false;
                s3IsClicked = false;
                s5IsClicked = false;
                userLayoutClicked = false;
                theModel.setPresetS1IsSet(s1IsClicked);
                theModel.setPresetS2IsSet(s2IsClicked);
                theModel.setPresetS3IsSet(s3IsClicked);
                theModel.setPresetS5IsSet(s5IsClicked);
                theModel.setUserLayoutSet(userLayoutClicked);
            }
        }
    }

    /**
     * ActionListener for strategy 5 button.
     * This listener handles the interactions when strategy 5 is selected by the user.
     */
    class s5ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            s5IsClicked = true;
            if (s5IsClicked) {
                System.out.println("Strategy 5 Layout button clicked");
                theModel.setPresetS5IsSet(s5IsClicked);
                theModel.presetS5(layout.getMapButton());
                layout.setMapButton(theModel.getRealGameMapButton());
                s1IsClicked = false;
                s2IsClicked = false;
                s3IsClicked = false;
                s4IsClicked = false;
                userLayoutClicked = false;
                theModel.setPresetS1IsSet(s1IsClicked);
                theModel.setPresetS2IsSet(s2IsClicked);
                theModel.setPresetS3IsSet(s3IsClicked);
                theModel.setPresetS4IsSet(s4IsClicked);
                theModel.setUserLayoutSet(userLayoutClicked);
            }
        }
    }

    /**
     * ActionListener for the save button.
     * This listener handles the actions when the save button is clicked to save the current layout.
     */
    class saveButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!s1IsClicked && !s2IsClicked && !s3IsClicked && !s4IsClicked && !s5IsClicked && !userLayoutClicked) {
                s2IsClicked = true;
                if (s2IsClicked) {
                    System.out.println("Strategy 2 Layout button clicked");
                    theModel.setPresetS2IsSet(s2IsClicked);
                    theModel.presetS2(layout.getMapButton());
                    layout.setMapButton(theModel.getRealGameMapButton());
                    s1IsClicked = false;
                    s3IsClicked = false;
                    s4IsClicked = false;
                    s5IsClicked = false;
                    userLayoutClicked = false;
                    theModel.setPresetS1IsSet(s1IsClicked);
                    theModel.setPresetS3IsSet(s3IsClicked);
                    theModel.setPresetS4IsSet(s4IsClicked);
                    theModel.setPresetS5IsSet(s5IsClicked);
                    theModel.setUserLayoutSet(userLayoutClicked);
                }
            }
            System.out.println("Save button clicked");
            layout.closeFrame();
        }
    }

    /**
     * ActionListener for the "choose server or client" button.
     * This listener handles the actions when the user selects either server or client mode.
     */
    class chooseServerOrClientActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            newWorkingButtonIsClicked = true;
            theView.chooseServerOrClient();
            if(theView.clientName == null) {
                try {
                    serverManager = new ServerManager(Integer.parseInt(theView.serverSelectedPort), theModel, BattleShipController.this);
                } catch (NumberFormatException e1) {
                    System.out.println("Network setting failed!");
                    return;
                }
                theModel.setServerManager(serverManager);
                startGame();
            } else {
                try {
                    clientManager = new ClientManager(theView.clientAddress, Integer.parseInt(theView.clientSelectedPort), theModel, BattleShipController.this);
                    startGame();    
                } catch (NumberFormatException | IOException e1) {
                    System.out.println("Network setting failed!");
                    return;
                }
                theModel.setClientManager(clientManager);
            }
        }
    }

    /**
     * ActionListener for the start button.
     * This listener handles the actions when the start button is clicked to begin the game.
     */
    class startButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            startGame();
        }
    }

    /**
     * Starts the game based on the current settings and mode (server, client, or PVC).
     */
    public void startGame() {
        if(serverManager != null && newWorkingButtonIsClicked) {
            theModel.setGameInterFaceClosed(false);
            if (!s1IsClicked && !s2IsClicked && !s3IsClicked && !s4IsClicked && !s5IsClicked && !userLayoutClicked) {
                s2IsClicked = true;
                if (s2IsClicked) {
                    System.out.println("Strategy 2 Layout button clicked");
                    theModel.setPresetS2IsSet(s2IsClicked);
                    theModel.presetS2(layout.getMapButton());
                    layout.setMapButton(theModel.getRealGameMapButton());
                    s1IsClicked = false;
                    s3IsClicked = false;
                    s4IsClicked = false;
                    s5IsClicked = false;
                    userLayoutClicked = false;
                    theModel.setPresetS1IsSet(s1IsClicked);
                    theModel.setPresetS3IsSet(s3IsClicked);
                    theModel.setPresetS4IsSet(s4IsClicked);
                    theModel.setPresetS5IsSet(s5IsClicked);
                    theModel.setUserLayoutSet(userLayoutClicked);
                }
            }
            while(serverManager.isServerIsWaiting()) {
                theView.serverIsWaiting(serverManager);
            }
            theModel.serverStartTimer = true;
            System.out.println("Start button clicked");
            game = new GameInterFace(currentLocale, layout.getMap(), theModel);
            MVCBattleShip.setGameInterface(game);
            game.swapButtonListener(new swapButtonActionListener());
            game.restartButtonListener(new restartButtonListener());
            computerPlayer = new Computer();  
            if(serverManager != null && serverManager.getRemotePlayerIsSet()) {
                System.out.println("ServerManager is set: ");
                theModel.setServerTurn(true);
                theModel.computerPlayerPreset(computerPlayer.getMapButton(),true);
            } else if(clientManager != null) {
                System.out.println("ClientManager is set: ");
                theModel.setServerTurn(true);
                theModel.computerPlayerPreset(computerPlayer.getMapButton(),true);
            } else {
                System.out.println("Default computer player map");
                theView.clientNotConnected();
                theModel.computerPlayerPreset(computerPlayer.getMapButton(),false);
            }
        } else if(clientManager != null) {
            if (!s1IsClicked && !s2IsClicked && !s3IsClicked && !s4IsClicked && !s5IsClicked && !userLayoutClicked) {
                s2IsClicked = true;
                if (s2IsClicked) {
                    System.out.println("Strategy 2 Layout button clicked");
                    theModel.setPresetS2IsSet(s2IsClicked);
                    theModel.presetS2(layout.getMapButton());
                    layout.setMapButton(theModel.getRealGameMapButton());
                    s1IsClicked = false;
                    s3IsClicked = false;
                    s4IsClicked = false;
                    s5IsClicked = false;
                    userLayoutClicked = false;
                    theModel.setPresetS1IsSet(s1IsClicked);
                    theModel.setPresetS3IsSet(s3IsClicked);
                    theModel.setPresetS4IsSet(s4IsClicked);
                    theModel.setPresetS5IsSet(s5IsClicked);
                    theModel.setUserLayoutSet(userLayoutClicked);
                }
            }
            theModel.clientStartTimer = true;
            System.out.println("Start button clicked");
            game = new GameInterFace(currentLocale, layout.getMap(), theModel);
            MVCBattleShip.setGameInterface(game);
            game.swapButtonListener(new swapButtonActionListener());
            game.restartButtonListener(new restartButtonListener());
            computerPlayer = new Computer();  
            if(serverManager != null && serverManager.getRemotePlayerIsSet()) {
                theModel.setServerTurn(true);
                theModel.computerPlayerPreset(computerPlayer.getMapButton(),true);
                System.out.println("ServerManager1 is Running");
            } else if(clientManager != null) {
                theModel.setServerTurn(true);
                theModel.computerPlayerPreset(computerPlayer.getMapButton(),true);
                System.out.println("Client1 manager is running: ");
            } else {
                System.out.println("Default computer player map");
                theModel.computerPlayerPreset(computerPlayer.getMapButton(),false);
            }
        } else {
            theView.computerPlayerSet();
            setPvcMode(true);
            if (!s1IsClicked && !s2IsClicked && !s3IsClicked && !s4IsClicked && !s5IsClicked && !userLayoutClicked) {
                s2IsClicked = true;
                if (s2IsClicked) {
                    System.out.println("Strategy 2 Layout button clicked");
                    theModel.setPresetS2IsSet(s2IsClicked);
                    theModel.presetS2(layout.getMapButton());
                    layout.setMapButton(theModel.getRealGameMapButton());
                    s1IsClicked = false;
                    s3IsClicked = false;
                    s4IsClicked = false;
                    s5IsClicked = false;
                    userLayoutClicked = false;
                    theModel.setPresetS1IsSet(s1IsClicked);
                    theModel.setPresetS3IsSet(s3IsClicked);
                    theModel.setPresetS4IsSet(s4IsClicked);
                    theModel.setPresetS5IsSet(s5IsClicked);
                    theModel.setUserLayoutSet(userLayoutClicked);
                }
            }
            System.out.println("Start button clicked");
            game = new GameInterFace(currentLocale, layout.getMap(), theModel);
            MVCBattleShip.setGameInterface(game);
            game.swapButtonListener(new swapButtonActionListener());
            game.restartButtonListener(new restartButtonListener());
            computerPlayer = new Computer();  
            if(serverManager != null && serverManager.getRemotePlayerIsSet()) {
                theModel.setServerTurn(true);
                theModel.computerPlayerPreset(computerPlayer.getMapButton(),true);
                System.out.println("ServerManager1 is Running");
            } else if(clientManager != null) {
                theModel.setServerTurn(true);
                theModel.computerPlayerPreset(computerPlayer.getMapButton(),true);
                System.out.println("Client1 manager is running: ");
            } else {
                System.out.println("Default computer player map");
                theModel.computerPlayerPreset(computerPlayer.getMapButton(),false);
            }
        }
    }

    /**
     * ActionListener for the back button.
     * This listener handles the actions when the back button is clicked.
     */
    class backButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!s1IsClicked && !s2IsClicked && !s3IsClicked && !s4IsClicked && !s5IsClicked && !userLayoutClicked) {
                s2IsClicked = true;
                if (s2IsClicked) {
                    System.out.println("Strategy 2 Layout button clicked");
                    theModel.setPresetS2IsSet(s2IsClicked);
                    theModel.presetS2(layout.getMapButton());
                    layout.setMapButton(theModel.getRealGameMapButton());
                    s1IsClicked = false;
                    s3IsClicked = false;
                    s4IsClicked = false;
                    s5IsClicked = false;
                    theModel.setPresetS1IsSet(s1IsClicked);
                    theModel.setPresetS3IsSet(s3IsClicked);
                    theModel.setPresetS4IsSet(s4IsClicked);
                    theModel.setPresetS5IsSet(s5IsClicked);
                }
            }
            System.out.println("Return button clicked");
            layout.strategyFrame.dispose();
        }
    }

    /**
     * ActionListener for the swap button.
     * This listener handles the actions when the swap button is clicked to toggle between user and computer maps.
     */
    class swapButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (swapped) {
                System.out.println("Swapped");
                theModel.setCenterPanel(computerPlayer.getComputerMap());
                theModel.updateCenterPanel(game);
                swapped = false;
            } else {
                System.out.println("Swapped back");
                theModel.setCenterPanel(layout.getMap());
                theModel.updateCenterPanel(game);
                swapped = true;
            }
        }
    }

    /**
     * Displays the winner panel and checks for a win condition periodically.
     * If a player or the computer wins, a message is displayed.
     */
    public void winnerPanelShow() {
        while (true) {
            theModel.win();
            if (theModel.getComputerWin()) {
                System.out.println("Computer win: " + theModel.getComputerWin());
                theModel.computerWinButtonMap();
                theModel.clearWinnerButtonListener();
                JOptionPane.showMessageDialog(null, "Computer Win", "Report", JOptionPane.INFORMATION_MESSAGE);
                game.closeFrame();
                if (theModel.getClientSocket() != null) {
                    theModel.sendMessageToServer(theModel.passingPlayerMapSet());
                }
                break;
            }
            if (theModel.getPlayerWin()) {
                System.out.println("Player win: " + theModel.getPlayerWin());
                JOptionPane.showMessageDialog(null, "Player Win", "Report", JOptionPane.INFORMATION_MESSAGE);
                theModel.setWinnerPanel(layout.getMap());
                theModel.updateWinnerPanel(game);
                game.closeFrame();
                if (theModel.getServerManager() != null) {
                    theModel.sendMessageToClient(theModel.passingPlayerMapSet());
                }
                break;
            }
            try {
                Thread.sleep(1000); // Check the status every second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ActionListener for the reset button.
     * This listener handles the actions when the reset button is clicked to reset the game map.
     */
    class resetButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            theModel.resetMap();
        }
    }

    /**
     * ActionListener for the restart button.
     * This listener handles the actions when the restart button is clicked to restart the game.
     */
    class restartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Restart button clicked");
            if (theModel.getClientSocket() == null) {
                String message = "RT";
                theModel.sendMessageToClient(message);
            } else {
                String message = "RT";
                theModel.sendMessageToServer(message);
            }
        }
    }
}
