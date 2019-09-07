import java.util.Scanner;

public class Duke {
    private static int counter = 0;
    private static Task[] tasks = new Task[100];

    public static void setTask(Task description){
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

    // public static String markAsDelete(int task){
    //     return tasks[task].mark();
    // }

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
                        if((input.substring(4, input.length())).trim().equals("")){
                            throw new DukeException("todo");
                        }
                        setTask(new Todo(input.substring(5, input.length())));
                    }
                    catch (DukeException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "deadline":
                    try{
                        if((input.substring(8, input.length()).trim()).equals("")){
                            throw new DukeException("deadline");
                        }
                        if(input.substring(input.indexOf("at")+2, input.length()).trim().equals("") || !(input.contains("at"))){
                            throw new DukeException("deadline", (input.substring(input.indexOf("by")+2, input.length()).trim()));
                        }
                        setTask(new Deadline(input.substring(9, input.indexOf("by")-1), input.substring(input.indexOf("by")+3, input.length())));
                    }
                    catch (DukeException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "event":
                    try{
                        if((input.substring(5, input.length()).trim()).equals("")){
                            throw new DukeException("event");
                        }
                        if(input.substring(input.indexOf("at")+2, input.length()).trim().equals("") || !(input.contains("at"))){
                            throw new DukeException("event", (input.substring(input.indexOf("at")+2, input.length()).trim()));
                        }
                        setTask(new Event(input.substring(6, input.indexOf("at")-1), input.substring(input.indexOf("at")+3, input.length())));
                    }
                    catch (DukeException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "list":
                    System.out.println(getTasks());
                    break;
                case "done":
                    try{
                        if((input.substring(input.indexOf("done")+4, input.length()).trim()).equals("")){
                            throw new DukeException("done");
                        }
                        System.out.println(markAsDone(Integer.parseInt(input.split(" ")[1])-1));
                    }
                    catch (DukeException e){
                        System.out.println(e.getMessage());
                    }
                    catch (NullPointerException e){
                        System.out.println("\t____________________________________________________________\n\t ☹ OOPS!!! The tasks list cannot be empty.\n\t____________________________________________________________");
                    }
                    catch (NumberFormatException e){
                        System.out.println("\t____________________________________________________________\n\t ☹ OOPS!!! The task number must be a numerical value.\n\t____________________________________________________________");
                    }
                    break;
                case "delete":
                    // try{
                    //     if((input.substring(input.indexOf("delete")+7, input.length()).trim()).equals("")){
                    //         throw new DukeException();
                    //     }
                default:
                    System.out.println("\t____________________________________________________________\n\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n\t____________________________________________________________");
            }
        }
        while(!(input.equals("bye")));
    }
}