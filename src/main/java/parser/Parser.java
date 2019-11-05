package parser;

import java.time.LocalDate;
import java.time.LocalDateTime;

import exception.DukeException;


public class Parser {
    private String validCommand, todoDescription, deadlineDescription, eventDescription, eventDate, listIndex;
    private String[] getDeadlineDetails;
    private String[] getEventDetails;
    //LocalDateTime eventDate;
    LocalDate deadlineDate;

    public Parser(String textInput) throws DukeException {
        boolean singleCommand = false;
        String[] textInputArr = textInput.split(" ",2);
        validCommand = textInputArr[0];
        if (validCommand.equals("list") || validCommand.equals("bye")) singleCommand = true;
        if (textInputArr.length < 2 && !singleCommand) throw new DukeException("missing Description");
        switch(validCommand){
            case "done":
            case "delete":
                listIndex = textInputArr[1];
                break;
            case "todo":
                todoDescription = textInputArr[1];
                break;
            case "deadline":
                getDeadlineDetails = textInputArr[1].split(" /by ", 2);
                if (getDeadlineDetails.length < 2) throw new DukeException("☹ OOPS!!! You did not specify a specific date/time for deadline. Please use /by yyyy-mm-dd");
                deadlineDescription = getDeadlineDetails[0];
                //strDeadlineDate
                deadlineDate = LocalDate.parse(getDeadlineDetails[1]);
                break;
            case "event":
               // if (textInputArr[1].isEmpty()) throw new DukeException(validCommand);
                getEventDetails = textInputArr[1].split(" /at ", 2);
                if (getEventDetails.length < 2) throw new DukeException("☹ OOPS!!! You did not specify a specific start/end date/time for event. Please use /at yyyy-mm-dd");
                eventDescription = getEventDetails[0];
                //eventDate = LocalDateTime.parse(getEventDetails[1]);
                eventDate = getEventDetails[1];
                break;
        }

    }

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

    public String getEventDate(){
        return eventDate;
    }

    public String getListIndex(){
        return listIndex;
    }



}
