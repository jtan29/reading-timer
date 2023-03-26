package ui;

import model.ListOfText;
import model.Text;
import persistence.ReadJson;
import persistence.WriteJson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ReadingTimerAppGUI extends JFrame {
    private ListOfText texts;
    private Text selectedText;
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
        displayPanel.setBackground(Color.red);
        displayPanel.setBounds(200, 200, 200, 200);
        textArea = new JTextArea(5, 20);
        textArea.setText("Test");
        displayPanel.setVisible(true);
        displayPanel.add(textArea);
        add(displayPanel, BorderLayout.CENTER);
        textArea.setEditable(false);
    }

    private void setUpMenu() {
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu menuManage = new JMenu("Manage Texts");
        JMenu menuTimer = new JMenu("Manage Timer");
        RemoveTextMenuItem removeTextMenuItem = new RemoveTextMenuItem(this, menuManage);
        StartTimer startTimer = new StartTimer(this, menuTimer);
        EndTimer endTimer = new EndTimer(this, menuTimer);
        menuBar.add(menuManage);
        menuBar.add(menuTimer);
    }

    private void loadData() {
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new GridLayout(0,1));
        dataPanel.setSize(new Dimension(0, 0));
        dataPanel.setBounds(0, 0, 300, 200);
        add(dataPanel, BorderLayout.CENTER);
        DataLoader dataLoader = new DataLoader(this, dataPanel);
    }

    public void addTexts(ListOfText texts) {
        textPanel.setLayout(new GridLayout(4,2));
        textPanel.setSize(new Dimension(0, 0));
        textPanel.setBackground(Color.blue);
        add(textPanel, BorderLayout.SOUTH);
        for (Text t : texts.getTexts()) {
            TextButton textButton = new TextButton(t, this, textPanel);
        }
    }

    public void startTimer() {
        selectedText.startTimer();
    }

    public void endTimer() {
        selectedText.endTimer();
    }

    public void removeText() {
        texts.removeText(selectedText);
        textPanel.removeAll();
        addTexts(texts);
    }

    public void setSelectedText(Text t) {
        this.selectedText = t;
        textArea.setText("Selected: " + t.getTitle());
    }

    public void setTextArea(String string) {
        textArea.setText(string);
    }
}
