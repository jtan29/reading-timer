package ui.menu;

import ui.ReadingTimerAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndTimer extends MenuItem {

    public EndTimer(ReadingTimerAppGUI frame, JComponent parent) {
        super("End Timer", frame, parent);
        this.addListener();

    }

    public void addListener() {
        this.menuItem.addActionListener(new EndTimerActionHandler());
    }

    private class EndTimerActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (frame.getSelectedText() == null) {
                frame.setTextArea("No text selected, try again.");
            } else {
                if (frame.getSelectedText().getTimerStatus()) {
                    frame.setTextArea("Ending timer...");
                    frame.endTimer();
                } else {
                    frame.setTextArea("No timer running for selected text.");
                }
            }
        }
    }
}