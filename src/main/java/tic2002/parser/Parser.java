package tic2002.parser;

import tic2002.enumerations.Priority;
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
    private static final String STRING_FIND = "find";
    private static final String STRING_TODO = "todo";
    private static final String STRING_DEADLINE = "deadline";
    private static final String STRING_EVENT = "event";
    private static final String SEPARATOR_BY = "/by";
    private static final String SEPARATOR_AT = "/at";
    private static final String SEPARATOR_PRIORITY = "/p";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HHmm";

    //Store keywords' number of characters
    private static int doneStrLen = STRING_DONE.length();
    private static int deleteStrLen = STRING_DELETE.length();
    private static int findStrLen = STRING_FIND.length();
    private static int todoStrLen = STRING_TODO.length();
    private static int deadlineStrLen = STRING_DEADLINE.length();
    private static int eventStrLen = STRING_EVENT.length();
    private static int byStrLen = SEPARATOR_BY.length();
    private static int atStrLen = SEPARATOR_AT.length();
    private static int priorityStrLen = SEPARATOR_PRIORITY.length();

    //Declare variables
    private static Ui currentUi;
    private static Storage currentStorage;
    private String currentInput;
    private TaskList tasksList;
    private boolean isAppExit = false;

    //Constructor
    public Parser(Ui currentUi, String currentInput, TaskList tasksList, Storage currentStorage) {
        this.currentUi = currentUi;
        this.currentStorage = currentStorage;
        this.currentInput = currentInput;
        this.tasksList = tasksList;
    }

    //Getter
    /**
     * Returns isAppExit boolean of Parser class.
     *
     * @return Boolean.
     */
    public boolean getIsExit() {
        return isAppExit;
    }

    //Miscellaneous functions
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

    //Execute function
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
            isAppExit = true;
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
            String taskIndexExtract = currentInput.substring(doneStrLen).trim();
            int taskNum = Integer.parseInt(taskIndexExtract) - 1;
            assert taskNum >= 0 : "Index cannot be negative!";

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

        } else if (currentInput.length() >= deleteStrLen && (currentInput.substring(0, deleteStrLen) ).equals(STRING_DELETE) ) {
            //Delete task
            //Get task index
            String taskIndex = currentInput.substring(deleteStrLen).trim();
            int taskNum = Integer.parseInt(taskIndex) - 1;
            assert taskNum >= 0 : "Index cannot be negative!";

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

        } else if (currentInput.length() >= findStrLen && (currentInput.substring(0, findStrLen) ).equals(STRING_FIND) ) {
            //Find task
            //Get find keyword
            String findKeyword = currentInput.substring(findStrLen).trim();
            int numOfResults = 0;

            currentUi.displayMessageFindPre();

            //Perform search
            for (int i = 0; i < tasksList.getListSize(); i++) {
                int keywordIndex = tasksList.getTask(i).getTaskDescription().indexOf(findKeyword);

                if (keywordIndex >= 0) {
                    numOfResults++;
                    currentUi.displayMessageFindElement(i, numOfResults);
                }
            }

            currentUi.displayLineSeperator();

        } else {
            //Add task
            if (currentInput.length() >= todoStrLen && (currentInput.substring(0, todoStrLen) ).equals(STRING_TODO) ) {
                //Add to-do
                String inputExtract = currentInput.substring(todoStrLen).trim();
                Todo tempTodo;

                if (inputExtract.indexOf(SEPARATOR_PRIORITY) >= 0) {
                    String taskExtract = inputExtract.substring(0, inputExtract.indexOf(SEPARATOR_PRIORITY) ).trim();
                    String priorityExtract = inputExtract.substring(inputExtract.indexOf(SEPARATOR_PRIORITY) + priorityStrLen).trim();
                    String priorityExtractUpper = priorityExtract.toUpperCase();

                    tempTodo = new Todo(taskExtract);

                    try {
                        tempTodo.setTaskPriority(Priority.valueOf(priorityExtractUpper) );
                    } catch (IllegalArgumentException e) {
                        currentUi.displayErrorPriority();
                        return;
                    }

                } else {
                    tempTodo = new Todo(inputExtract);
                }

                tasksList.addTask(tempTodo);
                currentStorage.appendTaskToFile(tasksList.getTask(tasksList.getListSize() -1) );

            } else if (currentInput.length() >= deadlineStrLen && (currentInput.substring(0, deadlineStrLen) ).equals(STRING_DEADLINE) ) {
                //Add deadline
                String inputExtract = currentInput.substring(deadlineStrLen).trim();
                Deadline tempDeadline;

                String taskExtract = inputExtract.substring(0, inputExtract.indexOf(SEPARATOR_BY) ).trim();
                String timeExtract = null;

                if (inputExtract.indexOf(SEPARATOR_PRIORITY) >= 0) {
                    timeExtract = inputExtract.substring(inputExtract.indexOf(SEPARATOR_BY) + byStrLen, inputExtract.indexOf(SEPARATOR_PRIORITY) ).trim();
                } else {
                    timeExtract = inputExtract.substring(inputExtract.indexOf(SEPARATOR_BY) + byStrLen).trim();
                }

                if (isDateTime(timeExtract) ) {
                    LocalDateTime timeConvert = parseStringToDateTime(timeExtract);
                    tempDeadline = new Deadline(taskExtract, timeConvert);
                } else {
                    tempDeadline = new Deadline(taskExtract, timeExtract);
                }

                if (inputExtract.indexOf(SEPARATOR_PRIORITY) >= 0) {
                    String priorityExtract = inputExtract.substring(inputExtract.indexOf(SEPARATOR_PRIORITY) + priorityStrLen).trim();
                    String priorityExtractUpper = priorityExtract.toUpperCase();

                    try {
                        tempDeadline.setTaskPriority(Priority.valueOf(priorityExtractUpper) );
                    } catch (IllegalArgumentException e) {
                        currentUi.displayErrorPriority();
                        return;
                    }
                }

                tasksList.addTask(tempDeadline);
                currentStorage.appendTaskToFile(tasksList.getTask(tasksList.getListSize() -1) );

            } else if (currentInput.length() >= eventStrLen && (currentInput.substring(0, eventStrLen) ).equals(STRING_EVENT) ) {
                //Add event
                String inputExtract = currentInput.substring(eventStrLen).trim();
                Event tempEvent;

                String taskExtract = inputExtract.substring(0, inputExtract.indexOf(SEPARATOR_AT) ).trim();
                String timeExtract = null;

                if (inputExtract.indexOf(SEPARATOR_PRIORITY) >= 0) {
                    timeExtract = inputExtract.substring(inputExtract.indexOf(SEPARATOR_AT) + atStrLen, inputExtract.indexOf(SEPARATOR_PRIORITY) ).trim();
                } else {
                    timeExtract = inputExtract.substring(inputExtract.indexOf(SEPARATOR_AT) + atStrLen).trim();
                }

                if (isDateTime(timeExtract) ) {
                    LocalDateTime timeConvert = parseStringToDateTime(timeExtract);
                    tempEvent = new Event(taskExtract, timeConvert);
                } else {
                    tempEvent = new Event(taskExtract, timeExtract);
                }

                if (inputExtract.indexOf(SEPARATOR_PRIORITY) >= 0) {
                    String priorityExtract = inputExtract.substring(inputExtract.indexOf(SEPARATOR_PRIORITY) + priorityStrLen).trim();
                    String priorityExtractUpper = priorityExtract.toUpperCase();

                    try {
                        tempEvent.setTaskPriority(Priority.valueOf(priorityExtractUpper) );
                    } catch (IllegalArgumentException e) {
                        currentUi.displayErrorPriority();
                        return;
                    }
                }

                tasksList.addTask(tempEvent);
                currentStorage.appendTaskToFile(tasksList.getTask(tasksList.getListSize() -1) );

            } else {
                //Reject non-known keywords
                throw new DukeException();
            }

            currentUi.displayMessageAddTask();
        }
    }
}
