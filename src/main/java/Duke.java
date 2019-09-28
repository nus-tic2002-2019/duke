/**
 *  <h1>TIC2002 - Introduction to Software Engineering Project</h1>
 *  The project aims to build a product named Duke,
 *  a Personal Assistant Chat bot that helps a person to keep track of various things.
 *
 * @author yralle.lesly.gimpaya
 * @version 0.1
 *
 */

// Imports everything cause lazy
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static void WelcomeBanner() {

        String logo = " _______   __    __   __  ___  _______ \n"
                + "|       \\ |  |  |  | |  |/  / |   ____|\n"
                + "|  .--.  ||  |  |  | |  '  /  |  |__   \n"
                + "|  |  |  ||  |  |  | |    <   |   __|\n"
                + "|  '--'  ||  `--'  | |  .  \\  |  |___\n"
                + "|_______/  \\______/  |__|\\__\\ |_______|\n";
        System.out.println(logo);
    }

    public static void line() {
        System.out.println("   ____________________________________________________________");
    }

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
        WelcomeBanner();
        DukeResponses Duke = new DukeResponses();

        while(Duke.isAlive()) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            line();
            Duke.converse(input);
        }

    }
}
