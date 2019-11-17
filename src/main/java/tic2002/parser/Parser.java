package tic2002.parser;

import tic2002.exception.DukeException;
import tic2002.storage.Storage;
import tic2002.task.Deadline;
import tic2002.task.Event;
import tic2002.task.Task;
import tic2002.task.Todo;
import tic2002.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    //Declare constant variables
    private static final String STRING_BYE = "bye";
    private static final String STRING_LIST = "list";
    private static final String STRING_DONE = "done";
    private static final String STRING_DELETE = "delete";
    private static final String STRING_TODO = "todo";
    private static final String STRING_DEADLINE = "deadline";
    private static final String STRING_EVENT = "event";

    //Store keywords' number of characters
    private static int doneStrLen = STRING_DONE.length();
    private static int deleteStrLen = STRING_DELETE.length();
    private static int todoStrLen = STRING_TODO.length();
    private static int deadlineStrLen = STRING_DEADLINE.length();
    private static int eventStrLen = STRING_EVENT.length();

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
                tasksList.add(new Deadline(inputExtract) );
                currentStorage.appendTaskToFile(tasksList.get(tasksList.size() -1) );

            } else if (currentInput.length() >= eventStrLen && (currentInput.substring(0, eventStrLen) ).equals(STRING_EVENT) ) {
                //Add event
                String inputExtract = currentInput.substring(eventStrLen + 1);
                tasksList.add(new Event(inputExtract) );
                currentStorage.appendTaskToFile(tasksList.get(tasksList.size() -1) );

            } else {
                throw new DukeException();
            }

            currentUi.displayMessageAddTask();
        }
    }
}
