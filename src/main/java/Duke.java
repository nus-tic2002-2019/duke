import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        InteractionsManager im = new InteractionsManager();
        im.start();
    }
    /**
    public static String hyphen_print(String message){
        String hyphens = "__________________";
        return hyphens + "\n" + message;
    }

    public static void main(String[] args) {
        System.out.println(hyphen_print("What can I do for you?"));
        String response;
        // initialise string array to store responses
        String[] my_list = new String[100];
        int idx = 0;
        // get input from user
        while(true){
            Scanner in = new Scanner(System.in);
            response = in.nextLine();
            if(response.toLowerCase().equals("bye")){
                System.out.println("Bye. Hope to see you again soon!\n");
                return;
            }
            if(response.toLowerCase().equals("list")){
                for(int i = 0; i < idx; i ++){
                    System.out.println(i+1 + ". " + my_list[i]);
                }
            }
            System.out.println(hyphen_print(("Added: " + response)));
            my_list[idx] = response;
            idx ++;

        }
    }**/
}
