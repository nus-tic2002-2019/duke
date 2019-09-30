import java.util.ArrayList;
import java.util.Scanner;

public class List {
    /*  */
    private Scanner read;
    static ArrayList<String> userList = new ArrayList<String>();
    static ArrayList<String> taskType = new ArrayList<String>(); // to store T, D & E
    //static ArrayList<String> deadTime = new ArrayList<String>();
    //static ArrayList<String> eventTime = new ArrayList<String>();
    //static ArrayList<String> listBullet = new ArrayList<String>();

    public List(){
        read = new Scanner(System.in);
        userList = new ArrayList<String>();
        taskType = new ArrayList<String>();
    }

    public static void enterList(String n) {

        userList.add(n);//----------------add task to arraylist
        Task.checkMark();//-----------------fill checkbox in Task
        System.out.println("added: " + n);
    }


    public static void printList() {
        //System.out.println("List");
        for (int i = 0; i < userList.size(); i++) {
            System.out.println( (i+1) + ". "
                                + "[" + taskType.get(i) + "]"
                                + "[" + Task.checkBox.get(i) + "] "
                                + userList.get(i));
        }
    }
}
