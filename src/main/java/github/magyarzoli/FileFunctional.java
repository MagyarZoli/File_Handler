package github.magyarzoli;

import java.io.IOException;

/**
 * Functional interfaces are commonly used in functional programming and with lambda expressions or method references to provide concise and expressive code.
 * By using this interface, you can define different implementations of {@code fileFunctional()} and pass them around as objects, which can be executed by invoking the method.
 * @since       1.0
 * @author      <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
@FunctionalInterface
public interface FileFunctional {
    
    /**
     * {@code fileFunctional}, which does not take any arguments and does not return a value.
     * @throws      IOException indicating that it can potentially encounter input/output errors.
     */
    void fileFunctional()
    throws IOException;
}