package duke.task;

import java.util.ArrayList;
import java.util.List;
import duke.DukeCheckLineException;
import duke.DukeException;
import duke.Storage;
import duke.command.Command;

public class Event extends Time {
    //private String time;

    public Event(String description, String time){
        super(description, time);
        //this.time = time;
    }
    static void CheckDescription(int index)throws DukeException {
        if (index == 1) {
            throw new DukeException();
        }
    }
    static void CheckTime(int index)throws DukeCheckLineException {
        if (index == -1){
            throw new DukeCheckLineException();
        }
    }
    public static Command getCommand(TaskList tasks, Storage storage){
        return fullCommand ->{

            List<String> commandList = List.of(fullCommand);
            int position = commandList.indexOf("/at");
            try {
                CheckDescription(position);
                CheckTime(position);
                String description = String.join(" ", commandList.subList(1, position));
                String time = String.join(" ", commandList.subList(position + 1, fullCommand.length));
                Task eventTask = new Event(description, time);
                tasks.add(eventTask);
                storage.store(tasks.ConvertAsLines());
                return List.of("    Got it. I've added this task: " + System.lineSeparator() + "    " + eventTask +
                        System.lineSeparator() +
                        "    Now you have " + tasks.size() + " tasks in the list.");
            }
            catch (DukeException e) {
                return List.of("    ☹ OOPS!!! The description of a " + "Event" + " cannot be empty.");
            }catch (DukeCheckLineException e) {
                return List.of("    ☹ OOPS!!! An event must have a time. ");
            }
        };
    }

    @Override
    public List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("E");
        list.addAll(super.getList());
        list.add(convertSaveTimeString());

        return list;
    }
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + convertTimeString() + ")";
    }
}
