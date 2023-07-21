package github.magyarzoli;

import java.io.*;
import java.util.*;

import org.junit.jupiter.api.*;
import github.magyarzoli.CreateFile.CreateCommand;
import github.magyarzoli.DeleteFile.DeleteCommand;
import github.magyarzoli.UpdateFile.UpdateCommand;

import static org.junit.jupiter.api.Assertions.*;

/**
 * FileHandler class testing with JUnit5.
 * @since       1.0
 * @author      <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public class FileHandlerTest {

    private static String fileName, expectedFileName;
    private static File file, expectedFile;
    private static FileHandler handler, expectedHandler;

    @BeforeAll
    static void beforeAll() {
        fileName = "read.txt";
        file = new File(fileName);
        handler = new FileHandler(fileName);
        if (!file.exists()) {
            assertDoesNotThrow(() -> handler.create(CreateCommand.CREATES_THE_FILE));
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write("test,\ntest2, test3,");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @BeforeEach
    void setUp() {
        fileName = "test.txt";
        assertDoesNotThrow(() -> file = new File(fileName));
        assertDoesNotThrow(() -> handler = new FileHandler(fileName));
    }

    @Test
    @DisplayName("FileHandler constructor checking.")
    void testFileHandlerConstructor() {
        fileName = null;
        file = null;
        handler = null;
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
    @DisplayName("Creates the file.")
    void testCreatesTheFile() {
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
    @DisplayName("Creates again the file.")
    void testCreatesAgainTheFile() {
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
    @DisplayName("Creates does not if it already exists.")
    void testCreatesDoesNotIfIrAlreadyExists() {
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
    @DisplayName("Read it and store it in a String array.")
    void testReadToArray() {
        String[] read;
        fileName = "invalid.txt";
        file = new File(fileName);
        handler = new FileHandler(fileName);
        assertThrows(RuntimeException.class, () -> handler.read());
        assertNull(read = handler.getReadArray());
        fileName = "read.txt";
        file = new File(fileName);
        handler = new FileHandler(fileName);
        assertDoesNotThrow(() -> handler.read());
        assertNotNull(read = handler.getReadArray());
        assertTrue(read.length > 0);
        System.out.println(Arrays.toString(read));
    }

    @Test
    @DisplayName("Read it and store it in a String Collection.")
    void testReadToCollection() {
        Collection<String> read;
        fileName = "invalid.txt";
        file = new File(fileName);
        handler = new FileHandler(fileName);
        assertThrows(RuntimeException.class, () -> handler.read());
        assertNull(read = handler.getReadCollection());
        fileName = "read.txt";
        file = new File(fileName);
        handler = new FileHandler(fileName);
        assertDoesNotThrow(() -> handler.read());
        assertNotNull(read = handler.getReadCollection());
        assertFalse(read.isEmpty());
        read.forEach((element) -> System.out.print(element + ", "));
    }

    @Test
    @DisplayName("Update file to starting contents, with all types.")
    void testUpdateWithStarting() {
        String[] update = {"update", "update2"};
        fileName = "read.txt";
        file = new File(fileName);
        handler = new FileHandler(fileName);
        assertDoesNotThrow(() -> handler.read());
        String[] unexpectedRead = handler.getReadArray();
        assertDoesNotThrow(() -> handler.update(update, UpdateCommand.UPDATE_FILE_TO_STARTING_CONTENTS));
        assertDoesNotThrow(() -> handler.read());
        assertNotEquals(unexpectedRead, handler.getReadArray());
        List<Integer> updateList = List.of(Integer.MAX_VALUE, Integer.MIN_VALUE);
        List<String> unexpectedReadList = (List<String>) handler.getReadCollection();
        assertDoesNotThrow(() -> handler.update(updateList, UpdateCommand.UPDATE_FILE_TO_STARTING_CONTENTS));
        assertDoesNotThrow(() -> handler.read());
        assertNotEquals(unexpectedReadList, handler.getReadCollection());
        Map<Integer, String> updateMap =  Map.of(3, "update3", 4, "update4");
        unexpectedReadList = (List<String>) handler.getReadCollection();
        assertDoesNotThrow(() -> handler.update(updateMap, UpdateCommand.UPDATE_FILE_TO_STARTING_CONTENTS));
        assertDoesNotThrow(() -> handler.read());
        assertNotEquals(unexpectedReadList, handler.getReadCollection());
    }

    @Test
    @DisplayName("Update file to ending contents, with all types.")
    void testUpdateWithEnding() {
        String[] update = {"update", "update2"};
        fileName = "read.txt";
        file = new File(fileName);
        handler = new FileHandler(fileName);
        assertDoesNotThrow(() -> handler.read());
        String[] unexpectedRead = handler.getReadArray();
        assertDoesNotThrow(() -> handler.update(update, UpdateCommand.UPDATE_FILE_TO_ENDING_CONTENTS));
        assertDoesNotThrow(() -> handler.read());
        assertNotEquals(unexpectedRead, handler.getReadArray());
        List<Integer> updateList = List.of(Integer.MAX_VALUE, Integer.MIN_VALUE);
        List<String> unexpectedReadList = (List<String>) handler.getReadCollection();
        assertDoesNotThrow(() -> handler.update(updateList, UpdateCommand.UPDATE_FILE_TO_ENDING_CONTENTS));
        assertDoesNotThrow(() -> handler.read());
        assertNotEquals(unexpectedReadList, handler.getReadCollection());
        Map<Integer, String> updateMap =  Map.of(3, "update3", 4, "update4");
        unexpectedReadList = (List<String>) handler.getReadCollection();
        assertDoesNotThrow(() -> handler.update(updateMap, UpdateCommand.UPDATE_FILE_TO_ENDING_CONTENTS));
        assertDoesNotThrow(() -> handler.read());
        assertNotEquals(unexpectedReadList, handler.getReadCollection());
    }

    @Test
    @DisplayName("Update file with contents overwriting, with all types.")
    void testUpdateWithContentsOverwriting() {
        String[] update = {"update", "update2"};
        fileName = "read.txt";
        file = new File(fileName);
        handler = new FileHandler(fileName);
        handler.setDelimiter(", ");
        assertDoesNotThrow(() -> handler.read());
        String[] unexpectedRead = handler.getReadArray();
        assertDoesNotThrow(() -> handler.update(update, UpdateCommand.UPDATE_FILE_WITH_CONTENTS_OVERWRITING));
        assertDoesNotThrow(() -> handler.read());
        assertNotEquals(unexpectedRead, handler.getReadArray());
        assertEquals(Arrays.toString(update), Arrays.toString(handler.getReadArray()));
        List<Integer> updateList = List.of(Integer.MAX_VALUE, Integer.MIN_VALUE);
        List<String> unexpectedReadList = (List<String>) handler.getReadCollection();
        assertDoesNotThrow(() -> handler.update(updateList, UpdateCommand.UPDATE_FILE_WITH_CONTENTS_OVERWRITING));
        assertDoesNotThrow(() -> handler.read());
        assertNotEquals(unexpectedReadList, handler.getReadCollection());
        List<String> expectedReadList = List.of(String.valueOf(updateList.get(0)), String.valueOf(updateList.get(1)));
        assertEquals(expectedReadList.toString(), handler.getReadCollection().toString());
        Map<Integer, String> updateMap =  Map.of(3, "update3", 4, "update4");
        unexpectedReadList = (List<String>) handler.getReadCollection();
        assertDoesNotThrow(() -> handler.update(updateMap, UpdateCommand.UPDATE_FILE_WITH_CONTENTS_OVERWRITING));
        assertDoesNotThrow(() -> handler.read());
        assertNotEquals(unexpectedReadList, handler.getReadCollection());
        expectedReadList = new ArrayList<>(updateMap.values());
        expectedReadList.add(0, "3");
        expectedReadList.add(2, "4");
        assertEquals(expectedReadList.toString(), handler.getReadCollection().toString());
    }

    @Test
    @DisplayName("Creates again and Delete the contents of the file.")
    void testCreatesAgainDeleteTheContentsOfTheFile() {
        handler.create(CreateCommand.CREATES_AGAIN_THE_FILE);
        handler.delete(DeleteCommand.DELETE_THE_CONTENTS_OF_THE_FILE);
        assertTrue(handler.getFile().exists());
        assertEquals(fileName, handler.getFileName());
        assertEquals(file, handler.getFile());
    }

    @Test
    @DisplayName("Creates and Delete the contents of the file.")
    void testCreatesDeleteTheContentsOfTheFile() {
        expectedFileName = "test(1).txt";
        expectedFile = new File(expectedFileName);
        expectedHandler = new FileHandler(expectedFileName);
        if (expectedFile.exists()) {
            expectedHandler.delete(DeleteCommand.DELETE_THE_FILE);
        }
        handler.create(CreateCommand.CREATES_THE_FILE);
        if (file.exists()) {
            handler.delete(DeleteCommand.DELETE_THE_CONTENTS_OF_THE_FILE);
            assertTrue(handler.getFile().exists());
            assertNotEquals(fileName, handler.getFileName());
            assertEquals(expectedFileName, handler.getFileName());
            assertNotEquals(file, handler.getFile());
            assertEquals(expectedFile, handler.getFile());
        }
    }

    @Test
    @DisplayName("Creates again and Delete the file.")
    void testCreatesAgainDeleteTheFile() {
        handler.create(CreateCommand.CREATES_AGAIN_THE_FILE);
        handler.delete(DeleteCommand.DELETE_THE_FILE);
        assertFalse(handler.getFile().exists());
        assertEquals(fileName, handler.getFileName());
        assertEquals(file, handler.getFile());
    }

    @Test
    @DisplayName("Creates and Delete the contents of the file.")
    void testCreatesDeleteTheFile() {
        expectedFileName = "test(1).txt";
        expectedFile = new File(expectedFileName);
        expectedHandler = new FileHandler(expectedFileName);
        if (expectedFile.exists()) {
            expectedHandler.delete(DeleteCommand.DELETE_THE_FILE);
        }
        handler.create(CreateCommand.CREATES_THE_FILE);
        if (file.exists()) {
            handler.delete(DeleteCommand.DELETE_THE_FILE);
            assertFalse(handler.getFile().exists());
            assertNotEquals(fileName, handler.getFileName());
            assertEquals(expectedFileName, handler.getFileName());
            assertNotEquals(file, handler.getFile());
            assertEquals(expectedFile, handler.getFile());
        }
    }
}