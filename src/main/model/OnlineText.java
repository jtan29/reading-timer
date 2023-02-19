package model;

public class OnlineText extends Text {
    private String link;

    public OnlineText(int wordCount, String title, String link) {
        super(wordCount, title);
        this.link = link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return this.link; // stub
    }

}
