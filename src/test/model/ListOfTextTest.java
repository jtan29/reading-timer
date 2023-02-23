package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ListOfTextTest {
    private Text tt1;
    private Text tt2;
    private Text tt3;
    private ListOfText lot1;
    private ListOfText lot2;

    @BeforeEach
    public void setUp() {
        tt1 = new Text(100, "Test", FictionGenre.GENERAL);
        tt2 = new Text(200, "Another Test", NonFictionGenre.ARTICLE);
        tt3 = new Text(300, "Test Three", FictionGenre.GENERAL);

        lot1 = new ListOfText();
        lot2 = new ListOfText();
    }
    @Test
    public void testConstructor() {
        assertEquals(0, lot1.getNumOfTexts());
        assertEquals(0, lot2.getNumOfTexts());
    }

    @Test
    public void testAddOneText() {
        lot1.addText(tt1);
        assertEquals(1, lot1.getNumOfTexts());
        assertEquals(tt1, lot1.getTextAt(0));
    }

    @Test
    public void testAddTwoTexts() {
        lot1.addText(tt1);
        lot1.addText(tt2);
        assertEquals(2, lot1.getNumOfTexts());
        assertEquals(tt1, lot1.getTextAt(0));
        assertEquals(tt2, lot1.getTextAt(1));
    }

    @Test
    public void testRemoveOneText() {
        lot1.addText(tt2);
        lot1.addText(tt3);
        assertEquals(2, lot1.getNumOfTexts());
        lot1.removeText(tt2);
        assertEquals(1, lot1.getNumOfTexts());
        assertEquals(tt3, lot1.getTextAt(0));
    }

    @Test
    public void testRemoveTwoTextsListEmpty() {
        lot1.addText(tt2);
        lot1.addText(tt3);
        assertEquals(2, lot1.getNumOfTexts());
        lot1.removeText(tt2);
        lot1.removeText(tt3);
        assertEquals(0, lot1.getNumOfTexts());
    }

    @Test
    public void testRemoveTwoTextsOneLeft() {
        lot1.addText(tt1);
        lot1.addText(tt2);
        lot1.addText(tt3);
        assertEquals(3, lot1.getNumOfTexts());
        lot1.removeText(tt2);
        lot1.removeText(tt1);
        assertEquals(1, lot1.getNumOfTexts());
        assertEquals(tt3, lot1.getTextAt(0));
    }

    @Test
    public void testCalcTotalAverageReadingSpeedOneText() {
        tt1.addTime(1000);
        lot1.addText(tt1);
        int result = lot1.calcGenreReadingSpeed(FictionGenre.GENERAL);
        assertEquals(6, result);
    }

    @Test
    public void testCalcTotalAverageReadingSpeedTwoTextsSameGenre() {
        tt1.addTime(3600);
        tt3.addTime(60 * 30);
        lot1.addText(tt1);
        lot1.addText(tt3);
        int result = lot1.calcGenreReadingSpeed(FictionGenre.GENERAL);
        assertEquals(4, result);
    }

    @Test
    public void testCalcTotalAverageReadingSpeedThreeTextsOneDifferentGenre() {
        tt1.addTime(3600);
        tt2.addTime(60 * 20);
        tt3.addTime(60 * 40);
        lot1.addText(tt1);
        lot1.addText(tt2);
        lot1.addText(tt3);
        int result = lot1.calcGenreReadingSpeed(FictionGenre.GENERAL);
        assertEquals(4, result);
    }


}