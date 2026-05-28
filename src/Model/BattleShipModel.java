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
package Model;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Controller.BattleShipController;
import View.GameInterFace;


/**
 * The BattleShipModel class manages the state and behavior of the Battleship game.
 */

public class BattleShipModel {
    // Ship icon
    private ImageIcon fireIcon;
    private ImageIcon battleShip2IconForButtons;
    private ImageIcon missIcon;
    private boolean computerAttackInProgress;

//    public int serverSendtime;
//    public int clientReceivedTime;
    private boolean remotePlayerMapIsSet = false;///////////////////////////////////////////////////
    
    public boolean isRemotePlayerMapIsSet() {return remotePlayerMapIsSet;}

	public boolean serverStartTimer = false;
    public boolean clientStartTimer = false;
    private boolean gameInterFaceClosed = false;
    
    public boolean isGameInterFaceClosed() {return gameInterFaceClosed;}

	public void setGameInterFaceClosed(boolean gameInterFaceClosed) {this.gameInterFaceClosed = gameInterFaceClosed;}

	//private boolean isServerTurn;
    private boolean isServerTurn;
    public boolean isServerTurn() {return isServerTurn;}

    public void setServerTurn(boolean isServerTurn) {this.isServerTurn = isServerTurn;}
    
    private ServerManager serverManager;
    private ClientManager clientManager;
    
    // Computer damage progress bar
    private JProgressBar[] computerDamageStatus;
    // Player1 damage bar
    private JProgressBar[] damageStatus;

    private JPanel swapCenterPanel;
    private JPanel winnerPanel;
    private JPanel restartPanel;

    // Computer attack index array
    private ArrayList<Integer> computerAttackArray;
    private ArrayList<Integer> computerBeHitedButton;
    private ArrayList<Integer> computerLayoutButtonIndex;
    
    public ArrayList<Integer> getComputerLayoutButtonIndex() {return computerLayoutButtonIndex;}
    public void setComputerLayoutButtonIndex(ArrayList<Integer> computerLayoutButtonIndex) {this.computerLayoutButtonIndex = computerLayoutButtonIndex;}

	private ArrayList<Integer> newComputerLayoutButtonIndex;

    // User's option of orientation and ship size
    private String[] orientations;
    private int shipSize;
    private int buttonNumber;
    private String confirmedOrientation;
    
    private int passShipSize;
    public int getPassShipSize() {return passShipSize;}

	private int passShip1Damage = 50;
    public int getPassShip1Damage() {return passShip1Damage;}

	private int passShip2Damage = 33;
    public int getPassShip2Damage() {return passShip2Damage;}

	private int passShip3Damage = 25;
    public int getPassShip3Damage() {return passShip3Damage;}

	private int passShip4Damage = 20;
	public int getPassShip4Damage() {return passShip4Damage;}

	// Player turn
    private boolean turnFinished = false;
    
    public boolean getTurnFinished() { return turnFinished; }
    public void setTurnFinished(boolean turnFinished) { this.turnFinished = turnFinished; }

    // User horizontal boundaries
    private ArrayList<Integer> ship2SizeBoundaries;
    private ArrayList<Integer> ship3SizeBoundaries;
    private ArrayList<Integer> ship4SizeBoundaries;
    private ArrayList<Integer> ship5SizeBoundaries;

    boolean ship1Rotate = false;
    boolean ship2Rotate = false;
    boolean ship3Rotate = false;
    boolean ship4Rotate = false;

    boolean ship1LayoutIsSet = false;
    boolean ship2LayoutIsSet = false;
    boolean ship3LayoutIsSet = false;
    boolean ship4LayoutIsSet = false;

    // Player1 and computer map
    private JButton[] realGameMapButton;
    private JButton[] computerGameMapButton;

    // Ship life count
    private int computership2Count = 0;
    private int computership3Count = 0;
    private int computership4Count = 0;
    private int computership5Count = 0;

    // Ship damage count
    private int computership2Damage = 0;
    private int computership3Damage = 0;
    private int computership4Damage = 0;
    private int computership5Damage = 0;

    // For further implement a function which can terminate the game
    private boolean computership2Defeated = false;
    private boolean computership3Defeated = false;
    private boolean computership4Defeated = false;
    private boolean computership5Defeated = false;

    // Win decision
    private boolean computerWin = false;
    private boolean playerWin = false;
    boolean hit;
    boolean localPlayerTurn = true;
    boolean remotePlayerTurn = false;

    // ArrayList for storing index of 6 strategies
    private ArrayList<Integer> userLayoutPresetArray;
	private ArrayList<Integer> presetS1Array;
    private ArrayList<Integer> presetS2Array;
    private ArrayList<Integer> presetS3Array;
    private ArrayList<Integer> presetS4Array;
    private ArrayList<Integer> presetS5Array;
    
    public ArrayList<Integer> getUserLayoutPresetArray(){ return userLayoutPresetArray; }
    
    public ArrayList<Integer> getPresetS1Array() {return presetS1Array;}
    public void setPresetS1Array(ArrayList<Integer> presetS1Array) {this.presetS1Array = presetS1Array;}
    
	public ArrayList<Integer> getPresetS2Array() {return presetS2Array;}
	public void setPresetS2Array(ArrayList<Integer> presetS2Array) {this.presetS2Array = presetS2Array;}

	public ArrayList<Integer> getPresetS3Array() {return presetS3Array;}
	public void setPresetS3Array(ArrayList<Integer> presetS3Array) {this.presetS3Array = presetS3Array;}

	public ArrayList<Integer> getPresetS4Array() {return presetS4Array;}
	public void setPresetS4Array(ArrayList<Integer> presetS4Array) {this.presetS4Array = presetS4Array;}

	public ArrayList<Integer> getPresetS5Array() {return presetS5Array;}
	public void setPresetS5Array(ArrayList<Integer> presetS5Array) {this.presetS5Array = presetS5Array;}

    // User ship target array
    private ArrayList<Integer> Ship1TargetArray;
    private ArrayList<Integer> Ship2TargetArray;
    private ArrayList<Integer> Ship3TargetArray;
    private ArrayList<Integer> Ship4TargetArray;
    private ArrayList<Integer> Ship5TargetArray;

    private boolean userLayoutSet = false;
    private boolean presetS1IsSet = false;
    private boolean presetS2IsSet = false;
    private boolean presetS3IsSet = false;
    private boolean presetS4IsSet = false;
    private boolean presetS5IsSet = false;

    private int ship2Count = 0;
    private int ship3Count = 0;
    private int ship4Count = 0;
    private int ship5Count = 0;

    private int ship2Damage = 0;
    private int ship3Damage = 0;
    private int ship4Damage = 0;
    private int ship5Damage = 0;

    private boolean ship2Defeated = false;
    private boolean ship3Defeated = false;
    private boolean ship4Defeated = false;
    private boolean ship5Defeated = false;

    /**
     * Constructor for the BattleShipModel class.
     */
    public BattleShipModel() {
        fireIcon = new ImageIcon("hit.png");
        battleShip2IconForButtons = new ImageIcon("battleShip2IconForButtons.png");
        missIcon = new ImageIcon("miss.png");
        realGameMapButton = new JButton[100];
        computerGameMapButton = new JButton[100];
        userLayoutPresetArray = new ArrayList<>();
        ship2SizeBoundaries = new ArrayList<>();
        ship3SizeBoundaries = new ArrayList<>();
        ship4SizeBoundaries = new ArrayList<>();
        ship5SizeBoundaries = new ArrayList<>();
        orientations = new String[2];
        confirmedOrientation = "";
        presetS1Array = new ArrayList<>();
        presetS2Array = new ArrayList<>();
        presetS3Array = new ArrayList<>();
        presetS4Array = new ArrayList<>();
        presetS5Array = new ArrayList<>();
        Ship1TargetArray = new ArrayList<>();
        Ship2TargetArray = new ArrayList<>();
        Ship3TargetArray = new ArrayList<>();
        Ship4TargetArray = new ArrayList<>();
        Ship5TargetArray = new ArrayList<>();
        computerAttackArray = new ArrayList<>();
        computerBeHitedButton = new ArrayList<>();
        computerLayoutButtonIndex = new ArrayList<>();
        newComputerLayoutButtonIndex = new ArrayList<>();
        restartPanel = new JPanel();
        computerAttackInProgress = false;
    }

