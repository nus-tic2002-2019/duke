package tic2002.ui;

import tic2002.exception.DukeException;
import tic2002.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    //Declare constant variables
    private static final String DUKE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE_SEPARATOR = "____________________________________________________________\n";
    private static final String USER_INSTRUCTIONS = "Instructions: [list | todo {} | deadline {} /by {} | event {} /at {} | done {} | delete {} | bye]\n"
            + "Format: [Date&Time {yyyy-MM-dd HHmm}]\n";
    private static final String MSG_GREET = DUKE_LOGO + LINE_SEPARATOR + "Hello! I'm Duke\n" + "What can I do for you?\n" + USER_INSTRUCTIONS + LINE_SEPARATOR;
    private static final String MSG_BYE = LINE_SEPARATOR + "Bye. Hope to see you again soon!\n" + LINE_SEPARATOR;
    private static final String MSG_LIST = LINE_SEPARATOR + "Here are the tasks in your list:\n";
    private static final String MSG_DONE = LINE_SEPARATOR + "Nice! I've marked this task as done:";
    private static final String MSG_DELETE = LINE_SEPARATOR + "Noted. I've removed this task:";
    private static final String MSG_PRE_TASK = LINE_SEPARATOR + "Got it. I've added this task:\n";
    private static final String MSG_POST_TASK_1 = "Now you have ";
    private static final String MSG_POST_TASK_2 = " tasks in the list.\n" + LINE_SEPARATOR;
    private static final String ERROR_MSG_IO = LINE_SEPARATOR + "Input or output failure!\n" + LINE_SEPARATOR;
    private static final String ERROR_MSG_INDEX = LINE_SEPARATOR + "OOPS!!! The description of a done/todo/deadline/event cannot be empty.\n" + LINE_SEPARATOR;
    private static final String ERROR_MSG_DUKE = LINE_SEPARATOR + "OOPS!!! I'm sorry, but I don't know what that means :-(\n" + LINE_SEPARATOR;
    private static final String ERROR_MSG_FILE_NOT_EXIST = LINE_SEPARATOR + "File not found!\n" + LINE_SEPARATOR;

    //Declare error inputs in an array
    private static String[] errorInputList = {"blah"};

    //Declare variables
    private ArrayList<Task> tasksList;

    //Constructor
    public Ui(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
        greetUser();
    }

    //Getter
    //Greet user
    public void greetUser() {
        System.out.print(MSG_GREET);
    }

    //Bye user
    public void byeUser() {
        System.out.print(MSG_BYE);
    }

    //Display message for tasks list
    public void displayMessageTasksList() {
        String tempString = MSG_LIST;

        for (int i = 0; i < tasksList.size(); i++) {
            tempString += (i + 1);
            tempString += ".";
            tempString += (tasksList.get(i) + "\n");
        }

        tempString += (LINE_SEPARATOR + USER_INSTRUCTIONS + LINE_SEPARATOR);

        System.out.print(tempString);
    }

    //Display message for done
    public void displayMessageDone(int currentTaskNum) {
        String tempString = MSG_DONE;
        tempString += (tasksList.get(currentTaskNum) + "\n");
        tempString += (LINE_SEPARATOR + USER_INSTRUCTIONS + LINE_SEPARATOR);
        System.out.print(tempString);
    }

    //Display message for delete
    public void displayMessageDelete(int currentTaskNum) {
        String tempString = MSG_DELETE;
        tempString += (tasksList.get(currentTaskNum) + "\n");
        tempString += (LINE_SEPARATOR + USER_INSTRUCTIONS + LINE_SEPARATOR);
        System.out.print(tempString);
    }

    //Display message after adding task
    public void displayMessageAddTask() {
        String tempString = MSG_PRE_TASK;
        tempString += (tasksList.get(tasksList.size() -1) + "\n");
        tempString += MSG_POST_TASK_1;
        tempString += tasksList.size();
        tempString += (MSG_POST_TASK_2 + USER_INSTRUCTIONS + LINE_SEPARATOR);
        System.out.print(tempString);
    }

    //Display error - file not found
    public String displayErrorFileNotFound() {
        return ERROR_MSG_IO;
    }

    //Display error - IO error
    public void displayErrorIo() {
        System.out.print(ERROR_MSG_FILE_NOT_EXIST);
    }

    //Display error - Duke error
    public void displayErrorDuke() {
        System.out.print(ERROR_MSG_DUKE);
    }

    //Get user input
    public String getUserInput() throws DukeException {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        //Check if input is error
        for (String s : errorInputList) {
            if (s.equals(input) ) {
                throw new DukeException();
            }
        }

        return input;
    }
}
