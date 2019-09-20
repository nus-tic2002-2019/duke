/*
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {
    private static int count = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String inData = null;
        ArrayList<Task> arlist = new ArrayList<Task>();
        Task T = null;

        do {
            Scanner scan = new Scanner(System.in);
            inData = scan.nextLine();

            switch (inData.split(" ")[0]) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                case "list":
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + "." + arlist.get(i).toString());
                    }
                    break;
                case "done":
                    int Marked = (Integer.parseInt(inData.substring(inData.indexOf("done") + 5, inData.length())));
                    T = arlist.get(Marked - 1);
                    T.markAsDone();
                    System.out.println("Nice! I've marked this task as done: \n" + T.toString());
                    break;
                case "todo":
                    if (inData.length() == 4) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        String itemName = inData.substring(4, inData.length());
                        ToDo To = new ToDo(itemName);
                        count++;
                        arlist.add(To);
                        System.out.println("Got it. I've added this task: \n" + To.toString() + "\nNow you have " + count + " tasks in list.");
                    }
                    break;
                case "deadline":
                    try {
                        int dividerPosition = inData.indexOf("/");
                        String itemName = inData.substring(8, dividerPosition);
                        String itemName1 = inData.substring(dividerPosition, inData.length());
                        String itemName2 = itemName1.replace("/", "");
                        Deadline D = new Deadline(itemName, itemName2);
                        count++;
                        arlist.add(D);
                        System.out.println("Got it. I've added this task: \n" + D.toString() + "\nNow you have " + count + " tasks in list.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! The description of a deadline's date needs a / after description");
                    }
                    break;
                case "event":
                    try {
                        int dividerPosition = inData.indexOf("/");
                        String itemName = inData.substring(5, dividerPosition);
                        String itemName1 = inData.substring(dividerPosition, inData.length());
                        String itemName2 = itemName1.replace("/", "");
                        Event E = new Event(itemName, itemName2);
                        count++;
                        arlist.add(E);
                        System.out.println("Got it. I've added this task: \n" + E.toString() + "\nNow you have " + count + " tasks in list.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! The description of a event's date needs a / after description");
                    }
                    break;
                case "delete":
                    Marked = (Integer.parseInt(inData.substring(inData.indexOf("delete") + 7, inData.length())));
                    System.out.println("Noted. I've removed this task:  \n" + T.toString());
                    arlist.remove(Marked-1);
                    count--;
                    System.out.println("Now you have " + count + " tasks in list.");
                    break;
                case "save":
                    String file2 = "C:\\NUS\\Year 3 Sem 1\\Software Engineering\\Duke Test\\sample.txt";
                    try {
                        FileOutputStream output = new FileOutputStream(file2);
                        for (int i = 0; i < count; i++) {
                            //writeToFile(file2,(i+1) + "." + arlist.get(i).toString()+ System.lineSeparator());
                            //FileWriter fw = new FileWriter(file2, true); // create a FileWriter in append mode
                            String temp = Integer.toString(i+1) + "." + arlist.get(i).toString()+ System.lineSeparator();
                            byte[] strToBytes=temp.getBytes();
                            output.write(strToBytes);
                        }
                        output.close();
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
            }
        }
        while(!(inData.equals("bye")));
        }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
}
*/
















