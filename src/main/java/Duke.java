import exceptions.EmptyException;
import exceptions.IndexOutOfRangeException;
import exceptions.ListEmptyException;
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


    //Format and save tasks to file in String e.g D | 0 | do homework | 11 Apr 2019
    /****
     *
     * @throws FileNotFoundException if file can't be found
     */
    static void save() throws FileNotFoundException {
        String list = "";
        try {
            listEmpty(taskList);
            assert taskList != null && taskList.size() > 0 : "List variable is null or empty";
            for (int i = 0; i < taskList.size(); i++) {
                list += taskList.get(i).saveFormat() + "\n";
            }
            Storage.writeToFile(list);
            UI.printTaskSaved();
        }catch  (ListEmptyException e) {
            UI.printListEmpty();
        }
    }

    //Output all the tasks
    static void list() {
        try {
            listEmpty(taskList);
            UI.printOutput((taskList));
        } catch (ListEmptyException e) {
            UI.printListEmpty();
        }
    }


    //Mark multiple tasks as done - implementation for C-MassOps e.g done 1,2
    /****
     *
     * @param line the command that user input
     */
    static void done(String line){
        try {
            listEmpty(taskList);
            String theStr = line.substring(5);
            String[] strArr = theStr.split(",");
            if (strArr[0].equals("all")){
                for (int i = 0; i < taskList.size(); i++) {
                    Task t = taskList.get(i);
                    t.markAsDone();
                }
                UI.printOutput((taskList));
            }else {
                int[] intArr = new int[strArr.length];
                for (int i = 0; i < strArr.length; i++) {
                    String num = strArr[i];
                    intArr[i] = Integer.parseInt(num);
                    indexOutOfRange(taskList.size(), intArr[i]);
                }

                UI.printLine();
                UI.printMarkedAsDone();

                assert intArr != null && intArr.length > 0 : "List variable is null or empty";
                for (int i = 0; i < intArr.length; i++) {
                    Task t = taskList.get(intArr[i] - 1);
                    t.markAsDone();
                    UI.printInLine(" " + t.toString());
                }

                UI.printLine();
            }
        }
        catch (NumberFormatException e) {
            UI.printNumberFormatException();
        }
        catch (IndexOutOfRangeException e) {
            UI.printIndexOutOfRangeException();
        }
        catch  (ListEmptyException e) {
            UI.printListEmpty();
        }
    }

    //Add new tasks
    /****
     *
     * @param line the command that user input
     */
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

    //Add new deadline/event tasks
    /****
     *
     * @param line the command that user input
     */
    static void deadlineEvent(String line) {
        if (line.charAt(0) == 'e') {
            //e.g event dinner /at 12 oct 2019
            if (line.contains("/at")) {
                //str[1] to get date - 12 oct 2019
                String[] str = line.split(" /at ", 2);
                //description[1] to get description - dinner
                String[] taskDescription = str[0].split(" ", 2);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                try {
                    //Format date 12 Oct 2019, to string "12 Oct 2019"
                    Date date = dateFormat.parse(str[1]);
                    String strDate = dateFormat.format(date);
                    Task t = new Event(taskDescription[1], strDate);
                    taskList.add(t);

                } catch (ParseException e) {
                    UI.printParseException();
                }

            } else {
                //e.g event dinner
                String taskDescription = line.substring(6);
                Task t = new Event(taskDescription, "Date not specified");
                taskList.add(t);
            }
        }else if (line.charAt(0) == 'd'){
            //e.g deadline duke /by 12 oct 2019
            if (line.contains("/by")) {
                //str[1] to get date - 12 oct 2019
                String[] str = line.split(" /by ", 2);
                //description[1] to get description - duke
                String[] taskDescription = str[0].split(" ", 2);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                try {
                    //Format date 12 Oct 2019, to string "12 Oct 2019"
                    Date date = dateFormat.parse(str[1]);
                    String strDate = dateFormat.format(date);
                    Task t = new Deadline(taskDescription[1], strDate);
                    taskList.add(t);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }else {
                //e.g deadline duke
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

    //Delete multiple tasks - implementation for C-MassOps
    /****
     *
     * @param line the command that user input
     */
    static void deleteTask(String line) {
            try {
            listEmpty(taskList);
            String theStr = line.substring(7);
            String[] strArr = theStr.split(",");
            if (strArr[0].equals("all")){
                UI.printOutput((taskList));
                taskList.clear();
                UI.printNumberOfTasks(taskList);
            }else {
                int[] intArr = new int[strArr.length];

                assert intArr != null && intArr.length > 0 : "List variable is null or empty";
                for (int i = 0; i < strArr.length; i++) {
                    String num = strArr[i];
                    intArr[i] = Integer.parseInt(num);
                    indexOutOfRange(taskList.size(), intArr[i]);
                }

                UI.printLine();
                UI.printRemoveTask();

                for (int i = 0; i < intArr.length; i++) {
                    Task t = taskList.get(intArr[i] - (i + 1));
                    UI.printInLine(" " + t.toString());
                    taskList.remove(intArr[i] - (i + 1));
                }

                UI.printNumberOfTasks(taskList);
                UI.printLine();
            }
        } catch (NumberFormatException e) {
            UI.printNumberFormatException();
        } catch (IndexOutOfRangeException e) {
            UI.printIndexOutOfRangeException();
        } catch  (ListEmptyException e) {
            UI.printListEmpty();
        }
    }

    //User input invalid command
    /****
     *
     * @param line the command that user input
     */
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

    //Search date using "on", "from", "between to"
    //Search by task type
    /****
     *
     * @param line the command that user input
     * @throws ParseException if date format is not dd MMM yyyy
     */
    static void searchDate(String line) throws ParseException {
        ArrayList<Task> foundTasks = new ArrayList<>();
        String[] str;
                try {
                    listEmpty(taskList);
                    containsWordSearch(line);
                    //e.g find EVENT on 12 oct 2019
                    if (line.contains("on")) {
                        str = line.split(" on ", 2);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                        //Format string "12 oct 2019" to date
                        Date date = dateFormat.parse(str[1]);
                        //Get task type e.g event
                        String taskType = str[0].substring(5).toLowerCase();

                        assert taskList != null && taskList.size() > 0 : "List variable is null or empty";
                        //Loop taskList to check conditions for findDate
                        for (int i = 0; i < taskList.size(); i++) {
                            if (taskList.get(i).findDate(date, taskType)) {
                                foundTasks.add(taskList.get(i));
                            }
                        }
                    }

                    //e.g find EVENT from 12 oct 2019
                    if (line.contains("from")) {
                        str = line.split(" from ", 2);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                        //Format string "12 oct 2019" to date
                        Date date = dateFormat.parse(str[1]);
                        String taskType = str[0].substring(5).toLowerCase();

                        assert taskList != null && taskList.size() > 0 : "List variable is null or empty";
                        //Loop taskList to check conditions for findFromDateRange
                        for (int i = 0; i < taskList.size(); i++) {
                            if (taskList.get(i).findFromDateRange(date, taskType)) {
                                foundTasks.add(taskList.get(i));
                            }
                        }
                    }

                    //e.g find EVENT between 12 oct 2019 to 16 oct 2019
                    if (line.contains("between") && line.contains("to")) {
                        str = line.split(" between ", 2);
                        //From: dateRange[0], To: dateRange[1]
                        String[] dateRange = str[1].split(" to ", 2);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                        //Format string "12 oct 2019" to date
                        Date date1 = dateFormat.parse(dateRange[0]);
                        Date date2 = dateFormat.parse(dateRange[1]);
                        String taskType = str[0].substring(5).toLowerCase();

                        assert taskList != null && taskList.size() > 0 : "List variable is null or empty";
                        //Loop taskList to check conditions for findBetweenDateRange
                        for (int i = 0; i < taskList.size(); i++) {
                            if (taskList.get(i).findBetweenDateRange(date1, date2, taskType)) {
                                foundTasks.add(taskList.get(i));
                            }
                        }
                    }

                    //e.g find all EVENTS
                    if (line.contains("all")) {
                        str = line.split(" ", 3);
                        //e.g "events"
                        String type = str[2].toLowerCase();

                        assert taskList != null && taskList.size() > 0 : "List variable is null or empty";
                        //Loop taskList to check conditions for taskType
                        for (int i = 0; i < taskList.size(); i++) {
                            if (taskList.get(i).taskType(type)) {
                                foundTasks.add(taskList.get(i));
                            }
                        }
                    }
                    UI.printOutput((foundTasks));
                } catch (StringFormatException e) {
                    UI.printStringFormatException();
                } catch (ListEmptyException e) {
                   UI.printListEmpty();
                }

    }

    //Exceptions

    /****
     *
     * @param description the command that user input
     * @throws StringFormatException if description does not hit the if conditions
     */
    static void containsWord(String description) throws StringFormatException {
        if ( !( description.equals("bye") || description.equals("list") || description.contains("/"))) {
            throw new StringFormatException ();
        }
    }

    /****
     *
     * @param description the command that user input
     * @throws StringFormatException if description does not hit the if conditions
     */
    static void containsWordSearch(String description) throws StringFormatException {
        if ( ! (description.contains("on") || description.contains("from") || description.contains("all") || (description.contains("between") && description.contains("to")) )) {
            throw new StringFormatException ();
        }
    }

    /****
     *
     * @param taskList taskList arr that stores all tasks
     * @throws ListEmptyException if list is empty
     */
    static void listEmpty( ArrayList<Task> taskList) throws ListEmptyException {
        if (taskList.isEmpty()) {
            throw new ListEmptyException ();
        }
    }


    /****
     *
     * @param description the command that user input
     * @throws EmptyException if user did not input anything
     */
    static void checkEmpty(String description) throws EmptyException {
        if (description.isEmpty()){
            throw new EmptyException ();
        }
    }

    /****
     *
     * @param size the size of the taskList e.g 5
     * @param number the task number that the user input e.g user input: delete 6
     * @throws IndexOutOfRangeException if the number is greater than the size of the taskList e.g 6 > 5, or number < 0
     */
    static void indexOutOfRange(int size,  int number) throws IndexOutOfRangeException {
        if (number > size || number < 0) {
            throw new IndexOutOfRangeException();
        }

    }

}

