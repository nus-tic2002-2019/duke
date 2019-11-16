package me.chercherlyn.duke.util;

import java.util.Scanner;

/**
 * Represents a utility to interact with user.
 */
public class Ui {
    
    private Scanner scanner;
    
    /**
     * Creates Ui instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Calls System.out.printf(text, args)
     * Method to deal with boilerplate code
     *
     * @param text text with placeholders
     * @param args placeholders
     */
    public void print(String text, Object ... args) {
        System.out.printf(text, args);
    }
    
    /**
     * Calls System.out.printf(text, args) with linebreak
     * Method to deal with boilerplate code
     *
     * @param text text with placeholders
     * @param args placeholders
     */
    public void println(String text, Object ... args) {
        System.out.printf(text + "\n", args);
    }
    
    /**
     * Prints text to console in fancy format
     *
     * @param text text with placeholders
     * @param args placeholders
     */
    public void printFancy(String text, Object ... args) {
        // indent each line (replace line break with line break + space indent)
        // also appent indent to first line
        text = "     " + text;
        text = text.replace("\n", "\n     ");
        
        // print
        System.out.println("    ------------------------------------------------------------");
        System.out.printf(text + "\n", args);
        System.out.println("    ------------------------------------------------------------");
    }
    
    /**
     * Read string line from console
     *
     * @return text entered from console
     */
    public String readLine() {
        return scanner.nextLine();
    }

    /*public void scenarioStart(){
        System.out.println("    3 Commands : Todo - todo {query} - Task which you want to do later on and record it down \n"+
                            "                 Deadline - deadline {query} /by {dd.MM.yyyy} HHmm - Task which is going to be due soon with the date and time recorded down\n" +
                            "                 Event - event {query} /at {dd.MM.yyyy} HHmm - Task which will be happening and dont want to miss it\n\n" +
                            "    Or else : User might enter a wrong input which will prompt them an ERROR MESSAGE &/or can choose to END Chatbot by 'Bye'\n\n" +
                            "    For a start, there is no data stored into the List, so if the user type 'list' there wont be any result shown just an ERROR MESSAGE to notify user.\n" +
                            "    Once User is ready to add a task, they can key in a todo, deadline or any event task command. \n\n" +

                            "    Scenario                  : If User wish to read a book later, they can add it into their Task List as a reminder - 'todo read book'.\n" +
                            "                                When the task is added, it is Mark with a cross [X] as you haven done the task yet.\n\n" +
                            "    Successful Syntax         : [T] - Todo , [D] - Deadline  & [E] - Event\n" +
                            "                                [✓] - Mark task is done & [X] - Task is not done\n\n" +
                            "    View Task Added           : User can type 'list' to view the task they have added earlier on.\n\n" +
                            "    Mark Task Done/Delete     : User can enter 'done {number}' / 'delete {number}\n\n" +
                            "    Find Task                 : With the keyword 'find {query}' & return you the result - based on description only\n\n" +
                            "    Find Task (Better Search) : With the keyword + regex express '-r' -> Like an arguement flag which says we want to search in regular expressions mode.\n" +
                            "                                User can easily search on tasks based on the whole task - Example : 'find -r {date}'. -> This cannot be done in normal find function");


    }*/
}
