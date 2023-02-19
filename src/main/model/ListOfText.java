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
