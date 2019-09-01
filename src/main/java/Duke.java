import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String seperatorLine = "___________________________________________\n";
        String line;
        boolean isSame;

        System.out.print(seperatorLine);
        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you");
        System.out.println(seperatorLine);

        Scanner input = new Scanner(System.in);
        line = input.nextLine();
        isSame = line.equals("bye");

        while (!isSame) {
            System.out.print("   " + seperatorLine);
            System.out.println("       " + line);
            System.out.print("   " + seperatorLine);
            line = input.nextLine();
            isSame = line.equals("bye");
        }

        System.out.print(seperatorLine);
        System.out.println("       " + "Bye. Hope to see you again soon!");
        System.out.println(seperatorLine);
    }
}
