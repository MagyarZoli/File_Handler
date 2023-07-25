package github.magyarzoli;

import java.io.IOException;

/**
 * File deletion interface.
 * Implemented, the inherited class will be able to delete a file.
 * @since       1.0
 * @author      <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public interface DeleteFile {

    /**
     * During  implementation, if the file exists, delete the file and create a new file.
     */
    public void deleteAndCreateFile();

    /**
     * During implementation, if the file exists, delete the file.
     */
    public void deleteFile();

    /**
     * {@code delete} method that takes a {@code DeleteCommand} object as a parameter.
     * The method uses a {@code switch} statement to determine the appropriate action based on the value of the {@code command} parameter.
     * <ul>
     *     <li>The {@code delete()} method is declared with a void return type and takes a {@code DeleteCommand} object as a parameter.</li>
     *     <li>Inside the method, a {@code switch} statement is used to evaluate the value of the {@code command} parameter.</li>
     *     <li>The {@code case} labels represent different values of the {@code DeleteCommand} enum.</li>
     *     <li>If the {@code command} is {@code DELETE_THE_CONTENTS_OF_THE_FILE}, the {@code deleteAndCreateFile()} method is invoked.</li>
     *     <li>If the {@code command} is {@code DELETE_THE_FILE}, the {@code deleteFile()} method is invoked.</li>
     *     <li>If none of the above cases match, meaning an unexpected value is passed,
     *     an {@link java.lang.IllegalArgumentException IllegalArgumentException} is thrown using the throw new {@code IllegalArgumentException()} statement.</li>
     *     <li>The {@code default} case is optional but recommended to catch unexpected or undefined values of the {@code DeleteCommand} enum.</li>
     * </ul>
     * {@code delete} method, you can pass different values of the {@code DeleteCommand} enum to specify different deletion behaviors.
     * The method will execute the corresponding action based on the value of the command parameter.
     * @param       command by specifying the file can be deleted in several ways.
     */
    default void delete(DeleteCommand command) {
        switch (command) {
            case DELETE_THE_CONTENTS_OF_THE_FILE -> deleteAndCreateFile();
            case DELETE_THE_FILE -> deleteFile();
            default -> throw new IllegalArgumentException();
        }
    }

    /**
     * {@code delete} method that takes a functional interface {@code FileFunctional} as a parameter. This functional
     * interface seems to have a single abstract method called {@code fileFunctional()}.
     * <ul>
     *     <li>This indicates that the method has a default implementation in the interface. Default methods were
     *     introduced in Java 8 and allow adding new methods to an interface without breaking the existing implementations
     *     of that interface.</li>
     *     <li>This means the method does not return any value; it performs some action.</li>
     *     <li>The method takes an instance of the functional interface {@code FileFunctional} as a parameter named
     *     {@code functional}.</li>
     *     <li>The method calls the abstract method {@code fileFunctional()} on the provided instance of {@code FileFunctional}.
     *     Since {@code FileFunctional} is a functional interface, it is expected to have a single abstract method, which
     *     is implemented by the calling code outside of this method.</li>
     * </ul>
     * The purpose of the {@code delete} method is to provide a mechanism for executing code provided by the caller
     * through the {@code FileFunctional} interface. The implementation of the {@code fileFunctional()} method can contain
     * custom logic for file deletion or file-related operations. By calling the {@code delete} method with an instance of
     * {@code FileFunctional}, the caller can effectively execute their custom code within the context of the
     * {@code delete} method.
     * @param       functional lambda function.
     * @throws      IOException if the named file exists but is a directory rather than a regular file, does not exist
     *              but cannot be created, or cannot be opened for any other reason
     * @see         github.magyarzoli.FileFunctional#fileFunctional() fileFunctional()
     */
    default void delete(FileFunctional functional)
    throws IOException {
        functional.fileFunctional();
    }

    /**
     * 2 named types of preparation.
     */
    enum DeleteCommand {

        /**
         * Delete file, and then create a new file.
         */
        DELETE_THE_CONTENTS_OF_THE_FILE,

        /**
         * Delete file.
         */
        DELETE_THE_FILE,
    }
}