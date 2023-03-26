package ui;

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
           // frame.endTimer();
            frame.setTextArea("Ending Timer...");
        }
    }
}