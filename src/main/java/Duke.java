package main.java;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static void printTasks() {
        int i = 0;
        for (Task t:tasks) {
            System.out.format("%d: %s\n", ++i, t);
        }
    }

    private static Task parseEventOrDeadline(String[] inputStrs) throws DukeMissingDescException{
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
        String tempStr = "";

        switch (inputStrs[0]) {
            default:
                throw new DukeUnknownException();
            case "todo":
                for (int i = 1; i < inputStrs.length; i++) {
                    tempStr = tempStr.concat(String.format("%s ", inputStrs[i]));
                }
                tasks.add(new ToDo(tempStr.stripTrailing())); //remove extra space added on last elem.
                break;
            case "deadline":
            case "event":
                tasks.add(parseEventOrDeadline(inputStrs));
                break;
        }
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1).toString());   //increment size after printing the task added.
        System.out.println(String.format("Now you have %d %s in the list.", tasks.size(), (tasks.size() > 1) ? "tasks" : "task"));
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
            Task currTask;
            String[] strings = inputStr.split(" ");
            String firstStr = strings[0].strip().toLowerCase();
            switch (firstStr) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    printTasks();
                    break;
                case "delete":
                case "done":
                    int pos;
                    try {
                        pos = Integer.parseInt(strings[1]) - 1;
                    } catch (NumberFormatException e) {
                        System.out.println("inputted index is not valid. Try again.");
                        continue;
                    }
                    currTask = tasks.get(pos);
                    if (pos >= tasks.size()) {
                        System.out.println("No such item in task list.");
                    } else {
                        if(firstStr.equals("done")){
                            if (currTask.isDone()) {
                                System.out.println("Task is already done.");
                            }
                            else{
                                currTask.setDone(true);
                                System.out.println(String.format("Nice! I've marked this task as done:\n%s", currTask));
                            }
                        }
                        if(firstStr.equals("delete")){
                            tasks.remove(pos);
                            System.out.println(String.format("Noted. I've removed this task:\n %s\nNow you have %d tasks in the list.", currTask, tasks.size()));
                        }
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
