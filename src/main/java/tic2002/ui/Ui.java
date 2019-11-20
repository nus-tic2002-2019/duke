package tic2002.ui;

import tic2002.enumerations.Priority;
import tic2002.exception.DukeException;
import tic2002.task.TaskList;

import java.util.Scanner;

/**
 * Represents Ui class.
 * Deals with interactions with the user.
 */
public class Ui {
    //Declare constant variables
    private static final String DUKE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE_SEPARATOR = "____________________________________________________________\n";
    private static final String USER_INSTRUCTIONS = "Instructions: [list /p {} | find {} | todo {} /p {} | deadline {} /by {} /p {} | event {} /at {} /p {} | done {} | delete {} | clear | bye]\n"
            + "Format: [Date&Time {yyyy-MM-dd HHmm} | Priority {/p} {high, medium, low}]\n";
    private static final String SAVE_INSTRUCTIONS = "***Save Instructions: Create duke.txt file in the same directory as this JAR file.***\n";
    private static final String MSG_GREET = DUKE_LOGO + LINE_SEPARATOR + "Hello! I'm Duke\n" + "What can I do for you?\n" + SAVE_INSTRUCTIONS + USER_INSTRUCTIONS + LINE_SEPARATOR;
    private static final String MSG_BYE = LINE_SEPARATOR + "Bye. Hope to see you again soon!\n" + LINE_SEPARATOR;
    private static final String MSG_PRE_LIST_1 = LINE_SEPARATOR + "You have ";
    private static final String MSG_PRE_LIST_2 = " tasks in your list:\n";
    private static final String MSG_CLEAR = LINE_SEPARATOR + "All tasks cleared!\n" + LINE_SEPARATOR;
    private static final String MSG_DONE = LINE_SEPARATOR + "Nice! I've marked this task as done:\n";
    private static final String MSG_DELETE = LINE_SEPARATOR + "Noted. I've removed this task:\n";
    private static final String MSG_PRE_FIND = LINE_SEPARATOR + "Here are the matching tasks in your list:\n";
    private static final String MSG_PRE_FIND_PRIORITY_1 = LINE_SEPARATOR + "Here are the tasks with priority ";
    private static final String MSG_PRE_FIND_PRIORITY_2 = ":\n";
    private static final String MSG_PRE_TASK = LINE_SEPARATOR + "Got it. I've added this task:\n";
    private static final String MSG_POST_TASK_1 = "Now you have ";
    private static final String MSG_POST_TASK_2 = " tasks in the list.\n" + LINE_SEPARATOR;
    private static final String ERROR_MSG_TRY_AGAIN = "Please verify and input again!\n";
    private static final String ERROR_MSG_IO = LINE_SEPARATOR + "Input or output failure!\n" + LINE_SEPARATOR;
    private static final String ERROR_MSG_INCOMPLETE_INPUT = LINE_SEPARATOR + "Incomplete input! " + ERROR_MSG_TRY_AGAIN + LINE_SEPARATOR;
    private static final String ERROR_MSG_DUKE = LINE_SEPARATOR + "OOPS!!! I'm sorry, but I don't know what that means :-(\n" + LINE_SEPARATOR;
    private static final String ERROR_MSG_FILE_NOT_EXIST = LINE_SEPARATOR + "File not found!\n" + LINE_SEPARATOR;
    private static final String ERROR_MSG_ILLEGAL_INPUT_PRIORITY = LINE_SEPARATOR + "Incorrect priority input! " + ERROR_MSG_TRY_AGAIN + LINE_SEPARATOR;
    private static final String ERROR_MSG_ILLEGAL_INDEX = LINE_SEPARATOR + "Incorrect index! " + ERROR_MSG_TRY_AGAIN + LINE_SEPARATOR;
    private static final String ASSERT_MSG_NEGATIVE_INDEX = LINE_SEPARATOR + "Index cannot be negative! " + ERROR_MSG_TRY_AGAIN + LINE_SEPARATOR;

    //Declare error inputs in an array
    private static String[] errorInputList = {"blah"};

    //Declare variables
    private TaskList tasksList;

    //Constructor
    public Ui(TaskList tasksList) {
        this.tasksList = tasksList;
        greetUser();
    }

    //Getter
    /**
     * Prints greeting message to user.
     */
    public void greetUser() {
        System.out.print(MSG_GREET);
    }

    /**
     * Prints bye message to user.
     */
    public void byeUser() {
        System.out.print(MSG_BYE);
    }

    /**
     * Prints clear message to user.
     */
    public void displayMessageClear() {
        System.out.print(MSG_CLEAR);
    }

