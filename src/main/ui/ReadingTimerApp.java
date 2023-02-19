package ui;

import exceptions.InvalidSelectionException;
import exceptions.TimerAlreadyRunningException;
import exceptions.TimerNotStartedException;
import model.ListOfText;
import model.OnlineText;
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
                } catch (TimerAlreadyRunningException e) {
                    System.out.println("Timer is already running for the selected text.");
                } catch (TimerNotStartedException e) {
                    System.out.println("There is no timer running for the selected text.");
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

    // EFFECTS: displays the main menu
    private void showMainMenu() {
        showTexts();
        System.out.println("\nEnter one of the following commands:");
        System.out.println("\tq -> quit");
        System.out.println("\tadd -> add a new text");
        System.out.println("\tremove -> remove a text");
        System.out.println("\ttimer -> show timer submenu");
        System.out.println("\tshow -> show details for a text");
        System.out.println("\tedit -> edit a text's details");
    }

    // MODIFIES: this
    // EFFECTS: based on user inputs, performs various commands
    private void handleInputs(String input) throws InvalidSelectionException, TimerAlreadyRunningException,
            TimerNotStartedException {
        switch (input) {
            case ("add"): {
                createText();
                break;
            }
            case ("remove"): {
                removeTextAt();
                break;
            }
            case ("timer"): {
                timerMenu();
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

    // MODIFIES: this
    // EFFECTS: prompts user to select a text to edit
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
    // EFFECTS: changes title for selected text
    private void editTextTitle() throws InvalidSelectionException {
        showTexts();
        selectText();
        System.out.println("Enter the new title for this text:");
        System.out.println("Note: currently does not support spaces or special characters in titles.");
        String newTitle = input.next();
        selectedText.editTitle(newTitle);

    }

    // MODIFIES: this
    // EFFECTS: changes word count for selected text
    private void editTextWordCount() throws InvalidSelectionException {
        showTexts();
        selectText();
        System.out.println("Enter the new word count for this text:");
        int newWordCount = input.nextInt();
        selectedText.editWordCount(newWordCount);

    }

    // MODIFIES: this
    // EFFECTS: shows the timer submenu
    private void timerMenu() throws InvalidSelectionException, TimerAlreadyRunningException, TimerNotStartedException {
        System.out.println("start -> start the timer on a text");
        System.out.println("end -> end the timer on a text");
        String selector = input.next();
        if (selector.equals("start")) {
            startTimer();
        } else {
            if (selector.equals("end")) {
                endTimer();
            } else {
                System.out.println("Invalid entry.");
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: begins the timer for the selected text
    private void startTimer() throws InvalidSelectionException, TimerAlreadyRunningException {
        showTexts();
        selectText();
        if (selectedText.getTimerStatus()) {
            throw new TimerAlreadyRunningException();
        } else {
            selectedText.startTimer();
            System.out.println("Starting timer!");
        }

    }

    // MODIFIES: this
    // EFFECTS: ends the timer for the selected text
    private void endTimer() throws InvalidSelectionException, TimerNotStartedException {
        showTexts();
        selectText();
        if (selectedText.getTimerStatus()) {
            selectedText.endTimer();
            System.out.println("Ending timer!");
        } else {
            throw new TimerNotStartedException();
        }

    }

    // MODIFIES: this
    // EFFECTS: shows a text's details
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
