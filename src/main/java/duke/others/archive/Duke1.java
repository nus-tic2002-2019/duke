package duke.others.archive;

import duke.others.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.nio.charset.StandardCharsets;
        import java.util.HashMap;
        import java.util.Map;
        import java.util.Scanner;
        import java.util.ArrayList;
        import java.io.*;
        import java.nio.file.Files;
        import java.nio.file.Paths;
        import java.nio.file.Path;


public class Duke1 {
    static ArrayList<Task> toDoList = new ArrayList();
    static Map <String,String> errList = new HashMap();
    static String filePath = "/Users/spencernah/code/duke/data/data.txt";


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        generateErrList();
        try {
            readFile(filePath);
            //readLine();
            greet();
            echo();
        } catch (DukeException ex) {
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("Line not found");
        }

    }



    public static void print(String input) {
        input = input.replace("\n", "\n\t");
        System.out.println("\t" + input);
        System.out.println("__________________________________________________________________________\n");
    }

    public static void greet() {
        print("Harrowwwwwww\nWut iz up?");
    }

    public static void generateErrList() {
        errList.put("General", "IDK what this :(");
        errList.put("ForbiddenChar", "Please do not use \";\" in your statements :(");
        errList.put("FilePathError", "To Do List not saved: File path not found!!");
        errList.put("EmptyTaskDesc", "Description cannot be empty >:(");
        errList.put("MissingChar/", "Please enter a date and lead it with \"/\"");
        errList.put("MissingDate", "Please include a date after the \"/\" :)");
        errList.put("TaskAlrDone", "duke.task.Task is already done!! D:<");
        errList.put("TaskNotFound", "No such duke.task here :(");
    }

    public static void echo() throws DukeException{
        Scanner in = new Scanner(System.in);
        boolean continueLoop = true;
        try {
            while (continueLoop) {
                String input = in.nextLine();
                switch (input.trim()) {
                    case ("bb"):
                    case ("bye"):
                        print("Oyasumi~");
                        continueLoop = false;
                        saveFile(filePath);
                        break;
                    case ("list"):
                        displayList();
                        break;
                    case("pending"):
                        displayList(false);
                        break;
                    default:
                        if (input.matches("done.*")) {
                            input = input.replace("done", "").trim();
                            if (isNumber(input)) {
                                int index = Integer.parseInt(input) - 1;
                                markAsDone(index);
                                updateLine(filePath, index, index + ";" + getInput(toDoList.get(index)));
                            }
                        }
                        else if (input.contains(";")){
                            throw new DukeException(errList.get("ForbiddenChar"));
                        }
                        else if (input.matches("delete.*")){
                            input = input.replace("delete", "").trim();
                            if (isNumber(input)) {
                                int index = Integer.parseInt(input) - 1;
                                deleteTask(index);
                                saveFile(filePath);
                            }
                        }
                        else if (input.matches("todo.*") || input.matches("deadline.*") || input.matches("event.*")) {
                            createTask(input);
                            int index = toDoList.size()-1;
                            appendToFile(filePath, index + ";" + getInput(toDoList.get(index)));
                        }
                        else {
                            throw new DukeException(errList.get("General"));
                        }
                }
            }
        }
        catch (IOException ex) {
            System.out.println("Line not found");
        }
        finally {
            echo();
        }
        in.close();
    }

    public static boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) == false)
                return false;
        }
        return true;
    }

    public static void createTask(String input) {
        try {
            if (input.matches("todo.*")) {
                createToDo(input);
            } else if (input.matches("deadline.*")) {
                createDeadlineEvent(input, "deadline");
            } else if (input.matches("event.*")) {
                createDeadlineEvent(input, "event");
            }
            print("New duke.task added: \n\t" + toDoList.get(toDoList.size()-1).getStatusIconAndDesc() + "\n" + toDoList.size() + " tasks in your list :)");

        } catch(DukeException ex) {

        }
    }

    public static void createToDo(String input) throws DukeException {
        String taskDesc = input.replace("todo", "").trim();
        if (taskDesc.length() > 0) {
            toDoList.add(new ToDo(taskDesc));
        }
        else {
            throw new DukeException(errList.get("EmptyTaskDesc"));
        }
    }

    public static void createDeadlineEvent(String input, String taskType) throws DukeException {
        String taskDesc = input.replace(taskType, "").trim();
        if (taskDesc.length() == 0 || taskDesc.charAt(0) == '/') throw new DukeException(errList.get("EmptyTaskDesc"));
        int lastIndex = taskDesc.lastIndexOf("/");
        if (lastIndex == -1) throw new DukeException(errList.get("MissingChar/"));
        String date = taskDesc.substring(lastIndex + 1);
        if (date.length() == 0)  throw new DukeException(errList.get("MissingDate"));
        taskDesc = taskDesc.substring(0, lastIndex).trim();
        if (taskType == "deadline") {
            if (date.indexOf("by") > 0) date = date.replace("by", "");
            toDoList.add(new Deadline(taskDesc, date));
        }
        else if (taskType == "event") {
            if (date.indexOf("at") > 0) date = date.replace("at", "");
            toDoList.add(new Event(taskDesc, date));
        }

    }

    public static void displayList() {
        String input = "Yessir! Here is the list!\n";
        if (toDoList.isEmpty())
            input = "No duke.task~ ^o^";
        else {
            for (int i = 0; i < toDoList.size(); ++i) {
                input += "\t" + (i + 1) + ". " + toDoList.get(i).getStatusIconAndDesc() + "\n";
            }
        }
        print(input);
    }

    public static void displayList(boolean all) {
        String input = "Yessir! Here is the list!\n";
        if (toDoList.isEmpty()) {
            input = "No duke.task~ ^o^";
            print(input);
        }
        else {
            if(all) {
                displayList();
            }
            else {
                for(int i = 0; i < toDoList.size(); ++i) {
                    if (!toDoList.get(i).getIsDone()) {
                        input += "\t" + (i+1) + ". "+ toDoList.get(i).getStatusIconAndDesc() + "\n";
                    }
                }
                print(input);
            }
        }
    }

    public static void markAsDone(int index) throws DukeException {
        if (toDoList.size() >= index) {
            if (toDoList.get(index).getIsDone()) {
                throw new DukeException(errList.get("TaskAlrDone"));
            }
            else {
                toDoList.get(index).markAsDone();
                String input = "NAISUUUUUUU!!! One duke.task off the list!!\n\t" + toDoList.get(index).getStatusIconAndDesc() + "\n";
                print(input);
            }
        }
        else {
            throw new DukeException(errList.get("TaskNotFound"));
        }
    }

    public static void deleteTask(int index) throws DukeException {
        if (toDoList.size() >= index) {
            print("Yessir! duke.task.Task removed!!\n\t" + toDoList.get(index).getStatusIconAndDesc() + "\n" + (toDoList.size()-1) + " tasks to go!");
            toDoList.remove(index);

        }
        else {
            throw new DukeException(errList.get("TaskNotFound"));
        }
    }

    public static String getInput(Task task) {
        String taskType = task.getType();
        String input = "";
        if (taskType == "D") {
            input = task.getType() + ";" + task.getIsDone() + ";" + task.getDesc() + ";" + ((Deadline) task).getDate();
        }
        else if (taskType == "E") {
            input = task.getType() + ";" + task.getIsDone() + ";" + task.getDesc() + ";" + ((Event) task).getDate();
        }
        else {
            input = task.getType() + ";" + task.getIsDone() + ";" + task.getDesc() + ";";
        }
        //System.out.println(input);
        return input;
    }

    public static void saveFile(String path) throws DukeException {
        try {
            FileWriter writer = new FileWriter(new File(path));
            int index = 0;
            for (Task task: toDoList) {
                String taskType = task.getType();
                String input = index + ";" + getInput(task);
                writer.write(input + System.lineSeparator());
                index++;
            }
            writer.close();
        }
        catch (Exception ex) {
            throw new DukeException(errList.get("FilePathError"));
        }
    }

    public static void readFile(String path) throws FileNotFoundException {
        File f = new File(path);
        Scanner s = new Scanner(f);
        String curLine = "";
        boolean status = false;
        while(s.hasNext()) {
            curLine = s.nextLine();
            String[] delimited = curLine.split(";");
            if ("T".equals(delimited[1])) {
                toDoList.add(new ToDo(delimited[3]));
            }
            else if ("E".equals(delimited[1])) {
                toDoList.add(new Event(delimited[3], delimited[4]));
            }
            else if ("D".equals(delimited[1])) {
                toDoList.add(new Deadline(delimited[3], delimited[4]));
            }
            toDoList.get(toDoList.size()-1).setStatus(Boolean.parseBoolean(delimited[2]));
        }
    }

    public static String readLine(String path, int n) throws IOException {
        return Files.readAllLines(Paths.get(path)).get(n);
    }

    public static void updateLine(String fPath, int n, String input) throws IOException {
        Path path = Paths.get(fPath);
        String content = new String(Files.readAllBytes(path));
        String line = readLine(fPath, n);
        content = content.replaceAll(line, input);
        Files.write(path, content.getBytes(StandardCharsets.UTF_8));
    }

    public static void appendToFile(String path, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        fw.write(System.lineSeparator());
        fw.write(textToAppend);
        fw.close();
    }


}




