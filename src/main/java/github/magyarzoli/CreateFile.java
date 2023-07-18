package github.magyarzoli;

import java.io.IOException;

/**
 * File creation interface.
 * Implementing the inherited class will create a file.
 * @since       1.0
 * @author      <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public interface CreateFile {

    /**
     * during implementation, if the file does not yet exist, create it, if it does, change the name of the file and create the file as such.
     */
    public void createFile();

    /**
     * during implementation, if the file does not yet exist, create it, if it does, then create it again, ignoring the existence of the previous file.
     */
    public void recreateFile();

    /**
     * during implementation, if the file does not yet exist, create it, if it does, do not create the file, protecting the existing file.
     */
    public void notCreateAlreadyExists();

    /**
     * {@code create} method that takes a {@code CreateCommand} object as a parameter.
     * The method uses a {@code switch} statement to determine the appropriate action based on the value of the {@code command} parameter.
     * <ul>
     *     <li>The {@code create()} method is declared with a {@code void} return type and takes a {@code CreateCommand} object as a parameter.</li>
     *     <li>Inside the method, a switch statement is used to evaluate the value of the command parameter.</li>
     *     <li>The {@code case} labels represent different values of the {@code CreateCommand} enum.</li>
     *     <li>If the {@code command} is {@code CREATES_THE_FILE}, the {@code createFile()} method is invoked.</li>
     *     <li>If the {@code command} is {@code CREATES_AGAIN_THE_FILE}, the {@code recreateFile}() method is invoked.</li>
     *     <li>If the {@code command} is {@code CREATES_DOES_NOT_IF_IT_ALREADY_EXISTS}, the {@code notCreateAlreadyExists}() method is invoked.</li>
     *     <li>If none of the above cases match, meaning an unexpected value is passed,
     *     an {@link java.lang.IllegalArgumentException IllegalArgumentException} is thrown using the throw new {@code IllegalArgumentException()} statement.</li>
     *     <li>The {@code default} case is optional but recommended to catch unexpected or undefined values of the {@code CreateCommand} enum.</li>
     * </ul>
     * By using this {@code create()} method, you can pass different values of the {@code CreateCommand} enum to specify different creation behaviors.
     * The method will execute the corresponding action based on the value of the {@code command} parameter.
     * @param       command by entering it, the file can be created in different ways.
     * @see         github.magyarzoli.CreateFile.CreateCommand CreateCommand
     * @see         github.magyarzoli.CreateFile#createFile() createFile()
     * @see         github.magyarzoli.CreateFile#recreateFile() recreateFile()
     * @see         github.magyarzoli.CreateFile#notCreateAlreadyExists() notCreateAlreadyExists()
     */
    default void create(CreateCommand command) {
        switch (command) {
            case CREATES_THE_FILE:
                createFile();
                break;
            case CREATES_AGAIN_THE_FILE:
                recreateFile();
                break;
            case CREATES_DOES_NOT_IF_IT_ALREADY_EXISTS:
                notCreateAlreadyExists();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * {@code createNewFile} method that accepts an instance of the {@code FileFunctional} functional interface as a parameter.
     * This method executes the {@code fileFunctional()} method of the provided {@code FileFunctional} object.
     * <ul>
     *     <li>The {@code createNewFile()} method is declared with a {@code void} return type, and it takes a {@code FileFunctional} object as a parameter.</li>
     *     <li>Inside the method, there is a {@code try-catch} block to handle potential {@link java.io.IOExceptions IOExceptions}.</li>
     *     <li>The {@code fileFunctional()} method of the {@code FileFunctional} object is invoked within the try block using the {@code fileFunctional.fileFunctional()} syntax.</li>
     *     <li>If an {@code IOException} occurs during the execution of {@code fileFunctional()}, it is caught by the catch block.</li>
     *     <li>In the catch block, a {@link java.lang.RuntimeException RuntimeException} is thrown, wrapping the original {@code IOException} as the cause. 
     *     This allows the exception to be propagated up the call stack while preserving the original exception information</li>
     * </ul>
     * By using this {@code createNewFile()} method, you can pass any implementation of the {@code FileFunctional} interface as an argument.
     * The method will execute the {@code fileFunctional()} method of that implementation, 
     * handling any {@code IOExceptions} that may occur and wrapping them in a {@code RuntimeException} for easier error handling.
     * @param       fileFunctional lambda function.
     * @see         github.magyarzoli.FileFunctional#fileFunctional() fileFunctional()
     */
    default void createNewFile(FileFunctional fileFunctional) {
        try {
            fileFunctional.fileFunctional();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 3 named types of preparation.
     */
    enum CreateCommand {

        /**
         * Create the file, if it exists create it again with a new name which is an extended version of the original name.
         */
        CREATES_THE_FILE,

        /**
         * Create the file, if it exists it will overwrite the existing file.
         */
        CREATES_AGAIN_THE_FILE,

        /**
         * Before creating the file, it checks if there is a file with that name, if there is, it does not create the file.
         */
        CREATES_DOES_NOT_IF_IT_ALREADY_EXISTS,
    }
}