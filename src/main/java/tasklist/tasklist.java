package duke.tasklist;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import duke.storage.storage;
import duke.ui.ui;

/**
 * Start of the tasklist module
 */
public class tasklist {
    /**
     * Create variables for user interface (ui), list storing (storage), command parsing (parser) and task handling (taskList)
     */
    private static storage storage;
    private static ui ui;

    /**
     * Lists an array passed to it
     * @param importedArray the input array to be listed
     */
    public void arrayList(ArrayList<String> importedArray){
        ui = new ui();
        ui.printLine2();
        for (int i = 0; i < importedArray.size(); i++){
            System.out.println((i+1)+"."+importedArray.get(i));
        }
        ui.printLine1();
    }

    /**
     * Finds text within an array passed to it
     * @param importedArray the input array to be searched
     * @param findText the target text
     */
    public void arrayFind(ArrayList<String> importedArray, String findText){
        ui = new ui();
        ui.printLine2();
        boolean missingText = true;
        assert (findText != null);
        for (int i = 0; i < importedArray.size(); i++){
            boolean foundText = importedArray.get(i).contains(findText);
            if (foundText){
                System.out.println((i+1)+"."+importedArray.get(i));
                missingText = false;
            }
        }
        if (missingText){
            System.out.println("No results");
        }
        ui.printLine1();
    }

    /**
     * Adds a new Todo task in the array passed to it
     * @param importedArray the input array that requires adding a Todo task
     * @param filteredInputText the Todo task's description
     */
    public void arrayToDo(ArrayList<String> importedArray, String filteredInputText){
        ui = new ui();
        storage = new storage();
        assert (filteredInputText != null);
        importedArray.add("[T][" + "\u2718" + "]" + filteredInputText);
        storage.saveFile(importedArray);
        ui.printLine1();
        System.out.println("Got it. I've added this todo:\n" + "[T][" + "\u2718" + "]" +
                filteredInputText +"\n" + "Now you have " + importedArray.size() + " tasks in the list.\n");
        ui.printLine1();
    }

    /**
     * Marks a task as done in the array passed to it
     * @param importedArray the array that contains a newly completed task
     * @param filteredInputText the task number of the task to be marked as complete
     */
    public void arrayDone(ArrayList<String> importedArray, String filteredInputText){
        ui = new ui();
        storage = new storage();
        try {
            int doneInt = Integer.parseInt(filteredInputText.substring(1));
            doneInt--;
            String filteredList = importedArray.get(doneInt);
            assert (doneInt <= importedArray.size());
            if ( filteredList.indexOf("][") == 2 ) {
                importedArray.set(doneInt, (filteredList.substring(0, 3) + "[" + "\u2713" + "]" + filteredList.substring(6)));
            } else {
                importedArray.set(doneInt, ("[" + "\u2713" + "]" + filteredList.substring(3)));
            }
            storage.saveFile(importedArray);
            ui.printLine2();
            System.out.println("Nice! I've marked this task as done:" + importedArray.get(doneInt));
            ui.printLine2();
        } catch (Exception e) {
            ui.printLine1();
            ui.todoUsage();
            ui.availableCommands();
            ui.invalidCommand();
            ui.printLine2();
        }
    }

    /**
     * Adds a new Event task in the array passed to it
     * @param importedArray the input array that requires adding a Event task
     * @param filteredInputText the Event task's description
     */
    public void arrayEvent(ArrayList<String> importedArray, String filteredInputText, LocalDate ldInputDate) {
        DateTimeFormatter inputdateFormat = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        ui = new ui();
        storage = new storage();
        assert (filteredInputText != null);
        assert (ldInputDate != null);
        importedArray.add("[E][" + "\u2718" + "]" + filteredInputText + " (at: " + inputdateFormat.format(ldInputDate) + ")");
        storage.saveFile(importedArray);
        ui.printLine1();
        System.out.println("Got it. I've added this event:\n" + "[E][" + "\u2718" + "]" + filteredInputText +
                " (at: " + inputdateFormat.format(ldInputDate) + ")" +"\n" +
                "Now you have " + importedArray.size() + " tasks in the list.\n");
        ui.printLine1();
    }

    /**
     * Adds a new Deadline task in the array passed to it
     * @param importedArray the input array that requires adding a Deadline task
     * @param filteredInputText the Deadline task's description
     */
    public void arrayDeadline(ArrayList<String> importedArray, String filteredInputText, LocalDate ldInputDate) {
        DateTimeFormatter inputdateFormat = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        ui = new ui();
        storage = new storage();
        assert (filteredInputText != null);
        assert (ldInputDate != null);
        importedArray.add("[D][" + "\u2718" + "]" + filteredInputText + " (by: " + inputdateFormat.format(ldInputDate) + ")");
        storage.saveFile(importedArray);
        ui.printLine1();
        System.out.println("Got it. I've added this deadline:\n" + "[D][" + "\u2718" + "]" + filteredInputText +
                " (at: " + inputdateFormat.format(ldInputDate) + ")" +"\n" +
                "Now you have " + importedArray.size() + " tasks in the list.\n");
        ui.printLine1();
    }

    /**
     * Adds a new Need task in the array passed to it
     * @param importedArray the input array that requires adding a Need task
     * @param filteredInputText the Need task's description
     */
    public void arrayNeed(ArrayList<String> importedArray, String filteredInputText, int filteredInputTime) {
        DateTimeFormatter inputdateFormat = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        ui = new ui();
        storage = new storage();
        assert (filteredInputText != null);
        importedArray.add("[N][" + "\u2718" + "]" + filteredInputText + " (need: " + filteredInputTime + ")");
        storage.saveFile(importedArray);
        ui.printLine1();
        System.out.println("Got it. I've added this need:\n" + "[N][" + "\u2718" + "]" + filteredInputText +
                " (need: " + filteredInputTime + ")" +"\n" +
                "Now you have " + importedArray.size() + " tasks in the list.\n");
        ui.printLine1();
    }

    /**
     * Deletes a task from the array passed to it
     * @param importedArray the array that requires a task deletion
     * @param filteredInputText the task number of the task to be deleted
     */
    public void arrayDelete(ArrayList<String> importedArray, String filteredInputText) {
        ui = new ui();
        storage = new storage();
        try {
            int deleteInt = Integer.parseInt(filteredInputText.substring(1));
            deleteInt--;
            String tempStorage = importedArray.get(deleteInt);
            importedArray.remove(deleteInt);
            storage.saveFile(importedArray);
            ui.printLine1();
            System.out.println("Noted. I've removed this task: " + tempStorage + "\n" +
                    "Now you have " + importedArray.size() + " tasks in the list.\n");
            ui.printLine2();
        } catch (Exception e) {
            ui.printLine1();
            ui.deleteUsage();
            ui.availableCommands();
            ui.invalidCommand();
            ui.printLine2();
        }
    }
}
