package parser;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import exception.DukeException;
import tasklist.Priority;

/**
 * The Parser class make sense of the various commands input by the user. It will look for the key details
 * necessary for each task and if they are missing, it will provide information regarding the missing details
 */

public class Parser {
    private String validCommand, taskDescription, todoDescription, deadlineDescription, eventDescription, listIndex, getDeadlineDate;
    private String[] getDeadlineDateAndPriority;
    private String[] getEventDateAndPriority;
    public LocalDate deadlineDate, eventDate;
    public LocalTime eventStartTime, eventEndTime;
    public Priority taskPriority;
    /**
     * The Parser constructor takes a String parameter and looks for the key details required for each tasks
     * @param textInput is the input from the user
     */
    public Parser(String textInput) throws DukeException{
        boolean singleCommand = false;
        String[] textInputArr = textInput.split(" ",2);
        validCommand = textInputArr[0].toLowerCase();
        if (validCommand.equals("list") || validCommand.equals("bye") || validCommand.equals("sort")) singleCommand = true;
        if (textInputArr.length < 2 && !singleCommand) throw new DukeException("unknown Command");
        switch(validCommand.toLowerCase()){
            case "done": //fallthrough as we are getting the index list.
            case "delete":
                listIndex = textInputArr[1];
                break;
            case "find":
                if (textInputArr.length < 2 )
                    throw new DukeException("Please indicate a keyword to search in the list");
                taskDescription = textInputArr[1];
                break;
            case "set":
                String[] listIndexPriority = textInputArr[1].split(" ", 2);
                listIndex = listIndexPriority[0];
                taskPriority = taskPriority.valueOf(listIndexPriority[1].toUpperCase());

            break;
            case "todo":
                String[] getTodoDetails = textInputArr[1].split(" /priority ", 2);
                todoDescription = getTodoDetails[0];
                if (getTodoDetails[1].isEmpty())
                    taskPriority = Priority.LOW;
                else
                    taskPriority = taskPriority.valueOf(getTodoDetails[1].toUpperCase());
                break;
            case "deadline":

                String[] getDeadlineDetails = textInputArr[1].split(" /priority ", 2);
                //extract deadline Description
                if (getDeadlineDetails.length < 2)
                    throw new DukeException("    ☹ OOPS!!! There are missing or incorrect details!\n" + "    Please type \"deadline description /priority level /by yyyy-mm-dd\"");
                deadlineDescription = getDeadlineDetails[0];
                //extract deadline priority
                String[] getDeadlinePriority = getDeadlineDetails[1].split(" /by ", 2);
                try {
                      taskPriority = taskPriority.valueOf(getDeadlinePriority[0].toUpperCase());
                    } catch (IllegalArgumentException e){
                        throw new DukeException("    Please use high, medium or low as priority level");
                    }
                //extract deadline date
                getDeadlineDate = getDeadlinePriority[1];
                if (getDeadlineDate.isEmpty())
                    throw new DukeException("    ☹ OOPS!!! You did not specify a specific date/time for deadline. Please use /by yyyy-mm-dd");
                try{
                    deadlineDate = LocalDate.parse(getDeadlineDate);
                } catch (DateTimeParseException e){
                    throw new DukeException("    Please use YYYY-MM-dd for Date format");
                }
                break;

            case "event":

                String[] getEventDetails = textInputArr[1].split(" /priority ", 2); // separate Event Description from Priority, Date and Time
                if (getEventDetails.length < 2)
                    throw new DukeException("    ☹ OOPS!!! There are missing or incorrect details!\n" + "    Please type as follows:\n" + "    \"event description /priority level(high, medium or low) /at yyyy-mm-dd Start time (HH:mm) - End time (HH:mm)\""); //throw exception if missing date & time
                eventDescription = getEventDetails[0];
                String[] getEventPriority = getEventDetails[1].split(" /at ", 2);
                try {
                    taskPriority = taskPriority.valueOf(getEventPriority[0].toUpperCase());
                } catch (IllegalArgumentException e){
                    throw new DukeException("    Please use high, medium or low as priority level");
                }

                //separate event Date and Time
                String[] eventDateTime = getEventPriority[1].split(" ", 2);
                if (eventDateTime.length < 2) throw new DukeException ("    ☹ OOPS!!! We are missing date and time. Please use /at YYYY-mm-dd Start Time(HH:mm) - End Time(HH:mm)");

                try {
                    eventDate = LocalDate.parse(eventDateTime[0]);
                } catch (DateTimeParseException e){
                    throw new DukeException("    Please use YYYY-MM-dd for Date format");
                }
                //separate Start and End Time
                String[] eventStartEndTime = eventDateTime[1].split(" - ", 2);
                if (eventStartEndTime.length < 2) throw new DukeException("  ☹ OOPS!!! Missing time information. Please use Start Time - End Time e.g. 10:00 - 12:00");
                try{
                    eventStartTime = LocalTime.parse(eventStartEndTime[0]);
                    eventEndTime = LocalTime.parse(eventStartEndTime[1]);
                    if (eventStartTime.compareTo(eventEndTime) > 0)
                        throw new DukeException("    Your end time cannot be earlier than your start time");
                } catch (DateTimeParseException e){
                    throw new DukeException("    Please use HH:mm for Time format");
                }
                break;

                /*
                getEventDetails = textInputArr[1].split(" /at ", 2); // separate Event Details from Date and Time
                if (getEventDetails.length < 2) throw new DukeException("☹ OOPS!!! You did not specify a specific date for event. Please use /at yyyy-mm-dd"); //throw exception if missing date & time
                eventDescription = getEventDetails[0];

                //separate event Date and Time
                eventDateTime = getEventDetails[1].split(" ", 2);
                if (eventDateTime.length < 2) throw new DukeException ("☹ OOPS!!! You did not specify a specific start and end time for event. Please use Start Time - End Time e.g. 10:00-12:00");

                try {
                    eventDate = LocalDate.parse(eventDateTime[0]);
                } catch (DateTimeParseException e){
                    throw new DukeException("Please use YYYY-MM-dd for Date format");
                }
                //separate Start and End Time
                eventStartEndTime = eventDateTime[1].split(" - ",2);
                if (eventStartEndTime.length < 2) throw new DukeException("☹ OOPS!!! Missing time information. Please use Start Time - End Time e.g. 10:00 - 12:00");
                try{
                    eventStartTime = LocalTime.parse(eventStartEndTime[0]);
                    eventEndTime = LocalTime.parse(eventStartEndTime[1]);
                } catch (DateTimeParseException e){
                    throw new DukeException("Please use HH:mm for Time format");
                }
                break;*/
        }

    }
    /**
     * The getValidCommand method return the command which is understood by Duke
     */
    public String getValidCommand(){
        return validCommand;
    }

    public String getTodoDescription(){
        return todoDescription;
    }

    public String getDeadlineDescription(){
        return deadlineDescription;
    }

    public LocalDate getDeadlineDate(){
        return deadlineDate;
    }

    public String getEventDescription(){
        return eventDescription;
    }

    public LocalDate getEventDate(){
        return eventDate;
    }

    public LocalTime getEventStartTime(){return eventStartTime;};

    public LocalTime getEventEndTime(){return eventEndTime;};

    public String getListIndex(){
        return listIndex;
    }

    public Priority getTaskPriority(){return taskPriority;}

    public String getTaskDescription(){return taskDescription;}



}
