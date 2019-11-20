package main.duke.storage;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    void writeToSaveFile() throws FileNotFoundException {
        Storage s = new Storage("data/test_duke.txt");  //write to separate file for testing.
        File f = s.writeToSaveFile("");
        Scanner scanner = new Scanner(f);
        assertFalse(scanner.hasNext());                         //file should be empty.
        f = s.writeToSaveFile(
                "1: [E][✓] tp visit (at: 2019-11-19T12:00)\n" +
                        "2: [T][❌] dance\n");
        scanner = new Scanner(f);
        assertEquals(scanner.nextLine(), "1: [E][✓] tp visit (at: 2019-11-19T12:00)");
        assertEquals(scanner.nextLine(), "2: [T][❌] dance");
    }
}