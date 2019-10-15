package storage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StorageFile {

    private static final String DEFAULT_STORAGE_FILEPATH = "data/duke.txt";

    private final Path path;

    public StorageFile() {
        this.path = Paths.get(DEFAULT_STORAGE_FILEPATH);
    }

    public StorageFile(String filePath) throws InvalidStorageFilePathException, FilePathNotFound {
        this.path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
        if(!FileExist(path)){
            throw new FilePathNotFound("Storage file not found.");
        }
    }

    public static boolean isValidPath(Path filePath) throws InvalidStorageFilePathException {
        return filePath.toString().endsWith(".txt");
    }

    public static boolean FileExist(Path path){
        return (Files.exists(path));
    }

    public static class FilePathNotFound extends Exception{
        FilePathNotFound(String message){
            super(message);
        }
    }

    public static class InvalidStorageFilePathException extends Exception{
        InvalidStorageFilePathException(String message){
            super(message);
        }
    }
}
