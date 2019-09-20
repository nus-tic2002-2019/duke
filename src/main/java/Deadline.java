public class Deadline extends ToDo {
    protected String by;

    public Deadline(String s, String by){
        super(s);
        this.by = by;
    }
    public String list(){
        return "[D]" +super.print()+ "do by: "+ by;
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
