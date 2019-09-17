import java.util.Scanner;

public class Duke {
    private static String[] storeWords = new String[100];
    private static int storeWordCount = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo +"What can I do for you?\n");

        Echo();

    }

    public static void Echo() {
        String line;
        Scanner in = new Scanner(System.in);
        boolean byebye = true;
        String s1 = "bye";

        while(byebye) {
            System.out.print("Type something: ");
            line = in.nextLine();
            if (s1.equalsIgnoreCase(line)) {
                byebye = false;
            }
            else if(line.equalsIgnoreCase("list")){
                for(int i = 0; i<storeWordCount; i++){
                    System.out.println((i+1) + ". "+ storeWords[i]);
                }
            }
            else{
                storeWords[storeWordCount] = line;
                storeWordCount++;

                System.out.println("added: "+ line);
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}

