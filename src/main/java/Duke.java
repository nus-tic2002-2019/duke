/**
 *  <h1>TIC2002 - Introduction to Software Engineering Project</h1>
 *  The project aims to build a product named Duke,
 *  a Personal Assistant Chat bot that helps a person to keep track of various things.
 *
 * @author yralle.lesly.gimpaya
 * @version 0.2
 *
 */

// Imports everything cause lazy
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // Static ArrayList to Store Tasks Objects (Level 2)
    public static ArrayList<Task> Tasks = new ArrayList<>();

    //Utility Function to print ArrayList
    public static void printTasks() {
        int count = 1;
        for (Task printable : Tasks){
            System.out.println( "   " + count + ". " + printable);
            count++;
        }
    }
    public static void main(String[] args) {
        Generics.WelcomeBanner();
        DukeResponses Duke = new DukeResponses();

        while(Duke.isAlive()) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            Generics.line();
            Duke.converse(input);
        }
    }
}
