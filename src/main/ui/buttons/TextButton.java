package ui.buttons;

import model.Text;
import ui.ReadingTimerAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Representation button that represents a Text
public class TextButton {
    private JButton button;
    private ReadingTimerAppGUI frame;
    private Text text;

    // MODIFIES: this
    // EFFECTS: makes a new TextButton
    public TextButton(Text t, ReadingTimerAppGUI frame, JComponent parent) {
        this.frame = frame;
        this.text = t;
        button = new JButton(t.getTitle());
        button.setVisible(true);
        parent.add(button);
        button.setFocusable(false);
        button.addActionListener(new TextButtonActionHandler());

    }


    public JButton getButton() {
        return button;
    }

    // the action listener for the TextButton
    private class TextButtonActionHandler implements ActionListener {
        @Override

        // MODIFIES: this
        // EFFECTS: sets the frame's selected text to the text corresponding with this button
        public void actionPerformed(ActionEvent e) {
            frame.setSelectedText(text);
        }
    }
}
