package storage;

import main.java.duke.storage.Storage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import main.java.duke.storage.Storage.StorageOperationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    @TempDir
    public static Path testFolder;

    private static final String TEST_DATA_FOLDER = "data";


    @Test
    public void load_invalidFormat_exceptionThrown() throws Exception {
        // The file contains valid txt data, but does not match the task list format
        Storage storage = getStorage("InvalidDuke.txt");
        assertThrows(StorageOperationException.class, () -> storage.load());
    }

    private Storage getStorage(String fileName) throws Exception {
        return new Storage(TEST_DATA_FOLDER + "/" + fileName);
    }
}
