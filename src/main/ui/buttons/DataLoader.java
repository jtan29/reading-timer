package ui.buttons;

import model.ListOfText;
import persistence.ReadJson;
import ui.ReadingTimerAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DataLoader {
    private ReadJson readJson;
    private static final String FILE_PATH = "./data/ListOfText.json";
    private JButton load;
    private JButton skip;
    private ReadingTimerAppGUI frame;
    private ListOfText texts;
    private JComponent parent;

    public DataLoader(ReadingTimerAppGUI frame, JComponent parent) {
        this.frame = frame;
        this.parent = parent;
        this.readJson = new ReadJson(FILE_PATH);
        load = new JButton("Load saved data");
        skip = new JButton("Skip");
        load.setVisible(true);
        load.setFocusable(false);
        skip.setVisible(true);
        skip.setFocusable(false);
        parent.add(load);
        parent.add(skip);
        load.addActionListener(new DataLoaderActionHandler());
        skip.addActionListener(new SkipActionHandler());
    }

    private class DataLoaderActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                texts = readJson.read();
            } catch (IOException exception) {
                frame.setTextArea("There was an issue reading your saved data.");
            }
            frame.initializeTexts(texts);
            load.setVisible(false);
            skip.setVisible(false);
            frame.remove(parent);
        }
    }

    private class SkipActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e2) {
            frame.initializeTexts(new ListOfText());
            load.setVisible(false);
            skip.setVisible(false);
            frame.remove(parent);
        }
    }

}
