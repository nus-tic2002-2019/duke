import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String line;
        int count = 0;
        Scanner input = new Scanner(System.in);
        String[] data = new String[100];
        line = input.toString();

        while (true) {
            line = input.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (line.equals("list")) {
                ReturnList(data, count);
            }
            else{
                StoreList (data, line, count);
                count ++;
            }
        }
    }

    public static String[] StoreList (String[]data, String phrase, int number){
        data[number] = phrase;
        System.out.println("added:" + phrase);
        return data;
    }

    public static void ReturnList (String[]data, int number){
        for (int i = 0; i < number; i++) {
            System.out.println(i + 1 + "." + data[i]);
        }
    }
}

