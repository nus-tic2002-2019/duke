import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    //private static Task[] tasks = new Task[100];
    //private static int taskCount = 0;

    private static ArrayList<Task> taskitems = new ArrayList<>();

    public static void addTask(Task t){
        //tasks[taskCount] = t;
        taskitems.add(t);
        System.out.println("-----------------------------------------------");
        System.out.println("added:" + t.getDescription());
        System.out.println("-----------------------------------------------");
        //taskCount++;
    }

    //public static void addTodo(Todo t){
    public static void addTask(Todo t) {
        //tasks[taskCount] = t;
        taskitems.add(t);
        System.out.println("-----------------------------------------------");
        System.out.println("Got it. I've added this task: :");
//        System.out.println("   [T][" + t.getStatusIcon() + "] " + t.getDescription());
        System.out.println(t);
        //taskCount++;
        //System.out.println("Now you have " + taskCount + " tasks in the list");
        System.out.println("Now you have " + taskitems.size() + " tasks in the list");
        System.out.println("-----------------------------------------------");
    }

    //public static void addDeadline(Deadlines t){
    public static void addTask(Deadlines t){
        //tasks[taskCount] = t;
        taskitems.add(t);
        System.out.println("-----------------------------------------------");
        System.out.println("Got it. I've added this task: :");
//        System.out.println("   [T][" + t.getStatusIcon() + "] " + t.getDescription());
        System.out.println(t);
        //taskCount++;
        //System.out.println("Now you have " + taskCount + " tasks in the list");
        System.out.println("Now you have " + taskitems.size() + " tasks in the list");
        System.out.println("-----------------------------------------------");
    }

    //public static void addEvent(Events t){
    public static void addTask(Events t){
        //tasks[taskCount] = t;
        taskitems.add(t);
        System.out.println("-----------------------------------------------");
        System.out.println("Got it. I've added this task: :");
//        System.out.println("   [T][" + t.getStatusIcon() + "] " + t.getDescription());
        System.out.println(t);
//        taskCount++;
        //System.out.println("Now you have " + taskCount + " tasks in the list");
        System.out.println("Now you have " + taskitems.size() + " tasks in the list");
        System.out.println("-----------------------------------------------");
    }

    public static void displayTasks()
    {
        System.out.println("-----------------------------------------------");
        for(int i=0; i < taskitems.size(); i++)
        {
            System.out.println(i + 1 + ". " + taskitems.get(i)); // or tasks[i].toString()
        }
        System.out.println("-----------------------------------------------");
    }

    public static void markDone(int pos) {
         //tasks[pos-1].markDone(true);
        //(taskitems[pos-1]).markDone(true);
        taskitems.get(pos-1).markDone(true);
        System.out.println("-----------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        //System.out.println("    [" +  tasks[pos-1].getStatusIcon() + "]" +  tasks[pos-1].getDescription());
        System.out.println("    [" +  taskitems.get(pos-1).getStatusIcon() + "]" +  taskitems.get(pos-1).getDescription());
        System.out.println("-----------------------------------------------");
    }

    public static void removeItem(int pos) {
        //tasks[pos-1].markDone(true);
        //(taskitems[pos-1]).markDone(true);
        System.out.println("-----------------------------------------------");
        System.out.println("Noted. I've removed this task: ");
        //System.out.println("    [" +  tasks[pos-1].getStatusIcon() + "]" +  tasks[pos-1].getDescription());
        //System.out.println("    [" +  taskitems.get(pos-1).getStatusIcon() + "]" +  taskitems.get(pos-1).getDescription());
        System.out.println("    [" +  taskitems.get(pos-1).getStatusIcon() + "]" +  taskitems.get(pos-1).toString());
        taskitems.remove(pos-1);
        System.out.println("Now you have " + taskitems.size() + " tasks in the list");
        System.out.println("-----------------------------------------------");
    }


    public static void main(String[] args) throws DukeException  {

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String line;
        String [] linearr;
        while(true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            linearr = line.split(" ", 2);
            try{
                switch (line.toUpperCase()) {
                //case "LIST":
                case "LIST":
                    displayTasks();
                    break;
                case "BYE":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                default:
//                    if (linearr[0].toUpperCase().equals("DONE")){
//                        markDone(Integer.parseInt(linearr[1]));
//                    }
//                    else {
//                        addTask(new Task(line));
//                    }
                    //System.out.println("linearray :" + linearr.length);

                    switch (linearr[0].toUpperCase())
                    {
                        case "DONE":
                            markDone(Integer.parseInt(linearr[1]));
                            break;
                        case "DELETE":
                            removeItem(Integer.parseInt(linearr[1]));
                            break;
                        case "TODO":
                            //addTodo(new Todo(linearr[1]));
                            //System.out.println("reach todo here");
                            try {
                                addTask(new Todo(linearr[1]));
                            } catch (IndexOutOfBoundsException e)
                            {
                                System.out.println("OOPS!!! The description of a todo cannot be empty.");
                            }
                            break;
                        case "DEADLINE":
                            try {
                                String lineDeadline[] = linearr[1].split("/by", 2);
                                //addDeadline(new Deadlines(lineDeadline[0],lineDeadline[1])) ;
                                addTask(new Deadlines(lineDeadline[0], lineDeadline[1]));
                            } catch (IndexOutOfBoundsException e)
                            {
                                System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                            }
                            break;
                        case "EVENT":
                            try {
                                String lineEvent[] = linearr[1].split("/at", 2);
                                //addEvent(new Events(lineEvent[0],lineEvent[1])); ;
                                addTask(new Events(lineEvent[0], lineEvent[1]));
                            } catch (IndexOutOfBoundsException e)
                            {
                                System.out.println("OOPS!!! The description of an event cannot be empty.");
                            }
                            break;
                        default:
//                            if (linearr.length <=1)
//                            {
                                //System.out.println("length <=1");
                                throw new DukeException();
//                            }
//                            addTask(new Todo(line));
                            //break;
                    }

                    //break;
            }
            } catch (DukeException e)
            {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(" );
            }catch (Exception e) {
                System.out.println("Exception caught" + e.getMessage());
            }
        }
    }
}
// list
//  todo borrow book
//  deadline return book /by Sunday
//  event project meeting /at Mon 2-4pm
// todo read this