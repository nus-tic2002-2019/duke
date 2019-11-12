import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static void showDukeWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo +"What can I do for you?\n");
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
        System.out.println("There are currently no task, proceeding to create");
    }

    public static void showAllArrayList(ArrayList<Task> taskList){
        int i=0;
        for(Task task: taskList){
            System.out.println((i+1) + ". " + task.toString());
        }
    }

    public static void showAllTask(TaskList tasks){
        for(int i = 0; i< tasks.getSize(); i++){
            System.out.println((i+1) + ". " + tasks.getTask(i).toString());
        }
    }

    public static void deleteMsg(String deletedTask){
        System.out.println("I have deleted the following: \n" + deletedTask);
    }

    public static void doneMsg(String doneTask){
        System.out.println("Nice! I've marked this task as done: \n" + doneTask);
    }

}
