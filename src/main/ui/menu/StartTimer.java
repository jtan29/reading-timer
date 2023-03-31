package ui.menu;

import model.Text;
import ui.ReadingTimerAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Representation of the menu item to start a Text's timer
public class StartTimer extends MenuItem {

    // MODIFIES: this
    // EFFECTS: creates a new StartTimer
    public StartTimer(ReadingTimerAppGUI frame, JComponent parent) {
        super("Start Timer", frame, parent);
        this.addListener();

    }

    // MODIFIES: this
    // EFFECTS: adds an action listener to the StartTimer action listener
    public void addListener() {
        this.menuItem.addActionListener(new StartTimerActionHandler());
    }

    // The action listener for the StartTimer menu item
    private class StartTimerActionHandler implements ActionListener {
        @Override

        // MODIFIES: this
        // EFFECTS: starts the timer for the selected text, if possible
        public void actionPerformed(ActionEvent e) {
            if (frame.getSelectedText() == null) {
                frame.setTextArea("No text selected, try again");
            } else {
                if (frame.getSelectedText().getTimerStatus() || frame.getSelectedText().getIsComplete()) {
                    frame.setTextArea("This action is not supported for the selected text.");
                } else {
                    frame.startTimer();
                    String newString = "Timer running for: ";
                    for (Text t : frame.getTexts().getTexts()) {
                        if (t.getTimerStatus()) {
                            newString = newString + t.getTitle() + ",";
                        }
                    }
                    frame.setLabelText(newString);
                    frame.setTimerIconVisible();
                    frame.setTextArea("Starting timer!");
                }
            }
        }
    }
}