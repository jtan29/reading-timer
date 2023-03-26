package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveTextMenuItem extends MenuItem {

    public RemoveTextMenuItem(ReadingTimerAppGUI frame, JComponent parent) {
        super("Remove Selected Text", frame, parent);
    }

    public void addListener() {
        this.menuItem.addActionListener(new RemoveActionHandler());
    }

    private class RemoveActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.removeText();
        }
    }
}
