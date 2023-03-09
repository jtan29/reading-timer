package persistence;

import model.FictionGenre;
import model.ListOfText;
import model.NonFictionGenre;
import model.Text;
import org.json.JSONWriter;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WriteJsonTest {

    @Test
    public void testInvalidFile() {
        WriteJson writer = new WriteJson(".data/test\0adfka");
        try {
            writer.start();
            fail("Should fail, invalid file given");
        } catch (FileNotFoundException e) {
            // fine
        }
    }

    @Test
    public void testEmptyListOfTexts() {
        ListOfText texts = new ListOfText();
        WriteJson writer = new WriteJson("./data/testEmptyListOfTexts.json");
        try {
            writer.start();
            writer.writeFile(texts);
            writer.close();
            ReadJson reader = new ReadJson("./data/testEmptyListOfTexts.json");
            texts = reader.read();
            assertEquals(0, texts.getNumOfTexts());
        } catch (IOException e) {
            fail("should pass");
        }
    }

    @Test
    public void testListOfTextsWithTexts() {
        ListOfText texts = new ListOfText();
        Text t1 = new Text(1000, "Test", FictionGenre.YA);
        Text t2 = new Text(2000, "Another Test", NonFictionGenre.MEMOIR);
        t2.setIsComplete(true);
        t2.addTime(10000);
        texts.addText(t1);
        texts.addText(t2);
        WriteJson writer = new WriteJson("./data/testListOfTextsWithTexts");
        try {
            writer.start();
            writer.writeFile(texts);
            writer.close();
            ReadJson reader = new ReadJson("./data/testListOfTextsWithTexts");
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
}
