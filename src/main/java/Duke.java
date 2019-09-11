import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private static String seperatorLine = "___________________________________________\n";
    private static String seperatorLine2 = "________________________________________\n";
    public static String[] listArray = new String[100];
    public static int listCount = 0;
    public static String userInput;


    public static void Added_Print(Scanner input){
        System.out.print("   " + seperatorLine2);
        System.out.println("      added:" + userInput);
        System.out.print("   " + seperatorLine2);

        listArray[listCount] = userInput;
        listCount++;
    }

    public static void Echo_Added_List(Scanner input) {

        boolean isBye = false;
        boolean isList;
        boolean isDone;
        int  listPrint, count = 0, listNum = 1, doneNumber = 1;

        String[] mark = new String[100];
        Arrays.fill(mark, "\u2718");

        while (!isBye) {

            userInput = input.nextLine();
            String[] words = userInput.split(" ");
            String[] result = new String[words.length];

            isList = userInput.equals("list");
            isBye = userInput.equals("bye");
            userInput = words[0];
            isDone= userInput.equals("done");
            Task t = new Task(userInput);

//            if(!isList && !isBye && !isDone){
//                Added_Print(input);
////                System.out.print("   " + seperatorLine2);
////                System.out.println("      added:" + userInput);
////                System.out.print("   " + seperatorLine2);
////
////                listArray[listCount] = userInput;
////                listCount++;
//            }

            if (isList){
                count = listCount;
                System.out.print("   " + seperatorLine2);

                for (listPrint = 0; listPrint < count; listPrint++) {

                    System.out.println("     " + listNum + ". " + "[" + mark[listPrint] + "]"  + " " + listArray[listPrint]);
                    listNum++;
                }
                listNum=1;
                System.out.print("   " + seperatorLine2);
            }

            else if(isDone){
                t.markAsDone();

                System.out.print("   " + seperatorLine2);
                System.out.println("       " + "Nice! I've marked this task as done:");
                System.out.print("          " + "[" + t.getStatusIcon() + "] ");
                doneNumber = Integer.parseInt(words[1]) - 1;
                System.out.println(listArray[doneNumber]);
                mark[doneNumber] = t.getStatusIcon();
                System.out.print("   " + seperatorLine2);

            }

            else if (isBye){
                System.out.print(seperatorLine);
                System.out.println("       " + "Bye. Hope to see you again soon!");
                System.out.println(seperatorLine);
            }

            else {
                Added_Print(input);
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.print(seperatorLine);
        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you");
        System.out.println(seperatorLine);

        Scanner input = new Scanner(System.in);

        Echo_Added_List(input);

    }
}
