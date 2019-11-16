package Duke;

import DukeExceptions.DukeMainException;
import DukeItems.Task;
import Output.writeToText;
import Ui.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Enter 'help' for a list of commands.");

        Scanner read = new Scanner(System.in);
        ArrayList<Task> userList = new ArrayList<Task>(); //store tasks
        Task task = null;

        boolean continueInput = true; //flag counter for switch case. If true, switch continues running. Else, abort.

        /*
            USER INTERACTION:

            save: Writes arraylist of tasks to a txt file.
            bye: exits application
            help: lists commands available
            list: displays arraylist of tasks
            done: mark a task as done in arraylist
            delete: delete a task in arraylist
            tod0: create a task in arraylist
            deadline: create a task with deadline in arraylist
            event: create a task with time in arraylist

         */

        do {
            String input = read.nextLine();
            String inputCommand = input.split(" ")[0]; //identifies command from first word
            switch (inputCommand) {
                case "save":
                    writeToText.write(userList);
                    break;
                case "bye":
                    System.out.println("Bye! I better see you again soon!");
                    continueInput = false;
                    break;

                case "help":

                    Ui.uiHelp.getHelp();
                    break;

                case "list":
                    /*
                        Displays list of saved tasks.
                     */
                    uiList uilist = new uiList(userList); //initialize
                    uilist.printList();
                    break;

                case "done":
                    /*
                        Input is split to isolate task number.
                        Task number passed to doneNum
                        use (doneNum-1) to navigate to respective item in arraylist
                     */
                    try {
                        if (input.equals("done")) {
                            throw new DukeMainException.invalidInput("Task number required!");
                        }

                        uiDone uidone = new uiDone(userList, input); //initialize
                        uidone.markDone();

                    } catch (DukeMainException.invalidInput e) {
                        e.printStackTrace();
                    }
                    break;

                case "delete":
                    try {
                        if (input.equals("delete")) {
                            throw new DukeMainException.invalidInput("Task number required!");
                        }
                        String[] split = input.split(" ");
                        int delNum = Integer.parseInt((split[1]));

                        if (delNum > userList.size()) {
                            throw new DukeMainException.invalidInput("Task number does not exist!");
                        }

                        if (delNum == 0) {
                            throw new DukeMainException.invalidInput("Task number does not exist!");
                        }

                        uiDelete uidelete = new uiDelete(userList, input, delNum);
                        uidelete.itemDelete();

                    } catch (DukeMainException.invalidInput e) {
                        e.printStackTrace();
                    }
                    break;

                case "todo":
                    try {
                        if (input.equals("todo")) {
                            throw new DukeMainException.nullDescription("Task description required!");
                        }

                        uiTodo uitodo = new uiTodo(userList, input);
                        uitodo.addTodo();

                    } catch (DukeMainException.nullDescription e) {
                        e.printStackTrace();
                    }
                    break;

                case "deadline":
                    try{
                        if (input.equals("deadline")){
                            throw new DukeMainException.nullDescription("DukeItems.Task description required!");
                        }
                        if (!input.contains("/by")){
                            throw new DukeMainException.nullDescription("Description has invalid format!");
                        }
                        if (input.equals("deadline /by")) {
                            throw new DukeMainException.nullDescription("Description has invalid format!");
                        }
                        if (input.split("/")[1].equals("by")) {
                            throw new DukeMainException.nullDescription("Date required!");
                        }

                        uiDeadline uideadline = new uiDeadline(userList, input);
                        uideadline.addDeadline();


                    } catch (DukeMainException.nullDescription e) {
                        e.printStackTrace();
                    }
                    break;

                case "event":
                    try {
                        if (input.equals("event")) {
                            throw new DukeMainException.nullDescription("DukeItems.Task description required!");
                        }
                        if (!input.contains("/at")) {
                            throw new DukeMainException.nullDescription("Description has invalid format!");
                        }
                        if (input.equals("event /at")) {
                            throw new DukeMainException.nullDescription("Description has invalid format!");
                        }
                        if (input.split("/")[1].equals("at")) {
                            throw new DukeMainException.nullDescription("Time or location required!");
                        }

                        uiEvent uievent = new uiEvent(userList, input);
                        uievent.addEvent();


                    } catch (DukeMainException.nullDescription e) {
                        e.printStackTrace();
                    }
                    break;

                case "find":
                    try {
                        if (input.equals("find")) {
                            throw new DukeMainException.nullDescription("Search key required!");
                        }

                        String searchTerm = input.substring(4);
                        uiFind find = new uiFind(userList, searchTerm);
                        find.findTask();

                    } catch (DukeMainException.nullDescription e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    try {
                        throw new DukeMainException.invalidInput("Invalid format! Enter 'help' for assistance");
                    } catch (DukeMainException.invalidInput e) {
                        e.printStackTrace();
                    }
                    continueInput = true;
            }
        }
        while (continueInput);
    }
}

