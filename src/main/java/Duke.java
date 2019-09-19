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
            else{
                storeFunction(taskList, line, counter);
                counter ++;
            }
        }
    }

    /////////////////////////////
    //store user input into arr//
    /////////////////////////////
    public static Task[] storeFunction(Task[] taskList, String line, int counter){
        //messageArr[counter] = line;
        System.out.println("added: " + line);
        Task newTask = new Task(line);
        newTask.description = line;
        taskList[counter] = newTask;
        return taskList;
    }

    ////////////////////////////////
    //print out item in stored arr//
    //////////////////////////////// 
    public static void printListFunction(Task[] taskList, int counter){
        for(int i = 1; i < counter; i++){
            System.out.println(i + "." + "[" + taskList[i].getStatusIcon() + "] " + taskList[i].getDescription());
        }
    }

    //////////////
    //Done task///
    //////////////
    public static void doneFunction(Task[] taskList, int listLocation){
        Task t = taskList[listLocation];
        t.markAsDone();
        System.out.println("[" + t.getStatusIcon() + "] " + t.getDescription());
    }
}
