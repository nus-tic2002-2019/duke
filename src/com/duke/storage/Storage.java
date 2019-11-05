package com.duke.storage;

import com.duke.exception.IllegalValueException;
import com.duke.task.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {

    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "duke.txt";

    public final Path path;

    public Storage(){
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public Storage(String filePath){
        path= Paths.get(filePath);
    }

    public void save(TaskList taskList) throws StorageOperationException{

        try{
            List<String> encodedTaskList=TaskListEncorder.encodeTaskList(taskList);
            Files.write(path,encodedTaskList);
        }catch (IOException ioe)
        {
            throw new StorageOperationException("Saving went wrong");
        }

    }

    public TaskList load() throws StorageOperationException{

        if (!Files.exists(path) || !Files.isRegularFile(path)) {
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
     * Signals that some error has occured while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }


}
