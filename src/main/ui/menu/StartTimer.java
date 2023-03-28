package ui.menu;

import model.Text;
import ui.ReadingTimerAppGUI;

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
            if (frame.getSelectedText() == null) {
                frame.setTextArea("No text selected, try again");
            } else {
                if (frame.getSelectedText().getTimerStatus() || frame.getSelectedText().getIsComplete()) {
                    frame.setTextArea("This action is not supported for the selected text.");
                } else {
                    frame.startTimer();
                    String newString = "Timer running for: ";
                    for (Text t : frame.getTexts().getTexts()) {
                        if (t.getTimerStatus()) {
                            newString = newString + t.getTitle() + ", ";
                        }
                    }
                    frame.setLabelText(newString);
                    frame.setTimerIconVisible();
                    frame.setTextArea("Starting timer!");
                }
            }
        }
    }
}