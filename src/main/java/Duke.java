import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
//import java.util.Arrays;
import java.util.ArrayList;

public class Duke {
    public static void deleteTask(ArrayList<Task> arr, int taskNo) {
        System.out.println("Noted. I've removed this task:\n  " + arr.get(taskNo-1));
        arr.remove(taskNo-1);
        System.out.println("Now you have " + arr.size() + " tasks in the list.");
    }

    public static void addText(ArrayList<Task> arr, String actType, String newTask, int ctr) {

        String dateline = "";
        int pos = 0;
        String temp = "";

        if (actType.equals("deadline")) {
            dateline = parseString(newTask, "/by ");
            pos = newTask.indexOf("/by");
        } else if (actType.equals("event")) {
            dateline = parseString(newTask, "/at ");
            pos = newTask.indexOf("/at");
        }

        switch (actType) {
            case "todo":
                arr.add(new Todo(newTask));
                temp = "T | " + arr.get(ctr).done + " | " + newTask;
                break;
            case "deadline":
                arr.add(new Deadline(newTask.substring(0, pos-1), dateline));
                temp = "D | " + arr.get(ctr).done + " | " + newTask.substring(0, pos-1) + " | " + dateline;
                break;
            case "event":
                arr.add(new Event(newTask.substring(0, pos-1), dateline));
                temp = "E | " + arr.get(ctr).done + " | " + newTask.substring(0, pos-1)  + " | " + dateline;
                break;
            default:
                break;
        }
        //System.out.println("task status: " + arr.get(ctr).done);

        try {
            appendToFile("C:/Users/AmyK/OneDrive/Documents/NUS SCALE/TIC2002/duke/data/Tasklist.txt", temp + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Exception Handling: something went wrong");
        }

        System.out.println("Got it. I've added this task:\n  " + arr.get(ctr));
        System.out.println("Now you have " + (ctr+1) + " tasks in the list.");
    }

     public static void listArr(ArrayList<Task> arr) {
        System.out.println("Here are the tasks in your list:");

        int i = 1;
        for (Task t : arr) {
            System.out.println(i + "." + t);
            i++;
        }
    }

    public static void markDone(ArrayList<Task> arr, int taskNo) throws IOException {
        arr.get(taskNo-1).isDone();
        System.out.println("Nice! I've marked this task as done:\n  " + arr.get(taskNo-1));
        try {
            Files.copy(Paths.get("C:/Users/AmyK/OneDrive/Documents/NUS SCALE/TIC2002/duke/data/Tasklist.txt"), Paths.get("C:/Users/AmyK/OneDrive/Documents/NUS SCALE/TIC2002/duke/data/tempTaskList.txt"));
            File f = new File("C:/Users/AmyK/OneDrive/Documents/NUS SCALE/TIC2002/duke/data/tempTaskList.txt");
            Scanner s = new Scanner(f);

            writeToFile("C:/Users/AmyK/OneDrive/Documents/NUS SCALE/TIC2002/duke/data/Tasklist.txt", "");
            int i = 0;
            while (s.hasNext()) {
                if (!arr.get(i).done) {
                    appendToFile("C:/Users/AmyK/OneDrive/Documents/NUS SCALE/TIC2002/duke/data/Tasklist.txt", s.nextLine() + System.lineSeparator());
                } else {
                    appendToFile("C:/Users/AmyK/OneDrive/Documents/NUS SCALE/TIC2002/duke/data/Tasklist.txt", arr.get(i).getClass().getSimpleName() + " | " + arr.get(i).done + " | "+ arr.get(i).getTask() + System.lineSeparator());
                    s.nextLine();
                }
                i++;
            }
            s.close();
            Files.delete(Paths.get("C:/Users/AmyK/OneDrive/Documents/NUS SCALE/TIC2002/duke/data/tempTaskList.txt"));
        } catch (IOException e) {
            System.out.println("module: markDone: IOException captured.");
        }
    }

    private static int taskDone(String userInput) {
        String[] d = userInput.split(" ");
        if (d.length > 2) {
            //throw Exception
        } else {
            return Integer.parseInt(d[1]);
        }
        return 0;
    }

    private static String parseString(String userInput, String delimit) {
        String[] d = userInput.split(delimit);
        if (delimit.equals(" ")) {
            return d[0].toLowerCase();
        } else {
            return d[1];
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    public static ArrayList<Task> loadFromFile(String filePath) throws IOException {
        ArrayList<Task> userArr = new ArrayList<>();
        try {
            File f = new File("C:/Users/AmyK/OneDrive/Documents/NUS SCALE/TIC2002/duke/data/TaskList.txt");
            Scanner s = new Scanner(f);

            int i = 0;
            s.useDelimiter("\\s*\\|\\s*|\\r\\n");
            while (s.hasNext()) {
                //System.out.println(s.next());
                String actType = s.next();
                String taskStatus = s.next();
                //String task = s.next();

                switch (actType) {
                    case "T":
                        userArr.add(new Todo(s.next()));
                        if (taskStatus.equals("true")) {
                            userArr.get(i).isDone();
                        }
                        break;
                    case "E":
                        userArr.add(new Event(s.next(), s.next()));
                        if (taskStatus.equals("true")) {
                            userArr.get(i).isDone();
                        }
                        break;
                    case "D":
                        userArr.add(new Deadline(s.next(), s.next()));
                        if (taskStatus.equals("true")) {
                            userArr.get(i).isDone();
                        }
                        break;
                    default:
                        System.out.println("Error");
                        break;
                }
                i++;
            }
            s.close();
        } catch (IOException e) {
            System.out.println("IOException error - Module: loadFromFile");
        }
        return userArr;
    }

    public static void main(String[] args) {

        //System.out.println("Hello from\n" + logo);*/
        String userInput;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello, I'm Duke.\nWhat can I do for you?");

        ArrayList<Task> userArr = new ArrayList<>();
        try {
            userArr = loadFromFile("C:/Users/AmyK/OneDrive/Documents/NUS SCALE/TIC2002/duke/data/TaskList.txt");
        } catch (IOException e) {
            System.out.println("System error: loadFromFile");
        }
        int i = 0;
        userInput = in.nextLine();
        String temp = parseString(userInput, " ");

        String filePath = "C:/Users/AmyK/OneDrive/Documents/NUS SCALE/TIC2002/duke/data/Tasklist.txt";
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
                System.out.println("File created");
            } else {
                System.out.println("File exists in the system");
            }
        } catch (IOException ioEx){
            //handle exception;
        }

        while (!temp.equals("bye")) {
            switch(temp) {
                case "done":
                    int taskNo = taskDone(userInput);
                    try {
                        markDone(userArr, taskNo);
                    } catch (IOException e) {
                    }
                    break;
                case "list":
                    listArr(userArr);
                    break;
                case "todo": case "deadline": case "event":
                    addText(userArr, temp, userInput.substring(temp.length()+1), i);
                    i++;
                    break;
                case "delete":
                    taskNo = taskDone(userInput);
                    deleteTask(userArr, taskNo);
                    i--;
                    break;
                default:
                    addText(userArr, "todo",userInput, i);
                    i++;
                    break;
            }

            userInput = in.nextLine();
            temp = parseString(userInput, " ");
        }

        if (userInput.toLowerCase().equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}
