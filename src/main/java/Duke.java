import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo +"What can I do for you?\n");

        Echo();

    }

    public static void Echo() {
        String line;
        Scanner in = new Scanner(System.in);
        boolean byebye = true;
        String s1 ="bye";

        while(byebye) {
            System.out.print("Type something: ");
            line = in.nextLine();
            if (s1.equalsIgnoreCase(line)) {
                byebye = false;
            }
            else{
                System.out.println(line);
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}

