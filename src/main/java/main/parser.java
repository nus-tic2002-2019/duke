package main;


import main.Commands.*;

/**
 * Parse the input of the user and returns a command based on the input.
 * @return Command  The command with reference to the given input.
 */
public class Parser {

    public static boolean isTrue;

    Parser() {
        this.isTrue = true;
    }

    //TODO Add exception here
    public static void parse(String input) {
        Command action = null;

        // Takes the first word of the input
        String word = input.split(" ")[0];


        //Parse the comment to the correct Command Action
        switch (word.toUpperCase()){

           case "BYE":
                action = new ByeCommand(input);
                break;

            case "LIST":
                action = new ListCommand(input);
                break;

            case "DONE":
                action = new DoneCommand(input.split(" ")[1]);
                break;

            default:
                action = new AddCommand(input);

        }


    }



}
