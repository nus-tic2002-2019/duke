import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");
        //System.out.println("week 4");
        //line = in.nextLine();
        //System.out.println(line);
        String [] item = new String[100];
        int index = 0;

        //line = in.nextLine();
        //System.out.println(line);
        do{

            line = in.nextLine();
            if (line.equals("list")){
                int k = 1;
                for (int i = 0; i< index; i++){

                    System.out.println(k + ". " + item[i]);
                    k++;
                }
            }else{
                item[index] = line;
                index++;
                System.out.println("added: " + line);
            }
            //item[index] = line;
            //index++;
            //System.out.println("added: " + line);

        }while(!line.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
    }
}