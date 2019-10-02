import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static int Count=0;

    public static void addTask(Task t){
        tasks[Count] = t;
        System.out.println("\t--------------------------------------------------");
        System.out.println("added: " + t.getDescription());
        System.out.println("\t--------------------------------------------------");
        Count++;
    }

    public static void displayTask(){
        System.out.println("\t--------------------------------------------------");
        for (int i = 0; i < Count; i++) {
            System.out.println(i + 1 + ". [" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
        System.out.println("\t--------------------------------------------------");
    }

    public static void setDone(int post){
        tasks[post-1].setDone(true);
        System.out.println("\t--------------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + tasks[post-1].getStatusIcon() + "] " + tasks[post-1].getDescription());
        System.out.println("\t--------------------------------------------------");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        //from L1 and L2
        //int X=0;
        //String input[] =new String[100];
        //Task[] input = new Task[100];
        //int X=0;



        String s1;
        String [] s2;

        while(true){
            Scanner in = new Scanner(System.in);
            s1 = in.nextLine();
            s2 = s1.split(" ",2);
            switch(s1.toUpperCase()){
                case "LIST":
                    displayTask();
                    break;
                case "BYE":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                default:
                    if(s2[0].toUpperCase().equals("DONE")){
                        setDone(Integer.parseInt(s2[1]));
                    } else{
                        addTask(new Task(s1));
                    }
                    break;
            }
        }
    }
}

