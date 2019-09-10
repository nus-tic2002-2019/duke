import java.util.Scanner;
//import java.util.Arrays;

public class Duke {

    public static void addText(String[] arr, String userText, int ctr) {
        arr[ctr] = userText;
        System.out.println("added: " + userText);
    }

    public static void listArr(String[] arr, int ctr) {
        int i = 0;
        while (i < ctr) {
            System.out.println((i+1) + ": " + arr[i]);
            i++;
        }
    }

    public static void main(String[] args) {

        //System.out.println("Hello from\n" + logo);*/
        String userInput;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello, I'm Duke.\nWhat can I do for you?");

        String[] userArr = new String[100];
        int i = 0;
        userInput = in.nextLine();

        while (!userInput.toLowerCase().equals("bye")) {
            if (userInput.toLowerCase().equals("list")) {
                listArr(userArr, i);
            } else {
                addText(userArr, userInput, i);
                i++;
            }
            userInput = in.nextLine();
        }

        if (userInput.toLowerCase().equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}
