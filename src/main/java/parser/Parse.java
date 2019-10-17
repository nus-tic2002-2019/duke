package parser;

import exceptions.ErrType;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Parse {

    private boolean isExit ;
    private Integer index;

    public Parse(){
        this.isExit = false;
        this.index = 0;
    }

    public Boolean isExit(){
        return isExit;
    }

    public void parser(ArrayList<Task> t){

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
                if (ErrType.TaskCheck(user_input)) {
                    taskString = user_input.replace("todo", "").trim();
                    t.add(new Todo(taskString));
                    Ui.added(t, t.size());
                }

                break;

            case "deadline":
                if (ErrType.TaskCheck(user_input) && ErrType.ScheduleCheck(user_input)) {
                    taskString = user_input.split("/")[0].replace("deadline", "").trim();
                    timeString = user_input.split("/")[1].replace("by", "").trim();
                    t.add(new Deadline(taskString, timeString));
                    Ui.added(t, t.size());
                }
                break;

            case "event":
                if (ErrType.TaskCheck(user_input) && ErrType.ScheduleCheck(user_input)) {
                    taskString = user_input.split("/")[0].replace("event","").trim();
                    timeString = user_input.split("/")[1].replace("at", "").trim();
                    t.add(new Event(taskString, timeString));
                    Ui.added(t, t.size());
                }
                break;

            case "done":
                idx = ErrType.toInteger(user_input.split(" ")[1], t.size()); // with Exceptions handling
                if (idx == -1) {
                    System.out.println("\tPlease key a valid task number.");
                    break;
                }
                Ui.done(t, idx);
                t.get(idx - 1).taskDone();

                this.index = idx;
                break;

            case "delete":
                idx = ErrType.toInteger(user_input.split(" ")[1], t.size()); // with Exceptions handling
                if (idx == -1) {
                    System.out.println("\tPlease key a valid task number.");
                    break;
                }
                Ui.delete(t, idx);
                t.remove(idx-1);

                this.index = idx;
                break;

            case "bye": // "bye" command will end loop after looping back to while()
                Ui.bye();
                this.isExit = true;
                break;

            default:   // any other command will be considered as error
                Ui.invalid();
                this.index = -1;
                break;
        }
        
    }

}
