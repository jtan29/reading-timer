package ui;

import exceptions.InvalidSelectionException;
import model.OnlineText;
import model.ListOfText;
import model.Text;

import java.util.Scanner;

public class ReadingTimerApp {
    private ListOfText texts;
    private Scanner input;
    private Text selectedText;

    // EFFECTS: constructs the reading timer application
    public ReadingTimerApp() {
        run();
    }

    // EFFECTS: keeps the program running until the user quits
    private void run() {
        boolean run = true;
        String currentInput = null;
        setUp();
        while (run) {
            showMainMenu();
            input.useDelimiter("\\n");
            currentInput = input.next();
            currentInput = currentInput.toLowerCase();
            if (currentInput.equals("q")) {
                run = false;
            } else {
                try {
                    handleInputs(currentInput);
                } catch (InvalidSelectionException e) {
                    System.out.println("Invalid selection.");
                }
            }

        }
    }

    // MODIFIES: this
    // EFFECTS: sets up the application
    private void setUp() {
        texts = new ListOfText();
        input = new Scanner(System.in);
    }

    // EFFECTS: shows the current texts
    private void showTexts() {
        if (texts.getNumOfTexts() == 0) {
            System.out.println("No texts yet!");
        } else {
            System.out.println("Currently available texts:");
            int position = 1;
            for (Text t : texts.getTexts()) {
                System.out.println(t.getTitle() + " " + "[" + position + "]");
                position++;
            }

        }
    }

    private void showMainMenu() {
        showTexts();
        System.out.println("\nEnter one of the following commands:");
        System.out.println("\tq -> quit");
        System.out.println("\tadd -> add a new text");
        System.out.println("\tremove -> remove a text");
        System.out.println("\tstart -> start the timer for a text");
        System.out.println("\tend -> end the timer for a text");
        System.out.println("\tshow -> show details for a text");
        System.out.println("\tedit -> edit a text's details");
    }

    // MODIFIES: this
    // EFFECTS: based on user inputs, performs various commands
    private void handleInputs(String input) throws InvalidSelectionException {
        switch (input) {
            case ("add"): {
                createText();
                break;
            }
            case ("remove"): {
                removeTextAt();
                break;
            }
            case ("start"): {
                startTimer();
                break;
            }
            case ("end"): {
                endTimer();
                break;
            }
            case ("show"): {
                showDetails();
                break;
            }
            case ("edit"): {
                editText();
                break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new text with the specified details and adds it to the list
    private void createText() {
        System.out.println("\nt -> create normal text");
        System.out.println("\not -> create new online text");
        String selector = input.next();
        if (selector.equals("t")) {
            createStandardText();
        } else {
            if (selector.equals("ot")) {
                createOnlineText();
            } else {
                System.out.println("Invalid entry.");
            }
        }


    }

    // MODIFIES: this
    // EFFECTS: creates a new text with specified details
    private void createStandardText() {
        System.out.println("First, enter a title:");
        String newTitle = input.next();
        System.out.println("Next, enter a word count:");
        int newWordCount = input.nextInt();
        Text toAdd = new Text(newWordCount, newTitle);
        texts.addText(toAdd);
    }

    // MODIFIES: this
    // EFFECTS: creates a new online text with specified details and adds it to the list
    private void createOnlineText() {
        System.out.println("First, enter a title:");
        String newTitle = input.next();
        System.out.println("Next, enter a word count:");
        int newWordCount = input.nextInt();
        System.out.println("Finally, enter a link to the text:");
        String newLink = input.next();
        OnlineText toAdd = new OnlineText(newWordCount, newTitle, newLink);
        texts.addText(toAdd);
    }

    // MODIFIES: this
    // EFFECTS: removes the text at specified position (index begins with 1)
    private void removeTextAt() throws InvalidSelectionException {
        selectText();
        texts.removeText(selectedText);
        System.out.println("Removed text with title: " + selectedText.getTitle());
    }

    // MODIFIES: this
    // EFFECTS: opens menu to edit texts

    private void editText() throws InvalidSelectionException {
        System.out.println("title -> change title");
        System.out.println("word -> change word count");
        String selector = input.next();
        if (selector.equals("title")) {
            editTextTitle();
        } else {
            if (selector.equals("word")) {
                editTextWordCount();
            } else {
                System.out.println("Invalid entry.");
            }
        }

    }

    private void selectText() throws InvalidSelectionException {
        System.out.println("Enter the number of the text you would like to select:");
        int selector = input.nextInt();
        if (selector < 1 || selector > texts.getNumOfTexts()) {
            throw new InvalidSelectionException();
        } else {
            selectedText = texts.getTextAt(selector - 1);
        }
    }

    // MODIFIES: this
    // EFFECTS: shows the list of all texts in the list and a number, user can enter a number and then the desired
    //          new title to change the title of that text
    private void editTextTitle() throws InvalidSelectionException {
        showTexts();
        selectText();
        System.out.println("Enter the new title for this text:");
        System.out.println("Note: currently does not support spaces or special characters in titles.");
        String newTitle = input.next();
        selectedText.editTitle(newTitle);

    }

    // MODIFIES: this
    // EFFECTS: shows the list of all texts in the list and a number, user can enter a number and then the desired
    //          new word count to change the word count of that text
    private void editTextWordCount() throws InvalidSelectionException {
        showTexts();
        selectText();
        System.out.println("Enter the new word count for this text:");
        int newWordCount = input.nextInt();
        selectedText.editWordCount(newWordCount);

    }

    // MODIFIES: this
    // EFFECTS: begins the timer for a specified text using its number
    private void startTimer() throws InvalidSelectionException {
        showTexts();
        selectText();
        selectedText.startTimer();
        System.out.println("Starting timer!");

    }

    // MODIFIES: this
    // EFFECTS: ends the timer for specified text using its number
    private void endTimer() throws InvalidSelectionException {
        showTexts();
        selectText();
        selectedText.endTimer();
        System.out.println("Ending timer!");

    }

    // MODIFIES: this
    // EFFECTS: shows a texts details
    private void showDetails() throws InvalidSelectionException {
        showTexts();
        selectText();
        System.out.println("Title: " + selectedText.getTitle());
        System.out.println("Word count: " + selectedText.getWordCount());
        System.out.println("Elapsed time: " + selectedText.calcTimeStatement());
        System.out.println("Your average reading speed was: " + selectedText.calcReadingSpeed() + " words/minute.");
        System.out.println("Note: assumes entire text has been read.");
    }

}
