import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        int X=0;
        String input[] =new String[100];

        Scanner in = new Scanner(System.in);

        String s1;
        String s2;

        s1 = in.nextLine();
        s2 = "bye";

        while (!s1.equalsIgnoreCase(s2)) {

            System.out.println("\t--------------------------------------------------");
            if (s1.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");

                for (int i = 0; i < X; i++) {

                    System.out.println(i + 1 + ". [" + input[i].getStatusIcon() + " ]" + input[i]);
                    //System.out.println(i + 1 + ". [" +  " ] " + input[i]);
                }

            } else {

                //System.out.println("\t--------------------------------------------------");
                System.out.println("added: " + s1);
                input[X] = s1;
                X++;

            }
            System.out.println("\t--------------------------------------------------");
            s1 = in.nextLine();

        }

        System.out.println("\t--------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("\t--------------------------------------------------");


    }



}

