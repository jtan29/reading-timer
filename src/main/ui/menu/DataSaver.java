package ui.menu;

import persistence.WriteJson;
import ui.ReadingTimerAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Representation of menu item to save data

public class DataSaver extends MenuItem {
    private WriteJson writeJson;
    private static final String FILE_PATH = "./data/ListOfText.json";

    // MODIFIES: this
    // EFFECTS: makes a new DataSaver
    public DataSaver(ReadingTimerAppGUI frame, JComponent parent) {
        super("Save Data", frame, parent);
        writeJson = new WriteJson(FILE_PATH);
        addListener();
    }

    // MODIFIES: this
    // EFFECTS: adds an action listener for the button to save data
    public void addListener() {
        this.menuItem.addActionListener(new DataSaverActionListener());
    }

    // The action listener for the Save Data menu item
    private class DataSaverActionListener implements ActionListener {
        @Override

        // MODIFIES: this
        // EFFECTS: saves the data if possible and informs the user
        public void actionPerformed(ActionEvent e) {
            try {
                writeJson.start();
                writeJson.writeFile(frame.getTexts());
                writeJson.close();
                frame.setTextArea("Your data has been saved.");
            } catch (IOException exception) {
                frame.setTextArea("There was an error saving your data.");
            }
        }
    }
}