    /**
     * Returns the string representation of the player's map setup.
     *
     * @return a string representing the player's map setup.
     */
    public String passingPlayerMapSet() {
    	String passingPlayerMapSet = "PWM,";
      if(getPresetS1IsSet()){
    	for(Integer i : getPresetS1Array()) {
    		passingPlayerMapSet += ","+String.valueOf(i);
    		
    	}                	
    }else if(getPresetS2IsSet()) {
    	for(Integer i : getPresetS2Array()) {
    		passingPlayerMapSet += ","+String.valueOf(i);
    	}
    }else if(getPresetS3IsSet()) {
    	for(Integer i : getPresetS3Array()) {
    		passingPlayerMapSet += ","+String.valueOf(i);
    	}
    }else if(getPresetS4IsSet()) {
    	for(Integer i : getPresetS4Array()) {
    		passingPlayerMapSet += ","+String.valueOf(i);
    	}
    }else if(getPresetS5IsSet()) {
    	for(Integer i : getPresetS5Array()) {
    		passingPlayerMapSet += ","+String.valueOf(i);
    	}
    }else if(getUserLayoutSet()) {
    	for(Integer i : getUserLayoutPresetArray()) {
    		passingPlayerMapSet += ","+String.valueOf(i);
    	}
    }
     return passingPlayerMapSet;
    }
    
    
    public void setComputerGameMapButton() {}
    // Getters for damage values
    public int getShip2Damage() { return ship2Damage; }
    public int getShip3Damage() { return ship3Damage; }
    public int getShip4Damage() { return ship4Damage; }
    public int getShip5Damage() { return ship5Damage; }
    
    //getter and setter of player1 win and player2 win
    public boolean getComputerWin() { return computerWin; }
    public boolean getPlayerWin() { return playerWin; }
    public void setComputerWin(boolean computerWin) { this.computerWin = computerWin; }
    public void setPlayerWin(boolean playerWin) { this.playerWin = playerWin; }
    
   
    
    // Setters for setting preset strategy variable
    public void setUserLayoutSet(boolean userLayoutSet) { this.userLayoutSet = userLayoutSet; }///////////////////
    
    public void initiateNewComputerPanel() {
    	
    }
    
    //getter and setter for ServerSocket and Socket
    public ServerManager getServerManager() {return serverManager;}
    public ClientManager getClientSocket() {return clientManager;}
    public void setServerManager(ServerManager serverManager) { this.serverManager = serverManager;}
    public void setClientManager(ClientManager clientManager) { this.clientManager = clientManager;}
    
    
    public void setPresetS1IsSet(boolean presetS1IsSet) { this.presetS1IsSet = presetS1IsSet; }
    public void setPresetS2IsSet(boolean presetS2IsSet) { this.presetS2IsSet = presetS2IsSet; }
    public void setPresetS3IsSet(boolean presetS3IsSet) { this.presetS3IsSet = presetS3IsSet; }
    public void setPresetS4IsSet(boolean presetS4IsSet) { this.presetS4IsSet = presetS4IsSet; }
    public void setPresetS5IsSet(boolean presetS5IsSet) { this.presetS5IsSet = presetS5IsSet; }
    

    //getter for getting preset strategy variable 
    public boolean getUserLayoutSet() { return userLayoutSet; }
    public boolean getPresetS1IsSet() { return presetS1IsSet; }
    public boolean getPresetS2IsSet() { return presetS2IsSet; }
    public boolean getPresetS3IsSet() { return presetS3IsSet; }
    public boolean getPresetS4IsSet() { return presetS4IsSet; }
    public boolean getPresetS5IsSet() { return presetS5IsSet; }

    // Getters for user layout preset button number and orientation
    public int getButtonNumber() { return buttonNumber; }
    public String getConfirmedOrientation() { return confirmedOrientation; }

    // Getters for getting player1 map and computer map
    public JButton[] getRealGameMapButton() { return realGameMapButton; }
    public JButton[] getComputerGameMapButton() { return computerGameMapButton; }

    // Setters for setting player1 map and computer map
    public void setRealGameMapButton(JButton[] realGameMapButton) { this.realGameMapButton = realGameMapButton; }    
    public void setComputerGameMapButton(JButton[] computerGameMapButton) { this.computerGameMapButton = computerGameMapButton; }

    // Setters and updaters for panel
    public void setCenterPanel(JPanel swapCenterPanel) { this.swapCenterPanel = swapCenterPanel; }
    public void setWinnerPanel(JPanel winnerPanel) { this.winnerPanel = winnerPanel;}
    
    public void setRestartPanel(JPanel restartPanel) { this.restartPanel = restartPanel;}
    public JPanel getRestartPanel() {return restartPanel;}
    
    /**
     * Updates the restart panel.
     *
     * @param game the GameInterface object.
     */
    public void updateRestartPanel(GameInterFace game) {
    	game.gameCenterPanel.removeAll();
    	game.gameCenterPanel.add(restartPanel);
    	game.gameCenterPanel.revalidate();
        game.gameCenterPanel.repaint();
    }
    
    /**
     * Updates the center panel.
     *
     * @param game the GameInterface object.
     */
    public void updateCenterPanel(GameInterFace game) {
        game.gameCenterPanel.removeAll();
        game.gameCenterPanel.add(swapCenterPanel);
//        game.add(swapCenterPanel);
        game.gameCenterPanel.revalidate();
        game.gameCenterPanel.repaint();
    }
    
    /**
     * Updates the winner panel.
     *
     * @param game the GameInterface object.
     */
    //update winnerPanel
    public void updateWinnerPanel(GameInterFace game) {
    	game.gameCenterPanel.removeAll();
    	game.gameCenterPanel.add(winnerPanel);
    	game.gameCenterPanel.revalidate();
        game.gameCenterPanel.repaint();
    }
    
    /**
     * Sets the damage status progress bars.
     *
     * @param damageStatus the array of JProgressBar objects representing damage status.
     */
    // Set and update player1 damage status progress bar
    public void setDamageStatus(JProgressBar[] damageStatus) { this.damageStatus = damageStatus; }

    /**
     * Updates the damage progress bars.
     */
    public void updateProgressBars() {
        if (damageStatus != null) {
            damageStatus[0].setValue(100 - ship2Damage);
            System.out.println("Ship2Damage " + ship2Damage);
            damageStatus[1].setValue(99 - ship3Damage);
            damageStatus[2].setValue(100 - ship4Damage);
            damageStatus[3].setValue(100 - ship5Damage);
        }
    }
    /**
     * Sets the computer damage status progress bars.
     *
     * @param computerDamageStatus the array of JProgressBar objects representing computer damage status.
     */
    // Set and update computer damage status progress bar
    public void setComputerDamageStatus(JProgressBar[] computerDamageStatus) { this.computerDamageStatus = computerDamageStatus; }
    
    /**
     * Updates the computer damage progress bars.
     */
    public void updateComputerProgressBars() {
        if (computerDamageStatus != null) {
            computerDamageStatus[0].setValue(100 - computership2Damage);
            computerDamageStatus[1].setValue(99 - computership3Damage);
            computerDamageStatus[2].setValue(100 - computership4Damage);
            computerDamageStatus[3].setValue(100 - computership5Damage);
        }
    }
    /**
     * Clears icons from the specified buttons.
     *
     * @param mapButtons the array of JButton objects to clear icons from.
     */
    // Clear icons on buttons
    public void clearIcons(JButton[] mapButtons) {
        for (JButton button : mapButtons) {
            button.setIcon(null);
        }
    }
    
    /**
     * Resets the game map by clearing icons and resetting layout arrays.
     */
    // Reset map
    public void resetMap() {
        clearIcons(realGameMapButton);
        userLayoutPresetArray.clear();
        presetS1Array.clear();
        presetS2Array.clear();
        presetS3Array.clear();
        presetS4Array.clear();
        presetS5Array.clear();
        Ship1TargetArray.clear();
        Ship2TargetArray.clear();
        Ship3TargetArray.clear();
        Ship4TargetArray.clear();
        Ship5TargetArray.clear();
    }

