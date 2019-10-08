import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.text.ParseException;
import subclass.*;

public class Duke {
    //private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        //storage = new Storage(filePath);
        try {
            Storage.loadFile(filePath);
        } catch (ParseException | FileNotFoundException e) {
            Ui.displayError();
            tasks = new TaskList();
        }

    }

    public void run() {
        Ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                //ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit;
            } catch (IOException | DukeException | ParseException e) {
                ui.displayError();
            }
        }
    }

    public static void main(String[] args) throws DukeException, todoException, IOException, ParseException {
        //load txt file
        String file_path = "C:\\Users\\marcus.ng\\Desktop\\m\\m\\NUS\\TIC2002 Introduction to Software Engineering\\duke\\src\\main\\java\\taskList.txt";
        new Duke(file_path).run();

        /*Ui.showWelcome();
        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();
            while (!line.contains("bye")) {
                try {
                    Task newTask = new Task(line);
                    //adding exceptions (in progress)
                    if (!line.contains("list") && !line.contains("done") && !line.contains("todo") && !line.contains("delete") && !line.contains("deadline") && !line.contains("event")) {
                        throw new DukeException();
                    }
                    //add inputs into array, add Todo
                    if (!line.contains("list") && !line.contains("done") && !line.contains("delete") && line.contains("todo")) {
                        String input_items = Parser.parseTask(line);
                        if (input_items.equals("todo")) {
                            throw new todoException();
                        }
                        Ui.showLine();
                        Ui.displayGotIt();
                        System.out.println("\t\t" + newTask.add_task(new Todo(input_items)));
                        System.out.println("\t\tNow you have " + newTask.word_count + " tasks in list.");
                        Ui.showLine();

                    }
                    //add deadline
                    if (!line.contains("list") && !line.contains("done") && !line.contains("delete") && line.contains("deadline")) {
                        String input_items = Parser.parseTask(line);
                        String by = Parser.parseDeadline_by(line);
                        String input_string = Parser.parseTask_description(input_items);
                        Task tmp = newTask.add_task(new Deadline(input_string, by));
                        Ui.showLine();
                        Ui.displayGotIt();
                        System.out.println("\t\t" + tmp);
                        System.out.println("\t\tNow you have " + newTask.word_count + " tasks in list.");
                        Ui.showLine();

                    }
                    //add event
                    if (!line.contains("list") && !line.contains("done") && line.contains("event")) {
                        String input_items = Parser.parseTask(line);
                        String at = Parser.parseEvent_at(line);
                        String input_string = Parser.parseTask_description(input_items);
                        Task tmp = newTask.add_task(new Event(input_string, at));
                        Ui.showLine();
                        Ui.displayGotIt();
                        System.out.println("\t\t" + tmp);
                        System.out.println("\t\tNow you have " + newTask.word_count + " tasks in list.");
                        Ui.showLine();

                    }

                    //displaying list
                    if (line.contains("list")) {
                        Ui.showLine();
                        System.out.println(newTask.getList());
                        Ui.showLine();
                    }
                    //mark done
                    if (line.contains("done")) {

                        String input_items = Parser.parseTask(line);
                        int task_option = Integer.parseInt(input_items);
                        Task tmp = newTask.markDone(task_option);
                        Ui.showLine();
                        Ui.markAsDone();
                        System.out.println("\t\t" + tmp);
                        Ui.showLine();
                    }
                    //delete task
                    if (line.contains("delete")) {

                        String input_items = Parser.parseTask(line);
                        int task_option = Integer.parseInt(input_items);
                        newTask.removeTask(task_option);
                    }
                } catch (DukeException e) {

                    Ui.showLine();
                    Ui.displayError();
                    Ui.showLine();

                } catch (StringIndexOutOfBoundsException e) {

                    Ui.showLine();
                    Ui.displayDeadlineEventError();
                    Ui.showLine();

                } catch (todoException e) {

                    Ui.showLine();
                    Ui.displayTodoError();
                    Ui.showLine();

                } catch (IndexOutOfBoundsException e) {

                    Ui.showLine();
                    Ui.displayError_noItem();
                    Ui.showLine();

                } catch (ParseException e) {
                    Ui.showLine();
                    Ui.displayError();
                    Ui.displayError_dateFormat();
                    Ui.showLine();

                }

                line = in.nextLine();

            } */
        //save Task.txt
        Storage.writeToFile(file_path, Storage.toTxt(Task.getOutput()));

        //exit
        //Ui.showLine();
        //Ui.goodBye();
        //Ui.showLine();
    }
}
