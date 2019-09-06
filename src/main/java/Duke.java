import java.util.Scanner;

public class Duke {
    private static int counter = 0;
    private static Task[] tasks = new Task[100];

    public static void setTasks(Task description){
        tasks[counter] = description;
        System.out.println("\t____________________________________________________________"
        + "\n\t Got it. I've added this task:"
        + "\n\t  " + tasks[counter].toString()
        + "\n\t Now you have " + ++counter + " tasks in the list."
        + "\n\t____________________________________________________________"
        );
    }

    public static String getTasks(){
        String output = "\t____________________________________________________________";
        for(int i=0;i<counter;i++){
            output += "\n\t" + Integer.toString(i+1) + ". " + tasks[i].toString();
        }
        output += "\n\t____________________________________________________________";
        return output;
    }

    public static String markAsDone(int task){
        return tasks[task].setDone();
    }

    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        String input;
        System.out.println("\t____________________________________________________________" 
        +"\n\tHello! I'm Duke\n\tWhat can I do for you?"
        + "\n\t____________________________________________________________"
        );

        do {
            input = sc.nextLine();
            switch(input.split(" ")[0]){
                case "bye":
                    System.out.println("\t____________________________________________________________"
                    + "\n\tBye. Hope to see you again soon!"
                    + "\n\t____________________________________________________________"
                    );
                    break;
                case "todo":
                    try{
                        if((input.substring(input.indexOf("todo")+5, input.length())).trim().equals("")){
                            throw new DukeException();
                        }
                        setTasks(new Todo(input.substring(input.indexOf("todo")+5, input.length())));
                    }
                    catch (IndexOutOfBoundsException | DukeException e){
                        System.out.println("\t____________________________________________________________\n\t ☹ OOPS!!! The description of a todo cannot be empty.\n\t____________________________________________________________");
                    }
                    break;
                case "deadline":
                    try{
                        if((input.substring(input.indexOf("deadline")+9, input.length()).trim()).equals("")){
                            throw new DukeException();
                        }
                        setTasks(new Deadline(input.substring(input.indexOf("deadline")+9, input.indexOf("by")-1), input.substring(input.indexOf("by")+3, input.length())));
                    }
                    catch (IndexOutOfBoundsException | DukeException e){
                        System.out.println("\t____________________________________________________________\n\t ☹ OOPS!!! The description of a deadline cannot be empty.\n\t____________________________________________________________");
                    }
                    break;
                case "event":
                    try{
                        if((input.substring(input.indexOf("event")+6, input.length()).trim()).equals("")){
                            throw new DukeException();
                        }
                        setTasks(new Event(input.substring(input.indexOf("event")+6, input.indexOf("at")-1), input.substring(input.indexOf("at")+3, input.length())));
                    }
                    catch (IndexOutOfBoundsException | DukeException e){
                        System.out.println("\t____________________________________________________________\n\t ☹ OOPS!!! The description of a event cannot be empty.\n\t____________________________________________________________");
                    }
                    break;
                case "list":
                    System.out.println(getTasks());
                    break;
                case "done":
                    try{
                        if((input.substring(input.indexOf("done")+5, input.length()).trim()).equals("")){
                            throw new DukeException();
                        }
                        System.out.println(markAsDone(Integer.parseInt(input.split(" ")[1])-1));
                    }
                    catch (IndexOutOfBoundsException | DukeException e){
                        System.out.println("\t____________________________________________________________\n\t ☹ OOPS!!! The description of a done cannot be empty.\n\t____________________________________________________________");
                    }
                    catch (NullPointerException e){
                        System.out.println("\t____________________________________________________________\n\t ☹ OOPS!!! The tasks list cannot be empty.\n\t____________________________________________________________");
                    }
                    break;
                default:
                    System.out.println("\t____________________________________________________________\n\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n\t____________________________________________________________");
            }
        }
        while(!(input.equals("bye")));
    }
}