    /**
     * Checks if a player has won the game.
     *
     * @return true if a player has won, false otherwise.
     */
    // Win criteria
    public boolean win() {
        if (computership2Defeated && computership3Defeated && computership4Defeated && computership5Defeated) {
            playerWin = true;
            return true;
        } else if (ship2Defeated && ship3Defeated && ship4Defeated && ship5Defeated) {
            computerWin = true;
            return true;
        }
        return false;
        
    }

    /**
     * Clears action listeners from the specified buttons.
     *
     * @param mapButtons the array of JButton objects to clear action listeners from.
     */
    // Clear icons listener
    public void clearIconListener(JButton[] mapButtons) {
        for (JButton button : mapButtons) {
            ActionListener[] listeners = button.getActionListeners();
            for (ActionListener listener : listeners) {
                button.removeActionListener(listener);
            }
        }
    }
    
    /**
     * Clears action listeners from the winner panel buttons.
     */
    //winner panel remove actionlistenner
    public void clearWinnerButtonListener() {
        for (JButton button : computerGameMapButton) {
            ActionListener[] listeners = button.getActionListeners();
            for (ActionListener listener : listeners) {
                button.removeActionListener(listener);
            }
        }
    }
    
    /**
     * Updates the computer's button map when it wins.
     */
	//Help method to set the unhitted button.
	public void computerWinButtonMap() {
		System.out.println("computer Be hited buttonNum"+computerBeHitedButton);
		System.out.println("ccomputer LayoutButtonIndex"+computerLayoutButtonIndex);
		computerLayoutButtonIndex.addAll(newComputerLayoutButtonIndex); 
		//newComputerLayoutButtonIndex;
	if(computerWin) {
		
		for(Integer hited: computerBeHitedButton) {
			if(computerLayoutButtonIndex.contains(hited)) {
				computerLayoutButtonIndex.remove(hited);
				
			}
		}
		for(Integer survived: computerLayoutButtonIndex) {
			System.out.println("survived");
			computerGameMapButton[survived].setIcon(battleShip2IconForButtons);
		}

	}
	
}
	
	/**
     * Initiates a new computer map.
     */
	///////////////////////////////
	public void initiateNewComputerMap() {////////////////////////////
        ship2Count = 0;
        ship3Count = 0;
        ship4Count = 0;
        ship5Count = 0;

        ship2Damage = 0;
        ship3Damage = 0;
        ship4Damage = 0;
        ship5Damage = 0;
        
        computership2Count = 0;
        computership3Count = 0;
        computership4Count = 0;
        computership5Count = 0;

        // Ship damage count
        computership2Damage = 0;
        computership3Damage = 0;
        computership4Damage = 0;
        computership5Damage = 0;
        
        ship2Defeated = false;
        ship3Defeated = false;
        ship4Defeated = false;
        ship5Defeated = false;
        
        computership2Defeated = false;
        computership3Defeated = false;
        computership4Defeated = false;
        computership5Defeated = false;
        
        computerWin = false;
        playerWin = false;
        if(serverManager == null) {
        	isServerTurn =  false;
        }else {
        	isServerTurn = true;
        }
		
        System.out.println("computer win: "+computerWin);
        System.out.println("player win: "+playerWin);
			for (int i = 0; i < computerGameMapButton.length; i++) {
				computerGameMapButton[i].setIcon(null);
				computerGameMapButton[i].setEnabled(true);

	        }
			
			for(int i = 0; i < realGameMapButton.length;i++) {
				realGameMapButton[i].setIcon(null);
				
				realGameMapButton[i].setEnabled(true);
			}
			
	        if (presetS1IsSet) {
	            for (int i = 0; i < realGameMapButton.length; i++) {
	                if (i == 35 || i == 36) {
	                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
	                    
	                } else if (i == 54 || i == 55 || i == 56) {
	                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
	                    
	                } else if (i == 42 || i == 43 || i == 44 || i == 45) {
	                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
	                    
	                } else if (i == 21 || i == 31 || i == 41 || i == 51 || i == 61) {
	                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
	                    
	                }
	            }
	            
	        }
			
	        if (presetS2IsSet) {
	            for (int i = 0; i < realGameMapButton.length; i++) {
	                if (i == 11 || i == 12) {
	                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
//	                    presetS2Array.add(i);
	                } else if (i == 81 || i == 82 || i == 83) {
	                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
//	                    presetS2Array.add(i);
	                } else if (i == 37 || i == 47 || i == 57 || i == 67) {
	                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
//	                    presetS2Array.add(i);
	                } else if (i == 14 || i == 15 || i == 16 || i == 17 || i == 18) {
	                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
//	                    presetS2Array.add(i);
	                }
	            }
	            
	        }
	        
	        if (presetS3IsSet) {
	            for (int i = 0; i < realGameMapButton.length; i++) {
	                if (i == 8 || i == 9) {
	                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
	                    
	                } else if (i == 78 || i == 88 || i == 98) {
	                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
	                    
	                } else if (i == 0 || i == 10 || i == 20 || i == 30) {
	                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
	                    
	                } else if (i == 43 || i == 53 || i == 63 || i == 73 || i == 83) {
	                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
	                    
	                }
	            }
	            
	        }
	        
	        if (presetS4IsSet) {
	            for (int i = 0; i < realGameMapButton.length; i++) {
	                if (i == 16 || i == 17) {
	                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
	                    
	                } else if (i == 40 || i == 50 || i == 60) {
	                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
	                    
	                } else if (i == 0 || i == 1 || i == 2 || i == 3) {
	                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
	                    
	                } else if (i == 29 || i == 39 || i == 49 || i == 59 || i == 69) {
	                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
	                    
	                }
	            }
	            
	        }
	        
	        if (presetS5IsSet) {
	            for (int i = 0; i < realGameMapButton.length; i++) {
	                if (i == 39 || i == 49) {
	                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
	                    
	                } else if (i == 28 || i == 38 || i == 48) {
	                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
	                    
	                } else if (i == 44 || i == 45 || i == 46 || i == 47) {
	                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
	                    
	                } else if (i == 5 || i == 6 || i == 7 || i == 8 || i == 9) {
	                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
	                    
	                }
	            }
	 
	        }
	        
	        if(userLayoutSet) {
	        	for (int i = 0; i < realGameMapButton.length; i++) {
                if (Ship1TargetArray.contains(i)) {
                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
                    
                } else if (Ship2TargetArray.contains(i)) {
                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
                    
                } else if (Ship3TargetArray.contains(i)) {
                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
                    
                } else if (Ship4TargetArray.contains(i)) {
                	realGameMapButton[i].setIcon(battleShip2IconForButtons);
                    
                }
            }
	        }
	            
	        updateProgressBars();
	        updateComputerProgressBars();
			
			//////////did not initiated computerMapButton
		
	}
	////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	 /**
     * Handles received messages and performs corresponding actions.
     *
     * @param message the received message.
     */
	public void handleReceivedMessage(String message) {
		String[] action = message.split(",");
		if(action[0].equalsIgnoreCase("A")) {
			int attack = Integer.parseInt(action[1]);
			isServerTurn = Boolean.parseBoolean(action[2]);
			ComputerAttackAction(attack);//99999
			System.out.println("hit:  "+hit);
			if(hit == true) {
				System.out.println("hit:  "+hit);
				String response = "AR"+","+attack+","+hit+","+getPassShipSize();//attact response, hitted true,switch turn true
				System.out.println(response);
				if (clientManager == null) {
                    sendMessageToClient(response);
                    System.out.println(response);
                    //setServerTurn(false);
                } else {
                    sendMessageToServer(response);
                    System.out.println(response);
                    //setServerTurn(isServerTurn);
                }
			}else {
				System.out.println("hit:  "+hit);
				String response = "AR"+","+attack+","+hit+","+getPassShipSize();//attact response, hitted true,switch turn true
				System.out.println(response);
				 if (clientManager == null) {
	                    sendMessageToClient(response);
	                    System.out.println(response);
	                    //setServerTurn(false);
	                } else {
	                    sendMessageToServer(response);
	                    System.out.println(response);
	                    //setServerTurn(true);
	                }
				
			}
		}else if(action[0].equalsIgnoreCase("C")) {
			//read from the third string to the end and replace it with comma
			String ChateMessage = message.substring(2).replace("#@!123", ",");
			handleChatMessage(ChateMessage);
		}else if(action[0].equalsIgnoreCase("AR")) {
			int attack = Integer.parseInt(action[1]);
			hit = Boolean.parseBoolean(action[2]);
			int shipSize = Integer.parseInt(action[3]);
				
			remotePlayerMap(attack,hit,shipSize);
		}else if(action[0].equalsIgnoreCase("timer")) {
			isServerTurn=Boolean.parseBoolean(action[1]);
			System.out.println("isSERVERTURN : "+isServerTurn);
		}else if(action[0].equalsIgnoreCase("PWM")) {
			for(int i = 2; i < 14 ; i++) {
				computerLayoutButtonIndex.add(Integer.parseInt(action[i]));
				computerWinButtonMap();
			}
		}else if(action[0].equalsIgnoreCase("RT")) {
			showRestartMessage("Restart request: ","Do you want to restart the game");
		}else if(action[0].equalsIgnoreCase("RTA")) {
			//agree to restart
			System.out.println("restarted ");
			initiateNewComputerMap();
		}else if(action[0].equalsIgnoreCase("RTD")) {
			//disagree to restart
		}

	}
	public boolean restart = false;
	
