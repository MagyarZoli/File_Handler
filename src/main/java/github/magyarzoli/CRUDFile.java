package github.magyarzoli;

/**
 * Create, Read, Update, Delete file interface
 * Collector interface, single implementation of 4 interfaces
 * Use, when the inherited class can create, read, update and delete at the same time.
 * @since       1.0
 * @author      <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public interface CRUDFile
extends CreateFile, ReadFile, UpdateFile, DeleteFile {}