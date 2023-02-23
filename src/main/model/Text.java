package model;

import java.time.Duration;
import java.time.Instant;

public class Text {

    public static final int SECONDS_PER_MINUTE = 60;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_DAY = 86400;
    private Genre genre;
    private boolean isTimerRunning;
    private int wordCount;
    private long elapsedTime;
    private String title;
    private Instant start;
    private Instant end;


    // REQUIRES: wordCount > 0, name and genre are not empty strings
    // EFFECTS: sets the text's word count to wordCount, the text's name is
    //          set to the given name, and the text's genre is set to the given genre

    public Text(int wordCount, String title, Genre genre) {
        this.wordCount = wordCount;
        this.title = title;
        this.genre = genre;
        elapsedTime = 0;
    }


    // EFFECTS: begins the timer
    public void startTimer() {
        start = Instant.now();
        isTimerRunning = true;
    }

    // REQUIRES: timer is running
    // MODIFIES: this
    // EFFECTS: stops the timer and adds elapsed time to total (in seconds)
    public void endTimer() {
        isTimerRunning = false;
        end = Instant.now();
        Duration duration = Duration.between(start, end);
        elapsedTime += duration.toSeconds();
    }

    // REQUIRES: given time interval is not negative
    // MODIFIES: this
    // EFFECTS: increases the elapsed time by given time (seconds)
    public void addTime(long time) {
        elapsedTime += time;

    }

    // REQUIRES: given time interval is not negative, elapsedTime - time >= 0
    // MODIFIES: this
    // EFFECTS: decreases the elapsed time by given time (seconds)

    public void removeTime(long time) {
        elapsedTime = elapsedTime - time;
        assert time >= 0;

    }

    // REQUIRES: newTitle is not empty string
    // MODIFIES: this
    // EFFECTS: changes the text's title
    public void editTitle(String newTitle) {
        this.title = newTitle;

    }

    // REQUIRES: newWordCount > 0
    // MODIFIES: this
    // EFFECTS: changes the text's word count
    public void editWordCount(int newWordCount) {
        this.wordCount = newWordCount;
    }

    // EFFECTS: calculates a day/hours/minutes/seconds statement for the elapsed time
    public String calcTimeStatement() {
        long elapsedDays = elapsedTime / SECONDS_PER_DAY;
        long remainingTime = elapsedTime - (elapsedDays * SECONDS_PER_DAY);
        long elapsedHours = remainingTime / SECONDS_PER_HOUR;
        remainingTime = remainingTime - (elapsedHours * SECONDS_PER_HOUR);
        long elapsedMinutes = remainingTime / SECONDS_PER_MINUTE;
        remainingTime = remainingTime - (elapsedMinutes * SECONDS_PER_MINUTE);
        String toPrint = "\n" + elapsedDays + " " + "day(s), " + elapsedHours + " hour(s), "
                + elapsedMinutes + " minute(s) "
                + remainingTime + " second(s).";
        return toPrint;
    }

    // EFFECTS: calculates the average reading speed
    public long calcReadingSpeed() {
        long readingSpeed;
        if ((elapsedTime / SECONDS_PER_MINUTE) == 0) {
            readingSpeed = wordCount;
        } else {
            readingSpeed = wordCount / (elapsedTime / SECONDS_PER_MINUTE);
        }
        return readingSpeed;
    }


    public boolean getTimerStatus() {
        return this.isTimerRunning;
    }

    public int getWordCount() {
        return this.wordCount;
    }

    public String getTitle() {
        return this.title;
    }

    public long getElapsedTime() {
        return this.elapsedTime;
    }

    public Genre getGenre() {
        return this.genre;
    }

}