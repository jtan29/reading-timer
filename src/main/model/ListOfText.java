package model;


import java.util.ArrayList;
import java.util.List;

public class ListOfText {
    private List<Text> texts;

    public ListOfText() {
        texts = new ArrayList<>();

    }

    // MODIFIES: this
    // EFFECTS: adds the Text to the list of texts
    public void addText(Text t) {
        texts.add(t);
    }

    // MODIFIES: this
    // EFFECTS: removes the Text from the list of texts
    public void removeText(Text t) {
        texts.remove(t);
    }

    // EFFECTS: returns average reading speed for given genre of texts in list
    public int calcGenreReadingSpeed(Genre g) {
        int totalWordCount = 0;
        int totalElapsedTime = 0;
        for (Text t: texts) {
            if (t.getGenre() == g && t.getIsComplete()) {
                totalWordCount += t.getWordCount();
                totalElapsedTime += t.getElapsedTime();
            }
        }
        if ((totalElapsedTime / Text.SECONDS_PER_MINUTE) > 0) {
            return totalWordCount / (totalElapsedTime / Text.SECONDS_PER_MINUTE);
        } else {
            return totalWordCount;
        }
    }

    // EFFECTS: returns days/hours/minutes to read a text, if given a word count and genre
    public String calcReadingTime(Genre g, int wordCount) {
        int readingSpeed = this.calcGenreReadingSpeed(g);
        if (readingSpeed == 0) {
            return "No texts with given genre.";
        }
        int readTimeMinutes = wordCount / readingSpeed;
        int readTimeDays = readTimeMinutes / (Text.SECONDS_PER_DAY / 60);
        int readTimeRemaining = readTimeMinutes - readTimeDays * (Text.SECONDS_PER_DAY / 60);
        int readTimeHours = readTimeRemaining / (Text.SECONDS_PER_HOUR / 60);
        readTimeRemaining -= (readTimeHours * (Text.SECONDS_PER_HOUR / 60));
        return "Your reading speed is: " + readTimeDays + " days, "
                + readTimeHours + " hours, " + readTimeRemaining + " minutes";
    }

    public int getNumOfTexts() {
        return texts.size();
    }

    public List<Text> getTexts() {
        return texts;
    }

    public Text getTextAt(int i) {
        return texts.get(i); // stub
    }
}
