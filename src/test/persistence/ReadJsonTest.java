package persistence;

import model.FictionGenre;
import model.ListOfText;
import model.NonFictionGenre;
import model.Text;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ReadJsonTest {

    @Test
    public void testReaderWithInvalidFile() {
        ReadJson reader = new ReadJson("./data/invalid");
        try {
            ListOfText texts = reader.read();
            fail("should throw an exception");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    public void testEmptyListOfTexts() {
        ListOfText texts;
        ReadJson reader = new ReadJson("./data/testEmptyListOfTexts.json");
        try {
            texts = reader.read();
            assertEquals(0, texts.getNumOfTexts());
        } catch (IOException e) {
            fail("should pass");
        }
    }

    @Test
    public void testListOfTextsWithTexts() {
        ListOfText texts;
        ReadJson reader = new ReadJson("./data/testListOfTextsWithTexts");
        try {
            texts = reader.read();
            assertEquals(2, texts.getNumOfTexts());
            Text tt1 = texts.getTextAt(0);
            Text tt2 = texts.getTextAt(1);
            assertEquals("Test", tt1.getTitle());
            assertEquals("Another Test", tt2.getTitle());
            assertEquals(1000, tt1.getWordCount());
            assertEquals(2000, tt2.getWordCount());
            assertEquals(FictionGenre.YA, tt1.getGenre());
            assertEquals(NonFictionGenre.MEMOIR, tt2.getGenre());
            assertFalse(tt1.getIsComplete());
            assertTrue(tt2.getIsComplete());
            assertEquals(0, tt1.getElapsedTime());
            assertEquals(10000, tt2.getElapsedTime());
        } catch (IOException e) {
            fail("should pass");
        }
    }

    @Test
    public void testListOfTextsWithTextsOtherGenres() {
        ListOfText texts;
        try {
            ReadJson reader = new ReadJson("./data/testListOfTextsWithTextsOtherGenres");
            texts = reader.read();
            Text tt1 = texts.getTextAt(0);
            Text tt2 = texts.getTextAt(1);
            Text tt3 = texts.getTextAt(2);
            Text tt4 = texts.getTextAt(3);
            Text tt5 = texts.getTextAt(4);
            Text tt6 = texts.getTextAt(5);
            Text tt7 = texts.getTextAt(6);
            Text tt8 = texts.getTextAt(7);
            assertEquals(FictionGenre.GENERAL, tt1.getGenre());
            assertEquals(FictionGenre.CHILDREN, tt2.getGenre());
            assertEquals(FictionGenre.GRAPHIC, tt3.getGenre());
            assertEquals(FictionGenre.OTHER, tt4.getGenre());
            assertEquals(NonFictionGenre.ARTICLE, tt5.getGenre());
            assertEquals(NonFictionGenre.SELF_HELP, tt6.getGenre());
            assertEquals(NonFictionGenre.TEXTBOOK, tt7.getGenre());
            assertEquals(NonFictionGenre.NF_OTHER, tt8.getGenre());
        } catch (IOException e) {
            fail("should not throw exceptions");
        }
    }


}
