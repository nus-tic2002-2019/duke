package parse;

import task.*;

public class Parser {
    public Parser () {

    }
    public static String convertFileName (String name) {
        name = name.toLowerCase();
        name = name.trim();
        String[] parts = name.split(" ");
        String filename = " ";
        for (String part : parts) {
            filename = filename + "-" + part;
        }
        return filename.substring(2);
    }
    public static String[] fileLineBreak (String line){
        String[] parts = line.split("\\|", 4);

        return parts;
    }

    public static String taskToText (Task task) {
        String line = "";
        String completion = "";
        if (task.getCompleted()) {
            completion = "1";
        } else {
            completion = "0";
        }

        if (task instanceof Todo) {
            Todo todo = (Todo) task;
            line = "T" + " | " + completion
                       + " | " + todo.getContent();
        }
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            line = "D" + " | " + completion
                       + " | " + deadline.getContent()
                       + " | " + deadline.getDate();
        }
        if (task instanceof Event) {
            Event event = (Event) task;
            line = "E" + " | " + completion
                       + " | " + event.getContent()
                       + " | " + event.getTime();
        }
        return line;
    }

}
