package github.magyarzoli;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * File update interface.
 * When implemented, the inherited class will be able to extend, rewrite.
 * @since       1.0
 * @author      <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public interface UpdateFile {

    /**
     * {@code updateFileWithStarting} method takes an array of type {@code T} named {@code update}.
     * It updates a file using the elements in the {@code update} array by appending them to the beginning of the file.
     * @param       update the desired content that you would upload.
     * @param       <T> allows this method to be used with different types of arrays.
     */
    public <T> void updateFileWithStarting(T[] update);

    /**
     * {@code updateFileWithEnding} method also takes an array of type {@code T} named {@code update}.
     * It updates a file using the elements in the {@code update} array by appending them to the end of the file.
     * @param       update the desired content that you would upload.
     * @param       <T> allows this method to be used with different types of arrays.
     */
    public <T> void updateFileWithEnding(T[] update);

    /**
     * {@code updateFile} method takes an array of type {@code T} named {@code update}.
     * It updates a file using the elements in the {@code update} array by overwriting the file with the contents of
     * the {@code update} array.
     * @param       update the desired content that you would upload.
     * @param       <T> allows this method to be used with different types of arrays.
     */
    public <T> void updateFile(T[] update);

    /**
     * {@code updateFileWithStarting} method takes a collection of type {@code C} containing elements of type
     * {@code T} named {@code update}. It updates a file using the elements in the {@code update} collection by appending
     * them to the beginning of the file.
     * @param       update the desired content that you would upload.
     * @param       <T> allows this method to be used with different types.
     * @param       <C> this constraint ensures that must be a subtype of Collection.
     */
    public <T, C extends Collection<T>> void updateFileWithStarting(C update);

    /**
     * {@code updateFileWithEnding} method takes a collection of type {@code C} containing elements of type
     * {@code T} named {@code update}. It updates a file using the elements in the {@code update} collection by appending them
     * to the end of the file.
     * @param       update the desired content that you would upload.
     * @param       <T> allows this method to be used with different types.
     * @param       <C> this constraint ensures that must be a subtype of Collection.
     */
    public <T, C extends Collection<T>> void updateFileWithEnding(C update);

    /**
     * {@code updateFile} method takes a collection of type {@code C} containing elements of type {@code T} named {@code update}.
     * It updates a file using the elements in the {@code update} collection by overwriting the file with the contents
     * of the {@code update} collection.
     * @param       update the desired content that you would upload.
     * @param       <T> allows this method to be used with different types.
     * @param       <C> this constraint ensures that must be a subtype of Collection.
     */
    public <T, C extends Collection<T>> void updateFile(C update);

    /**
     * {@code updateFileWithStarting} method takes a map of type {@code M} with keys of type {@code K} and values of
     * type {@code V} named {@code update}. It updates a file using the key-value pairs in the {@code update} map by
     * appending them to the beginning of the file.
     * @param       update the desired content that you would upload.
     * @param       <K> represents the type of keys in the map.
     * @param       <V> represents the type of values in the map.
     * @param       <M> this constraint ensures that must be a subtype of Map.
     */
    public <K, V, M extends Map<K, V>> void updateFileWithStarting(M update);

    /**
     * {@code updateFileWithEnding} method takes a map of type {@code M} with keys of type {@code K} and values of
     * type {@code V} named {@code update}. It updates a file using the key-value pairs in the {@code update} map by
     * appending them to the end of the file.
     * @param       update the desired content that you would upload.
     * @param       <K> represents the type of keys in the map.
     * @param       <V> represents the type of values in the map.
     * @param       <M> this constraint ensures that must be a subtype of Map.
     */
    public <K, V, M extends Map<K, V>> void updateFileWithEnding(M update);

    /**
     * {@code updateFile} method takes a map of type {@code M} with keys of type {@code K} and values of type
     * {@code V} named {@code update}. It updates a file using the key-value pairs in the {@code update} map by
     * overwriting the file with the contents of the {@code update} map.
     * @param       update the desired content that you would upload.
     * @param       <K> represents the type of keys in the map.
     * @param       <V> represents the type of values in the map.
     * @param       <M> this constraint ensures that must be a subtype of Map.
     */
    public <K, V, M extends Map<K, V>> void updateFile(M update);

    /**
     * {@code update} with a generic type {@code T}. The method takes two parameters:
     * an array of type {@code T} named {@code update} and an {@code UpdateCommand} enum named {@code command}.
     * The method is declared to throw an {@code IOException}.
     * <ul>
     *     <li>The method uses a switch statement on the {@code command} parameter to determine which action to perform
     *     based on the value of the enum.</li>
     *     <li>Depending on the value of {@code command}, the method performs different actions:</li>
     *     <li>If {@code command} is {@code UPDATE_FILE_TO_STARTING_CONTENTS},
     *     the method calls the {@code updateFileWithStarting} method, passing the {@code update} array as an argument.</li>
     *     <li>If {@code command} is {@code UPDATE_FILE_TO_ENDING_CONTENTS},
     *     the method calls the {@code updateFileWithEnding} method, passing the {@code update} array as an argument.</li>
     *     <li>If {@code command} is {@code UPDATE_FILE_WITH_CONTENTS_OVERWRITING},
     *     the method calls the {@code updateFile} method, passing the {@code update} array as an argument.</li>
     *     <li>If none of the above cases match, i.e., an unsupported {@code command} value is provided,
     *     the method throws an {@link java.lang.IllegalArgumentException IllegalArgumentException}
     *     to indicate an invalid command.</li>
     *     <li>The method is defined as a default method, which means it can be overridden in implementing classes
     *     but provides a default implementation if not overridden.</li>
     *     <li>The generic type {@code T} allows this method to be used with different types of arrays.</li>
     * </ul>
     * {@code update} method to perform different update actions on arrays, depending on the {@code UpdateCommand} provided.
     * @param       update the desired content that you would upload.
     * @param       command can be specified in which way the file update takes place.
     * @param       <T> allows this method to be used with different types of arrays.
     * @throws      IOException throw an exception if implemented methods are not properly implemented.
     * @see         github.magyarzoli.UpdateFile.UpdateCommand UpdateCommand
     * @see         github.magyarzoli.UpdateFile#updateFileWithStarting(Object[])  updateFileWithStarting(Object[])
     * @see         github.magyarzoli.UpdateFile#updateFileWithEnding(Object[]) updateFileWithEnding(Object[])
     * @see         github.magyarzoli.UpdateFile#updateFile(Object[]) updateFile(Object[])
     */
    default <T> void update(T[] update, UpdateCommand command)
    throws IOException {
        switch (command) {
            case UPDATE_FILE_TO_STARTING_CONTENTS -> updateFileWithStarting(update);
            case UPDATE_FILE_TO_ENDING_CONTENTS -> updateFileWithEnding(update);
            case UPDATE_FILE_WITH_CONTENTS_OVERWRITING -> updateFile(update);
            default -> throw new IllegalArgumentException();
        }
    }

    /**
     * {@code update} with two generic types: {@code T} and {@code C}. The method takes two parameters:
     * a collection of type {@code C} containing elements of type {@code T} named {@code update},
     * and an {@code UpdateCommand} enum named {@code command}. The method is declared to throw an {@code IOException}.
     * <ul>
     *     <li>The method now uses two generic types, {@code T} and {@code C}, where {@code T} represents
     *     the type of elements in the collection, and {@code C} represents the collection type itself.</li>
     *     <li>The {@code C} type parameter is constrained using the {@code extends} keyword with {@code Collection<T>}.
     *     This constraint ensures that {@code C} must be a subtype of {@code Collection<T>},
     *     meaning the {@code update} parameter must be a collection that can hold elements of type {@code T}.</li>
     *     <li>The {@code switch} statement operates on the {@code command} parameter.</li>
     *     <li>Depending on the value of {@code command}, the method performs different actions:</li>
     *     <li>If {@code command} is {@code UPDATE_FILE_TO_STARTING_CONTENTS},
     *     the method calls the {@code updateFileWithStarting} method, passing the {@code update} collection as an argument.</li>
     *     <li>If {@code command} is {@code UPDATE_FILE_TO_ENDING_CONTENTS},
     *     the method calls the {@code updateFileWithEnding} method, passing the {@code update} collection as an argument.</li>
     *     <li>If {@code command} is {@code UPDATE_FILE_WITH_CONTENTS_OVERWRITING},
     *     the method calls the {@code updateFile} method, passing the {@code update} collection as an argument.</li>
     *     <li>If none of the above cases match, i.e., an unsupported {@code command} value is provided,
     *     the method throws an {@link java.lang.IllegalArgumentException IllegalArgumentException} to indicate an invalid command.</li>
     *     <li>The method is still defined as a default method, which means it can be overridden in implementing
     *     classes but provides a default implementation if not overridden.</li>
     *     <li>The generic type {@code C} allows this method to be used with different types of collections that
     *     hold elements of type {@code T}.</li>
     * </ul>
     * {@code update} with allows more flexibility in the types of collections that can be passed to the update method
     * while still ensuring type safety. For example, you can pass a {@code List<Integer>} or a {@code Set<String>} to
     * the update method, as long as they match the constraint {@code Collection<T>}, where {@code T} represents the appropriate element type.
     * @param       update the desired content that you would upload.
     * @param       command can be specified in which way the file update takes place.
     * @param       <T> allows this method to be used with different types.
     * @param       <C> this constraint ensures that must be a subtype of Collection.
     * @throws      IOException throw an exception if implemented methods are not properly implemented.
     * @see         github.magyarzoli.UpdateFile.UpdateCommand UpdateCommand
     * @see         github.magyarzoli.UpdateFile#updateFileWithStarting(Collection) updateFileWithStarting(Collection)
     * @see         github.magyarzoli.UpdateFile#updateFileWithEnding(Collection) updateFileWithEnding(Collection)
     * @see         github.magyarzoli.UpdateFile#updateFile(Collection) updateFile(Collection)
     */
    default <T, C extends Collection<T>> void update(C update, UpdateCommand command)
    throws IOException {
        switch (command) {
            case UPDATE_FILE_TO_STARTING_CONTENTS -> updateFileWithStarting(update);
            case UPDATE_FILE_TO_ENDING_CONTENTS -> updateFileWithEnding(update);
            case UPDATE_FILE_WITH_CONTENTS_OVERWRITING -> updateFile(update);
            default -> throw new IllegalArgumentException();
        }
    }

    /**
     * {@code update} with three generic types: {@code K}, {@code V}, and {@code M}. The method takes two parameters:
     * a map of type {@code M} with keys of type {@code K} and values of type {@code V} named {@code update},
     * and an {@code UpdateCommand} enum named {@code command}. The method is declared to throw an {@code IOException}.
     * <ul>
     *     <li>The method now uses three generic types, {@code K}, {@code V}, and {@code M}, where {@code K} represents
     *     the type of keys in the map, {@code V} represents the type of values in the map,
     *     and {@code M} represents the map type itself.</li>
     *     <li>The {@code M} type parameter is constrained using the {@code extends} keyword with {@code Map<K, V>}.
     *     This constraint ensures that {@code M} must be a subtype of {@link java.util.Map Map} with keys of type
     *     {@code K} and values of type {@code V}, meaning the {@code update} parameter must be a map that can hold
     *     key-value pairs of the specified types.</li>
     *     <li>The switch statement operates on the {@code command} parameter.</li>
     *     <li>Depending on the value of {@code command}, the method performs different actions:</li>
     *     <li>If {@code command} is {@code UPDATE_FILE_TO_STARTING_CONTENTS},
     *     the method calls the {@code updateFileWithStarting} method, passing the {@code update} map as an argument.</li>
     *     <li>If {@code command} is {@code UPDATE_FILE_TO_ENDING_CONTENTS},
     *     the method calls the {@code updateFileWithEnding} method, passing the {@code update} map as an argument.</li>
     *     <li>If {@code command} is {@code UPDATE_FILE_WITH_CONTENTS_OVERWRITING},
     *     the method calls the {@code updateFile} method, passing the {@code update} map as an argument.</li>
     *     <li>If none of the above cases match, i.e., an unsupported {@code command} value is provided,
     *     the method throws an {@code IllegalArgumentException} to indicate an invalid command.</li>
     *     <li>The method is still defined as a default method, which means it can be overridden in implementing classes
     *     but provides a default implementation if not overridden.</li>
     *     <li>The updated generic type {@code M} allows this method to be used with different types of maps that have
     *     keys of type {@code K} and values of type {@code V}.</li>
     * </ul>
     * {@code update} with allows more flexibility in the types of maps that can be passed to the {@code update} method
     * while still ensuring type safety. For example, you can pass a {@code Map<Integer, String>} or a
     * {@code HashMap<String, Double>} to the {@code update} method, as long as they match the constraint {@code Map<K, V>},
     * where {@code K} and {@code V} represent the appropriate key and value types, respectively.
     * @param       update the desired content that you would upload.
     * @param       command can be specified in which way the file update takes place.
     * @param       <K> represents the type of keys in the map.
     * @param       <V> represents the type of values in the map.
     * @param       <M> this constraint ensures that must be a subtype of Map.
     * @throws      IOException throw an exception if implemented methods are not properly implemented.
     * @see         github.magyarzoli.UpdateFile.UpdateCommand UpdateCommand
     * @see         github.magyarzoli.UpdateFile#updateFileWithStarting(Map) updateFileWithStarting(Map)
     * @see         github.magyarzoli.UpdateFile#updateFileWithEnding(Map) updateFileWithEnding(Map)
     * @see         github.magyarzoli.UpdateFile#updateFile(Map) updateFile(Map)
     */
    default <K, V, M extends Map<K, V>> void update(M update, UpdateCommand command)
    throws IOException {
        switch (command) {
            case UPDATE_FILE_TO_STARTING_CONTENTS -> updateFileWithStarting(update);
            case UPDATE_FILE_TO_ENDING_CONTENTS -> updateFileWithEnding(update);
            case UPDATE_FILE_WITH_CONTENTS_OVERWRITING -> updateFile(update);
            default -> throw new IllegalArgumentException();
        }
    }

    /**
     * {@code update} method that takes an array of type {@code T} named {update} and a {@code functional} interface
     * {@code FileFunctional} as parameters. This functional interface seems to have a single abstract method called
     * {@code fileFunctional()}.
     * <ul>
     *     <li>This indicates that the method has a default implementation in the interface. Default methods were
     *     introduced in Java 8 and allow adding new methods to an interface without breaking the existing
     *     implementations of that interface.</li>
     *     <li>{@code <T>} This indicates that the method is a generic method with a type parameter {@code T}.</li>
     *     <li>This means the method does not return any value; it performs some action.</li>
     *     <li>The method takes an array of type {@code T} named {@code update} and an instance of the functional
     *     interface {@code FileFunctional} as parameters.</li>
     *     <li>The method calls the abstract method {@code fileFunctional()} on the provided instance of {@code FileFunctional}.
     *     Since {@code FileFunctional} is a functional interface, it is expected to have a single abstract method,
     *     which is implemented by the calling code outside of this method.</li>
     * </ul>
     * The purpose of the {@code update} method is to provide a mechanism for executing code provided by the caller
     * through the {@code FileFunctional} interface. The implementation of the {@code fileFunctional()} method can
     * contain custom logic for updating the file or performing other file-related operations. By calling the {@code update}
     * method with an array of type {@code T} and an instance of {@code FileFunctional}, the caller can effectively
     * execute their custom code within the context of the {@code update} method.
     * @param       update the desired content that you would upload.
     * @param       functional lambda function.
     * @param       <T> allows this method to be used with different types of arrays.
     * @throws      IOException throw an exception if implemented methods are not properly implemented.
     * @see         github.magyarzoli.FileFunctional#fileFunctional() fileFunctional()
     */
    default <T> void update(T[] update, FileFunctional functional)
    throws IOException {
        functional.fileFunctional();
    }

    /**
     * {@code update} method that takes a collection of type {@code C} containing elements of type {@code T}, and a
     * functional interface {@code FileFunctional} as parameters. This functional interface seems to have a single
     * abstract method called {@code fileFunctional()}.
     * <ul>
     *     <li>This indicates that the method has a default implementation in the interface. Default methods were
     *     introduced in Java 8 and allow adding new methods to an interface without breaking the existing
     *     implementations of that interface.</li>
     *     <li>{@code <T, C extends Collection<T>>} This indicates that the method is a generic method with two type
     *     parameters: {@code T} representing the type of elements in the collection, and {@code C}, which must be a
     *     subtype of {@link java.util.Collection Collection}{@code <T>}, representing the type of the collection itself.</li>
     *     <li>This means the method does not return any value; it performs some action.</li>
     *     <li>The method takes a collection of type {@code C} named update and an instance of the functional interface
     *     {@code FileFunctional} as parameters.</li>
     *     <li>The method calls the abstract method {@code fileFunctional()} on the provided instance of {@code FileFunctional}.
     *     Since {@code FileFunctional} is a functional interface, it is expected to have a single abstract method,
     *     which is implemented by the calling code outside of this method.</li>
     * </ul>
     * The purpose of the {@code update} method is to provide a mechanism for executing code provided by the caller through
     * the {@code FileFunctional} interface. The implementation of the {@code fileFunctional()} method can contain custom
     * logic for updating the file or performing other file-related operations based on the {@code update} collection.
     * By calling the {@code update} method with a collection of type {@code C} and an instance of {@code FileFunctional},
     * the caller can effectively execute their custom code within the context of the {@code update} method.
     * @param       update the desired content that you would upload.
     * @param       functional lambda function.
     * @param       <T> allows this method to be used with different types.
     * @param       <C> this constraint ensures that must be a subtype of Collection.
     * @throws      IOException throw an exception if implemented methods are not properly implemented.
     * @see         github.magyarzoli.FileFunctional#fileFunctional() fileFunctional()
     */
    default <T, C extends Collection<T>> void update(C update, FileFunctional functional)
    throws IOException {
        functional.fileFunctional();
    }

    /**
     * {@code update} method that takes a map of type {@code M} with keys of type {@code K} and values of type {@code V},
     * and a functional interface {@code FileFunctional} as parameters. This functional interface seems to have a single
     * abstract method called {@code fileFunctional()}.
     * <ul>
     *     <li>This indicates that the method has a default implementation in the interface. Default methods were
     *     introduced in Java 8 and allow adding new methods to an interface without breaking the existing
     *     implementations of that interface.</li>
     *     <li>{@code <K, V, M extends Map<K, V>>} This indicates that the method is a generic method with three type
     *     parameters: {@code K} representing the type of keys, {@code V} representing the type of values, and {@code M},
     *     which must be a subtype of {@link java.util.Map Map}{@code <K, V>}, representing the type of the map itself.</li>
     *     <li>This means the method does not return any value; it performs some action.</li>
     *     <li>The method takes a map of type {@code M} named update and an instance of the functional interface
     *     {@code FileFunctional} as parameters.</li>
     *     <li>The method calls the abstract method {@code fileFunctional()} on the provided instance of {@code FileFunctional}.
     *     Since {@code FileFunctional} is a functional interface, it is expected to have a single abstract method,
     *     which is implemented by the calling code outside of this method.</li>
     * </ul>
     * The purpose of the {@code update} method is to provide a mechanism for executing code provided by the caller
     * through the {@code FileFunctional} interface. The implementation of the {@code fileFunctional()} method can contain
     * custom logic for updating the file or performing other file-related operations based on the update map. By calling
     * the {@code update} method with a map of type {@code M} and an instance of {@code FileFunctional}, the caller can
     * effectively execute their custom code within the context of the {@code update} method.
     * @param       update the desired content that you would upload.
     * @param       functional lambda function.
     * @param       <K> represents the type of keys in the map.
     * @param       <V> represents the type of values in the map.
     * @param       <M> this constraint ensures that must be a subtype of Map.
     * @throws      IOException throw an exception if implemented methods are not properly implemented.
     * @see         github.magyarzoli.FileFunctional#fileFunctional() fileFunctional()
     */
    default <K, V, M extends Map<K, V>> void update(M update, FileFunctional functional)
    throws IOException {
        functional.fileFunctional();
    }

    /**
     * 3 named types of preparation.
     */
    enum UpdateCommand {

        /**
         * Update the file to the starting content, keeping any additional existing content.
         */
        UPDATE_FILE_TO_STARTING_CONTENTS,

        /**
         * Update the file to the ending content, keeping any additional existing content.
         */
        UPDATE_FILE_TO_ENDING_CONTENTS,

        /**
         * update the file, overwriting its contents.
         */
        UPDATE_FILE_WITH_CONTENTS_OVERWRITING,
    }
}