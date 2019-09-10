import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        ArrayList<String> toDoList = new ArrayList<String>();
        greet();
        echo(toDoList);
    }

    public static void dukePrint(String input) {
        input = input.replace("\n", "\n\t");
        System.out.println("\t" + input);
        System.out.println("__________________________________________________________________________\n");
    }

    public static void greet() {
        dukePrint("Harrowwwwwww\nWut iz up?");
    }

    public static void echo(ArrayList<String> toDoList) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String input = in.nextLine();
            switch(input) {
                case("bye"):
                    dukePrint("BYEBYE!");
                    break;
                case("list"):
                    displayList(toDoList);
                    continue;
                default:
                    toDoList.add(input);
                    dukePrint("added: " + input);
            }
        }
    }


    public static void displayList(ArrayList<String> toDoList) {
        //System.out.println("displayList initiated");
        String input = "Yessir! Here is the list!\n";
        for(int i = 0; i < toDoList.size(); ++i) {
            input += "\t" + (i+1) + ". " + toDoList.get(i) + "\n";
        }
        dukePrint(input);
    }
}



