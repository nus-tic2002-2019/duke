//level 7
package ui;

import task.Task;
import java.util.ArrayList;

public class Ui {

    public Ui(){

    }

    public void welcome(){
        //public static void main(String[] args) throws DukeException {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            System.out.println("Hello! I'm Duke");
            System.out.println("What can I do for you?\n");

        }

    public static void invalid(){
        System.out.println("\tOops!! You have key an invalid command.");
    }


}
