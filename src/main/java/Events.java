public class Events extends Task {
    String details;

    public Events(String description, String details){
        this.description = description;
        this.type = 'E';
        this. details = "(at: " + details + ")";
    }

    public String getDetails(){
        return details;
    }
}
