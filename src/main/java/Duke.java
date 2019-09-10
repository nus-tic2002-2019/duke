import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        greet();
        echo();
    }

    public static void dukePrint(String input) {
        input = input.replace("\n", "\n\t");
        System.out.println("\t" + input);
        System.out.println("__________________________________________________________________________\n");
    }

    public static void greet() {
        dukePrint("Hello, I'm Duke\nWhat can I do for you?");
    }

    public static void echo() {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String input = in.nextLine();
            switch(input) {
                case("bye"):
                    dukePrint("BYEBYE!");
                    break;
                default:
                    dukePrint("added: " + input);
            }
        }
    }

}


