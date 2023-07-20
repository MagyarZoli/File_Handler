package github.magyarzoli;

/**
 * File reader interface.
 * When implemented, the inherited class will be able to read the file.
 * @since       1.0
 * @author      <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public interface ReadFile {

    /**
     * Create method that the inherited class must implement.
     * This method can run in one way: it reads the contents of the file.
     */
    public void read();
}