package github.magyarzoli;

import java.io.File;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import github.magyarzoli.CreateFile.CreateCommand;

public class FileHandlerTest {

    private String fileName;
    private File file;
    private FileHandler handler;

    @Test
    void testFileHandlerConstructor() {
        fileName = null;
        Assertions.assertThrows(NullPointerException.class, () -> handler = new FileHandler(fileName));
        Assertions.assertThrows(IllegalArgumentException.class, () -> handler = new FileHandler(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> handler = new FileHandler(" "));
        Assertions.assertThrows(NullPointerException.class, () -> file = new File(fileName));
        Assertions.assertThrows(NullPointerException.class, () -> handler = new FileHandler(file));
        Assertions.assertDoesNotThrow(() -> file = new File(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> handler = new FileHandler(""));
        Assertions.assertDoesNotThrow(() -> file = new File(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> handler = new FileHandler(" "));
        fileName = "test1.txt";
        Assertions.assertDoesNotThrow(() -> handler = new FileHandler(fileName));
        Assertions.assertEquals(fileName, handler.getFileName());
        file = new File(fileName);
        Assertions.assertDoesNotThrow(() -> handler = new FileHandler(file));
        Assertions.assertEquals(file, handler.getFile());
    }
    
    @Test
    void testCreatesTheFile() {
        fileName = "test1.txt";
        file = new File(fileName);
        handler = new FileHandler(fileName);
        if (!file.exists()) {
            handler.create(CreateCommand.CREATES_THE_FILE);
            Assertions.assertTrue(handler.getFile().exists());
            Assertions.assertEquals(fileName, handler.getFileName());
            Assertions.assertEquals(file, handler.getFile());
        } else {
            handler.create(CreateCommand.CREATES_THE_FILE);
            Assertions.assertTrue(handler.getFile().exists());
            Assertions.assertNotEquals(fileName, handler.getFileName());
            Assertions.assertNotEquals(file, handler.getFile());
        }
    }

    @Test
    void testCreatesAgainTheFile() {
        fileName ="test2.txt";
        file = new File(fileName);
        handler = new FileHandler(fileName);
        if (!file.exists()) {
            handler.create(CreateCommand.CREATES_AGAIN_THE_FILE);
            Assertions.assertTrue(handler.getFile().exists());
            Assertions.assertEquals(fileName, handler.getFileName());
            Assertions.assertEquals(file, handler.getFile());
        } else {
            handler.create(CreateCommand.CREATES_AGAIN_THE_FILE);
            Assertions.assertTrue(handler.getFile().exists());
            Assertions.assertEquals(fileName, handler.getFileName());
            Assertions.assertEquals(file, handler.getFile());
        }
    }

    @Test
    void testCreatesDoesNotIfIrAlreadyExists() {
        fileName ="test3.txt";
        file = new File(fileName);
        handler = new FileHandler(fileName);
        if (!file.exists()) {
            handler.create(CreateCommand.CREATES_DOES_NOT_IF_IT_ALREADY_EXISTS);
            Assertions.assertTrue(handler.getFile().exists());
            Assertions.assertEquals(fileName, handler.getFileName());
            Assertions.assertEquals(file, handler.getFile());
        } else {
            handler.create(CreateCommand.CREATES_DOES_NOT_IF_IT_ALREADY_EXISTS);
            Assertions.assertTrue(handler.getFile().exists());
            Assertions.assertEquals(fileName, handler.getFileName());
            Assertions.assertEquals(file, handler.getFile());
        }
    }
}