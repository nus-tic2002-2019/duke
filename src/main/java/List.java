import java.util.ArrayList;
import java.util.Scanner;

public class List {
    /*  */
    private static Scanner read;
    static ArrayList<String> userList = new ArrayList<String>();
    //static ArrayList<String> listBullet = new ArrayList<String>();

    public List(){
        read = new Scanner(System.in);
        userList = new ArrayList<String>();
    }

    public static void enterList(String n) {
        userList.add(n);
        Task.checkMark();;
        System.out.println("added: " + n);
    }

    public static void printList() {
        //System.out.println("List");
        for (int i = 0; i < userList.size(); i++) {
            System.out.println( (i+1) + ". "
                                + "[" + Task.checkBox.get(i) + "] "
                                + userList.get(i));
        }
    }
}
