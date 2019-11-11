package basic;

import task.Task;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the file used to store address book data.
 */
public class Storage {

    /**
     * Loads the {@code basic.Duke} data from this storage file, and then returns it.
     */
    ArrayList<String> load() {
        List<String> lines = Collections.emptyList();
        try {
            lines =
                    Files.readAllLines(Paths.get("C:/Users/HUI YUAN-USER/Desktop/TIC2002/duke-master/duke/data/duke.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<String>(lines);
    }

    /**
     * Saves the {@code basic.Duke} data to the storage file.
     *
     * @param txt tasks in an array list
     */
    void save(ArrayList<Task> txt) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("C:/Users/HUI YUAN-USER/Desktop/TIC2002/duke-master/duke/data/duke.txt"), StandardCharsets.UTF_8))) {
            for (Task task : txt) {
                writer.write(task.toString());
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
