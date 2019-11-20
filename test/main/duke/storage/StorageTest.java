package main.duke.storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    void writeToSaveFile() {
        Storage s = new Storage("data/test_duke.txt");  //write to separate file for testing.
    }
}