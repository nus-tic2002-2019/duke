package storage;
import parse.Parser;
import task.*;

import java.io.*;
import java.util.Scanner;

public class Storage {

    private static final String DEFAULT_NAME = "demo";

    private File file;

    public Storage () throws IOException {
        this(DEFAULT_NAME);
    }
    public Storage (String name) throws IOException {
        String path = "data/taskSheet_" + name + ".txt";
        file = new File(path);
    }

    public File get() {
        return file;
    }

    public void read(TempTaskList to) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String[] linewords = Parser.fileLineBreak(s.nextLine());
            String taskType = linewords[0].trim();
            String isCompleted = linewords[1].trim();
            String content = linewords[2].trim();

            
            if (taskType.equals("T")) {
                Todo task = new Todo(content);
                if (isCompleted.equals("1"))
                    task.setcompleted();
                to.add(task);
            }
            if (taskType.equals("D")) {
                String time = linewords[3].trim();
                Deadline task = new Deadline(content, time);
                if (isCompleted.equals("1"))
                    task.setcompleted();
                to.add(task);
            }
            if (taskType.equals("E")) {
                String time = linewords[3].trim();
                Event task = new Event(content, time);
                if (isCompleted.equals("1"))
                    task.setcompleted();
                to.add(task);
            }

        }
    }

    public void write(TempTaskList from) {

    }

}
