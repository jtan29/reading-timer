package ui;

import exceptions.InvalidEntryException;
import exceptions.InvalidSelectionException;
import exceptions.TimerAlreadyRunningException;
import exceptions.TimerNotStartedException;
import model.*;

import java.util.InputMismatchException;
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
        setUp();
        while (run) {
            input = new Scanner(System.in);
            String currentInput;
            currentInput = runHelper();
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
                } catch (InvalidEntryException e) {
                    System.out.println("Invalid entry, try again.");
                }
            }
        }
    }

    // EFFECTS: helper tool for each cycle of the run method
    private String runHelper() {
        String currentInput;
        input.reset();
        input.useDelimiter("\\n");
        showMainMenu();
        currentInput = input.next();
        currentInput = currentInput.toLowerCase();
        return currentInput;
    }

    // MODIFIES: this
    // EFFECTS: sets up the application
    private void setUp() {
        System.out.println("Welcome to the Reading Time Tracker.");
        texts = new ListOfText();
        input = new Scanner(System.in);
    }

    // EFFECTS: shows the current texts
    private void showTexts() {
        if (texts.getNumOfTexts() == 0) {
            System.out.println("Currently available texts: none");
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
        System.out.println("\nEnter one of the following commands:");
        System.out.println("\tq -> quit");
        System.out.println("\tmanage -> add/remove/view statistics for all texts");
        System.out.println("\ttimer -> show timer submenu");
        System.out.println("\tshow -> show details for a text");
        System.out.println("\tedit -> edit a text's details");
    }

    // MODIFIES: this
    // EFFECTS: based on user inputs, performs various commands
    private void handleInputs(String input) throws InvalidSelectionException, TimerAlreadyRunningException,
            TimerNotStartedException, InvalidEntryException {
        switch (input) {
            case ("manage"): {
                showTextsManager();
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
            default:
                throw new InvalidEntryException();
        }
    }

    // MODIFIES: this
    // EFFECTS: shows texts management submenu
    private void showTextsManager() throws InvalidEntryException, InvalidSelectionException {
        System.out.println("add -> add a text");
        System.out.println("remove -> remove a text");
        System.out.println("stats -> show reading speed statistics for an entire genre");
        String nextInput = input.next();
        switch (nextInput) {
            case ("add"): {
                createText();
                break;
            }
            case ("remove"): {
                removeTextAt();
                break;
            }
            case ("stats"): {
                showGenreReadingSpeed();
                break;
            }
            default:
                throw new InvalidEntryException();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new text with the specified details and adds it to the list
    private void createText() throws InvalidEntryException {
        System.out.println("\nt -> create normal text");
        //    System.out.println("\not -> create new online text");
        String selector = input.next();
        if (selector.equals("t")) {
            createStandardText();
        } else {
            //    if (selector.equals("ot")) {
            //      createOnlineText();
            //  } else {
            throw new InvalidEntryException();
        }
    }


    // MODIFIES: this
    // EFFECTS: creates a new text with specified details
    private void createStandardText() throws InvalidEntryException {
        System.out.println("First, enter a title:");
        String newTitle = input.next();
        System.out.println("Next, select a genre:");
        Genre newGenre = selectGenre();
        int newWordCount;
        System.out.println("Next, enter a word count:");
        try {
            newWordCount = input.nextInt();
        } catch (InputMismatchException e) {
            throw new InvalidEntryException();
        }
        if (newWordCount < 0) {
            throw new InvalidEntryException();
        }
        Text toAdd = new Text(newWordCount, newTitle, newGenre);
        texts.addText(toAdd);
    }

    //  EFFECTS: shows submenu to select a genre to return
    private Genre selectGenre() throws InvalidEntryException {
        System.out.println("fiction -> f");
        System.out.println("non-fiction -> nf");
        int pos = 1;
        String selector = input.next();
        Genre g;
        if (selector.equals("f")) {
            for (Genre genre : FictionGenre.values()) {
                System.out.println(genre.getGenreDescription() + " [" + pos + "]");
                pos++;
            }
            g = selectFiction();
        } else {
            if (selector.equals("nf")) {
                for (Genre genre : NonFictionGenre.values()) {
                    System.out.println(genre.getGenreDescription() + " [" + pos + "]");
                    pos++;
                }
                g = selectNonFiction();
            } else {
                throw new InvalidEntryException();
            }
        }
        return g;
    }

    // EFFECTS: selector helper for non-fiction genres
    private Genre selectNonFiction() throws InvalidEntryException {
        int nextInput = input.nextInt();
        switch (nextInput) {
            case (1): {
                return NonFictionGenre.ARTICLE;
            }
            case (2): {
                return NonFictionGenre.TEXTBOOK;
            }
            case (3): {
                return NonFictionGenre.MEMOIR;
            }
            case (4): {
                return NonFictionGenre.SELF_HELP;
            }
            case (5): {
                return NonFictionGenre.OTHER;
            }
        }
        throw new InvalidEntryException();
    }

    // EFFECTS: selector helper for fiction genres
    private Genre selectFiction() throws InvalidEntryException {
        int nextInput = input.nextInt();
        switch (nextInput) {
            case (1): {
                return FictionGenre.GENERAL;
            }
            case (2): {
                return FictionGenre.YA;
            }
            case (3): {
                return FictionGenre.GRAPHIC;
            }
            case (4): {
                return FictionGenre.CHILDREN;
            }
            case (5): {
                return FictionGenre.SHORT_STORY;
            }
            case (6): {
                return FictionGenre.OTHER;
            }
        }
        throw new InvalidEntryException();
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
    private void editText() throws InvalidSelectionException, InvalidEntryException {
        System.out.println("title -> change title");
        System.out.println("word -> change word count");
        String selector = input.next();
        if (selector.equals("title")) {
            editTextTitle();
        } else {
            if (selector.equals("word")) {
                editTextWordCount();
            } else {
                throw new InvalidEntryException();
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: prompts user to select a text to edit
    private void selectText() throws InvalidSelectionException {
        showTexts();
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
        selectText();
        System.out.println("Enter the new title for this text:");
        String newTitle = input.next();
        selectedText.editTitle(newTitle);

    }

    // MODIFIES: this
    // EFFECTS: changes word count for selected text
    private void editTextWordCount() throws InvalidSelectionException, InvalidEntryException {
        selectText();
        System.out.println("Enter the new word count for this text:");
        int newWordCount = input.nextInt();
        if (newWordCount < 0) {
            throw new InvalidEntryException();
        }
        selectedText.editWordCount(newWordCount);

    }

    // MODIFIES: this
    // EFFECTS: shows the timer submenu
    private void timerMenu() throws InvalidSelectionException, InvalidEntryException,
            TimerAlreadyRunningException, TimerNotStartedException {
        System.out.println("start -> start the timer on a text");
        System.out.println("end -> end the timer on a text");
        String selector = input.next();
        if (selector.equals("start")) {
            startTimer();
        } else {
            if (selector.equals("end")) {
                endTimer();
            } else {
                throw new InvalidEntryException();
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: begins the timer for the selected text
    private void startTimer() throws InvalidSelectionException, TimerAlreadyRunningException {
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
        selectText();
        System.out.println("Title: " + selectedText.getTitle());
        System.out.println("Word count: " + selectedText.getWordCount());
        System.out.println("Elapsed time: " + selectedText.calcTimeStatement());
        System.out.println("Genre: " + selectedText.getGenre().getGenreDescription());
        System.out.println("Your average reading speed was: " + selectedText.calcReadingSpeed() + " words/minute.");
        System.out.println("Note: assumes entire text has been read.");
    }

    // EFFECTS: calculates the average reading speed for a genre
    private void showGenreReadingSpeed() throws InvalidEntryException {
        System.out.println("Select a genre: ");
        Genre g = selectGenre();
        int readingSpeed = texts.calcGenreReadingSpeed(g);
        System.out.println("The average reading speed for genre " + "[" + g.getGenreDescription() + "]"
                + " was " + readingSpeed + " words/min");
    }

}