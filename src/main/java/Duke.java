import java.util.Scanner;

public class Duke {
    private static int counter = 1;
    private static Task[] taskList = new Task[100];
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
        while (true) {
            line = in.nextLine();
            String lineArr[] = line.split(" ");
            switch(lineArr[0]){
                case"list":

                    System.out.println("Here are the tasks in your list:");
                    printListFunction(taskList, counter);
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    in.close();
                    break;
                case "done":
                    try{
                        if(lineArr[1] == ""){
                            throw new DukeException();
                        }
                        System.out.println("Nice! I've marked this task as done:");
                        doneFunction(taskList, Integer.valueOf(lineArr[1]));
                    }
                    catch (IndexOutOfBoundsException | DukeException e){
                        System.out.println("☹ OOPS!!! The description of a done cannot be empty.");
                    }
                    catch (NullPointerException e){
                        System.out.println(" ☹ OOPS!!! The tasks list cannot be empty.");
                    }
                    break;
                case "todo":
                    try{ 
                        if(lineArr[1] == ""){
                            throw new DukeException();
                        }
                        storeFunction(new Todo(lineArr[1]));   
                    }
                    catch(IndexOutOfBoundsException | DukeException e){
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }  
                    break;       
                case "deadline":
                    try{ 
                        if(lineArr[3] == ""){
                            throw new DukeException();
                        }
                        storeFunction(new Deadline(lineArr[1], lineArr[3]));   
                    }
                    catch(IndexOutOfBoundsException | DukeException e){
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    break;           
                case "event":  
                    try{ 
                        if(lineArr[3] == ""){
                            throw new DukeException();
                        }
                        storeFunction(new Event(lineArr[1], lineArr[3]));
                    }
                    catch(IndexOutOfBoundsException | DukeException e){
                        System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                    }    
                    break;
                default:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    /////////////////////////////
    //store user input into arr//
    /////////////////////////////
    public static void storeFunction(Task description){
        taskList[counter] = description;
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + taskList[counter].toString());
        System.out.println("Now you have " + counter + " tasks in the list.");
        counter++; 
    }

    ////////////////////////////////
    //print out item in stored arr//
    //////////////////////////////// 
    public static void printListFunction(Task[] taskList, int counter){
        for(int i = 1; i < counter; i++){
            System.out.println(i + "." + taskList[i].toString());
        }
    }

    //////////////
    //Done task///
    //////////////
    public static void doneFunction(Task[] taskList, int listLocation){
        Task t = taskList[listLocation];
        t.markAsDone();
        System.out.println(t.toString());
    }
}
