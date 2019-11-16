
public class Duke {
    //private UI ui;
    //private static int numberOfTask = 0;
    //private static ArrayList<Task> Tasks = new ArrayList<>();

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
       // tasks = new TaskList();

        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            ui.showLoadingError();
            tasks = new TaskList();

        }

    }

    public void run() {
        ui.showDukeWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();

        //Echo();

    }

/*
    public static void Echo() {
        String line;
        Scanner in = new Scanner(System.in);
        boolean byebye = true;
        String s1 = "bye";

        while(byebye) {
            System.out.print("Type something: ");
            line = in.nextLine();
            String itemName = " ";
            int dividerPosition =0;
            if(line.contains(" ")){
            dividerPosition = line.indexOf(" ");
            itemName = line.substring(0, dividerPosition);
            }

             switch (itemName) {
                case "bye":  monthString = "January";
                    break;
                case "list":  monthString = "February";
                    break;
                case 3:  monthString = "March";
                    break;
                case 4:  monthString = "April";
                    break;
                case 5:  monthString = "May";
                    break;
                case 6:  monthString = "June";
                    break;
                case 7:  monthString = "July";
                    break;
                case 8:  monthString = "August";
                    break;
                case 9:  monthString = "September";
                    break;
                case 10: monthString = "October";
                    break;
                case 11: monthString = "November";
                    break;
                case 12: monthString = "December";
                    break;
                default: monthString = "Invalid month";
                    break;
            }

            if (s1.equalsIgnoreCase(line)) {
                byebye = false;
            }
            else if(line.equalsIgnoreCase("list")){
                for(int i = 0; i< numberOfTask; i++){
                    System.out.println((i+1) + ". " + Tasks.get(i).toString());
                }
            }
            else if(itemName.equalsIgnoreCase("done")){
                String taskNumber = line.substring(dividerPosition+1,line.length());
                int taskIndex = Integer.parseInt(taskNumber)-1;
                if(numberOfTask >=taskIndex + 1 && taskIndex >= 0) {
                    Tasks.get(taskIndex).edit_done(true);
                    System.out.println("Nice! I've marked this task as done: \n" + Tasks.get(taskIndex).toString());
                }
                else{
                    System.out.println("Not such task");
                }
            }
            else if(itemName.equalsIgnoreCase("delete")){
                String taskNumber = line.substring(dividerPosition+1,line.length());
                int taskIndex = Integer.parseInt(taskNumber)-1;
                Tasks.remove(taskIndex);
                numberOfTask--;
            }
            else if(itemName.equalsIgnoreCase("deadline")){
                if(itemName.contains(" /by ")) {
                    int dividerPosition2 = line.indexOf(" /by ");
                    String taskDes = line.substring(dividerPosition + 1, dividerPosition2);
                    String taskTime = line.substring(dividerPosition2 + 5, line.length());
                    addTask(new Deadlines(taskDes, taskTime));
                }
                else{
                    addTask(new Task(line));
                }

            }
            else if(itemName.equalsIgnoreCase("event")){
                if(itemName.contains(" /at ")){
                    int dividerPosition2 = line.indexOf(" /at ");
                    String taskDes = line.substring(dividerPosition + 1, dividerPosition2);
                    String taskTime = line.substring(dividerPosition2 + 5, line.length());
                    addTask(new Events(taskDes, taskTime));
                }
                else{
                    addTask(new Task(line));
                }
            }
            else if(itemName.equalsIgnoreCase("todo")){
                String taskDes = line.substring(dividerPosition+1,line.length());
                addTask(new ToDos(taskDes));
            }
            else{
                addTask(new Task(line));
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }
    */
}

