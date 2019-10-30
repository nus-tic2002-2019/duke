import java.util.Scanner;

public class Duke {
    //private static Task[] tasklist = new Task[100];
    private static ArrayList<Task> tasklist=new ArrayList<Task>();
    private static int taskCount = 0;

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
    public static void onload() throws FileNotFoundException {
        File f = new File("duke.txt");
        Scanner sc = new Scanner(f);
        while(sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] str = s.split(" \\| ");
            String type = str[0];
            if (type.equals("T")) {
                Todo t = new Todo(str[2]);
                if (Integer.parseInt(str[1]) == 1) t.setDone();
                addTask(new ToDo(t));
            } else if (type.equals("D")) {
                Deadline d = new Deadline(str[2], str[3]);
                if (Integer.parseInt(str[1]) == 1) d.setDone();
                addTask(new Deadline(d));
            } else {
                Event e = new Event(str[2], str[3]);
                if (Integer.parseInt(str[1]) == 1) e.setDone();
                addTask(new Event(e));;
            }
        }
    }
    
    public static void main(String[] args){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        try {
            onLoad();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
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
}
