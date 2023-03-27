package ui.buttons;

import model.Text;
import ui.ReadingTimerAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTextTitleButton {
    private JButton button;
    private JButton otherButton;
    private ReadingTimerAppGUI frame;
    private Text incompleteText;
    private JTextField textField;
    private JComponent parent;

    public AddTextTitleButton(Text t, ReadingTimerAppGUI frame, JComponent parent) {
        this.incompleteText = t;
        this.frame = frame;
        this.parent = parent;
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(250, 40));
        parent.add(textField);
        button = new JButton("Submit title");
        button.setVisible(true);
        button.setFocusable(false);
        parent.add(button);
        button.addActionListener(new AddTextHandler());
    }

    private class AddTextHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            incompleteText.setTitle(textField.getText());
            textField.setText("");
            parent.remove(button);
            button.setVisible(false);
            otherButton = new JButton("Submit word count");
            otherButton.setVisible(true);
            otherButton.setFocusable(false);
            parent.add(otherButton);
            otherButton.addActionListener(new OtherButtonHandler());
        }


        private class OtherButtonHandler implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newWordCount;
                try {
                    newWordCount = Integer.parseInt(textField.getText());
                } catch (NumberFormatException exception) {
                    newWordCount = 100;
                }
                incompleteText.setWordCount(newWordCount);
                parent.remove(otherButton);
                otherButton.setVisible(false);
                parent.remove(textField);
                textField.setVisible(false);
                SelectGenreButtonList selectGenreButtonList = new SelectGenreButtonList(incompleteText, frame, parent);
            }
        }
    }

}
