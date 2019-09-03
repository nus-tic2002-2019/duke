import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String seperatorLine = "___________________________________________\n";
        String line;
        boolean isSame;
        String[] listArray = new String[100];
        int i = 0, j =0, count = 0;
        boolean listSame = false;

        System.out.print(seperatorLine);
        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you");
        System.out.println(seperatorLine);

        Scanner input = new Scanner(System.in);
        line = input.nextLine();
        listArray[i] = line;
        isSame = line.equals("bye");

        while (!isSame) {

            line = input.nextLine();

            listSame = line.equals("list");

            if(listSame == false){
                System.out.print("   " + seperatorLine);
                System.out.println("      added:" + line);
                System.out.print("   " + seperatorLine);
//                line = input.nextLine();
                isSame = line.equals("bye");
                listArray[i] = line;
                i++;

            }

            else {
//              listSame = line.equals("list");

                count = i;
                for (j = 0; j < count; j++) {
                    System.out.println(j + ": " + listArray[j]);
                }

//                line = input.nextLine();
                i++;
                listArray[i] = line;
                isSame = line.equals("bye");
            }
        }


        System.out.print(seperatorLine);
        System.out.println("       " + "Bye. Hope to see you again soon!");
        System.out.println(seperatorLine);
    }
}







 /*while (!isSame) {

         listSame = line.equals("list");

         if(listSame == false){
         System.out.print("   " + seperatorLine);
         System.out.println("      added:" + line);
         System.out.print("   " + seperatorLine);
         line = input.nextLine();
         i++;
         isSame = line.equals("bye");
         listArray[i] = line;
//            System.out.println(i);
         }

         else {
//              listSame = line.equals("list");

         count = i;
         for (j = 0; j < count; j++) {
        System.out.println(j + ": " + listArray[j]);
        }

        line = input.nextLine();
        i++;
        listArray[i] = line;
        isSame = line.equals("bye");
//                System.out.println(i);
        }
        }*/
