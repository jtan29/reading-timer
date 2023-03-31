package ui.menu;

import ui.ReadingTimerAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Representation of a menu item to add new Texts
public class AddTextMenuItem extends MenuItem {


    // MODIFIES: this
    // EFFECTS: makes a new AddTextMenuItem
    public AddTextMenuItem(ReadingTimerAppGUI frame, JComponent parent) {
        super("Add New Text", frame, parent);
        addListener();
    }

    @Override

    // MODIFIES: this
    // EFFECTS: adds an action listener to the menu item
    public void addListener() {
        this.menuItem.addActionListener(new AddActionHandler());
    }

    // The action listener for AddTextMenuItem
    private class AddActionHandler implements ActionListener {
        @Override

        // MODIFIES: this
        // EFFECTS: tells frame to set up options to add a text
        public void actionPerformed(ActionEvent e) {
            frame.addTextShowOptions();
        }
    }
}