import java.util.Scanner;                               //Scanner library

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        //Level-1
        Scanner myObj = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n What can I do for you?");

        String reply = myObj.nextLine();                //Reads the next line which is the input
        System.out.println(reply);
    }
}
