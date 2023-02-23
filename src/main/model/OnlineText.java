package model;

public class OnlineText extends Text {
    private String link;

    public OnlineText(int wordCount, String title, Genre g, String link) {
        super(wordCount, title, g);
        this.link = link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return this.link; // stub
    }

}
