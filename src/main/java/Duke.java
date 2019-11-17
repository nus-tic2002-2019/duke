import java.util.Arrays;
import java.util.Scanner;
import Exception_Package.DukeException;
import Parser.*;
import Ui.Ui;

public class Duke {

    public static String userInput;

    public static void main(String[] args) {

        String[] userInputArray = new String[100];

        Ui.welcome();
        userInputArray[0] = " ";

        Scanner input = new Scanner(System.in);

        System.out.println("Please enter task");

        while (!userInputArray[0].equals("bye") || !userInputArray[0].equals("exit")) {
            try {
                userInput = input.nextLine();
                userInputArray = userInput.split(" ");

                Parser.parseTaskCommand(userInput);
                System.out.println("Please type exit if want to quit program");
            } catch (DukeException e) {
                Ui.keywordError();
            } catch (StringIndexOutOfBoundsException e) {
                Ui.keywordError();
            }
        }
    }
}

