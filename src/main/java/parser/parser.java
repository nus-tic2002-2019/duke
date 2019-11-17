package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import duke.storage.storage;
import duke.tasklist.tasklist;
import duke.ui.ui;

/**
 * Start of the parser module
 */
public class parser {
    /**
     * Create variables for user interface (ui), list storing (storage), and task handling (taskList)
     */
    private static ui ui;
    private static storage storage;
    private static tasklist tasklist;

    /**
     * Takes the raw text string from the user and filters the text into the appropriate types (commands, tasks, date, hour) to be forwarded to the tasklist modules
     * @param rawInput raw text from the user input
     */
    public void rawParser(String rawInput){
        ui = new ui();
        storage = new storage();
        tasklist = new tasklist();
        String filteredInputCommand = "";
        String filteredInputText = "";
        String filteredInputDate = "";
        int filteredInputTime = 0;
        LocalDate ldInputDate = LocalDate.parse("1990-01-01");
        DateTimeFormatter inputdateFormat = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        try {
            filteredInputCommand = rawInput.substring(0, rawInput.indexOf(' '));
        } catch (Exception e) {
            filteredInputCommand = rawInput;
        }
        try {
            filteredInputText = rawInput.substring(rawInput.indexOf(' '));
        } catch (Exception e) {
            switch (filteredInputCommand) {
                case "delete":
                    ui.printLine2();
                    ui.deleteUsage();
                    filteredInputCommand = "";
                    break;
                case "deadline":
                    ui.printLine2();
                    ui.deadlineUsage();
                    filteredInputCommand = "";
                    break;
                case "todo":
                    ui.printLine2();
                    ui.todoUsage();
                    filteredInputCommand = "";
                    break;
                case "event":
                    ui.printLine2();
                    ui.eventUsage();
                    filteredInputCommand = "";
                    break;
                case "done":
                    ui.printLine2();
                    ui.doneUsage();
                    filteredInputCommand = "";
                    break;
                case "find":
                    ui.printLine2();
                    ui.findUsage();
                    filteredInputCommand = "";
                    break;
                case "need":
                    ui.printLine2();
                    ui.needUsage();
                    filteredInputCommand = "";
                    break;
            }
        }
        try {
            filteredInputText = filteredInputText.substring(0, (filteredInputText.indexOf('/')));
        } catch (Exception e) {
        }
        try {
            filteredInputDate = rawInput.substring(rawInput.indexOf('/'));
            filteredInputDate = filteredInputDate.substring(1);
            ldInputDate = LocalDate.parse(filteredInputDate);
        } catch (Exception e) {
            switch ( filteredInputCommand ) {
                case "deadline":
                    ui.printLine2();
                    ui.deadlineUsage();
                    filteredInputCommand = "";
                    break;
                case "event":
                    ui.printLine2();
                    ui.eventUsage();
                    filteredInputCommand = "";
                    break;
            }
        }
        try {
            filteredInputDate = rawInput.substring(rawInput.indexOf('/'));
            filteredInputDate = filteredInputDate.substring(1);
            filteredInputTime = Integer.parseInt(filteredInputDate);
        } catch (Exception e) {
            if (filteredInputCommand.equals("need")){
                ui.printLine2();
                ui.needUsage();
                filteredInputCommand = "";
            }
        }
        switch ( filteredInputCommand ) {
            case "list":
                tasklist.arrayList(storage.readFile());
                break;
            case "find":
                tasklist.arrayFind(storage.readFile(), filteredInputText);
                break;
            case "done":
                tasklist.arrayDone(storage.readFile(), filteredInputText);
                break;
            case "todo":
                tasklist.arrayToDo(storage.readFile(), filteredInputText);
                break;
            case "event":
                tasklist.arrayEvent(storage.readFile(), filteredInputText, ldInputDate);
                break;
            case "deadline":
                tasklist.arrayDeadline(storage.readFile(), filteredInputText, ldInputDate);
                break;
            case "need":
                tasklist.arrayNeed(storage.readFile(), filteredInputText, filteredInputTime);
                break;
            case "delete":
                tasklist.arrayDelete(storage.readFile(), filteredInputText);
                break;
            case "bye":
                break;
            default:
                ui.availableCommands();
                ui.invalidCommand();
                ui.printLine2();
                break;
        }
    }
}
