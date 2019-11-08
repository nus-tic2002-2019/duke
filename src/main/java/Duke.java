<<<<<<< HEAD
/**
 * <h>Duke</h>
 * <p>
 * This is the Tasks List Scheduler/Handler console based Application.
 * </p>
 *
 * @author BearTeddy
 * @version 1.0
 * @since 2019-Nov-01
 */
=======
import java.util.*;
import java.io.*;
>>>>>>> origin/master


import MyClasses.storage.Storage;
import MyClasses.ui.Process;
import MyClasses.ui.Utility;

<<<<<<< HEAD
import java.io.FileNotFoundException;
=======
    // Read the char input of the scanner;
    public static String ReadText() {
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();
        PrintHL();
        return data;
    }

    public static boolean Task(String Cmmd) {
        switch (Cmmd.toLowerCase()) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                PrintHL();
                return false;
<<<<<<< HEAD
=======
            case "list":
                try {
                    if (commands.size() > 1) {
                        if (isNumeric(commands.get(1))) {
                            task.ListTask(Integer.parseInt(commands.get(1)));
                        } else {
                            System.out.println("Unknown Command for List : " + commands);
                            PrintHL();
                        }
                    } else {
                        task.ListTask();
                    }
                } catch (IndexOutOfBoundsException e) {
                    task.ListTask();
                }
>>>>>>> origin/master

public class Duke {

<<<<<<< HEAD
    /**
     *  Main Method of the Project, uses Utility Class and Storage Class
     *
     * @param args
     * @throws FileNotFoundException
     */

    public static void main(String[] args) throws FileNotFoundException {
        Utility.WelcomeMessage();
        Storage.LoadFile();
        while (Process.Task(Utility.ReadText())) ;
=======
                break;
            case "add":
                if (commands.get(1).equals("-t") || commands.get(1).equals("task")) {
                    task.AddTask(commands.get(2));
                }
                break;
>>>>>>> e8c8f938ded94ada9c4f734dbeeace25db93415d
            default:
                System.out.println(Cmmd);
                PrintHL();
                return true;
        }
>>>>>>> origin/master
    }

}
