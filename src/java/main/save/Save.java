/*
Save.java
define Save task.
*/

package save;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.*;

import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class Save {
    /*
    constructor of loadtask object.
    @param description loaded the task.
    */
    public List<Task> loadTasks() throws IOException {
        List<Task> loadedTasks = new ArrayList<>();

        List<String> lines = getLines();
        for (String line : lines) {
            if (line.trim().isEmpty()) { //ignore empty lines
                continue;
            }
            loadedTasks.add(createTask(line)); //convert the line to a task and add to the list
        }
        return loadedTasks;
    }

    private Task createTask(String line) {
        Task task = null;
        String[] s = line.split("\\|");
        if (s[0].trim().equals("T")) {
            task = new Todo(" " + s[2].trim());
            if ((1 == Integer.parseInt(s[1].trim()))) {
                task.markAsDone();
            }
        } else if (s[0].trim().equals("D")) {
            task = new Deadline(s[2], LocalDate.parse(s[3].trim()));
            if ((1 == Integer.parseInt(s[1].trim()))) {
                task.markAsDone();
            }
        } else if (s[0].trim().equals("E")) {
            task = new Event(s[2], LocalDate.parse(s[3].trim()));
            if ((1 == Integer.parseInt(s[1].trim()))) {
                task.markAsDone();
            }
        } else {
            System.out.println("Error");
        }
        return task;
    }

    private List<String> getLines() throws IOException {
        List<String> lines = new ArrayList<String>();
        Path path = Paths.get("src/java/main/data/duke.txt");
        BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
        {
            String currentLine = null;
            while ((currentLine = reader.readLine()) != null) {//while there is content on the current line
//                System.out.println(currentLine); // print the current line
                lines.add(currentLine);
            }
        }
        return lines;
    }

    /*
    constructor of autosave object.
    @param description save the file to mentioned path.
    */
    public void autoSave(List<Task> tasks) throws IOException {

        try {
            Files.createFile(Paths.get("src/java/main/data/duke.txt"));
        } catch (FileAlreadyExistsException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter fw = new FileWriter(String.valueOf(Paths.get("src/java/main/data/duke.txt")));
        Scanner s = new Scanner(Paths.get("src/java/main/data/duke.txt"));
        for (Task task : tasks) {
            if (s.hasNext()) {
                fw.write("\r\n");
            }
            fw.write(task.save());
        }
        fw.close();


    }

    public Save() throws IOException {
    }
}
