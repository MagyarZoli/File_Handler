package github.magyarzoli;

import java.io.*;
import java.util.*;

/**
 * Class for creating, reading, updating and deleting a file.
 * @since       1.0
 * @author      <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public class FileHandler
implements CRUDFile {

    /**
     * Store specified file name in variable.
     */
    private String fileName;

    /**
     * Store specified file in variable.
     */
    private File file;

    /**
     * Store delimiter text string
     */
    private String delimiter = " ";

    /**
     * Store values read from the file in an array.
     */
    private String[] readArray;

    /**
     * Storage of values read from a file in a collection.
     */
    private Collection<String> readCollection;

    /**
     * Storage of values scanned from a file in a array, keeping the scanning pattern.
     */
    private String[] delimitedArray;

    /**
     * Storage of values scanned from a file in a collection, keeping the scanning pattern.
     */
    private Collection<String> delimitedCollection;

    /**
     * Constructor reserved for further inheritance in a hierarchical system.
     */
    FileHandler() {}

    /**
     * The constructor takes a {@code fileName} parameter of type {@code String}.
     * <ul>
     *     <li>Calls the {@code setFileName()} method, passing the fileName parameter.
     *     This method sets the value of the {@code fileName} property of the {@code FileHandler} object, performing validations on the provided file name.</li>
     *     <li>Calls the setFile() method, passing a new {@link java.io.File File} object created with the {@code fileName} parameter.
     *     This method sets the value of the file property of the {@code FileHandler} object, which is assumed to be a member variable of the class.</li>
     * </ul>
     * the constructor initializes a {@code FileHandler} object by setting the file name and creating a corresponding {@code File} object based on the provided {@code fileName}.
     * It ensures that the fileName property and file property are properly set for the FileHandler instance being constructed.
     * @param       fileName the specified file name.
     * @see         github.magyarzoli.FileHandler#setFileName(String) setFileName(String)
     * @see         github.magyarzoli.FileHandler#setFile(File) setFile(File)
     */
    public FileHandler(String fileName) {
        setFileName(fileName);
        setFile(new File(fileName));
    }

    /**
     * This constructor takes a file parameter of type {@link java.io.File File}.
     * <ul>
     *     <li>Calls the {@code setFileName()} method, passing {@code file}.{@link java.io.File#getName() getName()}.
     *     This method sets the value of the {@code fileName} property of the {@code FileHandler} object to the name of the provided file.</li>
     *     <li>Calls the {@code setFile()} method, passing the file object itself.
     *     This method sets the value of the file property of the {@code FileHandler} object to the provided file.</li>
     * </ul>
     * This constructor initializes a {@code FileHandler} object by taking a {@code File} object as a parameter.
     * It extracts the name of the file using {@code file.getName()} and sets it as the {@code fileName} property.
     * Additionally, it directly sets the file property to the provided {@code File} object.
     * This constructor allows you to create a {@code FileHandler} instance based on an existing {@code File} object.
     * @param       file the specified file.
     * @see         github.magyarzoli.FileHandler#setFileName(String) setFileName(String)
     * @see         github.magyarzoli.FileHandler#setFile(File) setFile(File)
     */
    public FileHandler(File file) {
        setFileName(file.getName());
        setFile(file);
    }

    /**
     * {@code getFileName} returns the specified file name.
     * @return      fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * {@code setFileName} that sets the value of the {@code fileName} property.
     * <ul>
     *     <li>The {@code setFileName()} method is declared with a void return type and takes a {@code fileName} parameter of type {@code String}.</li>
     *     <li>Inside the method, there is an {@code if} statement to perform the validations.</li>
     *     <li>The conditions check if the {@code fileName} is not null, not empty or blank, respectively.</li>
     *     <li>If all the validation conditions are met, the {@code fileName} property of the object is set to the provided {@code fileName} value.</li>
     *     <li>If any of the validation conditions fail, meaning the {@code fileName} is empty, blank, or null,
     *     an {@link java.lang.IllegalArgumentException IllegalArgumentException} is thrown using throw new {@code IllegalArgumentException()}.
     *     Explains the reason for the exception.</li>
     * </ul>
     * By using this {@code setFileName()} method, you can set the value of the fileName property of an object while ensuring that the provided {@code fileName} meets the required criteria.
     * If the criteria are not met, an exception is thrown to indicate the error.
     * @param       fileName enter the file name you want to use.
     */
    public void setFileName(String fileName) {
        if (!fileName.isEmpty() && !fileName.isBlank() && fileName != null) {
            this.fileName = fileName;
        } else {
            throw new IllegalArgumentException("File name takes a empty or blank value!");
        }
    }

    /**
     * {@code getFile} returns specified file in {@link java.io.File File} class.
     * @return      returns a file.
     */
    public File getFile() {
        return file;
    }

    /**
     * Can transfer to use an existing File.
     * @param       file specify the file you want to use.
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * {@code getDelimiter} currently set delimiter.
     * @return      a delimiter string.
     */
    public String getDelimiter() {
        return delimiter;
    }

    /**
     * {@code setDelimiter} is a setter method for setting the {@code delimiter} property of an object.
     * <ul>
     *     <li>The method checks if the {@code delimiter} parameter is not {@code null}.</li>
     *     <li>If the {@code delimiter} parameter is not {@code null}, it assigns the value of the {@code delimiter}
     *     parameter to the {@code delimiter} property of the object.</li>
     * </ul>
     * @param       delimiter specified delimiter
     */
    public void setDelimiter(String delimiter) {
        if (delimiter != null) {
            this.delimiter = delimiter;
        }
    }

    /**
     * Storing read values in an array.
     * @return      read values in an array.
     */
    public String[] getReadArray() {
        return readArray;
    }

    /**
     * Storing read values in a collection.
     * @return      read values in a collection.
     */
    public Collection<String> getReadCollection() {
        return readCollection;
    }

    /**
     * {@code createFile} method within the {@code FileHandler} class.
     * <ul>
     *     <li>It checks if the file specified by the {@code file} property already exists using {@code file}.{@link java.io.File#exists() exists()}.</li>
     *     <li>If the file exists, it invokes the {@code newFile()} method.
     *     This method is assumed to generate a unique file name based on the existing file name.</li>
     *     <li>After generating a new file name (if needed), it invokes the {@code createNewFile()} method, passing a lambda expression as an argument.</li>
     *     <li>The lambda expression {@code () -> file.createNewFile()} represents an implementation of the {@code FileFunctional} interface.
     *     It calls the {@code createNewFile()} method on the file object, which is assumed to be the property of the {@code FileHandler} class.</li>
     *     <li>The {@code createNewFile()} method is expected to handle any potential {@code IOException} that may occur when creating the file.</li>
     * </ul>
     * {@code createFile} method checks if the file already exists and generates a new file name if necessary.
     * Then, it proceeds to create a new file using the {@code createNewFile()} method by invoking the {@code createNewFile()} method of the {@code FileFunctional} interface,
     * which encapsulates the {@code file.createNewFile()} call. This approach allows for flexible handling of the file creation process,
     * including potential exceptions, using functional programming concepts.
     * @see         github.magyarzoli.FileFunctional FileFunctional
     * @see         github.magyarzoli.CreateFile#createNewFile(FileFunctional) createNewFile(FileFunctional)
     */
    @Override
    public void createFile() {
        if (file.exists()) {
            newFile();
        }
        createNewFile(() -> file.createNewFile());
    }

    /**
     * {@code recreateFile} method within the {@code FileHandler} class.
     * <ul>
     *     <li>It checks if the file specified by the {@code file} property exists using {@code file}.{@link java.io.File#exists() exists()}.</li>
     *     <li>If the file exists, it invokes the {@link java.io.File#delete() delete()} method on the file object to delete the existing {@code file}.</li>
     *     <li>After deleting the file (if it existed), it proceeds to create a new file using the {@code createNewFile()} method by invoking
     *     the {@code createNewFile()} method of the {@code FileFunctional} interface,
     *     which encapsulates the {@code file.createNewFile()} call.</li>
     * </ul>
     * {@code recreateFile} method first checks if the file exists and deletes it if it does. Then, it creates a new file using the {@code createNewFile()} method.
     * This approach allows for recreating the file by deleting any existing file with the same name and then creating a new one using the {@code createNewFile()} method of the {@code FileFunctional} interface.
     * @see         github.magyarzoli.FileFunctional FileFunctional
     * @see         github.magyarzoli.CreateFile#createNewFile(FileFunctional) createNewFile(FileFunctional)
     */
    @Override
    public void recreateFile() {
        if (file.exists()) {
            file.delete();
        }
        createNewFile(() -> file.createNewFile());
    }

    /**
     * {@code notCreateAlreadyExists} method within the {@code FileHandler}.
     * <ul>
     *     <li>It checks if the file specified by the {@code file} property does not exist using {@code !file}.{@link java.io.File#exists() exists()}.</li>
     *     <li>If the file does not exist, it proceeds to create a new file using the {@code createNewFile()} method by invoking the {@code createNewFile()} method of the {@code FileFunctional} interface,
     *     which encapsulates the {@code file.createNewFile()} call.</li>
     * </ul>
     * {@code notCreateAlreadyExists} method checks if the file does not exist and creates a new file using the {@code createNewFile()} method only in the case where the file does not already exist.
     * This approach allows for creating the file only when it does not exist, thus preventing the creation of duplicate files.
     * @see         github.magyarzoli.FileFunctional FileFunctional
     * @see         github.magyarzoli.CreateFile#createNewFile(FileFunctional) createNewFile(FileFunctional)
     */
    @Override
    public void notCreateAlreadyExists() {
        if (!file.exists()) {
            createNewFile(() -> file.createNewFile());
        }
    }

    /**
     * {@code read} It reads lines from a file and populates four different data structures:
     * {@code readArray}, {@code readCollection}, {@code delimitedArray}, and {@code delimitedCollection}.
     * <ul>
     *     <li>Two lists are declared to store the lines read from the file {@code result}
     *     and the lines with an additional newline character at the end {@code delimitedResult}.</li>
     *     <li>The method uses a try-with-resources block to ensure the {@link java.io.BufferedReader BufferedReader}
     *     is properly closed after use.</li>
     *     <li>A {@code BufferedReader} is created to read the lines from the file specified by {@code fileName}.</li>
     *     <li>Two {@link java.util.ArrayList ArrayList} instances are initialized for {@code result} and {@code delimitedResult}.</li>
     *     <li>The method reads each line from the file using {@code reader}.{@link java.io.BufferedReader#readLine()}
     *     and adds the line to both {@code result} and {@code delimitedResult}.</li>
     *     <li>The last line in {@code delimitedResult} (the one with an extra newline) is retrieved.</li>
     *     <li>The last line in {@code delimitedResult} is modified to remove the trailing newline character.</li>
     *     <li>If any {@code IOException} occurs during file reading, a {@link java.lang.RuntimeException RuntimeException}
     *     is thrown, wrapping the original {@code IOException}.</li>
     *     <li>After the try-with-resources block, the method converts both lists to arrays and assigns them to the corresponding class fields:
     *     {@code readArray} is assigned the array representation of {@code result}.
     *     {@code readCollection} is assigned the reference to {@code result} (not a new copy).
     *     {@code delimitedArray} is assigned the array representation of {@code delimitedResult}.
     *     {@code delimitedCollection} is assigned the reference to {@code delimitedResult} (not a new copy).</li>
     * </ul>
     * If the file is not found or there are any other issues while reading, a {@code RuntimeException} will be thrown.
     */
    @Override
    public void read() {
        List<String> result, delimitedResult;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            result = new ArrayList<>();
            delimitedResult = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
                delimitedResult.add(line + "\n");
            }
            String lastLine = delimitedResult.get(delimitedResult.size() - 1);
            delimitedResult.set((delimitedResult.size() - 1), lastLine.substring(0, lastLine.length() - 1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        readArray = result.toArray(new String[0]);
        readCollection = result;
        delimitedArray = delimitedResult.toArray(new String[0]);
        delimitedCollection = delimitedResult;
    }

    /**
     * {@code updateFileWithStarting} method that takes an array of type {@code T} named {@code update} and is used to
     * update a file with the elements of the {@code update} array. The update is done by adding the elements from the
     * {@code update} array to the beginning of the file.
     * <ul>
     *     <li>{@code <T>} This indicates that the method is a generic method with a type parameter {@code T}.</li>
     *     <li>his means the method does not return any value; it is meant to update the file with the provided data.</li>
     *     <li>The method calls the {@code write} method with the following arguments:</li>
     *     <li>{@code update} The array of type {@code T} that contains the elements to be added to the file. This
     *     represents the data that needs to be written to the file.</li>
     *     <li>{@code delimiter} The delimiter used to separate the elements in the {@code update} array. This delimiter
     *     will be used when writing the elements to the file.</li>
     *     <li>{@code delimitedArray} This seems to be an array of strings called {@code delimitedArray}, which is a
     *     property of the class or object that contains this method. It's used to provide additional data to the
     *     {@code write} method. The method write seems to be designed to handle writing collections {@code Collection<T>}
     *     and maps {@code Map<K, V>} in addition to arrays.</li>
     *     <li>An empty string "", which represents an empty delimiter. In this case, the {@code write} method does not
     *     use any delimiter between the elements from the {@code update} array and the elements from {@code delimitedArray}.
     *     This indicates that no separator is required between these two parts.</li>
     * </ul>
     * {@code write} method with these arguments, the {@code updateFileWithStarting} method effectively writes the elements
     * of the {@code update} array to the beginning of the file. The {@code delimitedArray} may contain some data that was
     * already in the file, and the new data from the {@code update} array is added at the beginning without any separator
     * between them. For the complete behavior of this method and the write method, you should also consider the
     * implementations of the write method and how the delimiter and delimitedArray properties are used.
     * @param       update the desired content that you would upload.
     * @param       <T> allows this method to be used with different types of arrays
     * @see         github.magyarzoli.FileHandler#write(Object[], String, Object[], String) write(Object[], String, Object[], String)
     */
    @Override
    public <T> void updateFileWithStarting(T[] update) {
        write(update, delimiter, delimitedArray, "");
    }

    /**
     * {@code updateFileWithEnding} method that takes an array of type {@code T} named {@code update}. This method is used
     * to update a file with the elements of the {@code update} array by adding them to the end of the file.
     * <ul>
     *     <li>{@code <T>} This indicates that the method is a generic method with a type parameter {@code T}.</li>
     *     <li>This means the method does not return any value; it is meant to update the file with the provided data.</li>
     *     <li>The method calls the {@code write} method with the following arguments:</li>
     *     <li>{@code delimitedArray} This seems to be an array of strings called {@code delimitedArray}, which is a
     *     property of the class or object that contains this method. The {@code delimitedArray} is used to provide data
     *     that was already in the file. It is meant to be written to the file first, as it represents the existing data
     *     at the end of the file.</li>
     *     <li>An empty string "", which represents an empty delimiter. In this case, the {@code write} method does not
     *     use any delimiter between the elements from {@code delimitedArray} and the elements from the {@code update} array.
     *     This indicates that no separator is required between these two parts.</li>
     *     <li>{@code update} The array of type {@code T} that contains the elements to be added to the file. This represents
     *     the data that needs to be written to the file.</li>
     *     <li>{@code delimiter} The delimiter used to separate the elements in the {@code update} array. This delimiter
     *     will be used when writing the elements to the file.</li>
     * </ul>
     * By calling the {@code write} method with these arguments, the {@code updateFileWithEnding} method effectively writes
     * the elements of the {@code update} array to the end of the file. The {@code delimitedArray} represents any data that
     * was already present in the file. The new data from the {@code update} array is added to the end of the file without
     * any separator between them. For the complete behavior of this method and the {@code write} method, you should also
     * consider the implementations of the {@code write} method and how the {@code delimitedArray} and {@code delimiter}
     * properties are used. The actual writing of the data to the file occurs in the write method when you flush or close
     * the {@link java.io.BufferedWriter BufferedWriter} after calling this method.
     * @param       update the desired content that you would upload.
     * @param       <T> allows this method to be used with different types of arrays.
     * @see         github.magyarzoli.FileHandler#write(Object[], String, Object[], String) write(Object[], String, Object[], String)
     */
    @Override
    public <T> void updateFileWithEnding(T[] update) {
        write(delimitedArray, "", update, delimiter);
    }

    /**
     * {@code updateFile} method that takes an array of type {@code T} named {@code update}. This method is used to update
     * a file with the elements of the {@code update} array.
     * <ul>
     *     <li>{@code <T>} This indicates that the method is a generic method with a type parameter {@code T}.</li>
     *     <li>This means the method does not return any value; it is meant to update the file with the provided data.</li>
     *     <li>The method calls the {@code write} method with the following arguments:</li>
     *     <li>{@code update} The array of type {@code T} that contains the elements to be added to the file. This
     *     represents the data that needs to be written to the file.</li>
     *     <li>{@code delimiter} The delimiter used to separate the elements in the {@code update} array. This delimiter
     *     will be used when writing the elements to the file.</li>
     *     <li>This indicates that there is no data to be provided as {@code delimitedArray}, so it passes {@code null}
     *     for this argument.</li>
     *     <li>An empty string "", which represents an empty delimiter. In this case, the {@code write} method does not
     *     use any delimiter.</li>
     * </ul>
     * By calling the {@code write} method with these arguments, the {@code updateFile} method effectively writes the
     * elements of the {@code update} array to the file. The write method will simply write the elements from the
     * {@code update} array to the file without any additional data provided as {@code delimitedArray}. For the complete
     * behavior of this method and the write method, you should also consider the implementations of the {@code write}
     * method and how the {@code delimiter} property is used. The actual writing of the data to the file occurs in the
     * write method when you flush or close the {@link java.io.BufferedWriter BufferedWriter} after calling this method.
     * @param       update the desired content that you would upload.
     * @param       <T> allows this method to be used with different types of arrays.
     * @see         github.magyarzoli.FileHandler#write(Object[], String, Object[], String) write(Object[], String, Object[], String)
     */
    @Override
    public <T> void updateFile(T[] update) {
        write(update, delimiter, null ,"");
    }

    /**
     * {@code updateFileWithStarting} method that takes a collection of type {@code C} containing elements of type {@code T}
     * named {@code update}. The method is used to update a file with the elements of the {@code update} collection by
     * adding the elements to the beginning of the file.
     * <ul>
     *     <li>{@link java.lang.SuppressWarnings @SuppressWarnings}{@code ("unchecked")}: This annotation is used to
     *     suppress unchecked warnings from the Java compiler. The warning appears because of the type casting
     *     {@code (C) delimitedCollection} on this line. The warning indicates that the compiler cannot guarantee type
     *     safety due to the unchecked cast. In this case, the developer assumes that {@code delimitedCollection} is of
     *     the same type as the {@code update} collection, and the warning is suppressed.</li>
     *     <li>{@code <T, C extends Collection<T>>} This indicates that the method is a generic method with two type
     *     parameters: {@code T} representing the type of elements in the collection, and {@code C}, which must be a
     *     subtype of {@code Collection<T>}, representing the type of the collection itself.</li>
     *     <li>This means the method does not return any value; it is meant to update the file with the provided data.</li>
     *     <li>The method calls the {@code write} method with the following arguments:</li>
     *     <li>{@code update} The collection of type {@code C} that contains the elements to be added to the file. This
     *     represents the data that needs to be written to the file.</li>
     *     <li>{@code delimiter} The delimiter used to separate the elements in the {@code update} collection. This
     *     delimiter will be used when writing the elements to the file.</li>
     *     <li>{@code (C) delimitedCollection} This seems to be a collection of type {@code C} called {@code delimitedCollection},
     *     which is a property of the class or object that contains this method. It's used to provide additional data to
     *     the {@code write} method.</li>
     *     <li>An empty string "", which represents an empty delimiter. In this case, the {@code write} method does not
     *     use any delimiter between the elements from the {@code update} collection and the elements from {@code delimitedCollection}.
     *     This indicates that no separator is required between these two parts.</li>
     * </ul>
     * {@code write} method with these arguments, the {@code updateFileWithStarting} method effectively writes the elements
     * of the {@code update} collection to the beginning of the file. The {@code delimitedCollection} may contain some data
     * that was already in the file, and the new data from the {@code update} collection is added at the beginning without
     * any separator between them. For the complete behavior of this method and the write method, you should also consider
     * the implementations of the write method and how the delimiter and delimitedCollection properties are used. The use
     * of {@code @SuppressWarnings("unchecked")} suggests that you should be cautious about the correctness of the cast
     * and ensure that the types are consistent to avoid runtime issues.
     * @param       update the desired content that you would upload.
     * @param       <T> allows this method to be used with different types.
     * @param       <C> this constraint ensures that must be a subtype of Collection.
     * @see         github.magyarzoli.FileHandler#write(Collection, String, Collection, String) write(Collection, String, Collection, String)
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T, C extends Collection<T>> void updateFileWithStarting(C update) {
        write(update, delimiter, (C) delimitedCollection, "");
    }

    /**
     * {@code updateFileWithEnding} method takes a collection of type {@code C} containing elements of type {@code T} named
     * update. This method is used to update a file with the elements of the {@code update} collection by adding them to
     * the end of the file.
     * <ul>
     *     <li>{@link java.lang.SuppressWarnings @SuppressWarnings}{@code ("unchecked")}: This annotation is used to
     *     suppress unchecked warnings from the Java compiler. The warning appears because of the type casting
     *     {@code (C) delimitedCollection} on this line. The warning indicates that the compiler cannot guarantee type
     *     safety due to the unchecked cast. In this case, the developer assumes that {@code delimitedCollection} is of
     *     the same type as the {@code update} collection, and the warning is suppressed.</li>
     *     <li>{@code <T, C extends Collection<T>>} This indicates that the method is a generic method with two type
     *     parameters: {@code T} representing the type of elements in the collection, and {@code C}, which must be a
     *     subtype of {@code Collection<T>}, representing the type of the collection itself.</li>
     *     <li>This means the method does not return any value; it is meant to update the file with the provided data.</li>
     *     <li>The method calls the {@code write} method with the following arguments:</li>
     *     <li>{@code (C) delimitedCollection} This seems to be a collection of type {@code C} called
     *     {@code delimitedCollection}, which is a property of the class or object that contains this method. The
     *     {@code delimitedCollection} is used to provide data that was already in the file. It is meant to be written to
     *     the file first, as it represents the existing data at the end of the file.</li>
     *     <li>An empty string "", which represents an empty delimiter. In this case, the {@code write} method does not
     *     use any delimiter between the elements from {@code delimitedCollection} and the elements from the {@code update}
     *     collection. This indicates that no separator is required between these two parts.</li>
     *     <li>{@code update} The collection of type {@code C} that contains the elements to be added to the file. This
     *     represents the data that needs to be written to the file.</li>
     *     <li>{@code delimiter} The delimiter used to separate the elements in the {@code update} collection. This delimiter
     *     will be used when writing the elements to the file.</li>
     * </ul>
     * By calling the {@code write} method with these arguments, the {@code updateFileWithEnding} method effectively writes
     * the elements of the {@code update} collection to the end of the file. The {@code delimitedCollection} represents any
     * data that was already present in the file. The new data from the {@code update} collection is added to the end of the
     * file without any separator between them. For the complete behavior of this method and the {@code write} method,
     * you should also consider the implementations of the {@code write} method and how the {@code delimitedCollection}
     * and {@code delimiter} properties are used. The actual writing of the data to the file occurs in the {@code write}
     * method when you flush or close the {@link java.io.BufferedWriter BufferedWriter} after calling this method. The use
     * of {@code @SuppressWarnings("unchecked")} suggests that you should be cautious about the correctness of the cast and
     * ensure that the types are consistent to avoid runtime issues.
     * @param       update the desired content that you would upload.
     * @param       <T> allows this method to be used with different types.
     * @param       <C> this constraint ensures that must be a subtype of Collection.
     * @see         github.magyarzoli.FileHandler#write(Collection, String, Collection, String) write(Collection, String, Collection, String)
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T, C extends Collection<T>> void updateFileWithEnding(C update) {
        write((C) delimitedCollection, "", update, delimiter);
    }

    /**
     * {@code updateFile} method that takes a collection of type {@code C} containing elements of type {@code T} named
     * {@code update}. This method is used to update a file with the elements of the {@code update} collection.
     * <ul>
     *     <li>{@code <T, C extends Collection<T>>} This indicates that the method is a generic method with two type
     *     parameters: {@code T} representing the type of elements in the collection, and {@code C}, which must be a
     *     subtype of {@code Collection<T>}, representing the type of the collection itself.</li>
     *     <li>This means the method does not return any value; it is meant to update the file with the provided data.</li>
     *     <li>The method calls the {@code write} method with the following arguments:</li>
     *     <li>{@code update} The collection of type {@code C} that contains the elements to be added to the file. This
     *     represents the data that needs to be written to the file.</li>
     *     <li>{@code delimiter} The delimiter used to separate the elements in the {@code update} collection. This
     *     delimiter will be used when writing the elements to the file.</li>
     *     <li>This indicates that there is no data to be provided as {@code delimitedCollection}, so it passes {@code null}
     *     for this argument.</li>
     *     <li>An empty string "", which represents an empty delimiter. In this case, the {@code write} method does not
     *     use any delimiter.</li>
     * </ul>
     * By calling the {@code write} method with these arguments, the {@code updateFile} method effectively writes the
     * elements of the {@code update} collection to the file. The write method will simply write the elements from the
     * update collection to the file without any additional data provided as {@code delimitedCollection}. For the complete
     * behavior of this method and the {@code write} method, you should also consider the implementations of the {@code write}
     * method and how the {@code delimiter} property is used. The actual writing of the data to the file occurs in the
     * {@code write} method when you flush or close the {@link java.io.BufferedWriter BufferedWriter} after calling this method.
     * @param       update the desired content that you would upload.
     * @param       <T> allows this method to be used with different types.
     * @param       <C> this constraint ensures that must be a subtype of Collection.
     * @see         github.magyarzoli.FileHandler#write(Collection, String, Collection, String) write(Collection, String, Collection, String)
     */
    @Override
    public <T, C extends Collection<T>> void updateFile(C update) {
        write(update, delimiter, null, "");
    }

    /**
     * {@code updateFileWithStarting} method that takes a map of type {@code M} with keys of type {@code K} and values of
     * type {@code V} named {@code update}. The method is used to update a file with the key-value pairs from the
     * {@code update} map by adding them to the beginning of the file.
     * <ul>
     *     <li>{@code <K, V, M extends Map<K, V>>} This indicates that the method is a generic method with three type
     *     parameters: {@code K} representing the type of keys, {@code V} representing the type of {@code values}, and
     *     {@code M}, which must be a subtype of {@code Map<K, V>}, representing the type of the map itself.</li>
     *     <li>This means the method does not return any value; it is meant to update the file with the provided data.</li>
     *     <li>The method uses a try-with-resources block to ensure the {@link java.io.BufferedWriter BufferedWriter} is
     *     properly closed after use. It creates a new {@code BufferedWriter} instance, wrapping it around a
     *     {@link java.io.FileWriter FileWriter} that writes to the file specified by {@code fileName}.</li>
     *     <li>The method checks if the {@code update} map is {@code null}.</li>
     *     <li>If {@code update} is {@code null}, it means there are no key-value pairs to be written from the {@code update}
     *     map, so it proceeds to write the key-value pairs from the {@code delimitedArray} using the {@code writing} method,
     *     passing {@code delimitedArray} and an empty string "" as the delimiter. This effectively writes the elements of
     *     {@code delimitedArray} to the beginning of the file.</li>
     *     <li>If {@code delimitedArray} is {@code null}, it means there is no data in {@code delimitedArray}, so it
     *     proceeds to write the key-value pairs from the {@code update} map using the {@code writing} method, passing
     *     {@code update} and the specified {@code delimiter}.</li>
     *     <li>If both {@code update} and {@code delimitedArray} are not {@code null}, it first writes the key-value pairs
     *     from the {@code update} map using the {@code writing} method, passing update and the specified {@code delimiter}.
     *     Then, it writes a newline character {@code "\n"} to the file using {@code writer.write("\n")} to separate the
     *     data from the {@code delimitedArray}. Finally, it writes the key-value pairs from the {@code delimitedArray}
     *     using the {@code writing} method, passing {@code delimitedArray} and an empty string "" as the {@code delimiter}.</li>
     *     <li>If any {@code IOException} occurs during file writing, a {@link java.lang.RuntimeException RuntimeException}
     *     is thrown, wrapping the original {@code IOException}.</li>
     * </ul>
     * {@code updateFileWithStarting} method writes all the key-value pairs from the {@code update} map to the beginning
     * of the file, separated by the provided {@code delimiter}. If the {@code update} map is {@code null}, it writes the
     * elements from the {@code delimitedArray} at the beginning of the file. If both the {@code update} map and the
     * {@code delimitedArray} are null, the file remains unchanged. As before, the actual writing of the data to the file
     * occurs in the {@code BufferedWriter} when you flush or close it after calling this method.
     * @param       update the desired content that you would upload.
     * @param       <K> represents the type of keys in the map.
     * @param       <V> represents the type of values in the map.
     * @param       <M> this constraint ensures that must be a subtype of Map.
     * @see         github.magyarzoli.FileHandler#writing(BufferedWriter, Map, String) writing(BufferedWriter, Map, String)
     */
    @Override
    public <K, V, M extends Map<K, V>> void updateFileWithStarting(M update) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            if (update == null) {
                writing(writer, delimitedArray, "");
            } else if (delimitedArray == null) {
                writing(writer, update, delimiter);
            } else {
                writing(writer, update, delimiter);
                writer.write("\n");
                writing(writer, delimitedArray, "");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {@code updateFileWithEnding} method that takes a map of type {@code M} with keys of type {@code K} and values of
     * type {@code V} named update. The method is used to update a file with the key-value pairs from the {@code update}
     * map by adding them to the end of the file.
     * <ul>
     *     <li>{@code <K, V, M extends Map<K, V>>} This indicates that the method is a generic method with three type
     *     parameters: {@code K} representing the type of keys, {@code V} representing the type of values, and {@code M},
     *     which must be a subtype of {@code Map<K, V>}, representing the type of the map itself.</li>
     *     <li>This means the method does not return any value; it is meant to update the file with the provided data.</li>
     *     <li>The method uses a try-with-resources block to ensure the {@link java.io.BufferedWriter BufferedWriter}
     *     is properly closed after use. It creates a new {@code BufferedWriter} instance, wrapping it around a
     *     {@link java.io.FileWriter FileWriter} that writes to the file specified by fileName.</li>
     *     <li>The method checks if the {@code update} map is {@code null}.</li>
     *     <li>If {@code update} is {@code null}, it means there are no key-value pairs to be written from the {@code update}
     *     map, so it proceeds to write the key-value pairs from the {@code delimitedArray} using the {@code writing} method,
     *     passing {@code delimitedArray} and an empty string "" as the delimiter. This effectively writes the elements of
     *     {@code delimitedArray} to the end of the file.</li>
     *     <li>If {@code delimitedArray} is {@code null}, it means there is no data in {@code delimitedArray}, so it
     *     proceeds to write the key-value pairs from the {@code update} map using the {@code writing} method, passing
     *     {@code update} and the specified {@code delimiter}.</li>
     *     <li>If both {@code update} and {@code delimitedArray} are not {@code null}, it first writes the key-value pairs
     *     from the {@code delimitedArray} using the {@code writing} method, passing {@code delimitedArray} and an empty
     *     string "" as the delimiter. Then, it writes a newline character {@code "\n"} to the file using
     *     {@code writer.write("\n")} to separate the data from the {@code update} map. Finally, it writes the key-value
     *     pairs from the update map using the {@code writing} method, passing {@code update} and the specified {@code delimiter}.</li>
     *     <li>If any {@code IOException} occurs during file writing, a {@link java.lang.RuntimeException RuntimeException}
     *     is thrown, wrapping the original {@code IOException}.</li>
     * </ul>
     * {@code updateFileWithEnding} method writes all the key-value pairs from the {@code update} map to the end of the file,
     * separated by the provided {@code delimiter}. If the {@code update} map is {@code null}, it writes the elements from
     * the {@code delimitedArray} to the end of the file. If both the {@code update} map and the {@code delimitedArray}
     * are {@code null}, the file remains unchanged. As before, the actual writing of the data to the file occurs in the
     * {@code BufferedWriter} when you flush or close it after calling this method.
     * @param       update the desired content that you would upload.
     * @param       <K> represents the type of keys in the map.
     * @param       <V> represents the type of values in the map.
     * @param       <M> this constraint ensures that must be a subtype of Map.
     * @see         github.magyarzoli.FileHandler#writing(BufferedWriter, Map, String) writing(BufferedWriter, Map, String)
     */
    @Override
    public <K, V, M extends Map<K, V>> void updateFileWithEnding(M update) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            if (update == null) {
                writing(writer, delimitedArray, "");
            } else if (delimitedArray == null) {
                writing(writer, update, delimiter);
            } else {
                writing(writer, delimitedArray, "");
                writer.write("\n");
                writing(writer, update, delimiter);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {@code updateFile} method that takes a map of type {@code M} with keys of type {@code K} and values of type {@code V}
     * named {@code update}. This method is used to update a file with the key-value pairs from the {@code update} map.
     * <ul>
     *     <li>{@code <K, V, M extends Map<K, V>>} This indicates that the method is a generic method with three type
     *     parameters: {@code K} representing the type of keys, {@code V} representing the type of values, and {@code M},
     *     which must be a subtype of {@code Map<K, V>}, representing the type of the map itself.</li>
     *     <li>This means the method does not return any value; it is meant to update the file with the provided data.</li>
     *     <li>The method uses a try-with-resources block to ensure the {@link java.io.BufferedWriter BufferedWriter} is
     *     properly closed after use. It creates a new {@code BufferedWriter} instance, wrapping it around a
     *     {@link java.io.FileWriter FileWriter} that writes to the file specified by {@code fileName}.</li>
     *     <li>The method checks if the {@code update} map is not {@code null}.</li>
     *     <li>If {@code update} is not {@code null}, it means there are key-value pairs to be written from the
     *     {@code update} map. It proceeds to write the key-value pairs from the {@code update} map using the {@code writing}
     *     method, passing {@code update} and the specified {@code delimiter}.</li>
     *     <li>If {@code update} is {@code null}, it means there are no key-value pairs to be written, so the method
     *     does nothing.</li>
     *     <li>If any {@code IOException} occurs during file writing, a {@link java.lang.RuntimeException RuntimeException}
     *     is thrown, wrapping the original {@code IOException}.</li>
     * </ul>
     * The {@code updateFile} method effectively writes all the key-value pairs from the {@code update} map to the file,
     * separated by the provided {@code delimiter}, if the {@code update} map is not {@code null}. If the {@code update}
     * map is {@code null}, the method does nothing, and the file remains unchanged. As before, the actual writing of the
     * data to the file occurs in the {@code BufferedWriter} when you flush or close it after calling this method.
     * @param       update the desired content that you would upload.
     * @param       <K> represents the type of keys in the map.
     * @param       <V> represents the type of values in the map.
     * @param       <M> this constraint ensures that must be a subtype of Map.
     * @see         github.magyarzoli.FileHandler#writing(BufferedWriter, Map, String) writing(BufferedWriter, Map, String)
     */
    @Override
    public <K, V, M extends Map<K, V>> void updateFile(M update) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            if (update != null) {
                writing(writer, update, delimiter);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {@code deleteAndCreateFile} method within the {@code FileHandler} class.
     * <ul>
     *     <li>It checks if the file specified by the {@code file} property exists using {@code file}.{@link java.io.File#exists() exists()}.</li>
     *     <li>If the file exists, it invokes the {@link java.io.File#delete() delete()} method on the file object to delete the existing {@code file}.</li>
     *     <li>After deleting the file (if it existed), it proceeds to create a new file using the {@code createNewFile()} method by invoking
     *     the {@code createNewFile()} method of the {@code FileFunctional} interface,
     *     which encapsulates the {@code file.createNewFile()} call.</li>
     * </ul>
     * {@code deleteAndCreateFile} method first checks if the file exists and deletes it if it does. Then, it creates a new file using the {@code createNewFile()} method.
     * This approach allows for recreating the file by deleting any existing file with the same name and then creating a new one using the {@code createNewFile()} method of the {@code FileFunctional} interface.
     * @see         github.magyarzoli.FileFunctional FileFunctional
     * @see         github.magyarzoli.CreateFile#createNewFile(FileFunctional) createNewFile(FileFunctional)
     */
    @Override
    public void deleteAndCreateFile() {
        if (file.exists()) {
            file.delete();
            createNewFile(() -> file.createNewFile());
        }
    }

    /**
     * {@code deleteFile} method within the {@code FileHandler} class.
     * <ul>
     *     <li>It checks if the file specified by the {@code file} property exists using {@code file}.{@link java.io.File#exists() exists()}.</li>
     *     <li>If the file exists, it invokes the {@link java.io.File#delete() delete()} method on the {@code file} object to delete the file.</li>
     * </ul>
     * {@code deleteFile} method checks if the file exists and deletes it if it does.
     * This method allows for explicitly deleting the file specified by the {@code file} property.
     */
    @Override
    public void deleteFile() {
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * {@code newFile} method that operates on the {@code fileName} property.
     * <ul>
     *     <li>The {@code fileName} string is split using the dot (.) as the delimiter. The result is stored in the {@code split} array.</li>
     *     <li>An {@code int} variable {@code count} is initialized to <i>1</i>.</li>
     *     <li>The {@code name} variable is created by joining the elements of the {@code split} array,
     *     except for the last element (the file extension), using the dot as the separator.</li>
     *     <li>The method checks if a file with the given {@code fileName} already exists by invoking {@code file}.{@link java.io.File#exists() exists()}.
     *     It's assumed that file is declared and initialized elsewhere in the class.</li>
     *     <li>If a file with the same name already exists, a loop is entered.</li>
     *     <li>Inside the loop, a new {@link java.io.File File} object is created with an updated name
     *     that includes the incremented {@code count} value and the original file extension.</li>
     *     <li>The loop continues until a file with the updated name does not exist {@code (!file.exists())}.</li>
     *     <li>Finally, the {@code fileName} property is updated with the name of the file that was found not to exist.</li>
     * </ul>
     * {@code newFile} method seems to be to generate a unique file name based on the original fileName if a file with the same name already exists. The method appends an incremented count within parentheses to the base file name until a non-existing file name is found.
     */
    private void newFile() {
        String[] split = fileName.split("\\.");
        int count = 1;
        String name = String.join(".", Arrays.copyOf(split, (split.length - 1)));
        if (file.exists()) {
            do {
                file = new File(name + "(" + count++ + ")." + split[(split.length - 1)]);
            } while (file.exists());
            fileName = file.getName();
        }
    }

    /**
     * {@code write} method that writes data to a file. It takes four parameters: two arrays of type {@code T}, {@code first}
     * and {@code last}, and two strings {@code firstDelimiter} and {@code lastDelimiter}. The method writes the elements
     * of the {@code first} array followed by the elements of the {@code last} array to the file specified by {@code fileName},
     * separated by the corresponding delimiters.
     * <ul>
     *     <li>This indicates that the method is a generic method with a type parameter {@code T}.</li>
     *     <li>This means the method does not return any value; it simply writes data to the file.</li>
     *     <li>The method uses a try-with-resources block to ensure the {@link java.io.BufferedWriter BufferedWriter}
     *     is properly closed after use. It creates a new {@code BufferedWriter} instance, wrapping it around a
     *     {@link java.io.FileWriter FileWriter} that writes to the file specified by {@code fileName}.</li>
     *     <li>The method checks if either {@code first} or {@code last} arrays are {@code null}.</li>
     *     <li>If {@code first} is {@code null}, it means there are no elements to be written from the {@code first} array,
     *     so it proceeds to write the elements from the {@code last} array using the {@code writing} method, passing
     *     {@code last} and {@code lastDelimiter}.</li>
     *     <li>If {@code last} is {@code null}, it means there are no elements to be written from the {@code last} array,
     *     so it proceeds to write the elements from the {@code first} array using the {@code writing} method, passing
     *     {@code first} and {@code firstDelimiter}.</li>
     *     <li>If both {@code first} and {@code last} arrays are not {@code null}, it first writes the elements from the
     *     {@code first} array using the {@code writing} method, passing first and {@code firstDelimiter}. Then, it
     *     writes a newline character {@code "\n"} to the file using {@code writer.write("\n")} to separate the data from
     *     the two arrays. Finally, it writes the elements from the {@code last} array using the writing method,
     *     passing {@code last} and {@code lastDelimiter}.</li>
     *     <li>If any {@code IOException} occurs during file writing, a {@link java.lang.RuntimeException RuntimeException}
     *     is thrown, wrapping the original {@code IOException}.</li>
     * </ul>
     * {@code write} method writes data from the {@code first} and {@code last} arrays to the file, separated by the
     * provided delimiters. The arrays can be of any type, and the delimiters can be any strings, allowing flexibility
     * in writing different data types to the file.
     * @param       first the desired content that you would upload the first.
     * @param       firstDelimiter specified delimiter the first.
     * @param       last the desired content that you would upload the last.
     * @param       lastDelimiter specified delimiter the last.
     * @param       <T> represents the type of elements in the first and last array.
     * @see         github.magyarzoli.FileHandler#writing(BufferedWriter, Object[], String) writing(BufferedWriter, Object[], String)
     */
    private <T> void write(T[] first, String firstDelimiter, T[] last, String lastDelimiter) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            if (first == null) {
                writing(writer, last, lastDelimiter);
            } else if (last == null) {
                writing(writer, first, firstDelimiter);
            } else {
                writing(writer, first, firstDelimiter);
                writer.write("\n");
                writing(writer, last, lastDelimiter);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {@code write} method that writes data to a file. It takes four parameters: two collections of type {@code C}
     * containing elements of type {@code T}, {@code first} and {@code last}, and two strings {@code firstDelimiter} and
     * {@code lastDelimiter}. The method writes the elements of the {@code first} collection followed by the elements of
     * the last collection to the file specified by {@code fileName}, separated by the corresponding delimiters.
     * <ul>
     *     <li>{@code <T, C extends Collection<T>>} This indicates that the method is a generic method with two type
     *     parameters: {@code T} representing the type of elements in the collection, and {@code C}, which must be a
     *     subtype of {@code Collection<T>}, representing the type of the collection itself.</li>
     *     <li>This means the method does not return any value; it simply writes data to the file.</li>
     *     <li>The method uses a try-with-resources block to ensure the {@link java.io.BufferedWriter BufferedWriter}
     *     is properly closed after use. It creates a new {@code BufferedWriter} instance, wrapping it around a
     *     {@link java.io.FileWriter FileWriter} that writes to the file specified by {@code fileName}.</li>
     *     <li>The method checks if either {@code first} or {@code last} collections are {@code null}.</li>
     *     <li>If {@code first} is {@code null}, it means there are no elements to be written from the {@code first} collection,
     *     so it proceeds to write the elements from the {@code last} collection using the {@code writing} method, passing
     *     {@code last} and {@code lastDelimiter}.</li>
     *     <li>If {@code last} is {@code null}, it means there are no elements to be written from the {@code last} collection,
     *     so it proceeds to write the elements from the {@code first} collection using the {@code writing} method, passing
     *     {@code first} and {@code firstDelimiter}.</li>
     *     <li>If both {@code first} and {@code last} collections are not {@code null}, it first writes the elements from the
     *     {@code first} collection using the {@code writing} method, passing first and {@code firstDelimiter}. Then, it
     *     writes a newline character {@code "\n"} to the file using {@code writer.write("\n")} to separate the data from
     *     the two collections. Finally, it writes the elements from the {@code last} array using the writing method,
     *     passing {@code last} and {@code lastDelimiter}.</li>
     *     <li>If any {@code IOException} occurs during file writing, a {@link java.lang.RuntimeException RuntimeException}
     *     is thrown, wrapping the original {@code IOException}.</li>
     * </ul>
     * {@code write} method writes data from the {@code first} and {@code last} collections to the file, separated by the
     * provided delimiters. The collections can be of any type, and the delimiters can be any strings, allowing flexibility
     * in writing different data types to the file. As before, the actual writing of the data to the file occurs in the
     * {@code BufferedWriter} when you flush or close it after calling this method.
     * @param       first the desired content that you would upload the first.
     * @param       firstDelimiter specified delimiter the first.
     * @param       last the desired content that you would upload the last.
     * @param       lastDelimiter specified delimiter the last.
     * @param       <T> allows this method to be used with different types.
     * @param       <C> this constraint ensures that must be a subtype of Collection.
     * @see         github.magyarzoli.FileHandler#writing(BufferedWriter, Collection, String) writing(BufferedWriter, Collection, String)
     */
    private <T, C extends Collection<T>> void write(C first, String firstDelimiter, C last, String lastDelimiter) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            if (first == null) {
                writing(writer, last, lastDelimiter);
            } else if (last == null) {
                writing(writer, first, firstDelimiter);
            } else {
                writing(writer, first, firstDelimiter);
                writer.write("\n");
                writing(writer, last, lastDelimiter);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {@code write} method that writes data to a file. It takes four parameters: two maps of type {@code M} with keys of
     * type {@code K} and values of type {@code V}, {@code first} and {@code last}, and two strings {@code firstDelimiter}
     * and {@code lastDelimiter}. The method writes the key-value pairs from the first map followed by the key-value pairs
     * from the last map to the file specified by {@code fileName}, separated by the corresponding delimiters.
     * <ul>
     *     <li>{@code <K, V, M extends Map<K, V>>} This indicates that the method is a generic method with three type
     *     parameters: {@code K} representing the type of keys, {@code V} representing the type of {@code values}, and
     *     {@code M}, which must be a subtype of {@code Map<K, V>}, representing the type of the map itself.</li>
     *     <li>This means the method does not return any value; it simply writes data to the file.</li>
     *     <li>The method uses a try-with-resources block to ensure the {@link java.io.BufferedWriter BufferedWriter}
     *     is properly closed after use. It creates a new {@code BufferedWriter} instance, wrapping it around a
     *     {@link java.io.FileWriter FileWriter} that writes to the file specified by {@code fileName}.</li>
     *     <li>The method checks if either {@code first} or {@code last} maps  are {@code null}.</li>
     *     <li>If {@code first} is {@code null}, it means there are no elements to be written from the {@code first} map,
     *     so it proceeds to write the elements from the {@code last} map using the {@code writing} method, passing
     *     {@code last} and {@code lastDelimiter}.</li>
     *     <li>If {@code last} is {@code null}, it means there are no elements to be written from the {@code last} map,
     *     so it proceeds to write the elements from the {@code first} map using the {@code writing} method, passing
     *     {@code first} and {@code firstDelimiter}.</li>
     *     <li>If both {@code first} and {@code last} maps are not {@code null}, it first writes the elements from the
     *     {@code first} map using the {@code writing} method, passing first and {@code firstDelimiter}. Then, it
     *     writes a newline character {@code "\n"} to the file using {@code writer.write("\n")} to separate the data from
     *     the two maps . Finally, it writes the elements from the {@code last} array using the writing method,
     *     passing {@code last} and {@code lastDelimiter}.</li>
     *     <li>If any {@code IOException} occurs during file writing, a {@link java.lang.RuntimeException RuntimeException}
     *     is thrown, wrapping the original {@code IOException}.</li>
     * </ul>
     * {@code write} method writes all the key-value pairs from the {@code first} and {@code last} maps to the file,
     * separated by the provided delimiters. The maps can be of any types for keys and values, and the delimiters can be
     * any strings, allowing flexibility in writing different data types to the file. As before, the actual writing of
     * the data to the file occurs in the {@code BufferedWriter} when you flush or close it after calling this method.
     * @param       first the desired content that you would upload the first.
     * @param       firstDelimiter specified delimiter the first.
     * @param       last the desired content that you would upload the last.
     * @param       lastDelimiter specified delimiter the last.
     * @param       <K> represents the type of keys in the map.
     * @param       <V> represents the type of values in the map.
     * @param       <M> this constraint ensures that must be a subtype of Map.
     * @see         github.magyarzoli.FileHandler#writing(BufferedWriter, Map, String) writing(BufferedWriter, Map, String)
     */
    private <K, V, M extends Map<K, V>> void write(M first, String firstDelimiter, M last, String lastDelimiter) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            if (first == null) {
                writing(writer, last, lastDelimiter);
            } else if (last == null) {
                writing(writer, first, firstDelimiter);
            } else {
                writing(writer, first, firstDelimiter);
                writer.write("\n");
                writing(writer, last, lastDelimiter);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {@code writing} method that takes three parameters: a {@link java.io.BufferedWriter BufferedWriter} named
     * {@code writer}, an array of type {@code T} named {@code update}, and a {@code String} named {@code delimiter}.
     * This method is used to write the elements of the {@code update} array to the {@code BufferedWriter},
     * separated by the specified {@code delimiter}.
     * <ul>
     *     <li>This indicates that the method is a generic method with a type parameter {@code T}. The type {@code T}
     *     represents the type of elements in the {@code update} array.</li>
     *     <li>This means the method does not return any value; it simply writes the elements to the {@code BufferedWriter}.</li>
     *     <li>The method uses a for-each loop to iterate over the elements of the {@code update} array.</li>
     *     <li> For each element value in the {@code update} array, the method writes the value to the {@code BufferedWriter}
     *     using the {@link java.io.Writer write} method. It also appends the {@code delimiter} to separate each value,
     *     except for the last value in the array.</li>
     *     <li>This checks if the current {@code value} is the last element in the {@code update} array. The
     *     {@link java.lang.Object#equals(Object) equals} method is used to compare the current value with the last
     *     element in the array.</li>
     *     <li>This is a ternary operator. If the current {@code value} is the last element, an empty string "" is added
     *     to the {@code BufferedWriter}, meaning there will be no delimiter after the last {@code value}. If the
     *     current value is not the last element, the specified {@code delimiter} is added after the value to separate
     *     it from the next value.</li>
     * </ul>
     * {@code writing} method writes all the elements of the {@code update} array to the {@code BufferedWriter}, with
     * the specified {@code delimiter} used to separate them. The last element is not followed by the delimiter to
     * avoid adding an extra delimiter at the end of the data. The actual writing of the data to the file occurs in the
     * {@code BufferedWriter} when you flush or close it after calling this method.
     * @param       writer the elements to the {@code BufferedWriter}.
     * @param       update the desired content that you would upload.
     * @param       delimiter specified delimiter.
     * @param       <T> represents the type of elements in the {@code update} array.
     * @throws      IOException if the named file exists but is a directory rather than a regular file, does not exist
     *              but cannot be created, or cannot be opened for any other reason
     */
    private <T> void writing(BufferedWriter writer, T[] update, String delimiter)
            throws IOException {
        for (T value : update) {
            writer.write(value + (value.equals(update[(update.length - 1)]) ? "" : delimiter));
        }
    }

    /**
     * {@code writing} method with a generic type {@code T} and a type parameter {@code C} that extends {@code Collection<T>}.
     * The method takes three parameters: a {@link java.io.BufferedWriter BufferedWriter} named {@code writer}, a collection
     * of type {@code C} containing elements of type {@code T} named {@code update}, and a {@code String} named {@code delimiter}.
     * This method is used to write the elements of the {@code update} collection to the {@code BufferedWriter},
     * separated by the specified {@code delimiter}.
     * <ul>
     *     <li>{@link java.lang.SuppressWarnings @SuppressWarnings}{@code ("unchecked")}: This annotation is used to
     *     suppress unchecked warnings from the Java compiler. The warning appears because of the type casting from a generic </li>
     *     <li>{@code Collection<T>} to a {@code List<T>} on this line: {@code List<T> listUpdate = (List<T>) update;}.
     *     The warning indicates that the compiler cannot guarantee type safety due to the unchecked cast. In this case,
     *     the developer assumes that the {@code update} collection is actually a {@code List<T>}, and the warning is suppressed.</li>
     *     <li>{@code <T, C extends Collection<T>>} This indicates that the method is a generic method with two type
     *     parameters: {@code T} representing the type of elements in the collection and {@code C}, which must be a
     *     subtype of {@code Collection<T>}, representing the type of the collection itself.</li>
     *     <li>This means the method does not return any value; it simply writes the elements to the {@code BufferedWriter}.</li>
     *     <li>The method casts the {@code update} collection to a {@code List<T>}. This line assumes that the {@code update}
     *     collection is actually a {@code List<T>}, and the developer is confident about this type casting. As mentioned
     *     earlier, this type casting can potentially cause runtime exceptions if the actual type of {@code update} is not
     *     a {@code List<T>}, which is why the {@code @SuppressWarnings("unchecked")} annotation is used to suppress
     *     the compiler warning.</li>
     *     <li>The method uses a for-each loop to iterate over the elements of the {@code listUpdate} list (which is a
     *     type-casted version of the {@code update} collection).</li>
     *     <li>For each element {@code value} in the {@code listUpdate} list, the method writes the value to the
     *     {@code BufferedWriter} using the {@link java.io.Writer write} method. It also appends the {@code delimiter}
     *     to separate each value, except for the last value in the list.</li>
     *     <li>This checks if the current {@code value} is the last element in the {@code listUpdate} list. The
     *     {@link java.lang.Object#equals(Object) equals} method is used to compare the current value with the last
     *     element in the list.</li>
     *     <li>This is a ternary operator. If the current {@code value} is the last element, an empty string "" is added
     *     to the {@code BufferedWriter}, meaning there will be no delimiter after the last value. If the current
     *     {@code value} is not the last element, the specified {@code delimiter} is added after the value to separate
     *     it from the next value.</li>
     * </ul>
     * {@code writing} method writes all the elements of the {@code update} collection to the {@code BufferedWriter},
     * with the specified {@code delimiter} used to separate them. The last element is not followed by the delimiter
     * to avoid adding an extra delimiter at the end of the data. As mentioned earlier, the actual writing of the data
     * to the file occurs in the {@code BufferedWriter} when you flush or close it after calling this method.
     * @param       writer the elements to the {@code BufferedWriter}.
     * @param       update the desired content that you would upload.
     * @param       delimiter specified delimiter.
     * @param       <T> allows this method to be used with different types.
     * @param       <C> this constraint ensures that must be a subtype of Collection.
     * @throws      IOException if the named file exists but is a directory rather than a regular file, does not exist
     *              but cannot be created, or cannot be opened for any other reason
     */
    @SuppressWarnings("unchecked")
    private <T, C extends Collection<T>> void writing(BufferedWriter writer, C update, String delimiter)
            throws IOException {
        List<T> listUpdate = (List<T>) update;
        for (T value : listUpdate) {
            writer.write(value + (value.equals(listUpdate.get(listUpdate.size() - 1)) ? "" : delimiter));
        }
    }

    /**
     * {@code writing} method with three generic types: {@code K}, {@code V}, and {@code M}, where {@code M} extends
     * {@code Map<K, V>}. The method takes three parameters: a {@link java.io.BufferedWriter BufferedWriter} named
     * {@code writer}, a map of type {@code M} with keys of type {@code K} and {@code values} of type {@code V} named
     * {@code update}, and a {@code String} named {@code delimiter}. This method is used to write the key-value pairs
     * from the update map to the {@code BufferedWriter}, with the specified {@code delimiter} used to separate the key and value.
     * <ul>
     *     <li>{@code <K, V, M extends Map<K, V>>} This indicates that the method is a generic method with three type
     *     parameters: {@code K}, {@code V}, and {@code M}, where {@code M} must be a subtype of {@link java.util.Map Map}
     *     with keys of type {@code K} and values of type {@code V}.</li>
     *     <li>This means the method does not return any value; it simply writes the key-value pairs to the {@code BufferedWriter}.</li>
     *     <li>This creates a boolean variable {@code first} and initializes it to {@code true}. This variable is used
     *     to keep track of whether it's the first entry being written or not. It is used to avoid writing a newline
     *     character before the first entry.</li>
     *     <li>The method uses a for-each loop to iterate over the key-value pairs (entries) in the {@code update} map.</li>
     *     <li>For each key-value pair {@code entry} in the {@code update} map, the method {@code writes} the key and
     *     value to the {@code BufferedWriter} using the {@link java.io.Writer write} method. It uses the {@code delimiter}
     *     to separate the key and value.</li>
     *     <li>This is a ternary operator. If it's the first entry being written ({@code first} is {@code true}), an
     *     empty string is added to the {@code BufferedWriter}, indicating no newline before the key-value pair. If it's
     *     not the first entry ({@code first} is {@code false}), a newline character {@code \n} is added before the
     *     key-value pair, separating it from the previous entry.</li>
     *     <li>This concatenates the key and value with the specified {@code delimiter} in between them. The
     *     {@link java.util.Map.Entry#getKey() getKey()} method retrieves the key, and the {@link java.util.Map.Entry#getValue() getValue()}
     *     method retrieves the value from the current {@code entry}.</li>
     *     <li>After writing the first entry, the {@code first} variable is set to {@code false}, so that subsequent
     *     entries won't have a newline before them.</li>
     * </ul>
     * {@code writing} method writes all the key-value pairs from the {@code update} map to the {@code BufferedWriter},
     * separated by the specified {@code delimiter}. The first entry does not have a newline before it, and each subsequent
     * entry is written on a new line. As mentioned earlier, the actual writing of the data to the file occurs in the
     * {@code BufferedWriter} when you flush or close it after calling this method.
     * @param       writer the elements to the {@code BufferedWriter}.
     * @param       update the desired content that you would upload.
     * @param       delimiter specified delimiter.
     * @param       <K> represents the type of keys in the map.
     * @param       <V> represents the type of values in the map.
     * @param       <M> this constraint ensures that must be a subtype of Map.
     * @throws      IOException if the named file exists but is a directory rather than a regular file, does not exist
     *              but cannot be created, or cannot be opened for any other reason
     */
    private <K, V, M extends Map<K, V>> void writing(BufferedWriter writer, M update, String delimiter)
            throws IOException {
        boolean first = true;
        for (Map.Entry<K, V> entry : update.entrySet()) {
            writer.write((first ? "" : "\n") + entry.getKey() + delimiter + entry.getValue());
            first = false;
        }
    }
}