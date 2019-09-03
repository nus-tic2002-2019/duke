import java.util.Arrays;
import java.util.Scanner;

public class Duke {


    public static void checkString(String args){
        if(args.equals("bye")){
            return;
        } else {
            System.out.println(args);
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            checkString(line);
        }
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello from\n" + logo);
        line = in.nextLine();
        checkString(line);
    }
}
