package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartTimer extends MenuItem {

    public StartTimer(ReadingTimerAppGUI frame, JComponent parent) {
        super("Start Timer", frame, parent);
        this.addListener();

    }

    public void addListener() {
        this.menuItem.addActionListener(new StartTimerActionHandler());
    }

    private class StartTimerActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // frame.startTimer();
            frame.setTextArea("Starting timer!");
        }
    }
}