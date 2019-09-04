import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from \n" + logo + "What can I do for you?" );
		
		// Level-1
		String line;
		while (true){   
        // Accepts text
		Scanner in = new Scanner(System.in);
		line = in.nextLine();
        //Check entered text for bye
			if (!"bye".equals(line)){
				System.out.println("   " + line + "\n");
			} else {
				System.out.println("Bye. Hope to see you again soon!");
				break;
			}
		}
    }
}

