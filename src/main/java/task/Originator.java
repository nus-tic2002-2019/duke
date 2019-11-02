package task;
import command.DeadlineCommand;
import command.EventCommand;
import exception.DukeException;
import java.util.Arrays;
import java.util.List;

public class Originator{

    private static Task createTask(String line) throws DukeException {
        String type=line.split("]")[0];
        String isDone=line.split("]")[1];
        String detail=line.split("]")[2];
        if(type.contains("T")){
            if(isDone.contains("✘")){
                return new ToDo(detail.substring(1),false);
            }else if(isDone.contains("✓")){
                return new ToDo(detail.substring(1),true);
            }

        }
        if(type.contains("D")) {
            if (isDone.contains("✘")) {
                int dividerPosition = detail.indexOf("do by:");
                String itemName = detail.substring(0, dividerPosition);
                String itemName1 = detail.substring(dividerPosition,detail.length());
                String itemName2 = itemName1.replace("do by:", "");
                return new Deadline(itemName, DeadlineCommand.convertDeadline(itemName2));
            } else if (isDone.contains("✓")) {
                int dividerPosition = detail.indexOf("do by:");
                String itemName = detail.substring(0, dividerPosition);
                String itemName1 = detail.substring(dividerPosition,detail.length());
                String itemName2 = itemName1.replace("do by:", "");
                return new Deadline(itemName, DeadlineCommand.convertDeadline(itemName2));
            }
        }
        if(type.contains("E")){
            if (isDone.contains("✘")) {
                int dividerPosition = detail.indexOf("at:");
                String itemName = detail.substring(0, dividerPosition);
                String itemName1 = detail.substring(dividerPosition,detail.length());
                String itemName2 = itemName1.replace("at:", "");
                return new Event(itemName, EventCommand.convertEvent(itemName2));
            } else if (isDone.contains("✓")) {
                int dividerPosition = detail.indexOf("at:");
                String itemName = detail.substring(0, dividerPosition);
                String itemName1 = detail.substring(dividerPosition,detail.length());
                String itemName2 = itemName1.replace("at:", "");
                return new Event(itemName, EventCommand.convertEvent(itemName2));
            }
        }

        else{
            throw new DukeException("☹ OOPS!!! Missing type element for CREATE TASK");
        }
        return new ToDo();
    }
    private int state;
    // The class could also contain additional data that is not part of the
    // state saved in the memento..

    public void set(int state) {
        this.state = state;
        System.out.println("Originator: Setting state to " + state);
    }

    public Memento saveToMemento(List<Task> taskList) {
        Memento mem=new Memento(this.state);
        for(int i=0;i<taskList.size();i++){
            mem.addString(taskList.get(i).list()+"\n");
        }
        System.out.println("Originator: Saving to Memento.");
        return mem;
    }

    public void restoreFromMemento(Memento memento, TaskList taskList) {
        this.state = memento.getSavedState();
        int size=taskList.getTaskList().size();
        for(int i=0;i<size;i++){
            taskList.removeTask(1);
        }
        try{
            List<String> lines = Arrays.asList(memento.tasks.split("\\r?\\n"));;
            for (String line : lines) {
                if (line.trim().isEmpty()) { //ignore empty lines
                    continue;
                }
                taskList.addTask(createTask(line)); //convert the line to a task and add to the list
            }
            System.out.println("Undo successfully");
        } catch (DukeException e1) {
            System.out.println("☹ OOPS!!! Problem encountered while undo: " +e1.getMessage());
        }
        System.out.println("Originator: State after restoring from Memento: " + state);
    }

    public static class Memento {
        private String tasks=new String();
        private final int state;

        public Memento(int stateToSave) {
            state = stateToSave;
        }
        public String getString(){
            return tasks;
        }
        public void addString(String add){
            tasks+=add;
        }
        // accessible by outer class only
        private int getSavedState() {
            return state;
        }
    }
}