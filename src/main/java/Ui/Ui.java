package Ui;
import java.util.Scanner;

public class Ui {
    public void showLoadingError()
    {

    }

    public void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?");
    }
    public static void showLine(){
        printUI( "__________________________________________"+  System.lineSeparator());
    }

    private static void printUI(String printline){
        System.out.println(printline);
    }

    public String readCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    public static void showError(String message){
        printUI( message +  System.lineSeparator());
    }
    public static void showBye(){
        printUI("Bye. Hope to see you again soon!");
    }

}
