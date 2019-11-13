import java.util.Scanner;
import java.util.*;
import DukeTasks.Task;
import java.io.IOException;
import DukeExceptions.Exception;
//import DukeClasses.Deadline;


public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from \n" + logo + "What can I do for you?" );
		ArrayList<Task> toDoList = new ArrayList<>();
		
		// Level-1
		String line;
		int i = 0;
		while (true){   
        // Accepts text
		Scanner in = new Scanner(System.in);
		line = in.nextLine();
		Task t; // Level-2
			try {
				if (line.equals("bye")){
					System.out.println("	Bye. Hope to see you again soon!");
					break;
				} else
				if (line.equals("list")){
					System.out.println("	Here are the tasks in your list:");
					for (int a=1; a<i+1;a++){
					System.out.println("	"+a+". "+toDoList.get(a-1).getStatusIcon()+" "+toDoList.get(a-1).description() );
					}
					continue;
				} else 
				if (line.contains("done")){ // Level-3  checks for interger && line.matches(".*\\d.*")
					try {
					Integer result = Integer.parseInt(line.replaceAll("[\\D]", "")) - 1;
					toDoList.get(result).setStatusIconTrue();
					System.out.println("	Nice! I've marked this task as done: 	\n	 "+toDoList.get(result).getStatusIcon()+" "+toDoList.get(result).description() );
					continue;
					} catch (NumberFormatException nfe){
						throw new Exception(Exception.Code.EMPTY_DONE_DESCRIPTION);
					} catch (NullPointerException npe){
						throw new Exception(Exception.Code.INVALID_TASK_NUMBER);
					}
				} else
				if (line.contains("todo")){
					String todo = line.substring(4,line.length());
					if ( todo==null || todo.trim().length() == 0){
						throw new Exception(Exception.Code.EMPTY_TODO_DESCRIPTION);
					}
					toDoList.add(new Todo(todo));
				} else
				if (line.contains("deadline")){
					int deadline = line.lastIndexOf("/by");
					String description = line.substring(8,deadline); //System.out.println(description.trim().length());
					String datetime = line.substring(deadline+3);
					if ( description==null || description.trim().length() == 0){
						throw new Exception(Exception.Code.EMPTY_DEADLINE_DESCRIPTION);
					}
					if ( datetime==null || datetime.trim().length() == 0){
						throw new Exception(Exception.Code.MISSING_DEADLINE_DATETIME);
					}
					toDoList.add(new Deadline(description, datetime));
				} else 
				if (line.contains("event") && line.contains(" ")){
					int event = line.lastIndexOf("/at");
					String description = line.substring(5,event);
					String venue = line.substring(event+3);
					toDoList.add(new Events(description, venue));
					if ( description==null || description.trim().length() == 0){
						throw new Exception(Exception.Code.EMPTY_EVENT_DESCRIPTION);
					}
					if ( venue==null || venue.trim().length() == 0){
						throw new Exception(Exception.Code.MISSING_EVENT_VENUE);
					}
				} else {
					throw new Exception(Exception.Code.UNKNOWN_COMMAND);
				}
			} catch (Exception e){
				System.out.println(e.errorDescription());
				continue;
			}
			System.out.println("	Got it. I've added this task:\n" + "	" + (toDoList.get(toDoList.size() - 1)).toString() + "\n	Now you have "+toDoList.size() +" tasks in the list.");
		}
	}
}
