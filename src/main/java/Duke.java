import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import java.nio.file.FileAlreadyExistsException;

public class Duke {

    public static ArrayList<String> list = new ArrayList<>();
    public static ArrayList<String> type = new ArrayList<>();
    public static ArrayList<Boolean> mark = new ArrayList<>();
    public static ArrayList<LocalDate> dateS = new ArrayList<>();

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        try {
            load();
            while (printout(echo())) ;
        } catch (Exception e) {
        }
    }

    public static String echo() throws DukeException {
        String userInput;
        Scanner scan = new Scanner(System.in);
        userInput = scan.nextLine();

        String[] splitStr;

        if (userInput.contains("done")) {
            splitStr = userInput.split("\\s+");
            mark.set(Integer.valueOf(splitStr[1]) - 1, true);
        } else if (userInput.contains("delete")) {
            save();
        } else if ((!userInput.equals("list")) && (!userInput.equals("bye"))) {
            String typeS = getType(userInput);
            LocalDate dateL =LocalDate.now();//LocalDate.parse("0000-00-00");

            userInput = userInput.replace("todo ", "");
            userInput = userInput.replace("deadline ", "");
            userInput = userInput.replace("event ", "");

            if (userInput.isEmpty()) {
                System.out.println("☹ OOPS!!! The description cannot be empty.");
                throw new DukeException();
            } else {
                if (typeS == "E" || typeS == "D") {
                    splitStr = userInput.split("/");
                    String[] splitStr2 = splitStr[1].split(" ");
                    dateL = LocalDate.parse(splitStr2[1]);
                /*    LocalDateTime datetime = LocalDateTime.parse("2019-11-17 1800");
                    System.out.println(datetime);//splitStr2[1] + " " + splitStr2[2]
                    System.out.println(datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm")));
                    System.out.println(datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")));*/
                    userInput = splitStr[0] + "(" + splitStr2[0];
                }

                list.add(userInput);
                mark.add(false);
                type.add(typeS);
                dateS.add(dateL);
            }
        }
        return userInput;
    }

    public static boolean printout(String userInput) throws DukeException {
        if (userInput.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            return false;
        } else if (userInput.equals("list")) {
            System.out.println("Here are the tasks in your list:");

            for (int i = 0; i < list.size(); i++) {
                if(type.get(i).contains("E") || type.get(i).contains("D"))
                    System.out.println(i + 1 + ". [" + type.get(i) + "][" + getMark(mark.get(i)) + "] " + list.get(i) + " " + dateS.get(i).format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
                else
                    System.out.println(i + 1 + ". [" + type.get(i) + "][" + getMark(mark.get(i)) + "] " + list.get(i));
            }

        } else if (userInput.contains("done")) {
            save();
            // int index = Integer.valueOf(userInput.substring(userInput.length() + 1)) - 1;
            String[] splitStr = userInput.split("\\s+");
            int index = Integer.valueOf(splitStr[1]) - 1;
            System.out.println("Nice! I've marked this task as done: ");
            if(type.get(index) == "E" || type.get(index) == "D")
                System.out.println("[" + getMark(mark.get(index)) + "] " + list.get(index) + " " + dateS.get(index).format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
            else
                System.out.println("[" + getMark(mark.get(index)) + "] " + list.get(index));

        } else if (userInput.contains("delete")) {
            save();
            String[] splitStr = userInput.split("\\s+");
            int index = Integer.parseInt(splitStr[1]) - 1;

            System.out.println("Noted. I've removed this task: ");
            if(type.get(index) == "E" || type.get(index) == "D")
                System.out.println("[" + type.get(index) + "][" + getMark(mark.get(index)) + "] " + list.get(index) + " " + dateS.get(index).format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
            else
                System.out.println("[" + type.get(index) + "][" + getMark(mark.get(index)) + "] " + list.get(index));

            list.remove(index);
            type.remove(index);
            mark.remove(index);

            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } else {
            save();
            int size = list.size() - 1;
            System.out.println("Got it. I've added this task: " + userInput);
            if(type.get(size) == "E" || type.get(size) == "D")
                System.out.println("[" + type.get(size) + "][" + getMark(mark.get(size)) + "] " + list.get(size) + " " + dateS.get(size).format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
            else
                System.out.println("[" + type.get(size) + "][" + getMark(mark.get(size)) + "] " + list.get(size));

            System.out.println("Now you have " + type.size() + " tasks in the list.");
        }
        return true;
    }

    public static String getMark(boolean result) {

        if (result == true)
            return "✓";
        else
            return "X";
    }

    public static String getType(String type) throws DukeException {

        if (type.contains("todo"))
            return "T";
        else if (type.contains("deadline"))
            return "D";
        else if (type.contains("event"))
            return "E";
        else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            throw new DukeException();
        }
    }

    public static void save() throws DukeException {

        try {
            FileOutputStream out = new FileOutputStream("C:\\Users\\User\\Desktop\\output.txt");

            String line2save;
            for (int i = 0; i < list.size(); i++) {
                line2save = type.get(i) + "|" + mark.get(i) + "|" + list.get(i) + "|" + dateS.get(i);
                line2save = line2save + System.lineSeparator();

                byte b[] = line2save.getBytes();//converting string into byte array
                out.write(b);
            }
            out.close();
            System.out.println("File saved successfully.");
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public static void load() throws DukeException {

        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\output.txt"));

            String i;
            String[] splitStr;

            while ((i = br.readLine()) != null) {
                splitStr = i.split("\\|");
                list.add(splitStr[2]);
                mark.add(Boolean.parseBoolean(splitStr[1]));
                type.add(splitStr[0]);
                dateS.add(LocalDate.parse(splitStr[3]));
            }

        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
