package github.magyarzoli;

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
    default void delet(DeleteCommand command) {
        switch (command) {
            case DELETE_THE_CONTENTS_OF_THE_FILE:
                deleteAndCreateFile();
                break;
            case DELETE_THE_FILE:
                deleteFile();
                break;
            default :
                throw new IllegalArgumentException();
        }
    }

    /**
     * 2 named types of preparation.
     */
    enum DeleteCommand {

        /**
         * delete file, and then create a new file.
         */
        DELETE_THE_CONTENTS_OF_THE_FILE,

        /**
         * delete file.
         */
        DELETE_THE_FILE,
    }
}