package main.java.duke.storage;

import main.java.duke.exception.IllegalValueException;
import main.java.duke.task.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {

    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "duke.txt";

    public final Path path;

    /**
     * @throws InvalidStorageFilePathException if the default path is invalid
     */
    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * @throws InvalidStorageFilePathException if the given file path is invalid
     */
    public Storage(String filePath) throws InvalidStorageFilePathException{
        path= Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }

    /**
     * Returns true if the given path is acceptable as a storage file.
     * The file path is considered acceptable if it ends with '.txt'
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    /**
     * Saves the {@code taskList} data to the storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void save(TaskList taskList) throws StorageOperationException{
        try{
            List<String> encodedTaskList=TaskListEncorder.encodeTaskList(taskList);
            Files.write(path,encodedTaskList);
        }catch (IOException ioe)
        {
            throw new StorageOperationException("Saving went wrong");
        }

    }


    /**
     * Loads the {@code taskList} data from this storage file, and then returns it.
     * Returns an empty {@code taskList} if the file does not exist, or is not a regular file.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public TaskList load() throws StorageOperationException, IOException {

        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
            return new TaskList();
        }
        try {
            return TaskListDecoder.decodeTaskList(Files.readAllLines(path));
        }catch (IOException ioe){
            throw new StorageOperationException("Loading went wrong");
        }catch (IllegalValueException ive){
            throw new StorageOperationException("File contains incorrect format.");
        }

    }

    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    public static class InvalidStorageFilePathException extends IllegalValueException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }


    /**
     * Signals that some error has occured while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }


}
