package main.java;

import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void printTasks(Task[] tasks){
        for (int i = 0; tasks[i] != null; i++) {
            System.out.format("%d: %s\n", i + 1, tasks[i]);
        }
    }
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int size = 0;
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        while (true) {
            String inputStr;
            try{
                inputStr = input.nextLine();
            }catch (Exception e){
                System.out.println("Error parsing input. Try again.");
                continue;
            }
            String[] strings = inputStr.split(" ");
            System.out.println(Arrays.toString(strings));
            String firstStr = strings[0].strip().toLowerCase();
            if(strings.length == 1) { //if list or bye
                if(firstStr.equals("bye")){
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                }
                else if (firstStr.equals("list"))
                    printTasks(tasks);
            }
            else if (firstStr.equals("done")) {
                int pos;
                try{
                    pos = Integer.parseInt(strings[1]);
                }catch (NumberFormatException e){
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
            }else {
                String formattedStr;
                System.out.println("Got it. I've added this task:");
                switch (firstStr){
                    default:
                        System.out.println("Unknown task detected. defaulting to todo.");
                    case "todo":
                        formattedStr = inputStr.replaceFirst("(todo)", "").strip();
                        tasks[size] = new ToDo(formattedStr);
                        System.out.println(tasks[size++].toString());   //increment size after printing the task added.
                        break;
                    case "deadline":
                        System.out.println("deadline detected!");
                        break;
                    case "event":
                        System.out.println("event detected!");
                        break;
                }
                System.out.println(String.format("Now you have %d %s in the list", size, (size > 1) ? "tasks" : "task"));
            }
        }
    }
}
