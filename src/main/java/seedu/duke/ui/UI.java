package seedu.duke.ui;

import java.util.Scanner;
import java.io.InputStream;

public class UI{
    private final Scanner in;
    private String output;

    public UI(){
        this(System.in);
    }

    public UI(InputStream in){
        this.in = new Scanner(in);
    }

    public String showWelcomeMessage(){
        return "Hello! I'm Duke\n\tWhat can I do for you?";
    }

    public void showGoodbyeMessage(){
        this.output = "Bye. Hope to see you again soon!";
    }

    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    public void setOutput(String output){
        this.output = output;
    }
    
    public String readUserInput(){
        String input = in.nextLine();
        while(shouldIgnore(input)){
            input = in.nextLine();
        }
        return input;
    }

    public String showOutputToUser() {
        return this.output;
    }

    public String showError(Exception exception){
        return "\u2639 OOPS!!! " + exception.getMessage();
    }
}