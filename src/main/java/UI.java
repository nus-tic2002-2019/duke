import java.util.Scanner;

public class UI {
    UI() {}

    void showWelcome(){
        showLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        showLine();
    }

    void showLine(){
        System.out.println("\t_____________________________________________________________");
    }

    String readCommand(){
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine();
    }

    void showLoadingError() {
        System.out.println("No record loaded into task list.");
    }

    void displayError(String message){
        System.out.println(message);
    }
}
