package ui;
import java.util.Scanner;

public class Ui {

    public void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?");
    }

    /**
     * show loading error of file
     */
    public static void showLoadingError(){
        printUI( "Unable to load file"+  System.lineSeparator());
    }

    /**
     * print line
     */
    public static void showLine(){
        printUI( "__________________________________________"+  System.lineSeparator());
    }

    /**
     * print line into UI
     * @param printline String to be printed
     */
    private static void printUI(String printline){
        System.out.println(printline);
    }

    /**
     * to read from user input
     * @return String output of user input
     */
    public String readCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * showand print error message
     * @param message String of error to be printed
     */
    public static void showError(String message){
        printUI( message +  System.lineSeparator());
    }

    /**
     * To show ending of of the application
     */
    public static void showBye(){
        printUI("Bye. Hope to see you again soon!");
    }

    /**
     * To show usage of a certain command
     * @param printline
     */
    private static void printHelp(String printline){
        System.out.println("Usage: ");
        System.out.println(printline);
    }

    /**
     *  To show help for a specific command available from the application
     * @param help String of a specific command supported by the application
     */
    public static void showHelp(String help){

        switch (help.toUpperCase())
        {
            case "LIST":
                printUI("To list available tasks available from the application, as well as loaded tasks from file");
                printHelp("List");
                break;
            case "BYE":
                printUI("Exit from the application");
                printHelp("Bye");
                break;
            case "DONE":
                printUI("To mark a task to be done. a Tick (instead of cross) will appear when user list available tasks");
                printHelp("Done <task serial number>");
                printUI("Example: Done 3");
                printUI("This will mark 3rd task from the list to be completed - with a tick mark");
                break;
            case "DELETE":
                printUI("To delete a task based on the serial/running number of tasks currently available");
                printHelp("Delete <task serial number>");
                printUI("Example: Delete 3");
                printUI("This will delete 3rd task from the list");
                break;
            case "TODO":
                printUI("To add Todo task.");
                printHelp("Todo <task description> ");
                printUI("Example: Todo Borrow book");
                break;
            case "DEADLINE":
                printUI("To add deadline task.");
                printHelp("Deadline <task description> /at <dd/MM/yyyy HHmm>");
                printUI("Example: Deadline return bike /by 15/04/2019 1800");
                break;
            case "EVENT":
                printUI("To add event task.");
                printHelp("Event <task description> /at <dd/MM/yyyy HHmm>");
                printUI("Example: Event Project meeting /at 19/05/2019 1920");
                break;
            case "FINDDATE":
                printUI("Search a events/deadline which falls on specific date");
                printHelp("Finddate <String date in dd/MM/yyyy format>. ");
                printUI("Example: Finddate 17/05/2019");
                break;
            case "SORT":
                printUI("To sort tasks in chronological order");
                printHelp("Sort");
                printUI("Example: Sort");
                break;
            case "FIND":
                printHelp("Find <String keyword>. Search a keyword of task description");
                break;
            default:
                printUI("Usage: Please type: Help <command>");
                printUI("Supported <command>:");
                printUI("List");
                printUI("Bye");
                printUI("Done");
                printUI("Delete");
                printUI("Todo");
                printUI("Deadline");
                printUI("Event");
                printUI("Finddate");
                printUI("Sort");
                printUI("Find");


        }
    }

}
