import java.util.Scanner;
import java.io.InputStream;

public class UI{
    private final Scanner in;

    public UI(){
        this(System.in);
    }

    public UI(InputStream in){
        this.in = new Scanner(in);
    }

    public String constantString(String output){
        return "\t____________________________________________________________\n\t"
        + output
        + "\n\t____________________________________________________________ ";
    }

    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    public void welcomeMessage(){
        String logo = "____         _        \n" 
                    + "\t   _ \\ _   _| | _____ \n" 
                    + "\t| | | | | | | |/ / _ \\\n"
                    + "\t| |_| | |_| |   <  __/\n" 
                    + "\t|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(constantString(logo + "Hello! I'm Duke\n\tWhat can I do for you?"));
    }

    public void goodbyeMessage(){
        System.out.println(constantString("Bye. Hope to see you again soon!"));
    }

    

    public String readUserInput(){
        String input = in.nextLine();
        while(shouldIgnore(input)){
            input = in.nextLine();
        }
        return input;
    }

    public void showOutputToUser(String output) {
        System.out.println(constantString(output));
    }

    public void showError(String errorMessage){
        System.out.println(constantString("☹ OOPS!!! " + errorMessage));
    }
}