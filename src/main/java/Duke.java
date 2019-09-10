import java.util.Scanner;

public class Duke {

    public static void addText(String[] arr, String userText, int ctr) {
        //String[] arr = new String[10];
        arr[ctr] = userText;
        System.out.println("added:" + userText);
    }

    public static void listArr(String[] arr) {
        int i = 0;
        while (!arr[i].equals("")) {
            System.out.println(arr[i]);
            i++;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    System.out.println("Hello from\n" + logo);*/
    String userInput;
    Scanner in = new Scanner(System.in);
    //Level 1
        System.out.println("Hello, I'm Duke.\nWhat can I do for you?");

    String[] userArr = new String[10];
    int i = 0;
    userInput = in.nextLine();
        while (!userInput.toLowerCase().equals("bye")) {
        System.out.println(userInput);
        userInput = in.nextLine();
    }

        if (userInput.toLowerCase().equals("bye")) {
        System.out.println("Bye. Hope to see you again soon!");
    }

        /*while (!userInput.toLowerCase().equals("bye") && (i < 10)) {
            if (userInput.toLowerCase().equals("list")) {
                listArr(userArr);
            } else {
                addText(userArr, userInput, i);
                userInput = in.nextLine();
                i++;
            }
        }*/

}
}
