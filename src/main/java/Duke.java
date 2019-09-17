import java.util.Scanner;
//import java.util.Arrays;

public class Duke {

    public static void addText(Task[] arr, String userText, int ctr) {
        Task t = new Task(userText);
        arr[ctr] = t;
    }

    public static void listArr(Task[] arr, int ctr) {
        System.out.println("Here are the tasks in your list:");

        int i = 0;
        while (i < ctr) {
            System.out.print ((i+1) + ". ");
            arr[i].print();
            i++;
        }
    }

    public static void markDone(Task[] arr, int taskNo) {
        arr[taskNo-1].isDone();
        System.out.println("Nice! I've marked this task as done:");
        arr[taskNo-1].print();
    }

    private static int taskDone(String userInput) {
        String[] d = userInput.split(" ");
        if (d.length > 2) {
            //throw Exception
        } else {
            return Integer.parseInt(d[1]);
        }
        return 0;
    }

    public static void main(String[] args) {

        //System.out.println("Hello from\n" + logo);*/
        String userInput;
        String temp;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello, I'm Duke.\nWhat can I do for you?");

        Task[] userArr = new Task[100];
        int i = 0;
        userInput = in.nextLine();
        temp = userInput.toLowerCase();

        while (!temp.equals("bye")) {
            if (temp.equals("list")) {
                listArr(userArr, i);
            } else if (temp.substring(0,4).equals("done")) {
                int taskNo = taskDone(temp);
                markDone(userArr, taskNo);
            } else {
                addText(userArr, userInput, i);
                i++;
            }
            userInput = in.nextLine();
            temp = userInput.toLowerCase();
        }

        if (temp.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}
