import java.io.IOException;
public class Duke {
    public static void main(String[] args) throws IOException, DukeException {
        run();
    }

    public static void run() throws IOException, DukeException {
        Ui ui = new Ui();
        TaskLists taskList = new TaskLists();
        Storage textFile = new Storage();
        boolean online = true;
        String command = null;
        String message = null;
        ui.showWelcomeMessage();
        try {
            textFile.readFile(taskList);
        } catch (DukeException a) {
            ui.showInputError();
        }
        while (online) {
            try {
                message = ui.readCommand().trim();
                command = new Parser().parseInput(message);
                switch (command) {
                    case "todo":
                        try {
                            taskList.addToDo(message);
                            textFile.saveFile(taskList.getList());
                        } catch (StringIndexOutOfBoundsException e) {
                            ui.showToDoEmptyError();
                            break;
                        }
                        ui.showTaskAdded(taskList.displayLatestTask(), taskList.getSize());
                        break;
                    case "deadline":
                        try {
                            taskList.addDeadline(message);
                            textFile.saveFile(taskList.getList());
                        } catch (StringIndexOutOfBoundsException e) {
                            ui.showDeadlineEmptyError();
                            break;
                        }
                        ui.showTaskAdded(taskList.displayLatestTask(), taskList.getSize());
                        break;
                    case "event":
                        try {
                            taskList.addEvent(message);
                            textFile.saveFile(taskList.getList());
                        } catch (StringIndexOutOfBoundsException e) {
                            ui.showEventEmptyError();
                            break;
                        }
                        ui.showTaskAdded(taskList.displayLatestTask(), taskList.getSize());
                        break;
                    case "doAfter":
                        try {
                            taskList.addDoAfter(message);
                            textFile.saveFile(taskList.getList());
                        } catch (StringIndexOutOfBoundsException e) {
                            ui.showDoAfterEmptyError();
                            break;
                        }
                        ui.showTaskAdded(taskList.displayLatestTask(), taskList.getSize());
                        break;
                    case "list":
                        try {
                            ui.showList(taskList.displayList());
                        } catch (DukeExceptionEmptyList e) {
                            ui.showListEmptyError();
                            break;
                        }
                        break;
                    case "delete":
                        try {
                            ui.showDeletedTask(taskList.deleteTask(message), taskList);
                            textFile.saveFile(taskList.getList());
                        } catch (DukeExceptionInvalidTaskInputFormat e) {
                            ui.showInvalidTaskFormatError();
                            break;
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            ui.showInvalidTaskNumberError();
                            break;
                        }
                        break;
                    case "done":
                        try {
                            ui.showDoneTask(taskList.doneTask(message));
                            break;
                        } catch (DukeExceptionInvalidTaskInputFormat e) {
                            ui.showInvalidTaskFormatError();
                            break;
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            ui.showInvalidTaskNumberError();
                            break;
                        }
                    case "find":
                        try {
                            ui.showFindResult((taskList.findTask(message)));
                            break;
                        } catch (DukeExceptionEmptyList e) {
                            ui.showListEmptyError();
                            break;
                        }
                    case "bye":
                        online = false;
                        ui.showOffline();
                        break;
                    default:
                        ui.showUnknownInputError();
                }
            } catch (IOException e) {
                ui.showFileError();
            }
        }
        textFile.saveFile(taskList.getList());
    }
}


/* idea from improvement to this chatbot
1. Wrap around function if input more than 100 entries?
2. Wrap around or ignore entry?
3. Handling errors in inputs by user?
4. if users type 'Done' only, allow user to type in value of task in the list using scanner input.
we will leave it to the future.
5. when list is empty, user types list? Error handling Null element of history
6. when users just types todo with no other input. Error handling Null element of history
 */


/* Splitted the new taskings, to implement error handling for each task input
 * To check on ui.showinputerror and unknownerrorinput usage*/