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
}
