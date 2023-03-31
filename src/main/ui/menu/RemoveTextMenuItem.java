package ui.menu;

import ui.ReadingTimerAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Representation of the menu item to remove Texts
public class RemoveTextMenuItem extends MenuItem {

    // MODIFIES: this
    // EFFECTS: creates a new RemoveTextMenuItem
    public RemoveTextMenuItem(ReadingTimerAppGUI frame, JComponent parent) {
        super("Remove Selected Text", frame, parent);
        addListener();
    }

    // MODIFIES: this
    // EFFECTS: adds a new action listener to the RemoveText menu item
    public void addListener() {
        this.menuItem.addActionListener(new RemoveActionHandler());
    }

    // The action listener for the RemoveTextMenuItem
    private class RemoveActionHandler implements ActionListener {
        @Override
        // MODIFIES: this
        // EFFECTS: removes the selected text
        public void actionPerformed(ActionEvent e) {
            frame.removeText();
        }
    }
}
