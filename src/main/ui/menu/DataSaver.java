package ui.menu;

import persistence.WriteJson;
import ui.ReadingTimerAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DataSaver extends MenuItem {
    private WriteJson writeJson;
    private static final String FILE_PATH = "./data/ListOfText.json";

    public DataSaver(ReadingTimerAppGUI frame, JComponent parent) {
        super("Save Data", frame, parent);
        writeJson = new WriteJson(FILE_PATH);
        addListener();
    }

    public void addListener() {
        this.menuItem.addActionListener(new SaveActionHandler());
    }

    private class SaveActionHandler implements ActionListener {
        @Override
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
