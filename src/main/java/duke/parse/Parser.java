package duke.parse;

import duke.task.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.UI.*;


public class Parser {

    //private static Message ui = new Message();

    public Parser () {
    }

    /**
     * From the String text
     * get the starttime of an event
     * @param text
     * @return
     */
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

    /**
     * From the String text
     * get the endtime of an event
     * @param text
     * @return
     */
    public static LocalDateTime getEndTime (String text) {
        String tillTimeText = text.substring(text.lastIndexOf("-") + 1);
        String atText = Parser.getStartTimeText(text);
        String datetime[] = atText.split(" ");
        String tillText = datetime[0].split(" ")[0] + " " + tillTimeText;

        LocalDateTime till = Parser.getDateTime(tillText);
        return till;
    }

    /**
     * From String get the Date and Time
     * Stored/return in LocalDateTime type
     * @param timeText
     * @return
     * @throws DateTimeParseException
     */

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

    /**
     * From String parse the Date
     * @param dateText
     * @return
     * @throws DateTimeException
     */
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

    /**
     * Convert user type "Li Shihao" to
     * return "li-shihao" as part of filename in the path
     * @param name
     * @return
     */
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

    /**
     * After get the line from txt file
     * separate the sentence by "|"
     * in java RE "|" & "\" is used, so must use "\\|"
     * @param line
     * @return
     */
    public static String[] fileLineBreak (String line){
        String[] parts = line.split("\\|", 4);

        return parts;
    }

    /**
     * From the attributes of Task
     * convert to the text format that will be stored
     * later in the txt file
     * @param task
     * @return
     */
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
