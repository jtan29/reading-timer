package ui.menu;

import ui.ReadingTimerAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToggleComplete extends MenuItem {

    public ToggleComplete(ReadingTimerAppGUI frame, JComponent parent) {
        super("Toggle Complete", frame, parent);
        this.addListener();

    }

    public void addListener() {
        this.menuItem.addActionListener(new ToggleCompleteActionHandler());
    }

    private class ToggleCompleteActionHandler implements ActionListener {
        @Override
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