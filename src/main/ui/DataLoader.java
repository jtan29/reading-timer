package ui;

import model.ListOfText;
import persistence.ReadJson;
import persistence.WriteJson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DataLoader {
    private ReadJson readJson;
    private static final String FILE_PATH = "./data/ListOfText.json";
    private JButton button;
    private ReadingTimerAppGUI frame;
    private ListOfText texts;
    private JComponent parent;

    public DataLoader(ReadingTimerAppGUI frame, JComponent parent) {
        this.frame = frame;
        this.parent = parent;
        this.readJson = new ReadJson(FILE_PATH);
        try {
            this.texts = readJson.read();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        button = new JButton("Load saved data");
        button.setVisible(true);
        parent.add(button);
        button.addActionListener(new DataLoaderActionHandler());
    }

    private class DataLoaderActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.addTexts(texts);
            button.setVisible(false);
            parent.remove(button);
            frame.remove(parent);
        }
    }

}
