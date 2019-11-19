package duke.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.*;
import duke.error_handling.*;
import duke.parse.Parser;
import duke.storage.*;
import duke.task.*;
import duke.UI.*;


public class CommandList {
    private HashMap<String, Command> keywords;
    private Message ui;

    /**
     * HashMap Stores all the keywords and their respective function
     * Upon matching Key with correct format (Exception handles the rest, calling Message ui)
     * Value is Lambda expression method will run automatically
     * Values are all <Command()> Interface with run() method
     * Updating the TempTaskList first if necessary and then write to the text file storage
     *
     * @param list
     * @param file
     */
    public CommandList(TempTaskList list, Storage file) {
        keywords = new HashMap<String, Command>();
        ui = new Message();

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
        keywords.put("sort", new Command() {
            public void run(String content) {
                list.sort();
            };
        } );
        keywords.put("find", new Command() {
            public void run(String content) {
                cmdFind(content, list);
            }
        } );
        keywords.put("done", new Command() {
            public void run(String content) throws Exception {
                cmdMarkDone(content, list);
                file.write(list);
            }
        } );

        keywords.put("do", new Command() {
            public void run(String content) throws Exception {
                cmdMarkDo(content, list);
                file.write(list);
            }
        } );

        keywords.put("delete", new Command() {
            public void run(String content) throws Exception {
                cmdDelete(content, list);
                file.write(list);
            }
        } );

        keywords.put("todo", new Command() {
            public void run(String content) {
                try{
                    cmdTodo(content, list);
                    file.write(list);
                } catch (DukeException e) {
                    ui.emptyTaskMessage ();
                } catch (IOException e) {
                    ui.errorFileMessage();
                }
            };
        } );
        keywords.put("deadline", new Command() {
            public void run(String content) {
                try {
                    cmdDeadline(content, list);
                    file.write(list);
                } catch (NullContentException e) {
                    ui.emptyTaskMessage ();
                } catch (InvalidCommandException e) {
                    ui.dlInvalidFormatMessage();
                } catch (IOException e) {
                    ui.errorFileMessage();
                } catch (DateTimeException e) {
                    ui.dateErrMessage();
                }
            }
        } );
        keywords.put("event", new Command() {
            public void run(String content) {
                try {
                    cmdEvent(content, list);
                    file.write(list);
                } catch (NullContentException e) {
                    ui.emptyTaskMessage();
                } catch (InvalidCommandException e) {
                    ui.evInvalidFormatMessage();
                } catch (IOException e) {
                    ui.errorFileMessage();
                } catch (DateTimeException e) {
                    ui.dateTimeErrMessage();
                }
            }
        } );
    }

    private void cmdFind(String tofind, TempTaskList list) {
        ArrayList<Task> find = new ArrayList<Task>();
        ArrayList<Integer> no = new ArrayList<Integer>();

        for (int i = 0; i < list.size() ;++i) {
            if (list.get(i).getContent().contains(tofind)) {
                find.add(list.get(i));
                no.add(list.get(i).getTaskNumber());
            }
        }
        ui.findMessage(no, find);
    }

    /**
     * This method will be called when keyword "done" is matched
     * It sets the status of a given task (by task no.) to completed (true)
     * It also catches many Exception to show error msg
     * such as the String followed bt "done":
     * is not an valid task number
     * is not number at all
     * the task is already completed
     * Lastly it call function to print the message which task is done
     * @param content
     * @param list
     * @throws Exception
     */
    private void cmdMarkDone(String content, TempTaskList list) throws Exception {
        try {
            int listIndex = Integer.parseInt(content) - 1;
            if (listIndex < 0 || listIndex > list.size()) {
                throw new IndexOutOfBoundsException();
            }
            if (list.get(listIndex).getCompleted()) {
                ui.doneAlreadyMessage();
            } else {
                list.get(listIndex).setCompleted();
                printMarkDone(list, listIndex);
            }
        } catch (NumberFormatException e) {
            ui.doneTaskNoMessage();
        } catch (IndexOutOfBoundsException e) {
            ui.doneValidTaskNoMessage();
        }
    }

