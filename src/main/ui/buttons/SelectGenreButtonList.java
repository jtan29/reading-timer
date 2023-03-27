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

public class SelectGenreButtonList {
    private List<JButton> buttonList;
    private Text incompleteText;
    private ReadingTimerAppGUI frame;
    private JComponent parent;
    private JButton nonFiction;
    private JButton fiction;

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

    public void addNewButton(Genre genre) {
        SelectGenreButton selectGenreButton = new SelectGenreButton(this, incompleteText, genre,
                frame, parent);
        buttonList.add(selectGenreButton.getButton());
    }

    public void removeAllButtons() {
        for (JButton button : buttonList) {
            parent.remove(button);
            button.setVisible(false);
            frame.remove(parent);
        }
    }

    private class NonFictionActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (Genre genre : NonFictionGenre.values()) {
                addNewButton(genre);
            }
        }
    }

    private class FictionActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (Genre genre : FictionGenre.values()) {
                addNewButton(genre);
            }
        }
    }

}
