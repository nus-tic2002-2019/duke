package seedu.duke.ui;

import java.util.Scanner;

public class UI{
    private final Scanner in;
    private String output;

    /** 
     * Constructs a new UI and initialise with stdin for the user input.
     */
    public UI(){
        this.in = new Scanner(System.in);
    }

    /** 
     * Returns the goodbye message of Duke.
     * @return String   The goodbye message of Duke.
     */
    public void showGoodbyeMessage(){
        this.output = "Bye. Hope to see you again soon!";
    }

    /** 
     * Checks if the input by the user is empty.
     * @param rawInputLine  The input by the user.
     * @return boolean      If the input by the user is empty returns true, if not return false when the input is not empty.
     */
    private boolean shouldIgnore(String rawInputLine){
        return rawInputLine.trim().isEmpty();
    }
  
    /** 
     * Setting the value of the output to return to the user.
     * @param output    The String that is intended for output to the user.
     */
    public void setOutput(String output){
        this.output = output;
    }
    
    /** 
     * Obtaining and interpreting the user input.
     * @return String   The input specified by the user.
     */
    public String readUserInput(){
        String input = in.nextLine();
        while(shouldIgnore(input)){
            input = in.nextLine();
        }
        return input;
    }
  
    /** 
     * Returns the output that is intended for the user.
     * @return String   The output intended for the user.
     */
    public String showOutputToUser(){
        return this.output;
    }
  
    /** 
     * Returns the formatted error message that Duke has encountered.
     * @param exception The type of exception Duke has encountered.
     * @return String   The exception message that Duke has encountered thrown by the exception.
     */
    public String showError(Exception exception){
        return "\u2639 OOPS!!! " + exception.getMessage();
    }
}