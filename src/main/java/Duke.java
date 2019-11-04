import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException, DukeException {
        run();
    }

    public static void run() throws IOException, DukeException {
        Ui ui = new Ui();
        TaskLists taskList = new TaskLists();
        Storage textFile = new Storage();
        boolean online = true;
        String command = null;
        String message = null;
        ui.showWelcomeMessage();
        try {
            textFile.readFile(taskList);
        } catch (DukeException a) {
            ui.showInputError();
        }
        while (online) {
            try {
                message = ui.readCommand();
                command = new Parser().parseInput(message);
                switch (command) {
                    case "todo":
                        try {
                            taskList.addToDo(message);
                            textFile.saveFile(taskList.getList());
                        } catch (StringIndexOutOfBoundsException e) {
                            ui.showToDoEmptyError();
                            break;
                        }
                        ui.showTaskAdded(taskList.displayLatestTask(), taskList.getSize());
                        break;
                    case "deadline":
                        try {
                            taskList.addDeadline(message);
                            textFile.saveFile(taskList.getList());
                        } catch (StringIndexOutOfBoundsException e) {
                            ui.showDeadlineEmptyError();
                            break;
                        }
                        ui.showTaskAdded(taskList.displayLatestTask(), taskList.getSize());
                        break;
                    case "event":
                        try {
                            taskList.addEvent(message);
                            textFile.saveFile(taskList.getList());
                        } catch (StringIndexOutOfBoundsException e) {
                            ui.showEventEmptyError();
                            break;
                        }
                        ui.showTaskAdded(taskList.displayLatestTask(), taskList.getSize());
                        break;
                    case "list":
                        try {
                            ui.showList(taskList.displayList());
                        } catch (DukeExceptionEmptyList e) {
                            ui.showListEmptyError();
                            break;
                        }
                        break;
                    case "delete":
                        try {
                            ui.showDeletedTask(taskList.deleteTask(message), taskList);
                            textFile.saveFile(taskList.getList());
                        } catch (DukeExceptionInvalidTaskInputFormat e) {
                            ui.showInvalidTaskFormatError();
                            break;
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            ui.showInvalidTaskNumberError();
                            break;
                        }
                        break;
                    case "done":
                        try {
                            ui.showDoneTask(taskList.doneTask(message));
                            break;
                        } catch (DukeExceptionInvalidTaskInputFormat e) {
                            ui.showInvalidTaskFormatError();
                            break;
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            ui.showInvalidTaskNumberError();
                            break;
                        }
                    case "bye":
                        online = false;
                        ui.showOffline();
                        break;
                    default:
                        ui.showUnknownInputError();
                }
            } catch (IOException e) {
                ui.showFileError();
            }
        }
        textFile.saveFile(taskList.getList());
    }
}

