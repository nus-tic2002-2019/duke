package ui;

import tasklist.*;
import exception.DukeException;

import java.util.ArrayList;

public class Ui {
    public Ui(){

    }

    public void showLine(){
        System.out.println("    ________________________________________");
    }

    public void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dukeGreet();
    }

    public void dukeGreet(){

        System.out.println ("    Hello! I'm Duke");
        System.out.println ("    What can I do for you?");

    }

    public void dukeBye(){

        System.out.println("     Bye. Hope to see you again soon!");

    }

    public void showLoadingError(){

        System.out.println ("    File not located");

    }

    public void showUnknownCommand(){
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void dukeReply(ArrayList<Task> taskList){


        System.out.println("     Got it. I've added this task: ");
        System.out.println("     " + taskList.get(taskList.size()-1).getDescription());
        System.out.println("     Now you have " + taskList.size() + " tasks in the list.");

    }

    public void dukeInput (taskList tasks, String textInput) throws DukeException {
        String textInputArr[] = textInput.split(" ",2);
        try {
            switch (textInputArr[0]) {
                case "list":
                    tasks.displayList();
                    break;
                case "done":
                    if (textInputArr[1].isEmpty()) throw new DukeException("done");
                    tasks.markInList(textInputArr[1]);
                    break;
                case "delete":
                    if (textInputArr[1].isEmpty()) throw new DukeException("delete");
                    tasks.deleteFromList(textInputArr[1]);
                    break;
                case "todo":
                    if (textInputArr[1].isEmpty()) throw new DukeException("todo");
                    taskList.addTodo(new Todo(textInputArr[1]));
                    break;
                case "deadline":
                    if (textInputArr[1].isEmpty()) throw new DukeException("deadline");
                    String textDeadline[] = textInputArr[1].split("/by", 2);
                    if (textDeadline.length < 2) throw new DukeException("/by");
                    tasks.addDeadlines(new Deadlines(textDeadline[0], textDeadline[1]));
                    break;
                case "event":
                    if (textInputArr[1].isEmpty()) throw new DukeException("event");
                    String textEvent[] = textInputArr[1].split("/at", 2);
                    if (textEvent.length < 2) throw new DukeException("/at");
                    tasks.addEvent(new Event(textEvent[0], textEvent[1]));
                    break;
                case "bye":
                    dukeBye();
                    break;
                default:
                    throw new DukeException(textInputArr[1]);
            }
        } catch (IndexOutOfBoundsException e){
            throw new DukeException(textInputArr[0]);
        }
        catch (DukeException e){
            //do we need anything here
        }

    }


}
