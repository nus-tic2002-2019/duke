package main.java;

import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    private static Scanner input = new Scanner(System.in);
    private static Task[] tasks = new Task[100];
    private static int size = 0;

    public static void printTasks(Task[] tasks) {
        for (int i = 0; tasks[i] != null; i++) {
            System.out.format("%d: %s\n", i + 1, tasks[i]);
        }
    }
    public static Task parseEventOrDeadline(String[] inputStrs) throws DukeMissingDescException{
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
    public static void processTask(String[] inputStrs) throws DukeUnknownException, DukeMissingDescException {
        String tempStr1 = "", tempStr2 = "";
        int delimiterIndex = inputStrs.length;

        switch (inputStrs[0]) {
            default:
                throw new DukeUnknownException();
            case "todo":
                for (int i = 1; i < inputStrs.length; i++) {
                    tempStr1 = tempStr1.concat(String.format("%s ", inputStrs[i]));
                }
                tasks[size] = new ToDo(tempStr1.stripTrailing());   //remove extra space added on last elem.
                break;
            case "deadline":
            case "event":
                tasks[size] = parseEventOrDeadline(inputStrs);
                break;
        }
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[size++].toString());   //increment size after printing the task added.
        System.out.println(String.format("Now you have %d %s in the list.", size, (size > 1) ? "tasks" : "task"));
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        while (true) {
            String inputStr;
            try {
                inputStr = input.nextLine().strip();
            } catch (Exception e) {
                System.out.println("Error parsing input. Try again.");
                continue;
            }
            String[] strings = inputStr.split(" ");
            String firstStr = strings[0].strip().toLowerCase();
            switch (firstStr) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    printTasks(tasks);
                    break;
                case "done":
                    int pos;
                    try {
                        pos = Integer.parseInt(strings[1]);
                    } catch (NumberFormatException e) {
                        System.out.println("inputted index is not valid. Try again.");
                        continue;
                    }
                    System.out.println(String.format("Completed: %d", pos--));//shift pos back by one here after printing as arrays are zero indexed.
                    if (pos >= size) {
                        System.out.println("No such item in task list.");
                    } else if (tasks[pos].isDone()) {
                        System.out.println("Task is already done.");
                    } else {
                        tasks[pos].setDone(true);
                        System.out.println(String.format("Nice! I've marked this task as done:\n%s", tasks[pos]));
                    }
                    break;
                default:    //task processing.
                    try {
                        if(firstStr.isBlank() || firstStr.isEmpty())
                            throw new DukeUnknownException();
                        processTask(strings);
                    } catch (DukeMissingDescException e) {
                        System.out.println(String.format("OOPS!!! The description of %s cannot be empty.", e.getMessage()));
                    } catch (DukeUnknownException e) {
                        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    break;
            }
        }
    }
}
