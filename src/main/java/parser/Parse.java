//level 7.more oop

/**
 * Implement Level 8 : date & time
 */

package parser;

import exceptions.DukeException;
import exceptions.Errortype;
import task.Deadlines;
import task.Events;
import task.Task;
import task.Todo;
import ui.Ui;

import java.lang.reflect.Array;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Parse {

    private static boolean isExit;
    private static Errortype errorType = new Errortype();
    private static Ui ui = new Ui();
    //private static Integer index;

    public Parse() {
        this.isExit = false;

    }

    public static Boolean isExit() {
        return isExit;
    }

    public void parser(ArrayList<Task> t) {

        //Level 8 date & time
        LocalDateTime resultDateTime = null;
        LocalDateTime notDate = LocalDateTime.parse("1900-01-01T00:00");


        Integer idx = 0;
        String command = "";
        String taskString = "";
        String timeString = "";
        String user_input = "";

        Scanner in = new Scanner(System.in);
        user_input = in.nextLine();
        command = user_input.split(" ")[0].toLowerCase();


        try {
            command = user_input.split(" ")[0].toLowerCase();
        } catch (ArrayIndexOutOfBoundsException e) {
            command = "";
        }


        switch (command) {
            case "list":
                ui.list(t, t.size());
                break;

            case "todo":
                if (Errortype.isTask(user_input)) {
                    taskString = user_input.replace("todo", "").trim();
                    t.add(new Todo(taskString));
                    ui.added(t, t.size());
                }

                break;

            case "deadline":
                if (errorType.isTask(user_input) && errorType.isSchedule(user_input)) {
                    taskString = user_input.split("/")[0].replace("deadline", "").trim();
                    timeString = user_input.split("/")[1].replace("by", "").trim();
                    //t.add(new Deadlines(taskString, timeString));
                    //Ui.added(t, t.size());

                    resultDateTime = dateParser(timeString);

                    if (!resultDateTime.equals(notDate)) {
                        t.add(new Deadlines(taskString, resultDateTime));
                        ui.added(t, t.size());
                    }

                }
                break;

            case "event":
                if (errorType.isTask(user_input) && errorType.isSchedule(user_input)) {
                    taskString = user_input.split("/")[0].replace("event", "").trim();
                    timeString = user_input.split("/")[1].replace("at", "").trim();
                    //t.add(new Events(taskString, timeString));
                    //Ui.added(t, t.size());

                    resultDateTime = dateParser(timeString);

                    if (!resultDateTime.equals(notDate)) {
                        t.add(new Events(taskString, resultDateTime));
                        ui.added(t, t.size());
                    }

                }
                break;

            case "done":
                if (!(user_input.trim().length() > 4)) {
                    ui.invalid();
                    break;
                }

                //idx = Errortype.toInteger(user_input.split(" ")[1], t.size());
                idx = errorType.toInteger(user_input.split(" ")[1], t.size());
                if (idx == -1) {
                    System.out.println("Oops, please provide a valid task number.");
                    break;
                }

                t.get(idx - 1).taskDone();
                Ui.done(t, idx);
                //index = idx;
                break;

            case "undo":
                if (!(user_input.trim().length() > 4)) {
                    ui.invalid();
                    break;
                }

                //idx = Errortype.toInteger(user_input.split(" ")[1], t.size());
                idx = errorType.toInteger(user_input.split(" ")[1], t.size());
                if (idx == -1) {
                    System.out.println("Oops, please provide a valid task number.");
                    break;
                }

                t.get(idx - 1).taskUndo();
                Ui.undo(t, idx);
                //index = idx;
                break;

            case "delete":
                if (!(user_input.trim().length() > 6)) {
                    ui.invalid();
                    break;
                }

                idx = errorType.toInteger(user_input.split(" ")[1], t.size());
                if (idx == -1) {
                    System.out.println("Oops, please provide a valid task number.");
                    break;
                }
                ui.delete(t, idx);
                t.remove(idx - 1);

                //this.index = idx;
                break;

            case "find":
                if (errorType.isTask(user_input)) {
                    String wordToFind = user_input.replace("find", "").trim().toLowerCase();
                    findKeyword(t, wordToFind);
                }
                break;

            case "sort":
                sortTask(t);
                break;

            case "duplicate":
                if (errorType.isTask(user_input)) {
                    String wordToFind = user_input.replace("duplicate", "").trim().toLowerCase();
                    DuplicatesInArray(t);
                }
                break;


            case "bye":
                ui.bye();
                this.isExit = true;
                break;

            default:
                ui.invalid();
                //this.index = -1;
                break;
        }

    }

    public static LocalDateTime dateParser(String date) {

        LocalDateTime resultDateTime = LocalDateTime.parse("1900-01-01T00:00"); // flag to represent error
        LocalDate resultDate = null;
        String dateTemp = "";
        String timeTemp = "";
        String dateReverse = "";
        String timeMin = "";
        String timeHrs = "";

        dateTemp = date.split(" ")[0]; // convert to 2019-11-01 form
        assert dateTemp.length() == 10 : dateTemp.length();

        if (errorType.isTime(date)) { //check if there is a time component
            timeTemp = date.split(" ")[1]; // if true, possibly there is time given
            try {
                timeHrs = timeTemp.substring(0, 2);
                timeMin = timeTemp.substring(2, 4);
            } catch (ArrayIndexOutOfBoundsException e1) {
                System.out.println("Not a valid time of format hhmm.");
                return resultDateTime;
            }
        }

        try {
            resultDate = LocalDate.parse(dateTemp); //try original date form
        } catch (DateTimeParseException e2) {
            try {
                // assume date format 01-11-2019 then change to this format 2019-11-01
                dateReverse = dateTemp.split("-")[2] + "-" + dateTemp.split("-")[1] + "-" + dateTemp.split("-")[0];
            } catch (ArrayIndexOutOfBoundsException e1) {
                System.out.println("Not a valid date of format yyyy-mm-dd");
                return resultDateTime;
            }
            try {
                //date swap successful, now parse the swapped date
                resultDate = LocalDate.parse(dateReverse);
            } catch (DateTimeParseException e3) {
                //dateConfirm is not a date
                System.out.println("Not a valid date of format yyyy-mm-dd.");
                return resultDateTime;
            }
        }
        // date format passed testing. to add time component behind date.
        try {
            resultDateTime = resultDate.atTime(Integer.parseInt(timeHrs), Integer.parseInt(timeMin));
        } catch (DateTimeException e4) {
            System.out.println("Not a valid time.");
            return resultDateTime;
        } catch (NumberFormatException e5) {
            System.out.println("Not a valid time, contains non-numbers");
            return resultDateTime;
        }
        return resultDateTime;
    }

    public static void findKeyword(ArrayList<Task> t, String wordToFind) {
        Boolean isFound = false;
        for (int i = 0; i < t.size(); i++) {
            int n = t.get(i).getDescription().toLowerCase().indexOf(wordToFind);
            //int n = taskString.indexOf(wordToFind);
            if (n >= 0) {
                ui.singleList(t, i);
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.println("Oops!! Cannot find what you looking for.");
        }
    }

    public static void sortTask(ArrayList<Task> t) {
        // sort [T} to the top first
        Boolean isFound = false;
        int number_of_Todo_list = 0;
        for (int i = 0; i < t.size(); i++) {
            String command = t.get(i).toString().substring(1, 2);
            if (command.equals("T")) {
                Collections.swap(t, number_of_Todo_list, i);
                number_of_Todo_list += 1;
            }
        }

        boolean isSwap = false;
        while (!isSwap) {
            isSwap = true;
            for (int i = number_of_Todo_list + 1; i < t.size(); i++) {
                if (t.get(i - 1).getDate().isAfter(t.get(i).getDate())) {
                    Collections.swap(t, i - 1, i);
                    isSwap = false;
                }

            }

        }
        ui.list(t, t.size());
    }

    public static void DuplicatesInArray(ArrayList<Task> t) {

        Boolean isFound = false;
        // First solution : finding duplicates using brute force method
        System.out.println("Finding duplicate elements in array using brute force method");
        {
            for (int i = 0; i < t.size(); i++) {
                int n = t.get(i).getDescription().toLowerCase().indexOf(i);
                //for (int j = i + 1; j < names.length; j++) {
                for (int j = i + 1; j < t.size(); j++) {
                    int m = t.get(j).getDescription().toLowerCase().indexOf(j);
                    //if (names[i].equals(names[j])) {
                    //if (t[i].equals(t[j])) {
                    if (n >= m) {

                        ui.singleList(t, i);
                        isFound = true;
                        // got the duplicate element
                    }
                }
                if (!isFound) {
                    System.out.println("Oops!! Cannot find what you looking for.");
                }
            }
        }

    }


}
