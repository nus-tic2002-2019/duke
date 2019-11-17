package DukeItems;

import java.util.ArrayList;

public class Deadline extends Task {

    protected String by;
    protected ArrayList<String> tagList = new ArrayList<>();
    protected boolean isTagExist;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getTaskType(){
        return "[D]";
    }

    public String getDue(){
        return by;
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
        return getTaskType() + " " + super.toString() + " (by: " + by + ")";
    }
}