import java.util.Scanner;

public class Duke {
    private static void DukeReply(String replies) { // DukeReplying format, only changing variable is replies/ input from main function.
        System.out.println("\t________________________________________________________________\n" + "\t" + replies + "\n\t________________________________________________________________\n");
    }

    private static String DukeList(String[] listing) { // Listing function: go through every string element and concatenate them together to become a huge string return the huge string back to main function.
        int index = 1;
        String answer = "";
        for (int i = 0; i < listing.length; i++) {
            answer = (answer + index + ". " + listing[i]);
            index++;
            if (listing[i + 1] == null) {
                break;
            }
            answer += "\n\t";
        }
        return answer;
    }

    public static void main(String[] args) {
        int count = 0;
        String[] history = new String[100];
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        DukeReply("Hello! I'm Duke\n\tWhat can i do for you?");
        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            input = input.trim();
            if (input.isEmpty()) { // if input is empty space or just enter, i will ignore it and not add to the list
                continue;
            }
            if (input.equalsIgnoreCase("bye")) { // if user enters "bye", we will exit the program
                DukeReply("Bye. Hope to see you again soon!");
                break;
            }
            if (input.equalsIgnoreCase("list")) { // if the user enters "list", it will list out everything, else add to the list.
                input = DukeList(history);
            } else {
                history[count] = input;
                count++;
                input = "added: " + input;
            }
            DukeReply(input);
        }
    }
}
/* idea from improvement to this chatbot
1. Wrap around function if input more than 100 entries?
2. Wrap around or ignore entry?
we will leave it to the future.
 */


