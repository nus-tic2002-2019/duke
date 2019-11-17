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
        GetInPut();
    }

    public static void GetInPut() {
        String line;
        int count = 0;
        String[] data = new String[100];
        Scanner input = new Scanner(System.in);
        line = input.toString();


        while (!line.equalsIgnoreCase("bye")) {
            line = input.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (line.equalsIgnoreCase("list")) {
                GetList(data, count);
            }
            else {
                StoreList(data, line, count);
                count++;
            }
        }
    }

    public static String[] StoreList(String[] data, String sentence, int pos) {
        data[pos] = sentence;
        System.out.println("added:" + sentence);
        return data;
    }

    public static void GetList(String[] data, int pos) {
        for (int i=0; i<pos; i++) {
            System.out.println(i + 1 + "." + data[i]);
        }
    }
}
