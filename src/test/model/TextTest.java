package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TextTest {
    private Text tt1;
    private Text tt2;
    @BeforeEach
    public void setUpTest() {
        tt1 = new Text(1000, "Test", FictionGenre.GRAPHIC);
        tt2 = new Text(5000, "Another Test", NonFictionGenre.MEMOIR);
    }

    @Test
    public void testConstructor() {
        assertEquals("Test", tt1.getTitle());
        assertEquals(1000, tt1.getWordCount());
        assertEquals(0, tt1.getElapsedTime());
        assertEquals(FictionGenre.GRAPHIC, tt1.getGenre());
        assertEquals("Another Test", tt2.getTitle());
        assertEquals(5000, tt2.getWordCount());
        assertEquals(0, tt2.getElapsedTime());
        assertEquals(NonFictionGenre.MEMOIR, tt2.getGenre());
    }

    @Test
    public void testEditTitle() {
        tt1.editTitle("New Title");
        assertEquals("New Title", tt1.getTitle());
        tt2.editTitle("Another New Title");
        assertEquals("Another New Title", tt2.getTitle());

    }

    @Test
    public void testEditWordCount() {
        tt1.editWordCount(2000);
        assertEquals(2000, tt1.getWordCount());
        tt2.editWordCount(7000);
        assertEquals(7000, tt2.getWordCount());
    }

    @Test
    public void testAddTime() {
        tt1.addTime(100);
        assertEquals(100, tt1.getElapsedTime());
    }

    @Test
    public void testRemoveTime() {
        tt1.addTime(1000);
        tt1.removeTime(200);
        assertEquals(800, tt1.getElapsedTime());

    }

    @Test
    public void testTimer() throws InterruptedException {
        tt1.startTimer();
        assertTrue(tt1.getTimerStatus());
        Thread.sleep(2000);
        tt1.endTimer();
        assertFalse(tt1.getTimerStatus());
        assertEquals(2, tt1.getElapsedTime());

    }

    @Test
    public void testCalcReadingSpeedLessThan1Min() {
        tt1.addTime(100);
        assertEquals(1000, tt1.calcReadingSpeed());
    }

    @Test
    public void testCalcReadingSpeedLong() {
        tt1.addTime(6000);
        assertEquals(10, tt1.calcReadingSpeed());
    }

    @Test
    public void testCalcTimeStatementSecondsOnly() {
        tt1.addTime(49);
        assertEquals( ("\n" + 0 + " " + "day(s), " + 0 + " hour(s), "
                + 0 + " minute(s) "
                + 49 + " second(s)."), tt1.calcTimeStatement());
    }

    @Test
    public void testCalcTimeStatementWithMinutes() {
        tt1.addTime(150);
        assertEquals( ("\n" + 0 + " " + "day(s), " + 0 + " hour(s), "
                + 2 + " minute(s) "
                + 30 + " second(s)."), tt1.calcTimeStatement());
    }

    @Test
    public void testCalcTimeStatementWithHours() {
        tt1.addTime(3700);
        assertEquals( ("\n" + 0 + " " + "day(s), " + 1 + " hour(s), "
                + 1 + " minute(s) "
                + 40 + " second(s)."), tt1.calcTimeStatement());
    }

    @Test
    public void testCalcTimeStatementWithDays() {
        tt1.addTime(86400 + 3600 + 120 + 3);
        assertEquals( ("\n" + 1 + " " + "day(s), " + 1 + " hour(s), "
                + 2 + " minute(s) "
                + 3 + " second(s)."), tt1.calcTimeStatement());
    }
}