package ui;

import javax.swing.*;

public abstract class MenuItem {
    protected JMenuItem menuItem;
    protected ReadingTimerAppGUI frame;

    public MenuItem(String label, ReadingTimerAppGUI frame, JComponent parent) {
        menuItem = new JMenuItem(label);
        this.frame = frame;
        parent.add(menuItem);
    }

    public abstract void addListener();
}
