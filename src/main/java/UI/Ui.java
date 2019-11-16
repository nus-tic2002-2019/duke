package UI;

import Tasks.Deadlines;
import Tasks.Events;
import Tasks.Task;
import Tasks.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static void showDukeWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo +
                "What can I do for you?\n"
                + "Type \"Help\" for list of helpful commands!\n");
    }
    public static void showLine(){
        System.out.println("_______________________");
    }

    public static void displayAfterAction(String thatTask, int numberOfTask){
        System.out.println("Added: "+ thatTask + "\n" +
                "Now you have "+ numberOfTask +" tasks in the list.");
    }

    public static void showExit(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static String readCommand(){
        String line;
        Scanner in = new Scanner(System.in);
        System.out.print("Type something: ");
        line = in.nextLine();
        return line;
    }

    public static void showError(String errorMessage){
        System.out.println("WE GOT AN ERROR:" + errorMessage);
    }
    public static void showLoadingError(){
        System.out.println("There are currently problem with loading the task, proceeding to create a new empty list");
    }

    public static void showAllArrayList(ArrayList<Task> taskList){
        int i=1;
        for(Task task: taskList){
            System.out.println(i + ". Tasks.Task number: " + task.getTaskIndex()+1 + task.toString());
            i++;
        }
    }

    public static void showAllTask(TaskList tasks){
        for(int i = 0; i< tasks.getSize(); i++){
            System.out.println("Tasks.Task Number " + (tasks.getTask(i).getTaskIndex() +1) + ": " + tasks.getTask(i).toString());
        }
    }

    public static void deleteMsg(String deletedTask, int numberOfTask){
        System.out.println("I have deleted the following: \n" +
                deletedTask + "\n" +
                "Now you have "+ numberOfTask +" tasks in the list.");
    }

    public static void doneMsg(String doneTask, int taskIndex){
        System.out.println("Nice! I've marked this task as done: \n" +
                "Tasks.Task Number "+ taskIndex + ": " + doneTask);
    }

    public static void displayMsg(String msg){
        System.out.println(msg);
    }

    public static void displaySchedule(ArrayList<Events> scheduleWithTime, ArrayList<Events>scheduleWithoutTime, ArrayList<Deadlines> deadlines, LocalDate date){
        System.out.println("The Deadline for " + date +" are as following: ");
        int i = 1;
        for(Deadlines deadline: deadlines){
            System.out.println(i + ". "+ deadline.toString());
            i++;
        }
        if(deadlines.size()==0){
            System.out.println("   There are no Deadline");
        }
        System.out.print("\n");
        System.out.println("The Event Schedule for " + date +" are as following: ");
        i = 1;
        for(Events event: scheduleWithTime){
            System.out.println(i + ". "+ event.toString());
            i++;
        }
        if(scheduleWithTime.size()==0){
            System.out.println("   There are no Event with time");
        }
        System.out.print("\n");
        System.out.println("The Event for " + date + " without a time are as following: ");
        i = 1;
        for(Events event: scheduleWithoutTime){
            System.out.println(i + ". "+ event.toString());
            i++;
        }
        if(scheduleWithoutTime.size()==0){
            System.out.println("   There are no Event without time");
        }


    };

    public static void displayListOfHelpFunction(){
        System.out.println("List of command that DUKE have: \n" +
                "(*Commands.Command are not case sensitive)\n" +
                "(**Words with double * are case sensitive");
        System.out.println("\"Hi\": Say hello to the bot\n" +
                "How to use: \n" +
                "Type Hi\n" +
                "E.g. Type something: Hi\n");
        System.out.println("\"Bye\" OR \"Exit\": Say goodbye to the bot and close the program\n" +
                "How to use: \n" +
                "Type Bye\n" +
                "E.g. Type something: Bye\n");
        System.out.println("\"Todo\": Set a task that you want to do\n" +
                "How to use: \n" +
                "Type Todo (Your To Do Tasks.Task)\n" +
                "E.g. Type something: Todo Get a pet\n");
        System.out.println("\"Event\": Set a event task\n" +
                "How to use: \n" +
                "Type Event (Your event Tasks.Task) /at (YYYY-MM-DD OR Monday-Sunday(Full spelling or First 3 letter)) (Optional: hh:mm OR hhmm <24 Hours format>) \n" +
                "E.g. Type something: Event Countdown party /at 2019/12/31\n" +
                "Type something: Event Basketball /at Wed 18:00 \n");
        System.out.println("\"Deadline\": Set a deadline task\n" +
                "How to use: \n" +
                "Type Deadline (Your event Tasks.Task) /by (YYYY-MM-DD OR Monday-Sunday(Full spelling or First 3 letter)) (Optional: hh:mm OR hhmm <24 Hours format>)\n" +
                "E.g. Type something: Deadline Duke.Duke project /by 2019/11/17 2359 \n" +
                "Type something: Deadline Pay phone bills /by Sat\n");
        System.out.println("\"List\": Bring out the list of task\n" +
                "How to use: \n" +
                "Type List\n" +
                "E.g. Type something: list\n");
        System.out.println("\"Find\": Find a task with the word you want to find\n" +
                "How to use: " +
                "Type Find (The word**)\n" +
                "E.g. Type something: Find bill\n");
        System.out.println("\"Schedule\": Find the Schedule for the day\n" +
                "How to use: \n" +
                "Type Schedule (YYYY-MM-DD OR Monday-Sunday(Full spelling or First 3 letter))\n" +
                "E.g. Type something: Schedule Sunday\n");
        System.out.println("\"Done\": Set a task to Done\n" +
                "How to use: \n" +
                "Type Done (Tasks.Task Number)\n" +
                "E.g. Type something: Done 5\n");
        System.out.println("\"Delete\": Delete a task(Be careful: Once you delete a task, it is gone forever. And all other task number changes) \n" +
                "How to use: \n" +
                "Type Delete (Tasks.Task Number)\n" +
                "E.g. Type something: Delete 5\n");
        System.out.println("\"Clearlist\": Clear and remove all the task in the task list\n" +
                "How to use: \n" +
                "Type Clearlist\n" +
                "E.g. Type something: Clearlist\n");
        System.out.println("\"Help\": Since you just used this, I'm sure I don't have to explain this...\n");
    }

}
