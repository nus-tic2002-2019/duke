package main;

import main.TaskLists.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    // TODO - Create a Storage Class
    public static ArrayList<Task> Tasks = new ArrayList<>();



    public static void main(String[] args) throws DukeException, IOException {

        Storage storage = new Storage("C:\\Users\\yralle.lesly.gimpaya\\Desktop\\duke\\src\\data\\tasks.txt");
        Parser response = new Parser();
        UI interaction = new UI();

        interaction.welcome();
        storage.start();


        while (response.isTrue) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            response.parse(input);
        }

        storage.save();

    }
}