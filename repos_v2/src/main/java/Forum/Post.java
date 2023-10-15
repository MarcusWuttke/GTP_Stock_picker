package Forum;

import java.time.LocalDate;

public class Post {

    private String id;
    private String postContent;
    private LocalDate postTime;

    public Post(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setPostContent (String postContent){
        this.postContent = postContent;
    }

    public void setPostTime (LocalDate postTime){
        this.postTime = postTime;
    }

    public String getPostContent(){
        return postContent;
    }

    public LocalDate getPostTime(){
        return postTime;
    }

}
