package Parser;
import Exception_Package.DukeException;
import Task.Task;
import Ui.Ui;
import Task.Todo;
import Task.Deadline;
import Task.Event;
import TaskList.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Parser {

    public static Task t = new Task("");
    private static String task_words, by_words = " ";
    private static int listPrint, listPrintFind, listNum = 1, doneNumber = 1;
    private static int task_stringIndex_After_taskWord = 0;

    /***
     *
     * @param full_user_input full command and task keywords(event, deadline, todo, list, delete, done, save, find)
     *                        key-in by user in one line
     * @throws DukeException  catch the DukeException
     */
    public static void parseTaskCommand(String full_user_input) throws DukeException {
        String[] input = new String[100];

//        String full_user_input = scannerInput.nextLine();
        input = full_user_input.split(" ");

        task_stringIndex_After_taskWord = full_user_input.indexOf(" ");
        int by_string = full_user_input.indexOf("/");
        by_words = full_user_input.substring(by_string + 3);

        try {
            switch (input[0]) {
            case ("todo"):
                try {
                    if (input[1] != "") {
                        task_words = Parser.parseEventDeadlineTask(full_user_input);
                        Task.addTask(new Todo(task_words));
                        Ui.printEvent(t);
                    }
                } catch (IndexOutOfBoundsException e) {
                    Ui.todoError();
                }
                break;

            case ("deadline"):
                try {
                    if (input[1] != "") {
                        task_words = Parser.parseEventDeadlineTask(full_user_input);
                        Task.addTask(new Deadline(task_words, by_words));
                        Ui.printEvent(t);
                    }
                } catch (IndexOutOfBoundsException e) {
                    Ui.deadlineDateError();
                }
                break;

            case ("event"):
                try {
                    if (input[1] != "") {
                        task_words = Parser.parseEventDeadlineTask(full_user_input);
                        Task.addTask(new Event(task_words, by_words));
                        Ui.printEvent(t);
                    }
                } catch (IndexOutOfBoundsException e) {
                    Ui.eventDateError();
                }
                break;

            case ("delete"):
                try {
//                    if(full_user_input.contains("&")) {
                        task_words = Parser.parseEventDeadlineTask(full_user_input);
                        Ui.deleteTask(Integer.parseInt(task_words.trim()));
//                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter which integer after delete ");
                } catch (IndexOutOfBoundsException e) {
                    Ui.deleteNumberOutOfList();
                }
                break;

            case ("list"):
                int count_todo = Ui.task_count;
                System.out.println(Ui.seperatorLine2);
                for (listPrint = 0; listPrint < count_todo; listPrint++) {
//                    System.out.println("        " + listNum + ". " + "[" + Ui.mark[listPrint] + "]" + TaskList.getTaskList(listPrint));
                    System.out.println("        " + listNum + ". " + "[" + Ui.mark.get(listPrint) + "]" + TaskList.getTaskList(listPrint));

                    listNum++;
                }
                listNum = 1;
                System.out.println(Ui.seperatorLine2);
                break;

            case ("done"):
                try {
                    if (input[1] != "") {
                        task_words = Parser.parseEventDeadlineTask(full_user_input);
                        Ui.doneTask(Integer.parseInt(task_words.trim()), input[1]);
                    }
                } catch (IndexOutOfBoundsException e) {
                    Ui.doneNumberEmptyError();
                } catch (NumberFormatException e) {
                    Ui.doneNumberInvalid();
                }
                break;

            case ("save"):
                try {
                    FileWriter fw = new FileWriter("/Volumes/Macintosh HD 1/Java Project-TIC2002-Duke/duke-project-chunygL/dukesave.txt");
                    File f = new File("dukesave.txt");

                    for (int i = 0; i < TaskList.todoListArraySize(); i++) {
//                        fw.write("        " + "[" + Ui.mark[i] + "]" + TaskList.getTaskList(i) + System.lineSeparator());
                        fw.write("        " + "[" + Ui.mark.get(i) + "]" + TaskList.getTaskList(i) + System.lineSeparator());

                    }
                    System.out.println("File save successfully");
                    fw.close();
                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                } catch (IOException e) {
                    System.out.println("Something went wrong" + e.getMessage());
                }
                break;

            case ("find"):
                int count_todo_find = Ui.task_count;
                System.out.println(Ui.seperatorLine2);
                System.out.println("Here are the matching task in your list");

                task_words = Parser.parseEventDeadlineTask(full_user_input);
                task_words = task_words.trim();
                for (listPrintFind = 0; listPrintFind < count_todo_find; listPrintFind++) {
                    if(TaskList.getTaskList(listPrintFind).contains(task_words)){
//                        System.out.println("        " + listNum + ". " + "[" + Ui.mark[listPrintFind] + "]" + TaskList.getTaskList(listPrintFind));
                        System.out.println("        " + listNum + ". " + "[" + Ui.mark.get(listPrintFind) + "]" + TaskList.getTaskList(listPrintFind));

                    }
                    listNum++;
                }
                listNum = 1;
                System.out.println(Ui.seperatorLine2);
                break;

            case ("view"):
                try{
                    if (input[1] != "") {
                    count_todo_find = Ui.task_count;
                    System.out.println(Ui.seperatorLine2);

                    task_words = Parser.parseEventDeadlineTask(full_user_input);
                    task_words = task_words.trim();

                    for (listPrintFind = 0; listPrintFind < count_todo_find; listPrintFind++) {
                        if (TaskList.getTaskList(listPrintFind).contains(task_words)) {
//                        System.out.println("Here are the matching schedule in your list");
//                        System.out.println("        " + listNum + ". " + "[" + Ui.mark[listPrintFind] + "]" + TaskList.getTaskList(listPrintFind));
                            System.out.println("        " + listNum + ". " + "[" + Ui.mark.get(listPrintFind) + "]" + TaskList.getTaskList(listPrintFind));
                        }
                    }
                    listNum++;
                    listNum = 1;
                    System.out.println(Ui.seperatorLine2);
                    }
                } catch (IndexOutOfBoundsException e){
                    Ui.viewScheduleError();
                }
                break;

            case ("bye"):
                Ui.byeMessage();
                break;

            case ("exit"):
                System.exit(0);
                break;

            default:
                throw new DukeException();
            }
        } catch(StringIndexOutOfBoundsException e){
            System.out.println("☹ OOPS!!! Please key in space after " + input[0]);
        }
    }

    /***
     *
     * @param full_user_input full command and task key in by user in one line
     * @return return array with seperate words
     */
    public static String[] parseTask(String full_user_input) {
        String[] input = new String[100];

        input = full_user_input.split(" ");
//        user_input = input[0];

        return input;
    }

    /***
     *
     * @param user_input when user input event and deadline task, detect the "/" or not include " ",
     *
     * @return return the substring after task keywords.
     */
    public static String parseEventDeadlineTask (String user_input ){
        int task_stringIndex_After_taskWord = 0;
        int indexOfMultipleItems = 0;
        String task_words ="", by_words = " ";
        task_stringIndex_After_taskWord = user_input.indexOf(" ");

        if (user_input.contains("/")) {
            int by_string = user_input.indexOf("/");
            task_words = user_input.substring(task_stringIndex_After_taskWord, by_string);
        }

        else if(user_input.contains("bye")){
        }

        else{
            task_words = user_input.substring(task_stringIndex_After_taskWord);
//            if(task_words.contains("&")){
//                indexOfMultipleItems = task_words.indexOf("&");
//                System.out.println(indexOfMultipleItems);
//                task_words.substring(1,2);
//            }
        }

        return task_words;
    }
}

