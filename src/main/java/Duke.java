import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Commands.Command;
import Parser.Parser;
import Tasks.*;
import Storage.*;
import Exceptions.*;
import TaskList.*;
import Ui.Ui;


public class Duke {

    private Storage storage;
    private TaskList tasklist;
    private Ui ui;
    private Parser parser;

    public Duke(){
        ui = new Ui();
        storage = new Storage();
        try {
            storage.load();
            tasklist = new TaskList();
        }
        catch (FileNotFoundException e){
            ui.showLoadingError();
            tasklist = new TaskList();
        }

    }




    public void runDuke(){
        ui.Greet();
        parser = new Parser();
        boolean exit = true;
        //System.out.println(" WHAT IS THIS EXIT STATUS : " + exit);
        int i = 0;
        while(exit){
            try {
                //exit = parser.getExitStatus();

                String cmd =  ui.readCommand();

                Command c = Parser.parse(cmd,tasklist);
                c.execute(tasklist, ui, storage);
                storage.saveFile(tasklist.getAllTasks());
                //exit = c.isExit();

//                if(!exit){
//                    exit = c.exit;
//                    //break;
//                }
                System.out.println(" WHAT IS THIS EXIT STATUS : " + exit);

            }
            catch (Exception e){
                ui.showLoadingError();
            }
            finally {
                //ui.printLine();

            }
        }
    }

    public static void main(String[] args) {

        //while(exit(echo()));
        //while (echo());
        new Duke().runDuke();



    }

}
