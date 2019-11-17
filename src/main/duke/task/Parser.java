package main.duke.task;

import main.duke.command.*;
import main.duke.exception.DukeMissingDescException;
import main.duke.exception.DukeUnknownException;

import java.util.Arrays;

public class Parser {

    public Command parseInput(String inputStr) throws DukeUnknownException, DukeMissingDescException {
        Command parsedCommand;
        String[] strings = inputStr.split(" ");
        String firstStr = strings[0].strip().toLowerCase();
        switch (firstStr) {
            case "bye":
                parsedCommand = new ExitCommand();
                break;
            case "list":
                parsedCommand = new ListCommand();
                break;
            case "delete":
            case "done":
                int pos = Integer.parseInt(strings[1]) - 1; //System.out.println("inputted index is not valid. Try again.");
                UpdateCommand.Operation op = UpdateCommand.Operation.valueOf(firstStr.substring(0, 1).toUpperCase() + firstStr.substring(1));
                parsedCommand = new UpdateCommand(op, pos);
                break;
            default:    //task processing.
                    if (firstStr.isBlank() || firstStr.isEmpty())
                        throw new DukeUnknownException();
                    parsedCommand = new UpdateCommand(UpdateCommand.Operation.Add, processTask(strings));
                break;
        }
        return parsedCommand;
    }

    public Task processTask(String[] inputStrs) throws DukeUnknownException, DukeMissingDescException {
        String tempStr = "";
        Task createdTask;
        switch (inputStrs[0]) {
            default:
                throw new DukeUnknownException();
            case "todo":
                for (int i = 1; i < inputStrs.length; i++) {
                    tempStr = tempStr.concat(String.format("%s ", inputStrs[i]));
                }
                createdTask = new ToDo(tempStr.stripTrailing()); //remove extra space added on last elem.
                break;
            case "deadline":
            case "event":
                createdTask = parseEventOrDeadline(inputStrs);
                break;
        }
        return createdTask;
    }

    private static Task parseEventOrDeadline(String[] inputStrs) throws DukeMissingDescException {
        String tempStr1 = "", tempStr2 = "";
        int delimiterIndex = Arrays.asList(inputStrs).indexOf((inputStrs[0].equals("event")) ? "/at" : "/by");
        for (int i = 1; i < delimiterIndex; i++) {
            tempStr1 = tempStr1.concat(String.format("%s ", inputStrs[i]));
        }
        for (int i = delimiterIndex + 1; i < inputStrs.length; i++) {
            tempStr2 = tempStr2.concat(String.format("%s ", inputStrs[i]));
        }
        tempStr1 = tempStr1.strip();
        tempStr2 = tempStr2.strip();
        return (inputStrs[0].equals("event")) ? new Event(tempStr1, tempStr2) : new Deadline(tempStr1, tempStr2);
    }

    public Parser() {
    }
}
