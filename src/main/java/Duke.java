import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");

        boolean cont = true;
        while(cont) { // level 1 - Echo
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();

            if (!input.toLowerCase().equals ("bye")){
                DukeResponse.echo(input);
            }
            else
                System.out.println("Bye. Hope to see you soon!");

        }
    }
}



