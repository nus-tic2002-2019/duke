package Ui;
import Task.Task;
import TaskList.*;
import Parser.*;

public class Ui {
    public static String seperatorLine = "___________________________________________\n";
    public static String seperatorLine2 = "________________________________________\n";
    public static int task_count = 0;
    public static Task t = new Task("");
    public static String[] mark = new String[100];
    public static int del_task_number =0;




    public static void welcome() {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.print(seperatorLine);
        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you");
        System.out.println(seperatorLine);
    }

    public static void keywordError() {

        System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void byeMessage() {
        System.out.print(seperatorLine);
        System.out.println("       " + "Bye. Hope to see you again soon!");
        System.out.println(seperatorLine);
    }

    public static void todoError() {
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    public static void deadlineDateError() {
        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
    }

    public static void eventDateError() {
        System.out.println("☹ OOPS!!! The event description cannot be empty.");
    }

    public static void doneNumberEmptyError() {
        System.out.println("☹ OOPS!!! The number of a done cannot be empty.");
    }

    public static void doneNumberInvalid() {
        System.out.println("Please enter which integer after delete ");

    }

    public static void printEvent(Task t){


        System.out.print("   " + seperatorLine2);
        System.out.println("     " + "Got it. I've added this task");
//           System.out.println("        " + "[" + t.getStatusIcon() + "]" + todolistArray[task_count] );
//        System.out.println("        " + "[" + t.getStatusIcon() + "]" + todolistArray.get(task_count) );

        System.out.println("        " + "[" + t.getStatusIcon() + "]" + TaskList.getTaskList(task_count));

        System.out.println("     "+ "Now you have "+ (task_count + 1) + " tasks in the list.");
        System.out.print("   " + seperatorLine2);
        task_count++;
    }

    public static void doneTask(int done_task_number, String userInput_taskWords) {
        t.markAsDone();

        System.out.print("   " + seperatorLine2);
        System.out.println("     " + "Nice! I've marked this task as done:");
        System.out.print("        " + "[" + t.getStatusIcon() + "] ");
        done_task_number = Integer.parseInt(userInput_taskWords) - 1;
//           System.out.println(listArray[doneNumber]);
//           System.out.println(todolistArray[doneNumber]);

//        System.out.println(todolistArray.get(done_task_number));
        System.out.println(TaskList.getTaskList(done_task_number));

        mark[done_task_number] = t.getStatusIcon();
        System.out.print("   " + seperatorLine2);
    }

    public static void print_delete_event(){

        System.out.print("   " + seperatorLine2);
        System.out.println("     " + "Noted. I've removed this task");
//        System.out.println("        " + "[" + t.getStatusIcon() + "]" + todolistArray.get(del_task_number) );

        System.out.println("        " + "[" + t.getStatusIcon() + "]" + TaskList.getTaskList(Ui.del_task_number-1) );

        System.out.println("     "+ "Now you have "+ (task_count-1 ) + " tasks in the list.");
        System.out.print("   " + seperatorLine2);

        task_count--;
    }

    public static void deleteTask(int del_task_number) {
        Ui.del_task_number = del_task_number--;

//        String remove_task = todolistArray.get(del_task_number);
        String remove_task = TaskList.getTaskList(del_task_number);
        print_delete_event();

//        todolistArray.remove(remove_task);
        TaskList.removeTaskList(del_task_number);
    }
}
