package main.java;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int size = 0;
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
        while(true)
        {
            String inputStr = input.nextLine();
            if(inputStr.toLowerCase().equals("bye"))
            {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(inputStr.matches("(done) \\d+")) {                                      //detects exactly for done + number.
                int pos = Integer.parseInt(inputStr.replaceAll("(done) ", ""));  //strips out done and parse to int.
                System.out.println(String.format("Completed: %d", pos));
            }
            else if(inputStr.toLowerCase().equals("list"))
            {
                for(int i = 0; i < size; i++)
                {
                    System.out.format("%d: %s\n", i + 1, tasks[i]);
                }
            }
            else
            {
                System.out.println("added: " + inputStr);
                tasks[size++] = new Task(inputStr);
            }
        }
    }
}
