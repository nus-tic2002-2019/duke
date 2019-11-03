package parser;

import exception.DukeException;

public class Parser {
    private String validCommand, todoDescription, deadlineDescription, eventDescription, deadlineDate, eventDate, listIndex;
    private String[] getDeadlineDetails;
    private String[] getEventDetails;

    public Parser(String textInput) throws DukeException {
        String[] textInputArr = textInput.split(" ",2);
        validCommand = textInputArr[0];
        if (textInputArr.length < 2 && !validCommand.equals("list")) throw new DukeException(validCommand);
        switch(validCommand){
            case "done":
            case "delete":

                listIndex = textInputArr[1];
                break;
            case "todo":

                todoDescription = textInputArr[1];
                break;
            case "deadline":

                getDeadlineDetails = textInputArr[1].split("/by", 2);
                if (getDeadlineDetails.length < 2) throw new DukeException("/by");
                deadlineDescription = getDeadlineDetails[0];
                deadlineDate = getDeadlineDetails[1];
                break;
            case "event":
               // if (textInputArr[1].isEmpty()) throw new DukeException(validCommand);
                getEventDetails = textInputArr[1].split("/at", 2);
                if (getEventDetails.length < 2) throw new DukeException("/at");
                eventDescription = getEventDetails[0];
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

    public String getDeadlineDate(){
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
