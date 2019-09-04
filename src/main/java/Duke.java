import java.util.Scanner;

public class Duke { 
	
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from \n" + logo + "What can I do for you?" );
		String[] toDoList = new String[100];
		
		// Level-1
		String line;
		int i = 0;
		while (true){   
        // Accepts text
		Scanner in = new Scanner(System.in);
		line = in.nextLine();
        //Check entered text for bye
			if ("bye".equals(line)){
				System.out.println("	Bye. Hope to see you again soon!");
				break;
			}
			if ("list".equals(line)){
				for (int a=1; a<i+1;a++){
				System.out.println("	"+a+". "+toDoList[a-1]);
				}
			} else {
				// Level-2
				toDoList[i] = line;
				System.out.println("	added: " + line + "\n");
				i++;
			}
		}
    }
}

