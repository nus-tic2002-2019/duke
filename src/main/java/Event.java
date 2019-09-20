public class Event extends ToDo {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    public String list(){
        return "[E]" +super.print()+ "at: "+ at;
    }
    public void setDone(boolean isDone){
        super.setDone(isDone);
    }
    public boolean getDone(){
        return super.getDone();
    }

    public String getText(){
        return super.getText();
    }
}
