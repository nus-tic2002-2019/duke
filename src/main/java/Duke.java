import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static String[] insertList(String[] strTask, String sentence, int pos) {
        strTask[pos] = sentence;
        System.out.println("-----------------------------------------------");
        System.out.println("added:" + sentence);
        System.out.println("-----------------------------------------------");
        return strTask;
    }

    public static void displayList(String[] strTask, int pos) {
        System.out.println("-----------------------------------------------");
        for (int i=0;i<pos;i++)
        {
            System.out.println( i + 1 + "." + strTask[i]);
        }
        System.out.println("-----------------------------------------------");
    }

    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String line;
        int iCounter = 0;
        String[] strTask = new String[100];
        while(true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            switch (line.toUpperCase()) {
                case "LIST":
                    displayList(strTask, iCounter);
                    break;
                case "BYE":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                default:
                    insertList(strTask,line, iCounter);
                    iCounter++;
                    break;
            }
        }
    }
}
