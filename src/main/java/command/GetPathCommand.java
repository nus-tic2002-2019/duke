package command;

/**
 * Represents the return path command.
 * */

public class GetPathCommand {

    /**
     * Returns file directory path set by user.
     * */

    public static String getPath(String fullCommand){
        System.out.println("New file path has been set into: "+fullCommand.split("\"")[1]);
        return fullCommand.split("\"")[1];
    }
}
