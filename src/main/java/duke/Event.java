package duke;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/***
 * class created to store event tasks
 */
public class Event extends Task{
        protected LocalDate at;
        public Event (String taskName, boolean taskDone, String at)
        {
            super(taskName, taskDone); // calls the parent constructor
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
            this.at = LocalDate.parse(at, formatter);

        }
    public Event (String taskName, boolean taskDone, LocalDate date)
    {
        super(taskName, taskDone); // calls the parent constructor
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        at = date;

    }
    /***
     * default constructor
     */
    public Event() {
        super();
    }

    /***
     * to return input as a converted string output
     * @return
     */
    public String toString()
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy"); //changing to format
            return "[E]" + super.toString() + " (at: "+ at.format(formatter) + ")";
        }

    /***
     * write event task into storage
     * @param storage
     * @throws IOException
     */
    public void write(FileWriter storage) throws IOException {
        storage.write("E\n"); //to represent as todo
        super.write(storage);
        storage.write(at + "\n");
    }

    /***
     * to read event task from storage file
     * @param fileRead
     * @throws IOException
     */
    public void read(BufferedReader fileRead) throws IOException
    {
        super.read(fileRead);
        String date = fileRead.readLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        this.at = LocalDate.parse(date, formatter);
    }

}