    /**
     * Prints message + list of Tasks to user.
     */
    public void displayMessageTasksList(int listSize) {
        String tempString = MSG_PRE_LIST_1 + listSize + MSG_PRE_LIST_2;

        for (int i = 0; i < tasksList.getListSize(); i++) {
            tempString += (i + 1);
            tempString += ".";
            tempString += (tasksList.getTask(i) + "\n");
        }

        tempString += (LINE_SEPARATOR + USER_INSTRUCTIONS + LINE_SEPARATOR);

        System.out.print(tempString);
    }

    /**
     * Prints message + list of tasks with the Priority inputted.
     *
     * @param currentPriority
     */
    public void displayTasksByPriority(Priority currentPriority) {
        String tempString = MSG_PRE_FIND_PRIORITY_1 + currentPriority + MSG_PRE_FIND_PRIORITY_2;

        for (int i = 0; i < tasksList.getListSize(); i++) {
            if (tasksList.getTask(i).getTaskPriority() == currentPriority) {
                tempString += (i + 1);
                tempString += ".";
                tempString += (tasksList.getTask(i) + "\n");
            }
        }

        tempString += (LINE_SEPARATOR + USER_INSTRUCTIONS + LINE_SEPARATOR);

        System.out.print(tempString);
    }

    /**
     * Prints done message + Task done to user.
     *
     * @param currentTaskNum
     */
    public void displayMessageDone(int currentTaskNum) {
        String tempString = MSG_DONE;
        tempString += (tasksList.getTask(currentTaskNum) + "\n");
        tempString += (LINE_SEPARATOR + USER_INSTRUCTIONS + LINE_SEPARATOR);
        System.out.print(tempString);
    }

    /**
     * Prints delete message + Task deleted to user.
     *
     * @param currentTaskNum
     */
    public void displayMessageDelete(int currentTaskNum) {
        String tempString = MSG_DELETE;
        tempString += (tasksList.getTask(currentTaskNum) + "\n");
        tempString += (LINE_SEPARATOR + USER_INSTRUCTIONS + LINE_SEPARATOR);
        System.out.print(tempString);
    }

    /**
     * Prints success message after adding Task to TaskList
     */
    public void displayMessageAddTask() {
        String tempString = MSG_PRE_TASK;
        tempString += (tasksList.getTask(tasksList.getListSize() -1) + "\n");
        tempString += MSG_POST_TASK_1;
        tempString += tasksList.getListSize();
        tempString += (MSG_POST_TASK_2 + USER_INSTRUCTIONS + LINE_SEPARATOR);
        System.out.print(tempString);
    }

    /**
     * Print find pre-message.
     */
    public void displayMessageFindPre() {
        System.out.print(MSG_PRE_FIND);
    }

    /**
     * Prints task with given display index.
     *
     * @param currentIndex as current index in loop of Task lists.
     */
    public void displayMessageFindElement(int currentIndex, int displayIndex) {
        System.out.print(displayIndex + "." + tasksList.getTask(currentIndex) + "\n");
    }

    /**
     * Prints a line separator.
     */
    public void displayLineSeperator() {
        System.out.print(LINE_SEPARATOR);
    }

    /**
     * Prints error message file not found
     */
    public void displayErrorFileNotFound() {
        System.out.print(ERROR_MSG_FILE_NOT_EXIST);
    }

    /**
     * Prints error message IO
     */
    public void displayErrorIo() {
        System.out.print(ERROR_MSG_IO);
    }

    /**
     * Prints error message Duke
     */
    public void displayErrorDuke() {
        System.out.print(ERROR_MSG_DUKE);
    }

    /**
     * Prints error message incorrect priority input.
     */
    public void displayErrorPriority() {
        System.out.print(ERROR_MSG_ILLEGAL_INPUT_PRIORITY);
    }

    /**
     * Prints error message incomplete input.
     */
    public void displayErrorIncompleteInput() {
        System.out.print(ERROR_MSG_INCOMPLETE_INPUT);
    }

    /**
     * Prints error message incorrect index input.
     */
    public void displayErrorIndexInput() {
        System.out.print(ERROR_MSG_ILLEGAL_INDEX);
    }

    /**
     * Prints assertion message incorrect negative index input.
     */
    public void displayAssertNegativeIndex() {
        System.out.print(ASSERT_MSG_NEGATIVE_INDEX);
    }

    /**
     * Returns String of user input.
     *
     * @return String
     * @throws DukeException for error inputs
     */
    public String getUserInput() throws DukeException {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        //Check if input is error
        for (String s : errorInputList) {
            if (s.equals(input) ) {
                throw new DukeException();
            }
        }

        return input.trim();
    }
}
