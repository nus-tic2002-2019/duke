import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
public class Duke {
    private static int counter = 0;
    private static ArrayList<Task> taskList = new ArrayList<Task>(100);
    public static void main(String[] args) {
        String logo = " ____        _        \n" 
                    + "|  _ \\ _   _| | _____ \n" 
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n" 
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\n");
        System.out.println("what can i do for you?\n");
        
        getMsg();
    }

    //////////////////////
    //getting user input//
    //////////////////////
    public static void getMsg(){
        String line;
        Scanner in = new Scanner(System.in);
        String file_path = "/Users/joseph/Desktop/tic2002Duke/src/main/java/taskList.txt";
        try {
            readFile(file_path);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        counter = taskList.size();
        while (true) {
            line = in.nextLine();
            String lineArr[] = line.split(" ");
            switch (lineArr[0]) {
            case "list":

                System.out.println("Here are the tasks in your list:");
                printListFunction();
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                in.close();
                break;
            case "done":
                try {
                    if (lineArr[1] == "") {
                        throw new DukeException();
                    }
                    System.out.println("Nice! I've marked this task as done:");
                    doneFunction((Integer.valueOf(lineArr[1]) - 1));
                } catch (IndexOutOfBoundsException | DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a done cannot be empty.");
                } catch (NullPointerException e) {
                    System.out.println(" ☹ OOPS!!! The tasks list cannot be empty.");
                }
                break;
            case "todo":
                try {
                    if (lineArr[1] == "") {
                        throw new DukeException();
                    }
                    storeFunction(new Todo(lineArr[1]));
                } catch (IndexOutOfBoundsException | DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case "delete":
                try {
                    if (lineArr[1] == "") {
                        throw new DukeException();
                    }
                    deleteFunction((Integer.valueOf(lineArr[1]) - 1));
                } catch (IndexOutOfBoundsException | DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a delete cannot be empty.");
                }
                break;
            case "deadline":
                try {
                    if (lineArr[3] == "") {
                        throw new DukeException();
                    }
                    storeFunction(new Deadline(lineArr[1], lineArr[3]));
                } catch (IndexOutOfBoundsException | DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                break;
            case "event":
                try {
                    if (lineArr[3] == "") {
                        throw new DukeException();
                    }
                    storeFunction(new Event(lineArr[1], lineArr[3]));
                } catch (IndexOutOfBoundsException | DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                }
                break;
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            try {
                saveToFile(file_path);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /////////////////////////////
    // store user input into arr//
    /////////////////////////////
    public static void storeFunction(Task description) {
        taskList.add(description);
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + taskList.get(counter).toString());
        counter++;
        System.out.println("Now you have " + counter + " tasks in the list.");

    }

    ////////////////////////////////
    // print out item in stored arr//
    ////////////////////////////////
    public static void printListFunction() {
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }
    }

    //////////////
    // Done task///
    //////////////
    public static void doneFunction(int listLocation) {
        Task t = taskList.get(listLocation);
        t.markAsDone();
        System.out.println(t.toString());
    }

    ///////////////
    // Delete task//
    ///////////////
    public static void deleteFunction(int listLocation) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + taskList.get(listLocation).toString());
        counter--;
        System.out.println("Now you have " + counter + " tasks in the list.");
        taskList.remove(listLocation);
    }

    /////////////
    // Save file//
    /////////////
    public static void saveToFile(String file_path) throws IOException {
        FileWriter fileWriter = new FileWriter(file_path);
        String add = "";
        for (int i = 0; i < taskList.size(); ++i) {
            Task task = taskList.get(i);
            String taskClass = "";
            int isDone = 0;
            String description = task.description;
            String date = "";

            if (task instanceof Todo) {
                taskClass = "T";
            } else if (task instanceof Event) {
                taskClass = "E";
                date = ((Event) task).at;
            } else if (task instanceof Deadline) {
                taskClass = "D";
                date = ((Deadline) task).by;
            } ///////////////
            if (task.isDone) {
                isDone = 1;
            } else {
                isDone = 0;
            }
            if (date.equals("")) {
                add += taskClass + " | " + Integer.toString(isDone) + " | " + description + "\n";
            } else {
                add += taskClass + " | " + Integer.toString(isDone) + " | " + description + " | " + date + "\n";
            }
        }
        try {
            fileWriter.write(add);
            fileWriter.close();
        } catch (IOException e) {
            new IOException("The file " + file_path + " has encountered an error writing.");
        }
    }

    /////////////
    // Read file//
    /////////////
    public static void readFile(String file_path) throws IOException {
        BufferedReader fileRead = new BufferedReader(new FileReader(file_path));
        String line = fileRead.readLine();
        while(line != null){
            String[] splitLine = line.split(" \\| ");
            switch(splitLine[0]){
                case "E":
                    Event newEvent = new Event(splitLine[2], splitLine[3]);
                    if(splitLine[1].equals("1")){
                        newEvent.markAsDone();
                    }
                    taskList.add(newEvent);
                    break;
                case "D":
                    Deadline newDeadline = new Deadline(splitLine[2], splitLine[3]);
                    if(splitLine[1].equals("1")){
                        newDeadline.markAsDone();
                    }
                    taskList.add(newDeadline);
                    break;
                case "T":
                    Todo newTodo = new Todo(splitLine[2]);
                    if(splitLine[1].equals("1")){
                    newTodo.markAsDone();
                    }
                    taskList.add(newTodo);
            }
            line = fileRead.readLine();
        }   
        fileRead.close();
    }
}