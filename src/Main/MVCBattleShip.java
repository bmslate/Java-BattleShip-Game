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
package Main;

import java.util.Locale;

import Controller.BattleShipController;
import Model.BattleShipModel;
import Model.Setting;
import View.BattleShipView;
import View.GameInterFace;
import View.Strategy;

/**
 * The MVCBattleShip class serves as the entry point for the Battleship game.
 * It initializes the Model, View, and Controller components and manages the game lifecycle.
 */
public class MVCBattleShip {
    private static BattleShipView theView;
    private static Strategy strategyView;
    private static GameInterFace gameInterface;

    /**
     * Default constructor for the MVCBattleShip class.
     */
    public MVCBattleShip() {
    }

    /**
     * The main method is the entry point of the application.
     * It initializes the game settings, creates the MVC components, and starts the game.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Load settings and initialize locale
        Setting setting = new Setting();
        String language = setting.loadLanguage();
        Locale locale = new Locale(language);

        // Initialize view components and set locale
        theView = new BattleShipView();
        theView.setLocale(locale);

        strategyView = Strategy.getInstance(locale);
        strategyView.setLocale(locale);

        // Initialize the model and controller
        BattleShipModel theModel = new BattleShipModel();
        BattleShipController theController = new BattleShipController(theView, theModel, locale, strategyView);

        // Start the controller and game
        theController.start();

        // Display the winner panel and restart the game when a winner is determined
        theController.winnerPanelShow();
        while (true) {
            theModel.initiateNewComputerMap();
            theController.winnerPanelShow();
        }
    }

    /**
     * Updates the language settings across all view components.
     *
     * @param locale the new locale to be applied to the view components.
     */
    public static void updateLanguage(Locale locale) {
        if (theView != null) {
            theView.setLocale(locale);
        }
        if (strategyView != null) {
            strategyView.setLocale(locale);
        }
        if (gameInterface != null) {
            gameInterface.setLocale(locale);
        }
    }

    /**
     * Sets the GameInterFace instance.
     *
     * @param game the GameInterFace instance to be used by the MVC framework.
     */
    public static void setGameInterface(GameInterFace game) {
        gameInterface = game;
    }
}
