import java.util.Scanner;

public class Duke {
    private static Task[] Tasks = new Task[100];
    private static int numberOfTask = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo +"What can I do for you?\n");

        Echo();

    }
    public static void addTask(Task s){
        Tasks[numberOfTask] = s;
        numberOfTask++;
    }

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

            /* switch (itemName) {
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
            }*/

            if (s1.equalsIgnoreCase(line)) {
                byebye = false;
            }
            else if(line.equalsIgnoreCase("list")){
                for(int i = 0; i< numberOfTask; i++){
                    System.out.println((i+1) + ". "+ " [" + Tasks[i].getStatusIcon() + "] "+Tasks[i].getTask());
                }
            }
            else if(itemName.equalsIgnoreCase("done")){
                String taskNumber = line.substring(dividerPosition+1,line.length());
                int taskIndex = Integer.parseInt(taskNumber)-1;
                if(numberOfTask >=taskIndex + 1 && taskIndex >= 0) {
                    Tasks[taskIndex].edit_done(true);
                    System.out.println("Nice! I've marked this task as done: \n" +
                            " [" + Tasks[taskIndex].getStatusIcon() + "] " + Tasks[taskIndex].getTask());
                }
                else{
                    System.out.println("Not such task");
                }
            }
            
            else{
                addTask(new Task(line));
                System.out.println("added: "+ line);
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}

