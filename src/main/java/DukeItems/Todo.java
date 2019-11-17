package DukeItems;

import java.util.ArrayList;

public class Todo extends Task {

    protected ArrayList<String> tagList = new ArrayList<>();
    protected boolean isTagExist;

    public Todo(String description) {
        super(description);
    }

    public String getTaskType(){
        return "[T]";
    }

    public String getDue(){
        return "null";
    };

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
        return getTaskType() + " " + super.toString();
    }
}
