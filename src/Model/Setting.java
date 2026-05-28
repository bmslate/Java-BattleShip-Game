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

import java.util.prefs.Preferences;

/**
 * The Setting class manages the application settings using Java's Preferences API.
 * It is primarily used to save and load the user's preferred language setting.
 */
public class Setting {
    
    // Preferences object for storing and retrieving user preferences
    private Preferences prefs;

    /**
     * Constructor for the Setting class.
     * Initializes the Preferences object for storing and retrieving settings.
     */
    public Setting() {
        prefs = Preferences.userNodeForPackage(Setting.class);
    }

    /**
     * Saves the user's preferred language to the preferences storage.
     * 
     * @param language The language code to be saved (e.g., "en" for English, "zh" for Chinese).
     */
    public void saveLanguage(String language) {
        prefs.put("language", language);
    }

    /**
     * Loads the user's preferred language from the preferences storage.
     * If no language has been saved, it defaults to English ("en").
     * 
     * @return The language code stored in preferences, or "en" if not set.
     */
    public String loadLanguage() {
        return prefs.get("language", "en");
    }
}
