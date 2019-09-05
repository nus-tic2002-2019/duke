import java.util.Scanner;

public class Duke {

    private static String seperatorLine = "___________________________________________\n";
    private static String seperatorLine2 = "________________________________________\n";


    public static void Echo_Added_List(Scanner input) {

        boolean isBye = false;
        boolean isList;
        int listCount = 0, listPrint, count = 0;
        String userInput;
        String[] listArray = new String[100];

        while (!isBye) {

            userInput = input.nextLine();

            isList = userInput.equals("list");
            isBye = userInput.equals("bye");

            if(!isList && !isBye){
                System.out.print("   " + seperatorLine2);
                System.out.println("      added:" + userInput);
                System.out.print("   " + seperatorLine2);

                listArray[listCount] = userInput;
                listCount++;
            }

            else if (isList){
                count = listCount;
                System.out.print("   " + seperatorLine2);

                for (listPrint = 0; listPrint < count; listPrint++) {
                    System.out.println("     " + listPrint + ". " + listArray[listPrint]);
                }
                System.out.print("   " + seperatorLine2);
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

        System.out.print(seperatorLine);
        System.out.println("       " + "Bye. Hope to see you again soon!");
        System.out.println(seperatorLine);
    }
}
