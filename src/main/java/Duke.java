import java.util.Scanner;
import java.util.*;
import DukeClasses.Task;

public class Duke { 
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from \n" + logo + "What can I do for you?" );
		Task[] toDoList = new Task[100];
		
		// Level-1
		String line;
		int i = 0;
		while (true){   
        // Accepts text
		Scanner in = new Scanner(System.in);
		line = in.nextLine();
        // Check entered text for bye
			if (line.equals("bye")){
				System.out.println("	Bye. Hope to see you again soon!");
				break;
			}
			if (line.equals("list")){
				System.out.println("	Here are the tasks in your list:");
				for (int a=1; a<i+1;a++){
				System.out.println("	"+a+". "+toDoList[a-1].getStatusIcon()+" "+toDoList[a-1].description() );
				}
				continue;
			}
			if (line.contains("done") && line.matches(".*\\d.*")){ // Level-3
				int result = Integer.parseInt(line.replaceAll("[\\D]", "")) - 1; 
				toDoList[result].setStatusIconTrue();
				System.out.println("	Nice! I've marked this task as done: 	\n	 "+toDoList[result].getStatusIcon()+" "+toDoList[result].description() );
				continue;
			} else {
			// Level-2
			Task t = new Task(line);
			toDoList[i] = t;
			System.out.println("	added: " + line + "\n");
			i++;
			}
		}
    }
}

