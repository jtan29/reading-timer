package ui;

import model.ListOfText;
import model.NonFictionGenre;
import model.Text;
import ui.buttons.AddTextTitleButton;
import ui.buttons.DataLoader;
import ui.buttons.TextButton;
import ui.menu.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// The graphical interface for the reading timer
public class ReadingTimerAppGUI extends JFrame {
    private ListOfText texts;
    private List<JButton> buttonList;
    private Text selectedText;
    private JButton selectedButton;
    private JTextArea textArea;
    private JPanel textPanel;
    private JPanel displayPanel;
    private JLabel timerIcon;

    // MODIFIES: this
    // EFFECTS: creates a new ReadingTimerAppGUI
    public ReadingTimerAppGUI() {
        texts = new ListOfText();
        selectedText = null;
        selectedButton = null;
        buttonList = new ArrayList<JButton>();
        textPanel = new JPanel();
        displayPanel = new JPanel();
        setUpGraphics();
        setUpMenu();
        setUpTextBox();
    }

    // MODIFIES: this
    // EFFECTS: helper to set up GUI graphics
    private void setUpGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(1000, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        loadData();
    }

    // MODIFIES: this
    // EFFECTS: sets up the central text area to display information
    private void setUpTextBox() {
        displayPanel = new JPanel();
        // displayPanel.setBackground(Color.red);
        displayPanel.setBounds(200, 200, 200, 200);
        add(displayPanel, BorderLayout.CENTER);
        displayPanel.setLayout(new FlowLayout());
        textArea = new JTextArea(10, 30);
        displayPanel.add(textArea);
        textArea.setEditable(false);
        textArea.setText("No text selected");
        displayPanel.setVisible(true);
        ImageIcon imageIcon = new ImageIcon("./data/clock.png");
        timerIcon = new JLabel();
        timerIcon.setText("Timer running");
        timerIcon.setVerticalTextPosition(JLabel.BOTTOM);
        timerIcon.setHorizontalTextPosition(JLabel.CENTER);
        timerIcon.setIcon(imageIcon);
        displayPanel.add(timerIcon);
        timerIcon.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: sets up the menu bar and menu items
    private void setUpMenu() {
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu menuManage = new JMenu("Manage Texts");
        JMenu menuTimer = new JMenu("Manage Timer");
        RemoveTextMenuItem removeTextMenuItem = new RemoveTextMenuItem(this, menuManage);
        AddTextMenuItem addTextMenuItem = new AddTextMenuItem(this, menuManage);
        DataSaver dataSaver = new DataSaver(this, menuManage);
        StartTimer startTimer = new StartTimer(this, menuTimer);
        EndTimer endTimer = new EndTimer(this, menuTimer);
        ToggleComplete toggleComplete = new ToggleComplete(this, menuTimer);
        menuBar.add(menuManage);
        menuBar.add(menuTimer);
    }

    // MODIFIES: this
    // EFFECTS: sets up buttons to manage loading data when starting the GUI
    private void loadData() {
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new GridLayout(2, 1));
        dataPanel.setSize(new Dimension(0, 0));
        //   dataPanel.setBounds(0, 0, 300, 200);
        add(dataPanel, BorderLayout.SOUTH);
        DataLoader dataLoader = new DataLoader(this, dataPanel);
    }

    // MODIFIES: this
    // EFFECTS: sets up Texts when previous data is loaded in
    public void initializeTexts(ListOfText texts) {
        this.texts = texts;
        textPanel.setLayout(new GridLayout(4, 2));
        textPanel.setSize(new Dimension(0, 0));
        // textPanel.setBackground(Color.blue);
        add(textPanel, BorderLayout.SOUTH);
        for (Text t : texts.getTexts()) {
            TextButton textButton = new TextButton(t, this, textPanel);
            buttonList.add(textButton.getButton());
        }

    }

    // MODIFIES: this
    // EFFECTS: starts the timer on the selected text
    public void startTimer() {
        selectedText.startTimer();
    }

    // MODIFIES: this
    // EFFECTS: ends the timer on the selected text
    public void endTimer() {
        selectedText.endTimer();
    }


    // MODIFIES: this
    // EFFECTS: removes the selected text
    public void removeText() {
        if (selectedText != null) {
            texts.removeText(selectedText);
            selectedText = null;
            selectedButton.setVisible(false);
            textPanel.remove(selectedButton);
            buttonList.remove(selectedButton);
            selectedButton = null;
        }
    }

    // MODIFIES: this
    // EFFECTS: displays new graphical options for adding a new Text
    public void addTextShowOptions() {
        Text newText = new Text(0, "Placeholder", NonFictionGenre.NF_OTHER);
        JPanel addPanel = new JPanel();
        //  addPanel.setBackground(Color.green);
        addPanel.setBounds(200, 100, 200, 400);
        addPanel.setLayout(new FlowLayout());
        add(addPanel, BorderLayout.NORTH);
        addPanel.setVisible(true);
        AddTextTitleButton addTextTitleButton = new AddTextTitleButton(newText, this, addPanel);
    }

    // MODIFIES: this
    // EFFECTS: sets up a new button representing a Text
    public void addText(Text text) {
        texts.addText(text);
        TextButton textButton = new TextButton(text, this, textPanel);
        buttonList.add(textButton.getButton());
    }

    // MODIFIES: this
    // EFFECTS: switches selection to a new selected text and displays its information
    public void setSelectedText(Text t) {
        this.selectedText = t;
        int index = texts.getTexts().indexOf(selectedText);
        selectedButton = buttonList.get(index);
        String areaText;
        areaText = "Selected: " + t.getTitle() + "\n" + "Elapsed Time: " + t.calcTimeStatement() + "\n"
                + "Genre: " + t.getGenre().getGenreDescription() + "\n"
                + "Words: " + t.getWordCount() + "\n"
                + "Complete: " + t.getIsComplete();

        if (selectedText.getIsComplete()) {
            areaText = areaText + "\n" + "Your average reading speed was: " + selectedText.calcReadingSpeed()
                    + " words/minute.";
        }
        textArea.setText(areaText);

    }

    // MODIFIES: this
    // EFFECTS: displays the timer icon when timer is running
    public void setTimerIconVisible() {
        this.timerIcon.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: hides the timer icon when timer is no longer running for any texts
    public void setTimerIconNotVisible() {
        this.timerIcon.setVisible(false);
    }


    // MODIFIES: this
    // EFFECTS: checks if there is any running timer
    public boolean anyRunningTimer() {
        boolean anyTimerRunning = false;
        for (Text t : texts.getTexts()) {
            if (t.getTimerStatus()) {
                anyTimerRunning = true;
                break;
            }
        }
        return anyTimerRunning;
    }

    // MODIFIES: this
    // EFFECTS: changes the text being displayed
    public void setTextArea(String string) {
        textArea.setText(string);
    }

    public Text getSelectedText() {
        return selectedText;
    }

    public ListOfText getTexts() {
        return texts;
    }

    public void setLabelText(String string) {
        timerIcon.setText(string);
    }
}
