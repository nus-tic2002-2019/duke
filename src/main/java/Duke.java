import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        String input = "";
        DukeTask[] arrayOfList = new DukeTask[100];
        int count = 0;

        while(!input.toLowerCase().equals("bye")) {

            input = in.nextLine();

            //split the string into two array
            String[] values = input.split(" ");

            //compare user input of the first array.

            if (values[0].equals("list")) {

                System.out.println("Here are the tasks in your list:");
                for(int i =0; i<count; i++){
                    System.out.println((i+1) + "." + arrayOfList[i].toString()+ arrayOfList[i].description);
                }
            }

            else {
                //System.out.println("Here"+input);
                arrayOfList[count] = new DukeTask(input, false);

                if (!input.toLowerCase().equals("bye") && (!values[0].equals("done"))){
                    //DukeAdd.add(input);
                    System.out.println("added: " + arrayOfList[count].description);
                    count++;

                }

                if (values[0].equals("done")) {
                    System.out.println("Nice! I've marked this task as done:");
                    int j = Integer.parseInt(values[1])-1;
                    int z = j;
                    arrayOfList[j].markAsDone();
                        System.out.println(arrayOfList[j].toString() + arrayOfList[z].description);

                }

            }
        }
        DukeResponse.bye(input);
    }
}



