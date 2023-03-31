package ui.menu;

import model.Text;
import ui.ReadingTimerAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Representation of the menu item that ends a Text's timer
public class EndTimer extends MenuItem {

    // MODIFIES: this
    // EFFECTS: makes a new EndTimer
    public EndTimer(ReadingTimerAppGUI frame, JComponent parent) {
        super("End Timer", frame, parent);
        this.addListener();

    }

    // MODIFIES: this
    // EFFECTS: adds a new action listener for the EndTimer menu item
    public void addListener() {
        this.menuItem.addActionListener(new EndTimerActionHandler());
    }

    // The action listener for the EndTimer menu item
    private class EndTimerActionHandler implements ActionListener {
        @Override

        // MODIFIES: this
        // EFFECTS: ends the timer for the selected text, if possible, and informs the user
        public void actionPerformed(ActionEvent e) {
            if (frame.getSelectedText() == null) {
                frame.setTextArea("No text selected, try again.");
            } else {
                if (frame.getSelectedText().getTimerStatus()) {
                    frame.setTextArea("Ending timer...");
                    frame.endTimer();
                    String newString = "Timer running for: ";
                    for (Text t : frame.getTexts().getTexts()) {
                        if (t.getTimerStatus()) {
                            newString = newString + t.getTitle() + ", ";
                        }
                    }
                    frame.setLabelText(newString);
                    if (!frame.anyRunningTimer()) {
                        frame.setTimerIconNotVisible();
                    }
                } else {
                    frame.setTextArea("No timer running for selected text.");
                }
            }
        }
    }
}