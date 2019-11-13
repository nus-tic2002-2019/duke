package Ui;

public class uiHelp {

    public static void getHelp(){
        System.out.println("[1] todo <task>: Enter a task.");
        System.out.println("[2] deadline <task> /by <date>: Enter a task with deadline.");
        System.out.println("[3] event <task> /at <time or location>: Enter a task with date or location.");
        System.out.println("[4] list: Display all tasks.");
        System.out.println("[5] done <enter task number>: Mark a task as done.");
        System.out.println("[6] bye: Exit application");
    }
}
