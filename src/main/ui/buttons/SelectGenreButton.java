package ui.buttons;

import model.FictionGenre;
import model.Genre;
import model.Text;
import ui.ReadingTimerAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectGenreButton {
    private Genre genre;
    private JButton button;
    private ReadingTimerAppGUI frame;
    private JComponent parent;
    private Text incompleteText;
    private SelectGenreButtonList buttonList;

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

    private class SelectGenreButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            incompleteText.setGenre(genre);
            frame.addText(incompleteText);
            buttonList.removeAllButtons();

        }
    }
}
