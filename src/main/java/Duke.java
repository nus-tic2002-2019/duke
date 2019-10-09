import java.util.Scanner;

public class Duke {
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
        Task[] taskList = new Task[100];
        int counter = 1;
        while (true) {
            line = in.nextLine();
            if (line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                printListFunction(taskList, counter);
            } 
            else if(line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                in.close();
            }
           
            else if(line.contains("done")){
                int listLocation = Integer.valueOf(line.substring(5,line.length()));
                System.out.println("Nice! I've marked this task as done:");
                doneFunction(taskList, listLocation);
            }
            else if(line.contains("todo")){
                String description = line.substring(5, line.length());
                System.out.println("Got it. I've added this task:");
                todoFunction(taskList, description , counter);
                System.out.println("    " + taskList[counter].toString());
                System.out.println("Now you have " + counter + " tasks in the list.");
                counter++;                
            }
            else if(line.contains("deadline")){
                String description = line.substring(line.indexOf("deadline")+9, line.indexOf("by")-1);
                String date = line.substring(line.indexOf("by")+3, line.length());
                System.out.println("Got it. I've added this task:");
                deadlineFunction(taskList, description, date, counter);
                System.out.println("    " + taskList[counter].toString());
                System.out.println("Now you have " + counter + " tasks in the list.");
                counter++;                
            }
            else if(line.contains("event")){
                String description = line.substring(line.indexOf("event")+6, line.indexOf("at")-1);
                String date = line.substring(line.indexOf("at")+3, line.length());
                System.out.println("Got it. I've added this task:");
                eventFunction(taskList, description, date, counter);
                System.out.println("   " + taskList[counter].toString());
                System.out.println("Now you have " + counter + " tasks in the list.");
                counter++;                
            }
            else{
                storeFunction(taskList, line, counter);
                counter ++;
            }
        }
    }

    /////////////////////////////
    //store user input into arr//
    /////////////////////////////
    public static Task[] storeFunction(Task[] taskList, String description, int counter){
        System.out.println("added: " + description);
        Task newTask = new Task(description);
        newTask.description = description;
        taskList[counter] = newTask;
        return taskList;
    }

    //////////////////////////////////
    //store user todo input into arr//
    /////////////////////////////////
    public static Task[] todoFunction(Task[] taskList, String description, int counter){
        Task newTask = new Todo(description);
        newTask.description = description;
        taskList[counter] = newTask;
        return taskList;
    }

    ///////////////////////////////////
    //store user event input into arr//
    //////////////////////////////////
    public static Task[] eventFunction(Task[] taskList, String description, String date, int counter){
        Task newTask = new Event(description, date);
        newTask.description = description;
        taskList[counter] = newTask;
        return taskList;
    }

    //////////////////////////////////////
    //store user deadline input into arr//
    //////////////////////////////////////
    public static Task[] deadlineFunction(Task[] taskList, String description, String date, int counter){
        Task newTask = new Deadline(description, date);
        newTask.description = description;
        taskList[counter] = newTask;
        return taskList;
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
