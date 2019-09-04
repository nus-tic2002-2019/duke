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

        System.out.println("\t--------------------------------------------------");
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println("\t--------------------------------------------------");

        Boolean isNotBye  = Boolean.TRUE;
        Boolean isNotList = Boolean.TRUE;
        String bye_word  = "bye";
        String list_word = "list";
        String line = "";
        String[] stringList = new String[50];
        int count = 0;

        Scanner in = new Scanner(System.in);

        while( isNotBye ){
            line = in.nextLine();
            isNotBye  = !(line.equalsIgnoreCase(bye_word));
            isNotList = !(line.equalsIgnoreCase(list_word));

            System.out.println("\t--------------------------------------------------");

            if (isNotBye && isNotList){
                System.out.println("\t"+line);
                stringList[count] = line;
                count += 1;
            } else if ( !isNotList ) {
                for (int i = 0; i < count; i++) {
                    System.out.println("\t" + (i+1) + ". " + stringList[i]);
                }
            } else {
                System.out.println("\tBye. Hope to see you again soon!");
            }
            System.out.println("\t--------------------------------------------------");
        }
        stringList = Arrays.copyOf(stringList, count);
    }
}
