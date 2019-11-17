package UI;

import storage.TempTaskList;
import task.Task;

import java.io.*;
import java.util.*;

public class Message {
    private Scanner in;
    private PrintStream out;

    public Message () {
        this (System.in, System.out);
    }

    public Message(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /*
    public String readCommand () {


    }
     */

    public String read() {
        return in.nextLine();
    }


    public void printTaskList (ArrayList<Task> list) {
        if (list.size() == 0) {
            System.out.println("\tCheers, you have a free day!"
                    + System.lineSeparator()
                    + "\tFree to dump things from your mind");
            return;
        }
        System.out.println("\tHere are the tasks in your list: ");
        int taskNumber = 1;
        for (Task task : list) {
            System.out.printf("\t%d.%s" + System.lineSeparator(),taskNumber, task);
            ++taskNumber;
        }
    }
    public void addTaskMessage (Task task, int numberOfTask) {
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t  " + task);
        System.out.printf("\tNow you have %d tasks in the list."
                + System.lineSeparator(), numberOfTask);
    }
    public void markDoneMessage (Task task) {
        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t  " + task);
    }
    public void markDoMessage (Task task) {
        System.out.println("\tNoted! I've marked this task as incompleted: ");
        System.out.println("\t  " + task);
    }

    public void markDelete (TempTaskList list, Task task) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task);
        System.out.printf("\tNow you have %d tasks in the list.", list.size());
    }

    public void dlInvalidFormatMessage () {
        System.out.println("\t☹ OOPS!!! Separate content and date with \" /by \" ");
    }
    public void evInvalidFormatMessage () {
        System.out.println("\t☹ OOPS!!! Separate content and time block with \" /at \" ");
    }
    public void emptyTaskMessage () {
        System.out.println("\t☹ OOPS!!! The description of a task cannot be empty.");
    }
    public void dontKnowWhatToDoMessage () {
        System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(" );
    }
    public void doneTaskNoMessage () {
        System.out.println("\t☹ OOPS!!! Please input a Task Number instead ~");
    }
    public void doneValidTaskNoMessage () {
        System.out.println("\t☹ OOPS!!! Please input a valid Task No. ~");
    }
    public void doneAlreadyMessage () {
        System.out.println("\t🥳 Hooray! This task has already been marked done ~");
    }
    public void notDoneAlreadyMessage () {
        System.out.println("\t🙄 Yes you should! This task was not completed in the first place =_=|||");
    }

    public void errorFileMessage () {
        System.out.println("\t☹ OOPS!!! Error occurred to get the file :-(");
    }

    public void dateErrMessage () {
        System.out.println("\t☹ OOPS!!! Please input a date in format as \" yyyy-mm-dd \" ");
    }
    public void dateTimeErrMessage () {
        System.out.println("\t☹ OOPS!!! Please input a time in format as \" yyyy-mm-dd time-time (24h)\" ");
    }
    public void findMessage (ArrayList<Integer> no, ArrayList<Task> list) {
        if (list.size() == 0) {
            System.out.println("\tNo matching task, dear ~");
        } else {
            System.out.println("\tHere are the matching tasks in your list:");
            for (int i = 0; i < list.size(); ++i) {
                System.out.println("\t" + no.get(i) + "." + list.get(i));
            }
        }
    }
    public void existingUser (String username) {
        System.out.println("\tWelcome back, " + username);
    }
    public void newUser () {
        System.out.println("\tNew master registered \\^o^/");
    }

    public void showGreetingMessage () {
        System.out.println("Knock knock  Σ ノ( ・◡ ・ ？）");
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        System.out.println(logo);
        System.out.println("\t    \\      / ");
        System.out.println("\tHey! \\    / here, living in a ...");
        System.out.println("\tpod... 🏺");
        System.out.println("\tWho is there summoning me?");
    }
    public void showExitMessage () {
        System.out.println("\tBye. Hope to see you again soon!");
    }

}
