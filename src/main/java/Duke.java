import java.util.Scanner;
//import java.util.Arrays;
import java.util.ArrayList;

public class Duke {

    public static void addText(ArrayList<Task> arr, String actType, String newTask, int ctr) {

        String dateline = "";
        int pos = 0;
        String temp = "";

        if (actType.equals("deadline")) {
            dateline = parseString(newTask, "/by ");
            pos = newTask.indexOf("/by");
        } else if (actType.equals("event")) {
            dateline = parseString(newTask, "/at ");
            pos = newTask.indexOf("/at");
        }

        switch (actType) {
            case "todo":
                arr.add(new Todo(newTask));
                break;
            case "deadline":
                arr.add(new Deadline(newTask.substring(0, pos-1), dateline));
                break;
            case "event":
                arr.add(new Event(newTask.substring(0, pos-1), dateline));
                break;
            default:
                break;
        }

        /*if (actType.equals("todo")) {
            arr.add(new Todo(newTask));
        } else if (actType.equals("deadline")) {
            dateline = parseString(newTask, "/by ");
            pos = newTask.indexOf("/by");
            arr.add(new Deadline(newTask.substring(0, pos-1), dateline));
        } else if (actType.equals("event")) {
            dateline = parseString(newTask, "/at ");
            pos = newTask.indexOf("/at");
            arr.add(new Event(newTask.substring(0, pos-1), dateline));
        }*/

        System.out.println("Got it. I've added this task:\n  " + arr.get(ctr));
        System.out.println("Now you have " + (ctr+1) + " tasks in the list.");
    }

     public static void listArr(ArrayList<Task> arr) {
        System.out.println("Here are the tasks in your list:");

        int i = 1;
        for (Task t : arr) {
            System.out.println(i + "." + t);
            i++;
        }
    }

    public static void markDone(ArrayList<Task> arr, int taskNo) {
        arr.get(taskNo-1).isDone();
        //arr(taskNo-1).isDone();
        System.out.println("Nice! I've marked this task as done:\n  " + arr.get(taskNo-1));
        //(arr.get(taskNo-1));
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

    private static String parseString(String userInput, String delimit) {
        String[] d = userInput.split(delimit);
        if (delimit.equals(" ")) {
            return d[0].toLowerCase();
        } else {
            return d[1];
        }
    }

    public static void main(String[] args) {

        //System.out.println("Hello from\n" + logo);*/
        String userInput;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello, I'm Duke.\nWhat can I do for you?");

        ArrayList<Task> userArr = new ArrayList<>();

        int i = 0;
        userInput = in.nextLine();
        String temp = parseString(userInput, " ");

        while (!temp.equals("bye")) {
            switch(temp) {
                case "done":
                    int taskNo = taskDone(userInput);
                    markDone(userArr, taskNo);
                    break;
                case "list":
                    listArr(userArr);
                    break;
                case "todo": case "deadline": case "event":
                    addText(userArr, temp, userInput.substring(temp.length()+1), i);
                    i++;
                    break;
                default:
                    addText(userArr, "todo",userInput, i);
                    i++;
                    break;
            }

            userInput = in.nextLine();
            temp = parseString(userInput, " ");
        }

        if (userInput.toLowerCase().equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}
