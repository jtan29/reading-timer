package ui;

import model.Text;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextButton {
    private JButton button;
    private ReadingTimerAppGUI frame;
    private Text text;

    public TextButton(Text t, ReadingTimerAppGUI frame, JComponent parent) {
        this.frame = frame;
        this.text = t;
        button = new JButton(t.getTitle());
        button.setVisible(true);
        parent.add(button);
        button.addActionListener(new TextButtonActionHandler());

    }

    private class TextButtonActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setSelectedText(text);
        }
    }
}
