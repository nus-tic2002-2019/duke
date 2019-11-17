package parse;

import task.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import UI.*;


public class Parser {

    private static Message ui = new Message();

    public Parser () {
    }

    public static String getStartTimeText (String text) {
        String start = text.substring(0, text.lastIndexOf("-"));
        return start;
    }
    public static LocalDateTime getStartTime (String text) {
        String atText = Parser.getStartTimeText(text);
        //System.out.println(text + "|" + atText);
        LocalDateTime at = Parser.getDateTime(atText);
        return at;
    }

    public static LocalDateTime getEndTime (String text) {
        String tillTimeText = text.substring(text.lastIndexOf("-") + 1);
        String atText = Parser.getStartTimeText(text);
        String datetime[] = atText.split(" ");
        String tillText = datetime[0].split(" ")[0] + " " + tillTimeText;

        LocalDateTime till = Parser.getDateTime(tillText);
        return till;
    }

    public static LocalDateTime getDateTime (String timeText) throws DateTimeParseException {
        LocalDateTime datetime = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H");
            datetime = LocalDateTime.parse(timeText, formatter);
        } catch (DateTimeParseException e) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
            datetime = LocalDateTime.parse(timeText, formatter);
        } catch (DateTimeException e) {
            //ui.dateTimeErrMessage();
        }
        return datetime;
    }

    public static LocalDate getDate (String dateText) throws DateTimeException {
        LocalDate dueDate = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dueDate = LocalDate.parse(dateText, formatter);
        } catch (DateTimeException e) {
            //ui.dateErrMessage();
        }
        return dueDate;
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
                       + " | " + event.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                       + "-" + event.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return line;
    }

}
