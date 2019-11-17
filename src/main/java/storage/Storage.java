/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duke.storage;

import duke.exception.DukeException;
import duke.task.*;
import duke.ui.Ui;
import java.io.*;
import java.text.*;
import java.util.*;
/**
 *
 * @author lug3g
 */
public class Storage {
    private String fileName;
    
    public Storage(String fileName){
        this.fileName = fileName;
    }
    
    public void save(TaskList tasks){
        try {
            FileWriter writer = new FileWriter(this.fileName);
            for ( int i = 0; i < tasks.getSize() ; i++ ){
                writer.write(tasks.getTask(i).writeTask() + System.lineSeparator());
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            createFile();
        } catch (IOException ex) {
            Ui.response("☹ OOPS!!! Sorry an error has occured.");
        }
    }
    
    private void createFile(){
        File file = new File(this.fileName);
        file.getParentFile().mkdirs();
        try {
            FileWriter writer = new FileWriter(file);
        } catch (IOException ex) {
            Ui.response("☹ OOPS!!! Sorry an error has occured.");
        }
    }
    
    public ArrayList<Task> load() throws FileNotFoundException{
        Scanner s = new Scanner(new File(this.fileName));
        
        s.useDelimiter(System.getProperty("line.separator"));
            ArrayList<Task> Tasks = new ArrayList<Task>();
            while (s.hasNext()){
                String line = s.next();
                String words[] = line.split(" [|] ");
                if ( words[0].equals("T")) {
                    Tasks.add(new Todo(words[2]));
                }
                if ( words[0].equals("E")) {
                    try {
                        Tasks.add(new Event(words[2],convertDate(words[3])));
                    } catch (ParseException e) {
                        Ui.response("☹ OOPS!!! Please include a valid date description after '/at'.");
                    } catch (DukeException e){
                        Ui.response("☹ OOPS!!! Please include a date description after '/at'.");
                    }
                }
                if ( words[0].equals("D")) {
                    try {
                        Tasks.add(new Deadline(words[2],convertDate(words[3])));
                    } catch (ParseException e) {
                        Ui.response("☹ OOPS!!! Please include a valid date description after '/by'.");
                    } catch (DukeException e){
                        Ui.response("☹ OOPS!!! Please include a date description after '/by'.");
                    }
                }
                if ( words[1].equals("1") ){
                    Tasks.get(Tasks.size()).markAsDone();
                }
            }
        s.close();
        return Tasks;
    }
    
    private static Date convertDate(String stringdate) throws DukeException, ParseException{
        if ( stringdate.equals("") ){
            throw new DukeException();
        }
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(stringdate);  
        return date1;
    }
}
