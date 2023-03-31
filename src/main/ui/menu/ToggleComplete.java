package ui.menu;

import ui.ReadingTimerAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Representation of the menu item to toggle Text completeness
public class ToggleComplete extends MenuItem {

    // MODIFIES: this
    // EFFECTS: creates a new ToggleComplete
    public ToggleComplete(ReadingTimerAppGUI frame, JComponent parent) {
        super("Toggle Complete", frame, parent);
        this.addListener();

    }

    // MODIFIES: this
    // EFFECTS: adds an action listener to the ToggleComplete menu item
    public void addListener() {
        this.menuItem.addActionListener(new ToggleCompleteActionHandler());
    }

    // the action listener for the Toggle Complete menu item
    private class ToggleCompleteActionHandler implements ActionListener {
        @Override

        // MODIFIES: this
        // EFFECTS: if possible, changes a complete text to incomplete and a complete text to complete
        public void actionPerformed(ActionEvent e) {
            if (frame.getSelectedText() == null) {
                frame.setTextArea("No text selected, try again.");
            } else {
                if (frame.getSelectedText().getIsComplete()) {
                    frame.getSelectedText().setIsComplete(false);
                } else {
                    frame.getSelectedText().setIsComplete(true);
                }
            }
        }
    }
}