package command;

import parser.Parser;
import storage.Storage;
import tasklist.Deadlines;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

//import java.util.Date;

import exception.DukeException;


import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static util.Util.convertDateTime;

public class DeadlineCommand extends Command{
    /**
     *
     * @param commandline
     */
    public DeadlineCommand(String commandline){

        super(commandline);
    }


    /**
     * This method to add Deadline tasks to the memory
     *
     * particular command supported by the application
     *
     * @param tasks   tasks object passed and used throughtout the program
     * @param ui ui object passed and used to interact with UI related object
     * @param storage storage object passed and used to interact with storage related object
     * @throws DukeException return any DukeException if any
     * @throws IOException  return any IOException if any
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try {
            //tasks.addTask(new Deadlines(Parser.parseDeadline(commandline)[0], Parser.parseDeadline(commandline)[1]), true);
            //System.out.println("This is datetime: ##" + Parser.parseDeadline(commandline)[1] + "##");
            tasks.addTask(new Deadlines(Parser.parseDeadline(commandline)[0], convertDateTime(Parser.parseDeadline(commandline)[1])),false);
        }
        catch (DukeException e){
            Ui.showError(e.getMessage());
        }
    }

    /**
     * To read file and add deadline tasks to the memory, and store in tasks object to be used by application
     * @param tasks tasks object passed and used throughtout the program
     * @throws DukeException throw any DukeException if any
     * @throws IOException throw any IOException if any
     */
    public void readFileFormat(TaskList tasks) throws DukeException, IOException {
        try {
            tasks.addTask(new Deadlines(Parser.parseDeadlineFile(commandline)[2], convertDateTime(Parser.parseDeadlineFile(commandline)[3])),true);
            if(Parser.parseDeadlineFile(commandline)[1].equals("1")){
                tasks.markDone(tasks.size(),true);
            }
        }
        catch (DukeException e){
            Ui.showError(e.getMessage());
        }
    }
}
