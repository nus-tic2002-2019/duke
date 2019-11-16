package storage;
import parse.Parser;
import task.*;
import UI.Message;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.stream.Stream;
import parse.Parser;

public class Storage {

    private static final String DEFAULT_NAME = "demo";

    private File file;
    private String path;
    private Message ui = new Message();

    public Storage () throws IOException {
        this(DEFAULT_NAME);
    }
    public Storage (String name) throws IOException {
        path = "data/taskSheet_" + name + ".txt";
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
                    task.setCompleted();
                to.add(task);
            }
            if (taskType.equals("D")) {
                LocalDate time = Parser.getDate(linewords[3].trim());
                Deadline task = new Deadline(content, time);
                if (isCompleted.equals("1"))
                    task.setCompleted();
                to.add(task);
            }
            if (taskType.equals("E")) {
                String time = linewords[3].trim();
                LocalDateTime at = Parser.getStartTime(time);
                LocalDateTime till = Parser.getEndTime(time);
                Event task = new Event(content, at, till);
                if (isCompleted.equals("1"))
                    task.setCompleted();
                to.add(task);
            }

        }
    }

    public void write(TempTaskList from) throws IOException{
        FileWriter fw = new FileWriter(file, false);
        try {
            for (int i = 0; i < from.size(); ++i) {
                fw.write(Parser.taskToText(from.get(i)));
                fw.write(System.lineSeparator());
            }
        } catch (IOException e) {
            ui.errorFileMessage();
        }
        fw.close(); // 🤦 spent ton of time because didn't put this
    }
}