	  /**
     * Shows a restart message dialog.
     *
     * @param title   the title of the dialog.
     * @param message the message of the dialog.
     */
    public void showRestartMessage(String title, String message) {
        SwingUtilities.invokeLater(() -> {
            int response = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                if(serverManager == null) {
                	sendMessageToServer("RTA");
                	initiateNewComputerMap();
                	//restart = true;
                }else if(clientManager == null) {
                	sendMessageToClient("RTA");
                	//restart = true;
                	initiateNewComputerMap();
                }
                //JOptionPane.showMessageDialog(null, "You chose to restart the game.", title, JOptionPane.INFORMATION_MESSAGE);
            } else {
            	if(serverManager == null) {
                	sendMessageToServer("RTD");
                }else {
                	sendMessageToClient("RTD");
                }
                // 
                //JOptionPane.showMessageDialog(null, "You chose not to restart the game.", title, JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
	
    /**
     * Handles chat messages.
     *
     * @param chatMessage the chat message to handle.
     */
	//Handle Chat Message.
    public void handleChatMessage(String chatMessage) {
        BattleShipController.game.updateChatMessage(chatMessage);
    }
    /**
     * Synchronizes chat messages.
     *
     * @param message the message to synchronize.
     */
	//Sycronize chat Area
	public void sycronizeChatMessage(String message) {
       String [] sycronizeChatMessage = message.split(",");
       if(sycronizeChatMessage[0].equalsIgnoreCase("c")) {
    	 //read from the third string to the end and replace it with comma
    	   String ChateMessage = message.substring(2).replace("#@!123", ",");
    	   handleChatMessage(ChateMessage);
       }
    }
	
	 /**
     * Sends a message to the client.
     *
     * @param message the message to send.
     */
	public void sendMessageToClient(String message) {
        if (serverManager != null) {
            serverManager.sendMessageToClient(message);            
            sycronizeChatMessage(message);
        }
	}
	
	   /**
     * Sends a message to the server.
     *
     * @param message the message to send.
     */
	public void sendMessageToServer(String message) {
		if (clientManager != null) {
            clientManager.sendMessageToServer(message);
            sycronizeChatMessage(message);
        }
	}
	
//	public void remotePlayer(JButton[] remotePlayerButton,int attack,int damage, int shipSize) {
//        for (int i = 0; i < remotePlayerButton.length; i++) {
//        	remotePlayerButton[i].addActionListener(createShipActionListener(attack,damage,shipSize));
//        }
//        
//        
//	}
	
    // Preset methods for computer strategy
	 /**
     * Presets the computer player strategy.
     *
     * @param mapButtons          the array of JButton objects representing the map.
     * @param remotePlayerMapIsSet whether the remote player map is set.
     */
    public void computerPlayerPreset(JButton[] mapButtons,boolean remotePlayerMapIsSet) {//////////////////////
       this.remotePlayerMapIsSet = remotePlayerMapIsSet;
    	computership2Count = 0;
        computership3Count = 0;
        computership4Count = 0;
        computership5Count = 0;
        //if isServerTurn is true execute, if not do not create buttonListener
        if(remotePlayerMapIsSet == true) {
        	 for (int i = 0; i < mapButtons.length; i++) {
             	mapButtons[i].addActionListener(createShipActionListener(i,0,remotePlayerMapIsSet));
             	System.out.println("Model isServerTurn : "+isServerTurn);
             }
        }else {
	        for (int i = 0; i < mapButtons.length; i++) {
	            if (i == 35 || i == 36) {
	                mapButtons[i].addActionListener(createShipActionListener(i, 2,remotePlayerMapIsSet));
	                computerLayoutButtonIndex.add(i);
	            } else if (i == 54 || i == 55 || i == 56) {
	                mapButtons[i].addActionListener(createShipActionListener(i, 3,remotePlayerMapIsSet));
	                computerLayoutButtonIndex.add(i);
	            } else if (i == 42 || i == 43 || i == 44 || i == 45) {
	                mapButtons[i].addActionListener(createShipActionListener(i, 4,remotePlayerMapIsSet));
	                computerLayoutButtonIndex.add(i);
	            } else if (i == 21 || i == 31 || i == 41 || i == 51 || i == 61) {
	                mapButtons[i].addActionListener(createShipActionListener(i, 5,remotePlayerMapIsSet));
	                computerLayoutButtonIndex.add(i);
	            } else {
	                mapButtons[i].addActionListener(new ComputerMissActionListener(i));////////////////////////////
	            }
	        }
    }
        this.computerGameMapButton = mapButtons;
    }

    /**
     * Shows a dialog for setting ship settings.
     */
    // Helper method to get the size and direction from user
    public void showShipSettingsDialog() {
        JTextField buttonNumberField = new JTextField();
        orientations[0] = "Vertical";
        orientations[1] = "Horizontal";
        JComboBox<String> orientationComboBox = new JComboBox<>(orientations);

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Button Number:"));
        panel.add(buttonNumberField);
        panel.add(new JLabel("Orientation:"));
        panel.add(orientationComboBox);

        int result = JOptionPane.showConfirmDialog(
            null,
            panel,
            "Ship Settings",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                buttonNumber = Integer.parseInt(buttonNumberField.getText());
                if (buttonNumber >= 100) {
                    JOptionPane.showMessageDialog(null, "Button should be less than 100", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                } else {
                    confirmedOrientation = (String) orientationComboBox.getSelectedItem();
                    System.out.println("Button Number: " + buttonNumber);
                    System.out.println("Orientation: " + confirmedOrientation);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for the button.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Sets horizontal boundaries for ship sizes.
     */
    // Helper method array for boundaries
    public void shipSizeHorizontalBoundaries() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 100; j += 10) {
                if (i == 0) {
                    ship2SizeBoundaries.add(i + j);
                } else if (i == 1) {
                    ship3SizeBoundaries.add(i + j);
                } else if (i == 2) {
                    ship4SizeBoundaries.add(3 + j);
                } else if (i == 3) {
                    ship5SizeBoundaries.add(i + j);
                }
            }
        }
    }
///////////////////////////////////////////////////////
    // UserLayout preset
    /**
     * Sets the user layout preset.
     *
     * @param mapButtons the array of JButton objects representing the map.
     * @param buttonNumber the button number.
     * @param direction the direction of the ship.
     * @param shipSize the size of the ship.
     */
    public void userLayoutPreset(JButton[] mapButtons, int buttonNumber, String direction, int shipSize) {
        ship2Count = 0;
        ship3Count = 0;
        ship4Count = 0;
        ship5Count = 0;

        if (userLayoutSet) {
            for (int i = 0; i < mapButtons.length; i++) {
                if (i == buttonNumber && shipSize == 2 ) {//ship 1
                    if (direction.equalsIgnoreCase("Horizontal") && !userLayoutPresetArray.contains(i)) {
                        if (!ship2SizeBoundaries.contains(i) && i < 100) {
                        	if(!Ship1TargetArray.contains(i) && !Ship1TargetArray.contains(i - 1) && !Ship1TargetArray.contains(i - 2) && !Ship1TargetArray.contains(i - 3) && !Ship1TargetArray.contains(i - 4)
                               && !Ship2TargetArray.contains(i) && !Ship2TargetArray.contains(i - 1) && !Ship2TargetArray.contains(i - 2) && !Ship2TargetArray.contains(i - 3) && !Ship2TargetArray.contains(i - 4)
                               && !Ship3TargetArray.contains(i) && !Ship3TargetArray.contains(i - 1) && !Ship3TargetArray.contains(i - 2) && !Ship3TargetArray.contains(i - 3) && !Ship3TargetArray.contains(i - 4)) 
                        	{

                                mapButtons[i].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i);
                                Ship1TargetArray.add(i);
                                mapButtons[i - 1].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i - 1);
                                Ship1TargetArray.add(i - 1);
                                ship1LayoutIsSet = true;
                                System.out.println("Ship 1: "+ship1LayoutIsSet);

                                  
                        	}else {
                                JOptionPane.showMessageDialog(null, "Ship position repeated", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Out of boundary", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (direction.equalsIgnoreCase("Vertical") && !userLayoutPresetArray.contains(i)) {
                        if (i >= 10 && i < 100) {
                        	if(!Ship1TargetArray.contains(i) && !Ship1TargetArray.contains(i - 1) && !Ship1TargetArray.contains(i - 2) && !Ship1TargetArray.contains(i - 3) && !Ship1TargetArray.contains(i - 4)
                                    && !Ship2TargetArray.contains(i) && !Ship2TargetArray.contains(i - 1) && !Ship2TargetArray.contains(i - 2) && !Ship2TargetArray.contains(i - 3) && !Ship2TargetArray.contains(i - 4)
                                    && !Ship3TargetArray.contains(i) && !Ship3TargetArray.contains(i - 1) && !Ship3TargetArray.contains(i - 2) && !Ship3TargetArray.contains(i - 3) && !Ship3TargetArray.contains(i - 4)) 
                        	{
                                mapButtons[i].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i);
                                Ship1TargetArray.add(i);
                                mapButtons[i - 10].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i - 10);
                                Ship1TargetArray.add(i - 10);
                                ship1LayoutIsSet = true;
                        	}else {
                                JOptionPane.showMessageDialog(null, "Ship position repeated", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Out of boundary", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else if (i == buttonNumber && shipSize == 3) {//ship2
                    if (direction.equalsIgnoreCase("Horizontal") && !userLayoutPresetArray.contains(i)) {
                        if (!ship2SizeBoundaries.contains(i) && !ship3SizeBoundaries.contains(i) && i < 100) {
                            if (!Ship1TargetArray.contains(i) && !Ship1TargetArray.contains(i - 1) && !Ship1TargetArray.contains(i - 2) && !Ship1TargetArray.contains(i - 3) && !Ship1TargetArray.contains(i - 4)
                                    && !Ship2TargetArray.contains(i) && !Ship2TargetArray.contains(i - 1) && !Ship2TargetArray.contains(i - 2) && !Ship2TargetArray.contains(i - 3) && !Ship2TargetArray.contains(i - 4)
                                    && !Ship3TargetArray.contains(i) && !Ship3TargetArray.contains(i - 1) && !Ship3TargetArray.contains(i - 2) && !Ship3TargetArray.contains(i - 3) && !Ship3TargetArray.contains(i - 4)) {
                                mapButtons[i].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i);
                                Ship2TargetArray.add(i);
                                mapButtons[i - 1].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i - 1);
                                Ship2TargetArray.add(i - 1);
                                mapButtons[i - 2].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i - 2);
                                Ship2TargetArray.add(i - 2);
                                ship2LayoutIsSet = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Ship position repeated", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Out of boundary", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (direction.equalsIgnoreCase("Vertical") && !userLayoutPresetArray.contains(i)) {
                        if (i >= 20 && i < 100) {
                            if (!Ship1TargetArray.contains(i) && !Ship1TargetArray.contains(i - 1) && !Ship1TargetArray.contains(i - 2) && !Ship1TargetArray.contains(i - 3) && !Ship1TargetArray.contains(i - 4)
                                    && !Ship2TargetArray.contains(i) && !Ship2TargetArray.contains(i - 1) && !Ship2TargetArray.contains(i - 2) && !Ship2TargetArray.contains(i - 3) && !Ship2TargetArray.contains(i - 4)
                                    && !Ship3TargetArray.contains(i) && !Ship3TargetArray.contains(i - 1) && !Ship3TargetArray.contains(i - 2) && !Ship3TargetArray.contains(i - 3) && !Ship3TargetArray.contains(i - 4)) {
                                mapButtons[i].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i);
                                Ship2TargetArray.add(i);
                                mapButtons[i - 10].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i - 10);
                                Ship2TargetArray.add(i - 10);
                                mapButtons[i - 20].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i - 20);
                                Ship2TargetArray.add(i - 20);
                                ship2LayoutIsSet = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Ship position repeated", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Out of boundary", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else if (i == buttonNumber && shipSize == 4) {//ship 3
                    if (direction.equalsIgnoreCase("Horizontal") && !userLayoutPresetArray.contains(i)) {
                        if (!ship2SizeBoundaries.contains(i) && !ship3SizeBoundaries.contains(i) && !ship4SizeBoundaries.contains(i) && i < 100) {
                            if (!Ship1TargetArray.contains(i) && !Ship1TargetArray.contains(i - 1) && !Ship1TargetArray.contains(i - 2) && !Ship1TargetArray.contains(i - 3) && !Ship1TargetArray.contains(i - 4)
                                    && !Ship2TargetArray.contains(i) && !Ship2TargetArray.contains(i - 1) && !Ship2TargetArray.contains(i - 2) && !Ship2TargetArray.contains(i - 3) && !Ship2TargetArray.contains(i - 4)
                                    && !Ship3TargetArray.contains(i) && !Ship3TargetArray.contains(i - 1) && !Ship3TargetArray.contains(i - 2) && !Ship3TargetArray.contains(i - 3) && !Ship3TargetArray.contains(i - 4)) {
                                mapButtons[i].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i);
                                Ship3TargetArray.add(i);
                                mapButtons[i - 1].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i - 1);
                                Ship3TargetArray.add(i - 1);
                                mapButtons[i - 2].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i - 2);
                                Ship3TargetArray.add(i - 2);
                                mapButtons[i - 3].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i - 3);
                                Ship3TargetArray.add(i - 3);
                                ship3LayoutIsSet = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Ship position repeated", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else if (direction.equalsIgnoreCase("Vertical") && !userLayoutPresetArray.contains(i)) {
                        if (i >= 30 && i < 100 && !ship3LayoutIsSet) {
                            if (!Ship1TargetArray.contains(i) && !Ship1TargetArray.contains(i - 1) && !Ship1TargetArray.contains(i - 2) && !Ship1TargetArray.contains(i - 3) && !Ship1TargetArray.contains(i - 4)
                                    && !Ship2TargetArray.contains(i) && !Ship2TargetArray.contains(i - 1) && !Ship2TargetArray.contains(i - 2) && !Ship2TargetArray.contains(i - 3) && !Ship2TargetArray.contains(i - 4)
                                    && !Ship3TargetArray.contains(i) && !Ship3TargetArray.contains(i - 1) && !Ship3TargetArray.contains(i - 2) && !Ship3TargetArray.contains(i - 3) && !Ship3TargetArray.contains(i - 4)) {
                                mapButtons[i].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i);
                                Ship3TargetArray.add(i);
                                mapButtons[i - 10].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i - 10);
                                Ship3TargetArray.add(i - 10);
                                mapButtons[i - 20].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i - 20);
                                Ship3TargetArray.add(i - 20);
                                mapButtons[i - 30].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i - 30);
                                Ship3TargetArray.add(i - 30);
                                ship3LayoutIsSet = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Ship position repeated", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Out of boundary", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else if (i == buttonNumber && shipSize == 5) {//ship 4
                    if (direction.equalsIgnoreCase("Horizontal") && !userLayoutPresetArray.contains(i)) {
                        if (!ship2SizeBoundaries.contains(i) && !ship3SizeBoundaries.contains(i) && !ship4SizeBoundaries.contains(i) && !ship5SizeBoundaries.contains(i) && i < 100) {
                            if (!Ship1TargetArray.contains(i) && !Ship1TargetArray.contains(i - 1) && !Ship1TargetArray.contains(i - 2) && !Ship1TargetArray.contains(i - 3) && !Ship1TargetArray.contains(i - 4)
                                    && !Ship2TargetArray.contains(i) && !Ship2TargetArray.contains(i - 1) && !Ship2TargetArray.contains(i - 2) && !Ship2TargetArray.contains(i - 3) && !Ship2TargetArray.contains(i - 4)
                                    && !Ship3TargetArray.contains(i) && !Ship3TargetArray.contains(i - 1) && !Ship3TargetArray.contains(i - 2) && !Ship3TargetArray.contains(i - 3) && !Ship3TargetArray.contains(i - 4)) {
                                mapButtons[i].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i);
                                Ship4TargetArray.add(i);
                                mapButtons[i - 1].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i - 1);
                                Ship4TargetArray.add(i - 1);
                                mapButtons[i - 2].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i - 2);
                                Ship4TargetArray.add(i - 2);
                                mapButtons[i - 3].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i - 3);
                                Ship4TargetArray.add(i - 3);
                                mapButtons[i - 4].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i - 4);
                                Ship4TargetArray.add(i - 4);
                                ship4LayoutIsSet = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Ship position repeated", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Out of boundary", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (direction.equalsIgnoreCase("Vertical") && !userLayoutPresetArray.contains(i)) {
                        if (i >= 40 && i < 100) {
                            if (!Ship1TargetArray.contains(i) && !Ship1TargetArray.contains(i - 10) && !Ship1TargetArray.contains(i - 20) && !Ship1TargetArray.contains(i - 30) && !Ship1TargetArray.contains(i - 40)
                                && !Ship2TargetArray.contains(i) && !Ship2TargetArray.contains(i - 10) && !Ship2TargetArray.contains(i - 20) && !Ship2TargetArray.contains(i - 30) && !Ship2TargetArray.contains(i - 40)
                                && !Ship3TargetArray.contains(i) && !Ship3TargetArray.contains(i - 10) && !Ship3TargetArray.contains(i - 20) && !Ship3TargetArray.contains(i - 30) && !Ship3TargetArray.contains(i - 40)) {
                                mapButtons[i].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i);
                                Ship4TargetArray.add(i);
                                mapButtons[i - 10].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i - 10);
                                Ship4TargetArray.add(i - 10);
                                mapButtons[i - 20].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i - 20);
                                Ship4TargetArray.add(i - 20);
                                mapButtons[i - 30].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i - 30);
                                Ship4TargetArray.add(i - 30);
                                mapButtons[i - 40].setIcon(battleShip2IconForButtons);
                                userLayoutPresetArray.add(i - 40);
                                Ship4TargetArray.add(i - 40);
                                ship4LayoutIsSet = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Ship position repeated", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Out of boundary", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    
                }

            }
            this.realGameMapButton = mapButtons;
            for (Integer number : userLayoutPresetArray) {
                realGameMapButton[number].setIcon(battleShip2IconForButtons);
            }
        }
    }
//////////////////////////////
    // Preset methods for different strategies of player1
    /**
     * Presets the first strategy.
     *
     * @param mapButtons the array of JButton objects representing the map.
     */
    public void presetS1(JButton[] mapButtons) {
        resetButtons(mapButtons);
        ship2Count = 0;
        ship3Count = 0;
        ship4Count = 0;
        ship5Count = 0;
        if (presetS1IsSet) {
            for (int i = 0; i < mapButtons.length; i++) {
                if (i == 35 || i == 36) {
                    mapButtons[i].setIcon(battleShip2IconForButtons);
                    presetS1Array.add(i);
                } else if (i == 54 || i == 55 || i == 56) {
                    mapButtons[i].setIcon(battleShip2IconForButtons);
                    presetS1Array.add(i);
                } else if (i == 42 || i == 43 || i == 44 || i == 45) {
                    mapButtons[i].setIcon(battleShip2IconForButtons);
                    presetS1Array.add(i);
                } else if (i == 21 || i == 31 || i == 41 || i == 51 || i == 61) {
                    mapButtons[i].setIcon(battleShip2IconForButtons);
                    presetS1Array.add(i);
                }
            }
            this.realGameMapButton = mapButtons;
        }
    }

    /**
     * Presets the second strategy.
     *
     * @param mapButtons the array of JButton objects representing the map.
     */
    public void presetS2(JButton[] mapButtons) {
        resetButtons(mapButtons);/////////////////////////////////////
        ship2Count = 0;
        ship3Count = 0;
        ship4Count = 0;
        ship5Count = 0;
        if (presetS2IsSet) {
            for (int i = 0; i < mapButtons.length; i++) {
                if (i == 11 || i == 12) {
                    mapButtons[i].setIcon(battleShip2IconForButtons);
                    presetS2Array.add(i);
                } else if (i == 81 || i == 82 || i == 83) {
                    mapButtons[i].setIcon(battleShip2IconForButtons);
                    presetS2Array.add(i);
                } else if (i == 37 || i == 47 || i == 57 || i == 67) {
                    mapButtons[i].setIcon(battleShip2IconForButtons);
                    presetS2Array.add(i);
                } else if (i == 14 || i == 15 || i == 16 || i == 17 || i == 18) {
                    mapButtons[i].setIcon(battleShip2IconForButtons);
                    presetS2Array.add(i);
                }
            }
            this.realGameMapButton = mapButtons;
        }
    }

    /**
     * Presets the third strategy.
     *
     * @param mapButtons the array of JButton objects representing the map.
     */
    public void presetS3(JButton[] mapButtons) {
        resetButtons(mapButtons);
        ship2Count = 0;
        ship3Count = 0;
        ship4Count = 0;
        ship5Count = 0;
        if (presetS3IsSet) {
            for (int i = 0; i < mapButtons.length; i++) {
                if (i == 8 || i == 9) {
                    mapButtons[i].setIcon(battleShip2IconForButtons);
                    presetS3Array.add(i);
                } else if (i == 78 || i == 88 || i == 98) {
                    mapButtons[i].setIcon(battleShip2IconForButtons);
                    presetS3Array.add(i);
                } else if (i == 0 || i == 10 || i == 20 || i == 30) {
                    mapButtons[i].setIcon(battleShip2IconForButtons);
                    presetS3Array.add(i);
                } else if (i == 43 || i == 53 || i == 63 || i == 73 || i == 83) {
                    mapButtons[i].setIcon(battleShip2IconForButtons);
                    presetS3Array.add(i);
                }
            }
            this.realGameMapButton = mapButtons;
        }
    }

    /**
     * Presets the fourth strategy.
     *
     * @param mapButtons the array of JButton objects representing the map.
     */
    public void presetS4(JButton[] mapButtons) {
        resetButtons(mapButtons);
        ship2Count = 0;
        ship3Count = 0;
        ship4Count = 0;
        ship5Count = 0;
        if (presetS4IsSet) {
            for (int i = 0; i < mapButtons.length; i++) {
                if (i == 16 || i == 17) {
                    mapButtons[i].setIcon(battleShip2IconForButtons);
                    presetS4Array.add(i);
                } else if (i == 40 || i == 50 || i == 60) {
                    mapButtons[i].setIcon(battleShip2IconForButtons);
                    presetS4Array.add(i);
                } else if (i == 0 || i == 1 || i == 2 || i == 3) {
                    mapButtons[i].setIcon(battleShip2IconForButtons);
                    presetS4Array.add(i);
                } else if (i == 29 || i == 39 || i == 49 || i == 59 || i == 69) {
                    mapButtons[i].setIcon(battleShip2IconForButtons);
                    presetS4Array.add(i);
                }
            }
            this.realGameMapButton = mapButtons;
        }
    }
    
    /**
     * Presets the fifth strategy.
     *
     * @param mapButtons the array of JButton objects representing the map.
     */
    public void presetS5(JButton[] mapButtons) {
        resetButtons(mapButtons);
        ship2Count = 0;
        ship3Count = 0;
        ship4Count = 0;
        ship5Count = 0;
        if (presetS5IsSet) {
            for (int i = 0; i < mapButtons.length; i++) {
                if (i == 39 || i == 49) {
                    mapButtons[i].setIcon(battleShip2IconForButtons);
                    presetS5Array.add(i);
                } else if (i == 28 || i == 38 || i == 48) {
                    mapButtons[i].setIcon(battleShip2IconForButtons);
                    presetS5Array.add(i);
                } else if (i == 44 || i == 45 || i == 46 || i == 47) {
                    mapButtons[i].setIcon(battleShip2IconForButtons);
                    presetS5Array.add(i);
                } else if (i == 5 || i == 6 || i == 7 || i == 8 || i == 9) {
                    mapButtons[i].setIcon(battleShip2IconForButtons);
                    presetS5Array.add(i);
                }
            }
            this.realGameMapButton = mapButtons;
        }
    }

    // Helper method to reset buttons
    /**
     * Resets the buttons by clearing icons and action listeners.
     *
     * @param mapButtons the array of JButton objects to reset.
     */
    public void resetButtons(JButton[] mapButtons) {
        for (JButton button : mapButtons) {
            button.setIcon(null);
            for (ActionListener al : button.getActionListeners()) {
                button.removeActionListener(al);
            }
        }
    }

    /**
     * Responds to computer map actions based on ship size.
     *
     * @param shipSize the size of the ship.
     */
    public void computerMapResponse(int shipSize){
	    switch (shipSize) {
	    case 2:
	        computership2Count++;
	        computership2Damage += getPassShip1Damage();
	        System.out.println("Computer ship 1 defeat: "+computership2Count);
	        if (computership2Count == 2) {
	            computership2Defeated = true;
	            JOptionPane.showMessageDialog(null, "Computer Ship1 has been defeated", "Report", JOptionPane.INFORMATION_MESSAGE);
	        }
	        break;
	    case 3:
	        computership3Count++;
	        computership3Damage += getPassShip2Damage();
	        System.out.println("Computer ship 2 defeat: "+computership3Count);
	        if (computership3Count == 3) {
	            computership3Defeated = true;
	            JOptionPane.showMessageDialog(null, "Computer Ship2 has been defeated", "Report", JOptionPane.INFORMATION_MESSAGE);
	        }
	        break;
	    case 4:
	        computership4Count++;
	        computership4Damage += getPassShip3Damage();
	        System.out.println("Computer ship 3 defeat: "+computership4Count);
	        if (computership4Count == 4) {
	            computership4Defeated = true;
	            JOptionPane.showMessageDialog(null, "Computer Ship3 has been defeated", "Report", JOptionPane.INFORMATION_MESSAGE);
	        }
	        break;
	    case 5:
	        computership5Count++;
	        computership5Damage += getPassShip4Damage();
	        System.out.println("Computer ship 4 defeat: "+computership5Count);
	        if (computership5Count == 5) {
	            computership5Defeated = true;
	            JOptionPane.showMessageDialog(null, "Computer Ship4 has been defeated", "Report", JOptionPane.INFORMATION_MESSAGE);
	        }
	        break;
	    }
	    updateComputerProgressBars();
    	}
    // Factory method to create ship action listeners
    /**
     * Creates a ship action listener.
     *
     * @param index the index of the button.
     * @param shipSize the size of the ship.
     * @param remotePlayerMapIsSet whether the remote player map is set.
     * @return the action listener.
     */
    public ActionListener createShipActionListener(int index, int shipSize,boolean remotePlayerMapIsSet) {
        return new ShipActionListener(index, shipSize,remotePlayerMapIsSet);
    }

    /**
     * The ShipActionListener class handles ship action events.
     */
    class ShipActionListener implements ActionListener {
        private int index;
        private int shipSize;
        
        private boolean remotePlayerMapIsSet;
        

        public ShipActionListener(int index, int shipSize,boolean remotePlayerMapIsSet) {
            this.index = index;
            this.shipSize = shipSize;
            
            this.remotePlayerMapIsSet = remotePlayerMapIsSet;
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        	checkIfShipDefeated();////////////////////
        	if(computerWin || playerWin) {return;}
        	
        	if(remotePlayerMapIsSet == true) {
	        	if(isServerTurn && serverManager != null) {
	        		
	        		String message;
	        		setServerTurn(false); 
	        		//setTurnFinished(false);
	        		message = "A,"+index+","+isServerTurn();
	        		sendMessageToClient(message);
	        		System.out.println(message);
	        		computerGameMapButton[index].setEnabled(false);


	        	}else {
	        		if(isServerTurn == false && clientManager != null) {
	        		String message;
	        		setServerTurn(true);
	        		//setTurnFinished(false);
	        		message = "A,"+index+","+isServerTurn();
	        		sendMessageToServer(message);
	        		computerGameMapButton[index].setEnabled(false);
	        		}else {
	        			//clearWinnerButtonListener();
	        			System.out.println("It's not your turn");
	        		}
	        	}
        	}else {

            computerGameMapButton[index].setIcon(fireIcon);
            computerBeHitedButton.add(index);
            computerGameMapButton[index].setDisabledIcon(fireIcon);
            computerGameMapButton[index].setEnabled(false);
            
            computerMapResponse(shipSize);
            updateComputerProgressBars();

            if (!computerAttackInProgress) {
                computerAttackInProgress = true;
                int attack = 0;
                Random random = new Random();
                do {
                    attack = random.nextInt(realGameMapButton.length);
                } while (computerAttackArray.contains(attack));
                computerAttackArray.add(attack);
                ComputerAttackAction(attack);
                computerAttackInProgress = false;
            }
            checkIfShipDefeated();
        }
        	checkIfShipDefeated();
        }
    }

    // Miss action listener for computer's attacks
    /**
     * The ComputerMissActionListener class handles computer miss action events.
     */
    class ComputerMissActionListener implements ActionListener {
        private int index;

        public ComputerMissActionListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        	checkIfShipDefeated();
        	if (computerWin || playerWin) return;//////////////////////////
            computerGameMapButton[index].setIcon(missIcon);
            computerGameMapButton[index].setDisabledIcon(missIcon);
            computerGameMapButton[index].setEnabled(false);
            int attack = 0;
            Random random = new Random();
            
            do {
                attack = random.nextInt(realGameMapButton.length);
            } while (computerAttackArray.contains(attack));
            
            
            computerAttackArray.add(attack);
            ComputerAttackAction(attack);///////////////////////////////////////////////////////////////////
        
        }
    }

    /**
     * Handles remote player map actions.
     *
     * @param attack the attack index.
     * @param hit whether the attack hit.
     * @param shipSize the size of the ship.
     */
    public void remotePlayerMap(int attack,boolean hit,int shipSize) {
    	
        if(hit == true) {
    	computerGameMapButton[attack].setIcon(fireIcon);
    	//computerLayoutButtonIndex.add(attack);
    	computerBeHitedButton.add(attack);
        computerGameMapButton[attack].setDisabledIcon(fireIcon);
        computerGameMapButton[attack].setEnabled(false);
        computerMapResponse(shipSize);
        }else {
        	computerGameMapButton[attack].setIcon(missIcon);
            computerGameMapButton[attack].setDisabledIcon(missIcon);
            computerGameMapButton[attack].setEnabled(false);
        }
        }
    // Computer attack action
    /**
     * Handles computer attack actions.
     *
     * @param attack the attack index.
     */
    public void ComputerAttackAction(int attack)  {
        
        	checkIfShipDefeated();
        	System.out.println("computer win "+computerWin);
        	System.out.println("Player win "+ playerWin);
        	if (computerWin || playerWin) return;
            //boolean hit = false;
        	hit = false;
            if (presetS1IsSet) {
                for (Integer i : presetS1Array) {
                    if (attack == i) {
                    	
                        realGameMapButton[attack].setIcon(fireIcon);
                        realGameMapButton[attack].setDisabledIcon(fireIcon);
                        realGameMapButton[attack].setEnabled(false);
                        if (i == 35 || i == 36) {
                        	passShipSize = 2;
                        	
                            ship2Count++;
                            ship2Damage += 50;
                        } else if (i == 54 || i == 55 || i == 56) {
                        	passShipSize = 3;
                        	
                            ship3Count++;
                            ship3Damage += 33;
                        } else if (i == 42 || i == 43 || i == 44 || i == 45) {
                        	passShipSize = 4;
                            ship4Count++;
                            ship4Damage += 25;
                        } else if (i == 21 || i == 31 || i == 41 || i == 51 || i == 61) {
                        	passShipSize = 4;
                            ship5Count++;
                            ship5Damage += 20;
                        }
                        updateProgressBars();
                        checkIfShipDefeated();
                        hit = true;
                        break;
                    }
                }
            }
            if (presetS2IsSet) {
                for (Integer i : presetS2Array) {
                    if (attack == i) {
                        realGameMapButton[attack].setIcon(fireIcon);
                        realGameMapButton[attack].setDisabledIcon(fireIcon);
                        realGameMapButton[attack].setEnabled(false);
                        if (i == 11 || i == 12) {
                        	passShipSize = 2;
                            ship2Count++;
                            ship2Damage += 50;
                        } else if (i == 81 || i == 82 || i == 83) {
                        	passShipSize = 3;
                            ship3Count++;
                            ship3Damage += 33;
                        } else if (i == 37 || i == 47 || i == 57 || i == 67) {
                        	passShipSize = 4;
                            ship4Count++;
                            ship4Damage += 25;
                        } else if (i == 14 || i == 15 || i == 16 || i == 17 || i == 18) {
                        	passShipSize = 5;
                            ship5Count++;
                            ship5Damage += 20;
                        }
                        updateProgressBars();
                        checkIfShipDefeated();
                        hit = true;
                        break;
                    }
                }
            }
            if (presetS3IsSet) {
                for (Integer i : presetS3Array) {
                    if (attack == i) {
                        realGameMapButton[attack].setIcon(fireIcon);
                        realGameMapButton[attack].setDisabledIcon(fireIcon);
                        realGameMapButton[attack].setEnabled(false);
                        if (i == 8 || i == 9) {
                        	passShipSize = 2;
                            ship2Count++;
                            ship2Damage += 50;
                        } else if (i == 78 || i == 88 || i == 98) {
                        	passShipSize = 3;
                            ship3Count++;
                            ship3Damage += 33;
                        } else if (i == 0 || i == 10 || i == 20 || i == 30) {
                        	passShipSize = 4;
                            ship4Count++;
                            ship4Damage += 25;
                        } else if (i == 43 || i == 53 || i == 63 || i == 73 || i == 83) {
                        	passShipSize = 5;
                            ship5Count++;
                            ship5Damage += 20;
                        }
                        updateProgressBars();
                        checkIfShipDefeated();
                        hit = true;
                        break;
                    }
                }
            }
            if (presetS4IsSet) {
                for (Integer i : presetS4Array) {
                    if (attack == i) {
                        realGameMapButton[attack].setIcon(fireIcon);
                        realGameMapButton[attack].setDisabledIcon(fireIcon);
                        realGameMapButton[attack].setEnabled(false);
                        if (i == 16 || i == 17) {
                        	passShipSize = 2;
                            ship2Count++;
                            ship2Damage += 50;
                        } else if (i == 40 || i == 50 || i == 60) {
                        	passShipSize = 3;
                            ship3Count++;
                            ship3Damage += 33;
                        } else if (i == 0 || i == 1 || i == 2 || i == 3) {
                        	passShipSize = 4;
                            ship4Count++;
                            ship4Damage += 25;
                        } else if (i == 29 || i == 39 || i == 49 || i == 59 || i == 69) {
                        	passShipSize = 5;
                            ship5Count++;
                            ship5Damage += 20;
                        }
                        updateProgressBars();
                        checkIfShipDefeated();
                        hit = true;
                        break;
                    }
                }
            }
            if (presetS5IsSet) {
                for (Integer i : presetS5Array) {
                    if (attack == i) {
                        realGameMapButton[attack].setIcon(fireIcon);
                        realGameMapButton[attack].setDisabledIcon(fireIcon);
                        realGameMapButton[attack].setEnabled(false);
                        if (i == 39 || i == 49) {
                        	passShipSize = 2;
                            ship2Count++;
                            ship2Damage += 50;
                        } else if (i == 28 || i == 38 || i == 48) {
                        	passShipSize = 3;
                            ship3Count++;
                            ship3Damage += 33;
                        } else if (i == 44 || i == 45 || i == 46 || i == 47) {
                        	passShipSize = 4;
                            ship4Count++;
                            ship4Damage += 25;
                        } else if (i == 5 || i == 6 || i == 7 || i == 8 || i == 9) {
                        	passShipSize = 5;
                            ship5Count++;
                            ship5Damage += 20;
                        }
                        updateProgressBars();
                        checkIfShipDefeated();
                        hit = true;
                        break;
                    }
                }
            }
            if (userLayoutSet) {
                for (Integer i : userLayoutPresetArray) {
                    if (attack == i) {
                        realGameMapButton[attack].setIcon(fireIcon);
                        realGameMapButton[attack].setDisabledIcon(fireIcon);
                        realGameMapButton[attack].setEnabled(false);
                        if (Ship1TargetArray.contains(i)) {
                        	passShipSize = 2;
                            ship2Count++;
                            ship2Damage += 50;
                        } else if (Ship2TargetArray.contains(i)) {
                        	passShipSize = 3;
                            ship3Count++;
                            ship3Damage += 33;
                        } else if (Ship3TargetArray.contains(i)) {
                        	passShipSize = 4;
                            ship4Count++;
                            ship4Damage += 25;
                        } else if (Ship4TargetArray.contains(i)) {
                        	passShipSize = 5;
                            ship5Count++;
                            ship5Damage += 20;
                        }
                        updateProgressBars();
                        checkIfShipDefeated();
                        hit = true;
                        break;
                    }
                }
            }
            if (!hit) {///////////////////////////////
                realGameMapButton[attack].setIcon(missIcon);
                realGameMapButton[attack].setDisabledIcon(missIcon);
                realGameMapButton[attack].setEnabled(false);
            }
    }

    // Helper method to check if ships are defeated
    /**
     * Checks if ships are defeated and updates the status.
     */
    private void checkIfShipDefeated() {
        if (ship2Count == 2 && !ship2Defeated) {
            JOptionPane.showMessageDialog(null, "Ship1 has been defeated", "Report", JOptionPane.INFORMATION_MESSAGE);
            ship2Defeated = true;
        }
        if (ship3Count == 3 && !ship3Defeated) {
            JOptionPane.showMessageDialog(null, "Ship2 has been defeated", "Report", JOptionPane.INFORMATION_MESSAGE);
            ship3Defeated = true;
        }
        if (ship4Count == 4 && !ship4Defeated) {
            JOptionPane.showMessageDialog(null, "Ship3 has been defeated", "Report", JOptionPane.INFORMATION_MESSAGE);
            ship4Defeated = true;
        }
        if (ship5Count == 5 && !ship5Defeated) {
            JOptionPane.showMessageDialog(null, "Ship4 has been defeated", "Report", JOptionPane.INFORMATION_MESSAGE);
            ship5Defeated = true;
        }
    }


}

