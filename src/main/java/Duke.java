
import java.util.Arrays;
import java.util.Scanner;

import Exception_Package.DukeException;
import Parser.*;
import Ui.*;


public class Duke {

    public static String userInput;

    public static void main(String[] args) {

        String[] userInputArray= new String[100];


        Ui.welcome();
        try {
            Scanner input = new Scanner(System.in);
            Arrays.fill(Ui.mark, "\u2718");  //initialize mark array = "x"
            System.out.println("Please enter login or bye to exit");
            userInputArray = input.nextLine().split(" ");
            System.out.println("Please enter task");

            while(!userInputArray[0].equals("bye")) {
                userInput = input.nextLine();
                Parser.parseTaskCommand(userInput);
                System.out.println("Please type exit if want to quit program");
            }
            userInputArray[0].equals("exit");
                System.out.println("Logout");
        }
        catch ( DukeException e){
//                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            Ui.keywordError();
        }
    }
}

