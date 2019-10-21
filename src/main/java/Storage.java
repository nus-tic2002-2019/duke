import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String delimiter = " \\| ";
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> load() throws DukeException{
        ArrayList<Task> tasks = new ArrayList<Task>();
        File file = new File(filepath);
        String[] taskInfo;
        Task task = null;
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                taskInfo = sc.nextLine().split(delimiter);
                switch (taskInfo[0]) {
                    case "T":
                        task = new Todo(taskInfo[2]);
                        break;
                    case "E":
                        task = new Event(taskInfo[2], convertToDateTime(taskInfo[3]));
                        break;
                    case "D":
                        task = new Deadline(taskInfo[2], convertToDateTime(taskInfo[3]));
                        break;
                }
                if(taskInfo[1].equals("1"))
                    task.markAsDone();
                tasks.add(task);
            }
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! File not found or error in file");
        }
        return tasks;
    }

    public void save(Tasklist tasks) throws DukeException{
        ArrayList<Task> tasklist = tasks.getTasks();
        File file = new File(filepath);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for (int i=0; i<tasklist.size(); i++) {
                writer.write(tasklist.get(i).saveTask()+"\n");
            }
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! File not found or error in file");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new DukeException("☹ OOPS!!! File not found or error in file");
            }
        }
    }
    public static LocalDateTime convertToDateTime(String dateTime) throws DukeException{
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(dateTime, format);
            return date;
        } catch (DateTimeParseException e) {
            throw new DukeException("The format of the date and time must be in this format: dd/mm/yyyy hhmm");
        }
    }
}
