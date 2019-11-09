package main;

import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    // TODO - Create a Storage Class
    public static ArrayList<Task> Tasks = new ArrayList<>();

    public static void main(String[] args) {

        UI interaction = new UI();
        interaction.welcome();

        Parser response = new Parser();

        while (response.isTrue) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            response.parse(input);
        }
    }
}