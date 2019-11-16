import java.util.ArrayList;

public class Ui {

    String horizontal_line = ("____________________________________\n");

    /**
     * Display the logo during Startup
     */
    public void welcome_note() {
        System.out.println(horizontal_line);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(horizontal_line + "Hello I'm Duke\nWhat can I do for you? \n" + horizontal_line);
    }

    /**
     * Print out everything in the Array List, for display.
     * Used when List is called by Users.
     *
     * @param my_Arr_List list of array passed from the main functions
     */
    public void print_Arry (ArrayList<Task> my_Arr_List) {

        System.out.println(horizontal_line + '\n' + "Here are the tasks in your list:");

        for(int i = 0; i< my_Arr_List.size() ; i++) {
            System.out.println( i+1 + ". " + my_Arr_List.get(i).toString());
        }
        System.out.println(horizontal_line);
    }

    /**
     * Printout whenever new stuff Users had just input. >>
     * Just printing for display whenever todo, event, or deadline is called and added
     *
     * @param input content of user_input
     */
    public void Print_input (int count, Task input) {
        System.out.println(horizontal_line + "Got it. I've added this task: ");
        System.out.println(" " + input);
        System.out.println("Now you have " + (count + 1) + " tasks in the list.\n" + horizontal_line);
    }

    /**
     * Print out result which contain similar words based on user input
     *
     * @param input is the words users want to search for
     * @param my_Arr_list Array of Task that is pass over from the main program
     */
    public void Print_Find_Result (ArrayList<Task> my_Arr_list, String input) {
        int count =0;
        System.out.println(horizontal_line + "Here are the matching tasks in your list: ");
        for (int i =0; i< my_Arr_list.size(); i++) {
            String line = my_Arr_list.get(i).toString();
            if(line.contains(input)) {
                count ++;
                System.out.println(count + ". " + my_Arr_list.get(i));
            }
        } if(count == 0) {
            System.out.println("No Result Found");
        } System.out.println(horizontal_line);
    }
    public void showLoadingError() {
        System.out.println("File Not Found");
    }
}
