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
        textField.setVisible(true);
        textField.setPreferredSize(new Dimension(250, 40));
        parent.add(textField);
        button = new JButton("Submit title");
        button.addActionListener(new AddTextHandler());
        button.setVisible(true);
        button.setFocusable(false);
        parent.add(button);
        parent.revalidate();
    }

    private class AddTextHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            incompleteText.setTitle(textField.getText());
            textField.setText("");
            button.setVisible(false);
            parent.remove(button);
            otherButton = new JButton("Submit word count");
            otherButton.addActionListener(new OtherButtonHandler());
            otherButton.setVisible(true);
            otherButton.setFocusable(false);
            parent.add(otherButton);
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
                otherButton.setVisible(false);
                parent.remove(otherButton);
                textField.setVisible(false);
                parent.remove(textField);
                SelectGenreButtonList selectGenreButtonList = new SelectGenreButtonList(incompleteText, frame, parent);
            }
        }
    }

}
