import java.io.IOException;
import java.time.format.DateTimeParseException;

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
        } catch (DukeExceptionFileInput a) {
            ui.showFileInputError();
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
                        } catch (DateTimeParseException e) {
                            ui.showDateTimeError();
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
                        } catch (DateTimeParseException e) {
                            ui.showDateTimeError();
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
                        } catch (StringIndexOutOfBoundsException e) {
                            ui.showDoAfterEmptyError();
                            break;
                        } catch (DukeExceptionFindNoResult e) {
                            ui.showFindNoResult();
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
            } catch (DukeException e) {
                ui.showUnknownInputError();
            }
        }
        textFile.saveFile(taskList.getList());
    }
}

