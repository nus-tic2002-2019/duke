/*
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    //private static Task[] tasklist = new Task[100];
    private static ArrayList<Task> tasklist=new ArrayList<Task>();
    private static int taskCount = 0;

    public static void printArrayListToFile(ArrayList<Task> arrayList, String filename) throws IOException, FileNotFoundException {
        PrintWriter writer = new PrintWriter(filename);
        for (Task line : arrayList) {
            writer.println(line);
        }
        writer.close();
    }

    public static void readFileIntoList() throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader("output.txt"));
        //ArrayList<String> listOfLines = new ArrayList<>();

        String line = bufReader.readLine();
        while (line != null) {
            char taskType = line.charAt(1);
            char taskDone = line.charAt(4);
            if (taskType == 'T'){
                addTask(new ToDo(line));
            }
            if (taskType == 'E'){
                //addTask(new Event(line));
                System.out.println("its an event");
            }
            if (taskType == 'D'){
                //addTask(new Deadline(line));
                System.out.println("its a deadline");
            }
            line = bufReader.readLine();
        }

        bufReader.close();
    }

    public static void addTask(Task s){
        tasklist.add(taskCount,s);
        taskCount++;
    }
    public static void deleteTask(int index){
        taskCount--;
        tasklist.remove(index);
    }
    public static void printLine(){
            System.out.println("_______________________________");
    }
    public static void checkException(String message){
    try{
     throw new DukeException(message);
      }
      catch (DukeException ex){
              System.out.println(ex.getMessage());
        }
        printLine();
    }
    
    public static void main(String[] args) throws IOException {
        readFileIntoList();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner myObj = new Scanner(System.in);
        String userInput;
        //int index = 0;
            while (myObj.hasNextLine()) {
                userInput = myObj.nextLine();
                String[] split = userInput.split(" ");
                String first = split[0];
                if (userInput.equals("list")) {
                    printLine();
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasklist.get(i).toString());
                    }
                    printArrayListToFile(tasklist, "output.txt");
                    printLine();
                    continue;
                }
                if (userInput.equals("bye")) {
                    break;
                }
                if (first.equals("delete")) {
                    String second = split[1];
                    printLine();
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println(tasklist.get(Integer.parseInt(second) - 1).toString());
                    System.out.println("Now you have " + (taskCount-1) + " tasks in the list.");
                    printLine();
                    deleteTask(Integer.parseInt(second) - 1);
                }
                 else if (first.equals("done")) {
                    String second = split[1];
                    tasklist.get(Integer.parseInt(second) - 1).markAsDone();
                    printLine();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(tasklist.get(Integer.parseInt(second) - 1).getStatusIcon() + " " + tasklist.get(Integer.parseInt(second) - 1).description);
                    printLine();
                } else {
                    printLine();
                    if (first.equals("todo")) {
                        try{
                        String[] split01 = userInput.split(" ");
                        String firstTD = split01[0];
                        String secondTD = split01[1];
                        addTask(new ToDo(secondTD));
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(tasklist.get(taskCount-1).toString());
                        System.out.println("Now you have " + taskCount + " tasks in the list.");
                        }
                        catch (ArrayIndexOutOfBoundsException e){
                          checkException("☹ OOPS!!! The description of a todo cannot be empty.");
                          continue;
                        }
                    }
                    if (first.equals("deadline")) {
                      try{
                        String[] headerSplit02 = userInput.split("deadline");
                        String[] split02 = headerSplit02[1].split("/by");
                        String firstD = split02[0];
                        String secondD = split02[1];
                        addTask(new Deadline(firstD, secondD));
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(tasklist.get(taskCount-1).toString());
                        System.out.println("Now you have " + taskCount + " tasks in the list.");
                      }
                        catch (ArrayIndexOutOfBoundsException e){
                          checkException("☹ OOPS!!! The description of a deadline cannot be empty.");
                          continue;
                        }
                    }
                    if (first.equals("event")) {
                      try{
                        String[] headerSplit03 = userInput.split("event");
                        String[] split03 = headerSplit03[1].split("/at");
                        String firstE = split03[0];
                        String secondE = split03[1];
                        addTask(new Event(firstE, secondE));
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(tasklist.get(taskCount-1).toString());
                        System.out.println("Now you have " + taskCount + " tasks in the list.");
                      }
                        catch (ArrayIndexOutOfBoundsException e){
                          checkException("☹ OOPS!!! The description of a event cannot be empty.");
                          continue;
                        }
                    }
                    else if (!first.equals("event") && !first.equals("deadline") &&!first.equals("todo")) {
                      checkException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                      continue;
                    }
                    printLine();
                    
                }
            
        }
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}*/
