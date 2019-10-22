import java.util.Scanner;

public class DukeAdd extends Duke {

        public static void add(String input) {

            if (!input.toLowerCase().equals("done 1") && (!input.toLowerCase().equals("done 2"))) {
                System.out.println("added: " + input);
            }

    }
}
