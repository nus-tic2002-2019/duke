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
    ArrayList<String> load() throws FileNotFoundException, UnsupportedEncodingException {
        List<String> lines = Collections.emptyList();
        Boolean isFileCreated = false;
        while (isFileCreated == false) {

            try {
                isFileCreated = true;
                lines =
                        Files.readAllLines(Paths.get(System.getProperty("user.dir") + "/duke.txt"), StandardCharsets.UTF_8);
            } catch (IOException e) {
                isFileCreated = true;
                PrintWriter writer = new PrintWriter(System.getProperty("user.dir") + "/duke.txt", "UTF-8");
                writer.println("The first line");
                writer.println("The second line");
                writer.close();
            }
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
                new FileOutputStream(System.getProperty("user.dir")+"/duke.txt"), StandardCharsets.UTF_8))) {
            for (Task task : txt) {
                writer.write(task.toString());
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
