import java.util.Scanner;

public class Duke {

    public static String hyphen_print(String message){
        String hyphens = "__________________";
        return hyphens + "\n" + message;
    }

    public static void main(String[] args) {
        System.out.println(hyphen_print("What can I do?"));
        String response;
        // get input from user
        while(true){
            Scanner in = new Scanner(System.in);
            response = in.nextLine();
            if(response.toLowerCase().equals("bye")){
                System.out.println("Bye. Hope to see you again soon!\n");
                return;
            }
            System.out.println(hyphen_print((response)));
        }
    }
}