/*  public static void main(String[] args) {
        ArrayList<Task> history = new ArrayList<Task>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        DukeReply("Hello! I'm Duke\n\tWhat can i do for you?");
        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            input = input.trim();
            if (input.isEmpty()) { // if input is empty space or just enter, i will ignore it and not add to the list
                continue;
            }
            if (input.equalsIgnoreCase("bye")) { // if user enters "bye", we will exit the program
                DukeReply("Bye. Hope to see you again soon!");
                break;
            }
            if (input.toLowerCase().contains("done")) {
                try {
                    DukeReply(DukeDone(history, input));
                }
                catch (DukeExceptionInvalidTaskInputFormat e) {
                    DukeReply("You've entered an invalid number of task list, please enter a valid task number again.");
                }
                continue;
            }
            if (input.equalsIgnoreCase("list")) { // if the user enters "list", it will list out everything, else add to the list. refer number 5.
                try {
                    DukeReply("Here are the Tasks in your list:\n\t" + DukeList(history));
                }
                catch (DukeException e) {
                    DukeReply("You have nothing in the list.");
                }
                continue;
            }
            if (input.toLowerCase().contains("delete")) { // if the user enters "delete", it will activate the delete task function
                try {
                    DukeReply(DukeDelete(history, input));
                }
                catch (DukeExceptionInvalidTaskInputFormat e) {
                    DukeReply("You've entered an invalid number of task list, please enter a valid task number again.");
                }
                continue;
            }
            // add the other words into the list
            else {
                if (input.toLowerCase().contains("todo")) {
                    try {
                        enterTodo(history, input, history.size());
                    }
                    catch (StringIndexOutOfBoundsException e) {
                        DukeReply("\u2639 OOPS!!! The description of a todo cannot be empty.");
                        continue;
                    }
                } else if (input.toLowerCase().contains("deadline")) {
                    try {
                        enterDeadline(history, input, history.size());
                    }
                    catch (StringIndexOutOfBoundsException e) {
                        DukeReply("\u2639 OOPS!!! The description of a deadline cannot be empty.");
                        continue;
                    }
                } else if (input.toLowerCase().contains("event")) {
                    try {
                        enterEvent(history, input, history.size());
                    }
                    catch (StringIndexOutOfBoundsException e) {
                        DukeReply("\u2639 OOPS!!! The description of a event cannot be empty.");
                        continue;
                    }
                } else {
                    DukeReply("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                    continue;
                }
                DukeReply("Got it. I've added this task:\n\t\t" + DukeDisplayTask(history, history.size() - 1) + "\n\tNow you have " + history.size() + " tasks in the list.");
            }
        }
    }
}
    /* private static String DukeList(ArrayList<Task> history) throws DukeException { // Listing function: go through every string element and concatenate them together to become a huge string return the huge string back to main function.
         if (history.size() == 0) {
             throw new DukeException();
         }
         int index = 1;
         String answer = "";
         for (int i = 0; i < history.size(); i++) {
             answer = (answer + index + "." + history.get(i).getType() + history.get(i).getTaskStatus() + " " + history.get(i).getTask() + " " + history.get(i).getDetails());
             index++;
             if (i == history.size() - 1) {
                 continue;
             }
             answer += "\n\t";
         }
         return answer;
     }

     private static String DukeListDone(ArrayList<Task> listing, int index) {
         listing.get(index - 1).setTaskDone();
         return listing.get(index - 1).getType() + listing.get(index - 1).getTaskStatus() + " " + listing.get(index - 1).getTask();
     }

     private static String DukeDone(ArrayList<Task> history, String input) throws DukeExceptionInvalidTaskInputFormat {
         if (input.length() < 5 || input.charAt(4) != ' ' || input.charAt(5) == ' ') { // Handling errors: When user types done1 done2 or done   2
             throw new DukeExceptionInvalidTaskInputFormat();
         }
         String number = input.substring(5);
         try {
             int index = Integer.parseInt(number);
         } catch (NumberFormatException e) {
             return "Please enter a valid number";
         }
         int index = Integer.parseInt(number);
         if (index > history.size()) { //Handling Errors: When user keys in index which is more than the list it has.
             throw new DukeExceptionInvalidTaskInputFormat();
         } else {
             history.get(index - 1).setTaskDone();
             return "Nice! I've marked this task as done: \n\t" + DukeDisplayTask(history, index - 1);
         }
     }

     private static String DukeDelete(ArrayList<Task> history, String input) throws DukeExceptionInvalidTaskInputFormat {
         if (input.length() < 7 || input.charAt(6) != ' ' || input.charAt(7) == ' ') { // Handling errors: When user types done1 done2 or done   2
             throw new DukeExceptionInvalidTaskInputFormat();
         }
         String number = input.substring(7);
         try {
             int index = Integer.parseInt(number);
         } catch (NumberFormatException e) {
             return "Please enter a valid number";
         }
         int index = Integer.parseInt(number);
         if (index > history.size()) {
             throw new DukeExceptionInvalidTaskInputFormat();
         } else {
             index = index - 1;
             String removed = "Noted. I've removed this task: \n\t\t" + DukeDisplayTask(history, index);
             history.remove(index);
             removed = removed + "\n\tNow you have " + history.size() + " tasks in the list.";
             return removed;
         }
     }

     private static void enterTodo(ArrayList<Task> history, String input, int count) {
         history.add(count, new Todo(input.substring(5)));
     }

     private static void enterDeadline(ArrayList<Task> history, String input, int count) {
         int idx = input.indexOf('/');
         history.add(count, new Deadlines(input.substring(9, idx - 1), input.substring(idx + 4)));
     }

     private static void enterEvent(ArrayList<Task> history, String input, int count) {
         int idx = input.indexOf('/');
         history.add(count, new Events(input.substring(6, idx - 1), input.substring(idx + 4)));
     }

     private static String DukeDisplayTask(ArrayList<Task> history, int number) {
         return history.get(number).getType() + history.get(number).getTaskStatus() + " " + history.get(number).getTask() + " " + history.get(number).getDetails();
     }
 */

/* idea from improvement to this chatbot
1. Wrap around function if input more than 100 entries?
2. Wrap around or ignore entry?
3. Handling errors in inputs by user?
4. if users type 'Done' only, allow user to type in value of task in the list using scanner input.
we will leave it to the future.
5. when list is empty, user types list? Error handling Null element of history
6. when users just types todo with no other input. Error handling Null element of history
 */


/* Splitted the new taskings, to implement error handling for each task input
 * To check on ui.showinputerror and unknownerrorinput usage*/