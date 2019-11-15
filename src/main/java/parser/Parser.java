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
    private String validCommand, todoDescription, deadlineDescription, eventDescription, listIndex;
    private String[] getDeadlineDetails, getEventDetails, eventDateTime, eventStartEndTime;
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
        validCommand = textInputArr[0];
        if (validCommand.equals("list") || validCommand.equals("bye") || validCommand.equals("sort")) singleCommand = true;
        if (textInputArr.length < 2 && !singleCommand) throw new DukeException("unknown Command");
        switch(validCommand.toLowerCase()){
            case "done": //fallthrough as we are getting the index list.
            case "delete":
                listIndex = textInputArr[1];
                break;
            case "set":
                String[] listIndexPriority = textInputArr[1].split(" ", 2);
                listIndex = listIndexPriority[0];
                switch(listIndexPriority[1].toUpperCase()){
                    case "LOW":
                        taskPriority = Priority.LOW;
                        break;
                    case "MEDIUM":
                        taskPriority = Priority.MEDIUM;
                        break;
                    case "HIGH":
                        taskPriority = Priority.HIGH;
                }
            break;
            case "todo":
                todoDescription = textInputArr[1];
                break;
            case "deadline":
                getDeadlineDetails = textInputArr[1].split(" /by ", 2);
                if (getDeadlineDetails.length < 2) throw new DukeException("☹ OOPS!!! You did not specify a specific date/time for deadline. Please use /by yyyy-mm-dd");
                deadlineDescription = getDeadlineDetails[0];
                try{
                    deadlineDate = LocalDate.parse(getDeadlineDetails[1]);
                } catch (DateTimeParseException e){
                    throw new DukeException("Please use YYYY-MM-dd for Date format");
                }
                break;
            case "event":
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
                if (eventStartEndTime.length < 2) throw new DukeException("☹ OOPS!!! Missing time information. Please use Start Time - End Time e.g. 10:00-12:00");
                try{
                    eventStartTime = LocalTime.parse(eventStartEndTime[0]);
                    eventEndTime = LocalTime.parse(eventStartEndTime[1]);
                } catch (DateTimeParseException e){
                    throw new DukeException("Please use HH:mm for Time format");
                }
                break;
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



}
