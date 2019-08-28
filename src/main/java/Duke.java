import java.util.Scanner;
import java.util.Arrays;


public class Duke {

    /*public static String[] add_task(String task){
        String[] tasklist = new String[100];
        int count=0;
        for (int i=0; i<tasklist.length; i++){
            tasklist[i] = task;
            count++;
            System.out.println(Arrays.toString(tasklist));
        }
        return Arrays.copyOf(tasklist, count);
    }*/

    /*public static void print_list(){

    }*/

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("_________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("_________________________________________");
        String[] inputs = new String[100];
        line = in.nextLine();
        int word_count = 0;
        while (!line.contains("bye")) {
            //add inputs into array
            if (!line.contains("list")){
                inputs[word_count] = line;
                System.out.println("_________________________________________");
                System.out.println("added: " + line);
                System.out.println("_________________________________________");
                word_count++;

                //add_task(line);

            }

            /*if (line.contains("bye")) {
                System.out.println("_________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("_________________________________________");

            }*/
            if (line.contains("list")) {
                System.out.println("_________________________________________");
                //print_list();
                String[] print_array = Arrays.copyOf(inputs, word_count);
                System.out.println(Arrays.toString(print_array));
                System.out.println("_________________________________________");

            }

            line = in.nextLine();
        }
        System.out.println("_________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_________________________________________");
    }
}
