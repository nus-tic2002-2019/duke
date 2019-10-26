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

    public static void displayAfterAction(String thatTask){
        System.out.println(thatTask);
    }

    public static String readCommand(){
        String line;
        Scanner in = new Scanner(System.in);
        System.out.print("Type something: ");
        line = in.nextLine();
        return line;
    }
    public static void showError(String errorMessage){
        System.out.print("WE GOT AN ERROR:" + errorMessage + "\n");
    }
    public static void showLoadingError(){
        System.out.print("There are currently no task, proceeding to create");
    }
}
