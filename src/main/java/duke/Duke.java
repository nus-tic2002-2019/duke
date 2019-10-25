package duke;

import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " __        _        \n"
                + "|  _ \\ _   | | __ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| || | || |   <  __/\n"
                + "|_/ \\,||\\\\___|\n";

        int k=0;
        String input[]=new String[100];
        System.out.println("________________" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("________________");

        Scanner in=new Scanner(System.in);

        String line;
        line =in.nextLine();
        while(!line.equalsIgnoreCase("bye")){
            System.out.println("________________");
            if(line.equalsIgnoreCase("list")){
                for(int i=0;i<k;i++){
                    System.out.println(i+1+". "+input[i]);
                }

            }else{

                System.out.println("added: "+line);
                input[k]=line;
                k++;
            }
            System.out.println("________________");
            line=in.nextLine();
        }
        System.out.println("________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________");

    }
}