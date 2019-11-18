package tic2002.parser;

import tic2002.exception.DukeException;
import tic2002.storage.Storage;
import tic2002.task.Deadline;
import tic2002.task.Event;
import tic2002.task.TaskList;
import tic2002.task.Todo;
import tic2002.ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

/**
 * Represents Parser class
 * Deals interaction with user.
 */
public class Parser {
    //Declare constant variables
    private static final String STRING_BYE = "bye";
    private static final String STRING_LIST = "list";
    private static final String STRING_CLEAR = "clear";
    private static final String STRING_DONE = "done";
    private static final String STRING_DELETE = "delete";
    private static final String STRING_TODO = "todo";
    private static final String STRING_DEADLINE = "deadline";
    private static final String STRING_EVENT = "event";
    private static final String SEPARATOR_BY = "/by";
    private static final String SEPARATOR_AT = "/at";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HHmm";

    //Store keywords' number of characters
    private static int doneStrLen = STRING_DONE.length();
    private static int deleteStrLen = STRING_DELETE.length();
    private static int todoStrLen = STRING_TODO.length();
    private static int deadlineStrLen = STRING_DEADLINE.length();
    private static int eventStrLen = STRING_EVENT.length();
    private static int byStrLen = SEPARATOR_BY.length();
    private static int atStrLen = SEPARATOR_AT.length();

    //Declare variables
    private static Ui currentUi;
    private static Storage currentStorage;
    private String currentInput;
    private TaskList tasksList;
    private boolean isExit = false;

    //Constructor
    public Parser(Ui currentUi, String currentInput, TaskList tasksList, Storage currentStorage) {
        this.currentUi = currentUi;
        this.currentStorage = currentStorage;
        this.currentInput = currentInput;
        this.tasksList = tasksList;
    }

    //Getter
    /**
     * Returns isExit boolean of Parser class.
     *
     * @return Boolean.
     */
    public boolean getIsExit() {
        return isExit;
    }

    /**
     * Checks and returns true if input String is a valid date.
     * Based on custom format.
     *
     * @param dateTimeInput
     * @return Boolean
     */
    public boolean isDateTime(String dateTimeInput) {
        try {
            LocalDateTime.parse(dateTimeInput, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT) );
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }

    /**
     * Returns parsed LocalDateTime from input String.
     * Based on custom format.
     * No checking of string validity.
     *
     * @param dateTimeInput
     * @return LocalDateTime
     */
    public LocalDateTime parseStringToDateTime(String dateTimeInput) {
        return LocalDateTime.parse(dateTimeInput, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT) );
    }

    //Execute functions
    /**
     * Executes different actions based on user input.
     * Display appropriate messages back to user.
     *
     * @throws DukeException to reject non-standard inputs.
     */
    public void runCommand() throws DukeException {
        if (currentInput.equals(STRING_BYE) ) {
            //Bye
            currentUi.byeUser();
            isExit = true;
            return;

        } else if (currentInput.equals(STRING_LIST) ) {
            //List tasks
            currentUi.displayMessageTasksList();

        } else if (currentInput.equals(STRING_CLEAR) ) {
            //Clear tasks
            tasksList.clearTask();
            try {
                currentStorage.clearFile();
            } catch (IOException e) {
                currentUi.displayErrorIo();
            }
            currentUi.displayMessageClear();

        } else if (currentInput.length() >= doneStrLen && (currentInput.substring(0, doneStrLen) ).equals(STRING_DONE) ) {
            //Mark task as done

            //Get task index
            String taskIndex = currentInput.substring(doneStrLen + 1);
            int taskNum = Integer.parseInt(taskIndex) - 1;

            //Set task as done
            tasksList.getTask(taskNum).setDone();

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
            tasksList.getTask(taskNum).resetDone();

            //Print task to be deleted
            currentUi.displayMessageDelete(taskNum);

            //Delete task
            tasksList.deleteTask(taskNum);

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
                tasksList.addTask(new Todo(inputExtract) );
                currentStorage.appendTaskToFile(tasksList.getTask(tasksList.getListSize() -1) );

            } else if (currentInput.length() >= deadlineStrLen && (currentInput.substring(0, deadlineStrLen) ).equals(STRING_DEADLINE) ) {
                //Add deadline
                String inputExtract = currentInput.substring(deadlineStrLen + 1);
                String taskExtract = inputExtract.substring(0, inputExtract.indexOf(SEPARATOR_BY) - 1);
                String timeExtract = inputExtract.substring(inputExtract.indexOf(SEPARATOR_BY) + 1 + byStrLen);

                if (isDateTime(timeExtract) ) {
                    LocalDateTime timeConvert = parseStringToDateTime(timeExtract);
                    tasksList.addTask(new Deadline(taskExtract, timeConvert) );
                } else {
                    tasksList.addTask(new Deadline(taskExtract, timeExtract) );
                }

                currentStorage.appendTaskToFile(tasksList.getTask(tasksList.getListSize() -1) );

            } else if (currentInput.length() >= eventStrLen && (currentInput.substring(0, eventStrLen) ).equals(STRING_EVENT) ) {
                //Add event
                String inputExtract = currentInput.substring(eventStrLen + 1);
                String taskExtract = inputExtract.substring(0, inputExtract.indexOf(SEPARATOR_AT) - 1);
                String timeExtract = inputExtract.substring(inputExtract.indexOf(SEPARATOR_AT) + 1 + atStrLen);

                if (isDateTime(timeExtract) ) {
                    LocalDateTime timeConvert = parseStringToDateTime(timeExtract);
                    tasksList.addTask(new Event(taskExtract, timeConvert) );
                } else {
                    tasksList.addTask(new Event(taskExtract, timeExtract) );
                }

                currentStorage.appendTaskToFile(tasksList.getTask(tasksList.getListSize() -1) );

            } else {
                throw new DukeException();
            }

            currentUi.displayMessageAddTask();
        }
    }
}
