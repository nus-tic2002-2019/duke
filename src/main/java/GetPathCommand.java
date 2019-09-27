public class GetPathCommand {

    public static String getPath(String fullCommand){
        System.out.println("New file path has been set into: "+fullCommand.split("\"")[1]);
        return fullCommand.split("\"")[1];
    }
}
