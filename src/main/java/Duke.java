import java.util.Arrays;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String line;

        while(true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.toUpperCase().equals("BYE")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else
            {
                System.out.println(line);
            };

        }
    }
}
