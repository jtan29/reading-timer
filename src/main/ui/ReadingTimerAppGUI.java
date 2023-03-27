package ui;

import model.*;
import ui.buttons.AddTextTitleButton;
import ui.buttons.DataLoader;
import ui.buttons.TextButton;
import ui.menu.AddTextMenuItem;
import ui.menu.EndTimer;
import ui.menu.RemoveTextMenuItem;
import ui.menu.StartTimer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ReadingTimerAppGUI extends JFrame {
    private ListOfText texts;
    private List<JButton> buttonList;
    private Text selectedText;
    private JButton selectedButton;
    private JTextArea textArea;
    private JPanel textPanel;
    private JPanel displayPanel;

    public ReadingTimerAppGUI() {
        run();
    }

    private void run() {
        setUp();
    }

    private void setUp() {
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

    private void setUpGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(1000, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        loadData();
    }

    private void setUpTextBox() {
        displayPanel = new JPanel();
     //   displayPanel.setBackground(Color.red);
        displayPanel.setBounds(200, 200, 200, 200);
        add(displayPanel, BorderLayout.CENTER);
        textArea = new JTextArea(10, 30);
        displayPanel.add(textArea);
        textArea.setEditable(false);
        textArea.setText("No text selected");
        displayPanel.setVisible(true);
    }

    private void setUpMenu() {
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu menuManage = new JMenu("Manage Texts");
        JMenu menuTimer = new JMenu("Manage Timer");
        RemoveTextMenuItem removeTextMenuItem = new RemoveTextMenuItem(this, menuManage);
        AddTextMenuItem addTextMenuItem = new AddTextMenuItem(this, menuManage);
        StartTimer startTimer = new StartTimer(this, menuTimer);
        EndTimer endTimer = new EndTimer(this, menuTimer);
        menuBar.add(menuManage);
        menuBar.add(menuTimer);
    }

    private void loadData() {
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new GridLayout(2, 1));
        dataPanel.setSize(new Dimension(0, 0));
     //   dataPanel.setBounds(0, 0, 300, 200);
        add(dataPanel, BorderLayout.NORTH);
        DataLoader dataLoader = new DataLoader(this, dataPanel);
    }

    public void initializeTexts(ListOfText texts) {
        this.texts = texts;
        textPanel.setLayout(new GridLayout(4, 2));
        textPanel.setSize(new Dimension(0, 0));
    //    textPanel.setBackground(Color.blue);
        add(textPanel, BorderLayout.SOUTH);
        for (Text t : texts.getTexts()) {
            TextButton textButton = new TextButton(t, this, textPanel);
            buttonList.add(textButton.getButton());
        }
    }

    public void startTimer() {
        selectedText.startTimer();
    }

    public void endTimer() {
        selectedText.endTimer();
    }

    public void removeText() {
        if (selectedText != null) {
            texts.removeText(selectedText);
            selectedText = null;
            textPanel.remove(selectedButton);
            buttonList.remove(selectedButton);
            selectedButton = null;
        }
    }

    public void addTextShowOptions() {
        Text newText = new Text(0,"Placeholder", NonFictionGenre.NF_OTHER);
        JPanel addPanel = new JPanel();
    //    addPanel.setBackground(Color.green);
        addPanel.setBounds(200, 100, 200, 400);
        addPanel.setLayout(new FlowLayout());
        add(addPanel, BorderLayout.NORTH);
        AddTextTitleButton addTextTitleButton = new AddTextTitleButton(newText, this, addPanel);
    }

    public void addText(Text text) {
        texts.addText(text);
        TextButton textButton = new TextButton(text, this, textPanel);
        buttonList.add(textButton.getButton());
    }

    public void setSelectedText(Text t) {
        this.selectedText = t;
        int index = texts.getTexts().indexOf(selectedText);
        selectedButton = buttonList.get(index);
        textArea.setText("Selected: " + t.getTitle() + "\n" + "Elapsed Time: " + t.calcTimeStatement() + "\n"
                + "Genre: " + t.getGenre().getGenreDescription() + "\n"
                + "Words: " + t.getWordCount() + "\n"
                + "Complete: " + t.getIsComplete());

    }

    public void setTextArea(String string) {
        textArea.setText(string);
    }

    public Text getSelectedText() {
        return selectedText;
    }
}
