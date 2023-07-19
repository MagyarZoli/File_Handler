package github.magyarzoli;

import java.io.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import github.magyarzoli.CreateFile.CreateCommand;
import github.magyarzoli.DeleteFile.DeleteCommand;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    private String fileName, expectedFileName;
    private File file, expectedFile;
    private Character split = ';';
    private FileHandler handler, expectedHandler;

    @Test
    @DisplayName(value = "FileHandler constructor checking.")
    void testFileHandlerConstructor() {
        fileName = null;
        assertThrows(NullPointerException.class, () -> handler = new FileHandler(fileName));
        assertThrows(IllegalArgumentException.class, () -> handler = new FileHandler(""));
        assertThrows(IllegalArgumentException.class, () -> handler = new FileHandler(" "));
        assertThrows(NullPointerException.class, () -> file = new File(fileName));
        assertThrows(NullPointerException.class, () -> handler = new FileHandler(file));
        assertDoesNotThrow(() -> file = new File(""));
        assertThrows(IllegalArgumentException.class, () -> handler = new FileHandler(""));
        assertDoesNotThrow(() -> file = new File(""));
        assertThrows(IllegalArgumentException.class, () -> handler = new FileHandler(" "));
        fileName = "test1.txt";
        assertDoesNotThrow(() -> handler = new FileHandler(fileName));
        assertEquals(fileName, handler.getFileName());
        file = new File(fileName);
        assertDoesNotThrow(() -> handler = new FileHandler(file));
        assertEquals(file, handler.getFile());
    }

    @Test
    @DisplayName(value = "Creates the file.")
    void testCreatesTheFile() {
        fileName = "test1.txt";
        file = new File(fileName);
        handler = new FileHandler(fileName);
        if (!file.exists()) {
            handler.create(CreateCommand.CREATES_THE_FILE);
            assertTrue(handler.getFile().exists());
            assertEquals(fileName, handler.getFileName());
            assertEquals(file, handler.getFile());
        } else {
            handler.create(CreateCommand.CREATES_THE_FILE);
            assertTrue(handler.getFile().exists());
            assertNotEquals(fileName, handler.getFileName());
            assertNotEquals(file, handler.getFile());
        }
    }

    @Test
    @DisplayName(value = "Creates again the file.")
    void testCreatesAgainTheFile() {
        fileName ="test2.txt";
        file = new File(fileName);
        handler = new FileHandler(fileName);
        if (!file.exists()) {
            handler.create(CreateCommand.CREATES_AGAIN_THE_FILE);
            assertTrue(handler.getFile().exists());
            assertEquals(fileName, handler.getFileName());
            assertEquals(file, handler.getFile());
        } else {
            handler.create(CreateCommand.CREATES_AGAIN_THE_FILE);
            assertTrue(handler.getFile().exists());
            assertEquals(fileName, handler.getFileName());
            assertEquals(file, handler.getFile());
        }
    }

    @Test
    @DisplayName(value = "Creates does not if it already exists.")
    void testCreatesDoesNotIfIrAlreadyExists() {
        fileName ="test3.txt";
        file = new File(fileName);
        handler = new FileHandler(fileName);
        if (!file.exists()) {
            handler.create(CreateCommand.CREATES_DOES_NOT_IF_IT_ALREADY_EXISTS);
            assertTrue(handler.getFile().exists());
            assertEquals(fileName, handler.getFileName());
            assertEquals(file, handler.getFile());
        } else {
            handler.create(CreateCommand.CREATES_DOES_NOT_IF_IT_ALREADY_EXISTS);
            assertTrue(handler.getFile().exists());
            assertEquals(fileName, handler.getFileName());
            assertEquals(file, handler.getFile());
        }
    }

    @Test
    @DisplayName(value = "Creates again and Delete the contents of the file.")
    void testCreatesAgainDeleteTheContentsOfTheFile() {
        fileName ="test4.txt";
        file = new File(fileName);
        handler = new FileHandler(fileName);
        handler.create(CreateCommand.CREATES_AGAIN_THE_FILE);
        handler.delet(DeleteCommand.DELETE_THE_CONTENTS_OF_THE_FILE);
        assertTrue(handler.getFile().exists());
        assertEquals(fileName, handler.getFileName());
        assertEquals(file, handler.getFile());
    }

    @Test
    @DisplayName(value = "Creates and Delete the contents of the file.")
    void testCreatesDeleteTheContentsOfTheFile() {
        fileName ="test4.txt";
        expectedFileName = "test4(1).txt";
        file = new File(fileName);
        expectedFile = new File(expectedFileName);
        handler = new FileHandler(fileName);
        expectedHandler = new FileHandler(expectedFileName);
        if (expectedFile.exists()) {
            expectedHandler.delet(DeleteCommand.DELETE_THE_FILE);
        }
        handler.create(CreateCommand.CREATES_THE_FILE);
        if (file.exists()) {
            handler.delet(DeleteCommand.DELETE_THE_CONTENTS_OF_THE_FILE);
            assertTrue(handler.getFile().exists());
            assertNotEquals(fileName, handler.getFileName());
            assertEquals(expectedFileName, handler.getFileName());
            assertNotEquals(file, handler.getFile());
            assertEquals(expectedFile, handler.getFile());
        }
    }

    @Test
    @DisplayName(value = "Creates again and Delete the file.")
    void testCreatesAgainDeleteTheFile() {
        fileName ="test5.txt";
        file = new File(fileName);
        handler = new FileHandler(fileName);
        handler.create(CreateCommand.CREATES_AGAIN_THE_FILE);
        handler.delet(DeleteCommand.DELETE_THE_FILE);
        assertFalse(handler.getFile().exists());
        assertEquals(fileName, handler.getFileName());
        assertEquals(file, handler.getFile());
    }

    @Test
    @DisplayName(value = "Creates and Delete the contents of the file.")
    void testCreatesDeleteTheFile() {
        fileName ="test4.txt";
        expectedFileName = "test4(1).txt";
        file = new File(fileName);
        expectedFile = new File(expectedFileName);
        handler = new FileHandler(fileName);
        expectedHandler = new FileHandler(expectedFileName);
        if (expectedFile.exists()) {
            expectedHandler.delet(DeleteCommand.DELETE_THE_FILE);
        }
        handler.create(CreateCommand.CREATES_THE_FILE);
        if (file.exists()) {
            handler.delet(DeleteCommand.DELETE_THE_FILE);
            assertFalse(handler.getFile().exists());
            assertNotEquals(fileName, handler.getFileName());
            assertEquals(expectedFileName, handler.getFileName());
            assertNotEquals(file, handler.getFile());
            assertEquals(expectedFile, handler.getFile());
        }
    }
}