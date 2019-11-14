package main;

import main.parsers.ParserText;
import main.taskLists.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


//TODO - Implement individual Features

/**
 *
 * [2] Provide a way to archive items so that the user can remove items from the app but still keep a record of them somewhere
 * e.g., archive all tasks in the list into a file so that the user can start over with a clean slate.
 * <p>
 */


public class Duke {

    public static ArrayList<Task> Tasks = new ArrayList<>();

    public static void main(String[] args) throws DukeException, IOException {

        Storage storage = new Storage("C:\\Users\\yralle.lesly.gimpaya\\Desktop\\duke\\src\\data\\current_tasks.txt");
        ParserText response = new ParserText();


        UI interaction = new UI();

        interaction.welcome();
        storage.start();

        while (response.isTrue) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            response.parsetext(input);
        }

        storage.save();

    }
}