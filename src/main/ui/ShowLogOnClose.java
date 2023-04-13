package ui;

import model.EventLog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// Shows a log of events when the GUI is closed
public class ShowLogOnClose implements WindowListener {
    private ReadingTimerAppGUI frame;

    // MODIFIES: this
    // EFFECTS: makes a new ShowLogOnClose
    public ShowLogOnClose(ReadingTimerAppGUI frame) {
        this.frame = frame;
        frame.addWindowListener(this);

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    // MODIFIES: this
    // EFFECTS: prints the log of events and closes the window when the close button is clicked
    @Override
    public void windowClosing(WindowEvent e) {
        for (model.Event event : EventLog.getInstance()) {
            System.out.println(event.toString());
        }
        ActionListener task = new ActionListener() {
            boolean alreadyDisposed = false;
            public void actionPerformed(ActionEvent e) {
                if (frame.isDisplayable()) {
                    alreadyDisposed = true;
                    frame.dispose();
                }
            }
        };
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
