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

import javax.swing.*;
import Controller.BattleShipController;
import Main.MVCBattleShip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import Model.Setting;

/**
 * The SettingUI class provides a user interface for adjusting application settings such as volume and language preferences.
 * It includes a slider for volume control and a combo box for language selection.
 */
public class SettingUI {

    private JFrame frame;  // Main frame of the settings window
    private JSlider volumeSlider;  // Slider for adjusting volume
    private JComboBox<String> languageComboBox;  // Combo box for selecting language
    private ResourceBundle messages;  // ResourceBundle for localized messages
    private Preferences prefs;  // Preferences to save and load settings

    /**
     * Constructor for the SettingUI class. Initializes the settings UI components.
     */
    public SettingUI() {
        // Load user preferences for language setting
        prefs = Preferences.userNodeForPackage(Setting.class);
        String language = prefs.get("language", "en");
        loadResourceBundle(new Locale(language));

        // Initialize the frame
        frame = new JFrame(messages.getString("SettingUI.title"));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(980, 800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Create content panel and set layout
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create and add volume label
        JLabel volumeLabel = new JLabel(messages.getString("SettingUI.volume"));
        volumeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(volumeLabel);

        // Create and add volume slider
        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        volumeSlider.setMajorTickSpacing(10);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        volumeSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(volumeSlider);

        // Create and add language label
        JLabel languageLabel = new JLabel(messages.getString("SettingUI.language"));
        languageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(languageLabel);

        // Create and add language combo box
        String[] languages = {"English", "中文"};
        languageComboBox = new JComboBox<>(languages);
        languageComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        languageComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Change the language setting based on the user's selection
                String selectedLanguage = (String) languageComboBox.getSelectedItem();
                Locale newLocale = selectedLanguage.equals("English") ? Locale.ENGLISH : Locale.CHINESE;
                prefs.put("language", newLocale.getLanguage());
                loadResourceBundle(newLocale);
                updateTexts();
                MVCBattleShip.updateLanguage(newLocale); // Update the main interface language
                BattleShipController.updateGameInterfaceLanguage(newLocale); // Update GameInterface language
            }
        });
        contentPanel.add(languageComboBox);

        // Set the initial selection of the language combo box
        languageComboBox.setSelectedItem(language.equals("en") ? "English" : "中文");

        // Add content panel to frame
        frame.add(contentPanel, BorderLayout.CENTER);
    }

    /**
     * Makes the settings window visible to the user.
     */
    public void show() {
        frame.setVisible(true);
    }

    /**
     * Loads the resource bundle for the specified locale.
     * 
     * @param locale The locale for which to load the resource bundle.
     */
    private void loadResourceBundle(Locale locale) {
        messages = ResourceBundle.getBundle("MessagesBundle", locale);
    }

    /**
     * Updates the text labels in the UI to reflect the current language setting.
     */
    private void updateTexts() {
        frame.setTitle(messages.getString("SettingUI.title"));
        ((JLabel) volumeSlider.getParent().getComponent(0)).setText(messages.getString("SettingUI.volume"));
        ((JLabel) languageComboBox.getParent().getComponent(2)).setText(messages.getString("SettingUI.language"));
    }
}
