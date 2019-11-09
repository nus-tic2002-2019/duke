package Ui;
import Task.Task;
import TaskList.TaskList;

public class Ui {
    public static String seperatorLine = "___________________________________________\n";
    public static String seperatorLine2 = "________________________________________\n";
    public static int task_count = 0;
    public static Task task = new Task("");
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

    /***
     * print out error when Duke can't regconize the task keywords
     */
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

    public static void dateTimeInvalidFormat() {
        System.out.println("Or can enter the date format like yyyy-mm-dd (e.g., 2019-01-01) ");
    }

    /**
     * print out all the list and status when user enter list keywords
     * @param task_list
     */
    public static void printEvent(Task task_list){

        System.out.print("   " + seperatorLine2);
        System.out.println("     " + "Got it. I've added this task");
        System.out.println("        " + "[" + task_list.getStatusIcon() + "]" + TaskList.getTaskList(task_count));
        System.out.println("     "+ "Now you have "+ (task_count + 1) + " tasks in the list.");
        System.out.print("   " + seperatorLine2);
        task_count++;
    }

    /***
     * printout mask task as done
     * @param done_task_number the number of the list need to be marked as done
     * @param userInput_taskWords the number of
     */
    public static void doneTask(int done_task_number, String userInput_taskWords) {
        task.markAsDone();

        System.out.print("   " + seperatorLine2);
        System.out.println("     " + "Nice! I've marked this task as done:");
        System.out.print("        " + "[" + task.getStatusIcon() + "] ");
        done_task_number = Integer.parseInt(userInput_taskWords) - 1;
        System.out.println(TaskList.getTaskList(done_task_number));
        mark[done_task_number] = task.getStatusIcon();
        System.out.print("   " + seperatorLine2);
    }

    /**
     * this public method print_delete_event() will be called after deleteTask() been executed
     */
    public static void print_delete_event(){

        System.out.print("   " + seperatorLine2);
        System.out.println("     " + "Noted. I've removed this task");
        System.out.println("        " + "[" + task.getStatusIcon() + "]" + TaskList.getTaskList(Ui.del_task_number-1) );
        System.out.println("     "+ "Now you have "+ (task_count-1 ) + " tasks in the list.");
        System.out.print("   " + seperatorLine2);
        task_count--;
    }

    /***
     * delete specific task from the list
     * @param del_task_number the number of list need to be delete by user
     */
    public static void deleteTask(int del_task_number) {
        Ui.del_task_number = del_task_number--;

        String remove_task = TaskList.getTaskList(del_task_number);
        print_delete_event();

        TaskList.removeTaskList(del_task_number);
    }
}
