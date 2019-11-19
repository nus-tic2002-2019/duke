package tic2002.task;

import tic2002.enumerations.Priority;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents Event sub-class, inherited from Task class.
 */
public class Event extends Task {
    //Declare constant variables
    protected final String STRING_AT = "at";

    //Declare variables
    protected String eventAtString;
    protected LocalDateTime eventDateTime;

    //Constructor
    public Event(String taskDescription, String taskTime) {
        super(taskDescription);
        eventAtString = taskTime;
        typeIdt = CHAR_EVENT;
    }

    public Event(String taskDescription, LocalDateTime taskDateTime) {
        super(taskDescription);
        eventDateTime = taskDateTime;
        typeIdt = CHAR_EVENT;
        isDateTimeFormat = true;
    }

    //Getter
    /**
     * Combine and return String for writing into file, for Event class.
     *
     * @return String
     */
    public String printToFile() {
        String tempString = super.printToFile() + CHAR_SEPARATOR_WRITE;

        if (isDateTimeFormat) {
            tempString += DateTimeFormatter.ofPattern(DATE_TIME_FORMAT).format(eventDateTime);
        } else {
            tempString += eventAtString;
        }

        return tempString;
    }

    @Override
    public String toString() {
        setPreOutput();
        preOutput += " (" + STRING_AT + ": ";

        if (isDateTimeFormat) {
            preOutput += DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_OUTPUT).format(eventDateTime);
        } else {
            preOutput += eventAtString;
        }

        preOutput += ")";

        if (taskPriority == Priority.NONE) {
            return preOutput;
        } else {
            return preOutput + " " + priorityOutput;
        }
    }
}