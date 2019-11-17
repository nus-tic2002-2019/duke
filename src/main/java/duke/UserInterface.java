package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private static Scanner in = new Scanner(System.in);
    /**
     * Greets user upon running application.
     */
    public static void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints placeholder lines for UI.
     */
    public static void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads user input.
     *
     * @return String returns the value that that the user has entered.
     */
    public static String readCommand() {
        return in.nextLine();
    }

    /**
     * Takes in 2 input, choice and message that the user has input and handles the input.
     *
     * @param choice This is the first parameter to handleInput, identifies which choice to make.
     * @param line This is the second parameter to handleInput, stores the original input value.
     */
    public static void handleInput(String choice, String line) throws DukeException {
        switch (choice) {
            case "hello":
            case "hi":
            case "hey":
                System.out.println("Hey there! :)");
                break;
            case "list":
                if (Task.getSize() == 0) {
                    System.out.println("Your list is empty.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    Task.printTaskList();
                }
                break;
            case "done":
                try {
                    int itemNumber = Parser.getItemNumber(line);
                    if (Task.setDone(itemNumber, true)) {
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("[✓] " + Task.getTask(itemNumber).display());
                    }
                } catch (NumberFormatException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException error) {
                    throw new DukeException("Unable to mark task as done.");
                }
                break;
            case "todo":
            case "event":
            case "deadline":
                String description = Parser.getBody(line);
                if (choice.equals("todo")) {
                    Task.addTodo(description);
                } else if (choice.equals("event")) {
                    Task.addEvent(description);
                } else if (choice.equals("deadline")) {
                    Task.addDeadline(description);
                }
                System.out.println("Got it. I've added this task:");
                System.out.println(description);
                System.out.println("Now you have " + Task.getSize() + " tasks in the list.");
                break;
            case "remove":
            case "delete":
                try {
                    int itemNumber = Parser.getItemNumber(line);
                    Todo removedItem = Task.getTask(itemNumber);
                    if (Task.removeTask(itemNumber)) {
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("[" + removedItem.getType() + "][" + (removedItem.getDone() ? "✓" : "✗") + "] " + removedItem.display());
                        System.out.println("Now you have " + Task.getSize() + " tasks in the list.");
                    }
                } catch (NumberFormatException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException error) {
                    throw new DukeException("Unable to delete selection.");
                }
                break;
            case "save":
                try {
                    FileUtils.clearFile();
                    FileUtils.setAppend(true);
                    for (int i = 0; i < Task.getSize(); i++) {
                        Todo item = Task.getTask(i);
                        FileUtils.writeToFile(item.getType() + " | " + item.getDone() + " | " + item.getTodo());
                    }
                    FileUtils.setAppend(false);
                    System.out.println("Saved successfully.");
                } catch (IOException error) {
                    throw new DukeException("Unable to save to file.");
                }
                break;
            case "find":
                String find = Parser.getBody(line);
                ArrayList<Todo> foundItems = Parser.find(find);
                System.out.println("Here are the matching tasks in your list:");
                for (int i = 0; i < foundItems.size(); i++) {
                    System.out.println(i+1 + "." + foundItems.get(i).getType() + "[" + (foundItems.get(i).getDone() ? "✓" : "✗") + "] " + foundItems.get(i).display());
                }
                break;
            case "sort":
                String sort = Parser.getBody(line);
                Task.sortTask(sort);
                System.out.println("Done sorting your items: ");
                Task.printTaskList();
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    /**
     * Executed when program ends, to inform user that the program has been terminated.
     */
    public static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
