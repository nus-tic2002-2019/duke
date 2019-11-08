/**
 * <h1>Utility Class</h1>
 * <p>
 * Class contains several utilities functions for the Main Process
 * </p>
 * <b>Methods</b>
 * <ul>
 *     <li>WelcomeMesssag</li>
 *     <li>ReadText</li>
 *     <li>ReadCommand</li>
 *     <li>isNumeric</li>
 * </ul>
 *
 * @author BearTeddy
 */

package MyClasses.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Utility {

    //To run at the start of the program for welcoming the user
    public static void WelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        PrintHL();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        PrintHL();
    }

    //To print the constant Line of HoriLine
    public static void PrintHL() {
        final String HoriLine = "--------------------------------------------------\n";
        System.out.println(HoriLine);
    }

    //To read the Console input text;
    public static String ReadText() {
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();
        PrintHL();
        return data;
    }

    //To split the console input text into each command to process
    public static ArrayList<String> ReadCommand(String command) {
        ArrayList<String> commands = new java.util.ArrayList<String>(Arrays.asList(command.split(" ", 3)));
        return commands;
    }

    //Check if String numeric
    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}
