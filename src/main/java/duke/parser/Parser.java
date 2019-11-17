package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import duke.exception.DukeException;
import duke.tasklist.Priority;

/**
 * The Parser class make sense of the various commands input by the user. It will look for the key details
 * necessary for each task and if they are missing, it will provide information regarding the missing details
 */

public class Parser {
    private String command;
    private String taskDescription;
    private String todoDescription;
    private String deadlineDescription;
    private String eventDescription;
    private String listIndex;
    private LocalDate deadlineDate, eventDate;
    private LocalTime eventStartTime, eventEndTime;
    private Priority taskPriority;
    /**
     * The Parser constructor takes a String parameter and looks for the key details required for each tasks
     * It will look for the following Command Keywords:
     * list, bye, sort, done, delete, find, set, todo, deadline, event
     * @param textInput is the input from the user
     * @throws DukeException if the command are not list, bye or sort for single word command.
     */
    public Parser(String textInput) throws DukeException{
        boolean isSingleCommand = false;
        String[] textInputArr = textInput.split(" ",2);
        command = textInputArr[0].toLowerCase();
        if (command.equals("list") ||
                command.equals("bye") ||
                command.equals("overdue") ||
                command.equals("sort") ||
                command.equals("help")) {
            isSingleCommand = true;
        }
        if (textInputArr.length < 2 && !isSingleCommand) {
            throw new DukeException("    I'm not sure what is that. Type \"help\" to see what I can understand");
        }
        switch(command.toLowerCase()){
        case "undone":
            //Fallthrough
        case "done":
            //Fallthrough
        case "delete":
            listIndex = textInputArr[1];
            break;
        case "find":
            if (textInputArr.length < 2 ) {
                throw new DukeException("    Please indicate a keyword to search in the list");
            }
            taskDescription = textInputArr[1];
            break;
        case "set":
            String[] listIndexPriority = textInputArr[1].split(" ", 2);
            listIndex = listIndexPriority[0];
            strToPriority(listIndexPriority[1]);
            break;
        case "todo":
            getTodoDetails(textInputArr[1]);
            break;
        case "deadline":
            getDeadlineDetails(textInputArr[1]);
            break;
        case "event":
            getEventDetails(textInputArr[1]);
            break;
        default:
            if (!isSingleCommand) {
                throw new DukeException("    I'm not sure what is that. Type \"help\" to see what I can understand");
            }
        }

    }

    private void getTodoDetails(String inputDetails) throws DukeException{
        String[] getTodoDetails = inputDetails.split(" /priority ", 2);
        if (getTodoDetails.length < 2)
            throw new DukeException("    ☹ OOPS!!! We are missing priority!\n" + "    Please type \"todo description /priority level(high, medium or low)\"");
        todoDescription = getTodoDetails[0];
        strToPriority(getTodoDetails[1]);
    }

    private void getDeadlineDetails(String inputDetails) throws DukeException{
        String[] getDeadlineDetails = inputDetails.split(" /priority ", 2);
        //extract deadline Description
        if (getDeadlineDetails.length < 2)
            throw new DukeException("    ☹ OOPS!!! There are missing or incorrect details!\n" + "    Please type \"deadline description /priority level(high, medium or low) /by yyyy-mm-dd\"");
        deadlineDescription = getDeadlineDetails[0];
        //extract deadline priority
        String[] getDeadlinePriority = getDeadlineDetails[1].split(" /by ", 2);
        strToPriority(getDeadlinePriority[0]);
        //extract deadline date
        String getDeadlineDate = getDeadlinePriority[1];
        if (getDeadlineDate.isEmpty())
            throw new DukeException("    ☹ OOPS!!! You did not specify a specific date/time for deadline. Please use /by yyyy-mm-dd");
        try{
            deadlineDate = LocalDate.parse(getDeadlineDate);
        } catch (DateTimeParseException e){
            throw new DukeException("    Please use YYYY-MM-dd for Date format");
        }
    }

    private void getEventDetails(String inputDetails) throws DukeException{
        String[] getEventDetails = inputDetails.split(" /priority ", 2); // separate Event Description from Priority, Date and Time
        if (getEventDetails.length < 2) {
            throw new DukeException("    ☹ OOPS!!! There are missing or incorrect details!\n" +
                    "    Please type as follows:\n" +
                    "    \"event description /priority level(high, medium or low) /at yyyy-mm-dd Start time (HH:mm) - End time (HH:mm)\"");
        }
        eventDescription = getEventDetails[0];
        String[] getEventPriority = getEventDetails[1].split(" /at ", 2);
        strToPriority(getEventPriority[0]);

        //separate event Date and Time
        String[] eventDateTime = getEventPriority[1].split(" ", 2);
        if (eventDateTime.length < 2)
            throw new DukeException ("    ☹ OOPS!!! We are missing date and time. Please use /at YYYY-mm-dd Start Time(HH:mm) - End Time(HH:mm)");
        try {
            eventDate = LocalDate.parse(eventDateTime[0]);
        } catch (DateTimeParseException e){
            throw new DukeException("    Please use YYYY-MM-dd for Date format");
        }
        //separate Start and End Time
        String[] eventStartEndTime = eventDateTime[1].split(" - ", 2);
        if (eventStartEndTime.length < 2)
            throw new DukeException("  ☹ OOPS!!! Missing time information. Please use Start Time - End Time e.g. 10:00 - 12:00");
        try{
            eventStartTime = LocalTime.parse(eventStartEndTime[0]);
            eventEndTime = LocalTime.parse(eventStartEndTime[1]);
            if (eventStartTime.compareTo(eventEndTime) > 0)
                throw new DukeException("    Your end time cannot be earlier than your start time");
        } catch (DateTimeParseException e){
            throw new DukeException("    Please use HH:mm for Time format");
        }
    }

    private void strToPriority(String strTaskPriority) throws DukeException{
        try {
            taskPriority = Priority.valueOf(strTaskPriority.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new DukeException("    Please use high, medium or low as priority level");
        }
    }

    /**
     * The getCommand method return the command which is understood by Duke
     */
    public String getCommand(){
        return command;
    }
    /**
     * The getTodoDescription method return the Description for Todo task after parsing the command
     */
    public String getTodoDescription(){
        return todoDescription;
    }
    /**
     * The getDeadlineDescription method return the Description for Deadline task after parsing the command
     */
    public String getDeadlineDescription(){
        return deadlineDescription;
    }
    /**
     * The getDeadlineDate method return the date for Deadline task after parsing the command
     */
    public LocalDate getDeadlineDate(){
        return deadlineDate;
    }
    /**
     * The getEventDescription method return the Description for Event task after parsing the command
     */
    public String getEventDescription(){
        return eventDescription;
    }
    /**
     * The getEventDate method return the date for Event task after parsing the command
     */
    public LocalDate getEventDate(){
        return eventDate;
    }
    /**
     * The getEventStartTime method return the Start Time for Event task after parsing the command
     */
    public LocalTime getEventStartTime(){return eventStartTime;};
    /**
     * The getEventEndTime method return the End Time for Event task after parsing the command
     */
    public LocalTime getEventEndTime(){return eventEndTime;};
    /**
     * The getListIndex method return the Index for the list after parsing the command
     */
    public String getListIndex(){
        return listIndex;
    }
    /**
     * The getTaskPriority method return the priority for the task after parsing the command
     */
    public Priority getTaskPriority(){return taskPriority;}
    /**
     * The getTaskDescription method return the description for each task type after parsing the command
     */
    public String getTaskDescription(){return taskDescription;}

}
