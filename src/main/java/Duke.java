import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("\t--------------------------------------------------");
        System.out.println("\tHello! I'm Duke.\n\tWhat can I do for you?");
        System.out.println("\t--------------------------------------------------");

        Boolean isNotBye = Boolean.TRUE;
        String line = "";
        String stop_word = "bye";
        Scanner in = new Scanner(System.in);

        while( isNotBye ){
            line = in.nextLine();
            isNotBye = !(line.equalsIgnoreCase(stop_word));
            System.out.println("\t--------------------------------------------------");
            if (isNotBye){
                System.out.println("\t"+line);
            } else {
                System.out.println("\tBye. Hope to see you soon.");
            }
            System.out.println("\t--------------------------------------------------");
        }
    }
}
