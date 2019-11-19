package duke.storage;
import duke.parse.Parser;
import duke.task.*;
import duke.UI.Message;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

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

    /**
     * Read from disk where txt file is stored for user
     * understand the task type and parameters of each line
     * push into TempTaskList
     * @param to
     * @throws FileNotFoundException
     */
    public void read(TempTaskList to) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String[] linewords = Parser.fileLineBreak(s.nextLine());
            assert linewords.length < 2;
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

    /**
     * Get from TempTaskList(often after updating), write into the file
     *
     * @param from
     * @throws IOException
     */
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
