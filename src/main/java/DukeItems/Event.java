package DukeItems;

import java.util.ArrayList;

public class Event extends Task {

    protected String at;
    protected ArrayList<String> tagList = new ArrayList<>();
    protected boolean isTagExist;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getTaskType(){
        return "[E]";
    }

    public String getDue(){
        return at;
    }

    public void setTags(String tag){
        tagList.add(tag);
    }

    public boolean verifyTag(String tag){
        for (int i=0; i<tagList.size(); i++){
            if(tagList.get(i) == tag){
                isTagExist = true;
            }
            else {
                isTagExist = false;
            }
        }
        return ( isTagExist ? true : false );
    }

    @Override
    public String toString() {
        return getTaskType() + " " + super.toString() + " (at: " + at + ")";
    }
}
