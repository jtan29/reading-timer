package ui.menu;

import ui.ReadingTimerAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTextMenuItem extends MenuItem {

    public AddTextMenuItem(ReadingTimerAppGUI frame, JComponent parent) {
        super("Add New Text", frame, parent);
        addListener();
    }

    public void addListener() {
        this.menuItem.addActionListener(new AddActionHandler());
    }

    private class AddActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.addTextShowOptions();
        }
    }
}