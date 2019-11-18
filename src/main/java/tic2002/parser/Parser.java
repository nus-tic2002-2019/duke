package tic2002.parser;

import tic2002.exception.DukeException;
import tic2002.storage.Storage;
import tic2002.task.Deadline;
import tic2002.task.Event;
import tic2002.task.Task;
import tic2002.task.Todo;
import tic2002.ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Parser {
    //Declare constant variables
    private static final String STRING_BYE = "bye";
    private static final String STRING_LIST = "list";
    private static final String STRING_DONE = "done";
    private static final String STRING_DELETE = "delete";
    private static final String STRING_TODO = "todo";
    private static final String STRING_DEADLINE = "deadline";
    private static final String STRING_EVENT = "event";
    private static final String STRING_BY = "by";
    private static final String STRING_AT = "at";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HHmm";
    private static final char CHAR_SEPARATOR = '/';

    //Store keywords' number of characters
    private static int doneStrLen = STRING_DONE.length();
    private static int deleteStrLen = STRING_DELETE.length();
    private static int todoStrLen = STRING_TODO.length();
    private static int deadlineStrLen = STRING_DEADLINE.length();
    private static int eventStrLen = STRING_EVENT.length();
    private static int byStrLen = STRING_BY.length();
    private static int atStrLen = STRING_AT.length();

    //Declare variables
    private static Ui currentUi;
    private static Storage currentStorage;
    private String currentInput;
    private ArrayList<Task> tasksList;
    private boolean isExit = false;

    //Constructor
    public Parser(Ui currentUi, String currentInput, ArrayList<Task> tasksList, Storage currentStorage) {
        this.currentUi = currentUi;
        this.currentStorage = currentStorage;
        this.currentInput = currentInput;
        this.tasksList = tasksList;
    }

    //Getter
    public boolean getIsExit() {
        return isExit;
    }

    //Check if string is valid date and time
    public boolean isDateTime(String dateTimeInput) {
        try {
            LocalDateTime.parse(dateTimeInput, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT) );
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }

    //Parse string to date and time
    public LocalDateTime parseStringToDateTime(String dateTimeInput) {
        return LocalDateTime.parse(dateTimeInput, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT) );
    }

    //Run user command
    public void runCommand() throws DukeException {
        if (currentInput.equals(STRING_BYE) ) {
            //Bye
            currentUi.byeUser();
            isExit = true;
            return;

        } else if (currentInput.equals(STRING_LIST) ) {
            //List tasks
            currentUi.displayMessageTasksList();

        } else if (currentInput.length() >= doneStrLen && (currentInput.substring(0, doneStrLen) ).equals(STRING_DONE) ) {
            //Mark task as done

            //Get task index
            String taskIndex = currentInput.substring(doneStrLen + 1);
            int taskNum = Integer.parseInt(taskIndex) - 1;

            //Set task as done
            tasksList.get(taskNum).setDone();

            //Write (update) to file
            try {
                currentStorage.writeToFile(tasksList);
            } catch (IOException e) {
                currentUi.displayErrorIo();
            }

            //Print completed task
            currentUi.displayMessageDone(taskNum);

        } else if (currentInput.length() >= deleteStrLen && (currentInput.substring(0, deleteStrLen) ).equals(STRING_DELETE) ){
            //Delete task

            //Get task index
            String taskIndex = currentInput.substring(deleteStrLen + 1);
            int taskNum = Integer.parseInt(taskIndex) - 1;

            //Reset task done status
            tasksList.get(taskNum).resetDone();

            //Print task to be deleted
            currentUi.displayMessageDelete(taskNum);

            //Delete task
            tasksList.remove(taskNum);

            //Write (update) to file
            try {
                currentStorage.writeToFile(tasksList);
            } catch (IOException e) {
                currentUi.displayErrorIo();
            }

        } else {
            if (currentInput.length() >= todoStrLen && (currentInput.substring(0, todoStrLen) ).equals(STRING_TODO) ) {
                //Add to-do
                String inputExtract = currentInput.substring(todoStrLen + 1);
                tasksList.add(new Todo(inputExtract) );
                currentStorage.appendTaskToFile(tasksList.get(tasksList.size() -1) );

            } else if (currentInput.length() >= deadlineStrLen && (currentInput.substring(0, deadlineStrLen) ).equals(STRING_DEADLINE) ) {
                //Add deadline
                String inputExtract = currentInput.substring(deadlineStrLen + 1);
                String taskExtract = inputExtract.substring(0, inputExtract.indexOf(CHAR_SEPARATOR) - 1);
                String timeExtract = inputExtract.substring(inputExtract.indexOf(CHAR_SEPARATOR) + 2 + byStrLen);

                if (isDateTime(timeExtract) ) {
                    LocalDateTime timeConvert = parseStringToDateTime(timeExtract);
                    tasksList.add(new Deadline(taskExtract, timeConvert) );
                } else {
                    tasksList.add(new Deadline(taskExtract, timeExtract) );
                }

                currentStorage.appendTaskToFile(tasksList.get(tasksList.size() -1) );

            } else if (currentInput.length() >= eventStrLen && (currentInput.substring(0, eventStrLen) ).equals(STRING_EVENT) ) {
                //Add event
                String inputExtract = currentInput.substring(eventStrLen + 1);
                String taskExtract = inputExtract.substring(0, inputExtract.indexOf(CHAR_SEPARATOR) - 1);
                String timeExtract = inputExtract.substring(inputExtract.indexOf(CHAR_SEPARATOR) + 2 + atStrLen);

                if (isDateTime(timeExtract) ) {
                    LocalDateTime timeConvert = parseStringToDateTime(timeExtract);
                    tasksList.add(new Event(taskExtract, timeConvert) );
                } else {
                    tasksList.add(new Event(taskExtract, timeExtract) );
                }

                currentStorage.appendTaskToFile(tasksList.get(tasksList.size() -1) );

            } else {
                throw new DukeException();
            }

            currentUi.displayMessageAddTask();
        }
    }
}
