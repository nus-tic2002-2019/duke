//level 7.more oop

/**
 * Main page of Duke
 */

import exceptions.DukeException;
import exceptions.Errortype;
import parser.Parse;
import storage.Storage;
import task.Deadlines;
import task.Events;
import task.Task;
import task.Todo;
import ui.Ui;

import java.io.FileNotFoundException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;


public class Duke {
    //more oop

    //private Storage store;
    private static ArrayList<Task> TaskList = new ArrayList<Task>();
    private static Ui ui = new Ui();
    private static Parse parser = new Parse();
    //private static Storage store = new Storage("src/main/java/data/Duke.txt");
    private static Storage store = new Storage("c:\\temp\\Duke.txt"); //changed to c:\temp\Duke.txt after export to jar file

    public static void main(String[] args){

        Boolean isExit = false;
        ui.welcome();
        store.loadFile(TaskList);

        while(!isExit){
            parser.parser(TaskList);
            isExit = Parse.isExit();
            store.saveFile(TaskList);
        }

    }

}

