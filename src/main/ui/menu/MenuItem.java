package ui.menu;

import ui.ReadingTimerAppGUI;

import javax.swing.*;

// Abstract representation of the menu items in the GUI
public abstract class MenuItem {
    protected JMenuItem menuItem;
    protected ReadingTimerAppGUI frame;

    // MODIFIES: this
    // EFFECTS: makes a new MenuItem
    public MenuItem(String label, ReadingTimerAppGUI frame, JComponent parent) {
        menuItem = new JMenuItem(label);
        this.frame = frame;
        parent.add(menuItem);
    }

    // MODIFIES: this
    // EFFECTS: adds an action listener
    public abstract void addListener();
}
