package COMMAND;
import java.util.HashMap;
import ERROR_HANDLING.DukeException;
import ERROR_HANDLING.NullContentException;
import STORAGE.TempTaskList;
import TASK.Deadline;
import TASK.Event;
import TASK.Todo;
import UI.*;


public class CommandList {
    private HashMap<String, Command> keywords;
    private Message ui;
    private TempTaskList list;


    public CommandList() {
        keywords = new HashMap<String, Command>();
        ui = new Message();
        list = new TempTaskList();

        keywords.put("bye", new Command() {
            public void run(String content) {
                // Do absolutely nothing
            };
        } );

        keywords.put("list", new Command() {
            public void run(String content) {
                list.print();
            };
        } );
        keywords.put("done", new Command() {
            public void run(String content) throws Exception {
                cmdMarkDone(content, list);
            };
        } );
        keywords.put("todo", new Command() {
            public void run(String content) {
                try{
                    cmdTodo(content, list);
                } catch (DukeException e) {
                    ui.emptyTaskMessage ();
                }
            };
        } );
        keywords.put("deadline", new Command() {
            public void run(String content) {
                try {
                    cmdDeadline(content, list);
                } catch (DukeException e) {
                    ui.emptyTaskMessage ();
                }
            };
        } );
        keywords.put("event", new Command() {
            public void run(String content) {
                try {
                    cmdEvent(content, list);
                } catch (DukeException e){
                    ui.emptyTaskMessage ();
                }
            };
        } );
    }

    public Command get(String keyword) {
        return keywords.get(keyword);
    }

    public boolean containsKey(String keyword) {
        return keywords.containsKey(keyword);
    }

    public Command delete(String keyword) {
        return keywords.remove(keyword);
    }

    public String[] splitKeyword(String userInput) throws DukeException {
        //command: list

        userInput = userInput.toLowerCase();
        userInput = userInput.trim();
        String[] parts = userInput.split(" ", 2);
        parts[0] = parts[0].trim();
        if (parts.length != 1) {
            parts[1] = parts[1].trim();
        }

        return parts;
    }


    private void cmdMarkDone(String content, TempTaskList list) throws Exception {
        try {
            int listIndex = Integer.parseInt(content) - 1;
            if (listIndex < 0 || listIndex > list.size()) {
                throw new IndexOutOfBoundsException();
            }
            list.get(listIndex).setcompleted();
            printMarkDone(list, listIndex);
        } catch (NumberFormatException e) {
            ui.doneTaskNoMessage();
        } catch (IndexOutOfBoundsException e) {
            ui.doneValidTaskNoMessage();
        }
    }
    private void cmdTodo(String content, TempTaskList list) throws NullContentException {
        //String content = removeKeyword(userInput);

        if (content == null) {
            throw new NullContentException();
        }

        list.set(new Todo(content));
        int index = list.get(0).getTotalTask() - 1;
        ui.addTaskMessage(list.get(index), list.get(0).getTotalTask());
    }

    private void cmdDeadline(String content, TempTaskList list) throws NullContentException {
        //String content = removeKeyword(userInput);
        if (content == null) {
            throw new NullContentException();
        }
        String[] parts = content.split(" /by ");
        list.set(new Deadline(parts[0], parts[1]));
        int index = list.get(0).getTotalTask() - 1;
        ui.addTaskMessage(list.get(index), list.get(0).getTotalTask());
    }

    private void cmdEvent(String content, TempTaskList list) throws NullContentException {
        //String content = removeKeyword(userInput);
        if (content == null) {
            throw new NullContentException();
        }
        String[] parts = content.split(" /at ");
        list.set(new Event(parts[0], parts[1]));
        int index = list.get(0).getTotalTask() - 1;
        ui.addTaskMessage(list.get(index), list.get(0).getTotalTask());
    }

    private void printMarkDone(TempTaskList list, int listIndex) {
        ui.markDoneMessage(list.get(listIndex));
    }

}
