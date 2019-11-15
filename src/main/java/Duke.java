import exceptions.EmptyException;
import exceptions.IndexOutOfRangeException;
import exceptions.StringFormatException;
import tasklist.Deadline;
import tasklist.Event;
import tasklist.Task;
import tasklist.ToDo;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Duke {
   public static ArrayList<Task> taskList = new ArrayList<>();

        public static void main(String[] args) throws FileNotFoundException, ParseException {
            Storage.loadFile();
            UI.printDuke();
            String line;
            Scanner in = new Scanner(System.in);

            while (true) {
                line = in.nextLine();
                if (line.equals("bye")) {
                    UI.bye();
                    break;
                } else if (line.equals("list")) {
                    list();

                } else if (line.contains("done")) {
                    done(line);

                } else if (line.contains("event")) {
                    deadlineEvent(line);

                } else if (line.contains("deadline")) {
                    deadlineEvent(line);

                } else if (line.contains("todo")) {
                    todoTask(line);

                } else if (line.contains("delete")) {
                    deleteTask(line);

                } else if (line.equals("save")) {
                    save();

                } else if (line.contains("find")) {
                    searchDate(line);

                } else {
                    invalidTask(line);
                }

            }
        }

    // functions
    static void save() throws FileNotFoundException {
        String list = "";
        for (int i = 0; i < taskList.size(); i++) {
            list += taskList.get(i).saveFormat() + "\n";
        }
        Storage.writeToFile(list);
        UI.printTaskSaved();
    }

    static void list() {
            if (!(taskList.isEmpty())) {
            UI.printOutput((taskList));
        }else{
                UI.printListEmpty();
            }
    }

    static void done(String line){
        try {
            String theStr = line.substring(5);
            String[] strArr = theStr.split(",");
            int[] intArr = new int[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                String num = strArr[i];
                intArr[i] = Integer.parseInt(num);
                indexOutOfRange(taskList.size(), intArr[i]);
            }

            UI.printLine();
            UI.printMarkedAsDone();

            for (int i = 0; i < intArr.length; i++) {
                Task t = taskList.get(intArr[i] - 1);
                t.markAsDone();
                UI.printInLine(" " + t.toString());
            }

            UI.printLine();
        }
        catch (NumberFormatException e) {
            UI.printNumberFormatException();
        }
        catch (IndexOutOfRangeException e) {
            UI.printIndexOutOfRangeException();
        }
    }

    static void todoTask(String line) {
        String taskDescription = line.substring(5);
        Task t = new ToDo(taskDescription);
        taskList.add(t);
        UI.printLine();
        UI.printAddedTask();
        UI.printTask(taskList);
        UI.printNumberOfTasks(taskList);
        UI.printLine();
    }

    static void deadlineEvent(String line) {
        if (line.charAt(0) == 'e') {
            if (line.contains("/at")) {
                String[] str = line.split(" /at ", 2); //str[1] to get date
                String[] taskDescription = str[0].split(" ", 2); //description[1] to get description
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                try {
                    Date date = dateFormat.parse(str[1]);
                    String strDate = dateFormat.format(date);
                    Task t = new Event(taskDescription[1], strDate);
                    taskList.add(t);

                } catch (ParseException e) {
                    UI.printParseException();
                }

            } else {
                String taskDescription = line.substring(6);
                Task t = new Event(taskDescription, "Date not specified");
                taskList.add(t);
            }
        }else if (line.charAt(0) == 'd'){
            if (line.contains("/by")) {
                String[] str = line.split(" /by ", 2); //str[1] to get date
                String[] taskDescription = str[0].split(" ", 2); //description[1] to get description
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                try {
                    Date date = dateFormat.parse(str[1]);
                    String strDate = dateFormat.format(date);
                    Task t = new Deadline(taskDescription[1], strDate);
                    taskList.add(t);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }else {
                String taskDescription = line.substring(9);
                Task t = new Deadline(taskDescription, "Date not specified");
                taskList.add(t);
            }
        }
        UI.printLine();
        UI.printAddedTask();
        UI.printTask(taskList);
        UI.printNumberOfTasks(taskList);
        UI.printLine();

    }


    static void deleteTask(String line) {
        try {

            String theStr = line.substring(7, line.length());
            String[] strArr = theStr.split(",");
            int[] intArr = new int[strArr.length];

            for (int i = 0; i < strArr.length; i++) {
                String num = strArr[i];
                intArr[i] = Integer.parseInt(num);
                indexOutOfRange(taskList.size(), intArr[i]);
            }

            UI.printLine();
            UI.printRemoveTask();

            for (int i = 0; i < intArr.length; i++) {
                Task t = taskList.get(intArr[i]-(i+1));
                UI.printInLine(" " + t.toString());
                taskList.remove(intArr[i]-(i+1));
            }

            UI.printNumberOfTasks(taskList);
            UI.printLine();
        } catch (NumberFormatException e) {
            UI.printNumberFormatException();
        } catch (IndexOutOfRangeException e) {
            UI.printIndexOutOfRangeException();
        }
    }


    static void invalidTask(String line) {
        try {
            checkEmpty(line);
            containsWord(line);
        } catch (EmptyException e) {
            UI.printEmptyException();
        } catch (StringFormatException e) {
            UI.printStringFormatException();
        }
    }


    static void searchDate(String line) throws ParseException {
        ArrayList<Task> foundTasks = new ArrayList<>();
        String[] str;
        try {
            containsWordSearch(line);
            if (line.contains("on")) {
                str = line.split(" on ", 2);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                Date date = dateFormat.parse(str[1]);
                String taskType = str[0].substring(5).toLowerCase();


                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).findDate(date, taskType)) {
                        foundTasks.add(taskList.get(i));
                    }
                }
            }

            if (line.contains("from")) {
                str = line.split(" from ", 2);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                Date date = dateFormat.parse(str[1]);

                String taskType = str[0].substring(5).toLowerCase();


                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).findFromDateRange(date, taskType)) {
                        foundTasks.add(taskList.get(i));
                    }
                }
            }

            if (line.contains("between") && line.contains("to")) {
                str = line.split(" between ", 2);
                String[] dateRange = str[1].split(" to ", 2); //from: dateRange[0], to: dateRange[1]
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                Date date1 = dateFormat.parse(dateRange[0]);
                Date date2 = dateFormat.parse(dateRange[1]);
                String taskType = str[0].substring(5).toLowerCase();

                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).findBetweenDateRange(date1, date2, taskType)) {
                        foundTasks.add(taskList.get(i));
                    }
                }
            }

            if (line.contains("all")) {
                str = line.split(" ", 3);
                String type = str[2].toLowerCase();

                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).taskType(type)) {
                        foundTasks.add(taskList.get(i));
                    }
                }
            }
            UI.printOutput((foundTasks));
        }catch (StringFormatException e){
            UI.printStringFormatException();
        }

    }

    //exceptions
    static void containsWord(String description) throws StringFormatException {
        if ( !( description.equals("bye") || description.equals("list") || description.contains("/"))) {
            throw new StringFormatException ();
        }
    }

    static void containsWordSearch(String description) throws StringFormatException {
        if ( ! (description.contains("on") || description.contains("from") || description.contains("all") || (description.contains("between") && description.contains("to")) )) {
            throw new StringFormatException ();
        }
    }

    static void checkEmpty(String description) throws EmptyException {
        if (description.isEmpty()){
            throw new EmptyException ();
        }
    }

    static void indexOutOfRange(int size,  int number) throws IndexOutOfRangeException {
        if (number > size || number < 0) {
            throw new IndexOutOfRangeException();
        }

    }

}

