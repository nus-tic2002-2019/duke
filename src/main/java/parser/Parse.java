//level 7.more oop

package parser;

import exceptions.DukeException;
import exceptions.Errortype;
import task.Deadlines;
import task.Events;
import task.Task;
import task.Todo;
import ui.Ui;


import java.util.ArrayList;
import java.util.Scanner;

public class Parse {

    private static boolean isExit ;
    private static Integer index;

    public Parse() {
        this.isExit = false;
        this.index = 0;
    }

    public static Boolean isExit() {
        return isExit;
    }

    public void parser(ArrayList<Task> t) {

        String user_input = "";
        Integer idx = 0;


        String command = "";
        String taskString = "";
        String timeString = "";

        Scanner in = new Scanner(System.in);
        user_input = in.nextLine();
        command = user_input.split(" ")[0].toLowerCase();


        switch (command) {
            case "list":
                Ui.list(t, t.size());
                break;

            case "todo":
                if (Errortype.TaskCheck(user_input)) {
                    taskString = user_input.replace("todo", "").trim();
                    t.add(new Todo(taskString));
                    Ui.added(t, t.size());
                }

                break;

            case "deadline":
                if (Errortype.TaskCheck(user_input) && Errortype.ScheduleCheck(user_input)) {
                    taskString = user_input.split("/")[0].replace("deadline", "").trim();
                    timeString = user_input.split("/")[1].replace("by", "").trim();
                    t.add(new Deadlines(taskString, timeString));
                    Ui.added(t, t.size());
                }
                break;

            case "event":
                if (Errortype.TaskCheck(user_input) && Errortype.ScheduleCheck(user_input)) {
                    taskString = user_input.split("/")[0].replace("event","").trim();
                    timeString = user_input.split("/")[1].replace("at", "").trim();
                    t.add(new Events(taskString, timeString));
                    Ui.added(t, t.size());
                }
                break;

            case "done":
                idx = Errortype.toInteger(user_input.split(" ")[1], t.size());
                if (idx == -1) {
                    System.out.println("\tPlease provide a valid task number.");
                    break;
                }
                Ui.done(t, idx);
                t.get(idx - 1).taskDone();

                index = idx;
                break;

            case "delete":
                idx = Errortype.toInteger(user_input.split(" ")[1], t.size());
                if (idx == -1) {
                    System.out.println("\tPlease provide a valid task number.");
                    break;
                }
                Ui.delete(t, idx);
                t.remove(idx-1);

                this.index = idx;
                break;

            case "bye":
                Ui.bye();
                this.isExit = true;
                break;

            default:
                Ui.invalid();
                this.index = -1;
                break;
        }

    }

}

