package ui.buttons;

import model.FictionGenre;
import model.Genre;
import model.NonFictionGenre;
import model.Text;
import ui.ReadingTimerAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Representation for all the buttons representing genres to be selected
public class SelectGenreButtonList {
    private List<JButton> buttonList;
    private Text incompleteText;
    private ReadingTimerAppGUI frame;
    private JComponent parent;
    private JButton nonFiction;
    private JButton fiction;

    // MODIFIES: this
    // EFFECTS: creates a new SelectGenreButtonList
    public SelectGenreButtonList(Text incompleteText, ReadingTimerAppGUI frame, JComponent parent) {
        this.frame = frame;
        this.incompleteText = incompleteText;
        this.buttonList = new ArrayList<>();
        nonFiction = new JButton("Non-fiction");
        nonFiction.setFocusable(false);
        fiction = new JButton("Fiction");
        fiction.setFocusable(false);
        this.parent = parent;
        parent.add(nonFiction);
        parent.add(fiction);
        nonFiction.addActionListener(new NonFictionActionHandler());
        fiction.addActionListener(new FictionActionHandler());
    }

    // MODIFIES: this
    // EFFECTS: makes a new SelectGenreButton and adds the corresponding button to the list
    public void addNewButton(Genre genre) {
        SelectGenreButton selectGenreButton = new SelectGenreButton(this, incompleteText, genre,
                frame, parent);
        buttonList.add(selectGenreButton.getButton());
    }


    // MODIFIES: this
    // EFFECTS: hides all buttons
    public void removeAllButtons() {
        for (JButton button : buttonList) {
            button.setVisible(false);
            parent.remove(button);
            frame.remove(parent);
        }
    }

    // the action listener for the NonFiction button
    private class NonFictionActionHandler implements ActionListener {
        @Override

        // MODIFIES: this
        // EFFECTS: displays all the NonFiction genres as buttons to select
        public void actionPerformed(ActionEvent e) {
            for (Genre genre : NonFictionGenre.values()) {
                addNewButton(genre);
            }
            nonFiction.setVisible(false);
            fiction.setVisible(false);
            parent.remove(nonFiction);
            parent.remove(fiction);
        }
    }

    // the action listener for the Fiction button
    private class FictionActionHandler implements ActionListener {
        @Override

        // MODIFIES: this
        // EFFECTS: displays all the Fiction genres as buttons to select
        public void actionPerformed(ActionEvent e) {
            for (Genre genre : FictionGenre.values()) {
                addNewButton(genre);
            }
            nonFiction.setVisible(false);
            fiction.setVisible(false);
            parent.remove(nonFiction);
            parent.remove(fiction);
        }
    }

}
