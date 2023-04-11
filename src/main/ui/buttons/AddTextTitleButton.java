package ui.buttons;

import model.Text;
import ui.ReadingTimerAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Representation of the option to input Text information
public class AddTextTitleButton {
    private JButton button;
    private JButton otherButton;
    private ReadingTimerAppGUI frame;
    private Text incompleteText;
    private JTextField textField;
    private JComponent parent;

    // MODIFIES: this
    // EFFECTS: creates a new AddTextTitleButton
    public AddTextTitleButton(Text t, ReadingTimerAppGUI frame, JComponent parent) {
        this.incompleteText = t;
        this.frame = frame;
        this.parent = parent;
        textField = new JTextField();
        textField.setVisible(true);
        textField.setPreferredSize(new Dimension(250, 40));
        parent.add(textField);
        button = new JButton("Submit title");
        button.addActionListener(new AddTextButtonActionListener());
        button.setVisible(true);
        button.setFocusable(false);
        parent.add(button);
        parent.revalidate();
    }

   // the action listener for the title submit button
    private class AddTextButtonActionListener implements ActionListener {

        @Override

        // MODIFIES: this
        // EFFECTS: sets the Text to be added to have the title entered in the text field,
        //          and sets up the text field to take the word count
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


        // the action listener for the word count submit button
        private class OtherButtonHandler implements ActionListener {
            @Override

            // MODIFIES: this
            // EFFECTS: sets the text to be added to have the given word count and sets up genre selection buttons
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
