package ui.buttons;

import model.FictionGenre;
import model.Genre;
import model.Text;
import ui.ReadingTimerAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Representation for genre selection buttons
public class SelectGenreButton {
    private Genre genre;
    private JButton button;
    private ReadingTimerAppGUI frame;
    private JComponent parent;
    private Text incompleteText;
    private SelectGenreButtonList buttonList;

    // MODIFIES: this
    // EFFECTS: creates a new SelectGenreButton
    public SelectGenreButton(SelectGenreButtonList buttonList, Text incompleteText,
                             Genre g, ReadingTimerAppGUI frame, JComponent parent) {
        this.genre = g;
        this.frame = frame;
        this.parent = parent;
        this.buttonList = buttonList;
        this.button = new JButton(g.getGenreDescription());
        button.setFocusable(false);
        this.incompleteText = incompleteText;
        parent.add(button);
        button.addActionListener(new SelectGenreButtonActionListener());

    }


    public JButton getButton() {
        return this.button;
    }

    // the action listener for the button representing a genre
    private class SelectGenreButtonActionListener implements ActionListener {
        @Override

        // MODIFIES: this
        // EFFECTS: sets the text to be added to have the genre associated with this button, and hides all the buttons
        public void actionPerformed(ActionEvent e) {
            incompleteText.setGenre(genre);
            frame.addText(incompleteText);
            buttonList.removeAllButtons();

        }
    }
}
