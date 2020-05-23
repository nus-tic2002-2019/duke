package duke.storage;

import duke.task.TaskList;
import duke.others.Utility;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Represents the file and its corresponding methods used to store task list data.
 */
public class Storage {
    protected String path;
    protected String data;
    protected boolean isEmpty;

    /**
     * The file used to store task list data.
     *
     * @param filePath is the directory of the data.txt file.
     */
    public Storage(String filePath) {
        this.path = filePath;
        this.data = "";
        this.isEmpty = false;
    }

    /**
     * Loads the data from this storage and returns it
     *
     * @return the data in String.
     * @throws FileNotFoundException if the path is not valid
     */

    public String load() throws FileNotFoundException {
        File f = new File(this.path);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            this.data = this.data + s.nextLine() + System.lineSeparator();
        }
        if (this.data.length() > 0) {
            this.isEmpty = true;
        }
        return this.data;
    }

    /**
     * Saves Task List data to storage file.
     *
     * @param tasks is the Task List to be saved.
     * @throws IOException if there are errors writing the data to the storage file.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(new File(this.path));
        int index = 0;
        for (int i = 0; i < tasks.size(); ++i) {
            String input = index + ";" + Utility.constructInput(tasks.get(i));
            writer.write(input + System.lineSeparator());
            index++;
        }
        writer.close();
    }

    /**
     * Appends data to the storage file. Used mainly when new tasks are created.
     *
     * @param textToAppend is the task data in String
     * @throws IOException if there are errors writing the file
     */
    public void append(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(this.path, true);
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

    /**
     * Updates a specific line of data (a.k.a task data). Used mainly when changes are made to existing tasks.
     *
     * @param n is the index of the record.
     * @param input is the new data.
     * @throws IOException if there are errors writing the file.
     */
    public void updateLine(int n, String input) throws IOException {
        Path path = Paths.get(this.path);
        String content = new String(Files.readAllBytes(path));
        String line = readLine(n);
        content = content.replaceAll(line, input);
        Files.write(path, content.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Read a specific line of data/task data in the storage.
     *
     * @param n is the index of the record.
     * @return a specific line of data/task data.
     * @throws IOException if the line of data is not found.
     */
    private String readLine(int n) throws IOException {
        return Files.readAllLines(Paths.get(this.path)).get(n);
    }

}
