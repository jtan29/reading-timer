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
            if (t.getGenre() == g) {
                totalWordCount += t.getWordCount();
                totalElapsedTime += t.getElapsedTime();
            }
        }
        if (totalElapsedTime > 0) {
            return totalWordCount / (totalElapsedTime / Text.SECONDS_PER_MINUTE);
        } else {
            return totalWordCount;
        }
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