    /**
     * mark completed task to incompleted
     * @param content
     * @param list
     * @throws Exception
     */
    private void cmdMarkDo(String content, TempTaskList list) throws Exception {
        try {
            int listIndex = Integer.parseInt(content) - 1;
            if (listIndex < 0 || listIndex > list.size()) {
                throw new IndexOutOfBoundsException();
            }
            if (!list.get(listIndex).getCompleted()) {
                ui.notDoneAlreadyMessage();
            } else {
                list.get(listIndex).setIncompleted();
                ui.markDoMessage(list.get(listIndex));
            }
        } catch (NumberFormatException e) {
            ui.doneTaskNoMessage();
        } catch (IndexOutOfBoundsException e) {
            ui.doneValidTaskNoMessage();
        }
    }

    /**
     * Remove task in TempTaskList by task number
     * @param content
     * @param list
     * @throws Exception
     */
    private void cmdDelete(String content, TempTaskList list) throws Exception {
        try {
            int listIndex = Integer.parseInt(content) - 1;
            if (listIndex < 0 || listIndex > list.size()) {
                throw new IndexOutOfBoundsException();
            }
            Task deletedTask = list.get(listIndex);
            list.delete(listIndex);
            ui.markDelete(list, deletedTask);

        } catch (NumberFormatException e) {
            ui.doneTaskNoMessage();
        } catch (IndexOutOfBoundsException e) {
            ui.doneValidTaskNoMessage();
        }
    }

    private void cmdTodo(String content, TempTaskList list) throws DukeException {
        //String content = removeKeyword(userInput);

        if (content == null) {
            throw new NullContentException();
        }

        list.add(new Todo(content));
        int index = list.size() - 1;
        ui.addTaskMessage(list.get(index), list.size());
    }

    private void cmdDeadline(String content, TempTaskList list) throws NullContentException, InvalidCommandException, DateTimeException {
        //String content = removeKeyword(userInput);
        if (content == null) {
            throw new NullContentException();
        }
        if (!content.contains(" /by ")) {
            throw new InvalidCommandException();
        }
        String[] parts = content.split(" /by ");

        LocalDate by = Parser.getDate(parts[1]);
        if (by == null) {
            throw new DateTimeException("\t☹ OOPS!!! Please input a date in format as \" yyyy-mm-dd \" ");
        }

        list.add(new Deadline(parts[0], by));
        int index = list.size() - 1;

        ui.addTaskMessage(list.get(index), list.size());
    }

    private void cmdEvent(String content, TempTaskList list) throws NullContentException, InvalidCommandException, DateTimeException {
        //String content = removeKeyword(userInput);
        if (content == null) {
            throw new NullContentException();
        }
        if (!content.contains(" /at ")) {
            throw new InvalidCommandException();
        }
        String[] parts = content.split(" /at ");

        LocalDateTime at = Parser.getStartTime(parts[1]);
        LocalDateTime till = Parser.getEndTime(parts[1]);

        if (at == null || till == null) {
            throw new DateTimeException("\"\\t☹ OOPS!!! Please input a time in format as \" yyyy-mm-dd time-time (24h) \"");
        }

        list.add(new Event(parts[0], at, till));
        int index = list.size() - 1;

        ui.addTaskMessage(list.get(index), list.size());
    }

    private void printMarkDone(TempTaskList list, int listIndex) {
        ui.markDoneMessage(list.get(listIndex));
    }


    public Command get(String keyword) {
        return keywords.get(keyword);
    }

    public boolean containsKey(String keyword) {
        return keywords.containsKey(keyword);
    }

    public void  add(String keyword, Command method) {
        keywords.put(keyword, method);
    }
    public Command delete(String keyword) {
        return keywords.remove(keyword);
    }

    public String[] splitKeyword(String userInput) throws DukeException {
        //duke.command: list

        userInput = userInput.trim();
        String[] parts = userInput.split(" ", 2);
        parts[0] = parts[0].toLowerCase().trim();
        if (parts.length != 1) {
            parts[1] = parts[1].trim();
        }

        return parts;
    }

}
