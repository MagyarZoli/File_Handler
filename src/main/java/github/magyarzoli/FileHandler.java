package github.magyarzoli;

import java.io.File;
import java.util.Arrays;

/**
 * Class for creating a file.
 * @since       1.0
 * @author      <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public class FileHandler
implements CreateFile {

    /**
     * Store specified file name in variable.
     */
    private String fileName;

    /**
     * Store specified file in variable.
     */
    private File file;

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
     * @param       fileName
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
     * @return      file
     */
    public File getFile() {
        return file;
    }

    /**
     * Can transfer to use an existing File.
     * @param       file
     */
    public void setFile(File file) {
        this.file = file;
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
                file = new File(name + "(" + count++ + ")." + split[(split.length-1)]);
            } while (file.exists());
            fileName = file.getName();
        }
    }
}