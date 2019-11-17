package duke;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Parser {
    /**
     * Reads user input, and identifies the choice.
     *
     * @return String returns the first word value that that the user has entered.
     */
    public static String getChoice(String message) {
        return message.split(" ", 0)[0].toLowerCase();
    }

    /**
     * Reads user input, and identifies the item number of choice. By splitting the message, and taking value of second item minus 1.
     *
     * @return int returns the value of user entered value at 0 indexed for use on ArrayList <Todo> Task.taskList
     */
    public static int getItemNumber(String message) throws DukeException {
        String[] splitMessage = message.split(" ", 0);
        if (splitMessage.length > 1) {
            return Integer.parseInt(splitMessage[1]) - 1;
        }
        throw new DukeException("Missing selection number.");
    }

    /**
     * Reads user input, and identifies the body of the message input. By splitting the message into 2, and taking the latter.
     *
     * @return String returns the description of the message that the user has entered.
     */
    public static String getBody(String message) throws DukeException {
        String[] splitMessage = message.split(" ", 2);
        if (splitMessage.length <= 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return splitMessage[1];
    }

    /**
     * Takes in a string item. Will find todo that contains the string.
     *
     * @return ArrayList<Todo> returns an ArrayList of matching todo.
     */
    public static ArrayList<Todo> find(String item) throws DukeException {
        ArrayList<Todo> foundItems = new ArrayList<Todo>();
        for (int i = 0; i < Task.getSize(); i++) {
            String[] words = Task.getTask(i).getTodo().split(" ");
            for (String word: words) {
                if (word.equals(item)) {
                    foundItems.add(Task.getTask(i));
                }
            }
        }
        return foundItems;
    }

    /**
     * Reads String format of a date input ("d/MM/yyyy") and outputs ("MMM d yyyy, E")
     *
     * @return String returns date format ("MMM d yyyy, E")
     */
    public static String formatDate(String date) throws DukeException {
        try {
            SimpleDateFormat format = new SimpleDateFormat("d/MM/yyyy");
            SimpleDateFormat display = new SimpleDateFormat("MMM d yyyy, E");
            Date formatDate = format.parse(date);
            return display.format(formatDate);
        } catch (Exception error) {
            throw new DukeException("Problem formatting date, please format as 'd/MM/yyyy - (e.g. 20/09/2019)'");
        }
    }

    /**
     * Reads String format of a 24HR time input ("HHmm") and outputs 12HR format ("hhmm a")
     *
     * @return String returns date format ("hhmm a")
     */
    public static String formatTime(String time) throws DukeException {
        try {
            SimpleDateFormat _24HourFormat = new SimpleDateFormat("HHmm");
            SimpleDateFormat _12HourFormat = new SimpleDateFormat("hhmm a");
            Date _24HourDateTime = _24HourFormat.parse(time);
            return _12HourFormat.format(_24HourDateTime);
        } catch (Exception error) {
            throw new DukeException("Problem formatting time, please format as 'HHmm - (e.g. 0800)'");
        }
    }
}
